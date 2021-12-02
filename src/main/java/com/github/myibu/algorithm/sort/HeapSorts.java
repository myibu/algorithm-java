//package com.github.myibu.algorithm.sort;
//
//import java.util.Comparator;
//
///**
// * Heap Sort
// * @author myibu
// * Created on 2021/11/2
// */
//public class HeapSorts extends AbstractSorts {
//	private void heapify(byte arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			byte swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(short arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			short swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(int arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			int swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(long arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			long swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(float arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			float swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(double arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			double swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(char arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && arr[l] > arr[largest])
//			largest = l;
//		if (r < n && arr[r] > arr[largest])
//			largest = r;
//
//		if (largest != i) {
//			char swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private void heapify(Object arr[], int n, int i) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && ((Comparable)arr[l]).compareTo(arr[largest]) > 0)
//			largest = l;
//		if (r < n && ((Comparable)arr[r]).compareTo(arr[largest]) > 0)
//			largest = r;
//
//		if (largest != i) {
//			Object swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//	private <T> void heapify(T arr[], int n, int i, Comparator<? super T> c) {
//		int largest = i;
//		int l = 2 * i + 1;
//		int r = 2 * i + 2;
//
//		if (l < n && c.compare(arr[l], arr[largest]) > 0)
//			largest = l;
//		if (r < n && c.compare(arr[r], arr[largest]) > 0)
//			largest = r;
//
//		if (largest != i) {
//			T swap = arr[i];
//			arr[i] = arr[largest];
//			arr[largest] = swap;
//			heapify(arr, n, largest);
//		}
//	}
//
//	@Override
//	public void sort(byte[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			byte temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(byte[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(short[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			short temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(short[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(int[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			int temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(int[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(long[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			long temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(long[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(float[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			float temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(float[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(double[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			double temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(double[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(char[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			char temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(char[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(Object[] a) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i);
//
//		for (int i = n - 1; i > 0; i--) {
//			Object temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0);
//		}
//	}
//
//	@Override
//	public void sort(Object[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
//
//	}
//
//	@Override
//	public <T> void sort(T[] a, Comparator<? super T> c) {
//		int n = a.length;
//		for (int i = n / 2 - 1; i >= 0; i--)
//			heapify(a, n, i, c);
//
//		for (int i = n - 1; i > 0; i--) {
//			T temp = a[0];
//			a[0] = a[i];
//			a[i] = temp;
//			heapify(a, i, 0, c);
//		}
//	}
//}
