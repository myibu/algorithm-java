package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Sorts interface
 * @author myibu
 * Created on 2021/11/2
 */
public interface Sorts {
    void sort(byte[] a);
    void sort(byte[] a, int fromIndex, int toIndex);
    void sort(short[] a);
    void sort(short[] a, int fromIndex, int toIndex);
    void sort(int[] a);
    void sort(int[] a, int fromIndex, int toIndex);
    void sort(long[] a);
    void sort(long[] a, int fromIndex, int toIndex);
    void sort(float[] a);
    void sort(float[] a, int fromIndex, int toIndex);
    void sort(double[] a);
    void sort(double[] a, int fromIndex, int toIndex);
    void sort(char[] a);
    void sort(char[] a, int fromIndex, int toIndex);
    void sort(Object[] a);
    void sort(Object[] a, int fromIndex, int toIndex);
    <T> void sort(T[] a, Comparator<? super T> c);
    <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c);
}
