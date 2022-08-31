package util;

import java.util.Scanner;

/**
 * @author lrd
 * @date 2022-08-29 下午3:59
 */
public class Formatutils {
    public static Integer inputInteger(Scanner sc) {
        Integer a = null;
        while (true) {
            if (sc.hasNextLine()) {//如果有数据可读
                try {
                    a = Integer.valueOf(sc.nextLine());//读入数据
                    if(a<0){
                        System.out.println("输入数据异常或格式错误,请重新输入");//输出文本
                        continue;
                    }
                } catch (NumberFormatException e) {//捕获输入异常
                    System.out.println("输入数据异常或格式错误,请重新输入");//输出文本
                    continue;//重新循环
                }
            }
            return a;//返回Integer
        }
    }
    public static String inputString(Scanner sc){
        if (sc.hasNextLine()) //如果有输入
            return sc.nextLine();
        return null;
    }
}
