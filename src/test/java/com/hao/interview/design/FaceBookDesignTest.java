package com.hao.interview.design;

import org.testng.annotations.Test;

/**
 * Created by hzou on 5/18/17.
 */
public class FaceBookDesignTest {
    private FacebookDesign facebookDesign = new FacebookDesign();
    @Test
    public void testGenShortenHash() {
        String str = "http://www.gopro.com/helloworld";
        //System.out.println(facebookDesign.genMd5(str));
        System.out.println(facebookDesign.genShortenHash(str));
    }
}
