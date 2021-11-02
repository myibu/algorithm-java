package com.github.myibu.algorithm.sort;// Java implementation of QuickSort
import java.util.Comparator;

/**
 * Quick Sort
 * @author myibu
 * Created on 2021/11/2
 */
public class QuickSorts implements Sorts {
	private void swap(byte[] arr, int i, int j) {
		byte temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(byte[] arr, int low, int high) {
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
	private void sort(byte[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(short[] arr, int i, int j) {
		short temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(short[] arr, int low, int high) {
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
	private void sort(short[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(int[] arr, int low, int high) {
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
	private void sort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(long[] arr, int i, int j) {
		long temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(long[] arr, int low, int high) {
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
	private void sort(long[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(float[] arr, int i, int j) {
		float temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(float[] arr, int low, int high) {
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
	private void sort(float[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(double[] arr, int i, int j) {
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(double[] arr, int low, int high) {
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
	private void sort(double[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(char[] arr, int low, int high) {
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
	private void sort(char[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private void swap(Object[] arr, int i, int j) {
		Object temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(Object[] arr, int low, int high) {
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
	private void sort(Object[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}
	private <T> int partition(T[] arr, int low, int high, Comparator<? super T> c) {
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
	private <T> void sort(T[] arr, int low, int high, Comparator<? super T> c) {
		if (low < high) {
			int pi = partition(arr, low, high, c);
			sort(arr, low, pi - 1, c);
			sort(arr, pi + 1, high, c);
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

// This code is contributed by Ayush Choudhary
