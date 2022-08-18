package dao;

import model.Comment;
import model.Post;
import model.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 上午10:55
 */
public interface PostDao {
    List<Post> getPost();//获取Comment类

    void savePost(Connection conn,Post post);//插入Comment

    void deletePostById(Connection conn,String postId);//删除Comment

    Post getPostById(Connection conn, String postId);//根据Id查

    void updatePost(Connection conn,Post post);//更新信息
}
