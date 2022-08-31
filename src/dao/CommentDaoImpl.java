package dao;

import model.Comment;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午3:35
 */
public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao{
    @Override
    public List<Comment> getComment() {
        String sql="select * from `comment`";
        List beanList = BaseDao.getForList(Comment.class, sql);
        return beanList;
    }

    @Override
    public void saveComment(Connection conn, Comment comment) {
        String sql="insert into `comment` VALUES (?,?,?,?)";
        BaseDao.update(conn,sql,comment.getIdPosts(),comment.getCommenttext(),comment.getUserIds(),comment.getCommentId());
    }

    @Override
    public void deleteCommentById(Connection conn, String commentid) {
        String sql="delete from `comment` where  `commentId` = ?";
        BaseDao.update(conn,sql,commentid);
    }

    @Override
    public Comment getCommentById(Connection conn, String commentId) {
        String sql="select * from `comment` where  `commentId` = ?";
        return BaseDao.getInstance(Comment.class,sql,commentId);
    }

    @Override
    public void updateComment(Connection conn, Comment comment) {
        String sql="update `comment` set `commenttext`=? , `UserIds`=? ,`idPosts`=? where `commentId`=?;";
        BaseDao.update(conn,sql,comment.getCommenttext(),comment.getUserIds(),comment.getIdPosts(),comment.getCommentId());
    }
    public static Long countnum(){
        Connection conn= JDBCUtils.getConnection();
        String sql="select count(*) from ourforum.comment;";
        Long value = BaseDao.<Long>getValue(conn, sql);
        return value;
    }
}
