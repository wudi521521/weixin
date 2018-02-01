package com.service.impl;

import com.Converters.OrderMaster2OrderMasterDTO;
import com.DTO.CartDTO;
import com.DTO.OrderMasterDTO;
import com.Exception.SellException;
import com.Utils.KeyUtils;
import com.dataoObject.OrderDetail;
import com.dataoObject.OrderMaster;
import com.dataoObject.ProductInfo;
import com.enums.OrderMasterEnum;
import com.enums.PayStatusEnum;
import com.enums.ResultEnum;
import com.repository.OrderDetailDao;
import com.repository.OrderMasterDao;
import com.repository.ProductInfoDao;
import com.service.OrderMasterService;
import com.service.PayService;
import com.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.enums.ResultEnum.RESULT_NOTEXIST;

/**
 * @author Wudi
 * @Description:
 * @date 14:59  2017/12/28
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PayService payService;

    /**通过openId来获取订单数据列表*/
    @Override
    public Page<OrderMasterDTO> findAllByBuyerOpenid(String openid, Pageable pageable) {
                    log.info("【参数openid】"+openid);

                    Page<OrderMaster> allByBuyerOpenid = orderMasterDao.findAllByBuyerOpenid(openid, pageable);
                    List<OrderMasterDTO> converters = OrderMaster2OrderMasterDTO.converters(allByBuyerOpenid.getContent());
                    PageImpl<OrderMasterDTO> orderMasterDTOs = new PageImpl<>(converters, pageable, allByBuyerOpenid.getTotalElements());

        return orderMasterDTOs;
    }

    /**通过订单id获取数据*/
    @Override
    public List<OrderMaster> findAllByid(String id) {
        log.info("【订单id】"+id);

        return null;
    }

    /**创建订单*/
    @Transactional//出现错误事物就回滚
    public OrderMasterDTO createOrder(OrderMasterDTO dto) {
         log.info("【创建订单时数据打印】"+ dto.toString());

                //唯一id主订单id
                String uniqueKey = KeyUtils.getUniqueKey();
                //给总价赋值
                BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

                 //TODO 1:查询商品(数量，价格)
                 for (OrderDetail detail:dto.getOrderDetailList()){

                     /**商品价格.通过商品id获取价格判断，判断商品价格和传递过来的价格是否一致*/
                     ProductInfo product = productInfoDao.findOne(detail.getProductId());

                     if (product == null){

                         throw new SellException(RESULT_NOTEXIST);

                     }
                         /** 计算订单总价，通过下单的数量 */
                         orderAmount = product.getProductPrice()
                                 .multiply(new BigDecimal(detail.getProductQuantity()))
                                 .add(orderAmount);

                         //TODO 3:订单详情入库(OrderMaster和orderDetail)每一个商品都需要创建要给订单详情
                         OrderDetail orderDetail = new OrderDetail();
                         BeanUtils.copyProperties(product,orderDetail);
                         orderDetail.setDetailId(KeyUtils.getUniqueKey());//详情订单id
                         orderDetail.setProductId(product.getProductId());//商品id
                         orderDetail.setOrderId(uniqueKey);//订单id
                         orderDetail.setProductQuantity(detail.getProductQuantity());//购买商品的数量

                         orderDetailDao.save(orderDetail);
                 }
                          dto.setBuyerAmount(orderAmount);
                          dto.setOrderId(uniqueKey);
                          //TODO 4:创建主订单
                          OrderMaster master = new OrderMaster();
                          BeanUtils.copyProperties(dto,master);
                          //订单id
                          master.setOrderId(uniqueKey);
                          //下单创建新订单
                          master.setBuyerStatus(OrderMasterEnum.MASTER_NEW.getCode());
                          //待支付,待支付是2
                          master.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());
                          //订单号
                          master.setOrderId(uniqueKey);

                          orderMasterDao.save(master);

                          //TODO 扣除库存,jdk 8.0,先转化为流，主要是购物车的数据
                          List<CartDTO> collect = dto.getOrderDetailList().stream()
                                        .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                                        .collect(Collectors.toList());

                          productInfoService.cutProductStock(collect);
                          //发送webSocket消息


        return dto;
    }

    /**查询单个订单*/
    @Override
    public OrderMasterDTO findOne(String orderId) {
                log.info("【查询单个订单参数】"+orderId);

                    OrderMasterDTO dto = new OrderMasterDTO();

                    OrderMaster one = orderMasterDao.findOne(orderId);

                    if (StringUtils.isEmpty(one)){

                        throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
                    }

                    List<OrderDetail> allByOrderId = orderDetailDao.findAllByOrderId(orderId);

                    if (CollectionUtils.isEmpty(allByOrderId)){

                        throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
                    }

                           BeanUtils.copyProperties(one,dto);
                           dto.setOrderDetailList(allByOrderId);

        return dto;
    }

    /**取消订单*/
    @Override
    @Transactional //取消订单，
    public OrderMasterDTO cancelOrder(OrderMasterDTO dto) {
                    log.info("【取消订单数据】"+dto.toString());

                    if (StringUtils.isEmpty(dto)){

                        throw new SellException(ResultEnum.PARAM_NOT_EXIST);
                    }

                    //TODO 1:判断订单的是否存在，只有订单为结束的状态,才可以取消订单,修改商品订单下单状态
                    OrderMaster orderMasterDaoOne = orderMasterDao.findOne(dto.getOrderId()==null?"0":dto.getOrderId());

                    if (StringUtils.isEmpty(orderMasterDaoOne)){

                        throw new SellException(ResultEnum.ORDER_NOT_EXIST);
                    }
                    if (orderMasterDaoOne.getBuyerStatus().equals(OrderMasterEnum.MASTER_CANCLE.getCode())){

                        throw new SellException(ResultEnum.ORDER_MASTER_CANCLE);
                    }
                    orderMasterDaoOne.setBuyerStatus(OrderMasterEnum.MASTER_CANCLE.getCode());
                    OrderMaster save = orderMasterDao.save(orderMasterDaoOne);
                    if (StringUtils.isEmpty(save)){

                        throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
                    }

                    //TODO 2:修改商品库存.添加库存
                    List<CartDTO> cartDTOList = dto.getOrderDetailList().stream()
                                                    .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                                                    .collect(Collectors.toList());
                    productInfoService.addProductStock(cartDTOList);

                    //TODO 3:如果已经支付需要进行退款
                   if (PayStatusEnum.PAY_SUCCESS.getCode().equals(dto.getPayStatus())){
                       payService.refund(dto);
                   }


      return dto;
    }

    /**结束订单*/
    @Override
    public OrderMasterDTO finishOrder(OrderMasterDTO dto) {
        log.info("【结束订单参数】"+dto.toString());

                //TODO 1:判断订单
                 if (StringUtils.isEmpty(dto)){
                     throw new SellException(ResultEnum.PARAM_NOT_EXIST);
                 }
                OrderMaster one = orderMasterDao.findOne(dto.getOrderId() == null ? "0" : dto.getOrderId());
                if (StringUtils.isEmpty(one)){

                    throw new SellException(ResultEnum.RESULT_NOTEXIST);
                }
                if (!OrderMasterEnum.MASTER_NEW.getCode().equals(one.getBuyerStatus())){

                    throw new SellException(ResultEnum.ORDER_MASTER_CANCLE);
                }
                //TODO 2:修改订单
                one.setBuyerStatus(OrderMasterEnum.MASTER_FINISHED.getCode());
                OrderMaster save = orderMasterDao.save(one);
                BeanUtils.copyProperties(save,dto);

                //TODO 3:微信推送

        return dto;
    }

    /**修改支付订单，支付订单*/
    @Override
    public OrderMasterDTO paidOrder(OrderMasterDTO dto) {
        log.info("【修改支付订单参数】"+dto.toString());

                //TODO 判断支付状态
                if(StringUtils.isEmpty(dto)){

                    throw new SellException(ResultEnum.PARAM_NOT_EXIST);
                }
                OrderMaster master = orderMasterDao.findOne(dto.getOrderId()==null?"0":dto.getOrderId());
                if(StringUtils.isEmpty(master)){

                    throw new SellException(ResultEnum.RESULT_NOTEXIST);
                }
                if (!PayStatusEnum.PAY_WAIT.getCode().equals(master.getPayStatus())){

                    throw new SellException(ResultEnum.PAY_STATUS_ERROR);
                }
                //TODO 修改支付状态
                master.setPayStatus(PayStatusEnum.PAY_SUCCESS.getCode());
                OrderMaster save = orderMasterDao.save(master);

        return dto;
    }

    /**查询所有的订单列表*/
    @Override
    public Page<OrderMasterDTO> findAll(Pageable pageable) {
        Page<OrderMaster> all = orderMasterDao.findAll(pageable);
        //详情订单转化为OrderMasterDTO
        List<OrderMasterDTO> converters = OrderMaster2OrderMasterDTO.converters(all.getContent());
        PageImpl<OrderMasterDTO> orderMasterDTOs = new PageImpl<>(converters, pageable, all.getTotalElements());

        return orderMasterDTOs;
    }

    /**修改订单*/
    @Override
    public OrderMasterDTO save(OrderMasterDTO dto) {
        log.info("dto{}",dto.toString());

                if (StringUtils.isEmpty(dto)){

                    throw new SellException(ResultEnum.PARAM_NOT_EXIST);
                }
                OrderMaster master = new OrderMaster();
                BeanUtils.copyProperties(dto,master);
                OrderMaster save = orderMasterDao.save(master);
                BeanUtils.copyProperties(save,dto);

        return dto;
    }


}
