package com.github.myibu.algorithm.data;
/**
 * Bit enum
 * @author myibu
 * Created on 2021/9/14
 */
public enum Bit {
    /**
     * 0
     */
    ZERO(0),
    /**
     * 1
     */
    ONE(1);
    final int value;

    Bit(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}