package com.github.myibu.algorithm.compress;

import com.github.myibu.algorithm.data.Bits;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * LZW compress algorithm
 * @author myibu
 * Created on 2021/10/25
 */
public class LZWCompressor implements Compressor {
    private static final int BITS = 12;
    private static final int MAX_CODE = (1 << BITS) - 1;
    private static final Map<String, Integer> STANDARD_STR_TO_CODE_DICT;
    private static final Map<Integer, String> STANDARD_CODE_TO_STR_DICT;
    static {
        STANDARD_STR_TO_CODE_DICT = new HashMap<>();
        STANDARD_CODE_TO_STR_DICT = new HashMap<>();
        byte b = Byte.MIN_VALUE;
        int i = 0;
        for (; b < Byte.MAX_VALUE; b++, i++) {
            STANDARD_STR_TO_CODE_DICT.put("" + ((char)b), i);
            STANDARD_CODE_TO_STR_DICT.put(i, "" + ((char)b));
        }
    }
    /**
     * s = empty string;
     * while (there is still data to be read)
     * {
     *     ch = read a character;
     *     if (dictionary contains s+ch)
     *     {
     * 	        s = s+ch;
     *     }
     *     else
     *     {
     * 	        encode s to output file;
     * 	        add s+ch to dictionary;
     * 	        s = ch;
     *     }
     * }
     * encode s to output file;
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    @Override
    public int compress(byte[] in_data, int in_len, byte[] out_data) {
        Map<String, Integer> str2CodeDict = new HashMap<>(STANDARD_STR_TO_CODE_DICT);
        String s = "";
        int ip = 0;
        Bits seq = new Bits();
        while (ip < in_len) {
            byte ch = in_data[ip++];
            if (str2CodeDict.containsKey(s + (char)ch)) {
                s = s + (char)ch;
            } else {
                if (isDebug) {
                    System.out.println("entry=" + s + ", code="+ str2CodeDict.get(s));
                    if (s.equals("t")) {
                        System.out.println();
                    }
                }
                seq.append(Bits.ofBits(str2CodeDict.get(s), BITS));
                str2CodeDict.put(s + (char)ch, str2CodeDict.size());
                s = "" + (char)ch;
            }
        }
        seq.append(Bits.ofBits(str2CodeDict.get(s), BITS));
        if (isDebug) {
            System.out.println("entry=" + s + ", code="+ str2CodeDict.get(s));
        }
        byte[] fr = seq.toByteArray();
        System.arraycopy(fr, 0, out_data, 0, fr.length);
        if (isDebug) {
            System.out.println("after encode: bits=" + seq);
            System.out.println("after encode: compressed rate=" + new BigDecimal(fr.length * 100.0 / in_len).setScale(2, RoundingMode.HALF_UP) + "%");
        }
        return fr.length;
    }

    /**
     * prevcode = read in a code;
     * decode/output prevcode;
     * while (there is still data to read)
     * {
     *     currcode = read in a code;
     *     entry = translation of currcode from dictionary;
     *     output entry;
     *     ch = first char of entry;
     *     add ((translation of prevcode)+ch) to dictionary;
     *     prevcode = currcode;
     * }
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    @Override
    public int decompress(byte[] in_data, int in_len, byte[] out_data) {
        Map<Integer, String> code2StrDict = new HashMap<>(STANDARD_CODE_TO_STR_DICT);
        Bits bits = Bits.ofByte(in_data);
        if (isDebug) {
            System.out.println("before decode: bits=" + bits);
        }
        Bits seq = new Bits();
        int ip = 0;
        Bits b1 = bits.subBits(ip, ip + BITS);
        int preCode = b1.toInt();
        String entry = code2StrDict.get(preCode);
        if (isDebug) {
            System.out.println("code="+ preCode + ", entry=" + entry);
        }
        seq.append(Bits.ofRawString(entry));
        ip += BITS;
        while (ip < bits.length() && ip + BITS <= bits.length()) {
            b1 = bits.subBits(ip, ip + BITS);
            int curCode = b1.toInt();
            if (code2StrDict.containsKey(curCode)) {
                entry = code2StrDict.get(curCode);
            } else {
                entry = code2StrDict.get(preCode);
                entry = entry + entry.charAt(0);
            }
            if (isDebug) {
                System.out.println("code="+ curCode + ", entry=" + entry);
            }
            seq.append(Bits.ofRawString(entry));
            char ch = entry.charAt(0);
            code2StrDict.put(code2StrDict.size(), code2StrDict.get(preCode) + ch);
            preCode = curCode;
            ip += BITS;
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
}
