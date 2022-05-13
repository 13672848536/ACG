package com.example.xjlacgapp.mapper;


import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
   List<User> queryAll();

    /**
     * 添加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 根据昵称查询
     * @param nickname
     * @return
     */
  User queryByNickname(String nickname);

    /**
     * 修改密码
     * @param password
     * @param nickname
     */
  int updatePassword(String password,String nickname);

    /**
     * 修改用户信息
     * @param phone
     * @param email
     * @param address
     * @param nickname
     */
    Boolean updateUserInfo(Long phone,String email,String address,String provincial, String city,String nickname);

    /**
     * 修改年龄
     * @param year
     * @param id
     * @return
     */
    int updateYear(Long year, Long id);

    /**
     * 上传图片
     * @param img
     * @param id
     * @return
     */
    int updateImg(String img, Long id);
    /**
     * 查看用户男女比例数据
     * @return
     */
    List<User> findSex();

    /**
     * 根据昵称模糊查询
     * @param nickname
     * @return
     */
    List<User> findByNickname(String nickname,int pageSize, int currentPage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Long id);
    /**
     * 根据类型查询
     * @param type
     * @return
     */
    List<User> findByType(String type);

    /**
     * 根据年龄查询
     * @return
     */
    List<User> findAge();

    /**
     * 查看用户地区分布数据
     * @return
     */
    List<User> findAddress();

  List<User> queryAge1();
  List<User> queryAge2();
  List<User> queryAge3();
  User findTotal(String name);
}
