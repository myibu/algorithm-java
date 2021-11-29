package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Tim Sort
 * @author myibu
 * Created on 2021/11/29
 */
public class TimSorts extends AbstractSorts {
    private static final int MIN_MERGE = 32;

    public static void timSort(int[] arr, int n) {
        int minRun = minRunLength(n);
        for (int i = 0; i < n; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)) + 1);
        }
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void timSort(byte[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(short[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(int[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(long[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(float[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(double[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(char[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void timSort(Object[] arr, int leftIndex, int rightIndex) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right);
                }
            }
        }
    }

    public static <T> void timSort(T[] arr, int leftIndex, int rightIndex, Comparator<? super T> c) {
        int minRun = minRunLength(rightIndex - leftIndex);
        for (int i = leftIndex; i < rightIndex; i += minRun) {
            InsertionSorts.insertSort(arr, i, Math.min((i + MIN_MERGE - 1), (rightIndex - 1)) + 1, c);
        }
        for (int size = minRun; size < rightIndex; size = 2 * size) {
            for (int left = leftIndex; left < rightIndex; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (rightIndex - 1));

                if(mid < right) {
                    MergeSorts.merge(arr, left, mid, right, c);
                }
            }
        }
    }

    @Override
    public void sort(byte[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(short[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(int[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(long[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(float[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(double[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(char[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public void sort(Object[] a, int fromIndex, int toIndex) {
        timSort(a, fromIndex, toIndex);
    }

    @Override
    public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
        timSort(a, fromIndex, toIndex, c);
    }
}
