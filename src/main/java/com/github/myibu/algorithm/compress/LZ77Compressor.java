package com.github.myibu.algorithm.compress;

import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.endode.GolombEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            // abracadabrad
            // update search buffer
            int sStart = 0, sEnd = sp < s ? sp : s;
            for (int i = sStart; i < sEnd; i++) {
                System.out.println("ip=" + ip + ", i=" + i + ", sEnd=" + sEnd + ", sp=" + sp);
                sBuf[i] = in_data[ip - i - 1];
            }
            // update look ahead window
            int lStart = 0, lEnd = ip + l < in_len ? l : in_len - ip;
            for (int i = lStart; i < lEnd; i++) {
                lWindow[i] = in_data[ip + i];
            }
            System.out.println("all=abracadabrad, sBuf=" +  new StringBuilder(new String(sBuf)).reverse().toString() + ", lWindow=" + new String(lWindow));

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
            System.out.println("minIndex=" +  minIndex + ", all=abracadabrad, sBuf=" +  new StringBuilder(new String(sBuf)).reverse().toString() + ", lWindow=" + new String(lWindow));
            // matched
            if (minIndex > 0) {
//                byte[] tuple = String.format("(%d,%d,%s)", minIndex + 1, minMatched, new String(new byte[]{lWindow[minMatched]})).getBytes();
//                System.arraycopy(tuple, 0, out_data, (op++) * tuple.length, tuple.length);
                System.out.println(String.format("(%d, %d, %s)", minIndex + 1, minMatched, new String(new byte[]{lWindow[minMatched]})));
                tuples.add(Arrays.asList( minIndex + 1, minMatched, (int)lWindow[minMatched]));
                sp += (minMatched + 1);
//                if (sp > s) {
//                    sp = s-1;
//                }
                ip += (minMatched + 1);
            } else {
                sp++;
//                if (sp > s) {
//                    sp = s-1;
//                }
                ip++;
//                byte[] tuple = String.format("(%d,%d,%s)", 0, 0, new String(new byte[]{lWindow[0]})).getBytes();
//                System.arraycopy(tuple, 0, out_data, (op++) * tuple.length, tuple.length);
                System.out.println(String.format("(%d, %d, %s)", 0, 0, new String(new byte[]{lWindow[0]})));
                tuples.add(Arrays.asList(0, 0, (int)lWindow[0]));
            }
        }
        System.out.println(tuples);
        int sum = 0;
        GolombEncoder encoder = new GolombEncoder();
        for (List<Integer> tuple: tuples) {
            Bits bits = new Bits();
            bits.append(encoder.encodeToBinary(tuple.get(0), (int)(Math.ceil(Math.log(s) / Math.log(2)))));
            System.out.println("1" + bits);
            bits.append(encoder.encode(tuple.get(1), 5));
            System.out.println("2" + bits);
            bits.append(Bits.ofByte((byte)tuple.get(2).intValue()));
            System.out.println("3" + bits);
            sum += bits.length();
        }
        System.out.println("compressed length: " + sum);
        return 0;
    }

//    private int indexOf(int llStart, int rrStart, int llEnd, int rrEnd, byte[] sBuf, byte[] lWindow) {
//        int minMatched = 1, minIndex = 0;
//        while (llStart >= 0) {
//            int matched = 0, left = llStart, right = rrStart;
//            while (left >= 0 && right < rrEnd && sBuf[left--] == lWindow[right++]) {
//                matched++;
//            }
//            if (matched >= minMatched) {
//                minIndex = llStart;
//                minMatched = matched;
//            }
//            llStart--;
//        }
//        System.out.println("minIndex=" +  minIndex + ", all=abracadabrad, sBuf=" +  new StringBuilder(new String(sBuf)).reverse().toString() + ", lWindow=" + new String(lWindow));
//        return minIndex;
//    }

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
        return 0;
    }
}
