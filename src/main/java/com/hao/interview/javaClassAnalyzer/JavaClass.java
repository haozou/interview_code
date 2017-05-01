package com.hao.interview.javaClassAnalyzer;

/**
 * Created by hzou on 4/26/17.
 */
public class JavaClass extends SourceFile implements Comparable<JavaClass> {
    private int sloc;
    private int slocWithoutComments;

    public JavaClass(String absolutePath, int sloc, int slocWithoutComments) {
        super(absolutePath);
        this.sloc = sloc;
        this.slocWithoutComments = slocWithoutComments;
    }

    public int getSlocWithoutComments() {
        return slocWithoutComments;
    }

    public void setSlocWithoutComments(int slocWithoutComments) {
        this.slocWithoutComments = slocWithoutComments;
    }

    public int getSloc() {
        return sloc;
    }

    public void setSloc(int sloc) {
        this.sloc = sloc;
    }

    @Override
    public int compareTo(JavaClass javaClass) {
        return sloc - javaClass.getSloc();
    }

    public String toString() {
        String str = String.format("absolutePath: %s, SLOC: %d, SLOC without comments: %d",
                absolutePath, sloc, slocWithoutComments);
        return str;
    }
}
