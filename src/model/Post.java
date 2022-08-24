package model;
import java.util.Date;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:47
 */
// 帖子类
public class Post {
    private String idPost;// 帖子id
    private String handline;// 标题
    private String content;// 内容
    private Date time;// 发帖时间
    private int sectionId;// 所属板块id 默认0
    private int nsTop;// 是否置顶 置顶为1，未置顶为0
    private int nsVisual;// 是否可见 不可见为1，可见为0
    private String userId;// 用户id

    // 构造方法
    public Post(String idPost, String handline, String content, Date time, int sectionId, int nsTop, int nsVisual,String userId) {
        this.idPost = idPost;
        this.handline = handline;
        this.content = content;
        this.time = time;
        this.sectionId = sectionId;
        this.nsTop = nsTop;
        this.nsVisual = nsVisual;
        this.userId = userId;
    }

    // get/set系列方法
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getNsTop() {
        return nsTop;
    }

    public void setNsTop(int nsTop) {
        this.nsTop = nsTop;
    }

    public int getNsVisual() {
        return nsVisual;
    }

    public void setNsVisual(int nsVisual) {
        this.nsVisual = nsVisual;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost='" + idPost + '\'' +
                ", handline='" + handline + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", sectionId=" + sectionId +
                ", nsTop=" + nsTop +
                ", nsVisual=" + nsVisual +
                ", userId='" + userId + '\'' +
                '}';
    }
}
