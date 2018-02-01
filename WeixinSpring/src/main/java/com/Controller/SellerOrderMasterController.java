package com.Controller;

import com.DTO.OrderMasterDTO;
import com.enums.ResultEnum;
import com.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Wudi
 * @Description: 卖家订单后台
 * @date 18:20  2018/1/15
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderMasterController {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 卖家后台订单的展示数据
     * @param page  当前页
     * @param size  当前页面展示数据的总数
     * @param map
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView findOrderList(@RequestParam(value ="page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size,
                                      Map<String,Object> map){

                PageRequest pageRequest = new PageRequest(page - 1, size);
                Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findAll(pageRequest);
                map.put("orderMasterDTOPage", orderMasterDTOPage);     //当前数据
                map.put("contentPage",page);       //当前页
                map.put("contentSize",size);       //当前页的数据

        return new ModelAndView("order/seller_orderlist",map);
    }

    /**
     * 卖家后台取消订单
     * @param orderId  订单id
     * @return
     */
    @RequestMapping(value="/cancle",method = RequestMethod.GET)
    public ModelAndView cancelOrder(@RequestParam("orderId") String orderId,
                                    Map<String,Object> map){
             map.put("url","/sell/seller/order/list");
        try{
             OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
             OrderMasterDTO orderMasterDTO1 = orderMasterService.cancelOrder(orderMasterDTO);

        }catch (Exception e){
                log.info("卖家取消订单数据异常："+e);
                map.put("msg",e.getMessage());

            return new ModelAndView("common/error",map);
        }
            map.put("msg", ResultEnum.ORDER_CANCLE.getMsg());

        return new ModelAndView("common/success",map);
    }

    /**
     * 卖家后台完结订单
     * @param orderId  订单id
     * @return
     */
    @RequestMapping(value="/finish",method = RequestMethod.GET)
    public ModelAndView endOrder(@RequestParam("orderId") String orderId,
                                    Map<String,Object> map){
                map.put("url","/sell/seller/order/list");

        try{
                OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
                OrderMasterDTO orderMasterDTO1 = orderMasterService.finishOrder(orderMasterDTO);

        }catch(Exception e){
                    log.error("卖家后台完结订单数据异常"+e);
                    map.put("msg",e.getMessage());

                return new ModelAndView("common/error",map);
        }
            map.put("msg",ResultEnum.ORDER_FINISH);

        return new ModelAndView("common/success",map);

    }

    /**
     *    卖家 订单详情
     * @param orderId   订单id
     * @param map
     * @return
     */
    @RequestMapping(value="detail",method = RequestMethod.GET)
    public ModelAndView detailOrder(@RequestParam("orderId") String orderId,
                                    Map<String,Object> map){

        OrderMasterDTO dto = new OrderMasterDTO();
        try {
            dto = orderMasterService.findOne(orderId);

        }catch(Exception e){
            log.info("【获取卖家订单详情数据异常】"+e);
            map.put("url","/sell/seller/order/list");
            map.put("msg",e.getMessage());

            return new ModelAndView("common/error",map);
        }
        map.put("orderMasterDto",dto);
        return new ModelAndView("order/seller_orderdetail",map);

    }
}
