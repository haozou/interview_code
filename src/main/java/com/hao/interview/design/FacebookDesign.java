package com.hao.interview.design;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by hzou on 5/18/17.
 */
public class FacebookDesign {
    public String genShortenHash(String str) {
        return new String(Base64.encodeBase64(DigestUtils.md5(str)));
    }

}
