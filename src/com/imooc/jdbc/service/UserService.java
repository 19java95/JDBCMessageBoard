package com.imooc.jdbc.service;
import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.dao.UserDAO;


/**
 * UserSevice
 * Created by TangTian on 2018/11/11.
 */
public class UserService {

    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public User login(String username, String password){
        return userDAO.login(username,password);
    }

    /**
     * 获取ID查询用户信息
     */

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }
    /**
     * 修改用户信息
     */
    public  boolean updateUser(User user){
        return userDAO.updateUser(user);
    }
    /***
     * 添加用户信息
     *
     */
    public boolean addUser(User user){

        return UserDAO.save(user);
    }
}
