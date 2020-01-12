package com.hao.interview;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.*;
/**
 * Created by hzou on 9/8/16.
 */
public class TrieTest {

    @Test
    public void testSearch() {
        Trie trie = new Trie();
        trie.addWord("gopro");
        trie.addWord("function");
        trie.addWord("computer");
        trie.addWord("computing");

        assertTrue(trie.search("gopro"));
        assertTrue(trie.startsWith("gopr"));
        assertFalse(trie.search("gopr"));
        assertTrue(trie.search("computer"));
        assertTrue(trie.search("computing"));
        assertTrue(trie.searchPattern("com...er"));
    }
}
