package cn.stylefeng.guns.core.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

public interface QiniuService {

    String uploadFile(File file, String key) throws QiniuException;

    String uploadFile(InputStream inputStream, String key) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
