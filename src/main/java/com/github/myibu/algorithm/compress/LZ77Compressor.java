package com.github.myibu.algorithm.compress;

import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.endode.GolombEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            if (lEnd < l) {
                lWindow = new byte[lEnd];
            }
            for (int i = lStart; i < lEnd; i++) {
                lWindow[i] = in_data[ip + i];
            }
            int llStart = sEnd - 1, rrStart = 0, llEnd = 0, rrEnd = (lp = lEnd);
            int minMatched = 1, minIndex = 0;
            for (int i = llStart; i >= 0; i--) {
                int matched = 0, left = i, right = rrStart;
                while (left >= llEnd && right < rrEnd && sBuf[left--] == lWindow[right++]) {
                    matched++;
                }
                if (matched >= minMatched) {
                    minIndex = i;
                    minMatched = matched;
                }
            }
            int lWindowLen = lWindow.length;
            // only one byte in window, set tuple to (0, 0, lWindow[0])
            if (lWindowLen == 1) {
                minIndex = 0;
            }
            // matched
            if (minIndex > 0) {
                tuples.add(Arrays.asList( minIndex + 1, minMatched, (minMatched == lWindowLen) ? null : (int)lWindow[minMatched]));
                sp += ((minMatched == lWindowLen) ? minMatched : (minMatched + 1));
                ip += ((minMatched == lWindowLen) ? minMatched : (minMatched + 1));
            } else {
                sp++;
                ip++;
                tuples.add(Arrays.asList(0, 0, (int)lWindow[0]));
            }
            if (isDebug) {
                System.out.println(", SearchBuffer="
                        +  new StringBuilder(new String(sBuf)).reverse().toString() + ", LookaheadWindow=" + new String(lWindow)
                        + " | " + tuples.get(tuples.size()-1)/* + " | " + (char)(tuples.get(tuples.size()-1).get(2).intValue())*/);
            }
        }
        int compressedLen = doEncode(tuples, out_data);
        if (isDebug) {
            System.out.println("after encode: compressed rate=" + new BigDecimal(compressedLen * 100.0 / in_len).setScale(2, RoundingMode.HALF_UP) + "%");
        }
        return compressedLen;
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
            Bits bits3 = new Bits();
            if (tuple.get(2) != null) {
                bits3 = Bits.ofByte((byte) tuple.get(2).intValue());
                bits.append(bits3);
            }
            if (isDebug) {
                System.out.println(tuple + " encoded result: " + "("+ bits1 + ", "+ bits2 + ", "+ bits3 + ")");
            }
            finalRes.append(bits);
        }
        byte[] fr = finalRes.toByteArray();
        System.arraycopy(fr, 0, out_data, 0, fr.length);
        if (isDebug) {
            System.out.println("after encode: bits=" + finalRes);
        }
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
        if (isDebug) {
            System.out.println("before decode: bits=" + bits);
        }
        int ip = 0;
        List<List<Integer>> tuples = new ArrayList<>();
        while (ip < bits.length() && ip + e1 <= bits.length()) {
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
            if (length == -1 ) {
                break;
            }
            if (length != l && ip + 8 <= bits.length()) {
                int symbol = (int) bits.subBits(ip, ip + 8).toByte();
                tuples.add(Arrays.asList(offset, length, symbol));
                ip += 8;
            } else {
                tuples.add(Arrays.asList(offset, length, null));
            }
        }
        if (isDebug) {
            System.out.println("decode tuples=" + tuples);
        }
        return doDecode(tuples, out_data);
    }

    private int doDecode(List<List<Integer>> tuples, byte[] out_data) {
        Bits seq = new Bits();
        for (List<Integer> tuple: tuples) {
            int offset = tuple.get(0), length = tuple.get(1);
            if (tuple.get(2) != null) {
                int symbol = tuple.get(2);
                Bits sb = Bits.ofByte((byte) symbol);
                if (offset == 0) {
                    seq.append(sb);
                    if (isDebug) {
                        System.out.println(tuple + ", seq=" + new String(seq.toByteArray()));
                    }
                } else {
                    int start = seq.byteLength() < s ? seq.byteLength() - offset: s - offset;
                    int used = seq.byteLength() < s ? 0 : seq.byteLength() - s;
                    seq.append(seq.subBits((used + start) * 8, (used + start + length) * 8)).append(sb);
                    if (isDebug) {
                        System.out.println(tuple + ", seq=" + new String(seq.toByteArray()));
                    }
                }
            } else {
                int start = seq.byteLength() < s ? seq.byteLength() - offset: s - offset;
                int used = seq.byteLength() < s ? 0 : seq.byteLength() - s;
                seq.append(seq.subBits((used + start) * 8, (used + start + length) * 8));
                if (isDebug) {
                    System.out.println(tuple + ", seq=" + new String(seq.toByteArray()));
                }
            }
        }
        if (isDebug) {
            System.out.println("after decode, bits=" + seq);
        }
        int len = seq.byteLength();
        for (int i = 0; i < len; i++) {
            out_data[i] = seq.getByte(i).toByte();
        }
        return len;
    }


    private boolean isDebug = false;

    @Override
    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void setSL(int s, int l) {
        this.s = s;
        this.l = l;
    }
}
