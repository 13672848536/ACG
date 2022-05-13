package com.example.xjlacgapp.service;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.UserDTO;
import com.example.xjlacgapp.model.UserInfoDTO;
import com.example.xjlacgapp.pojo.User;

import java.util.List;

public interface UserService {
     /**
      * 用户注册
      * @param userInfoDTO
      */
     void register(UserInfoDTO userInfoDTO);

     /**
      * 管理员注册
      * @param userInfoDTO
      */
     void adminRegister(UserInfoDTO userInfoDTO);

     /**
      * 用户登录
      * @param userDTO
      * @return
      */
     String login(UserDTO userDTO);

     /**
      * 管理员登录
      * @param userDTO
      * @return
      */
     String adminLogin(UserDTO userDTO);

     /**
      * 修改密码
      * @param userDTO
      * @param newpassword
      */
     Boolean update(UserDTO userDTO,String newpassword);

     /**
      * 忘记密码
      * @param nickname
      * @param name
      * @param identification
      * @param password
      */
    Boolean forget(String nickname,String name,String identification,String password);

     /**
      * 根据类型查询
      * @param type
      * @return
      */
     List<User> findByType(String type);

     /**
      * 根据昵称查询
      * @param nickname
      * @return
      */
     List<User> findByNickname(String nickname,int pageSize, int currentPage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(String id);

     /**
      * 查询所有
      * @return
      */
     List<User> findAll();

     /**
      * 修改用户信息
      * @param phone
      * @param email
      * @param address
      * @param nickname
      * @param provincial
      * @param city
      * @return
      */
     Boolean updateUserInfo(String phone, String email, String address, String provincial ,String city,String nickname);

    /**
     * 上传图片
     * @param img
     * @param id
     * @return
     */
    int updateImg(String img, Long id);

    /**
     * 查看男女比例
     * @return
     */
     List<LookDTO> findSex();

    /**
     * 根据年龄查询
     * @return
     */
    List<LookDTO> findAge();

    /**
     * 查看用户地区分布数据
     * @return
     */
    List<LookDTO> findAddress();

    Long findTotal(String name);
}
