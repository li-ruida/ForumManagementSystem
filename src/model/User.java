package model;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:39
 */
// 用户类
public class User {
    private String Passwords;// 密码
    private String Names;// 用户名
    private String UserIds;// 用户id
    private int Rightnum;// 权限

    // 构造方法
    public User( String password, String name, String userId, int rightNum) {
        this.Passwords = password;
        this.Names = name;
        this.UserIds = userId;
        this.Rightnum = rightNum;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "  password='" + Passwords + '\'' +
                ", name='" + Names + '\'' +
                ", userId='" + UserIds + '\'' +
                ", rightNum=" + Rightnum +
                '}';
    }

    // get/set系列方法

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        this.Passwords = passwords;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        this.Names = names;
    }

    public String getUserIds() {
        return UserIds;
    }

    public void setUserIds(String userIds) {
        this.UserIds = userIds;
    }

    public int getRightnum() {
        return Rightnum;
    }

    public void setRightnum(int rightnum) {
        this.Rightnum = rightnum;
    }

}
