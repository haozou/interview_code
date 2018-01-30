package com.hao.interview;

import com.hao.interview.QuestionForTree.TreeNode;
import com.hao.interview.concurrency.Broker;
import com.hao.interview.concurrency.Consumer;
import com.hao.interview.concurrency.Producer;
import com.hao.interview.dataStructure.NestedElement;
import com.hao.interview.dataStructure.NestedElementInterface;
import com.hao.interview.dataStructure.NestedList;
import com.hao.interview.stockOutliers.machinLearningUtils.MachineLearningUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hzou on 10/2/16.
 */
public class GoogleTest {
    private Facebook facebook = new Facebook();

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
        System.out.println(google.permutations(new int[]{1,2,3}));
        System.out.println(google.permutationNonUnique(new int[]{1,2,2}));
        google.stringPermutation("aac");
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
            Future producerStatus = threadPool.submit(new Producer("producer", broker));

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

    @Test
    public void testAllRootToLeafSum() {
        QuestionForTree q = new QuestionForTree();
        QuestionForTree.TreeNode root = q.createMinimalHeightBinaryTree(new int[]{1,2,3,4,5,6,7,8,9});
        q.printLevelOrder(root);
        System.out.println(google.allRootToLeafSum(root, 19));
        System.out.println(google.hasRootToLeafSum(root, 11));
        google.allPaths(root, 0);
        System.out.println(google.allPathsSum(root, 0));
    }

    @Test
    public void testCheckInclusion() {
        google.checkInclusion("ba", "eidbaooo");
    }

    @Test
    public void testNestedList() {
        final NestedElementInterface<Integer> nestedElement1 = new NestedElement<Integer>(1);
        final NestedElementInterface<Integer> nestedElement2 = new NestedElement<Integer>(1);
        NestedElementInterface<Integer> nestedElements1 = new NestedElement(new ArrayList<NestedElementInterface<Integer>>() {{
          add(nestedElement1);
          add(nestedElement2);
        }});
        final NestedElementInterface<Integer> nestedElement3 = new NestedElement<Integer>(2);
        final NestedElementInterface<Integer> nestedElement4 = new NestedElement<Integer>(3);
        final NestedElementInterface<Integer> nestedElement5 = new NestedElement<Integer>(3);
        NestedElementInterface<Integer> nestedElements2 = new NestedElement(new ArrayList<NestedElementInterface<Integer>>() {{
            add(nestedElement4);
            add(nestedElement5);
        }});
        List<NestedElementInterface<Integer>> lists = new ArrayList<>();
        lists.add(nestedElements1);
        lists.add(nestedElement3);
        lists.add(nestedElements2);
        NestedList<Integer>  nestedList = new NestedList<>(lists);
        while (nestedList.hasNext()) {
            System.out.println(nestedList.next());
        }


    }

//    11110
//            11010
//            11000
//            00000
    @Test
    public void testNumOfIslands() {
        int[][] matrix = new int[][]{
                {1,1,0,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,1,1,1}
        };
        System.out.println(google.numOfIslands(matrix));


    }

    /**
      10
     /  \
     5   -3
     / \    \
     3   2   11
     / \   \
     3  -2   1

     */
    @Test
    public void allPathsTest() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(-2);
        node.right = new TreeNode(-3);
        node.right.left = new TreeNode(-2);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.left.left.left = new TreeNode(-1);
//        node.left.left.right = new TreeNode(-2);
//        node.left.right.right = new TreeNode(1);
        QuestionForTree.Result result = new QuestionForTree.Result();
        result.val = google.allPaths(node, "", 3);
        System.out.println(result.val);
    }

    @Test
    public void linerRegressionTest() {
        double[] xs = new double[]{43, 21, 25, 42, 57, 59};
        double[] ys = new double[]{99, 65, 79, 75, 87, 81};
        double[] result = MachineLearningUtils.linerRegression(xs, ys);
        System.out.println(result[0] + "\n" + result[1]);
    }


    @Test
    public void testFindPaths() {
       System.out.println(google.findPaths(new String[]
               {"a,b", "b,c", "c,d", "e,f", "a,d", "d,f", "d,a", "c,f"},
               "a", "f"));

        System.out.println(google.calculate(new String[]
                        {"a,b,2", "b,c,3", "c,d,4", "e,f,5", "a,d,3", "d,f,5", "d,a,6", "c,f,5"},
                "a", "f"));

        System.out.println(google.getAllPaths(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}}));
    }

    @Test
    public void testMissingBrackets() {
        System.out.println(google.missingBrackets("((((())"));
        System.out.println(google.missingBrackets("))(()))((())"));
    }


}

