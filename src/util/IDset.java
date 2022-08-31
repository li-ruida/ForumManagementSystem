package util;

import org.junit.Test;

/**
 * @author lrd
 * @date 2022-08-30 下午6:26
 */
public class IDset {
    public static long count1(long num){//计算位数
        long ans=0;
        while (num>0){
            num/=10;
            ans++;
        }
        return ans;
    }
    @Test
    public void test1(){
        long l = count1(111);
        String str=111+"";
        for(long i=6-l;i>0;i--){
            str="0"+str;
        }
        System.out.println(str);
    }
}
