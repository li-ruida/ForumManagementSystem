package test;

import org.junit.Test;
import util.Sensitivewords;

/**
 * @author lrd
 * @date 2022-08-17 下午6:45
 */
public class SensitivewordsTest {
    @Test
    public void test1()
    {
        String str="w cnmd f";
        boolean b = Sensitivewords.SensitivewordsCheck(str);
        System.out.println(b);
    }
    @Test
    public void test2()
    {
        String str="你是一条狗abc";
        boolean b = Sensitivewords.SensitivewordsCheck(str);
        System.out.println(b);
    }
    @Test
    public void test3()
    {
        String str="你是一只猫abc";
        boolean b = Sensitivewords.SensitivewordsCheck(str);
        System.out.println(b);
    }
}
