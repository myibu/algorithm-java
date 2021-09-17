package com.github.myibu.algorithm.data;

/**
 * util for byte
 * @author myibu
 * Created on 2021/9/10
 */
public class Bytes {
    public static byte[] appendBytes(byte[] dest, byte[] src) {
        int tl = dest.length, sl = src.length;
        byte[] b = new byte[tl + sl];
        System.arraycopy(dest, 0, b, 0, tl);
        System.arraycopy(src, 0, b, tl, sl);
        return b;
    }

    public static byte[] appendByte(byte[] dest, byte src) {
        int tl = dest.length;
        byte[] b = new byte[tl + 1];
        System.arraycopy(dest, 0, b, 0, tl);
        b[tl] = src;
        return b;
    }

    public static int unsignedByte(byte val) {
        return val & 0xFF;
    }

    public static long unsignedInt(int val) {
        return val & 0xFFFFFFFFL;
    }

    private static final char[] DIGITS = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    public static int byteArrayToInt(byte[] bs, int offset) {
        return  unsignedByte(bs[offset    ]) * 16777216 +
                unsignedByte(bs[offset + 1]) * 65536 +
                unsignedByte(bs[offset + 2]) * 256 +
                unsignedByte(bs[offset + 3]);
    }

    public static long byteArrayToUnsignedInt(byte[] bs, int offset) {
        return unsignedInt(byteArrayToInt(bs, offset));
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(intToHexString(b & 0x000000FF));
        }
        return builder.toString();
    }

    public static String intToHexString(int val) {
        byte[] buf = new byte[2];
        formatUnsignedInt(val, 4, buf, 0, 2);
        return new String(buf);
    }

    private static void formatUnsignedInt(int val, int shift, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[--charPos] = (byte)DIGITS[val & mask];
            val >>>= shift;
        } while (charPos > offset);
    }
}
