package com.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author Wudi
 * @Description: 商品提交效验是否为空
 * @date 18:06  2018/1/17
 */
@Data
public class ProductForm {

    private String productId;

    /**商品名*/
    @NotBlank(message = "请输入商户名称")
    private String productName;

    /**单价*/
    @DecimalMin(value = "0",message = "商品价格不能小于0")
    private BigDecimal productPrice;

    /**库存*/
    @Min(value=0,message = "库存不能小于0")
    private Integer productStock;

    /**商品描述*/
    @NotBlank(message = "请填写商品描述")
    private String productDescribtion;

    /**图片链接*/
    @NotBlank(message = "请上传商品图片")
    private String productIcon;

    /**类目编号*/
    @Min(value=0,message = "请选择类目")
    private Integer  categoryType;

}
