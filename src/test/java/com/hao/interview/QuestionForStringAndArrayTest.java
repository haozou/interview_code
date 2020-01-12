package com.hao.interview;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by hzou on 8/31/16.
 */
public class QuestionForStringAndArrayTest {
    @Test
    public void testIsUnique() {
        QuestionForStringAndArray q1 = new QuestionForStringAndArray();
        assertFalse(q1.isUnique("aaa"));
    }
}
