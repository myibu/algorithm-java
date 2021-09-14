package com.github.myibu.algorithm.hash;

import static com.github.myibu.algorithm.data.Bytes.byteArrayToUnsignedInt;

/**
 * MurmurHash2 algorithm
 * @author myibu
 * Created on 2021/9/13
 */
public class MurmurHash2 {
    public static long hash(byte[] key, int len, long seed) {
        long m = 0x5bd1e995;
        int r = 24;

        long h = seed ^ len;
        int offset = 0;
        while (len >= 4) {
            long k = byteArrayToUnsignedInt(key, offset);

            k *= m;
            k ^= k >> r;
            k *= m;

            h *= m;
            h ^= k;

            offset += 4;
            len -= 4;
        }

        switch (len) {
            case 3:
                h ^= key[2] << 16;
            case 2:
                h ^= key[1] << 8;
            case 1:
                h ^= key[0];
                h *= m;
        };

        h ^= h >> 13;
        h *= m;
        h ^= h >> 15;

        return h;
    }
}
