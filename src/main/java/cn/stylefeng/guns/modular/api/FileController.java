//package cn.stylefeng.guns.modular.api;
//
//import cn.stylefeng.guns.config.properties.GunsProperties;
//import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
//import cn.stylefeng.roses.core.base.controller.BaseController;
//import cn.stylefeng.roses.core.reqres.response.ResponseData;
//import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
//import cn.stylefeng.roses.core.util.FileUtil;
//import cn.stylefeng.roses.core.util.ToolUtil;
//import cn.stylefeng.roses.kernel.model.exception.ServiceException;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
///**
// * Description: xbcard-xbcard
// * Created by : qunge
// * Date : 2019/10/29 17:51
// */
//@RestController
//@RequestMapping("/api/file")
//@Api(tags = "文件")
//public class FileController extends BaseController{
//
//    @Autowired
//    private GunsProperties gunsProperties;
//
//
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
//
//    /**
//     * 返回图片
//     * @param pictureId 图片id
//     */
//    @RequestMapping("/read/{pictureId}")
//    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
//        String path = gunsProperties.getFileUploadPath() + pictureId;
//        try {
//            byte[] bytes = FileUtil.toByteArray(path);
//            response.getOutputStream().write(bytes);
//        } catch (Exception e) {
//            //如果找不到图片就返回一个默认图片
//            try {
//                response.sendRedirect("/static/img/girl.gif");
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//}
