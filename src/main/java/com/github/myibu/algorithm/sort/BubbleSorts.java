package com.github.myibu.algorithm.sort;
import java.util.Comparator;

/**
 * Bubble Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class BubbleSorts implements Sorts {
	@Override
	public void sort(byte[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					byte temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(short[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					short temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(long[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					long temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(float[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					float temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(double[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					double temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(char[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					// swap arr[j+1] and arr[j]
					char temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public void sort(Object[] a) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				Comparable pre = (Comparable)a[j];
				if (pre.compareTo(a[j+1]) > 0) {
					// swap arr[j+1] and arr[j]
					Object temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public <T> void sort(T[] a, Comparator<? super T> c) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (c.compare(a[j], a[j+1]) > 0) {
					// swap arr[j+1] and arr[j]
					T temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
}