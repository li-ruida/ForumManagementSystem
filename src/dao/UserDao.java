package dao;

import model.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 上午10:55
 */
public interface UserDao {
    List<User> getUser();//获取User类
    void saveUser(Connection conn,User user);//插入User
    void deleteUserById(Connection conn,String userid);//删除User
    User getUserById(Connection conn,String userId);//根据Id查

    void updateUser(Connection conn,User user);//更新信息

}
