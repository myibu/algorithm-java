package com.github.myibu.algorithm;

import com.github.myibu.algorithm.hash.SHA256;
import com.github.myibu.algorithm.hash.SipHash;
import com.github.myibu.algorithm.validate.IDCardChecker;
import org.junit.Assert;
import org.junit.Test;

public class AlgorithmTest {
    @Test
    public void testSHA256() throws Exception {
        SHA256 sha256 = new SHA256();
        byte[] bs = sha256.encode("abc".getBytes("utf-8"));
        Assert.assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                ByteOperator.byteArrayToHexString(bs));

        bs = sha256.encode("this is a test message".getBytes("utf-8"));
        Assert.assertEquals("4e4aa09b6d80efbd684e80f54a70c1d8605625c3380f4cb012b32644a002b5be",
                ByteOperator.byteArrayToHexString(bs));
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
}
