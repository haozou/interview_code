package com.hao.interview.design;

import org.testng.annotations.Test;

/**
 * Created by hzou on 1/24/18.
 */
public class Base62Test {
    @Test
    public void testBase62() {
        System.out.println(Base62.fromBase10(8000000L));
        System.out.println(Base62.toBase10("KYRoqI3"));
    }
}
