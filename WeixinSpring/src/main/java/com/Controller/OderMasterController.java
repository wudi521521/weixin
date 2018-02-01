package com.Controller;

import com.Converters.OrderForm2OrderMasterDTO;
import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.Utils.ResultUtils;
import com.domain.Results;
import com.enums.ResultEnum;
import com.form.OrderForm;
import com.service.BuyerService;
import com.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description:订单controller层
 * @date 23:22  2018/1/2
 */
@RestController()
@RequestMapping("/api/ordermaster")
@Slf4j
public class OderMasterController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;


    /**
     * 创建订单
     * @param orderForm   效验参数实体
     * @param bindingResult  返回结果
     * @return 订单id
     */
    @PostMapping("/create")
    public Results<List<Map<String,String>>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
              log.info("【创建订单数据打印】"+orderForm.toString());

             if (bindingResult.hasErrors()){//有错误为true
                  throw new SellException(1,bindingResult.getFieldError().getDefaultMessage());

              }

               //TODO 调用GJson把orderForm转化为OrderMasterDTO
                OrderMasterDTO converters = OrderForm2OrderMasterDTO.converters(orderForm);
                OrderMasterDTO order = orderMasterService.createOrder(converters);
                Map map = new HashMap();
                   map.put("productId",order.getOrderId());

        return ResultUtils.Success(map);
    }

    /**
     * 通过openid   获取订单列表
     * @param openid  用户登录的唯一标识
     * @param page   当前页
     * @param size  当前页数据
     * @return
     */
    @GetMapping("/list")
    public Results<List<OrderMasterDTO>> findAllByBuyerOpenid(@RequestParam("openid") String openid,
                                                              @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size",defaultValue = "10") Integer size){
                log.info("【通过openid获取数据参数打印】"+openid);

                if (StringUtils.isEmpty(openid)){

                    throw new SellException(ResultEnum.OPENDID_NOT_NULL);
                }

                PageRequest pageRequest = new PageRequest(page, size);

                Page<OrderMasterDTO> allByBuyerOpenid = orderMasterService.findAllByBuyerOpenid(openid, pageRequest);
                List<OrderMasterDTO> content = allByBuyerOpenid.getContent();

        return ResultUtils.Success(content);
    }

    /***
     * 买家   查询订单详情
     * @param openid    唯一标识
     * @param orderid   订单id
     * @return
     */
    @GetMapping("/detail")
    public Results<OrderMasterDTO> findOrderDetail(@RequestParam("openid") String openid,
                                                   @RequestParam("orderid") String orderid){
        log.info("【订单查询打印数据】"+openid+":orderid"+orderid);

        OrderMasterDTO orderMasterOne = buyerService.findOrderMasterOne(openid, orderid);

        return ResultUtils.Success(orderMasterOne);
    }

    /***
     * 买家  取消订单
     * @param openid  唯一标识
     * @param orderid 订单id
     * @return
     */
    @PostMapping("/cancle")
    public Results<OrderMasterDTO> cancleOrderMasterStatus(@RequestParam("openid") String openid,
                                                           @RequestParam("orderid") String orderid){
        log.info("【取消订单数据打印】,openid{},orderid{}",openid,orderid);

        OrderMasterDTO orderMasterDTO = buyerService.cancelOrderMaster(openid, orderid);

        return ResultUtils.Success(orderMasterDTO);
    }

}
