package com.Converters;

import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.dataoObject.OrderDetail;
import com.enums.ResultEnum;
import com.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wudi
 * @Description: OrderForm转化为orderMasterDTO
 * @date 9:17  2018/1/3
 */
@Slf4j
public class OrderForm2OrderMasterDTO {

    /**OrderForm转化为OrderMasterDTO*/
    public static OrderMasterDTO converters(OrderForm orderForm){
        log.info("【gson转化惊悚】"+orderForm.toString());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderMasterDTO dto = new OrderMasterDTO();
        dto.setBuyerName(orderForm.getName());
        dto.setBuyerPhone(orderForm.getPhone());
        dto.setBuyerAddress(orderForm.getAddress());
        dto.setBuyerOpenid(orderForm.getOpenid());

        //TODO 调用Gson将实体将String转化为Json
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());

        }catch (Exception e){
            log.error("【使用gson转化数据】"+e);

            throw new SellException(ResultEnum.ORDERFORM_ERROR);
        }

        dto.setOrderDetailList(orderDetailList);

        return dto;
    }
}
