package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.BrowseMapper;
import com.example.xjlacgapp.mapper.CommentMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Comment;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.User;
import com.example.xjlacgapp.service.BrowseService;
import com.example.xjlacgapp.service.CommentService;
import com.example.xjlacgapp.unit.IdUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;
    private UserMapper userMapper;
    @Autowired
    public CommentServiceImpl( UserMapper userMapper, CommentMapper commentMapper){
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Boolean addComment(String userid, String productid, String comment) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setProductid(Long.valueOf(productid));
        comment1.setUserid(Long.valueOf(userid));
        comment1.setId(Long.valueOf(IdUnit.getId()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            comment1.setCreatetime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return commentMapper.addComment(comment1);
    }

    @Override
    public List<BrowseDate> queryById(String productid) {
       List<Comment> list=commentMapper.queryById(Long.valueOf(productid));
       List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size();i++){
            BrowseDate browseDate = new BrowseDate();
            User user =userMapper.findById(list.get(i).getUserid());
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setId(list.get(i).getId());
            browseDate.setComment(list.get(i).getComment());
            browseDate.setImg(user.getImg());
            browseDate.setUsername(user.getNickname());
            browseDates.add(0,browseDate);
        }
       return  browseDates;
    }
}
