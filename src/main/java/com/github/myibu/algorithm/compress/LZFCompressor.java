package com.github.myibu.algorithm.compress;

import java.nio.charset.StandardCharsets;

/**
 * LZF compress algorithm
 * @author myibu
 * Created on 2021/10/11
 */
public class LZFCompressor implements Compressor {
    private static final int LZF_HSLOT_BIAS = 0;

    private static final int HLOG = 16;
    private static final int HSIZE = (1 << HLOG);

    private static final int MAX_LIT = (1 <<  5);
    private static final int MAX_OFF = (1 << 13);
    private static final int MAX_REF = ((1 << 8) + (1 << 3));

    private static int FRST(byte[] p, int offset) {
        return ((p[offset+0] << 8) | p[offset+1]);
    }

    private static int NEXT(int v, byte[] p, int offset) {
        return ((v << 8) | p[offset+2]);
    }

    private static int IDX(int h) {
        return ((((h ^ (h << 5)) >> (3*8 - HLOG)) - h*5) & (HSIZE - 1));
    }

    @Override
    public int compress(byte[] in_data, int in_len, byte[] out_data) {
        int out_len = out_data.length;
        if (in_len == 0 || out_len == 0)
            return 0;
        int ip = 0, op = 0;
        int lit;
        lit = 0; op++; /* start run */
        int hslot;
        int[] htab = new int[HSIZE];
        int ref;
        int off;

        int hval = FRST(in_data, ip);
        while (ip < in_len - 2) {
            hval = NEXT(hval, in_data, ip);
            hslot = IDX(hval);
            ref = htab[hslot];
            htab[hslot] = ip;

            if ((off = ip - ref - 1) < MAX_OFF
                && ref > 0
                && in_data[ref + 2] == in_data[ip + 2]
                && ((in_data[ref+1] << 8) | in_data[ref+0]) == ((in_data[ip+1] << 8) | in_data[ip+0])) {
                /* match found at *ref++ */
                int len = 2;
                int maxlen = in_len - ip - len;
                maxlen = maxlen > MAX_REF ? MAX_REF : maxlen;

                if (op + 3 + 1 >= out_len) /* first a faster conservative test */
                    if (op - (lit==0 ? 1: 0) + 3 + 1 >= out_len) /* second the exact but rare test */
                        return 0;

                out_data[op- lit - 1] = (byte)(lit - 1); /* end run */
                op -= (lit==0 ? 1: 0); /* undo run if length is zero */

                do
                    len++;
                while (len < maxlen && in_data[ref+len] == in_data[ip+len]);

                len -= 2; /* len is now #octets - 1 */
                ip++;

                if (len < 7) {
                    out_data[op++] = (byte)((off >> 8) + (len << 5));
                } else {
                    out_data[op++] = (byte)((off >> 8) + (  7 << 5));
                    out_data[op++] = (byte)(len - 7);
                }

                out_data[op++] = (byte)(off);

                lit = 0; op++; /* start run */

                ip += len+1;

                if (ip >= in_len - 2)
                    break;

                hval = FRST(in_data, ip);

                hval = NEXT(hval, in_data, ip);
                htab[IDX(hval)] = ip - LZF_HSLOT_BIAS;
                ip++;

                ip -= len + 1;

                do
                {
                    hval = NEXT(hval, in_data, ip);
                    htab[IDX(hval)] = ip - LZF_HSLOT_BIAS;
                    ip++;
                }
                while ((len--) > 0);
            }
            else {
                lit++;
                out_data[op++] = in_data[ip++];
                if (lit == MAX_LIT) {
                    out_data[op- lit - 1] = (byte)(lit - 1); /* stop run */
                    lit = 0; op++; /* start run */
                }
            }
        }

        if (op + 3 > out_len) /* at most 3 bytes can be missing here */
            return 0;

        while (ip < in_len) {
            lit++;
            out_data[op++] = in_data[ip++];
            if (lit == MAX_LIT) {
                out_data[op- lit - 1] = (byte)(lit - 1); /* stop run */
                lit = 0; op++; /* start run */
            }
        }

        out_data[op- lit - 1] = (byte)(lit - 1); /* end run */
        op -= (lit==0 ? 1: 0); /* undo run if length is zero */

        return op;
    }

    @Override
    public int decompress(byte[] in_data, int in_len, byte[] out_data) {
        int out_len = out_data.length;
        int ip = 0, op = 0;
        while (ip < in_len) {
            int ctrl;
            ctrl = in_data[ip++];

            if (ctrl < (1 << 5)) /* literal run */ {
                    ctrl++;

                if (op + ctrl > out_len) {
                    if (isDebug) {
                        System.out.println("SET_ERRNO (E2BIG);");
                    }
                    return 0;
                }

                do
                    out_data[op++] = in_data[ip++];
                while ((--ctrl) > 0);
            }
            else /* back reference */ {
                int len = ctrl >> 5;
                int ref = op - ((ctrl & 0x1f) << 8) - 1;
                if (len == 7)
                    len += in_data[ip++];

                ref -= in_data[ip++];

                if (op + len + 2 > out_len) {
                    if (isDebug) {
                        System.out.println("SET_ERRNO (E2BIG);");
                    }
                    return 0;
                }

                if (ref < 0) {
                    if (isDebug) {
                        System.out.println("SET_ERRNO (EINVAL);");
                    }
                    return 0;
                }
                out_data[op++] = out_data[ref++];
                out_data[op++] = out_data[ref++];
                do
                    out_data[op++] = out_data[ref++];
                while ((--len) > 0);
            }
        }
        return op;
    }

    private boolean isDebug;

    @Override
    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }
}
