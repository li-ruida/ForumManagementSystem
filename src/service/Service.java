package service;
import dao.*;
import model.*;
import util.IDset;
import util.JDBCCRUD;
import util.JDBCUtils;
import util.Sensitivewords;

import java.sql.Connection;
import java.util.List;

/**
 * @author Joe不看月亮
 * @create 2022-08-25-10:15
 */

public class Service {


    // 发帖 // post里面的属性已经设置好了// 用于判断是否是拉黑会员
    static boolean putPost(Post post) {

        UserDao userDao = new UserDaoImpl();
        Connection conn = JDBCUtils.getConnection();
        User user = userDao.getUserById(conn, post.getUserIds());
        if (user.getRightnum() == -1) { // -1 即为拉黑会员
            System.out.println("您为拉黑会员，没有发帖权限");
            return false;
        } else {
            // 查找敏感词
            if (Sensitivewords.SensitivewordsCheck(post.getHandline())) {
                System.out.println("标题含有敏感词，发帖失败");
                return false;// 发帖失败
            } else if (Sensitivewords.SensitivewordsCheck(post.getContent())) {
                System.out.println("内容含有敏感词，发帖失败");
                return false;// 发帖失败
            } else{
                // 帖子储存进Post
                Connection conn2 = JDBCUtils.getConnection();
                PostDao postDao= new PostDaoImpl();
                postDao.savePost(conn2,post);

                // 帖子储存进UserPost
                UserandPosts userandPosts= new UserandPosts(post.getUserIds(),post.getIdPosts());
                UserPostDao userPostDao= new UserPostDaoImpl();
                userPostDao.saveUserandPosts(conn2,userandPosts);

                System.out.println("发帖成功");
                return true;// 发帖成功

            }
        }
    }

    // 删帖
    static boolean deletePost(Post post,User user){// 要删的帖子，谁要删除这个帖子
        if((user.getRightnum() == -2) || (user.getUserIds().equals(post.getUserIds()) ) || (user.getRightnum()==post.getSectionIds())){
            // 管理员、自己、此版块版主  有权限删除帖子

            // 帖子从post中删除
            Connection conn = JDBCUtils.getConnection();
            PostDao postDao= new PostDaoImpl();
            Post postById = postDao.getPostById(conn, post.getIdPosts());
            postById.setNsvisual(1);
            postDao.updatePost(JDBCUtils.getConnection(),postById);
            System.out.println("删帖成功");
            return true;
        }else{
            System.out.println("您没有删帖权限");
            return false;
        }
    }

    // 查看帖子
    Post getPost(Post post,User user){
        if(user.getRightnum() == -1){
            System.out.println("你已被拉黑，没有查看权限!");
            return null;
        }else{
            return post;
        }
    }

    // 发表评论
    static boolean putComment(Post post, Comment comment,User user){
        // 被评论的帖子，评论，评论者
        if(user.getRightnum() != -1){
            // 查找敏感词
            if (Sensitivewords.SensitivewordsCheck(comment.getCommenttext())) {
                System.out.println("评论含有敏感词，评论失败");
                return false;// 评论失败
            }else{
                comment.setIdPosts(post.getIdPosts());
                comment.setUserIds(user.getUserIds());


                // 存入数据库
                CommentDao commentDao = new CommentDaoImpl();
                Connection conn = JDBCUtils.getConnection();
                commentDao.saveComment(conn,comment);
                System.out.println("评论发表成功！");
                return true;
            }
        }else {
            System.out.println("你已被拉黑没有评论权限！");
            return false;
        }
    }

    // 删除评论
    static boolean deleteComment(Comment comment,User user){
        // 自己，楼主，管理员，版主 能删除评论

        // 楼主
        String postId = comment.getIdPosts(); // 帖子id
        String userid = null; // 楼主id
        UserPostDao userPostDao = new UserPostDaoImpl();
        List<UserandPosts> list = userPostDao.getUser();

        for(UserandPosts o:list){
            if(o.getIdPosts() == postId){
                userid = o.getUserIds(); // 获取楼主id
                break;
            }
        }
        // 版主
        PostDao postDao = new PostDaoImpl();
        Connection conn = JDBCUtils.getConnection();
        Post post = postDao.getPostById(conn,postId);
        int sectionId = post.getSectionIds(); // 版主id = 版块id

        if((comment.getUserIds() .equals( user.getUserIds())) || (user.getUserIds() .equals(userid )) ||(user.getRightnum() == -2) || (user.getRightnum() == sectionId)){
            // 从数据库中删除评论
            Connection conn2 = JDBCUtils.getConnection();
            CommentDao commentDao = new CommentDaoImpl();

            comment.setIdPosts("000000");
            commentDao.updateComment(conn2,comment);
            System.out.println("删除评论成功!");
            return true;
        }else{
            System.out.println("没有权限，删除失败！");
            return false;
        }
    }

    // 查看评论
    Comment getComment(Comment comment,User user){
        if(user.getRightnum() == -1){
            System.out.println("您已被拉黑，没有查看权限!");
            return null;
        }else {
            return comment;
        }
    }

    // 置顶帖子
    boolean Topping(Post post,User user){
        if((user.getRightnum() == -2) || (user.getRightnum() == post.getSectionIds())){
            // 管理员或者版主有权限置顶
            post.setNstop(1); // 置顶为1，未置顶为0

            // 存入数据库
            PostDao postDao = new PostDaoImpl();
            Connection conn = JDBCUtils.getConnection();
            postDao.savePost(conn,post);
            System.out.println("置顶成功");
            return true;
        }else{
            System.out.println("没有权限，置顶失败");
            return false;
        }
    }

    //拉黑会员
    boolean blacklist(User user1,User user2){
        // user2对user1进行拉黑
        if((user2.getRightnum() == -2) ){
            user1.setRightnum(-1);

            // 存入数据库
            UserDao userDao = new UserDaoImpl();
            Connection conn = JDBCUtils.getConnection();
            userDao.saveUser(conn,user1);

            System.out.println("拉黑"+user1.getNames()+"成功!");
            return true;
        }else {
            System.out.println("没有拉黑权限，拉黑失败!");
            return false;
        }
    }

    // 设置版主
    boolean setSectioner(User user1,User user2,int num){
        // user2将user1设置为版主,num为将其设置为几号版块的版主
        if((user2.getRightnum() == -2)){
            if(user1.getRightnum() > 0){ // 已经是版主了，设置失败
                System.out.println(user1.getNames()+"已经是"+user1.getRightnum()+"号版块的版主了,设置失败，请先取消其版主");
                return false;
            }else {
                user1.setRightnum(num);

                // 存入数据库
                UserDao userDao = new UserDaoImpl();
                Connection conn = JDBCUtils.getConnection();
                userDao.saveUser(conn,user1);
                System.out.println("将" + user1.getNames() + "设置为版主成功!");
                return true;
            }
        }else {
            System.out.println("您不是管理员，没有设置版主权利!");
            return false;
        }
    }

    // 删除版主
    boolean deleteSectioner(User user1,User user2){
        // user2将user1版主删除
        if((user2.getRightnum() == -2)){
            if(user1.getRightnum() > 0){
                user1.setRightnum(0); // 删除版主，默认将其设置为普通会员 即RightNum=0
                // 存入数据库
                UserDao userDao = new UserDaoImpl();
                Connection conn = JDBCUtils.getConnection();
                userDao.saveUser(conn,user1);
                System.out.println("将" + user1.getNames() + "删除版主成功!");
                return true;
            }else {
                System.out.println(user1.getNames()+"不是版主,删除失败！");
                return false;
            }
        }else{
            System.out.println("您不是管理员，没有设置版主权利!");
            return false;
        }
    }

    // 增加版块
    boolean insertSection(Section section,User user1,User user2,int num){
        // 增加版块，并且user2将user1设置为版主，num为初始化sectionId
        if(user2.getRightnum() == -2){

            section.setUserIds(user1.getUserIds()); // 设置版主
            section.setSectionIds(num); // 设置版块id

            // 写入数据库
            Connection conn = JDBCUtils.getConnection();
            SectionDao sectionDao = new SectionDaoImpl();
            sectionDao.saveSection(conn,section);
            System.out.println("增加版块成功!");
            return true;

        }else{
            System.out.println("您不是管理员，没有增加版块权限!");
            return false;
        }
    }

    // 删除版块
    boolean deleteSection(Section section,User user){
        if(user.getRightnum() == -2){
            Connection conn = JDBCUtils.getConnection();
            SectionDao sectionDao = new SectionDaoImpl();
            Object flag =  sectionDao.getSectionById(conn,section.getSectionIds());
            if(flag == null){ // 该版块是否存在
                System.out.println("该版块不存在，删除失败!");
                return false;
            }else{
                sectionDao.deleteSectionById(conn,section.getSectionIds());// 删除版块
                // 将对应版主也删除
                UserDao userDao = new UserDaoImpl();
                deleteSectioner(userDao.getUserById(conn,section.getUserIds()),user);

                System.out.println("删除版块成功!");
                return true;
            }
        }else {
            System.out.println("您不是管理员，没有删除版块权限!");
            return false;
        }
    }

    // 增加敏感词
    static boolean insertSensitiveWord(Sensitiveword sensitiveword, User user){
        if(user.getRightnum() != -2){
            // 非管理
            System.out.println("您不是管理员，没有增加敏感词权限!");
            return false;
        }else{
            // 敏感词是否已经存在于库中
            SensitivewordsDao sensitivewordsDao = new SensitivewordsDaoImpl();
            List<Sensitiveword> list = sensitivewordsDao.getComment();
            if(list.contains(sensitiveword)){
                System.out.println("敏感词已经存在,增加失败！");
                return false;
            }else{
                // 储存入库
                Connection conn = JDBCUtils.getConnection();
                sensitivewordsDao.saveComment(conn,sensitiveword);

                return true;
            }
        }
    }

    // 删除敏感词
    static boolean deleteSensitiveWord(Sensitiveword sensitiveword, User user){
        if(user.getRightnum() != -2){
            // 非管理
            System.out.println("您不是管理员，没有删除敏感词权限!");
            return false;
        }else{
            SensitivewordsDao sensitivewordsDao = new SensitivewordsDaoImpl();
            List<Sensitiveword> list = sensitivewordsDao.getComment();
            if(list.contains(sensitiveword)){// 敏感词是否已经存在于库中
                Connection conn = JDBCUtils.getConnection();
                sensitivewordsDao.deleteCommentById(conn,sensitiveword.getSstiveword());
                System.out.println("删除成功！");
                return true;
            }else{
                System.out.println("敏感词不存在,删除失败！");
                return false;
            }
        }
    }
}
