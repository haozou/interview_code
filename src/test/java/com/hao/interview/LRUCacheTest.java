package com.hao.interview;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by hzou on 9/8/16.
 */
public class LRUCacheTest {
    @Test
    public void testLRUCache() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(1, 2);
        lruCache.set(2, 3);
        lruCache.set(3, 4);
        lruCache.set(4, 5);
        System.out.println(lruCache);
        assertEquals(lruCache.get(1), -1);
        assertEquals(lruCache.get(4), 5);
        assertEquals(lruCache.get(2), 3);

        System.out.println(lruCache);
    }
}
