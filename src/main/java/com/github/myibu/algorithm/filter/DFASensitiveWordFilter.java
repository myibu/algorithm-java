package com.github.myibu.algorithm.filter;

import java.util.*;

/**
 * Sensitive Word filter based on DFA algorithm
 * @author myibu
 * Created on 2021/9/23
 */
public class DFASensitiveWordFilter implements SensitiveWordFilter {
    private DictionaryTree tree;

    public DFASensitiveWordFilter() {
        this.tree = new DictionaryTree();
    }

    public static SensitiveWordFilter fromWordSet(Set<String> fromWordSet) {
        SensitiveWordFilter filter = new DFASensitiveWordFilter();
        return filter.addWords(fromWordSet);
    }

    @Override
    public SensitiveWordFilter addWords(Set<String> sensitiveWords) {
        tree.insertAll(sensitiveWords.toArray(new String[0]));
        return this;
    }

    @Override
    public List<String> searchWords(String text) {
        return tree.search(text);
    }
}
