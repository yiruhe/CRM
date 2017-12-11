package com.it.crm.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-08 19:53
 */
@Getter
@Setter
public class BaseQueryObject {

    private Integer rows = 5;

    private Integer page=1;

    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
}
