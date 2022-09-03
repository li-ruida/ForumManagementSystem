package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensitiveword that = (Sensitiveword) o;
        return Objects.equals(Sstiveword, that.Sstiveword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Sstiveword);
    }
}
