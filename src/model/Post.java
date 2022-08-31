package model;
import java.util.Date;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:47
 */
// 帖子类
public class Post {
    private String idPosts;// 帖子id
    private String handline;// 标题
    private String content;// 内容
    private int SectionIds;// 所属板块id 默认0
    private int nstop;// 是否置顶 置顶为1，未置顶为0
    private int nsvisual;// 是否可见 不可见为1，可见为0
    private String UserIds;// 用户id


    public Post() {
    }

    // 构造方法
    public Post(String idPost, String handline, String content, int sectionId, int nsTop, int nsVisual,String userId) {
        this.idPosts = idPost;
        this.handline = handline;
        this.content = content;

        this.SectionIds = sectionId;
        this.nstop = nsTop;
        this.nsvisual = nsVisual;
        this.UserIds = userId;
    }

    // get/set系列方法
    public int getSectionIds() {
        return SectionIds;
    }

    public void setSectionIds(int sectionIds) {
        this.SectionIds = sectionIds;
    }

    public String getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(String idPosts) {
        this.idPosts = idPosts;
    }

    public String getHandline() {
        return handline;
    }

    public void setHandline(String handline) {
        this.handline = handline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public int getNstop() {
        return nstop;
    }

    public void setNstop(int nstop) {
        this.nstop = nstop;
    }

    public int getNsvisual() {
        return nsvisual;
    }

    public void setNsvisual(int nsvisual) {
        this.nsvisual = nsvisual;
    }

    public String getUserIds() {
        return UserIds;
    }

    public void setUserIds(String userIds) {
        this.UserIds = userIds;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost='" + idPosts + '\'' +
                ", handline='" + handline + '\'' +
                ", content='" + content + '\'' +
                ", sectionId=" + SectionIds +
                ", nsTop=" + nstop +
                ", nsVisual=" + nsvisual +
                ", userId='" + UserIds + '\'' +
                '}';
    }
}
