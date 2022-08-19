package dao;

import model.Comment;
import model.Post;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午3:44
 */
public class PostDaoImpl extends BaseDao<Post> implements PostDao{
    @Override
    public List<Post> getPost() {
        String sql="select * from `posts`";
        List beanList = BaseDao.getForList(Post.class, sql);
        return beanList;
    }
    @Override
    public void savePost(Connection conn, Post post) {
        String sql="insert into `posts` VALUES (?,?,?,?,?,?.?)";
        BaseDao.update(conn,sql,post.getIdPost(),post.getHandline(),post.getContent(),post.getTime(),post.getNsTop(),post.getNsVisual(),post.getIdPost());
    }

    @Override
    public void deletePostById(Connection conn, String postId) {
        String sql="delete from `posts` where  `idPosts` = ?";
        BaseDao.update(conn,sql,postId);
    }

    @Override
    public Post getPostById(Connection conn, String postId) {
        String sql="select from `posts` where `idPosts` = ?";
        return BaseDao.getInstance(Post.class,sql,postId);
    }

    @Override
    public void updatePost(Connection conn, Post post) {
        String sql="update `post` set `handline`=? `content`=? `time`=? `nstop` = ? `nsvisual`=? `SectionIds`=? where `idPosts` =?";
        BaseDao.update(conn,sql,post.getHandline(),post.getContent(),post.getTime(),post.getNsTop(),post.getNsVisual(),post.getSectionId(),post.getIdPost());
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