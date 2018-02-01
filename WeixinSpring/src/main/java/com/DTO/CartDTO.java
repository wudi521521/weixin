package com.DTO;

import lombok.Data;

/**
 * @author Wudi
 * @Description: 购物车
 * @date 19:41  2017/12/28
 */
@Data
public class CartDTO {
    /**商品id*/
    private String id;

    /**下单数量*/
    private Integer quatity;

    public CartDTO() {
    }

    public CartDTO(String id, Integer quatity) {
        this.id = id;
        this.quatity = quatity;
    }
}
