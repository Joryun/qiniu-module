package com.yuan.module.qiniu.controller;

import com.yuan.module.qiniu.pojo.QiniuToken;
import com.yuan.module.qiniu.pojo.QiniuMany;
import com.yuan.module.qiniu.pojo.QiniuOne;
import com.yuan.module.qiniu.service.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joryun ON 2017/12/29.
 */
@RestController
@Api(tags = "七牛模块")
@RequestMapping("/api/module/qiniu")
public class QiniuController {

    @Resource
    private QiniuService qiniuService;

    /**
     * 返回Token给前端上传
     *
     * @param key
     * @return Token
     */
    @GetMapping("token")
    @ApiOperation("前端上传: 返回Token")
    public QiniuToken getToken(@RequestParam(required = false) String key) {
        return qiniuService.getToken(key);
    }

    /**
     * 后端上传
     *
     * @param file
     * @return 单文件地址
     */
    @PostMapping("one")
    @ApiOperation("后台上传: 单文件")
    public QiniuOne uploadOne(@NotNull MultipartFile file) {
        QiniuOne uploadOneVO = new QiniuOne();
        uploadOneVO.setFileURL(qiniuService.upload(file));
        return uploadOneVO;
    }

    /**
     * 后端批量上传
     *
     * @param files
     * @return 多文件地址集合
     */
    @PostMapping("many")
    @ApiOperation("后台上传: 多文件")
    public QiniuMany uploadMany(@NotEmpty MultipartFile[] files) {
        QiniuMany uploadManyVO = new QiniuMany();
        List<String> fileURLList = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            String fileURL = qiniuService.upload(file);
            fileURLList.add(fileURL);
        }
        uploadManyVO.setFileURLList(fileURLList);
        return uploadManyVO;
    }

    /**
     * 后端文件上传，流方式
     *
     * @param inputStream
     * @return
     */
    @PostMapping("stream")
    @ApiOperation("后台上传: 流上传")
    public QiniuOne uploadByStream(InputStream inputStream) {
        QiniuOne uploadOneVO = new QiniuOne();
        uploadOneVO.setFileURL(qiniuService.uploadByStream(inputStream));
        return uploadOneVO;
    }

    /**
     * 后端文件上传，字节方式
     *
     * @param byteData
     * @return
     */
    @PostMapping("byte")
    @ApiOperation("后台上传: 字节上传")
    public QiniuOne uploadByByte(byte[] byteData) {
        QiniuOne uploadOneVO = new QiniuOne();
        uploadOneVO.setFileURL(qiniuService.uploadByByte(byteData));
        return uploadOneVO;
    }

}
