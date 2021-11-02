package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Insertion Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class InsertionSorts implements Sorts {
	@Override
	public void sort(byte[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			byte key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(short[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			short key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(long[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			long key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(float[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			float key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(double[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			double key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(char[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			char key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public void sort(Object[] a) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			Object key = a[i];
			int j = i - 1;
			while (j >= 0 && ((Comparable)a[j]).compareTo(key) > 0) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}

	@Override
	public <T> void sort(T[] a, Comparator<? super T> c) {
		int n = a.length;
		for (int i = 1; i < n; ++i) {
			T key = a[i];
			int j = i - 1;
			while (j >= 0 && c.compare(a[j], key) > 0) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}
}
