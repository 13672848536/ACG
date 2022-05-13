package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Comment;
import com.example.xjlacgapp.service.impl.BrowseServiceImpl;
import com.example.xjlacgapp.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "浏览记录管理API")
@RestController
@RequestMapping("/api/demo/Comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @ApiOperation(value = "评论添加")
    @PostMapping("/addComment")

    public Boolean addComment(@RequestParam(value = "userid")String userid, @RequestParam(value = "productid")String productid,@RequestParam(value = "comment")String comment){
        return commentService.addComment(userid,productid,comment);
    }

    @ApiOperation(value = "查看评论")
    @PostMapping("/queryById")
    List<BrowseDate> queryById(@RequestParam(value = "productid")String productid){
        return commentService.queryById(productid);}
}
