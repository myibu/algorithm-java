package com.github.myibu.algorithm.sort;

import java.util.Comparator;
/**
 * abstract Sort
 * @author myibu
 * Created on 2021/11/2
 */
public abstract class AbstractSorts implements Sorts {
    @Override
    public void sort(byte[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(short[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(long[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(float[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(double[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(char[] a) {
        sort(a, 0, a.length);
    }

    @Override
    public void sort(Object[] a) {
        sort(a, 0, a.length);
    }


    @Override
    public <T> void sort(T[] a, Comparator<? super T> c) {
        sort(a, 0, a.length, c);
    }
}
