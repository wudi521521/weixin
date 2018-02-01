package com.repository;

import com.dataoObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wudi
 * @Description:
 * @date 13:14  2018/1/18
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    /**
     * 保存用户的实体
     * @param sellerInfo
     * @return
     */
    SellerInfo save(SellerInfo sellerInfo);

    /**
     * 通过openid查询卖家实体
     * @param openid 开放平平台的的唯一标识
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
