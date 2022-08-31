package model;
import java.util.Date;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:56
 */
// 评论类
public class Comment {
    private String idPosts;// 帖子id
    private String UserIds;// 用户id
    private String commenttext;// 评论内容




    public Comment() {
    }

    public Comment(String idPost, String userId, String commentText, String commentID) {
        this.idPosts = idPost;
        this.UserIds = userId;
        this.commenttext = commentText;
        this.commentId = commentID;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    private String commentId;//评论id

    // 构造方法

    // get/set 系列方法
    public String getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(String idPosts) {
        this.idPosts = idPosts;
    }

    public String getUserIds() {
        return UserIds;
    }

    public void setUserIds(String userIds) {
        this.UserIds = userIds;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "idPost='" + idPosts + '\'' +
                ", userId='" + UserIds + '\'' +
                ", commentText='" + commenttext + '\'' +
                ", commentID='" + commentId + '\'' +
                '}';
    }
}
