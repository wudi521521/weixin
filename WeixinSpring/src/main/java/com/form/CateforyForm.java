package com.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author Wudi
 * @Description: 类目页面传递效验实体
 * @date 10:02  2018/1/18
 */
@Data
public class CateforyForm {

    /**类目id*/
    private Integer categoryId;

    /**类目名*/
    @NotBlank(message = "请填写类目名称")
    private String  categoryName;

    /**类目编号*/
    @Min(value=0,message = "请添加类目编号")
    private Integer categoryType;

}
