package com.service;

import com.dataoObject.SellerInfo;

/**
 * @author Wudi
 * @Description: 卖家端service
 * @date 13:18  2018/1/18
 */
public interface SellerService {

    /**
     * 通过openid查询SellerInfo实体
     * @param openid 开放平台唯一标识
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    /**
     * 保存SellerInfo实体
     * @param sellerInfo
     * @return
     */
    SellerInfo saveSelleInfo(SellerInfo sellerInfo);
}
