package model;

/**
 * @author lrd
 * @date 2022-08-19 下午10:19
 */
public class UserandPosts {
    private String UserIds;
    private String IdPosts;

    @Override
    public String toString() {
        return "UserandPosts{" +
                "userId='" + UserIds + '\'' +
                ", idPost='" + IdPosts + '\'' +
                '}';
    }

    public UserandPosts() {
    }

    public UserandPosts(String userId, String idPost) {
        this.UserIds = userId;
        this.IdPosts = idPost;
    }
    public UserandPosts(User user ,Post post) {
        this.UserIds =user.getUserIds();
        this.IdPosts =post.getIdPosts();
    }
    public String getUserIds() {
        return UserIds;
    }

    public void setUserIds(String userIds) {
        this.UserIds = userIds;
    }

    public String getIdPosts() {
        return IdPosts;
    }

    public void setIdPosts(String idPosts) {
        this.IdPosts = idPosts;
    }
}
