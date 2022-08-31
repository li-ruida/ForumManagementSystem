package dao;

import model.User;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 上午11:09
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{
    @Override
    public List<User> getUser() {
        String sql="select * from `users`";
        List beanList = BaseDao.getForList(User.class, sql);
        return beanList;
    }

    @Override
    public void saveUser(Connection conn, User user) {
        String sql="insert into `users` VALUES (?,?,?,?)";
        BaseDao.update(conn,sql,user.getPasswords(),user.getNames(),user.getUserIds(),user.getRightnum());
    }

    @Override
    public void deleteUserById(Connection conn, String userid) {
        String sql="delete from `users` where `UserIds` = ?";
        BaseDao.update(conn,sql,userid);
    }

    @Override
    public User getUserById(Connection conn, String userId) {
        String sql="select * from `users` where `UserIds` = ?";
        return BaseDao.getInstance(User.class,sql,userId);
    }

    @Override
    public void updateUser(Connection conn, User user) {
        String sql="update `users` set  `Passwords`=? ,`Names`=?,`Rightnum`=? where `UserIds` = ?";
        BaseDao.update(conn,sql,user.getPasswords(),user.getNames(),user.getRightnum(),user.getUserIds());
    }
    public static Long countnum(){
        Connection conn= JDBCUtils.getConnection();
        String sql="select count(*) from ourforum.users;";
        Long value = BaseDao.<Long>getValue(conn, sql);
        return value;
    }
}
