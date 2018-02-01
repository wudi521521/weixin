package com.service.impl;

import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.enums.OrderMasterEnum;
import com.enums.ResultEnum;
import com.service.BuyerService;
import com.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wudi
 * @Description: 买家订单查询，取消
 * @date 12:12  2018/1/3
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    /**查询订单*/
    @Override
    public OrderMasterDTO findOrderMasterOne(String openid, String orderId) {
        log.info("openid{},orderId{}",openid,orderId);

                    OrderMasterDTO orderMasterDTO = checkOrderMasterDTO(openid, orderId);

        return orderMasterDTO;
    }

    /**买家取消订单*/
    @Override
    public OrderMasterDTO cancelOrderMaster(String openid, String orderId) {
        log.info("【买家取消订单数据打印】,openid{},orderId{}",openid,orderId);

                    OrderMasterDTO orderMasterDTO = checkOrderMasterDTO(openid, orderId);
                    if (orderMasterDTO == null){

                        throw new SellException(ResultEnum.RESULT_NOTEXIST);
                    }
                    //修改订单
                    orderMasterDTO.setBuyerStatus(OrderMasterEnum.MASTER_CANCLE.getCode());
                    OrderMasterDTO save = orderMasterService.save(orderMasterDTO);

        return save;
    }

    /**效验生成OrderMasterDTO*/
    private OrderMasterDTO checkOrderMasterDTO(String openid, String orderId){
            log.info("【效验生成OrderMasterDto】,openid{},orderId{}",openid,orderId);

                    if (StringUtils.isBlank(openid)){

                        throw new SellException(ResultEnum.OPENDID_NOT_NULL);
                    }else if(StringUtils.isBlank(orderId)){

                        throw new SellException(ResultEnum.ORDERID_NONE_NULL);
                    }
                    OrderMasterDTO one = orderMasterService.findOne(orderId);
                    if (one == null){
                        return null;
                    }

                    if (!openid.equals(one.getBuyerOpenid())){

                        throw new SellException(ResultEnum.PERSON_NONE_NULL);
                    }

        return one;
    }
}
