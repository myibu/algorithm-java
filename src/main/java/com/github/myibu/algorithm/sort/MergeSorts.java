package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Merge Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class MergeSorts implements Sorts {
	private void merge(byte a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		byte L[] = new byte[n1];
		byte R[] = new byte[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(byte a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(short a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		short L[] = new short[n1];
		short R[] = new short[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(short a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(int a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(int a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(long a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		long L[] = new long[n1];
		long R[] = new long[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(long a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(float a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		float L[] = new float[n1];
		float R[] = new float[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(float a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(double a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		double L[] = new double[n1];
		double R[] = new double[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(double a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(char a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		char L[] = new char[n1];
		char R[] = new char[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(char a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}
	private void merge(Object a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		Object L[] = new Object[n1];
		Object R[] = new Object[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (((Comparable)L[i]).compareTo(R[j]) <= 0) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(Object a[], int l, int r) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m);
			sort(a, m + 1, r);

			merge(a, l, m, r);
		}
	}

	private <T> void merge(T a[], int l, int m, int r, Comparator<? super T> c) {
		int n1 = m - l + 1;
		int n2 = r - m;

		Object L[] = new Object[n1];
		Object R[] = new Object[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (c.compare((T)L[i], (T)R[j]) <= 0) {
				a[k] = (T)L[i];
				i++;
			}
			else {
				a[k] = (T)R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = (T)L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = (T)R[j];
			j++;
			k++;
		}
	}

	private <T> void sort(T a[], int l, int r, Comparator<? super T> c) {
		if (l < r) {
			int m =l+ (r-l)/2;

			sort(a, l, m, c);
			sort(a, m + 1, r, c);

			merge(a, l, m, r, c);
		}
	}
	@Override
	public void sort(byte[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(short[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(int[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(long[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(float[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(double[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(char[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public void sort(Object[] a) {
		sort(a, 0, a.length-1);
	}

	@Override
	public <T> void sort(T[] a, Comparator<? super T> c) {
		sort(a, 0, a.length-1, c);
	}
}