package model;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-14:56
 */
// 板块类
public class Section {
    private int sectionId; // 板块id 0为默认板块
    private String secName; // 板块名字
    private String userId;// 版主id

    // 构造方法
    public Section(int sectionId, String secName, String userId) {
        this.sectionId = sectionId;
        this.secName = secName;
        this.userId = userId;
    }

    // get/set系列方法
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", secName='" + secName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
