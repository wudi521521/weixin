package com.Controller;

import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.enums.ResultEnum;
import com.lly835.bestpay.model.PayResponse;
import com.service.OrderMasterService;
import com.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Wudi
 * @Description: 支付
 * @date 13:16  2018/1/8
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    /**发起支付预订单*/
    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map){
        //1：查询订单
        OrderMasterDTO one = orderMasterService.findOne(orderId);
        if (one == null){
            throw new SellException(ResultEnum.RESULT_NOTEXIST);
        }
        //2:发起支付
        PayResponse payResponse = payService.create(one);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        //ModelAndView中前面的是页面的路径后面的时参数
        return new ModelAndView("pay/create",map);
    }

    /**
     *<xml>
     <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     <attach><![CDATA[支付测试]]></attach>
     <bank_type><![CDATA[CFT]]></bank_type>
     <fee_type><![CDATA[CNY]]></fee_type>
     <is_subscribe><![CDATA[Y]]></is_subscribe>
     <mch_id><![CDATA[10000100]]></mch_id>
     <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
     <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
     <out_trade_no><![CDATA[1409811653]]></out_trade_no>
     <result_code><![CDATA[SUCCESS]]></result_code>
     <return_code><![CDATA[SUCCESS]]></return_code>
     <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
     <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
     <time_end><![CDATA[20140903131540]]></time_end>
     <total_fee>1</total_fee>
     <coupon_fee><![CDATA[10]]></coupon_fee>
     <coupon_count><![CDATA[1]]></coupon_count>
     <coupon_type><![CDATA[CASH]]></coupon_type>
     <coupon_id><![CDATA[10000]]></coupon_id>
     <coupon_fee><![CDATA[100]]></coupon_fee>
     <trade_type><![CDATA[JSAPI]]></trade_type>
     <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
     </xml>
     * */
    /**
     * 异步通知
     * @param notifyData application/xml 格式 参数
     */
    @RequestMapping(value="/notify",method = RequestMethod.POST)
    public ModelAndView returnUrl(@RequestBody  String  notifyData){
        log.info("微信支付回调的的参数controller层："+notifyData);

         payService.returnUrl(notifyData);

        /**
         * <xml>

         <return_code><![CDATA[SUCCESS]]></return_code>
         <return_msg><![CDATA[OK]]></return_msg>
         </xml>
         */
      return new ModelAndView("pay/success");
    }
}
