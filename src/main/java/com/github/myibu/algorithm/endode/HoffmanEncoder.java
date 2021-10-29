package com.github.myibu.algorithm.endode;

import com.github.myibu.algorithm.compress.Compressor;
import com.github.myibu.algorithm.data.Bit;
import com.github.myibu.algorithm.data.Bits;
import java.util.*;

/**
 * Hoffman encoding
 * @author myibu
 * Created on 2021/10/29
 */
public class HoffmanEncoder implements Encoder {
    static class Symbol implements Comparable<Symbol> {
        byte value;
        int times;
        float probability;
        boolean isRaw;
        Bit bit;
        Symbol parent;
        Symbol left;
        Symbol right;

        public Symbol(byte value, float probability) {
            this.value = value;
            this.probability = probability;
            this.isRaw = true;
        }

        public Symbol(byte value, int total) {
            this.value = value;
            this.times = 1;
            this.probability = 1.0f / total;
            this.isRaw = true;
        }

        public void addTimes(int times, int total) {
            this.times += times;
            this.probability = this.times * 1.0f / total;
        }

        public static Symbol merge(Symbol s1, Symbol s2) {
            s1.bit = Bit.ZERO;
            s2.bit = Bit.ONE;
            Symbol parent = new Symbol((byte)0, s1.probability + s2.probability);
            parent.isRaw = false;
            parent.left = s1;
            parent.right = s2;
            s1.parent = parent;
            s2.parent = parent;
            return parent;
        }

        @Override
        public int hashCode() {
            int hashCode = 1;
            hashCode = 31*hashCode + (int)value;
            return hashCode;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof Symbol) {
                Symbol anotherSymbol = (Symbol)anObject;
                return this.value == anotherSymbol.value;
            }
            return false;
        }

        @Override
        public int compareTo(Symbol o) {
            if (o == null) {
                return Float.compare(this.probability, 0f);
            }
            return Float.compare(this.probability, o.probability);
        }

        @Override
        public String toString() {
            return "Symbol{" +
                    "value=" + value +
                    ", probability=" + probability +
                    ", isRaw=" + isRaw +
                    ", bit=" + bit +
                    '}';
        }
    }

    Symbol root;
    /**
     * 1. Sort the probability of all source symbols in a descending order.
     * 2. Merge the last two into a new symbol, add their probabilities.
     * 3. Repeat Step 1, 2 until only one symbol (the root) is left.
     * 4. Code assignment:
     * Traverse the tree from the root to each leaf node,
     * assign 0 to the top branch and 1 to the bottom branch
     */
    public Bits encode(byte[] in_data, int in_len) {
        List<Symbol> probs = new ArrayList<>();
        for (int i = 0; i < in_len; i++) {
            Symbol symbol = new Symbol(in_data[i], in_len);
            int index;
            if ((index = probs.indexOf(symbol)) != -1) {
                probs.get(index).addTimes(1, in_len);
            } else {
                probs.add(symbol);
            }
        }
        List<Symbol> sortedProbs = new ArrayList<>();
        for (Symbol sym: probs) {
            insertDescendingSort(sortedProbs, sym);
        }
        List<Symbol> sortedProbsCopy = new ArrayList<>(sortedProbs);
        Symbol rootSymbol = null;
        while (sortedProbs.size() > 1) {
            int len = sortedProbs.size();
            rootSymbol = Symbol.merge(sortedProbs.get(len -2), sortedProbs.get(len-1));
            sortedProbs.remove(sortedProbs.size()-1);
            sortedProbs.remove(sortedProbs.size()-1);
            insertDescendingSort(sortedProbs, rootSymbol);
        }
        root = rootSymbol;
        Map<Byte, Bits> wordDict = new HashMap<>(sortedProbsCopy.size());
        for (Symbol sym: sortedProbsCopy) {
            Symbol p = sym;
            Bits encodeBits = new Bits();
            while (p != rootSymbol) {
                encodeBits.append(p.bit);
                p = p.parent;
            }
            wordDict.put(sym.value, Bits.reverse(encodeBits));
        }
        System.out.println(wordDict);
        Bits seq = new Bits();
        for (int i = 0; i < in_len; i++) {
            seq.append(wordDict.get(in_data[i]));
        }
        return seq;
    }

    /**
     * insert sort with descending order.
     */
    private static void insertDescendingSort(List<Symbol> sortDest, Symbol target) {
        int insertIndex = -1;
        for (int i = sortDest.size()-1; i >= 0; i--) {
            if (sortDest.get(i).compareTo(target) >= 0) {
                insertIndex = i;
                break;
            }
        }
        sortDest.add(insertIndex + 1, target);
    }

    public byte[] decode(Bits encodedBits) {
        int index = 0;
        byte[] res = new byte[encodedBits.length()];
        int resLen = 0;
        Symbol p = root;
        while (index < encodedBits.length() || p.isRaw) {
            if (p.isRaw) {
                res[resLen++] = p.value;
                System.out.println(p);
                p = root;
            } else {
                p = (encodedBits.get(index++) == Bit.ZERO) ? p.left: p.right;
            }
        }
        return Arrays.copyOf(res, resLen);
    }
}
