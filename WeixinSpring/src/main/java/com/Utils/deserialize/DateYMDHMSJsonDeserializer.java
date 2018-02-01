package com.Utils.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author Wudi
 * @Description: 日期转换 - "yyyy-MM-dd HH:mm:ss"
 * @date 10:42  2018/1/3
 */
public class DateYMDHMSJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
       /* try {
            return DateUtil.formatStringToDate(jsonParser.getText(), DateUtil.DATE_FORMAT_TIME_T);
        } catch (BusinessException e) {
            return new Date(jsonParser.getLongValue());
        }*/
       return null;

}
}
