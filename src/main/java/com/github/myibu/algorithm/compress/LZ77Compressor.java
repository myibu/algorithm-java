package com.github.myibu.algorithm.compress;

import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.endode.GolombEncoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LZ77 compress algorithm
 * @author myibu
 * Created on 2021/10/11
 */
public class LZ77Compressor implements Compressor {
    private static int DEFAULT_SEARCH_BUFFER_LENGTH = 7;
    private static int DEFAULT_LOOK_AHEAD_WINDOW_LENGTH = 5;

    /**
     * S is the length of the search buffer
     */
    private int s;
    /**
     * L is the length of the look ahead window
     */
    private int l;

    public LZ77Compressor() {
        s = DEFAULT_SEARCH_BUFFER_LENGTH;
        l = DEFAULT_LOOK_AHEAD_WINDOW_LENGTH;
    }

    /**
     * while look-ahead buffer is not empty
     *      go backwards in search buffer to find longest match of the look-ahead buffer
     *      if match found
     *          print: (offset from window boundary, length of match, next symbol in look ahead buffer);
     *          shift window by length+1;
     *      else
     *          print: (0, 0, first symbol in look-ahead buffer);
     *          shift window by 1;
     *      fi
     * end while
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    @Override
    public int compress(byte[] in_data, int in_len, byte[] out_data) {
        // no need to compress
        if (l > in_len) {
            System.arraycopy(in_data, 0, out_data, 0, in_len);
            return in_len;
        }
        List<List<Integer>> tuples = new ArrayList<>();
        // search buffer
        byte[] sBuf = new byte[s];
        // look ahead window
        byte[] lWindow = new byte[l];
        int sp = 0, lp = l, ip = 0, op = 0;
        while (lWindow.length > 0 && ip < in_len) {
            // update search buffer
            int sStart = 0, sEnd = sp < s ? sp : s;
            for (int i = sStart; i < sEnd; i++) {
                sBuf[i] = in_data[ip - i - 1];
            }
            // update look ahead window
            int lStart = 0, lEnd = ip + l < in_len ? l : in_len - ip;
            for (int i = lStart; i < lEnd; i++) {
                lWindow[i] = in_data[ip + i];
            }
            //System.out.println("txt=" + new String() + new String(in_data) + ", sBuf="
            //        +  new StringBuilder(new String(sBuf)).reverse().toString() + ", lWindow=" + new String(lWindow));

            int llStart = sEnd - 1, rrStart = 0, llEnd = 0, rrEnd = (lp = lEnd);
            int minMatched = 1, minIndex = 0;
            while (llStart >= 0) {
                int matched = 0, left = llStart, right = rrStart;
                while (left >= 0 && right < rrEnd && sBuf[left--] == lWindow[right++]) {
                    matched++;
                }
                if (matched >= minMatched) {
                    minIndex = llStart;
                    minMatched = matched;
                }
                llStart--;
            }
            // matched
            if (minIndex > 0) {
                tuples.add(Arrays.asList( minIndex + 1, minMatched, (int)lWindow[minMatched]));
                sp += (minMatched + 1);
                ip += (minMatched + 1);
            } else {
                sp++;
                ip++;
                tuples.add(Arrays.asList(0, 0, (int)lWindow[0]));
            }
        }
        // System.out.println(tuples);
        return doEncode(tuples, out_data);
    }

    private int doEncode(List<List<Integer>> tuples, byte[] out_data) {
        Bits finalRes = new Bits();
        GolombEncoder encoder = new GolombEncoder();
        for (List<Integer> tuple: tuples) {
            Bits bits = new Bits();
            Bits bits1 = encoder.encodeToBinary(tuple.get(0), (int)(Math.ceil(Math.log(s) / Math.log(2))));
            bits.append(bits1);
            Bits bits2 = encoder.encode(tuple.get(1), l);
            bits.append(bits2);
            Bits bits3 = Bits.ofByte((byte)tuple.get(2).intValue());
            bits.append(bits3);
            // System.out.println("("+ bits1 + ", "+ bits2 + ", "+ bits3 + ")");
            finalRes.append(bits);
        }
        byte[] fr = finalRes.toByteArray();
        System.arraycopy(fr, 0, out_data, 0, fr.length);
        // System.out.println("bits: " + finalRes);
        return fr.length;
    }

    /**
     * for each token (offset, length, symbol)
     *      if offset = 0 then
     *          print symbol;
     *      else
     *          go reverse in previous output by offset characters and copy
     *          character wise for length symbols;
     *          print symbol;
     *      fi
     * next
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    @Override
    public int decompress(byte[] in_data, int in_len, byte[] out_data) {
        int e1 = (int)(Math.ceil(Math.log(s) / Math.log(2)));
        GolombEncoder encoder = new GolombEncoder();
        Set<Bits> allEncodeSeq = new HashSet<>();
        for (int i = 0; i <= l; i++) {
            allEncodeSeq.add(encoder.encode(i, l));
        }
        List<Bits> sortedEncodeSeq = allEncodeSeq.stream().sorted(Comparator.comparingInt(Bits::length)).collect(Collectors.toList());
        Bits bits = Bits.ofByte(in_data);
        int ip = 0;
        List<List<Integer>> tuples = new ArrayList<>();
        while (ip < bits.length()) {
            Bits b1 = bits.subBits(ip, ip + e1);
            ip = ip + e1;
            int offset = encoder.encodeToBinary(b1);
            int length = -1;
            for (Bits sortedEncode: sortedEncodeSeq) {
                if (ip + sortedEncode.length() < bits.length()) {
                    if (sortedEncode.equals(bits.subBits(ip, ip+sortedEncode.length()))) {
                        length = encoder.decode(sortedEncode, l);
                        ip += sortedEncode.length();
                        break;
                    }
                }
            }
            if (length == -1 || ip+8 > bits.length()) {
                break;
            }
            int symbol = (int)bits.subBits(ip, ip+8).toByte();
            tuples.add(Arrays.asList(offset, length, symbol));
            ip += 8;
        }
        // System.out.println(tuples);
        return doDecode(tuples, out_data);
    }

    private int doDecode(List<List<Integer>> tuples, byte[] out_data) {
        Bits seq = new Bits();
        for (List<Integer> tuple: tuples) {
            int offset = tuple.get(0), length = tuple.get(1), symbol = tuple.get(2);
            Bits sb = Bits.ofByte((byte) symbol);
            if (offset == 0) {
                seq.append(sb);
            } else {
                int start = seq.byteLength() < s ? seq.byteLength() - offset: s - offset;
                seq.append(seq.subBits(start * 8, (start + length) * 8)).append(sb);
            }
        }
        int len = seq.byteLength();
        for (int i = 0; i < len; i++) {
            out_data[i] = seq.getByte(i).toByte();
        }
        return len;
    }
}
