package dao;


import model.UserandPosts;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-19 下午10:18
 */
public interface UserPostDao {
    List<UserandPosts> getUser();//获取User类

    void saveUserandPosts(Connection conn, UserandPosts userandPosts);//插入User

    void deleteUserandPostsById(Connection conn,String userid,String idPosts);//删除User

    UserandPosts getUserandPostsById(Connection conn, String userId,String idPosts);//根据Id查

    void updateUserandPosts(Connection conn,UserandPosts userandPosts);//更新信息
}
