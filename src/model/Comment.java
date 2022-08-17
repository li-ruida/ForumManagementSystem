package model;
import java.util.Date;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:56
 */
// 评论类
public class Comment {
    private String idPost;// 帖子id
    private String userId;// 用户id
    private String commentTest;// 评论内容
    private Date commentTime;// 评论时间

    // 构造方法
    public Comment(String idPost, String userId, String commentTest, Date commentTime) {
        this.idPost = idPost;
        this.userId = userId;
        this.commentTest = commentTest;
        this.commentTime = commentTime;
    }

    public Comment() {
    }

    // get/set 系列方法
    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentTest() {
        return commentTest;
    }

    public void setCommentTest(String commentTest) {
        this.commentTest = commentTest;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
