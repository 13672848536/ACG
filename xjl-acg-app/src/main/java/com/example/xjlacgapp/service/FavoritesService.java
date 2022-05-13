package com.example.xjlacgapp.service;


import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Favorites;

import java.util.List;

public interface FavoritesService {
    /**
     * 添加收藏
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
     * 查看收藏
     * @param favorites
     * @return
     */
    Boolean findFavorites(Favorites favorites);
    List<BrowseDate> queryById(String userid);
}
