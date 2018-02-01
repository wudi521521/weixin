package com.service.impl;

import com.Exception.SellException;
import com.dataoObject.SellerInfo;
import com.enums.ResultEnum;
import com.repository.SellerInfoDao;
import com.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Wudi
 * @Description: 卖家service实现层
 * @date 13:22  2018/1/18
 */
@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        log.info("【卖家通过openid查询数据初始化参数openid={}】",openid);

        if (StringUtils.isEmpty(openid)){
            throw new SellException(ResultEnum.PARAM_NOT_EXIST);
        }
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid(openid);
        if (sellerInfo == null){
            throw new SellException(ResultEnum.USER_NOT_LOGIN);
        }

        return sellerInfo;
    }

    @Override
    public SellerInfo saveSelleInfo(SellerInfo sellerInfo) {
        log.info("【卖家保存数据初始化参数sellerInfo={}】",sellerInfo);

        return sellerInfoDao.save(sellerInfo);
    }
}
