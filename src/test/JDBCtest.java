package test;

import dao.UserDaoImpl;
import model.Sensitiveword;
import org.junit.Test;
import util.JDBCCRUD;

import java.util.List;

import static util.Sensitivewords.myregexp;

/**
 * @author lrd
 * @date 2022-08-17 下午6:15
 */
public class JDBCtest {
    @Test
    public void test1(){
        String sql="select `Sstiveword` from `sstiveword`";
        List<Sensitiveword> Sensitivewords = JDBCCRUD.getForList(Sensitiveword.class,sql);
        for (Sensitiveword i:Sensitivewords) {
            System.out.println(i);
        }
        
    }
}
