package com.github.myibu.algorithm;

import com.github.myibu.algorithm.compress.*;
import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.data.Bytes;
import com.github.myibu.algorithm.endode.GolombEncoder;
import com.github.myibu.algorithm.endode.HoffmanEncoder;
import com.github.myibu.algorithm.filter.*;
import com.github.myibu.algorithm.hash.MurmurHash2;
import com.github.myibu.algorithm.hash.SHA256;
import com.github.myibu.algorithm.hash.SipHash;
import com.github.myibu.algorithm.id.SnowflakeIdWorker;
import com.github.myibu.algorithm.random.LinearCongruentialRandom;
import com.github.myibu.algorithm.random.MersenneTwisterRandom;
import com.github.myibu.algorithm.random.Random;
import com.github.myibu.algorithm.random.RandomArrays;
import com.github.myibu.algorithm.sort.*;
import com.github.myibu.algorithm.validate.IDCardChecker;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
        Assert.assertEquals(150, Bits.ofString("10010110").toInt());
        Assert.assertEquals(-150, Bits.ofString("11111111111111111111111101101010").toInt());
        Assert.assertEquals(-1, Bits.ofString("11111111111111111111111111111111").toInt());
        Assert.assertEquals(-55, Bits.ofString("11111111111111111111111111001001").toInt());
        Assert.assertEquals(-2, Bits.ofString("0011111111111111111111111111111110").toInt());
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
        String txt = "Minimum size reduction in bytes to store compressed quicklistNode data. This also prevents us from storing compression if the compression resulted in a larger size than the original data.";
        byte[] in_data = txt.getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        LZFCompressor com = new LZFCompressor();
        com.setDebug(true);
        int compressed = com.compress(in_data, in_data.length, out_data);
        System.out.println("compressed: " + compressed);
        byte[] compressed_data = Arrays.copyOf(out_data, compressed);
        byte[] decompressed_data = new byte[in_data.length];
        int decompressed = com.decompress(compressed_data, compressed, decompressed_data);
        System.out.println("decompressed: " + decompressed);
        Assert.assertEquals(txt,
                new String(Arrays.copyOf(decompressed_data, decompressed), StandardCharsets.UTF_8));
    }

    @Test
    public void testLZ77Compressor() {
        String txt = "abracadabradabracadabradabracadabradabracadabradabracadabradabracadabradabracadabradabracadabradabracadabrad";
        byte[] in_data = txt.getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        Compressor compressor = new LZ77Compressor();
        compressor.setDebug(true);
        int compressed = compressor.compress(in_data, in_data.length, out_data);
        byte[] compressed_data = Arrays.copyOf(out_data, compressed);
        byte[] decompressed_data = new byte[in_data.length];
        int decompressed = compressor.decompress(compressed_data, compressed, decompressed_data);
        Assert.assertEquals(txt,
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

    @Test
    public void testLZWCompressor() {
        String txt = "banana_bandanatttttttttttt";
        byte[] in_data = txt.getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        Compressor compressor = new LZWCompressor();
        compressor.setDebug(true);
        int compressed = compressor.compress(in_data, in_data.length, out_data);
        byte[] compressed_data = Arrays.copyOf(out_data, compressed);
        byte[] decompressed_data = new byte[in_data.length];
        int decompressed = compressor.decompress(compressed_data, compressed, decompressed_data);
        Assert.assertEquals(txt,
                new String(Arrays.copyOf(decompressed_data, decompressed), StandardCharsets.UTF_8));
    }

    @Test
    public void testHoffmanEncoder() {
        String txt = "brbcadabdb";
        byte[] in_data = txt.getBytes(StandardCharsets.UTF_8);
        byte[] out_data = new byte[in_data.length*2];
        HoffmanEncoder encoder = new HoffmanEncoder();
        Bits encodedBits = encoder.encode(in_data, in_data.length);
        byte[] decodedBytes = encoder.decode(encodedBits);
        Assert.assertEquals(txt,
                new String(decodedBytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testDeflateCompressor() {
//        String txt = "banana_bandanatttttttttttt";
//        byte[] in_data = txt.getBytes(StandardCharsets.UTF_8);
//        byte[] out_data = new byte[in_data.length*2];
//        Compressor compressor = new DeflateCompressor();
//        compressor.setDebug(true);
//        int compressed = compressor.compress(in_data, in_data.length, out_data);
//        byte[] compressed_data = Arrays.copyOf(out_data, compressed);
//        byte[] decompressed_data = new byte[in_data.length];
//        int decompressed = compressor.decompress(compressed_data, compressed, decompressed_data);
//        Assert.assertEquals(txt,
//                new String(Arrays.copyOf(decompressed_data, decompressed), StandardCharsets.UTF_8));
    }

    private static final int[] SORTED_A_BYTE = new int[]{2, 5, 6, 1, 3, 4, 7, 7, 8, 8, 9};
    private static final String[] SORTED_A_OBJECT = new String[]{"2","5","1","6","7","7","8","8","9","3","4"};
    private static final String[] SORTED_A_GENERIC = new String[]{"bb", "a", "b", "ba", "aab"};

    @Test
    public void testBubbleSorts() {
        int[] a_byte = new int[]{2,5,6,1,8,8,9,7,7,3,4};
        String[] a_object = new String[]{"2","5","6","1","8","8","9","7","7","3","4"};
        String[] a_generic = new String[]{"bb", "ba","a", "aab", "b"};
        Sorts sorts = new BubbleSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length-2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

    @Test
    public void testInsertionSorts() {
        int[] a_byte = new int[]{2,5,6,1,8,8,9,7,7,3,4};
        String[] a_object = new String[]{"2","5","6","1","8","8","9","7","7","3","4"};
        String[] a_generic = new String[]{"bb", "ba","a", "aab", "b"};
        Sorts sorts = new BubbleSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length-2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

    @Test
    public void testQuickSorts() {
        int[] a_byte = new int[]{2,5,6,1,8,8,9,7,7,3,4};
        String[] a_object = new String[]{"2","5","6","1","8","8","9","7","7","3","4"};
        String[] a_generic = new String[]{"bb", "ba","a", "aab", "b"};
        Sorts sorts = new BubbleSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length-2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

    @Test
    public void testSelectionSorts() {
        int[] a_byte = new int[]{2,5,6,1,8,8,9,7,7,3,4};
        String[] a_object = new String[]{"2","5","6","1","8","8","9","7","7","3","4"};
        String[] a_generic = new String[]{"bb", "ba","a", "aab", "b"};
        Sorts sorts = new BubbleSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length-2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

//    @Test
//    public void testHeapSorts() {
//        int[] a = new int[]{2,5,6,1,8,8,9,7,7,3,4};
//        Sorts sorts = new HeapSorts();
//        sorts.sort(a);
//        int n = a.length;
//        for (int i = 0; i < n; ++i)
//            System.out.print(a[i] + " ");
//        System.out.println();
//        float arr[] = {(float) 0.897, (float) 0.565,
//                (float) 0.656, (float) 0.1234,
//                (float) 0.665, (float) 0.3434};
//    }

//    @Test
//    public void testBucketSorts() {
//        // todo compelte
////        float a[] = {0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f};
//        int[] a = new int[]{2,5,6,1,8,8,90,7,7,3,4};
//        Sorts sorts = new BucketSorts();
//        sorts.sort(a);
//        int n = a.length;
//        for (int i = 0; i < n; ++i)
//            System.out.print(a[i] + " ");
//        System.out.println();
//    }

    @Test
    public void testMergeSorts() {
        int[] a_byte = new int[]{2, 5, 6, 1, 8, 8, 9, 7, 7, 3, 4};
        String[] a_object = new String[]{"2", "5", "6", "1", "8", "8", "9", "7", "7", "3", "4"};
        String[] a_generic = new String[]{"bb", "ba", "a", "aab", "b"};
        Sorts sorts = new BubbleSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length - 2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

    @Test
    public void testTimSorts() {
        int[] a_byte = new int[]{2, 5, 6, 1, 8, 8, 9, 7, 7, 3, 4};
        String[] a_object = new String[]{"2", "5", "6", "1", "8", "8", "9", "7", "7", "3", "4"};
        String[] a_generic = new String[]{"bb", "ba", "a", "aab", "b"};
        Sorts sorts = new TimSorts();
        sorts.sort(a_byte, 4, a_byte.length);
        sorts.sort(a_object, 2, a_object.length - 2);
        sorts.sort(a_generic, 1, a_generic.length, Comparator.comparingInt(String::length));
        Assert.assertArrayEquals(SORTED_A_BYTE, a_byte);
        Assert.assertArrayEquals(SORTED_A_OBJECT, a_object);
        Assert.assertArrayEquals(SORTED_A_GENERIC, a_generic);
    }

    @Test
    public void testBitsEncoder() {
        Assert.assertEquals(Bits.ofString("10"), Bits.Encoder.encodeIntValue(2));
        Assert.assertEquals(Bits.ofString("11111111111111111111111111111110"), Bits.Encoder.encodeIntValue(-2));

        Assert.assertEquals(Bits.ofString("00111111001000000000000000000000"), Bits.Encoder.encodeFloatValue(0.625f));
        Assert.assertEquals(Bits.ofString("10111111001000000000000000000000"), Bits.Encoder.encodeFloatValue(-0.625f));
        Assert.assertEquals(Bits.ofString("01000001100011010000000000000000"), Bits.Encoder.encodeFloatValue(17.625f));
        Assert.assertEquals(Bits.ofString("01000000110000000000000000000000"), Bits.Encoder.encodeFloatValue(6.0f));
        Assert.assertEquals(Bits.ofString("00000000000000000000000000000000"), Bits.Encoder.encodeFloatValue(0.0f));
        Assert.assertEquals(Bits.ofString("10111111011111111111100101110010"), Bits.Encoder.encodeFloatValue(-0.9999f));

        Assert.assertEquals(Bits.ofString("0100000000000010000000000000000000000000000000000000000000000000"), Bits.Encoder.encodeDoubleValue(2.25));
        Assert.assertEquals(Bits.ofString("0011111111010110110010001011010000111001010110000001000001100010"), Bits.Encoder.encodeDoubleValue(0.356));
        Assert.assertEquals(Bits.ofString("0100000000101001100100111111011111001110110110010001011010000111"), Bits.Encoder.encodeDoubleValue(12.789));
        Assert.assertEquals(Bits.ofString("1100000000101001100100111111011111001110110110010001011010000111"), Bits.Encoder.encodeDoubleValue(-12.789));
        Assert.assertEquals(Bits.ofString("1100000011000101101100111010101010101010101010101010101010101011"), Bits.Encoder.encodeDoubleValue(-11111.3333333333333333));

        Assert.assertEquals(0.625f, Bits.Decoder.decodeFloatValue(Bits.ofString("00111111001000000000000000000000")), 0);
        Assert.assertEquals(0.0f, Bits.Decoder.decodeFloatValue(Bits.ofString("00000000000000000000000000000000")), 0);
        Assert.assertEquals(0.4f, Bits.Decoder.decodeFloatValue(Bits.ofString("00111110110011001100110011001101")), 0);
        Assert.assertEquals(-0.9999f, Bits.Decoder.decodeFloatValue(Bits.ofString("10111111011111111111100101110010")), 0);

        Assert.assertEquals(2.25, Bits.Decoder.decodeDoubleValue(Bits.ofString("0100000000000010000000000000000000000000000000000000000000000000")), 0);
        Assert.assertEquals(0.356, Bits.Decoder.decodeDoubleValue(Bits.ofString("0011111111010110110010001011010000111001010110000001000001100010")), 0);
        Assert.assertEquals(12.789, Bits.Decoder.decodeDoubleValue(Bits.ofString("0100000000101001100100111111011111001110110110010001011010000111")), 0);
        Assert.assertEquals(-12.789, Bits.Decoder.decodeDoubleValue(Bits.ofString("1100000000101001100100111111011111001110110110010001011010000111")), 0);
        Assert.assertEquals(-11111.3333333333333333, Bits.Decoder.decodeDoubleValue(Bits.ofString("1100000011000101101100111010101010101010101010101010101010101011")), 0);

        Assert.assertEquals(3, Bits.Encoder.encodeZigzagValue(-2));
        Assert.assertEquals(0, Bits.Encoder.encodeZigzagValue(0));
        Assert.assertEquals(-2, Bits.Decoder.decodeZigzagValue(3));
        Assert.assertEquals(-1, Bits.Decoder.decodeZigzagValue(1));
    }

    @Test
    public void testSnowflakeIdWorker() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }
}
