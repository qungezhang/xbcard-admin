package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.FileUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/10/29 17:51
 */
@RestController
@RequestMapping("/api")
@Api(tags = "文件")
public class FileController extends BaseController{

    @Autowired
    private GunsProperties gunsProperties;


//    /**
//     * 上传图片
//     */
//    @PostMapping("/upload")
//    @ApiOperation("文件上传")
//    public Object upload(@RequestPart("file") MultipartFile picture) {
//        SuccessResponseData successResponseData = new SuccessResponseData();
//        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
//        try {
//            String fileSavePath = gunsProperties.getFileUploadPath();
//            picture.transferTo(new File(fileSavePath + pictureName));
//        } catch (Exception e) {
//            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
//        }
//        successResponseData.setData(pictureName);
//        return successResponseData;
//    }

//    /**
//     * 返回图片
//     * @param pictureId 图片id
//     */
//    @RequestMapping("/file/{pictureId}")
//    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
//        String path = gunsProperties.getFileUploadPath() + pictureId;
//        try {
//            byte[] bytes = FileUtil.toByteArray(path);
//            response.getOutputStream().write(bytes);
//        } catch (Exception e) {
//            //如果找不到图片就返回一个默认图片
//            try {
//                response.sendRedirect("/static/img/null.png");
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }

    /**
     * 使用说明视频文件
     * @param
     */
    @GetMapping("/file/instructions")
    @ApiOperation("使用说明视频文件")
    public ResponseData instructions() {
        List<Map<String, Object>> list = new ArrayList<>(5);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("number", 1);
        map1.put("url", "https://.....");
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("number", 2);
        map2.put("url", "https://.....");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("number", 3);
        map3.put("url", "https://.....");
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("number", 4);
        map4.put("url", "https://.....");
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("number", 5);
        map5.put("url", "https://.....");
        list.add(map5);

        return new SuccessResponseData(list);
    }
}
