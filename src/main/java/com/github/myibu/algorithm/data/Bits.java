package com.github.myibu.algorithm.data;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Bits entity
 * @author myibu
 * Created on 2021/9/14
 */
public class Bits implements Iterable<Bit>, Cloneable {
    public static final int BYTE_SIZE = 8;
    public static final int SHORT_SIZE = 16;
    public static final int INT_SIZE = 32;
    public static final int LONG_SIZE = 64;
    private static final int INITIAL_SIZE = SHORT_SIZE;
    private Bit[] table;
    private int size;
    private int used;

    public Bits() {
        table = new Bit[INITIAL_SIZE];
        this.used = 0;
        this.size = INITIAL_SIZE;
    }

    public static Bits inverse(Bits x) {
        Bits bits = new Bits();
        bits.expand(x.used);
        for (int i = 0; i < x.used; i++) {
            bits.table[i] = (x.table[i] == Bit.ZERO) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += x.used;
        return bits;
    }

    public static Bits reverse(Bits x) {
        Bits bits = new Bits();
        bits.expand(x.used);
        for (int i = 0; i < x.used; i++) {
            bits.table[i] = x.table[x.used-i-1];
        }
        bits.used += x.used;
        return bits;
    }

    public static Bits and(Bits x, Bits y) {
        Bits x1, y1;
        Bits bits = new Bits();
        if (x.used > y.used) {
            x1 = x;
            y1 = Bits.ofZero(y.used-x.used).append(y);
        } else if (x.used < y.used) {
            x1 = Bits.ofZero(x.used-y.used).append(x);
            y1 = y;
        } else {
            x1 = x;
            y1 = y;
        }
        int newLen = x1.used;
        bits.expand(newLen);
        for (int i = 0; i < x1.used; i++) {
            bits.table[i] = (x1.table[i] == Bit.ZERO || y1.table[i] == Bit.ZERO) ? Bit.ZERO : Bit.ONE;
        }
        bits.used += newLen;
        return bits;
    }

    public static Bits or(Bits x, Bits y) {
        Bits x1, y1;
        Bits bits = new Bits();
        if (x.used > y.used) {
            x1 = x;
            y1 = Bits.ofZero(y.used-x.used).append(y);
        } else if (x.used < y.used) {
            x1 = Bits.ofZero(x.used-y.used).append(x);
            y1 = y;
        } else {
            x1 = x;
            y1 = y;
        }
        int newLen = x1.used;
        bits.expand(newLen);
        for (int i = 0; i < x1.used; i++) {
            bits.table[i] = (x1.table[i] == Bit.ONE || y1.table[i] == Bit.ONE) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += newLen;
        return bits;
    }

    public static Bits xor(Bits x, Bits y) {
        Bits x1, y1;
        Bits bits = new Bits();
        if (x.used > y.used) {
            x1 = x;
            y1 = Bits.ofZero(y.used-x.used).append(y);
        } else if (x.used < y.used) {
            x1 = Bits.ofZero(x.used-y.used).append(x);
            y1 = y;
        } else {
            x1 = x;
            y1 = y;
        }
        int newLen = x1.used;
        bits.expand(newLen);
        for (int i = 0; i < x1.used; i++) {
            bits.table[i] = (x1.table[i] == y1.table[i]) ? Bit.ZERO : Bit.ONE;
        }
        bits.used += newLen;
        return bits;
    }

    public Bits lShift(int offset) {
        if (offset >= this.used) {
            return Bits.ofZero(this.used);
        }
        return this.subBits(offset, this.used).append(Bits.ofZero(offset));
    }

    public Bits lShift(Bits offset) {
        return lShift(offset.intLength());
    }

    public Bits rShift(int offset) {
        if (offset >= this.used) {
            return Bits.ofZero(this.used);
        }
        boolean isPositive = true;
        if (this.used > 0 && this.table[0] == Bit.ONE) {
            isPositive = false;
        }
        Bits prefix = isPositive ? Bits.ofZero(offset) : Bits.ofOne(offset);
        return prefix.append(this.subBits(0, this.used-offset));
    }

    public Bits rShift(Bits offset) {
        return rShift(offset.intLength());
    }

    public Bits rrShift(int offset) {
        if (offset >= this.used) {
            return Bits.ofZero(this.used);
        }
        return Bits.ofZero(offset).append(this.subBits(0, this.used-offset));
    }

    public Bits rrShift(Bits offset) {
        return rrShift(offset.intLength());
    }

    public byte[] toByteArray() {
        Bits bits;
        if (this.used % BYTE_SIZE != 0) {
            Bits completed = this.clone();
            completed.append(Bits.ofZero(BYTE_SIZE - this.used % BYTE_SIZE));
            bits = completed;
        } else {
            bits = this;
        }
        int len = bits.byteLength();
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = bits.getByte(i).toByte();
        }
        return data;
    }

    public byte toByte() {
        return (byte)toLong(8);
    }

    public short[] toShortArray() {
        int len = shortLength();
        short[] data = new short[len];
        for (int i = 0; i < len; i++) {
            data[i] = getShort(i).toShort();
        }
        return data;
    }

    public short toShort() {
        return (short)toLong(16);
    }

    public int[] toIntArray() {
        int len = intLength();
        int[] data = new int[len];
        for (int i = 0; i < len; i++) {
            data[i] = getInt(i).toInt();
        }
        return data;
    }

    public int toInt() {
        return (int)toLong(32);
    }

    public long[] toLongArray() {
        int len = longLength();
        long[] data = new long[len];
        for (int i = 0; i < len; i++) {
            data[i] = getLong(i).toLong();
        }
        return data;
    }

    public Bits plus(Bit bit) {
        Bits bits = this.clone();
        Bit next = bit;
        for (int i = bits.table.length-1; i >= 0; i--) {
            if (bits.table[i] == next) {
                if (bits.table[i] == Bit.ONE) {
                    bits.table[i] = Bit.ZERO;
                    next = Bit.ONE;
                } else {
                    next = Bit.ZERO;
                }
            } else {
                bits.table[i] = Bit.ONE;
                break;
            }
        }
        return bits;
    }

    private long toLong(int maxBitStoreSize) {
        Bits bits = ofZero(maxBitStoreSize);
        copy(this, Math.max(0, this.used - maxBitStoreSize),
                bits, maxBitStoreSize - Math.min(this.used, maxBitStoreSize),
                Math.min(this.used, maxBitStoreSize));

        boolean isPositive = bits.table[0] == Bit.ZERO;
        if (!isPositive) {
            bits = inverse(bits).plus(Bit.ONE);
        }
        long res = 0;
        for (int i = bits.used-1, j = 0; i >= 0; i--, j++) {
            res = res + (long) bits.table[i].value() * (int)pow(2, j);
        }
        return isPositive ? res : -res;
    }

    public long toLong() {
        return toLong(64);
    }

    private static double pow(int m, int n){
        if (m == 0) return 0;
        double md = (n >= 0) ? m : 1.0 / m;
        double res = 1;
        for (int i = 0; i < Math.abs(n); i++) {
            res *= md;
        }
        return res;
    }

    public static Bits ofString(String txt) {
        if (txt == null || txt.length() == 0) {
            return new Bits();
        }
        Bits bits = new Bits();
        for (int i = 0; i < txt.length(); i++) {
            char ch = txt.charAt(i);
            if (ch == '0') {
                bits.append(Bit.ZERO);
            } else if (ch == '1') {
                bits.append(Bit.ONE);
            } else {
                throw new IllegalArgumentException("illegal character " + (ch-'0') + " in index " + i);
            }
        }
        return bits;
    }

    public static Bits ofRawString(String raw) {
        if (raw == null || raw.length() == 0) {
            return new Bits();
        }
        byte[] bys = raw.getBytes(StandardCharsets.UTF_8);
        return ofByte(bys);
    }

    public static Bits ofByte(byte val) {
        return ofByte(val, BYTE_SIZE);
    }

    public static Bits ofByte(byte val, int len) {
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < BYTE_SIZE - 1; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        for (; j >= 0; j--) {
            bits.table[j] = val >= 0 ? Bit.ZERO : Bit.ONE;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofByte(byte[] val) {
        if (val == null || val.length == 0) return new Bits();
        int i = 0;
        Bits first = ofByte(val[i++]);
        for (; i < val.length; i++) {
            first.append(ofByte(val[i]));
        }
        return first;
    }

    public static Bits ofShort(short val) {
        return ofShort(val, SHORT_SIZE);
    }

    public static Bits ofShort(short val, int len) {
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < SHORT_SIZE - 1; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        for (; j >= 0; j--) {
            bits.table[j] = val >= 0 ? Bit.ZERO : Bit.ONE;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofShort(short[] val) {
        if (val == null || val.length == 0) return new Bits();
        int i = 0;
        Bits first = ofShort(val[i++]);
        for (; i < val.length; i++) {
            first.append(ofShort(val[i]));
        }
        return first;
    }

    public static Bits ofInt(int val) {
        return ofInt(val, INT_SIZE);
    }

    public static Bits ofInt(int val, int len) {
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < INT_SIZE - 1; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        for (; j >= 0; j--) {
            bits.table[j] = val >= 0 ? Bit.ZERO : Bit.ONE;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofInt(int[] val) {
        if (val == null || val.length == 0) return new Bits();
        int i = 0;
        Bits first = ofInt(val[i++]);
        for (; i < val.length; i++) {
            first.append(ofInt(val[i]));
        }
        return first;
    }

    public Bits getByte(int index) {
        Bits subBits = subBits(index * BYTE_SIZE, (index + 1) * BYTE_SIZE);
        if (subBits.used < BYTE_SIZE) {
            return ofZero(BYTE_SIZE - subBits.used).append(subBits);
        }
        return subBits;
    }

    public Bits getShort(int index) {
        Bits subBits = subBits(index * SHORT_SIZE, (index + 1) * SHORT_SIZE);
        if (subBits.used < SHORT_SIZE) {
            return ofZero(SHORT_SIZE - subBits.used).append(subBits);
        }
        return subBits;
    }

    public Bits getInt(int index) {
        Bits subBits = subBits(index * INT_SIZE, (index + 1) * INT_SIZE);
        if (subBits.used < INT_SIZE) {
            return ofZero(INT_SIZE - subBits.used).append(subBits);
        }
        return subBits;
    }

    public Bits getLong(int index) {
        Bits subBits = subBits(index * LONG_SIZE, (index + 1) * LONG_SIZE);
        if (subBits.used < LONG_SIZE) {
            return ofZero(LONG_SIZE - subBits.used).append(subBits);
        }
        return subBits;
    }

    public int length() {
        return this.used;
    }

    public int byteLength() {
        return this.used / BYTE_SIZE;
    }

    public int shortLength() {
        return this.used / SHORT_SIZE;
    }

    public int intLength() {
        return this.used / INT_SIZE;
    }

    public int longLength() {
        return this.used / LONG_SIZE;
    }

    public static Bits ofLong(long val) {
        return ofLong(val, LONG_SIZE);
    }

    public static Bits ofLong(long val, int len) {
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < LONG_SIZE - 1; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        for (; j >= 0; j--) {
            bits.table[j] = val >= 0 ? Bit.ZERO : Bit.ONE;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofLong(long[] val) {
        if (val == null || val.length == 0) return new Bits();
        int i = 0;
        Bits first = ofLong(val[i++]);
        for (; i < val.length; i++) {
            first.append(ofLong(val[i]));
        }
        return first;
    }

    public static Bits ofZero(int len) {
        Bits bits = new Bits();
        bits.expand(len);
        for (int i = 0; i < len; i++) {
            bits.table[i] = Bit.ZERO;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofZero() {
        return ofZero(1);
    }

    public static Bits ofOne(int len) {
        Bits bits = new Bits();
        bits.expand(len);
        for (int i = 0; i < len; i++) {
            bits.table[i] = Bit.ONE;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofOne() {
        return ofOne(1);
    }

    public static Bits ofBits(byte val, int len) {
        if (len > BYTE_SIZE) {
            throw new IllegalArgumentException(len + " can not larger than byte value");
        }
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < len; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += len;
        return bits;
    }

    public static Bits ofBits(short val, int len) {
        if (len > SHORT_SIZE) {
            throw new IllegalArgumentException(len + " can not larger than short value");
        }
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < len; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += len;
        return bits;
    }
    public static Bits ofBits(int val, int len) {
        if (len > INT_SIZE) {
            throw new IllegalArgumentException(len + " can not larger than int value");
        }
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < len; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += len;
        return bits;
    }
    public static Bits ofBits(long val, int len) {
        if (len > LONG_SIZE) {
            throw new IllegalArgumentException(len + " can not larger than long value");
        }
        Bits bits = new Bits();
        bits.expand(len);
        int i = 0, j = len-1;
        for (; i < len; i++, j--) {
            bits.table[j] = (((val>>i) & 0x01) ==1) ? Bit.ONE : Bit.ZERO;
        }
        bits.used += len;
        return bits;
    }

    public Bits append(Bits other) {
        int newLen = this.used + other.used;
        expand(newLen);
        System.arraycopy(other.table, 0, table, this.used, other.used);
        this.used += other.used;
        return this;
    }

    public Bits append(Bit bit) {
        int newLen = this.used + 1;
        expand(newLen);
        table[this.used] = bit;
        this.used += 1;
        return this;
    }

    private void expand(int newLen) {
        if (newLen <= this.size) {
            return;
        }
        Bits n = new Bits();
        n.size = newLen;
        n.table = new Bit[newLen];
        n.used = this.used;
        System.arraycopy(table, 0, n.table, 0, used);

        this.size = n.size;
        this.table = n.table;
        this.used = n.used;
    }

    public Bits subBits(int fromIndex, int toIndex) {
        subBitsRangeCheck(fromIndex, toIndex, used);
        Bits bits = new Bits();
        int newLen = toIndex - fromIndex;
        bits.expand(newLen);
        System.arraycopy(this.table, fromIndex, bits.table, 0, newLen);
        bits.used += newLen;
        return bits;
    }

    static void subBitsRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    @Deprecated
    public Bit[] table(){
        return toArray();
    }

    public Bit[] toArray() {
        return Arrays.copyOf(table, used);
    }

    public List<Bit> toList() {
        Bit[] usedTable = new Bit[used];
        System.arraycopy(table, 0, usedTable, 0, used);
        return Arrays.asList(usedTable);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < used; i++) {
            builder.append(table[i].value());
        }
        return builder.toString();
    }

    @Override
    public Iterator<Bit> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Bit> {
        int cursor = 0;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != length();
        }

        @Override
        public Bit next() {
            try {
                int i = cursor;
                Bit next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }

    public Bit get(int index) {
        if (index < 0 || index >= used)
            throw new IndexOutOfBoundsException();
        return table[index];
    }

    public Bit set(int index, Bit bit) {
        if (index < 0 || index >= used)
            throw new IndexOutOfBoundsException();
        Bit oldValue = table[index];
        table[index] = bit;
        return oldValue;
    }

    @Override
    public Bits clone() {
        Bits dest = null;
        try{
            dest = (Bits) super.clone();
            dest.table = new Bit[used];
            System.arraycopy(table, 0, dest.table, 0, used);
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bits bits = (Bits) o;
        return Arrays.equals(table, bits.table);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }

    public static void copy(Bits src, int srcPos, Bits dest, int destPos, int length) {
        System.arraycopy(src.table, srcPos, dest.table, destPos, length);
    }

    public static class Encoder {
        public static int encodeZigzagValue(int value) {
            return  (value << 1) ^ (value >> 31);
        }

        public static long encodeZigzagValue(long value) {
            return  (value << 1) ^ (value >> 63);
        }

        public static Bits encodeIntValue(int value) {
            return Bits.ofString(Integer.toBinaryString(value));
        }

        public static Bits encodeLongValue(long value) {
            return Bits.ofString(Long.toBinaryString(value));
        }

        public static Bits encodeDecimalValue(double value) {
            Bits bits = new Bits();
            while (value-(int)value != 0) {
                int left = (int)(value * 2);
                bits.append(left == 0 ? Bit.ZERO : Bit.ONE);
                value = value * 2 - left;
            }
            return bits;
        }

        public static Bits encodeFloatValue(float value) {
            int intValue = (int)value;
            float dotValue = BigDecimal.valueOf(value).subtract(BigDecimal.valueOf(intValue)).floatValue();
            Bits bits1 = encodeIntValue(Math.abs(intValue));
            Bits bits2 = encodeDecimalValue(dotValue);
            int currentDotIndex = bits1.length();
            int firstDotIndex = -1;
            Bits bits = bits1.append(bits2);
            for (int i = 0; i < bits.length(); i++) {
                if (Bit.ONE == bits.get(i)) {
                    firstDotIndex = i;
                    break;
                }
            }
            if (firstDotIndex == -1) {
                return Bits.ofString("00000000000000000000000000000000");
            }
            Bits S = value < 0 ? Bits.ofOne() : Bits.ofZero();
            Bits E = Bits.ofZero(8);
            Bits eValue = encodeIntValue(currentDotIndex - firstDotIndex -1 + 127);
            Bits.copy(eValue, 0, E, Math.max(0, 8 - eValue.length()), Math.min(eValue.length(), 8));
            Bits M = Bits.ofZero(23);
            Bits.copy(bits, firstDotIndex+1, M, 0, Math.min(bits.length() - firstDotIndex - 1, 23));
            return S.append(E).append(M);
        }

        public static Bits encodeDoubleValue(double value) {
            int intValue = (int)value;
            double dotValue = BigDecimal.valueOf(value).subtract(BigDecimal.valueOf(intValue)).doubleValue();
            Bits bits1 = encodeIntValue(Math.abs(intValue));
            Bits bits2 = encodeDecimalValue(dotValue);
            int currentDotIndex = bits1.length();
            int firstDotIndex = -1;
            Bits bits = bits1.append(bits2);
            for (int i = 0; i < bits.length(); i++) {
                if (Bit.ONE == bits.get(i)) {
                    firstDotIndex = i;
                    break;
                }
            }
            if (firstDotIndex == -1) {
                return Bits.ofString("0000000000000000000000000000000000000000000000000000000000000000");
            }
            Bits S = value < 0 ? Bits.ofOne() : Bits.ofZero();
            Bits E = Bits.ofZero(11);
            Bits eValue = encodeIntValue(currentDotIndex - firstDotIndex -1 + 1023);
            Bits.copy(eValue, 0, E, Math.max(0, 11 - eValue.length()), Math.min(eValue.length(), 11));
            Bits M = Bits.ofZero(52);
            Bits.copy(bits, firstDotIndex+1, M, 0, Math.min(bits.length() - firstDotIndex - 1, 52));
            return S.append(E).append(M);
        }

        public static Bits encodeStringValue(String value) {
            return Bits.ofByte(value.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static class Decoder {
        public static int decodeZigzagValue(int value) {
            return (value >> 1) ^ -(value & 1);
        }

        public static long decodeZigzagValue(long value) {
            return (value >> 1) ^ -(value & 1);
        }

        public static int decodeIntValue(Bits value) {
            int res = 0;
            for (int i = value.length() - 1, j = 0; i >= 0; i--, j++) {
                res = res + value.get(i).value() * (int)pow(2, j);
            }
            return res;
        }

        public static double decodeDecimalValue(Bits value) {
            double res = 0;
            for (int i = 0, j = 0; i < value.length(); i++, j++) {
                res = res + value.get(i).value() * pow(2, -(j+1));
            }
            return res;
        }

        public static float decodeFloatValue(Bits value) {
            if (value.length() != 32) throw new IllegalArgumentException("float value must be 32 bits");
            if (Bits.ofString("00000000000000000000000000000000").equals(value)) return 0.0f;
            Bits bits = value.clone();
            int S = bits.get(0).value();
            int E = bits.subBits(1, 9).toInt() - 127;
            Bits M = Bits.ofOne().append(bits.subBits(9, 32));
            float floatValue =  (float)((1 + decodeDecimalValue(M.subBits(1, M.length())))* pow(2, E));
            return (S == 0 ? 1 : -1) * floatValue;
        }

        public static double decodeDoubleValue(Bits value) {
            if (value.length() != 64) throw new IllegalArgumentException("double value must be 64 bits");
            if (Bits.ofString("0000000000000000000000000000000000000000000000000000000000000000").equals(value)) return 0.0;
            Bits bits = value.clone();
            int S = bits.get(0).value();
            int E = bits.subBits(1, 12).toInt() - 1023;
            Bits M = Bits.ofOne().append(bits.subBits(12, 64));
            double doubleValue =  (1 + decodeDecimalValue(M.subBits(1, M.length())))* pow(2, E);
            return (S == 0 ? 1 : -1) * doubleValue;
        }
    }
}
