package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReplyMapper {
    /**
     * 添加订单
     * @param reply
     * @return
     */
    Boolean addReply(Reply reply);
    List<Reply> queryById(Long id);
}
