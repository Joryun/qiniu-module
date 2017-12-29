package com.yuan.module.qiniu.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author joryun ON 2017/12/29.
 */
@Data
public class QiniuMany {

    @ApiModelProperty("多个文件地址")
    private List<String> fileURLList;

}