package com.github.myibu.algorithm.sort;// Java implementation of QuickSort
import java.util.Comparator;

/**
 * Quick Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class QuickSorts extends AbstractSorts {
	public static void swap(byte[] arr, int i, int j) {
		byte temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(byte[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot)
			{
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(byte[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(short[] arr, int i, int j) {
		short temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(short[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(short[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(long[] arr, int i, int j) {
		long temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(long[] arr, int low, int high) {
		long pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(long[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(float[] arr, int i, int j) {
		float temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(float[] arr, int low, int high) {
		float pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(float[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(double[] arr, int i, int j) {
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(double[] arr, int low, int high) {
		double pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(double[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(char[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(char[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	public static void swap(Object[] arr, int i, int j) {
		Object temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int partition(Object[] arr, int low, int high) {
		Object pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (((Comparable)arr[j]).compareTo(pivot) < 0) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static void quickSort(Object[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	private static <T> int partition(T[] arr, int low, int high, Comparator<? super T> c) {
		T pivot = arr[high];
		int i = (low - 1);
		for(int j = low; j <= high - 1; j++) {
			if (c.compare(arr[j], pivot) < 0) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}
	public static <T> void quickSort(T[] arr, int low, int high, Comparator<? super T> c) {
		if (low < high) {
			int pi = partition(arr, low, high, c);
			quickSort(arr, low, pi - 1, c);
			quickSort(arr, pi + 1, high, c);
		}
	}

	@Override
	public void sort(byte[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(short[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(int[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(long[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(float[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(double[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(char[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public void sort(Object[] a, int fromIndex, int toIndex) {
		quickSort(a, fromIndex, toIndex-1);
	}

	@Override
	public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
		quickSort(a, fromIndex, toIndex-1, c);
	}
}