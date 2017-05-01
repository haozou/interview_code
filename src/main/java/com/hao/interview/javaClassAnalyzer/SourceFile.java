package com.hao.interview.javaClassAnalyzer;

/**
 * Created by hzou on 4/26/17.
 */
public abstract class SourceFile {
    protected String absolutePath;

    public SourceFile(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String toString() {
        return "absolutePath: " + absolutePath;
    }
}
