package com.yuan.module.qiniu;

import com.yuan.module.qiniu.service.QiniuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiniuServiceImplTest {

    @Autowired
    private QiniuService qiniuService;

    @Test
    public void testUpload() {

    }

}
