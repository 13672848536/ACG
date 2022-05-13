package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ProductMapper {
    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    /**
     * 根据类型查询
     * @param type
     * @param id
     * @return
     */
    List<Product>  findByType(String type,Long id);

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    List<Product>  findByType2(String type);

    /**
     * 根据商品名称查询
     * @param product
     * @return
     */
    List<Product> findByProduct(String product ,Long id);

    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<Product> findByProduct2(String name,int pageSize, int currentPage);

    /**
     * 根据商品名称查询
     * @param product
     * @return
     */
    List<Product> findByProduct3(String product ,Long id);

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    List<Product> findById(Long id);
    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    List<Product> findById2(Long id);

    /**
     * 根据用户id查询删除商品
     * @param id
     * @return
     */
    List<Product> findById3(Long id);

    /**
     * 添加商品
     * @param product
     * @return
     */
    Boolean addProduct(Product product);

    /**
     * 修改商品信息
     * @param product
     * @param title
     * @param content
     * @param type
     * @param price
     * @param productid
     * @return
     */
     int updateProduct(String product,String title,String content,String type,int price,Long productid);

    /**
     * 删除商品
     * @param productid
     * @return
     */
    Boolean deleteByProduct(Long productid);

    /**
     * 查询类型
     * @return
     */
    List<Product> findType( Long id);

    /**
     * 查询类型
     * @return
     */
    List<Product> findType2();

    /**
     * 上传图片
     * @param img
     * @param productid
     * @return
     */
    int updateImg(String img, Long productid);

    /**
     * 根据商品id查询
     * @param productid
     * @return
     */
    Product findByProductId(Long productid);

    /**
     * 修改点击量
     * @param apv
     * @param productid
     * @return
     */
    int updateapv(int apv,Long productid);

    /**
     * 修改库存
     * @param stock
     * @param amount
     * @param productid
     * @return
     */
    int updateStock(int stock, int amount,Long productid);
    /**
     * 修改库存
     * @param stock
     * @param productid
     * @return
     */
    int updateStock2(int stock,Long productid);

    /**
     * 查询总数
     * @return
     */
    Product findTotal(String name);

    /**
     * 查看已下架商品
     * @return
     */
    List<Product> findDelete();

    /**
     * 恢复商品
     * @param productid
     * @return
     */
    Boolean RecoveryProduct(Long productid);
}
