package com.Utils;

import com.enums.CodeEnum;

/**
 * @author Wudi
 * @Description: 判断code和Class中的code是否一致
 * @date 22:13  2018/1/15
 */
public class EnumUntil {

    public static <T extends CodeEnum> T getCodeEnum(Integer code,Class<T> enumClass ){

        for (T enums:enumClass.getEnumConstants()) {

            if (enums.getCode().equals(code)){

                return enums;
            }

        }

        return null;

    }
}
