package com.hao.interview;

/**
 * Created by hzou on 9/8/16.
 */
public class Trie {
    public static class TrieNode {
        private final int CHILDREN_SIZE = 26;
        char val;
        TrieNode[] children;
        boolean isLeaf;
        public TrieNode(char val) {
            this.val = val;
            this.isLeaf = false;
            this.children = new TrieNode[CHILDREN_SIZE];
        }
    }

    private TrieNode root;
    public Trie() {
        this.root = new TrieNode(' ');
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode(c);
            }
            cur = cur.children[index];
        }
        cur.isLeaf = true;
    }

    public boolean startsWith(String word) {
        TrieNode node = findLastNode(word);
        if (node == null) return false;
        return true;
    }

    public boolean search(String word) {
        TrieNode node = findLastNode(word);
        if (node == null) return false;
        return node.isLeaf;
    }

    public boolean searchPattern(String word) {
        return searchPattern(word, 0, root);
    }

    private boolean searchPattern(String word, int index, TrieNode node) {
        if (node == null || index > word.length()) return false;
        if (index == word.length()) return node.isLeaf;

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode n: node.children) {
                if (searchPattern(word, index + 1, n)) {
                    return true;
                }
            }
            return false;
        } else {
            return searchPattern(word, index + 1, node.children[c - 'a']);
        }
    }

    private TrieNode findLastNode(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (cur.children[index] != null) {
                cur = cur.children[index];
            } else {
                return null;
            }
        }
        return cur;
    }



}
