package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.qiniu.http.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/qiniu")
@Api(tags = "七牛云")
public class QiniuController {

    @Autowired
    private QiniuService qiniuService;


    /**
     * 以流的形式上传图片
     *
     * @param file
     * @return 返回访问路径
     * @throws IOException
     */
    @PostMapping("upload")
    @ApiOperation("文件上传")
    public Object uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        SuccessResponseData successResponseData = new SuccessResponseData();
        String dateToStr = DateUtils.dateToStr(new Date(), "yyyyMMddhhmmss");
        String pictureName = dateToStr + UUID.randomUUID().toString().split("-")[4] + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
        successResponseData.setData(qiniuService.uploadFile(file.getInputStream(), pictureName));
        return successResponseData;
    }
    /**
     * 以流的形式上传图片
     *
     * @param files
     * @return 返回访问路径
     * @throws IOException
     */
    @PostMapping("uploadFiles")
    @ApiOperation("多张图片上传")
    public Object uploadFiles(@RequestParam(value = "files") MultipartFile[] files) throws IOException {
        SuccessResponseData successResponseData = new SuccessResponseData();
        List<String> list = new ArrayList<>();
        if (ToolUtil.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                String dateToStr = DateUtils.dateToStr(new Date(), "yyyyMMddhhmmss");
                String pictureName = dateToStr + UUID.randomUUID().toString().split("-")[4] + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
                list.add(qiniuService.uploadFile(file.getInputStream(), pictureName));
            }
        }
        successResponseData.setData(list);
        return successResponseData;
    }
    /**
     * 删除文件
     *
     * @param key
     * @return
     * @throws IOException
     */
    @GetMapping("delete/{key}")
    public Response deleteFile(@PathVariable String key) throws IOException {
        return qiniuService.delete(key);
    }
}
