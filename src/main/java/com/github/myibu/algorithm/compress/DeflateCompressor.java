package com.github.myibu.algorithm.compress;

/**
 * Deflate compressor
 * @author myibu
 * Created on 2021/10/29
 */
public class DeflateCompressor implements Compressor {
    @Override
    public int compress(byte[] in_data, int in_len, byte[] out_data) {
        return 0;
    }

    @Override
    public int decompress(byte[] in_data, int in_len, byte[] out_data) {
        return 0;
    }

    private boolean isDebug = false;

    @Override
    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }
}
