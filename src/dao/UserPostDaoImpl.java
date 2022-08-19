package dao;

import model.User;
import model.UserandPosts;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-19 下午10:25
 */
/*
CREATE TABLE `userandposts` (
  `UserIds` varchar(16) NOT NULL,
  `IdPosts` varchar(20) NOT NULL,
  PRIMARY KEY (`UserIds`,`IdPosts`),
)
 */
public class UserPostDaoImpl extends  BaseDao<UserandPosts> implements UserPostDao{
    @Override
    public List<UserandPosts> getUser() {
        String sql="select * from `userandposts`";
        List beanList = BaseDao.getForList(UserandPosts.class, sql);
        return beanList;
    }

    @Override
    public void saveUserandPosts(Connection conn, UserandPosts userandPosts) {
        String sql="insert into `userandposts` VALUES (?,?)";
        BaseDao.update(conn,sql,userandPosts.getUserId(),userandPosts.getIdPost());
    }

    @Override
    public void deleteUserandPostsById(Connection conn, String userid, String idPosts) {
        String sql="delete from `userandposts` where `UserIds` = ? `IdPosts`=? ";
        BaseDao.update(conn,sql,userid,idPosts);
    }

    @Override
    public UserandPosts getUserandPostsById(Connection conn, String userId, String idPosts) {
        String sql="select from `userandposts` where `UserIds` = ? `IdPosts`=?";
        return BaseDao.getInstance(UserandPosts.class,sql,userId,idPosts);
    }

    @Override
    public void updateUserandPosts(Connection conn, UserandPosts userandPosts) {
        String sql="update `userandposts` set `UserIds` = ? `IdPosts`=? where `UserIds` = ? `IdPosts`=?";
        BaseDao.update(conn,sql,userandPosts.getUserId(),userandPosts.getIdPost(),userandPosts.getUserId(),userandPosts.getIdPost());
    }
}
