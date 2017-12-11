package com.it.crm.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-10 01:31
 */
public class JSONUtil {

    public static ObjectMapper mapper = new ObjectMapper();


    /**
     * 将对象转为数组
     * @return sting
     */
    public static String getJson(Object o) throws JsonProcessingException {


        //忽略null字段
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

       return  mapper.writeValueAsString(o);
    }

}
