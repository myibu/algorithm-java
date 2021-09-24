package com.github.myibu.algorithm.filter;

import java.util.List;
import java.util.Set;

/**
 * Sensitive Word filter
 * @author myibu
 * Created on 2021/9/23
 */
public interface SensitiveWordFilter {
    /**
     * add sensitive word
     * @param sensitiveWords sensitive word
     * @return SensitiveWordFilter
     */
    SensitiveWordFilter addWords(Set<String> sensitiveWords);

    /**
     * search sensitive word in a text
     * @param text text
     * @return sensitive word list
     */
    List<String> searchWords(String text);
}
