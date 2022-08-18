package dao;

import model.Comment;
import model.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 上午10:56
 */
public interface CommentDao {
    List<Comment> getComment();//获取Comment类

    void saveComment(Connection conn,Comment comment);//插入Comment

    void deleteCommentById(Connection conn,String commentid);//删除Comment

    Comment getCommentById(Connection conn,String commentId);//根据Id查

    void updateComment(Connection conn,Comment comment);//更新信息
}
