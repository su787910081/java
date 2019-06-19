package cn.tedu

import java.sql.DriverManager
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

// 需要导入mysql 驱动包: mysql-connector-java-5.1.34-bin.jar
object Driver {
    def main(args: Array[String]): Unit = {
        var conn: Connection = null
        var ps: PreparedStatement = null
        var rs: ResultSet = null
        try {
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///stest", "root", "root")
            //3.获取传输器对象
            ps = conn.prepareStatement("select * from tabx1 where id >= ?")
            ps.setInt(1, 2);
            //4.传输sql执行获取结果集
            rs = ps.executeQuery();
            //5.遍历结果集
            while (rs.next()) {
                val name = rs.getString("name");
                println(name)
            }
        } catch {
            case t: Throwable => t.printStackTrace()
        } finally {
            //6.关闭资源
            if (rs != null) {
                try {
                rs.close();
                } catch {
                case t: Throwable => t.printStackTrace() // TODO: handle error
                } finally {
                rs = null;
                }
            }
            if (ps != null) {
                try {
                ps.close();
                } catch {
                case t: Throwable => t.printStackTrace() // TODO: handle error
                } finally {
                ps = null;
                }
            }
            if (conn != null) {
                try {
                conn.close();
                } catch {
                case t: Throwable => t.printStackTrace() // TODO: handle error
                } finally {
                conn = null;
                }
            }
        }
    }
}