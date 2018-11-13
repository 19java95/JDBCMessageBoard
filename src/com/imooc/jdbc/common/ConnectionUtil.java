package com.imooc.jdbc.common;

import java.sql.*;

/**
 * 操作数据库的公共类
 * Created by TangTian on 2018/11/10.
 */
public final class ConnectionUtil {
    private static String url = "jdbc:mysql://localhost:3306/king";
    private static String user = "root";
    private static String password = "root";

    private ConnectionUtil() {}

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("找不到驱动程序类，加载驱动失败");
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
          return  DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("创建数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放数据库资源
     */

    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                            }
                        }
                    }
                }
            }
        }


    }

}
