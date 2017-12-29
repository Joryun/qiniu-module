package com.yuan.module.qiniu.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/12/29.
 */
@Data
public class QiniuToken {

    @ApiModelProperty("七牛token")
    private String token;

    public QiniuToken(String token) {
        this.token = token;
    }
}