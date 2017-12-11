package com.it.crm.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-08 20:46
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {

    private long total;
    private List<?> rows;

}
