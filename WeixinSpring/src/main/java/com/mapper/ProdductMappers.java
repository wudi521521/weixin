package com.mapper;

import com.dataoObject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description: Mapper的dao层
 * @date 15:52  2018/1/30
 */
public interface ProdductMappers {
    /**保存数据*/
    @Insert("INSERT INTO product_category(category_id,category_name,category_type)VALUE(#{category_id},#{category_name},#{category_type})")
    public int insert(Map<String,Object> map);
    /**查询数据单个实体*/
    @Select("select * from product_category where category_id =#{categoryId}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    }
    )
    public ProductCategory findData(String categoryId);
    /**查询数据返回一个list列表*/
    @Select("select * from product_category ")
    @Results({ @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    }
    )
    public List<ProductCategory> findDataList();
    /**修改数据*/
    @Update("update product_category set category_name = #{categoryName} where category_id=#{categoryId} ")
    public int updateData(@Param("categoryName") String categoryName,@Param("categoryId") String categoryId);
    /**删除数据*/
    @Delete("delete from product_category where category_id=#{categoryId}")
    public void deleteData(String categoryId);


    //TODO 调用xml配置文件尽心册书
    public List<ProductCategory> findProductDataList();

}
