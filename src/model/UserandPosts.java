package model;

/**
 * @author lrd
 * @date 2022-08-19 下午10:19
 */
public class UserandPosts {
    private String userId;
    private String idPost;

    @Override
    public String toString() {
        return "UserandPosts{" +
                "userId='" + userId + '\'' +
                ", idPost='" + idPost + '\'' +
                '}';
    }

    public UserandPosts() {
    }

    public UserandPosts(String userId, String idPost) {
        this.userId = userId;
        this.idPost = idPost;
    }
    public UserandPosts(User user ,Post post) {
        this.userId=user.getUserId();
        this.idPost=post.getIdPost();
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
}
