package com.pyjava.regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangheng on 2017/5/5.
 */
public class TestRegular {
    public static void main(String[] args) {
        String str = "wh_peak@163.com";
        String regEx = "^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";
        TestRegular.test(str,regEx);

    }

    public static void test(String str,String regEx)
    {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.find();
        System.out.println(rs);
    }
}
