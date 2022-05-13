package com.example.xjlacgapp.controller;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.UserDTO;
import com.example.xjlacgapp.model.UserInfoDTO;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.User;
import com.example.xjlacgapp.service.impl.ProductServiceImpl;
import com.example.xjlacgapp.service.impl.UserServiceImpl;
import com.example.xjlacgapp.unit.UpPhotoNameUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "用户管理API")
@RestController
@RequestMapping("/api/demo/user")
public class UserController {
@Autowired
UserServiceImpl userServiceimpl;

    @ApiOperation(value = "用户登录")
    @PostMapping("/Login")
    public String  login( @RequestBody UserDTO userDTO){
   return userServiceimpl.login(userDTO);
}

     @ApiOperation(value = "管理员登录")
     @PostMapping("/administratorLogin")
     @ResponseBody
     public String adminLogin(@RequestBody UserDTO userDTO){
        return userServiceimpl.adminLogin(userDTO);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/Register")
    public void Register(@RequestBody UserInfoDTO userInfoDTO){
    userServiceimpl.register(userInfoDTO);
    }

    @ApiOperation(value = "管理员注册")
    @PostMapping("/adminRegister")

    public void adminRegister(@RequestBody UserInfoDTO userInfoDTO){
        userServiceimpl.adminRegister(userInfoDTO);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/update/{id}")
    @ResponseBody
    public Boolean update(
            @RequestBody UserDTO dto,
            @PathVariable("id")  String newpassword
           ){
       return userServiceimpl.update(dto,newpassword);
    }

    @ApiOperation(value = "忘记密码")
    @PostMapping("/forget")
    @ResponseBody
    public Boolean forget(
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "identification") String identification,
            @RequestParam(value = "password") String password){
       return userServiceimpl.forget(nickname,name,identification,password);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Boolean updateUserInfo(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "provincial") String provincial ,
            @RequestParam(value = "city") String city
    ){
        return userServiceimpl.updateUserInfo(phone,email,address,provincial,city,nickname);
    }
    @ApiOperation(value = "查询所有用户信息")
    @PostMapping("/findAll")
    public List<User> findAll(){
        return userServiceimpl.findAll();
    }

    @ApiOperation(value = "根据昵称查询用户信息")
    @PostMapping("/find")
    @ResponseBody
    public List<User> findByNickname(@RequestParam(value = "nickname") String nickname,@RequestParam(value = "pageSize")int pageSize, @RequestParam(value = "currentPage")int currentPage){
        return userServiceimpl.findByNickname(nickname,pageSize,currentPage);
    }

    @ApiOperation(value = "根据昵称查询用户信息")
    @PostMapping("/findById")
    @ResponseBody
    public User findById(@RequestParam(value = "id") String id){
        return userServiceimpl.findById(id);
    }

    @ApiOperation(value = "根据类型查询用户信息")
    @PostMapping("/findByType")
    public List<User> findByType(@RequestParam(value = "type") String type){
        return userServiceimpl.findByType(type);
    }

    @ApiOperation(value = "查询男女比例")
    @PostMapping("/findSex")
    public List<LookDTO> findBySex(){
        return userServiceimpl.findSex();
    }

    @ApiOperation(value = "查询男女比例")
    @PostMapping("/findAge")
    public List<LookDTO> findByAge(){
        return userServiceimpl.findAge();
    }

    @ApiOperation(value = "查询男女比例")
    @PostMapping("/findAddress")
    public List<LookDTO> findByAddress(){
        return userServiceimpl.findAddress();
    }

    /**
     * 修改头像
     */
    @ApiOperation(value = "上传头像")
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request, Long userId) {
        //@RequestParam("file") MultipartFile file为接收图片参数
        //Long userId,String status 用户Id和状态

        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
            Path path = Paths.get("C:\\Users\\LENOVO\\ngproject\\acg\\src\\assets\\" + fileName);
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;
            User user= userServiceimpl.findById(String.valueOf(userId));
            String name = user.getImg();
            if (name != null && !name.isEmpty()){
                File files = new File("C:\\Users\\LENOVO\\ngproject\\acg\\src\\assets\\"+name);
                files.delete();
            }
            userServiceimpl.updateImg(avatar_url,userId);
            System.out.println(fileName+"\n");
            return fileName;//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @ApiOperation(value = "查询总数")
    @PostMapping("/findTotal")
    @ResponseBody
    public Long findTotal(@RequestParam(value = "name") String name){
        return userServiceimpl.findTotal(name);
    }
}
