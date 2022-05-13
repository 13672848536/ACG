package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.ProvincialMapper;
import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.UserDTO;
import com.example.xjlacgapp.model.UserInfoDTO;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.Provincial;
import com.example.xjlacgapp.pojo.User;
import com.example.xjlacgapp.service.UserService;
import com.example.xjlacgapp.unit.IdUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
   private UserMapper userMapper;
   private ProvincialMapper provincialMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,ProvincialMapper provincialMapper){
        this.userMapper = userMapper;
        this.provincialMapper = provincialMapper;
    }
    @Override
    public void register(UserInfoDTO userInfoDTO) {
        User user=new User();
        Provincial provincial = provincialMapper.queryById(Integer.parseInt(userInfoDTO.getProvincial()));
        user.setPassword(userInfoDTO.getPassword());
        user.setIdentification(userInfoDTO.getIdentification());
        user.setEmail(userInfoDTO.getEmail());
        user.setAddress(userInfoDTO.getAddress());
       user.setId(Long.valueOf(IdUnit.getId()));
       user.setNickname(userInfoDTO.getNickname());
       user.setName(userInfoDTO.getName());
       user.setPhone(userInfoDTO.getPhone());
       user.setProvincial(provincial.getProvincial());
       user.setCity(userInfoDTO.getCity());
       user.setType("普通用户");
        if (userInfoDTO.getIdentification()%2==1){
            user.setSex("男");
        }else {
            user.setSex("女");
        }
        String id = String.valueOf(userInfoDTO.getIdentification());
        user.setYear(2022-Long.valueOf(id.substring(6,10)));
       userMapper.add(user);
    }

    @Override
    public void adminRegister(UserInfoDTO userInfoDTO) {
        User user1=new User();
        Provincial provincial = provincialMapper.queryById(Integer.parseInt(userInfoDTO.getProvincial()));
        user1.setPassword(userInfoDTO.getPassword());
        user1.setIdentification(userInfoDTO.getIdentification());
        user1.setEmail(userInfoDTO.getEmail());
        user1.setAddress(userInfoDTO.getAddress());
        user1.setId(Long.valueOf(IdUnit.getId()));
        user1.setNickname(userInfoDTO.getNickname());
        user1.setName(userInfoDTO.getName());
        user1.setPhone(userInfoDTO.getPhone());
        user1.setProvincial(provincial.getProvincial());
        user1.setCity(userInfoDTO.getCity());
        user1.setType("管理员");
        if (userInfoDTO.getIdentification()%2==1){
            user1.setSex("男");
        }else {
            user1.setSex("女");
        }
        String id = String.valueOf(userInfoDTO.getIdentification());
        user1.setYear(2022-Long.valueOf(id.substring(6,10)));
        userMapper.add(user1);
    }

    @Override
    public String login(UserDTO userDTO) {
        User user1=userMapper.queryByNickname(userDTO.getNickname());
        //判断用户是否为空
        if (user1.getPassword().equals(userDTO.getPassword())){
            return String.valueOf(user1.getId());
        }else {
            return null;
        }
    }

    @Override
    public String adminLogin(UserDTO userDTO) {
        User user1=userMapper.queryByNickname(userDTO.getNickname());
        //判断用户是否为空
        if (user1.getPassword().equals(userDTO.getPassword()) && user1.getType().equals("管理员")){
            return String.valueOf(user1.getId());
        }else {
            return null;
        }
    }

    @Override
    public Boolean update(UserDTO userDTO, String newpassword) {
        User user=userMapper.queryByNickname(userDTO.getNickname());
        if (user.getPassword().equals(userDTO.getPassword()) ){
          userMapper.updatePassword(newpassword,userDTO.getNickname());
          return true;
        }
        return false;
    }

    @Override
    public Boolean forget(String nickname, String name, String identification, String password) {
        User user1=userMapper.queryByNickname(nickname);
        if (user1.getName().equals(name) && user1.getIdentification().equals(Long.valueOf(identification))){
            userMapper.updatePassword(password,nickname);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findByType(String type) {
        return userMapper.findByType(type);
    }

    @Override
    public List<User> findByNickname(String nickname,int pageSize, int currentPage) {
        return userMapper.findByNickname(nickname,pageSize,currentPage*pageSize-pageSize);
    }

    @Override
    public User findById(String id) {
        Long ids =Long.valueOf(id);
        User user=userMapper.findById(ids);
        String identification = String.valueOf(user.getIdentification());
        Long year=2022-Long.valueOf(identification.substring(6,10));
       userMapper.updateYear(year,ids);
        return  userMapper.findById(ids);
    }

    @Override
    public List<User> findAll() {
        return userMapper.queryAll();
    }

    @Override
    public Boolean updateUserInfo(String phone, String email, String address, String provincial ,String city,String nickname) {
        Long phone1 = Long.valueOf(phone);
        if( provincial.indexOf("省") != -1 || provincial.indexOf("市") != -1 || provincial.indexOf("区") != -1){
        return userMapper.updateUserInfo(phone1,email,address,provincial,city,nickname);
        }else {
            Provincial provincial1 = provincialMapper.queryById(Integer.parseInt(provincial));
            provincial = provincial1.getProvincial();
            return userMapper.updateUserInfo(phone1,email,address,provincial,city,nickname);
        }
    }

    @Override
    public int updateImg(String img, Long id) {
        return userMapper.updateImg(img,id);
    }

    @Override
    public List<LookDTO> findSex() {
        List<User> data = userMapper.findSex();
        List<LookDTO> dataList= new ArrayList<LookDTO>();
        for(int i = 0; i < data.size();i++) {
            LookDTO lookDTO = new LookDTO();
            lookDTO.setName(data.get(i).getName());
            lookDTO.setValue(data.get(i).getValue());
            dataList.add(0, lookDTO);
        }
        return dataList;
    }

    @Override
    public List<LookDTO> findAge() {
        List<User> data = userMapper.findAge();
        List<LookDTO> dataList= new ArrayList<LookDTO>();
        for(int i = 0; i < data.size();i++) {
            LookDTO lookDTO = new LookDTO();
            lookDTO.setName(data.get(i).getName());
            lookDTO.setValue(data.get(i).getValue());
            dataList.add(0, lookDTO);
        }
        return dataList;
    }

    @Override
    public List<LookDTO> findAddress() {
        List<User> data = userMapper.findAddress();
        List<LookDTO> dataList= new ArrayList<LookDTO>();
        for(int i = 0; i < data.size();i++) {
            LookDTO lookDTO = new LookDTO();
            lookDTO.setName(data.get(i).getName());
            lookDTO.setValue(data.get(i).getValue());
            dataList.add(0, lookDTO);
        }
        return dataList;
    }
    @Override
    public Long findTotal(String name) {
       User user = userMapper.findTotal(name);
        return user.getId();
    }
}
