package model;

/**
 * @author lrd
 * @date 2022-08-17 下午6:53
 */
public class Sensitiveword {
    public String Sstiveword;//敏感词只能删除和增加,不能修改

    public Sensitiveword(String sstiveword) {
        Sstiveword = sstiveword;
    }

    public Sensitiveword() {
    }

    public String getSstiveword() {
        return Sstiveword;
    }

    public void setSstiveword(String sstiveword) {
        Sstiveword = sstiveword;
    }

    @Override
    public String toString() {
        return "Sensitiveword{" +
                "Sstiveword='" + Sstiveword + '\'' +
                '}';
    }
}
