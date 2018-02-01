package com.service.impl;

import com.DTO.CartDTO;
import com.Exception.ProductException;
import com.Exception.SellException;
import com.Utils.ResultUtils;
import com.Utils.redislock.RedisLock;
import com.VO.ProductInfoVO;
import com.VO.ProductVO;
import com.alibaba.fastjson.JSON;
import com.dataoObject.ProductCategory;
import com.dataoObject.ProductInfo;
import com.domain.ProductInfoEnum;
import com.domain.Results;
import com.enums.ResultEnum;
import com.repository.CategoryDao;
import com.repository.OrderMasterDao;
import com.repository.ProductInfoDao;
import com.service.OrderMasterService;
import com.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wudi
 * @Description:  商品逻辑层
 * @date 21:23  2017/12/21
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    //成员变量
    static HashMap<String,Object> products ;
    static HashMap<String,Object> stock;
    static HashMap<String,Object> orders;
    //设置一个静态常量,超时设置为10s
    private final static Long  TIMEOUT=10*1000L;
    //静态代码块
    static{
        products = new  HashMap<>();
        stock = new  HashMap<>();
        orders = new  HashMap<>();
        products.put("123456",10000);
        stock.put("123456",10000);
    }

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private  OrderMasterService  orderMasterService;

    @Autowired
    private RedisLock redisLock;

    @Override
    public ProductInfo findOne(String id) {
        log.info("【获取数据id】"+id);

        return productInfoDao.findOne(id);

    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        log.info("【保存数据】"+productInfo.toString());

        return productInfoDao.save(productInfo);

    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        log.info("【数据打印】"+pageable);

        return productInfoDao.findAll(pageable);

    }

    @Override
    public List<ProductInfo> findAllByCategoryTypeIn(List<Integer> type) {
        log.info("【数据打印】"+type);

        return productInfoDao.findAllByCategoryTypeIn(type);

    }

    @Override
    public List<ProductInfo> findAllByStatus(Integer status) {
        log.info("【数据打印上架下架】"+status);

        return productInfoDao.findAllByStatus(status);

    }

    @Override
    public List<ProductInfo> findUpByStatus() {
        log.info("【数据打印上架】");


        return productInfoDao.findAllByStatus(ProductInfoEnum.UP.getCode());

    }

    @Override
    public List<ProductInfo> findDownByStatus(Integer status) {
        log.info("【数据打印上架下架】"+status);

        return productInfoDao.findAllByStatus(ProductInfoEnum.DOWN.getCode());

    }

    @Override
    public Results getProductList() {
        log.info("【查询所有商品数据】");

        //查询所有的上架商品
        List<ProductInfo> productInfoList = findUpByStatus();

        //通过jdk8.0lambda
        List<Integer> collect = productInfoList.stream()
                                .map(e -> e.getCategoryType())
                                .collect(Collectors.toList());

        //通过获取category类目
        List<ProductCategory> categoryList = categoryDao.findAllByCategoryTypeIn(collect);

        //TODO 类目数组
        ArrayList<ProductVO> arraylist = new ArrayList<ProductVO>();

        for (ProductCategory category: categoryList ) {
            //一级嵌套
            ProductVO vo = new ProductVO();

            BeanUtils.copyProperties(category, vo);

            ArrayList<ProductInfoVO> infoVoList = new ArrayList<ProductInfoVO>();
            for (ProductInfo info : productInfoList) {

                //TODO 商品的数组
                if (vo.getCategoryType().equals(info.getCategoryType())) {
                    //二级嵌套
                    ProductInfoVO infoVo = new ProductInfoVO();

                    BeanUtils.copyProperties(info, infoVo);

                    infoVoList.add(infoVo);

                }
                vo.setProductInfoVOList(infoVoList);

            }
            arraylist.add(vo);

        }

        return ResultUtils.Success(arraylist);
    }
    @Transactional
    @Override
    public void addProductStock( List<CartDTO> cartDTOs) {
                log.info("【增加库存数据】"+ JSON.toJSONString(cartDTOs));

                if (cartDTOs ==null || cartDTOs.size() ==0){
                    throw new ProductException(ResultEnum.RESULT_NOTEXIST);
                }
                //TODO 增加商品库存
                for (CartDTO dto: cartDTOs){
                    ProductInfo one = productInfoDao.getOne(dto.getId());
                    if (one == null){
                        throw  new SellException(ResultEnum.PARAM_NOT_EXIST);
                    }
                    Integer quatity = dto.getQuatity();
                    Integer stockNumber = one.getProductStock() + quatity;
                    if (stockNumber<0){

                        throw new SellException(ResultEnum.PRODUCT_STOCK_LT);
                    }
                    one.setProductStock(stockNumber);
                    productInfoDao.save(one);

                }

    }

    @Transactional
    @Override
    public void cutProductStock( List<CartDTO> cartDTOs) {
        log.info("【减少库存数据打印】"+JSON.toJSONString(cartDTOs));

        for (CartDTO dto:cartDTOs) {
            //通过判断商品id商品
            ProductInfo one = productInfoDao.findOne(dto.getId());
            if (one == null){

                throw  new SellException(ResultEnum.PARAM_NOT_EXIST);
            }
            //判断下的订单是否超过库存
            int number = one.getProductStock() - dto.getQuatity();

            if (number<0){

                throw new SellException(ResultEnum.PRODUCT_STOCK_LT);
            }

               one.setProductStock(number);

            productInfoDao.save(one);

        }

    }
    /**商品上架*/
    @Override
    public ProductInfo onSale(String productId) {
        log.info("【商品上架初始化参数】productId={}",productId);

        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productId==null){
            throw new SellException(ResultEnum.RESULT_PRODUCT_NULL);
        }

        if (ProductInfoEnum.UP.equals(productInfo.getStatus())){
            throw new SellException(ResultEnum.PRODUCT_STATUS_NOTEQUAL);
        }
        //修改为下架状态
        productInfo.setStatus(ProductInfoEnum.UP.getCode());

        return productInfoDao.save(productInfo);

    }

    /**商品下架*/
    @Override
    public ProductInfo offSale(String productId) {
        log.info("【商品下架初始化参数】productId={}",productId);

        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productId==null){
            throw new SellException(ResultEnum.RESULT_PRODUCT_NULL);
        }

        if (ProductInfoEnum.DOWN.equals(productInfo.getStatus())){
            throw new SellException(ResultEnum.PRODUCT_STATUS_NOTEQUAL);
        }
        //修改为下架状态
        productInfo.setStatus(ProductInfoEnum.DOWN.getCode());

        return productInfoDao.save(productInfo);

    }

    /**通过秒杀获取商品*/
    @Override
    public  void cutProudct(String productId) {
        if (StringUtils.isEmpty(productId)){

            throw new SellException(1,"请添加商品id");
        }
        //TODO 添加锁
        long times = System.currentTimeMillis()+TIMEOUT;
        if(redisLock.lock(productId,String.valueOf(times))){

            throw new SellException(101,"添加锁失败");
        }
        //1:查询商品是否存在
        ProductInfo productInfoDaoOne = productInfoDao.findOne(productId);
        if (productInfoDaoOne == null){

            throw new SellException(2,"商品不存在");
        }
        //2:判断是否还有库存
        Integer productStock = productInfoDaoOne.getProductStock();
        if(productStock==0){

            throw new SellException(3,"库存为空");
        }
        //3:减少库存
        int stocks = productStock - 1;
        try{
            Thread.sleep(100);//暂停100ms
        }catch(Exception e){
            log.info("【秒杀减少库存】"+e);
        }
        //4:修改库存
        productInfoDaoOne.setProductStock(stocks);
        productInfoDao.save(productInfoDaoOne);
        stock.put(productId,stocks);

        //TODO 解锁
        redisLock.unlock(productId,String.valueOf(times));


    }

    @Override
    public String queryProductData(String productId) {
        return "商品限购："+products.get(productId)+
                "剩余商品："+stock.get(productId)+
                "购买人次："+orders.size();
    }

    @Override
    public void updateProductName(String productId, String prodcutName) {
        log.info("【通过商品id修改商品名productId={},productName={}】",productId,prodcutName);
        //1：查询商品
        ProductInfo productInfoDaoOne = productInfoDao.findOne(productId);
        //2: 保存商品
        productInfoDaoOne.setProductName(prodcutName);

        productInfoDao.save(productInfoDaoOne);

    }
}
