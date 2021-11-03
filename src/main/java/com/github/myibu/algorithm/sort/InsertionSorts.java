package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Insertion Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class InsertionSorts extends AbstractSorts {
	public static void insertSort(byte[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			byte key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(short[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			short key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(int[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			int key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(long[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			long key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(float[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			float key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(double[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			double key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(char[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			char key = a[i];
			int j = i - 1;
			while (j >= fromIndex && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort(Object[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			Object key = a[i];
			int j = i - 1;
			while (j >= fromIndex && ((Comparable)a[j]).compareTo(key) > 0) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	public <T> void insertSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		for (int i = fromIndex; i < toIndex; ++i) {
			T key = a[i];
			int j = i - 1;
			while (j >= fromIndex && c.compare(a[j], key) > 0) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(byte[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(short[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(int[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(long[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(float[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(double[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(char[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(Object[] a, int fromIndex, int toIndex) {
		insertSort(a, fromIndex, toIndex);
	}

	@Override
	public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		insertSort(a, fromIndex, toIndex, c);
	}
}
