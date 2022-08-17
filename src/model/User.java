package model;

/**
 * @author Joe不看月亮
 * @create 2022-08-17-9:39
 */
// 用户类
public class User {
    private String account;// 账号
    private String password;// 密码
    private String name;// 用户名
    private String userId;// 用户id
    private int rightNum;// 权限

    // 构造方法
    public User(String account, String password, String name, String userId, int rightNum) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.userId = userId;
        this.rightNum = rightNum;
    }

    public User() {
    }

    // get/set系列方法
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRightNum() {
        return rightNum;
    }

    public void setRightNum(int rightNum) {
        this.rightNum = rightNum;
    }

}
