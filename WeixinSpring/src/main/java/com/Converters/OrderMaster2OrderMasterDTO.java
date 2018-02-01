package com.Converters;

import com.DTO.OrderMasterDTO;
import com.dataoObject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wudi
 * @Description: orderMaster转化为OrderMasterDTO
 * @date 16:02  2018/1/2
 */
@Slf4j
public class OrderMaster2OrderMasterDTO {

    /**orderMaster转化为OrderMasterDTO*/
    public static OrderMasterDTO converters(OrderMaster master){
                log.info("【converters数据打印】"+master.toString());

                OrderMasterDTO dto = new OrderMasterDTO();
                BeanUtils.copyProperties(master,dto);

            return dto;
    }

    /**List<OrderMaster>转化为List<OrderMasterDTO>*/
    public static List<OrderMasterDTO> converters(List<OrderMaster> masterList){
                log.info("【converters数据打印】");

                //TODO jdk1.8中特有的api
                List<OrderMasterDTO> collect = masterList.stream()
                        .map(e -> converters(e))
                        .collect(Collectors.toList());

           return collect;
    }


}
