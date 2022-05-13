package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.ProductDTO;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.service.impl.ProductServiceImpl;
import com.example.xjlacgapp.unit.UpPhotoNameUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * 商品管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "商品管理API")
@RestController
@RequestMapping("/api/demo/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @ApiOperation(value = "根据昵称查询用户信息")
    @PostMapping("/findById")
    @ResponseBody
    public List<Product> findById(@RequestParam(value = "id") String id){
        return productService.findById(id);
    }

    @ApiOperation(value = "根据昵称查询用户信息")
    @PostMapping("/findById2")
    @ResponseBody
    public List<Product> findById2(@RequestParam(value = "id") String id){
        return productService.findById2(id);
    }

    @ApiOperation(value = "商品添加")
    @PostMapping("/addProduct")

    public void addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
    }

    @ApiOperation(value = "查询类型")
    @PostMapping("/findType")
    public List<String> findType( @RequestParam(value = "id") String id){
        return productService.findType( id);
    }

    @ApiOperation(value = "根据类型查询商品信息")
    @PostMapping("/findByType")
    @ResponseBody
    public List<Product> findByType(@RequestParam(value = "type") String type ,
                                    @RequestParam(value = "id") String id){
        return productService.findByType(type,id);
    }

    @ApiOperation(value = "根据类型查询商品信息")
    @PostMapping("/find")
    @ResponseBody
    public List<Product> findByProduct(@RequestParam(value = "product") String product ,
                                    @RequestParam(value = "id") String id){
        return productService.findByProduct(product,id);
    }
    @ApiOperation(value = "根据类型查询商品信息")
    @PostMapping("/find4")
    @ResponseBody
    public List<Product> findByProduct4(@RequestParam(value = "product") String product ,
                                       @RequestParam(value = "id") String id){
        return productService.findByProduct3(product,id);
    }


    @ApiOperation(value = "商品信息修改")
    @PostMapping("/updateProduct")

    public int updateProduct(@RequestParam(value = "product") String product,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "type") String type,
                             @RequestParam(value = "price") String price,
                             @RequestParam(value = "productid") String productid){
        return productService.updateProduct( product, title, content, type,price, productid);
    }
    /**
     * 上传图片
     */
    @ApiOperation(value = "上传图片")
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request,  Long productid) {
        //@RequestParam("file") MultipartFile file为接收图片参数
        //Long userId,String status 用户Id和状态

        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
            Path path = Paths.get("C:\\Users\\LENOVO\\ngproject\\acg\\src\\assets\\" + fileName);
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;
            Product products = productService.findByProductId(productid);
            String img= products.getImg();
            if ( img == null || img.isEmpty()) {
                productService.updateImg(avatar_url, productid);
            }
            else {
                String files = img+","+avatar_url;
                productService.updateImg(files,productid);
            }
            System.out.println(fileName+"\n");
            return fileName;//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param productid
     * @return
     */
    @ApiOperation(value = "查询图片")
    @PostMapping("/findByImg")
    @ResponseBody
    List<String> findByImg(@RequestParam(value = "productid",required = false) String productid){
        return productService.findByImg(productid);
    }
    @ApiOperation(value = "删除图片")
    @PostMapping("/deleteImg")
    @ResponseBody
    List<String> deleteImg(@RequestParam(value = "img") String img,@RequestParam(value = "productid") String productid){
        return productService.deleteImg(img,productid);
    }
    @ApiOperation(value = "删除商品")
    @PostMapping("/delete")
    @ResponseBody
    Boolean deleteByProduct(@RequestParam(value = "productids") String productids){
        String[] productid= productids.split(",");
        return productService.deleteByProduct(productid);
    }
    @ApiOperation(value = "还原商品")
    @PostMapping("/recoveryProduct")
    @ResponseBody
    Boolean RecoveryProduct(@RequestParam(value = "productids") String productids){
        String[] productid= productids.split(",");
        return productService.RecoveryProduct(productid);
    }
    @ApiOperation(value = "查询所有商品")
    @PostMapping("/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @ApiOperation(value = "查询类型")
    @PostMapping("/findType2")
    public List<String> findType2(){
        return productService.findType2();
    }

    @ApiOperation(value = "根据类型查询商品信息")
    @PostMapping("/findByType2")
    @ResponseBody
    public List<Product> findByType2(@RequestParam(value = "type") String type){
        return productService.findByType2(type);
    }

    @ApiOperation(value = "根据类型查询商品信息")
    @PostMapping("/find2")
    @ResponseBody
    public List<Product> findByProduct2(@RequestParam(value = "name") String name ,@RequestParam(value = "pageSize")int pageSize, @RequestParam(value = "currentPage")int currentPage){
        return productService.findByProduct2(name,pageSize,currentPage);
    }

    @ApiOperation(value = "根据商品id查询商品信息")
    @PostMapping("/findByProductId")
    @ResponseBody
    public Product findByProductId(@RequestParam(value = "productid") String productId){
        return productService.findByProductId(Long.valueOf(productId));
    }
    @ApiOperation(value = "修改点击量")
    @PostMapping("/updateapv")
    @ResponseBody
    public int updateapv(@RequestParam(value = "productid") String productid){
        return productService.updateapv(productid);
    }
    @ApiOperation(value = "查询总数")
    @PostMapping("/findTotal")
    @ResponseBody
    public Long findTotal(@RequestParam(value = "name") String name){
        return productService.findTotal(name);
    }

    @ApiOperation(value = "根据推荐查询商品信息")
    @PostMapping("/find3")
    @ResponseBody
    public List<Product> findByProduct3(@RequestParam(value = "id") String id){
        return productService.findByProduct3(id);
    }
    @ApiOperation(value = "统计各个年龄段喜欢的类型")
    @PostMapping("/SumType")
    List<String> SumTotal(){
        return  productService.SumTotal();
    }
}
