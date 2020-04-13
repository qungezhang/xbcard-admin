package cn.stylefeng.guns.core.qiniu.impl;

import cn.stylefeng.guns.config.properties.QiniuProperties;
import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Service
@EnableConfigurationProperties(QiniuProperties.class)
public class QiniuServiceImpl implements QiniuService, InitializingBean {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiniuProperties qiniuProperties;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    // 定义七牛云上传的相关策略
    private StringMap putPolicy;


    /**
     * 以文件的形式上传
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    @Override
    public String uploadFile(File file, String key) throws QiniuException {
        Response response = this.uploadManager.put(file, key, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, key, getUploadToken());
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String return_path = qiniuProperties.getPrefix() + "/" + putRet.key;
        log.info("文件名称={}", return_path);
        return return_path;
    }

    /**
     * 以流的形式上传
     *
     * @param inputStream
     * @return
     * @throws QiniuException
     */
    @Override
    public String uploadFile(InputStream inputStream, String key) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String return_path = qiniuProperties.getPrefix() + "/" + putRet.key;
        log.info("文件名称={}", return_path);
        return return_path;
    }

    /**
     * 删除七牛云上的相关文件
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(qiniuProperties.getBucket(), key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(qiniuProperties.getBucket(), key);
        }
        return response;
    }

    @Override
    public void deleteOldQiniuByUrl(String oldUrl, String newUrl) throws QiniuException {
        Map<String, String> oldDomainAndKey = StringUtil.getDomainAndKeyByUrl(oldUrl);
        if (ToolUtil.isNotEmpty(oldDomainAndKey)) {
            final String qiniuDomain = "img.xbdzmp.com";//自己的七牛域名
            String oldDomain = oldDomainAndKey.get("domain");
            String oldKey = oldDomainAndKey.get("qiNiuKey");
            Map<String, String> newDomainAndKey = StringUtil.getDomainAndKeyByUrl(newUrl);
            if (oldDomain.equals(qiniuDomain) && ToolUtil.isNotEmpty(newDomainAndKey) && !oldKey.equals(newDomainAndKey.get("qiNiuKey"))) {
                delete(oldKey);
            }
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
        // 自定义文件名字
        // putPolicy.put("saveKey", UUID.randomUUID().timestamp());
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiniuProperties.getBucket(), null, 3600, putPolicy);
    }
}
