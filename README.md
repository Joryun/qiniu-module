七牛云上传操作接口文档
------

### 一、上传交互方式(两种)

* 前端上传(推荐方式)

客户端在上传资源到七牛云前，要先从业务服务器获取一个有效的上传凭证Token，即请求相应项目的七牛云接口拿到Token，
接着使用Token调用前端上传七牛云的api，上传文件至七牛云，返回数据(得到图片URl，即可传给后台存储)。

![](https://odum9helk.qnssl.com/Fmy1Y_s9I4oCPYuMGDrvYxCRv2FM)

PS：如果果有设置回调，则上传完成时七牛云存储会自动发起回调到指定的业务服务器。

![](https://odum9helk.qnssl.com/FkPZ31ECmtGnEisOahMKc5kQkuRr)

* 后台上传

**单图上传** & **多图上传**

老规矩，后台项目会依赖七牛云模块，前端需请求相应后台接口，参数传File文件(当多图片时为数组)，后台将会直接上传文件至七牛云，并存储至数据库。

### 二、前端与七牛交互

待完善...

### 三、后台项目配置

* 一般不变配置：accessKey，secretKey

* 可变配置：bucket，key，expires，policy，strict

####项目依赖七牛方式：

##### 1. 依赖七牛云
```
		<dependency>
            <artifactId>qiniu</artifactId>
            <groupId>com.yuan.module</groupId>
            <version>1.0.0</version>
        </dependency>
```

注：随着七牛模块的迭代，version可能会有所不同，切记使用最新版本，可能接口会有所优化完善。

##### 2. 配置文件application.yml

覆盖accessKey，secretKey，linkAddress，bucket。

按如下配置格式配置，填充相关信息即可。

```
joryun:
  module:
    qiniu:
      bucket: xxx
      access-key: xxx
      secret-key: xxx
      link-address: xxx
```


```
注意：linkAddress为默认外链域名，项目需要配置，否则无法访问。(不同的bucket对应的外链也不同)
```


### 四、返回token的情况分析

#####  1.提供空间名bucket，其余默认

```
String getToken(String bucket)
```

#####  2. 提供空间名bucket，上传指定key，其余默认

```
String getToken(String bucket, String key)
```

#####  3. 提供空间名bucket，上传指定key，有效时长expires，上传策略policy([详见官方SDK上传策略](https://developer.qiniu.com/kodo/manual/1206/put-policy))

```
String getToken(String bucket, String key, long expires, StringMap policy)
```

##### 4. 提供空间名bucket，上传指定key，有效时长expires，上传策略policy，是否去除非限定的策略字段strict

```
String getToken(String bucket, String key, long expires, StringMap policy, boolean strict)
```

### 五、接口调用

##### 1. 前端上传(推荐方式)

* 返回Token给前端

```
http://server_name:port/api/module/qiniu/token
```

* 调用前端与七牛交互的API，带着Token凭证上传

##### 2. 后端上传

* 单图上传

```
http://server_name:port/api/module/qiniu/one
```

Param：**MultipartFile file** （单个file）

* 多图上传

```
http://server_name:port/api/module/qiniu/many
```

Param：**MultipartFile[] files** （file数组）

* 后端文件上传，流方式

```
http://server_name:port/api/module/qiniu/stream
```

* 后端文件上传，字节方式

```
http://server_name:port/api/module/qiniu/byte
```


