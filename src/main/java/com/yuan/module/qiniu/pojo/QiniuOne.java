package com.yuan.module.qiniu.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/12/29.
 */
@Data
public class QiniuOne {

    @ApiModelProperty("单个文件地址")
    private String fileURL;

}
