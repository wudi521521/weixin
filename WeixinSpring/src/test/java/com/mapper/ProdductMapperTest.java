package com.mapper;

import com.dataoObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description:
 * @date 16:24  2018/1/30
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProdductMapperTest {

    @Autowired
    private ProdductMappers productMapper;

    //@Test
    public void insertData(){

        Map<String, Object> map = new HashMap<>();
        map.put("category_id","132456789");
        map.put("category_name","category_name9");
        map.put("category_type",9);
        int insert = productMapper.insert(map);
        Assert.assertNotNull(insert);
    }
    
    //@Test
    public void findData(){
        ProductCategory data = productMapper.findData("132456789");
        Assert.assertNotNull(data);
    }

    //@Test
    public void findDataList(){
        List<ProductCategory> dataList = productMapper.findDataList();
        Assert.assertNotNull(dataList.size());
    }

    //@Test
    public void updateData(){
        int updateData = productMapper.updateData("hello world", "132456789");
        Assert.assertNotNull(updateData);
    }

    //@Test
    public void deleteData(){
        productMapper.deleteData("132456789");

    }

    @Test
    public void findDataXml(){
        List<ProductCategory> productDataList = productMapper.findProductDataList();
        Assert.assertNotNull(productDataList.size());
    }
}