package dao;

import model.Comment;
import model.User;

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
        String sql="insert into `comment` VALUES (?,?,?,?,?)";
        BaseDao.update(conn,sql,comment.getIdPost(),comment.getCommentText(),comment.getCommentTime(),comment.getUserId(),comment.getCommentID());
    }

    @Override
    public void deleteCommentById(Connection conn, String commentid) {
        String sql="delete from `comment` where  `commentId` = ?";
        BaseDao.update(conn,sql,commentid);
    }

    @Override
    public Comment getCommentById(Connection conn, String commentId) {
        String sql="select from `comment` where  `commentId` = ?";
        return BaseDao.getInstance(Comment.class,sql,commentId);
    }

    @Override
    public void updateComment(Connection conn, Comment comment) {
        String sql="update `comment` set `commenttext`=? `commenttime`=? `UserIds`=? `idPosts` = ? where `commentId` =?";
        BaseDao.update(conn,sql,comment.getCommentText(),comment.getCommentTime(),comment.getUserId(),comment.getUserId(),comment.getCommentID());
    }
}
