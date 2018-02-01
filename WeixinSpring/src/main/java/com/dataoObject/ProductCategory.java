package com.dataoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Wudi
 * @Description: 类目
 * @Data包含了get,set,toString 方法
 * @date 21:13  2017/12/20
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**类目名*/
    private String  categoryName;

    /**类目编号*/
    private Integer categoryType;

    /**创建时间*/
    private Date createTime;

    /**修改的时间*/
    private Date updateTime;

    //无参构造
    public ProductCategory() {
    }

    //有参构造
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
