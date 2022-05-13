package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Favorites;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavoritesMapper {
    /**
     * 添加订单
     * @param favorites
     * @return
     */
    Boolean addFavorites(Favorites favorites);

    /**
     * 删除收藏
     * @param favorites
     * @return
     */
    Boolean deleteByFavorites(Favorites favorites);

    /**
     * 查看产品是否收藏
     * @param favorites
     * @return
     */
    Favorites findFavorites(Favorites favorites);

    /**
     * 查看用户收藏
     * @param userid
     * @return
     */
    List<Favorites> queryById(Long userid);
}
