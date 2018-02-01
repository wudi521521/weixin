package com.service.impl;

import com.Utils.KeyUtils;
import com.dataoObject.SellerInfo;
import com.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Wudi
 * @Description:
 * @date 13:32  2018/1/18
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo sellerInfoByOpenid = sellerService.findSellerInfoByOpenid("1235467");

        Assert.assertNotNull(sellerInfoByOpenid);

    }

    @Test
    public void saveSelleInfo() throws Exception {
        SellerInfo info = new SellerInfo();
        info.setSellerId(KeyUtils.getUniqueKey());
        info.setUsername("wudi");
        info.setOpenid("1235467");
        info.setPassword("2345678");
        SellerInfo sellerInfo = sellerService.saveSelleInfo(info);
        Assert.assertNotNull(sellerInfo);
    }

}