package com.hao.interview.javaClassAnalyzer;

/**
 * Created by hzou on 4/26/17.
 */
public class JavaClassAnalyzer {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: JavaClassAnalyzer <inputPath>");
        }
        String inputPath = args[0];
        SourceFileParser sourceFileParser = new SourceFileParser(inputPath);
        sourceFileParser.discoverSources();

        System.out.println("total number of valid classes:");
        System.out.println(sourceFileParser.getJavaClasses().size());

        System.out.println("all valid classes:");
        for (JavaClass javaClass: sourceFileParser.getJavaClasses()) {
            System.out.println(javaClass);
        }

        System.out.println("total sloc:");
        System.out.println(sourceFileParser.getAllSloc());

        System.out.println("total sloc excluding comments:");
        System.out.println(sourceFileParser.getAllSlocWithoutComments());

        System.out.println("All invalid classes:");
        for (InvalidJavaClass invalidJavaClass: sourceFileParser.getInvalidJavaClasses())
        System.out.println(invalidJavaClass);

    }
}
