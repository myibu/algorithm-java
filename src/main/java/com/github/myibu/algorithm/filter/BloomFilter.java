package com.github.myibu.algorithm.filter;

import com.github.myibu.algorithm.data.Bit;
import com.github.myibu.algorithm.hash.MurmurHash2;
import java.nio.charset.StandardCharsets;

/**
 * bloom filter
 * @author myibu
 * Created on 2021/9/14
 */
public final class BloomFilter {
    private final static int DEFAULT_CAPACITY = 100;
    private final static double DEFAULT_ERROR_RATE = 0.01;

    private final Bloom bloom;

    public BloomFilter() {
        this(DEFAULT_CAPACITY, DEFAULT_ERROR_RATE);
    }

    public BloomFilter(int capacity, double errorRate) {
        Bloom bloom = new Bloom();
        bloom.init(capacity, errorRate);
        this.bloom = bloom;
    }

    public void addAll(String[] values) {
        for (String value: values) {
            add(value);
        }
    }
    public void add(String value) {
        byte[] buffer = (null == value) ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
        bloom.add(buffer, buffer.length);
    }

    public boolean contains(String value) {
        byte[] buffer = (null == value) ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
        return bloom.check(buffer, buffer.length);
    }

    static class Bloom {
        /**
         * Size of Bit Array
         */
        int size;
        /**
         * Probability of False positivity
         */
        double fpProb;
        /**
         * Optimum number of hash functions
         */
        int hashCount;
        /**
         * bit Array
         */
        Bit[] bitArray;

        private int calcSize(int n, double p) {
            double m = -(n * Math.log(p))/(Math.log(2) * Math.log(2));
            return (int)(m);
        }

        private int calcHashCount(int m, int n) {
            double k = (m * 1.0 / n) * Math.log(2);
            return (int)(k);
        }

        private BloomHashVal calcHashVal(byte[] buffer, int len) {
            BloomHashVal rv = new BloomHashVal();
            rv.a = MurmurHash2.hash(buffer, len, 0x9747b28c);
            rv.b = MurmurHash2.hash(buffer, len, rv.a);
            return rv;
        }

        void init(int itemCount, double fpProb) {
            this.fpProb = fpProb;
            this.size = calcSize(itemCount, fpProb);
            this.hashCount = calcHashCount(this.size, itemCount);
            this.bitArray = new Bit[this.size];
            for (int i = 0, len = bitArray.length; i < len; i++)
                bitArray[i] = Bit.ZERO;
        }

        void add(byte[] buffer, int len) {
            BloomHashVal hashVal = calcHashVal(buffer, len);
            for (int i = 0; i < this.hashCount; i++) {
                int digest = (int)(Math.abs(hashVal.a + i * hashVal.b) % this.size);
                this.bitArray[digest] = Bit.ONE;
            }
        }

        boolean check(byte[] buffer, int len) {
            BloomHashVal hashVal = calcHashVal(buffer, len);
            for (int i = 0; i < this.hashCount; i++) {
                int digest = (int)(Math.abs(hashVal.a + i * hashVal.b) % this.size);
                if (this.bitArray[digest] == Bit.ZERO) return false;
            }
            return true;
        }
    }

    static class BloomHashVal {
        long a;
        long b;
    }
}
