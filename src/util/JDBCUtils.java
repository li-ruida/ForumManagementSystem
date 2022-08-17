package util;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * @author lrd
 * @date 2022-08-17 下午5:14
 */
public class JDBCUtils {
    //获取数据库连接
    public static Connection getConnection(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.加载配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            try {
                pros.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //2.读取配置信息
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");
            //3.加载驱动
            Class.forName(driverClass);
            //4.获取连接
            try {
                conn = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void closeResource(Connection conn, PreparedStatement ps){
        try{
            if (ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet resultSet){
        try{
            if (ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
