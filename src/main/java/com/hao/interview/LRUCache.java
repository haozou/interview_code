package com.hao.interview;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzou on 9/8/16.
 */
public class LRUCache {
    static class DLLNode {
        int key, val;
        DLLNode next;
        DLLNode prev;
        public DLLNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "DLLNode{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private Map<Integer, DLLNode> cache;

    private int capacity;
    private DLLNode head;
    private DLLNode tail;


    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            removeNode(node);
            setHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    private void removeNode(DLLNode node) {
        if (node == null) return;
        DLLNode prev = node.prev;
        DLLNode next = node.next;
        if (prev != null) {
            prev.next = next;
            node.prev = null;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
            node.next = null;
        } else {
            tail = prev;
        }
    }

    private void setHead(DLLNode node) {
        if (node == null) return;
        if (head != null) {
            head.prev = node;
        } else {
            tail = node;
        }
        node.next = head;
        node.prev = null;
        head = node;
    }

    public void set(int key, int val) {
        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.val = val;
            removeNode(node);
            setHead(node);
        } else {
            DLLNode node = new DLLNode(key, val);
            if (cache.size() == capacity) {
                cache.remove(tail.key);
                removeNode(tail);
            }
            cache.put(key, node);
            setHead(node);
        }
    }

    @Override
    public String toString() {
        String result1 = "";
        for (Map.Entry<Integer, DLLNode> entry: cache.entrySet()) {
            result1 += "[" + entry.getKey() + ":" +  entry.getValue() + "]" + " ";

        }
        result1 = "{" + result1 + "}";
        String result2 = "";
        for (DLLNode cur = head; cur != null; cur = cur.next) {
            result2 += cur + " ";
        }
        result2 = "{" + result2 + "}";

        return result1 + "\n" + result2;
    }
}
