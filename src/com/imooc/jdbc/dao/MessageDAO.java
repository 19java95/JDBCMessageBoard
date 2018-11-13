package com.imooc.jdbc.dao;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.bean.PersonalMessage;
import com.imooc.jdbc.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * 消息DAO
 * Created by TangTian on 2018/11/10.
 */
public class MessageDAO {

    /**
     * 分页查看全部留言
     *
     * @param page     当前页码
     * @param pageSize 每页记录数
     * @return
     */

    public List<Message> getMessages(int page, int pageSize) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message order by create_time desc limit ?, ?";//limit m, n：从第m条开始，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<Message>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return messages;
    }

    /**
     * 计算所有留言数量
     *
     * @return
     */
    public int countMessages() {

        Connection conn = ConnectionUtil.getConnection();
        String sql = "select count(*) total from message";//limit m,n从m条开始 ，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return 0;
    }

    /**
     * 保存留言
     */
    public boolean save(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "INSERT INTO message(user_id,username,title,content,create_time)VALUES(?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,message.getUserId());
            stmt.setString(2,message.getUsername());
            stmt.setString(3,message.getTitle());
            stmt.setString(4,message.getContent());
            stmt.setTimestamp(5,new Timestamp(message.getCreateTime().getTime()));
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }

    /**
     * 查看个人留言
     */


    public List<PersonalMessage> getPmessages(String username) {

        Connection conn = ConnectionUtil.getConnection();
        String sql = "SELECT m.id,m.user_id,m.username,m.title,m.content,m.create_time FROM message AS m INNER JOIN user AS u ON m.user_id=u.id WHERE u.username=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PersonalMessage> pmessages = new ArrayList<PersonalMessage>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pmessages.add(new PersonalMessage(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return pmessages;
    }
}
