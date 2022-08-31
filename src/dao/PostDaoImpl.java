package dao;

import model.Post;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午3:44
 */
public class PostDaoImpl extends BaseDao<Post> implements PostDao{
    @Override
    public List<Post> getPost() {
        String sql="select * from `posts` order by `idPosts` desc;";
        List beanList = BaseDao.getForList(Post.class, sql);
        return beanList;
    }
    @Override
    public void savePost(Connection conn, Post post) {
        String sql="insert into `posts` VALUES (?,?,?,?,?,?,?)";

        BaseDao.update(conn,sql,post.getIdPosts(),post.getHandline(),post.getContent(),post.getNstop(),post.getNsvisual(),post.getIdPosts(),post.getUserIds());
    }

    @Override
    public void deletePostById(Connection conn, String postId) {
        String sql="delete from `posts` where  `idPosts` = ?";
        BaseDao.update(conn,sql,postId);
    }

    @Override
    public Post getPostById(Connection conn, String postId) {
        String sql="select * from `posts` where `idPosts` = ?";
        return BaseDao.getInstance(Post.class,sql,postId);
    }

    @Override
    public void updatePost(Connection conn, Post post) {
        String sql="update `post` set `handline`=? `content`=?  `nstop` = ? `nsvisual`=? `SectionIds`=? `UserIds`=? where `idPosts` =?";
        BaseDao.update(conn,sql,post.getHandline(),post.getContent(),post.getNstop(),post.getNsvisual(),post.getSectionIds(),post.getUserIds(),post.getIdPosts());
    }

    public static Long countnum(){
        Connection conn= JDBCUtils.getConnection();
        String sql="select count(*) from ourforum.posts;";
        Long value = BaseDao.<Long>getValue(conn, sql);
        return value;
    }
}
/*
`idPosts` varchar(20) NOT NULL,
  `handline` varchar(45) NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `time` datetime NOT NULL,
  `nstop` int NOT NULL,
  `nsvisual` int NOT NULL,
  `SectionIds` int NOT NULL DEFAULT '0',
 */