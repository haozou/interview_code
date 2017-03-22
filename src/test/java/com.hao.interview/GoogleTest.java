package com.hao.interview;

import com.hao.interview.concurrency.Broker;
import com.hao.interview.concurrency.Consumer;
import com.hao.interview.concurrency.Producer;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hzou on 10/2/16.
 */
public class GoogleTest {

    private Google<Integer> google;
    @BeforeTest
    public void setup() {
        google = new Google<Integer>();
    }

    @Test
    public void isSubsequence() {
        Assert.assertTrue(google.isSubsequence("apple", "abppple"));
        Assert.assertFalse(google.isSubsequence("apple", "abpppl"));
        Assert.assertFalse(google.isSubsequence("apple", "abpppe"));
    }

    @Test
    public void isStartWithUpperCase() {
        Assert.assertTrue(google.isStartWithUpperCase("Addd"));
        Assert.assertTrue(google.isStartWithUpperCase("A"));
        Assert.assertFalse(google.isStartWithUpperCase("aA"));
        Assert.assertTrue(google.isStartWithUpperCase("Bddd"));
        Assert.assertFalse(google.isStartWithUpperCase("a"));
    }

    @Test
    public void testBFS() {
        char[][] matrix = new char[][]{
                {'0', '0', '0', 'B'},
                {'B', 'B', 'G', '0'},
                {'B', '0', '0', '0'},
                {'B', '0', 'G', 'G'}};
        google.addShortestDist(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testCombine() {
        System.out.println(google.combinations(new int[]{1,2,3}));
    }

    @Test
    public void testPermutation() {
        System.out.println(google.permutation(new int[]{1,2,3}));
    }

    @Test
    public void testNextPermute() {
        Integer[] nums = new Integer[]{1,2,3,4};
        int count = 24;
        while (count-- > 0) {
            google.nextPermute(nums);
            System.out.println(Arrays.deepToString(nums));
        }
    }

    @Test
    public void testLetterCombinations() {
        System.out.println(google.letterCombinations2("23"));
    }

    @Test
    public void testCombination4() {
        //System.out.println(google.combination4(new int[]{1,2,3}, 32));
        System.out.println(google.combination4(new int[]{1,2,3}, 4, 2));
    }

    @Test
    public void testFactorCombination() {
        System.out.println(google.factorCombination(8));
    }

    @Test
    public void testHasPath() {
        char[][] matrix = new char[][]{
                {'0', '0', '0', 'k'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'}};
        System.out.println(google.hasPath(matrix, new int[]{3,3}));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testMajorityElement()  {
        System.out.println(google.majorityElement(new int[]{0, 3, 4, 0}, 2));
    }

    @Test
    public void testFindPrimes() {
        System.out.println(google.findPrimes(30));
    }

    @Test
    public void testFindFactors() {
        System.out.println(google.findFactors(32));
        System.out.println(google.findFactors(35));
    }

    @Test
    public void testConcurrency() {
        try
        {
            Broker broker = new Broker();

            ExecutorService threadPool = Executors.newFixedThreadPool(3);


            Future consumerStatus1 = threadPool.submit(new Consumer("1", broker));
            Future consumerStatus2 = threadPool.submit(new Consumer("2", broker));
            Future producerStatus = threadPool.submit(new Producer(broker));

            // this will wait for the consumer to finish its execution.
            consumerStatus1.get();


            threadPool.shutdown();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testKthSmallest() {
        QuestionForTree q = new QuestionForTree();
        QuestionForTree.TreeNode root = q.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8,9,10});

        root = new QuestionForTree.TreeNode(1);
        root.right = new QuestionForTree.TreeNode(2);
        System.out.println(google.kthSmallest(root, 2));
    }
}

