package service;

import com.mysql.cj.exceptions.DeadlockTimeoutRollbackMarker;
import dao.*;
import model.*;
import util.Formatutils;
import util.IDset;
import util.JDBCCRUD;
import util.JDBCUtils;

import java.util.List;
import java.util.Scanner;

import static service.Service.*;

/**
 * @author lrd
 * @date 2022-08-29 下午4:43
 */
public class Server {
    public static void service(User user, Scanner sc){
        System.out.println("你好,"+user.getNames());
        System.out.println("\t欢迎来到大工论坛");
        System.out.println("********************************");
        int rightnum = user.getRightnum();
        switch (rightnum){
            case -1:
                System.out.println("你已被拉黑!");
                return;
            case -2:
                admin(user,sc);
                return;
            case 0:
                System.out.println("欢迎回来"+user.getNames());
                theuser(user,sc);
                return;
            case -3:
                System.out.println("该账号已注销");
                return;
            default:
                System.out.println("欢迎回来"+user.getNames());
                theuser(user,sc);
                return;
        }
    }
    public static void theuser(User user, Scanner sc){
        while (true){
            System.out.println("输入1查看帖子,输入3退出");
            System.out.println("********************************");
            int temp= Formatutils.inputInteger(sc);
            switch (temp){
                case 3:
                    return;
                case 1:
                    adminpost(user,sc);
                    break;
                default:
                    System.out.println("输入数据异常");

            }
        }

    }
    public static void admin(User user, Scanner sc){
        System.out.println("欢迎回来,管理员!");
        while (true){
            System.out.println("输入1查看或发布帖子,输入2进入管理系统,输入3退出");
            System.out.println("********************************");
            int temp= Formatutils.inputInteger(sc);
            switch (temp){
                case 2:
                    admincontrol(user,sc);
                    break;
                case 1:
                    adminpost(user,sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("输入数据异常");

            }
        }

    }
    public static void adminpost(User user,Scanner sc){
        System.out.println("输入1查看帖子,输入2发帖,输入3查看板块,输入4退出");
        System.out.println("********************************");
        int temp;
        int temp1=Formatutils.inputInteger(sc);
        PostDao postDao=new PostDaoImpl();
        UserDao userDao=new UserDaoImpl();
        switch (temp1){
            case 1:
                List<Post> post = postDao.getPost();
                for (Post p:post){
                    if(user.getRightnum()==-2||p.getNsvisual()==0){
                        System.out.println("标题:\t"+p.getHandline());
                        System.out.println("作者:\t--"+userDao.getUserById(JDBCUtils.getConnection(),p.getUserIds()).getNames());
                        System.out.println(p.getContent());
                        int state = postcontrol(user, sc, p);
                        switch (state){
                            case 1:
                                break;
                            case 5:
                                return;
                            default:
                                System.out.println("数据异常");
                        }
                    }
                }
                System.out.println("没有下一条了!");
                break;
            case 4:
                return;
            case 2:
                Post post1=new Post();
                System.out.println("输入标题");
                String str=Formatutils.inputString(sc);
                post1.setHandline(str);
                System.out.println("输入内容");
                String str1=Formatutils.inputString(sc);
                post1.setContent(str1);
                System.out.println("请输入板块ID");
                SectionDao sectionDao=new SectionDaoImpl();
                List<Section> section = sectionDao.getSection();
                for (Section sec: section) {
                    System.out.println(sec.getSecName()+"--->"+sec.getSectionIds());
                }
                temp=Formatutils.inputInteger(sc);
                boolean hassec=false;
                for (Section sec: section) {
                    if(sec.getSectionIds()==temp){
                        hassec=true;
                    }
                }
                if(hassec){
                    post1.setSectionIds(temp);
                    post1.setUserIds(user.getUserIds());
                    post1.setNstop(0);
                    post1.setNsvisual(0);
                    Long countnum = PostDaoImpl.countnum();
                    long l = IDset.count1(countnum+1);
                    str=(countnum+1)+"";
                    for(long i=6-l;i>0;i--){
                        str="0"+str;
                    }
                    post1.setIdPosts(str);
                    putPost(post1);
                }
                else {
                    System.out.println("板块ID不存在,创建失败!");
                }
                break;
            case 3:
                SectionDao sectionDao1=new SectionDaoImpl();
                List<Section> section1 = sectionDao1.getSection();
                for (Section sec: section1) {
                    System.out.println(sec.getSecName()+"--->"+sec.getSectionIds());
                }
                System.out.println("请输入版块ID");
                temp=Formatutils.inputInteger(sc);
                String sql="select * from `posts` where `SectionIds`=? order by `idPosts` desc;";
                List<Post> forList = JDBCCRUD.getForList(Post.class, sql, temp);
                if(forList!=null){
                    List<Post> post2 = postDao.getPost();
                    for (Post p:post2){
                        if(user.getRightnum()==-2||p.getNsvisual()==0){
                            System.out.println("标题:\t"+p.getHandline());
                            System.out.println("作者:\t--"+userDao.getUserById(JDBCUtils.getConnection(),p.getUserIds()).getNames());
                            System.out.println(p.getContent());
                            int state = postcontrol(user, sc, p);
                            switch (state){
                                case 1:
                                    break;
                                case 5:
                                    return;
                                default:
                                    System.out.println("数据异常");
                            }
                        }
                    }
                }
                else{
                    System.out.println("版块不存在或版块为空");
                }
                break;
            default:
                System.out.println("输入数据异常");
        }
    }
    public static int postcontrol(User user,Scanner sc,Post post){
        int state=0;
        while (true){
            System.out.println("输入1下一条,输入2删帖,输入3查看评论,输入4发表或删除评论,输入5退出");
            int temp1=Formatutils.inputInteger(sc);
            switch (temp1){
                case 1:
                    return 1;
                case 5:
                    return 5;
                case 2:
                    boolean b = deletePost(post, user);
                    break;
                case 3:
                    CommentDao commentDao=new CommentDaoImpl();
                    String sql="select * from `comment` where `idPosts` = ?;";
                    List<Comment> commentList = JDBCCRUD.getForList(Comment.class, sql, post.getIdPosts());
                    for (Comment comment:commentList){
                        System.out.println(comment.getUserIds()+": "+comment.getCommenttext()+"---CommentID:"+comment.getCommentId());
                    }
                    break;
                case 4:
                    commentcontrol(user,sc,post);
                    break;
                default:
                    System.out.println("输入数据异常");
                        break;
            }
        }
    }
    public static void commentcontrol(User user,Scanner sc,Post post){
        int state=0;
        String str;
        while (true){
            CommentDao commentDao=new CommentDaoImpl();
            String sql="select * from `comment` where `idPosts` = ?";
            List<Comment> commentList = JDBCCRUD.getForList(Comment.class, sql, post.getIdPosts());
            for (Comment comment:commentList){
                System.out.println(comment.getUserIds()+": "+comment.getCommenttext()+"---CommentID:"+comment.getCommentId());
            }
            System.out.println("输入1发表评论,输入2删除评论,输入3退出");
            Comment comment1=null;
            int temp1=Formatutils.inputInteger(sc);
            int temp;
            switch (temp1){
                case 1:
                    comment1=new Comment();
                    System.out.println("输入评论内容");
                    str=Formatutils.inputString(sc);
                    comment1.setCommenttext(str);
                    comment1.setUserIds(user.getUserIds());
                    CommentDaoImpl commentDaoimpl=new CommentDaoImpl();
                    Long countnum = commentDaoimpl.countnum();
                    long l = IDset.count1(countnum+1);
                    str=(countnum+1)+"";
                    for(long i=6-l;i>0;i--){
                        str="0"+str;
                    }
                    comment1.setCommentId(str);
                    putComment(post, comment1,user);
                    break;
                case 2:
                    System.out.println("输入要删除的评论的ID");
                    comment1=null;
                    String str1=Formatutils.inputString(sc);
                    comment1=commentDao.getCommentById(JDBCUtils.getConnection(), str1);
                    if(comment1==null){
                        System.out.println("评论不存在");
                    }
                    else {
                        deleteComment(comment1,user);
                    }
                    return;
                case 3:
                    return;
                default:
                    System.out.println("数据异常");
            }

        }

    }
    public static void admincontrol(User user, Scanner sc){
        while (true){
            System.out.println("输入1成员管理,输入2敏感词管理,输入3版块管理,输入4返回上级");
            System.out.println("****************************************************************");
            int temp= Formatutils.inputInteger(sc);
            switch (temp){
                case 1:
                    admincontrol1(user,sc);
                    break;
                case 2:
                    admincontrol2(user,sc);
                    break;
                case 3:
                    admincontrol3(user,sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("输入数据异常,请重新输入");

            }
        }

    }
    public static void admincontrol1(User user, Scanner sc) {// 管理员 成员管理
        System.out.println("输入1拉黑成员,输入2解禁成员,输入3设置版主,输入4撤销版主,输入5增加管理员,输入6撤销管理员,输入7退出");
        System.out.println("************************************************************************************************");
        int temp1= Formatutils.inputInteger(sc);
        int temp;
        switch (temp1){
            case 1:
                System.out.println("输入要拉黑的成员的ID");
                UserDao userDao=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById = userDao.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById!=null){
                    userById.setRightnum(-1);
                    userDao.updateUser(JDBCUtils.getConnection(),userById);
                    System.out.println("拉黑成功");
                }
                else {
                    System.out.println("用户不存在");
                }
                return;
            case 2:
                System.out.println("输入要解禁的成员的ID");
                UserDao userDao1=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById1 = userDao1.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById1!=null){
                    userById1.setRightnum(0);
                    userDao1.updateUser(JDBCUtils.getConnection(),userById1);
                    System.out.println("解禁成功");
                }
                else {
                    System.out.println("用户不存在");
                }
                return;
            case 3:
                System.out.println("输入要设置为版主的成员的ID");
                UserDao userDao2=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById2 = userDao2.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById2!=null){
                    System.out.println("输入要设置版块的ID");
                    temp= Formatutils.inputInteger(sc);
                    SectionDao sectionDao=new SectionDaoImpl();
                    Section sectionById = sectionDao.getSectionById(JDBCUtils.getConnection(), temp);
                    if(sectionById!=null){
                        sectionById.setUserIds(userById2.getUserIds());
                        userById2.setRightnum(sectionById.getSectionIds());
                        userDao2.updateUser(JDBCUtils.getConnection(),userById2);
                        sectionDao.updateSection(JDBCUtils.getConnection(),sectionById);
                        System.out.println("设置成功");
                    }
                    else {
                        System.out.println("板块不存在");
                    }
                }
                else {
                    System.out.println("用户不存在");
                }
                return;
            case 4:
                System.out.println("输入要撤销版主的成员的ID");
                UserDao userDao3=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById3 = userDao3.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById3!=null){
                    System.out.println("输入要设置版块的ID");
                    temp= Formatutils.inputInteger(sc);
                    SectionDao sectionDao=new SectionDaoImpl();
                    Section sectionById = sectionDao.getSectionById(JDBCUtils.getConnection(), temp);
                    if(sectionById!=null){
                        sectionById.setUserIds("000000");
                        userById3.setRightnum(0);
                        userDao3.updateUser(JDBCUtils.getConnection(),userById3);
                        sectionDao.updateSection(JDBCUtils.getConnection(),sectionById);
                        System.out.println("设置成功");
                    }
                    else {
                        System.out.println("板块不存在");
                    }
                }
                else {
                    System.out.println("用户不存在");
                }
                return;
            case 5:
                System.out.println("输入要设置为管理员的成员ID");
                UserDao userDao4=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById4 = userDao4.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById4!=null){
                    userById4.setRightnum(-2);
                    userDao4.updateUser(JDBCUtils.getConnection(),userById4);
                    System.out.println("设置成功");
                }
                else {
                    System.out.println("用户不存在");
                }
                return;
            case 6:
                System.out.println("输入要撤销管理员的成员ID");
                UserDao userDao5=new UserDaoImpl();
                temp= Formatutils.inputInteger(sc);
                User userById5 = userDao5.getUserById(JDBCUtils.getConnection(), String.valueOf(temp));
                if(userById5!=null&&!(userById5.getUserIds().equals("000000"))){
                    userById5.setRightnum(0);
                    userDao5.updateUser(JDBCUtils.getConnection(),userById5);
                    System.out.println("撤销成功");
                }
                else {
                    System.out.println("用户不存在或权利不足");
                }
                return;
            case 7:
                break;
            default:
                System.out.println("数据异常");
        }
    }
    public static void admincontrol2(User user, Scanner sc) {// 管理员 敏感词管理
        System.out.println("输入1设置敏感词,输入2删除敏感词,输入3退出");
        System.out.println("******************************");
        int temp1=Formatutils.inputInteger(sc);
        Sensitiveword sensitiveword=new Sensitiveword();
        String str;
        switch (temp1){
            case 1:
                System.out.println("输入敏感词");
                str=Formatutils.inputString(sc);
                sensitiveword.setSstiveword(str);
                boolean b = Service.insertSensitiveWord(sensitiveword, user);
                if(b)
                {
                    System.out.println("敏感词增加成功！");
                }
                else {
                    System.out.println("增加失败");
                }
                break;
            case 2:
                System.out.println("输入敏感词");
                str=Formatutils.inputString(sc);
                sensitiveword.setSstiveword(str);
                boolean b1 = Service.deleteSensitiveWord(sensitiveword, user);
                if(b1)
                {
                    System.out.println("敏感词删除成功！");
                }
                else {
                    System.out.println("删除失败");
                }
                break;
            case 3:break;
            default:
                System.out.println("数据异常");
        }
    }
    public static void admincontrol3(User user, Scanner sc) {// 管理员 板块管理
        System.out.println("输入1设置板块,输入2删除板块,输入3退出");
        System.out.println("******************************");
        int temp1=Formatutils.inputInteger(sc);
        int temp;
        String str;
        switch (temp1){
            case 1:
                System.out.println("输入板块名称");
                str=Formatutils.inputString(sc);
                SectionDao sectionDao=new SectionDaoImpl();
                SectionDaoImpl sectionDaoimpl=new SectionDaoImpl();
                Long countnum = sectionDaoimpl.countnum();
                String t=countnum+"";
                Integer te=Integer.parseInt(t);
                Section section=new Section(te+1,str,"000000");
                sectionDao.saveSection(JDBCUtils.getConnection(),section);
                break;
            case 2:
                System.out.println("输入板块Id");
                temp=Formatutils.inputInteger(sc);
                SectionDao sectiondao1=new SectionDaoImpl();
                Section section1=sectiondao1.getSectionById(JDBCUtils.getConnection(),temp1);
                if(section1!=null&&section1.getSectionIds()!=1){
                    PostDao postDao=new PostDaoImpl();
                    List<Post> post = postDao.getPost();
                    for (Post p:post) {
                        if(p.getSectionIds()==temp){
                            p.setSectionIds(1);
                            postDao.updatePost(JDBCUtils.getConnection(),p);
                        }
                    }
                    sectiondao1.deleteSectionById(JDBCUtils.getConnection(),temp);
                }
                break;
            case 3:break;
            default:
                System.out.println("数据异常");
        }
    }


}
