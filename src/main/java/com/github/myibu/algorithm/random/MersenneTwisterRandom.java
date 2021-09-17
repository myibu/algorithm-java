package com.github.myibu.algorithm.random;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Mersenne Twister method for generating Pseudo Random Numbers
 * MT19937
 * @author myibu
 * Created on 2021/9/17
 */
public class MersenneTwisterRandom implements Random {
    static final int MT19937_SIZE = 624; //  624 * 32 - 31 = 19937
    static final String BadBound = "bound must be positive";
    private static final double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53)

    private final long seed;
    private long[] mt;

    public MersenneTwisterRandom() {
        this((int)(seedUniquifier() ^ System.nanoTime()));
    }

    public MersenneTwisterRandom(long seed) {
        this.seed = seed;
        this.mt = new long[MT19937_SIZE];
        mt[0] = seed;
        for (int i = 1; i < MT19937_SIZE; i++) {
            mt[i] = 1812433253L * (mt[i-1] ^ (mt[i-1] >> 30)) + i;
        }
    }

    private static long seedUniquifier() {
        for (;;) {
            long current = seedUniquifier.get();
            long next = current * 1181783497276652981L;
            if (seedUniquifier.compareAndSet(current, next))
                return next;
        }
    }

    private static final AtomicLong seedUniquifier
            = new AtomicLong(8682522807148012L);

    private void generate() {
        for (int i = 0; i < MT19937_SIZE; i++) {
            // 2^31 = 0x80000000, 2^31-1 = 0x7fffffff
            long y = (mt[i] & 0x80000000L) + (mt[(i+1) % 624] & 0x7fffffffL);
            mt[i] = mt[(i + 397) % 624] ^ (y >> 1);
            if ((y & 1) != 0)
                mt[i] ^= 0x9908b0dfL;
        }
    }

    @Override
    public void nextBytes(byte[] bytes) {
        for (int i = 0, len = bytes.length; i < len; )
            for (int rnd = nextInt(),
                 n = Math.min(len - i, Integer.SIZE/Byte.SIZE);
                 n-- > 0; rnd >>= Byte.SIZE)
                bytes[i++] = (byte)rnd;
    }

    @Override
    public int nextInt() {
        generate();
        long y = mt[0];
        y = y ^ (y >> 11);
        y = y ^ ((y << 7) & 2636928640L);
        y = y ^ ((y << 15) & 4022730752L);
        y = y ^ (y >> 18);
        return (int)y;
    }

    @Override
    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);

        int r = nextInt();
        int m = bound - 1;
        if ((bound & m) == 0)  // i.e., bound is a power of 2
            r = (int)((bound * (long)r) >> 31);
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0;
                 u = nextInt())
                ;
        }
        return r;
    }

    @Override
    public long nextLong() {
        return ((long)(nextInt()) << 32) + nextInt();
    }

    @Override
    public boolean nextBoolean() {
        return (nextInt() & 0x00000001) != 0;
    }

    @Override
    public float nextFloat() {
        return nextInt() / ((float)(1 << 24));
    }

    @Override
    public double nextDouble() {
        return (((long)(nextInt()) << 27) + nextInt()) * DOUBLE_UNIT;
    }
}
