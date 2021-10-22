package com.github.myibu.algorithm.filter;


import com.github.myibu.algorithm.filter.doublearray.AhoCorasickDoubleArrayTrie;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Aho Corasick algorithm based on Double Array Trie
 * @author hdh
 * Created on 2021/9/23
 */
public class AhoCorasickSensitiveWordFilter implements SensitiveWordFilter {
    private AhoCorasickDoubleArrayTrie<String> trie;

    public static SensitiveWordFilter fromWordSet(Set<String> sensitiveWordSet) {
        AhoCorasickSensitiveWordFilter filter = new AhoCorasickSensitiveWordFilter();
        return filter.addWords(sensitiveWordSet);
    }

    @Override
    public SensitiveWordFilter addWords(Set<String> sensitiveWords) {
        TreeMap<String, String> dataMap = new TreeMap<>();
        for (String key : sensitiveWords) {
            dataMap.put(key, key);
        }
        // Build an AhoCorasickDoubleArrayTrie
        AhoCorasickDoubleArrayTrie<String> acdat = new AhoCorasickDoubleArrayTrie<>();
        acdat.build(dataMap);
        trie = acdat;
        return this;
    }

    @Override
    public List<String> searchWords(String text) {
        List<AhoCorasickDoubleArrayTrie.Hit<String>> wordList = trie.parseText(text);
        return wordList.stream().map(t -> t.value).collect(Collectors.toList());
    }
}
