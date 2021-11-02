package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Sorts interface
 * @author myibu
 * Created on 2021/11/2
 */
public interface Sorts {
    void sort(byte[] a);
    void sort(short[] a);
    void sort(int[] a);
    void sort(long[] a);
    void sort(float[] a);
    void sort(double[] a);
    void sort(char[] a);
    void sort(Object[] a);
    <T> void sort(T[] a, Comparator<? super T> c);
}
