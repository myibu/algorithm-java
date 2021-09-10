package com.github.myibu.algorithm.hash;

/**
 * SipHash 1-3 algorithm
 * @author myibu
 * Created on 2021/9/8
 */
public class SipHash13 extends SipHash {
    public SipHash13() {
        super();
        this.c = 1;
        this.d = 3;
    }

    public SipHash13(byte[] seed) {
        super(seed, 1, 3);
    }
}
