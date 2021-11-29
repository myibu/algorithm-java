package com.github.myibu.algorithm.sort;
import java.util.Comparator;

/**
 * Bubble Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class BubbleSorts extends AbstractSorts {
	public static void bubbleSort(byte[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					byte temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(short[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					short temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(int[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(long[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					long temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(float[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					float temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(double[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					double temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(char[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					char temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static void bubbleSort(Object[] a, int fromIndex, int toIndex) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				Comparable pre = (Comparable)a[j];
				if (pre.compareTo(a[j+1]) > 0) {
					Object temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public static <T> void bubbleSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		for (int i = 0; i < toIndex-fromIndex; i++) {
			for (int j = fromIndex; j < toIndex - i - 1; j++) {
				if (c.compare(a[j], a[j+1]) > 0) {
					T temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}


	@Override
	public void sort(byte[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(short[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(int[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(long[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(float[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(double[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(char[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public void sort(Object[] a, int fromIndex, int toIndex) {
		bubbleSort(a, fromIndex, toIndex);
	}

	@Override
	public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		bubbleSort(a, fromIndex, toIndex, c);
	}
}