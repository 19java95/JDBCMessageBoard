package com.imooc.jdbc.dao;


import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.common.ConnectionUtil;

import java.sql.*;

/**
 * USERDAO
 * 用户登录
 * Created by TangTian on 2018/11/11.
 */
public class UserDAO {

    public User login(String username, String password) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return user;
    }


    /**
     * 根据ID查询用户信息
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM user WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("查新用户信息失败");
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return user;
    }

    /**
     * 修改用户信息
     * */


    public boolean updateUser(User user){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE user SET username = ? ,password = ?,real_name=?,birthday =?,phone=?,address=?WHERE id=?    ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getRealName());
            stmt.setDate(4,new Date(user.getBirthday().getTime()));
            stmt.setString(5,user.getPhone());
            stmt.setString(6,user.getAddress());
            stmt.setLong(7,user.getId());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("查新用户信息失败");
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }

    /**
     * 保存用户信息
     */
    public static boolean save(User user){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "INSERT INTO user(username,password) VALUES(?,?) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getPassword());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("添加用户信息失败");
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
       return true;
    }
}
