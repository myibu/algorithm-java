package com.github.myibu.algorithm.random;

import java.util.Random;

/**
 * shuffle array
 * @author myibu
 * Created on 2021/9/17
 */
public class RandomArrays {
    public static void shuffle(char[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(char[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            char temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(boolean[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(boolean[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            boolean temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(byte[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(byte[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            byte temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(short[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(short[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            short temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(int[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(int[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            int temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(long[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(long[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            long temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(float[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(float[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            float temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(double[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(double[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            double temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(String[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(String[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            String temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }

    public static void shuffle(Object[] a) {
        if (null == a || a.length < 2) return;
        Random random = new Random();
        for (int i = a.length-1; i >= 0; i--) {
            swap(a, i, random.nextInt(i+1));
        }
    }

    public static void swap(Object[] a, int i, int j) {
        if (null == a) {
            return;
        }
        if (i >= 0 && i < a.length && j >= 0 && j < a.length) {
            Object temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }
    }
}
