package com.Utils;

import com.domain.Results;
import com.enums.ResultEnum;
import lombok.Data;


/**
 * @author Wudi
 * @Description: 返回参数工具类
 * @date 11:58  2017/12/22
 */
@Data
public class ResultUtils {

    private Integer code;

    private String msg ;

    private Object data;

    private Boolean flate;

    /**
     * 成功的时候保存不用传递数据
     * @return
     */
    public static Results Success(){

        Results results = new Results();
        results.setCode(0);
        results.setMsg("成功");

        return results;
    }

    /**
     * 保存的时候传递数据
     * @param data
     * @return
     */
    public static Results Success(Object data){

        Results results = new Results();
        results.setCode(0);
        results.setMsg("成功");
        results.setData(data);

        return results;
    }

    /**
     * 数据有错误时返回的数据
     * @return
     */
    public static Results Error(){
        Results results = new Results();
        results.setCode(1);
        results.setMsg("失败");

        return results;
    }

    /**
     * 数据有错误时返回的数据
     * @return
     */
    public static Results Error(ResultEnum enums){
        Results results = new Results();
        results.setCode(enums.getCode());
        results.setMsg(enums.getMsg());

        return results;
    }

    /***
     * 数据错误时返回的数据
     * @param code
     * @param msg
     * @return
     */
    public static Results Error(Integer code,String msg){

        Results results = new Results();
        results.setCode(code);
        results.setMsg(msg);

        return results;
    }
}
