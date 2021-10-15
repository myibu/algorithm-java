package com.github.myibu.algorithm.compress;

/**
 * compressor for compress and decompress
 * @author myibu
 * Created on 2021/10/11
 */
public interface Compressor extends Debugable {
    /**
     * compress bytes
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    int compress(byte[] in_data, int in_len, byte[] out_data);

    /**
     * decompress bytes
     * @param in_data input
     * @param in_len length of input
     * @param out_data output
     * @return offset in output
     */
    int decompress(byte[] in_data, int in_len, byte[] out_data);
}
