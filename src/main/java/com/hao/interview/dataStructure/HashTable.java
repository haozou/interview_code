package com.hao.interview.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by hzou on 1/17/17.
 */
public class HashTable<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private LinkedList<Node<K, V>>[] containers;

    private int size;
    final static int DEFAULT_SIZE = 10;

    public HashTable() {
        this(DEFAULT_SIZE);
    }

    public HashTable(int size) {
        this.size = size;
        containers = new LinkedList[size];
    }

    private int getNodeIndex(K key) {
        int hashedKey = hash(key);
        if (containers[hashedKey] == null) {
            return -1;
        }
        int i = 0;
        for (Node<K, V> node: containers[hashedKey]) {
            if (node.key.equals(key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public V get(K key) {
        int hashedKey = hash(key);
        int index = getNodeIndex(key);
        if (index == -1) return null;
        return containers[hashedKey].get(index).value;
    }

    public void put(K key, V value) {
        int hashedKey = hash(key);
        if (containers[hashedKey] == null) {
            containers[hashedKey] = new LinkedList<>();
        }
        int index = getNodeIndex(key);
        if (index == -1 ) {
            containers[hashedKey].add(new Node<K, V>(key, value));
        } else {
            containers[hashedKey].get(index).value = value;
        }
    }

    public void remove(K key) {
        int hashedKey = hash(key);
        int index = getNodeIndex(key);
        if (index == -1) return;
        containers[hashedKey].remove(index);

    }

    public int hash(K key) {
        if (key == null) return 0;
        return key.hashCode() % size;
    }
}
