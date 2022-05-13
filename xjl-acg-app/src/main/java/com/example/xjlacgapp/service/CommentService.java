package com.example.xjlacgapp.service;


import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Comment;

import java.util.List;

public interface CommentService {
    Boolean addComment(String userid , String productid,String comment);

    List<BrowseDate> queryById(String productid);
}
