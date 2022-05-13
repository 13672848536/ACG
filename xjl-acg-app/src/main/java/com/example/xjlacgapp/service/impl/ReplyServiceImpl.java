package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.BrowseMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.ReplyMapper;
import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.*;
import com.example.xjlacgapp.service.BrowseService;
import com.example.xjlacgapp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    private ReplyMapper replyMapper;
    private UserMapper userMapper;
    @Autowired
    public ReplyServiceImpl(ReplyMapper replyMapper, UserMapper userMapper){
        this.replyMapper = replyMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Boolean addReply(String userid, String id, String reply) {
        Reply reply1 =new Reply();
        reply1.setReply(reply);
        reply1.setId(Long.valueOf(id));
        reply1.setUserid(Long.valueOf(userid));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            reply1.setCreatetime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return replyMapper.addReply(reply1);
    }

    @Override
    public List<BrowseDate> queryById(String id) {
         List<Reply> list=replyMapper.queryById(Long.valueOf(id));
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size();i++){
            BrowseDate browseDate = new BrowseDate();
            User user =userMapper.findById(list.get(i).getUserid());
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setId(list.get(i).getId());
            browseDate.setComment(list.get(i).getReply());
            browseDate.setImg(user.getImg());
            browseDate.setUsername(user.getNickname());
            browseDates.add(0,browseDate);
        }
        return  browseDates;
    }
}
