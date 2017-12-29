package com.yuan.module.qiniu.service;

import com.yuan.module.qiniu.pojo.Qiniu;
import com.yuan.module.qiniu.pojo.QiniuToken;
import com.yuan.module.qiniu.properties.QiniuProperties;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author joryun ON 2017/12/29.
 */
@Service
public class QiniuService {

    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);

    @Autowired
    private QiniuProperties qiniuProperties;

    /**
     * @return Token
     */
    public String getToken() {
        //密钥配置
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        //获取token并返回
        return auth.uploadToken(qiniuProperties.getBucket());
    }

    /**
     * @param key 上传指定key，Default null
     * @return Token
     */
    public QiniuToken getToken(String key) {
        //密钥配置
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        //获取token并返回
        String token = auth.uploadToken(qiniuProperties.getBucket(), key);
        return new QiniuToken(token);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return 文件地址
     */
    public String upload(MultipartFile file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            //调用put方法上传
            byte[] fileBytes = file.getBytes();
            Response res = uploadManager.put(fileBytes, null, getToken());
            Qiniu qiniu = res.jsonToObject(Qiniu.class);
            return qiniuProperties.getLinkAddress() + qiniu.getHash();
        } catch (IOException e) {
            logger.error(" upload qi niu error : {}", e);
        }
        return null;
    }

    /**
     * 上传文件，流方式
     *
     * @param inputStream
     * @return
     */
    public String uploadByStream(InputStream inputStream) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            //调用put方法上传
            byte[] byteData = IOUtils.toByteArray(inputStream);
            Response res = uploadManager.put(byteData, null, getToken());
            Qiniu qiniu = res.jsonToObject(Qiniu.class);
            return qiniuProperties.getLinkAddress() + qiniu.getHash();
        } catch (IOException e) {
            logger.error(" upload qi niu error : {}", e);
        }
        return null;
    }

    /**
     * 上传文件，字节方式
     *
     * @param byteData
     * @return
     */
    public String uploadByByte(byte[] byteData) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //创建上传对象
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            //调用put方法上传
            Response res = uploadManager.put(byteData, null, getToken());
            Qiniu qiniu = res.jsonToObject(Qiniu.class);
            return qiniuProperties.getLinkAddress() + qiniu.getHash();
        } catch (IOException e) {
            logger.error(" upload qi niu error : {}", e);
        }
        return null;
    }

}
