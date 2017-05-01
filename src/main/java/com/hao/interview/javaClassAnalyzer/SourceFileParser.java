package com.hao.interview.javaClassAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hzou on 4/26/17.
 */
public class SourceFileParser {
    private String input;
    private List<JavaClass> javaClasses = new ArrayList<>();
    private List<InvalidJavaClass> invalidJavaClasses = new ArrayList<>();

    public SourceFileParser(String input) {
        this.input = input;
    }


    public List<JavaClass> getJavaClasses() {
        return javaClasses;
    }

    public List<InvalidJavaClass> getInvalidJavaClasses() {
        return invalidJavaClasses;
    }
    public void discoverSources() {
        discoverSources(new File(input));
    }

    public void discoverSources(File file) {
        if (file == null || !file.exists()) return;

        if (file.isFile()) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith(".java")) {
                int[] result = detectSLOCAndSLOCExcludeComments(file);
                javaClasses.add(new JavaClass(absolutePath, result[0], result[1]));
            } else {
                invalidJavaClasses.add(new InvalidJavaClass(absolutePath));
            }
        } else {
            for (File child: file.listFiles()) {
                discoverSources(child);
            }
        }
        Collections.sort(javaClasses);
    }

    private int[] detectSLOCAndSLOCExcludeComments(File file) {
        int sloc = 0;
        int slocExcludeComments = 0;
        try {
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;

            boolean isComments = false;
            while ((line = br.readLine()) != null) {

                line = line.trim();
                // execluding the whiteSpace
                if (line.isEmpty()) continue;

                if (line.startsWith("/*")) {
                    isComments = true;
                }

                if (!isComments && !line.startsWith("//")) {
                    slocExcludeComments++;
                }

                if (line.endsWith("*/")) {
                    isComments = false;
                }

                sloc++;
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new int[]{sloc, slocExcludeComments};
    }

    public int getAllSloc() {
        int sum = 0;
        for (JavaClass javaClass: javaClasses) {
            sum += javaClass.getSloc();
        }
        return sum;
    }

    public int getAllSlocWithoutComments() {
        int sum = 0;
        for (JavaClass javaClass: javaClasses) {
            sum += javaClass.getSlocWithoutComments();
        }
        return sum;
    }
}
