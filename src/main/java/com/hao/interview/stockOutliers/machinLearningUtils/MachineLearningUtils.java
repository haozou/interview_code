package com.hao.interview.stockOutliers.machinLearningUtils;

/**
 * Created by hzou on 10/25/17.
 */
public class MachineLearningUtils {
    public static double[] linerRegression(double[] xs, double[] ys) {
        double sumOfx = 0, sumOfy = 0, sumOfxy = 0, sumOfxSquare = 0, sumOfySquare = 0;
        for (double x: xs) {
            sumOfx += x;
            sumOfxSquare += Math.pow(x, 2);
        }
        for (double y: ys) {
            sumOfy += y;
            sumOfySquare += Math.pow(y, 2);
        }

        for (int i = 0; i < xs.length; i++) {
            sumOfxy += xs[i] * ys[i];
        }

        double a = (sumOfy * sumOfxSquare - sumOfx * sumOfxy) /
                (xs.length * sumOfxSquare - Math.pow(sumOfx, 2));
        double b = (xs.length * sumOfxy - sumOfx * sumOfy) /
                (xs.length * sumOfxSquare - Math.pow(sumOfx, 2));

        return new double[]{a, b};
    }

    public static double getVariance(double[] ys) {
        double sum = 0;
        for (int i = 0; i < ys.length; i++) {
            sum += ys[i];
        }
        double mean = sum / ys.length;
        sum = 0;
        for (int i = 0; i < ys.length; i++) {
            sum += Math.pow(ys[i] - mean, 2);
        }
        mean = sum / (ys.length);
        return mean;
    }
}
