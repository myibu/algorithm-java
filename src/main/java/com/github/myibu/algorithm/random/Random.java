package com.github.myibu.algorithm.random;
/**
 * random interface
 * @author myibu
 * Created on 2021/9/17
 */
public interface Random {
    void nextBytes(byte[] bytes);
    int nextInt();
    int nextInt(int bound);
    long nextLong();
    boolean nextBoolean();
    float nextFloat();
    double nextDouble();
}
