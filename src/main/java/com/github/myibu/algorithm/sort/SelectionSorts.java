package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Insertion Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class SelectionSorts implements Sorts {
	@Override
	public void sort(byte[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			byte temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(short[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			short temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			int temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(long[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			long temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(float[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			float temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(double[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			double temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(char[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			char temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(Object[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (((Comparable)a[j]).compareTo(a[min_idx]) < 0)
					min_idx = j;

			Object temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public <T> void sort(T[] a, Comparator<? super T> c) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (c.compare(a[j], a[min_idx]) < 0)
					min_idx = j;

			T temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}
}
