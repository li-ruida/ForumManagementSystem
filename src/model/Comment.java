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
    private String commentText;// 评论内容
    private Date commentTime;// 评论时间

    public Comment(String idPost, String userId, String commentText, Date commentTime, String commentID) {
        this.idPost = idPost;
        this.userId = userId;
        this.commentText = commentText;
        this.commentTime = commentTime;
        this.commentID = commentID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    private String commentID;//评论id

    // 构造方法

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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
