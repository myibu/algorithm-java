package com.github.myibu.algorithm.endode;

import com.github.myibu.algorithm.data.Bits;

/**
 * Golomb code
 * see <a herf="https://en.wikipedia.org/wiki/Golomb_coding#Simple_algorithm">https://en.wikipedia.org/wiki/Golomb_coding#Simple_algorithm</a>
 * @author myibu
 * Created on 2021/10/12
 */
public class GolombEncoder implements Encoder {
    /**
     *
     * @param n the value to encode
     * @param m m, like 5
     * @return the length of encoded bits
     */
    public Bits encode(int n, int m) {
        Bits bits = new Bits();
        // 向下取整
        int q = (int)Math.floor(n * 1.0 / m);
        bits.append(Bits.ofOne(Math.max(0, q))).append(Bits.ofZero(1));
        int r = n % m;
        int k = (int)(Math.ceil(Math.log(m) / Math.log(2)));
        if ((m & 0x01) == 0) {
            return bits.append(encodeToStandardBinary(r, k));
        } else {
            // truncated binary encoding
            if (r < Math.pow(2, k) - m) {
                return bits.append(encodeToTruncatedBinary(r, m));
            } else {
                return bits.append(encodeToTruncatedBinary(r, m));
            }
        }
    }

    private Bits encodeToStandardBinary(int x, int len) {
        return encodeToBinary(x, len);
    }

    private Bits encodeToTruncatedBinary(int x, int n) {
        // Set k = floor(log2(n)), i.e., k such that 2^k <= n < 2^(k+1).
        int k = 0, t = n;
        while (t > 1) { k++;  t >>= 1; }

        // Set u to the number of unused codewords = 2^(k+1) - n.
        int u = (1 << k+1) - n;

        if (x < u)  return encodeToBinary(x, k);
        else  return encodeToBinary(x+u, k+1);
    }

    private Bits encodeToBinary(int x, int len) {
        Bits s = new Bits();
        while (x != 0) {
            if ((x & 0x01) == 0)  s = Bits.ofZero().append(s);
            else  s = Bits.ofOne().append(s);
            x >>= 1;
        }
        while (s.length() < len)  s = Bits.ofZero().append(s);
        return s;
    }
}
