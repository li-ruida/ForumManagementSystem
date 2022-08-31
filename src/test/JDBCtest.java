package test;

import dao.*;
import model.Comment;
import model.Post;
import model.Sensitiveword;
import org.junit.Test;
import util.JDBCCRUD;
import util.JDBCUtils;

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
    @Test
    public void test2(){

        PostDao postDao=new PostDaoImpl();
        List<Post> post = postDao.getPost();
        for (Post p:post){
            System.out.println(p);
        }

        Post postById = postDao.getPostById(JDBCUtils.getConnection(), "000001");
        System.out.println(postById);

    }
    @Test
    public void test3(){

        CommentDao commentDao=new CommentDaoImpl();
        List<Comment> comment = commentDao.getComment();
        System.out.println(comment);

    }
}
