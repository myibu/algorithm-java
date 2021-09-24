package com.github.myibu.algorithm.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dictionary tree algorithm
 * @author myibu
 * Created on 2021/9/24
 */
public class DictionaryTree {
    static final State START = new State();
    static final State END = new State();
    static class State {
        /**
         * transfer to next state according to current character
         */
        Map<Character, State> transfer;

        public State() {
            this.transfer = new HashMap<>();
        }
    }
    State root;

    public void insert(CharSequence word) {
        State cur;
        if (root == null) {
            root = START;
        }
        cur = root;
        // iterator word
        for (int pos = 0; pos < word.length(); pos++) {
            char ch = word.charAt(pos);
            // point to END state if current position is the end character of word
            if (pos == word.length()-1) {
                cur.transfer.put(ch, END);
                continue;
            }
            // change current position to next state if current character is found
            if (cur.transfer.containsKey(ch)) {
                cur = cur.transfer.get(ch);
            }
            // create a new state and point current state to new state
            else {
                State newState = new State();
                cur.transfer.put(ch, newState);
                cur = newState;
            }
        }
    }

    public void insertAll(String[] words) {
        for (String word: words)
            insert(word);
    }

    public List<String> search(CharSequence txt) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < txt.length(); i++) {
            String word = search(txt, i, txt.length());
            if (word != null) {
                words.add(word);
            }
        }
        return words;
    }

    public String search(CharSequence txt, int start, int end) {
        State cur = root;
        int i = start;
        while (i < end) {
            char ch = txt.charAt(i);
            if (!cur.transfer.containsKey(ch)) break;
            cur = cur.transfer.get(ch);
            i++;
        }
        return cur == END ? txt.subSequence(start, i).toString() : null;
    }

    public static void main(String[] args) {

    }
}
