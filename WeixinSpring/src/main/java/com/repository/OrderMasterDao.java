package com.repository;

import com.dataoObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Wudi
 * @Description:
 * @date 14:32  2017/12/28
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    //通过唯一标识openId获取数据,分页
    Page<OrderMaster> findAllByBuyerOpenid(String openid, Pageable pageable);


}
