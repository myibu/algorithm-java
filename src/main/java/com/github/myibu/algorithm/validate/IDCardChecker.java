package com.github.myibu.algorithm.validate;

/**
 * tool of checking whether id card is validate
 * @author myibu
 * Created on 2021/9/8
 */
public class IDCardChecker {
    private static int pow(int m, int n){
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= m;
        }
        return res;
    }

    public static boolean check(String id) {
        if (id == null || !id.matches("[\\d]{17}[xX0-9]")) {
            return false;
        }
        char[] chars = new StringBuffer(id).reverse().toString()
                .toCharArray();
        int[] W = new int[chars.length+1], A = new int[chars.length+1];
        A[0] = W[0] = Integer.MAX_VALUE;
        for (int i = 1; i < 19; i++) {
            W[i] = pow(2,i-1) % 11;
        }
        for (int i = 1; i < 19; i++) {
            if(i == 1 && (chars[1] == 'X' || chars[1] == 'x')){
                A[i] = 10;
            }else{
                A[i] = Integer.parseInt(""+chars[i-1]);
            }
        }
        int res1 = 0;
        for (int i = 2; i < 19; i++) {
            res1 += A[i] * W[i];
        }
        int res2 = res1 + A[1] * W[1];
        int a1 = (12 - res1 % 11) % 11;
        return A[1] == a1 && res2 % 11 == 1 % 11;
    }
}