package com.example.xjlacgapp.service;


import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Reply;

import java.util.List;

public interface ReplyService {
    Boolean addReply(String userid , String id, String reply);

    List<BrowseDate> queryById(String id);
}
