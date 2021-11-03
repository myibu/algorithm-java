//package com.github.myibu.algorithm.sort;
//
//import java.util.Comparator;
//
///**
// * Insertion Sort
// * @author myibu
// * Created on 2021/11/2
// */
//public class TimSorts extends AbstractSorts {
//    private static final int MIN_MERGE = 32;
//
//    private static int minRunLength(int n) {
//        assert n >= 0;
//        int r = 0;
//        while (n >= MIN_MERGE) {
//            r |= (n & 1);
//            n >>= 1;
//        }
//        return n + r;
//    }
//
//    public static void timSort(int[] arr, int n) {
//        int minRun = minRunLength(MIN_MERGE);
//        for (int i = 0; i < n; i += minRun) {
//            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
//        }
//        for (int size = minRun; size < n; size = 2 * size) {
//            for (int left = 0; left < n; left += 2 * size) {
//
//                int mid = left + size - 1;
//                int right = Math.min((left + 2 * size - 1), (n - 1));
//
//                if(mid < right) {
//                    MergeSorts.merge(arr, left, mid, right);
//                }
//            }
//        }
//    }
//
//    public static void timSort(int[] a, int fromIndex, int toIndex) {
////        int nRemaining  = toIndex - fromIndex;
////        if (nRemaining < 2)
////            return;
////        if (nRemaining < MIN_MERGE) {
////            InsertionSorts.insertSort(a, fromIndex, toIndex);
////            return;
////        }
////        int minRun = minRunLength(nRemaining);
////        do {
////            int runLen = countRunAndMakeAscending(a, lo, hi, c);
////
////            // If run is short, extend to min(minRun, nRemaining)
////            if (runLen < minRun) {
////                int force = nRemaining <= minRun ? nRemaining : minRun;
////                binarySort(a, lo, lo + force, lo + runLen, c);
////                runLen = force;
////            }
////
////            // Push run onto pending-run stack, and maybe merge
////            ts.pushRun(lo, runLen);
////            ts.mergeCollapse();
////
////            // Advance to find next run
////            lo += runLen;
////            nRemaining -= runLen;
////        } while (nRemaining != 0);
////        for (int i = 0; i < n; i += minRun) {
////            InsertionSorts.insertSort(a, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
////        }
////        for (int size = minRun; size < n; size = 2 * size) {
////            for (int left = 0; left < n; left += 2 * size) {
////
////                int mid = left + size - 1;
////                int right = Math.min((left + 2 * size - 1), (n - 1));
////
////                if(mid < right) {
////                    MergeSorts.merge(arr, left, mid, right);
////                }
////            }
////        }
//    }
//
//    @Override
//    public void sort(byte[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(short[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(int[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(long[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(float[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(double[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(char[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public void sort(Object[] a, int fromIndex, int toIndex) {
//
//    }
//
//    @Override
//    public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
//
//    }
//}
