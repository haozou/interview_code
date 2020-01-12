package com.hao.interview;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by hzou on 9/6/16.
 */
public class QuestionForTreeTest {
    private QuestionForTree q;
    @BeforeTest
    public void init() {
        q = new QuestionForTree();
    }

    @Test
    public void testPrintAllPathFromRoot() {
        QuestionForTree.TreeNode root = q.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8,9});
        q.printLevelOrder(root);
        q.printAllPathFromRoot(root);
        System.out.println(q.getMostWeightedPath(root));
        q.convertBSTToDLL(root);
    }

}
