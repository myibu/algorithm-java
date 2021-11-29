package com.github.myibu.algorithm.sort;

import java.util.Comparator;

/**
 * Insertion Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class SelectionSorts extends AbstractSorts {
	public static void selectSort(byte[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			byte temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(short[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			short temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(int[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			int temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(long[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			long temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(float[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			float temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(double[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			double temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(char[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (a[j] < a[min_idx])
					min_idx = j;

			char temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static void selectSort(Object[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (((Comparable)a[j]).compareTo(a[min_idx]) < 0)
					min_idx = j;

			Object temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	public static <T> void selectSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		for (int i = fromIndex; i < toIndex-1; i++) {
			int min_idx = i;
			for (int j = i+1; j < toIndex; j++)
				if (c.compare(a[j], a[min_idx]) < 0)
					min_idx = j;

			T temp = a[min_idx];
			a[min_idx] = a[i];
			a[i] = temp;
		}
	}

	@Override
	public void sort(byte[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(short[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(int[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(long[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(float[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(double[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(char[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(Object[] a, int fromIndex, int toIndex) {
		selectSort(a, fromIndex, toIndex);
	}

	@Override
	public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		selectSort(a, fromIndex, toIndex, c);
	}
}
