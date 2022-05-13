package com.example.xjlacgapp.service;

import com.example.xjlacgapp.model.ProductDTO;
import com.example.xjlacgapp.pojo.Product;

import java.util.List;

public interface ProductService {
    /**
     * 添加商品
     * @param productDTO
     * @return
     */
   Boolean addProduct(ProductDTO productDTO);

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findById(String id);
    /**
     * 查询删除商品
     * @return
     */
    List<Product> findById2(String id);
    /**
     * 查询类型
     * @return
     */
    List<String> findType(String id);

    /**
     * 查询类型
     * @return
     */
    List<String> findType2();

    /**
     * 根据类型查询
     * @param type
     * @param id
     * @return
     */
    List<Product>  findByType(String type,String id);

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    List<Product>  findByType2(String type);

    /**
     * 根据用户id查询
     * @param product
     * @param id
     * @return
     */
    List<Product> findByProduct(String product, String id);

    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<Product> findByProduct2(String name,int pageSize, int currentPage);

    /**
     * 根据用户id查询删除商品
     * @param product
     * @param id
     * @return
     */
    List<Product> findByProduct3(String product, String id);
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
    int updateProduct(String product,String title,String content,String type,String price,String productid);

 /**
  * 上传图片
  * @param img
  * @param productid
  */
    void updateImg(String img, Long productid);

    /**
     * 根据商品id查询
     * @param productid
     * @return
     */
    Product findByProductId(Long productid);

    /**
     * 查询图片
     * @param productid
     * @return
     */
    List<String> findByImg(String productid);

    /**
     * 删除图片
     * @param img
     * @param productid
     * @return
     */
    List<String> deleteImg(String img,String productid);

    /**
     * 删除商品
     * @param productid
     * @return
     */
    Boolean deleteByProduct(String[] productid);

    /**
     * 恢复商品
     * @param productid
     * @return
     */
    Boolean RecoveryProduct(String[] productid);
    /**
     *  查询所有
     * @return
     */
    List<Product> findAll();

    /**
     * 修改点击量
     * @param productid
     * @return
     */
    int updateapv(String productid);

    /**
     * 查询总数
     * @return
     */
    Long findTotal(String name);

    /**
     * 根据推荐商品名称查询
     * @param id
     * @return
     */
    List<Product> findByProduct3(String id);

    List<String> SumTotal();
}
