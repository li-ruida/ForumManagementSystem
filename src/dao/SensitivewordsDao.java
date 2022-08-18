package dao;

import model.Comment;
import model.Sensitiveword;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午4:21
 */
public interface SensitivewordsDao {
    List<Sensitiveword> getComment();//获取Comment类

    void saveComment(Connection conn, Sensitiveword sensitiveword);//插入Comment

    void deleteCommentById(Connection conn,String sensitiveword);//删除Comment


}
