package com.github.myibu.algorithm.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * SipHash 2-4 algorithm
 * @author myibu
 * Created on 2021/9/8
 */
public class SipHash {
    protected byte[] hashSeed;
    protected int c;
    protected int d;
    protected static final int DEFAULT_SEED_SIZE = 128 / 8;
    private static final String LINUX_RANDOM_FILE = "/dev/urandom";

    public SipHash() {
        this.hashSeed = generateHashSeed();
        this.c = 2;
        this.d = 4;
    }

    private byte[] generateHashSeed() {
        byte[] seed = new byte[DEFAULT_SEED_SIZE];
        boolean seedInitialized = false;
        File randomFile = Paths.get(LINUX_RANDOM_FILE).toFile();
        if (randomFile.exists() && randomFile.canRead()) {
            try {
                FileInputStream fis = new FileInputStream(randomFile);
                if (fis.read(seed, 0, seed.length) == DEFAULT_SEED_SIZE) {
                    seedInitialized = true;
                }
            } catch (IOException e) {

            }
        }
        if (!seedInitialized) {
            for (int j = 0; j < seed.length; j++) {
                long sec = System.currentTimeMillis() / 1000;
                long usec = System.nanoTime();
                long pid = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
                seed[j] = (byte)(sec ^  usec ^ pid);
            }
        }
        return seed;
    }

    public SipHash(byte[] seed) {
        this(seed, 2, 4);
    }

    protected SipHash(byte[] seed, int c, int d) {
        if (null == seed || seed.length != DEFAULT_SEED_SIZE) {
            throw new IllegalArgumentException("Seed can not be empty or the size of Seed is not equals to 16");
        }
        hashSeed = new byte[seed.length];
        System.arraycopy(seed, 0, hashSeed, 0, seed.length);
        this.c = c;
        this.d = d;
    }

    private long v0;
    private long v1;
    private long v2;
    private long v3;

    private void initialize(byte[] key) {
        v0 = 0x736f6d6570736575L;
        v1 = 0x646f72616e646f6dL;
        v2 = 0x6c7967656e657261L;
        v3 = 0x7465646279746573L;

        long k0 = u8To64LE(key, 0);
        long k1 = u8To64LE(key, 8);

        v3 ^= k1;
        v2 ^= k0;
        v1 ^= k1;
        v0 ^= k0;
    }

    private void compress(byte[] message) {
        int end = message.length / 8;
        int left = message.length & 7;
        long b = ((long)message.length) << 56;
        long m;
        for (int i = 0; i < end; i++) {
            m = u8To64LE(message, i * 8);
            v3 ^= m;
            for (int j = 0; j < c; j++) {
                sipRound();
            }
            v0 ^= m;
        }
        switch (left) {
            case 7: b |= ((long)message[6]) << 48; 
            case 6: b |= ((long)message[5]) << 40; 
            case 5: b |= ((long)message[4]) << 32; 
            case 4: b |= ((long)message[3]) << 24; 
            case 3: b |= ((long)message[2]) << 16; 
            case 2: b |= ((long)message[1]) << 8; 
            case 1: b |= ((long)message[0]); break;
            case 0: break;
        }
        v3 ^= b;
        for (int j = 0; j < c; j++) {
            sipRound();
        }
        v0 ^= b;
    }
    private long sipFinalize() {
        v2 ^= 0xff;
        for (int j = 0; j < d; j++) {
            sipRound();
        }
        return v0 ^ v1 ^ v2 ^ v3;
    }

    private long sipHash(byte[] key, byte[] message) {
        if (null == key || key.length != 16) {
            throw new IllegalArgumentException("key can not be empty or the size of key is not equals to 16");
        }
        if (null == message) {
            throw new IllegalArgumentException("message can not be empty");
        }
        initialize(key);
        compress(message);
        return sipFinalize();
    }

    private void sipRound() {
        v0 += v1;                                                            
        v1 = rotl(v1, 13);                                                   
        v1 ^= v0;                                                            
        v0 = rotl(v0, 32);                                                   
        v2 += v3;                                                            
        v3 = rotl(v3, 16);                                                   
        v3 ^= v2;                                                            
        v0 += v3;                                                            
        v3 = rotl(v3, 21);                                                   
        v3 ^= v0;                                                            
        v2 += v1;                                                            
        v1 = rotl(v1, 17);                                                   
        v1 ^= v2;                                                            
        v2 = rotl(v2, 32);                                                   
    }

    private long rotl(long x , int b) {
        return (x << b) | (x >>> (64 - b));
    }

    private long u8To64LE(byte[] p, int offset) {
        return  ((long)p[offset    ])       |
                ((long)p[offset + 1]) << 8  |
                ((long)p[offset + 2]) << 16 |
                ((long)p[offset + 3]) << 24 |
                ((long)p[offset + 4]) << 32 |
                ((long)p[offset + 5]) << 40 |
                ((long)p[offset + 6]) << 48 |
                ((long)p[offset + 7]) << 56;
    }

    public long hash(String key) {
        return sipHash(hashSeed, key.getBytes(StandardCharsets.UTF_8));
    }

    public long hash(String key, byte[] hashSeed)  {
        return sipHash(hashSeed, key.getBytes(StandardCharsets.UTF_8));
    }
}
