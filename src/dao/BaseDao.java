package dao;

import util.JDBCCRUD;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-17 下午7:07
 */
public abstract class BaseDao<T> {

    // 定义一个变量来接收泛型的类型
    private Class<T> type;
    // 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
    public BaseDao() {
        // 获取子类的类型
        Class clazz = this.getClass();
        // 获取父类的类型
        // getGenericSuperclass()用来获取当前类的父类的类型
        // ParameterizedType表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
        // 这个方法会返回一个Type的数组
        Type[] types = parameterizedType.getActualTypeArguments();
        // 获取具体的泛型的类型·
        this.type = (Class<T>) types[0];
    }
    /**
     * 通用的增删改操作
     */
    public void update(Connection conn,String sql, Object... params) {
        JDBCCRUD.update(conn, sql, params);
    }
    /**
     * 获取一个对象
     */
    public T getBean(Connection conn,String sql, Object... params) {
        T t = null;
        t = (T) JDBCCRUD.getInstance(t.getClass(), sql, params);
        return t;
    }
    /**
     * 获取所有对象
     */
    public List<T> getBeanList(Connection conn,String sql, Object... params) {
        List<T> list = null;
        list = (List<T>) JDBCCRUD.getForList(type.getClass(),sql, params);
        return list;
    }
    /**
     * 获取一个值 如count(*)
     */
    public Object getValue(Connection conn,String sql, Object... params) {
        Object count = null;
        count = JDBCCRUD.getValue(conn, sql, params);
        return count;
    }
}