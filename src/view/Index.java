package view;

import model.User;
import service.LoginandRegister;
import service.Server;
import util.Formatutils;

import java.util.Scanner;

/**
 * @author lrd
 * @date 2022-08-29 下午3:56
 */
public class Index {
    public static void main(String[] args) {
        boolean tobreak=false;
        User user=new User();
        Scanner sc=new Scanner(System.in);
        int menunum=0;
        System.out.println("\t欢迎来到大工论坛");
        System.out.println("********************************");
        while (true){
            menunum=0;
            System.out.println("输入1登录 输入2注册 输入3退出");
            Integer integer = Formatutils.inputInteger(sc);
            switch (integer){
                case 1:
                    user = LoginandRegister.login(sc);
                    if(user!=null){
                        Server.service(user,sc);
                    }
                    else {
                        System.out.println("账号或密码错误,请重新登录!");
                        continue;
                    }
                    break;
                case 2:
                    user=LoginandRegister.register(sc);
                    Server.service(user,sc);
                    break;
                case 3:
                    tobreak=true;
                    System.out.println("下次再见");
                    break;
                default:
                    System.out.println("输入数据错误,请重新输入");
                    continue;
            };

            if(tobreak)
                break;
        }
        sc.close();
    }
}
