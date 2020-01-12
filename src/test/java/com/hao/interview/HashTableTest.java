package com.hao.interview;

import com.hao.interview.dataStructure.HashTable;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hzou on 1/17/17.
 */
public class HashTableTest {
    @Test
    public void hashTableTest() {
        HashTable<String, String> hashTable = new HashTable<>(3);
        hashTable.put("a", "a");
        hashTable.put("b", "b");
        hashTable.put("c", "c");
        hashTable.put("d", "d");
        hashTable.put("e", "e");
        hashTable.put("f", "f");
        hashTable.put("g", "g");
        hashTable.put("h", "h");
        hashTable.put("i", "i");
        hashTable.put("j", "j");
        hashTable.put("k", "k");


        Assert.assertEquals(hashTable.get("a"), "a");
        hashTable.put("k", "fuffff");
        Assert.assertEquals(hashTable.get("k"), "fuffff");
        hashTable.remove("a");
        Assert.assertEquals(hashTable.get("a"), null);

    }
}
