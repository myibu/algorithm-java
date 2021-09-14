package com.github.myibu.algorithm;

import com.github.myibu.algorithm.data.Bits;
import com.github.myibu.algorithm.data.Bytes;
import com.github.myibu.algorithm.filter.BloomFilter;
import com.github.myibu.algorithm.hash.MurmurHash2;
import com.github.myibu.algorithm.hash.SHA256;
import com.github.myibu.algorithm.hash.SipHash;
import com.github.myibu.algorithm.validate.IDCardChecker;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

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
}
