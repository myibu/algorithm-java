package com.github.myibu.algorithm.random;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Linear Congruence method for generating Pseudo Random Numbers
 * Copy from "java.util.Random"
 * @author myibu
 * Created on 2021/9/17
 */
public class LinearCongruentialRandom implements Random {
    static final String BadBound = "bound must be positive";
    static final String BadRange = "bound must be greater than origin";
    static final String BadSize  = "size must be non-negative";

    private final AtomicLong seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    private static final double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53)

    public LinearCongruentialRandom() {
        this(seedUniquifier() ^ System.nanoTime());
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

    public LinearCongruentialRandom(long seed) {
        this.seed = new AtomicLong(initialScramble(seed));
    }

    private static long initialScramble(long seed) {
        return (seed ^ multiplier) & mask;
    }

    public synchronized void setSeed(long seed) {
        this.seed.set(initialScramble(seed));
    }

    public int next(int bits) {
        long oldseed, nextseed;
        AtomicLong seed = this.seed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
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
        return next(32);
    }

    @Override
    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);

        int r = next(31);
        int m = bound - 1;
        if ((bound & m) == 0)  // i.e., bound is a power of 2
            r = (int)((bound * (long)r) >> 31);
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0;
                 u = next(31))
                ;
        }
        return r;
    }

    @Override
    public long nextLong() {
        return ((long)(next(32)) << 32) + next(32);
    }

    public boolean nextBoolean() {
        return next(1) != 0;
    }

    @Override
    public float nextFloat() {
        return next(24) / ((float)(1 << 24));
    }

    @Override
    public double nextDouble() {
        return (((long)(next(26)) << 27) + next(27)) * DOUBLE_UNIT;
    }
}
