package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.Formatutils;
import util.JDBCUtils;

import java.util.Scanner;

/**
 * @author lrd
 * @date 2022-08-29 下午4:13
 */
public class LoginandRegister {
    public static User login(Scanner sc){
        User temp=null;
        System.out.println("请输入账号");
        String ac= Formatutils.inputString(sc);
        System.out.println("请输入密码");
        String password = Formatutils.inputString(sc);
        UserDao userDao=new UserDaoImpl();
        temp = userDao.getUserById(JDBCUtils.getConnection(), ac);
        if(temp!=null&& temp.getPasswords().equals(password)){
            System.out.println("登录成功");
            return temp;
        }
        return null;
    }
    public static User register (Scanner sc){
        User temp=new User();
        UserDao userDao=new UserDaoImpl();
        System.out.println("请输入用户名");
        String name= Formatutils.inputString(sc);
        temp.setNames(name);
        System.out.println("请输入密码");
        String password = Formatutils.inputString(sc);
        temp.setPasswords(password);
        Long id=UserDaoImpl.countnum()+100000;
        temp.setUserIds(id+"");
        temp.setRightnum(0);

        userDao.saveUser(JDBCUtils.getConnection(),temp);
        System.out.println("注册成功"+ "您的账号是"+temp.getUserIds());

        return temp;
    }
}
