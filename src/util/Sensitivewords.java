package util;

import model.Sensitiveword;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author lrd
 * @date 2022-08-17 下午6:19
 */
public class Sensitivewords {
    public static boolean SensitivewordsCheck(String str){

        String sql="select `Sstiveword` from `sstiveword`";
        List<Sensitiveword> Sensitivewords = JDBCCRUD.getForList(Sensitiveword.class,sql);
        for (Sensitiveword i:Sensitivewords) {
            if(myregexp(str,i.getSstiveword()))
                return true;
        }

        return false;
    }
    public static boolean myregexp(String str, String sstiveword){

        String pattern = "(\\b)*"+sstiveword+"(\\b)*";
        Pattern r=Pattern.compile(pattern);
        Matcher m=r.matcher(str);
        return m.find();
    }

}
