package dao;

import model.Comment;
import model.Section;
import model.Sensitiveword;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午4:24
 */
public class SensitivewordsDaoImpl extends BaseDao<Sensitiveword> implements SensitivewordsDao{
    @Override
    public List<Sensitiveword> getComment() {
        String sql="select * from `sstiveword`";
        List beanList = BaseDao.getForList(Sensitiveword.class, sql);
        return beanList;
    }
    @Override
    public void saveComment(Connection conn, Sensitiveword sensitiveword) {
        String sql="insert into `sstiveword` VALUES (?)";
        BaseDao.update(conn,sql,sensitiveword.getSstiveword());
    }

    @Override
    public void deleteCommentById(Connection conn, String sensitiveword) {
        String sql="delete from `sstiveword` where  `Sstiveword` = ?";
        BaseDao.update(conn,sql,sensitiveword);
    }

}
