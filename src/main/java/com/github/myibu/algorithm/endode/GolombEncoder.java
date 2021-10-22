package com.github.myibu.algorithm.endode;

import com.github.myibu.algorithm.data.Bit;
import com.github.myibu.algorithm.data.Bits;

/**
 * Golomb code
 * see <a href="https://en.wikipedia.org/wiki/Golomb_coding#Simple_algorithm">https://en.wikipedia.org/wiki/Golomb_coding#Simple_algorithm</a>
 * @author myibu
 * Created on 2021/10/12
 */
public class GolombEncoder implements Encoder {
    /**
     * encode n to binary bits based on argument m
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
            return bits.append(encodeToBinary(r, k));
        } else {
            // truncated binary encoding
            if (r < Math.pow(2, k) - m) {
                return bits.append(encodeToTruncatedBinary(r, m));
            } else {
                return bits.append(encodeToTruncatedBinary(r, m));
            }
        }
    }

    public static Bits encodeToTruncatedBinary(int x, int n) {
        // Set k = floor(log2(n)), i.e., k such that 2^k <= n < 2^(k+1).
        int k = 0, t = n;
        while (t > 1) { k++;  t >>= 1; }

        // Set u to the number of unused codewords = 2^(k+1) - n.
        int u = (1 << k+1) - n;

        if (x < u)  return encodeToBinary(x, k);
        else  return encodeToBinary(x+u, k+1);
    }

    public static Bits encodeToBinary(int x, int len) {
        Bits s = new Bits();
        while (x != 0) {
            if ((x & 0x01) == 0)  s = Bits.ofZero().append(s);
            else  s = Bits.ofOne().append(s);
            x >>= 1;
        }
        while (s.length() < len)  s = Bits.ofZero().append(s);
        return s;
    }

    /**
     * decode binary bits to n
     * @param bits encoded binary bits
     * @param m m, like 5
     * @return decoded value
     */
    public int decode(Bits bits, int m) {
        // To decode, read the first k bits.
        // If they encode a value less than u, decoding is complete.
        // Otherwise, read an additional bit and subtract u from the result.
        boolean isRStart = false;
        Bits qb = new Bits(), rb = new Bits();
        for (Bit bit: bits) {
            if (!isRStart && bit == Bit.ZERO) {
                isRStart = true;
                continue;
            }
            if (!isRStart) {
                qb.append(bit);
            } else {
                rb.append(bit);
            }
        }
        int q = qb.length();
        int r = 0;
        if ((m & 0x01) == 0) {
            r = encodeToBinary(rb);
        } else {
            r = decodeTruncatedBinary(rb, m);
        }
        return q * m + r;
    }

    public static int decodeTruncatedBinary(Bits bits, int m) {
        // Set k = floor(log2(n)), i.e., k such that 2^k <= n < 2^(k+1).
        int k = 0, t = m;
        while (t > 1) { k++;  t >>= 1; }
        // Set u to the number of unused codewords = 2^(k+1) - n.
        int u = (1 << k+1) - m;
        int x = encodeToBinary(bits);
        return (x < u) ? x : (x - u);
    }

    public static int encodeToBinary(Bits bits) {
        int x = 0;
        for (int i = 0; i < bits.length(); i++) {
            x += (bits.get(i).value() << (bits.length() - i - 1));
        }
        return x;
    }
}
