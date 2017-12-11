package com.it.crm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-07 21:49
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResultMessage {

    private Boolean success = false;
    private String message;


}
