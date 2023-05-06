package com.xylink.test.scheme.proxy.jdk;

/**
 * @author 刘启航
 * @date 2023/4/8 16:48
 * @desc
 */
public class DemoImpl implements Demo {
    @Override
    public String appendStr(String source, String append) {
        return source + "_proxy_"  + append;
    }
}
