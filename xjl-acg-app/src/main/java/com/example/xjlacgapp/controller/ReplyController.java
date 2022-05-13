package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Reply;
import com.example.xjlacgapp.service.impl.BrowseServiceImpl;
import com.example.xjlacgapp.service.impl.ReplyServiceImpl;
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
@RequestMapping("/api/demo/Reply")
public class ReplyController {
    @Autowired
    ReplyServiceImpl replyService;
    @ApiOperation(value = "回复添加")
    @PostMapping("/addReply")

    public Boolean addReply(@RequestParam(value = "userid")String userid, @RequestParam(value = "id")String id,@RequestParam(value = "reply")String reply){
        return replyService.addReply(userid,id,reply);
    }

    @ApiOperation(value = "查看回复")
    @PostMapping("/queryById")
    List<BrowseDate> queryById(@RequestParam(value = "id") String id){
        return replyService.queryById(id);
    }
}
