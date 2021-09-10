package com.github.myibu.algorithm.hash;

import static com.github.myibu.algorithm.ByteOperator.*;
/**
 * SHA256 algorithm
 * @author myibu
 * Created on 2021/9/10
 */
public class SHA256 {
    private final int[] state = new int[8];

    private final static int OUTPUT_BYTE_SIZE = 32;
    private static final int[] k = new int[]{
                0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b,0x59f111f1,0x923f82a4,0xab1c5ed5,
                0xd807aa98,0x12835b01,0x243185be,0x550c7dc3,0x72be5d74,0x80deb1fe,0x9bdc06a7,0xc19bf174,
                0xe49b69c1,0xefbe4786,0x0fc19dc6,0x240ca1cc,0x2de92c6f,0x4a7484aa,0x5cb0a9dc,0x76f988da,
                0x983e5152,0xa831c66d,0xb00327c8,0xbf597fc7,0xc6e00bf3,0xd5a79147,0x06ca6351,0x14292967,
                0x27b70a85,0x2e1b2138,0x4d2c6dfc,0x53380d13,0x650a7354,0x766a0abb,0x81c2c92e,0x92722c85,
                0xa2bfe8a1,0xa81a664b,0xc24b8b70,0xc76c51a3,0xd192e819,0xd6990624,0xf40e3585,0x106aa070,
                0x19a4c116,0x1e376c08,0x2748774c,0x34b0bcb5,0x391c0cb3,0x4ed8aa4a,0x5b9cca4f,0x682e6ff3,
                0x748f82ee,0x78a5636f,0x84c87814,0x8cc70208,0x90befffa,0xa4506ceb,0xbef9a3f7,0xc67178f2
    };

    private void init() {
        this.state[0] = 0x6a09e667;
        this.state[1] = 0xbb67ae85;
        this.state[2] = 0x3c6ef372;
        this.state[3] = 0xa54ff53a;
        this.state[4] = 0x510e527f;
        this.state[5] = 0x9b05688c;
        this.state[6] = 0x1f83d9ab;
        this.state[7] = 0x5be0cd19;
    }

    private void update(byte[] data, int datalen) {
        // 末尾加上bit 1
        data = appendByte(data, intToOneByte(0x80000000));
        // 补0
        if (data.length % 64 < 56) {
            data = appendBytes(data, new byte[56 - (data.length % 64)]);
        } else {
            data = appendBytes(data, new byte[64 + 56 - (data.length % 64)]);
        }
        // 添加消息长度的64位bit
        data = appendBytes(data, intTo8Bytes(datalen * 8));
        // 处理每个消息块
        for (int i = 0; i < data.length / 64; i++) {
            byte[] block = new byte[64];
            System.arraycopy(data, i*64,  block, 0, 64);
            transform(block);
        }
    }

    private void transform(byte[] data) {
        int a, b, c, d, e, f, g, h, i, j, t1, t2;
        int[] m = new int[64];

        for (i = 0, j = 0; i < 16; ++i, j += 4)
            m[i] = byteArrayToInt(data, j);

        for ( ; i < 64; ++i)
            m[i] = sig1(m[i - 2]) + m[i - 7] + sig0(m[i - 15]) + m[i - 16];

        a = this.state[0];
        b = this.state[1];
        c = this.state[2];
        d = this.state[3];
        e = this.state[4];
        f = this.state[5];
        g = this.state[6];
        h = this.state[7];

        for (i = 0; i < 64; ++i) {
            t1 = (int) (h + ep1(e) + ch(e,f,g) + k[i] + unsignedInt(m[i]));
            t2 = ep0(a) + maj(a,b,c);
            h = g;
            g = f;
            f = e;
            e = d + t1;
            d = c;
            c = b;
            b = a;
            a = t1 + t2;
        }

        this.state[0] += a;
        this.state[1] += b;
        this.state[2] += c;
        this.state[3] += d;
        this.state[4] += e;
        this.state[5] += f;
        this.state[6] += g;
        this.state[7] += h;
    }
    
    private byte[] doFinal() {
        byte[] hash = new byte[OUTPUT_BYTE_SIZE];
        for (int i = 0; i < OUTPUT_BYTE_SIZE; i = i + 4) {
            int state = this.state[i/4];
            hash[i] =  (byte)(((state & 0xFF000000) >>> 24) & 0x000000FF);
            hash[i+1] =  (byte)(((state & 0x00FF0000) >>> 16) & 0x000000FF);
            hash[i+2] =  (byte)(((state & 0x0000FF00) >>> 8) & 0x000000FF);
            hash[i+3] =  (byte)(((state & 0x000000FF)) & 0x000000FF);
        }
        return hash;
    }

    private  int rotateRight(int a, int b) {
        return (((a) >>> (b)) | ((a) << (32-(b))));
    }

    private  int ch(int x, int y, int  z)  {
        return (((x) & (y)) ^ (~(x) & (z)));
    }

    private  int maj(int x, int y, int  z) {
        return (((x) & (y)) ^ ((x) & (z)) ^ ((y) & (z)));
    }

    private  int ep0(int x) {
        return (rotateRight(x,2) ^ rotateRight(x,13) ^ rotateRight(x,22));
    }

    private  int ep1(int x) {
        return (rotateRight(x,6) ^ rotateRight(x,11) ^ rotateRight(x,25));
    }

    private  int sig0(int x) {
        return (rotateRight(x,7) ^ rotateRight(x,18) ^ ((x) >>> 3));
    }

    private  int sig1(int x) {
        return (rotateRight(x,17) ^ rotateRight(x,19) ^ ((x) >>> 10));
    }

    public byte[] encode(byte[] input) {
        init();
        update(input, input.length);
        return doFinal();
    }
}
