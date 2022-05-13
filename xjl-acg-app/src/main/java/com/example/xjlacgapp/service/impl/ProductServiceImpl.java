package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.*;
import com.example.xjlacgapp.model.ProductDTO;
import com.example.xjlacgapp.pojo.*;
import com.example.xjlacgapp.service.ProductService;
import com.example.xjlacgapp.unit.IdUnit;
import com.example.xjlacgapp.unit.KNN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  implements ProductService {
private ProductMapper productMapper;
private UserMapper userMapper;
private BrowseMapper browseMapper;
private OrderMapper orderMapper;
private FavoritesMapper favoritesMapper;
    @Autowired
public ProductServiceImpl(ProductMapper productMapper, UserMapper userMapper,BrowseMapper browseMapper,OrderMapper orderMapper,FavoritesMapper favoritesMapper){
    this.productMapper = productMapper;
    this.userMapper = userMapper;
    this.browseMapper = browseMapper;
    this.orderMapper = orderMapper;
    this.favoritesMapper = favoritesMapper;
}
    @Override
    public Boolean addProduct(ProductDTO productDTO) {
    Product product =new Product();
    product.setProduct(productDTO.getProduct());
    product.setProductid(Long.valueOf(IdUnit.getId()));
    product.setId(Long.valueOf(productDTO.getId()));
    product.setType(productDTO.getType());
    product.setTitle(productDTO.getTitle());
    product.setPrice(productDTO.getPrice());
    product.setContent(productDTO.getContent());
    product.setStock(productDTO.getStock());
    product.setState("0");
    User user=userMapper.findById(Long.valueOf(productDTO.getId()));
    product.setUsername(user.getNickname());
    return productMapper.addProduct(product);
    }


    @Override
    public List<Product> findById(String id) {
        Long ids= Long.valueOf(id);
        return productMapper.findById2(ids);
    }

    @Override
    public List<Product> findById2(String id) {
        Long ids= Long.valueOf(id);
        return productMapper.findById3(ids);
    }

    @Override
    public List<String> findType( String id) {
     List<Product> data = productMapper.findType(Long.valueOf(id));
     List<String> types= new ArrayList<String>();
        for(int i = 0; i < data.size();i++) {
            types.add(0, data.get(i).getType());
        }
        return types;
    }

    @Override
    public List<String> findType2() {
        List<Product> data = productMapper.findType2();
        List<String> types= new ArrayList<String>();
        for(int i = 0; i < data.size();i++) {
            types.add(0, data.get(i).getType());
        }
        return types;
    }

    @Override
    public List<Product> findByType(String type, String id) {
        Long ids = Long.valueOf(id);
        return productMapper.findByType(type,ids);
    }

    @Override
    public List<Product> findByType2(String type) {
        return productMapper.findByType2(type);
    }

    @Override
    public List<Product> findByProduct(String product, String id) {
        Long ids = Long.valueOf(id);
        return productMapper.findByProduct(product,ids);
    }
    @Override
    public List<Product> findByProduct3(String product, String id) {
        Long ids = Long.valueOf(id);
        return productMapper.findByProduct3(product,ids);
    }

    @Override
    public List<Product> findByProduct2(String name,int pageSize, int currentPage) {
        return productMapper.findByProduct2(name,pageSize,currentPage*pageSize-pageSize);
    }

    @Override
    public int updateProduct(String product,String title,String content,String type,String price,String productid) {
        int price1 = Integer.parseInt(price);
       Long productid1 = Long.valueOf(productid);
        return productMapper.updateProduct(product,title,content,type,price1,productid1);
    }

    @Override
    public void updateImg(String img, Long productid) {
        productMapper.updateImg(img,productid);
    }

    @Override
    public Product findByProductId(Long productid) {
        return productMapper.findByProductId(productid);
    }

    @Override
    public List<String> findByImg(String productid) {
        Product product = productMapper.findByProductId(Long.valueOf(productid));
        String imgs= product.getImg();
        if (imgs != null){
        String[] img= imgs.split(",");
        List<String> list =  new ArrayList<String>();
        for(int i = 0; i < img.length;i++) {
            list.add(0, img[i]);
        }
        return list;
        }
        return null;
    }

    @Override
    public List<String> deleteImg(String img,String productid) {
        Product product = productMapper.findByProductId(Long.valueOf(productid));
        String imgs= product.getImg();
        if (imgs.indexOf(img+",") != -1){
         String img1 = imgs.replace(img+",","");
            productMapper.updateImg(img1, Long.valueOf(productid));
        }else if (imgs.indexOf(","+img) != -1){
            String img1 = imgs.replace(","+img,"");
            productMapper.updateImg(img1, Long.valueOf(productid));
        }else if (imgs.indexOf(img) != -1){
            String img1 = imgs.replace(img,"");
            productMapper.updateImg(img1, Long.valueOf(productid));
        }
        if (img != null && !img.isEmpty()){
            File files = new File("C:\\Users\\LENOVO\\ngproject\\acg\\src\\assets\\"+img);
            files.delete();
        }
        List<String> list=findByImg(productid);
        return list;
    }

    @Override
    public Boolean deleteByProduct(String[] productid) {
        for (int i =0;i<productid.length;i++){
//            Product product = productMapper.findByProductId(Long.valueOf(productid[i]));
//            String imgs= product.getImg();
//            if (imgs != null){
//            String[] img= imgs.split(",");
//            for (int j = 0; j<img.length; j++){
//            if (img[j] != null && !img[j].isEmpty()){
//            File files = new File("C:\\Users\\LENOVO\\ngproject\\acg\\src\\assets\\"+img[j]);
//            files.delete();
//        }}}
            productMapper.deleteByProduct(Long.valueOf(productid[i]));
        }
        return true;
    }

    @Override
    public Boolean RecoveryProduct(String[] productid) {
        for (int i =0;i<productid.length;i++){
            productMapper.RecoveryProduct(Long.valueOf(productid[i]));
        }
        return true;
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public int updateapv(String productid) {
       Product product= productMapper.findByProductId(Long.valueOf(productid));
       int apv = product.getApv()+1;
        return productMapper.updateapv(apv, Long.valueOf(productid));
    }

    @Override
    public Long findTotal(String name) {
        Product product = productMapper.findTotal(name);
        return product.getId();
    }

    /**
     * KNN推荐算法
     * @param id
     * @return
     */
    @Override
    public List<Product> findByProduct3(String id) {
        List<Order> orders=orderMapper.queryById3(Long.valueOf(id));//当前用户订单数据（去重）
        String name = "";
        List<Product> products = new ArrayList<Product>();
        if (orders.size() == 0){
        User user = userMapper.findById(Long.valueOf(id));
        if (user.getYear()<=20){
            Browse browse = browseMapper.likeType();
            name = browse.getType();
            return productMapper.findByProduct2(name,5,0);
        }
        if (user.getYear()>20 && user.getYear()<=25){
            Browse browse = browseMapper.likeType2();
            name = browse.getType();
            return productMapper.findByProduct2(name,5,0);
        }
        if (user.getYear()>=26){
            Browse browse = browseMapper.likeType3();
            name = browse.getType();
            return productMapper.findByProduct2(name,5,0);
        }}else {
           List<Order> users = orderMapper.findId();//存在订单数据的用户id集合（去重）
           List<Favorites> favorites = favoritesMapper.queryById(Long.valueOf(id));//当前收藏的商品集合
           List<Product> deletes = productMapper.findDelete();//查看已经下架的商品
           List<Long> a = new ArrayList<Long>();
           List<Double> jaccards =new ArrayList<Double>();
            List<Long> d = new ArrayList<Long>();
            List<Long> e = new ArrayList<Long>();;
            List<Long> c ;
            List<Long> h = new ArrayList<Long>();
            List<Long> l = new ArrayList<Long>();
            double min = 1.0;
            for (int s =0;s<orders.size();s++){
                a.add(orders.get(s).getProductid());
            }
            for (int s =0;s<favorites.size();s++){
                h.add(favorites.get(s).getProductid());
            }
            for (int s =0;s<deletes.size();s++){
                l.add(deletes.get(s).getProductid());
            }
            for (int i =0;i<users.size();i++){
                List<Order> user = orderMapper.queryById3(users.get(i).getUserid());//查看用户订单数据（去重）
                List<Long> use = new ArrayList<Long>();
                for (int j =0; j<user.size();j++){
                    use.add(user.get(j).getProductid());
                }
                double Jaccard = KNN.Jaccard(a,use);//获取杰卡德距离
                jaccards.add(Jaccard);
            }
            for ( ;e.size()< 5;) {
                jaccards.remove(min);//删除最小值
                min = Collections.min(jaccards);//获取最小值
                for (int g = 0; g < users.size(); g++) {
                    List<Order> user = orderMapper.queryById3(users.get(g).getUserid());//查看用户订单数据（去重）
                    List<Long> use = new ArrayList<Long>();
                    for (int f = 0; f < user.size(); f++) {
                        use.add(user.get(f).getProductid());
                    }
                    c = KNN.Jaccard2(a, use, min);//用户距离与最小值对比
                    if (c != null) {
                        users.remove(g);
                        d.addAll(c);//存放满足条件的用户订单数据
                        d.removeAll(a);//删除当前用户订单数据
                        d.removeAll(h);//删除用户已经收藏的产品id
                        d.removeAll(l);//删除已经下架的产品id
                        e = d.stream().distinct().collect(Collectors.toList());//去重
                        break;
                    }
                }
            }
            for (int k = 0; k < 5; k++) {
                Product product = productMapper.findByProductId(e.get(k));//获取产品信息
                products.add(product);
            }
        }
        return products;
    }
    @Override
    public List<String> SumTotal() {
        List<String> type = new ArrayList<String>();
        Browse browse = browseMapper.likeType();
        Browse browse1 = browseMapper.likeType2();
        Browse browse2 = browseMapper.likeType3();
        if(browse.getType() == null){
            browse.setType("空");
        }
        if(browse1.getType() == null){
            browse1.setType("空");
        }
        if(browse2.getType() == null){
            browse2.setType("空");
        }
        type.add(0,browse.getType());
        type.add(0,browse1.getType());
        type.add(0,browse2.getType());
        return type;
    }
}
