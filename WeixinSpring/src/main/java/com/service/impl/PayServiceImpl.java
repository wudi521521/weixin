package com.service.impl;

import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.Utils.JsonUtils;
import com.Utils.MathUtils;
import com.enums.PayStatusEnum;
import com.enums.ResultEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.service.OrderMasterService;
import com.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wudi
 * @Description: 支付service层
 * @date 13:25  2018/1/8
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private  static final String ORDER_NAME="微信点餐";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderMasterService orderMasrerService;

    /**订单预支付*/
    @Override
    public PayResponse create(OrderMasterDTO orderMasterDTO) {
        log.info(orderMasterDTO.toString());

        /**订单发起支付的必须的参数*/
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderMasterDTO.getBuyerOpenid());//登录者的唯一id
        payRequest.setOrderAmount(orderMasterDTO.getBuyerAmount().doubleValue());//订单金额
        payRequest.setOrderId(orderMasterDTO.getOrderId());//订单id
        payRequest.setOrderName(orderMasterDTO.getBuyerName());//订单名
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);//支付方式

        log.info("【微信支付的参数】payRequest{}",payRequest);

        /**调用sdk返回参数*/
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付的的结果】payResponse{}",payResponse);

        return payResponse;

    }

    /**微信公众号支付回调，修改支付状态*/
    @Override
    public PayResponse returnUrl(String notifyData) {
        log.info("微信支付回调初始化的参数,notifyData{}",JsonUtils.toJson(notifyData));
        //1:验证延签
        //2:支付状态
        //3:支付金额
        //4:支付(下单人==支付人)一般金融行业会判断下单人和支付人是否是同一个人
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("微信支付回调转化的参数,payResponse={}", JsonUtils.toJson(payResponse));

        //判断订单是否存在
        OrderMasterDTO one = orderMasrerService.findOne(payResponse.getOrderId());
        if (one == null){
            log.info("微信支付回调订单号不存在"+payResponse.getOrderId());

            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否相等,都转化为BigDecimal进行判断(0.1与0.10)
        if(!MathUtils.equals(one.getBuyerAmount().doubleValue(),payResponse.getOrderAmount())){
            log.info("微信支付回调订单号不存在,订单金额={},回调订单金额={}",one.getBuyerAmount(),payResponse.getOrderId());

            throw new SellException(ResultEnum.ODER_AMOUNT_NOTEQUAL);
        }
        //修改订单的支付状态
        one.setPayStatus(PayStatusEnum.PAY_SUCCESS.getCode());

            try {
                     OrderMasterDTO save = orderMasrerService.save(one);
            } catch (Exception e){
                     log.error("微信公众号异步支付回调数据异常:"+e);

                     throw new SellException(ResultEnum.RESULT_ERROR);
            }

        return payResponse;
    }

    /**微信公众号退款*/
    @Override
    public RefundResponse refund(OrderMasterDTO orderMasterDTO) {
        //使用第三方的sdk
        RefundRequest refundRequest = new RefundRequest();

        //需要的参数订单orderId，退款金额,支付类型
        refundRequest.setOrderId(orderMasterDTO.getOrderId());
        refundRequest.setOrderAmount(orderMasterDTO.getBuyerAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款初始化参数】"+JsonUtils.toJson(refundRequest));

        RefundResponse refund = bestPayService.refund(refundRequest);
        log.info("【微信退款返回参数】"+JsonUtils.toJson(refund));

        return refund;
    }
}
