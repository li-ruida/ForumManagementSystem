package test;

import org.junit.Test;
import util.JDBCCRUD;

/**
 * @author lrd
 * @date 2022-08-17 下午6:15
 */
public class JDBCtest {
    @Test
    public void test1(){
        String sql="insert into student values(?,?,?)";
        JDBCCRUD.update(sql,10015,"李正","123456");
    }
}
