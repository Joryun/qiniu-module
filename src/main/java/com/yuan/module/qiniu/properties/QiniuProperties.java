package com.yuan.module.qiniu.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author joryun ON 2017/12/29.
 */
@Component
@ConfigurationProperties(prefix = "joryun.module.qiniu")
public class QiniuProperties {

    //七牛密钥对
    private String accessKey;
    private String secretKey;

    //七牛存储空间名
    private String bucket;

    //七牛存储外链域名
    private String linkAddress;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

}
