package com.hao.interview;

/**
 * Created by hzou on 9/8/16.
 */
public class Trie {
    public static class TrieNode {
        char val;
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode(char val) {
            this.val = val;
            children = new TrieNode[26];
            this.isLeaf = false;
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode(' ');
    }


    public void addWord(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char val = word.charAt(i);
            int index = val - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode(val);
            }
            cur = cur.children[index];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char val = word.charAt(i);
            int index = val - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return cur.isLeaf;
    }

    public boolean startsWith(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char val = word.charAt(i);
            int index = val - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }

        return true;
    }


    public boolean searchPattern(String word) {
        return searchPattern(word, 0, root);
    }

    private boolean searchPattern(String word, int i, TrieNode root) {
        if (root == null) return false;
        TrieNode cur = root;
        if (i == word.length()) return root.isLeaf;

        char val = word.charAt(i);
        int index = val - 'a';
        if (val == '.') {
            boolean flag = false;
            for (TrieNode node: cur.children) {
                if (searchPattern(word, i + 1, node)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } else {
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return searchPattern(word, i + 1, cur);
    }
}
