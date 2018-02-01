package com.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wudi
 * @Description:
 * @date 23:05  2017/12/21
 */
@Data
public class Results<T> implements Serializable{

    /**序列化*/
    private static final long serialVersionUID = -7817043265852783645L;

    private Integer code;

    private String msg;

    private T data;

}
