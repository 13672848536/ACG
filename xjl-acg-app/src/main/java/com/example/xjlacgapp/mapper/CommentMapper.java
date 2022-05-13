package com.example.xjlacgapp.mapper;


import com.example.xjlacgapp.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    /**
     * 添加订单
     * @param comment
     * @return
     */
    Boolean addComment(Comment comment);
    List<Comment> queryById(Long productid);
}
