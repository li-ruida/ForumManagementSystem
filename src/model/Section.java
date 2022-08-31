package model;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-14:56
 */
// 板块类
public class Section {
    private int SectionIds; // 板块id 0为默认板块
    private String SecName; // 板块名字
    private String UserIds;// 版主id
    // 构造方法
    public Section(int sectionId, String secName, String userId) {
        this.SectionIds = sectionId;
        this.SecName = secName;
        this.UserIds = userId;
    }

    public Section() {
    }

    // get/set系列方法
    public int getSectionIds() {
        return SectionIds;
    }

    public void setSectionIds(int sectionIds) {
        this.SectionIds = sectionIds;
    }

    public String getSecName() {
        return SecName;
    }

    public void setSecName(String secName) {
        this.SecName = secName;
    }

    public String getUserIds() {
        return UserIds;
    }

    public void setUserIds(String userIds) {
        this.UserIds = userIds;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + SectionIds +
                ", secName='" + SecName + '\'' +
                ", userId='" + UserIds + '\'' +
                '}';
    }
}
