//package com.github.myibu.algorithm.sort;
//import java.util.*;
//
///**
// * Bucket Sort
// * @author myibu
// * Created on 2021/11/2
// */
//public class BucketSorts implements Sorts {
//	@Override
//	public void sort(byte[] a) {
//
//	}
//
//	@Override
//	public void sort(byte[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(short[] a) {
//
//	}
//
//	@Override
//	public void sort(short[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(int[] a) {
//		int max = Integer.MIN_VALUE;
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < a.length; i++) {
//			max = Math.max(max, a[i]);
//			min = Math.min(min, a[i]);
//		}
//
//		int bucketNum = (max - min) / a.length + 1;
//		List<List<Integer>> bucketArr = new ArrayList<>(bucketNum);
//		for (int i = 0; i < bucketNum; i++) {
//			bucketArr.add(new ArrayList<>());
//		}
//
//		for (int i = 0; i < a.length; i++) {
//			int num = (a[i] - min) / (a.length);
//			bucketArr.get(num).add(a[i]);
//		}
//
//		for (int i = 0; i < bucketArr.size(); i++) {
//			Integer[] a1 = bucketArr.get(i).toArray(new Integer[0]);
//			new QuickSorts().sort(a1);
//			bucketArr.set(i, Arrays.asList(a1));
//		}
//
//		int index = 0;
//		for (int i = 0; i < bucketArr.size(); i++) {
//			for (int j = 0; j < bucketArr.get(i).size(); j++) {
//				a[index++] = bucketArr.get(i).get(j);
//			}
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
//
//	}
//
//	@Override
//	public void sort(long[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(float[] a) {
//
//	}
//
//	@Override
//	public void sort(float[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(double[] a) {
//
//	}
//
//	@Override
//	public void sort(double[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(char[] a) {
//
//	}
//
//	@Override
//	public void sort(char[] a, int fromIndex, int toIndex) {
//
//	}
//
//	@Override
//	public void sort(Object[] a) {
//
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
//
//	}
//}
