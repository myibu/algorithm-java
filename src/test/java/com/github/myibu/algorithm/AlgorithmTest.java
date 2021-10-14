package com.github.myibu.algorithm;

import com.github.myibu.algorithm.compress.Compressor;
import com.github.myibu.algorithm.compress.LZ77Compressor;
import com.github.myibu.algorithm.compress.LZFCompressor;
import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.data.Bytes;
import com.github.myibu.algorithm.endode.GolombEncoder;
import com.github.myibu.algorithm.filter.*;
import com.github.myibu.algorithm.hash.MurmurHash2;
import com.github.myibu.algorithm.hash.SHA256;
import com.github.myibu.algorithm.hash.SipHash;
import com.github.myibu.algorithm.random.LinearCongruentialRandom;
import com.github.myibu.algorithm.random.MersenneTwisterRandom;
import com.github.myibu.algorithm.random.Random;
import com.github.myibu.algorithm.random.RandomArrays;
import com.github.myibu.algorithm.validate.IDCardChecker;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class AlgorithmTest {
    @Test
    public void testSHA256() throws Exception {
        SHA256 sha256 = new SHA256();
        byte[] bs = sha256.encode("abc".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                Bytes.byteArrayToHexString(bs));

        bs = sha256.encode("this is a test message".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals("4e4aa09b6d80efbd684e80f54a70c1d8605625c3380f4cb012b32644a002b5be",
                Bytes.byteArrayToHexString(bs));
    }

    @Test
    public void testDefaultSipHash() throws Exception {
        SipHash sipHash = new SipHash();
        long hashRes = sipHash.hash("12345678");
        System.out.println(Long.toHexString(hashRes));
    }

    @Test
    public void testSipHash() throws Exception {
        byte[] hashSeed = new byte[]{
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
        };
        SipHash sipHash = new SipHash(hashSeed);
        long hashRes = sipHash.hash("12345678");
        Assert.assertEquals(0x2130609caea37ebL, hashRes);

        hashRes = sipHash.hash("abcdef");
        Assert.assertEquals(0x2a6e77e733c7c05dL, hashRes);
    }

    @Test
    public void testIdCardChecker() throws Exception {
        String id = "320125199301012565";
        assert IDCardChecker.check(id);

        id = "320125199301012563";
        assert !IDCardChecker.check(id);
    }

    @Test
    public void testMurmurHash2() throws Exception {
        byte[] key = "abc".getBytes(StandardCharsets.UTF_8);
        long seed = 0x9747b28c;
        long hash = MurmurHash2.hash(key, key.length, seed);
        Assert.assertEquals(0x47670dfa7a2ee3bbL, hash);
    }

    @Test
    public void testBits() throws Exception {
        Bits bits = Bits.ofInt(25);
        Assert.assertEquals(Bits.and(bits, Bits.ofInt(1)).toInt(), 1);
        Assert.assertEquals(Bits.or(bits, Bits.ofInt(0xFFFFFFe6)).toInt(), 0xFFFFFFFF);
        Assert.assertEquals(Bits.xor(bits, Bits.ofInt(25)).toInt(), 0x00000000);
        Assert.assertEquals(Bits.inverse(bits).toInt(), 0xFFFFFFe6);
    }

    @Test
    public void testBloomFilter() throws Exception {
        String[] registerName = new String[] {
                "tom", "baby", "bob", "nacy", "xiaoming",
                "linda", "tracy", "iu", "jack", "ming"
        };
        BloomFilter filter = new BloomFilter();
        filter.addAll(registerName);
        Assert.assertFalse(filter.contains("nacyy"));
        Assert.assertTrue(filter.contains("jack"));
        Assert.assertTrue(filter.contains("ming"));
        Assert.assertFalse(filter.contains("abc"));
    }

    @Test
    public void testRandomArrays() {
        Boolean[] a = new Boolean[]{true, true, false, false, true};
        RandomArrays.shuffle(a);
        System.out.println();
    }

    @Test
    public void testRandom() {
        Random rd = new LinearCongruentialRandom();
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Linear Congruential [%d]：%d", i, rd.nextInt(10)));
        }

        Random rd1 = new MersenneTwisterRandom();
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Mersenne Twister [%d]：%d", i, rd1.nextInt(10)));
        }
    }

    @Test
    public void testDictionaryTree() {
        DictionaryTree tree = new DictionaryTree();
        tree.insertAll(new String[] {"hi", "hello", "nihao", "see", "hey"});
        System.out.println(tree.search("hello, i am myibu, i see"));
    }

    @Test
    public void testSensitiveWordFilter() {
        SensitiveWordFilter filter = new DFASensitiveWordFilter();
        filter.addWords(Set.of("黄色", "绿色", "红色"));
        System.out.println(filter.searchWords("昨天我过马路的时候先遇到红色灯，再遇到绿灯"));

        SensitiveWordFilter filter1 = new AhoCorasickSensitiveWordFilter();
        filter1.addWords(Set.of("黄色", "绿色", "红色"));
        System.out.println(filter1.searchWords("昨天我过马路的时候先遇到红色灯，再遇到绿灯"));
    }

    @Test
    public void testLZFCompressor() {
        /**
         * 1111122222
         * hex:    01-31-31-20-00-00-32-20-00-00-32
         * binary: 01-49-49-32-00-00-50-32-00-00-50
         *
         * 111112222233333
         * hex:    01-31-31-20-00-00-32-40-00-00-33-20-00-01-33-33
         * binary: 01-49-49-32-00-00-50-64-00-00-51-32-00-01-51-51
         *
         * 111112222233333344444
         * hex:    01-31-31-20-00-00-32-40-00-00-33-60-00-00-34-20-00-00-34
         * binary: 01-49-49-32-00-00-50-64-00-00-51-96-00-00-52-32-00-00-52
         *
         * this is a test
         * hex:    04-74-68-69-73-20-20-02-05-61-20-74-65-73-74
         * binary: 04-116-104-105-115-32-32-02-05-97-32-116-101-115-116
         */
//       [4, 116, 104, 105, 115, 32, 32, 2, 5, 97, 32, 116, 101, 115, 116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
//        byte[] in_data = "1111122222".getBytes(StandardCharsets.UTF_8);
//        byte[] in_data = "111112222233333344444".getBytes(StandardCharsets.UTF_8);
//        byte[] in_data = "this is a test".getBytes(StandardCharsets.UTF_8);
        byte[] in_data = "Type \"help\", \"copyright\", \"credits\" or \"license\" for more information.".getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        LZFCompressor com = new LZFCompressor();
        int op = com.compress(in_data, in_data.length, out_data);
        byte[] decompress_data = new byte[out_data.length * 2];
        op = com.decompress(out_data, op, decompress_data);
        System.out.println(Arrays.toString(out_data));
    }

    @Test
    public void testLZ77Compressor() {
        // todo
        byte[] in_data = "com.github.myibu.algorithm.AlgorithmTest.testLZ77Compressor".getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        Compressor compressor = new LZ77Compressor();
        int compressed = compressor.compress(in_data, in_data.length, out_data);
        byte[] compressed_data = Arrays.copyOf(out_data, compressed);
//        System.out.println(new String(compressed_data));
        byte[] decompressed_data = new byte[compressed * 2];
        int decompressed = compressor.decompress(compressed_data, compressed, decompressed_data);
        Assert.assertEquals("com.github.myibu.algorithm.AlgorithmTest.testLZ77Compressor",
                new String(Arrays.copyOf(decompressed_data, decompressed), StandardCharsets.UTF_8));
    }

    @Test
    public void testGolombEncoder() {
        int m = 4;
        GolombEncoder encoder = new GolombEncoder();
        System.out.println(encoder.encode(0, 5));
        System.out.println(encoder.encode(1, 5));
        System.out.println(encoder.encode(4, 5));
        List<Bits> encodeList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            encodeList.add(encoder.encode(i, m));
        }
        for (int i = 0; i < encodeList.size(); i++) {
            Bits bits = encodeList.get(i);
            System.out.println("encode " + (i+1) + " => " + bits);
            System.out.println("decode " + bits + " => " + encoder.decode(bits, m));
        }
    }
}
