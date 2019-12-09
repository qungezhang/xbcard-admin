package cn.stylefeng.guns.modular.api;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.stylefeng.guns.config.WxMaConfiguration;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.config.properties.WxMaProperties;
import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 微信小程序二维码
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/api/WxCode")
@Api(tags = "微信小程序二维码")
public class WxCodeUnilimitController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private GunsProperties gunsProperties;
    private String appid;
    @Autowired
    public WxCodeUnilimitController(WxMaProperties properties) {
        this.appid = properties.getConfigs().get(0).getAppid();
    }

     /**
     * 接口B: 获取小程序码（永久有效、数量暂无限制）.
     * <pre>
     * 通过该接口生成的小程序码，永久有效，数量暂无限制。
     * 用户扫描该码进入小程序后，将统一打开首页，开发者需在对应页面根据获取的码中 scene 字段的值，再做处理逻辑。
     * 使用如下代码可以获取到二维码中的 scene 字段的值。
     * 调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，开发工具模拟时的 scene 的参数值需要进行 urlencode
     * </pre>
     *
     * @param scene     最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
     *                  其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     * @param page      必须是已经发布的小程序页面，例如 "pages/index/index" ,如果不填写这个字段，默认跳主页面
     * @param width     默认false 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
//     * @param autoColor 默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
//     * @param lineColor auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
//     * @param isHyaline 是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
     * @return 文件对象
     * @throws WxErrorException 异常
     */
//    @GetMapping("/unlimitCreate")
//    @ApiOperation("接口B: 获取小程序码")
//    public Object createWxaCodeUnlimitQiniu(String scene, String page, int width) throws WxErrorException, QiniuException {
//        final WxMaQrcodeService qrcodeService = WxMaConfiguration.getMaService(appid).getQrcodeService();
////        File codeUnlimit = qrcodeService.createWxaCodeUnlimit(scene, page, width,true, (WxMaCodeLineColor)null, false);
//        WxUser loginWxUser = wxUserService.getLoginWxUser();
//        if (ToolUtil.isNotEmpty(loginWxUser)) {
//            String qrcodeUrl = loginWxUser.getQrcode();
//            if (ToolUtil.isNotEmpty(qrcodeUrl)) {
//                String qiniKeyByUrl = StringUtil.getQiniKeyByUrl(qrcodeUrl);
//                qiniuService.delete(qiniKeyByUrl);
//
//            }
//        } else {
//            return new ErrorResponseData("用户登录异常");
//        }
//        InputStream inputStream = new ByteArrayInputStream(qrcodeService.createWxaCodeUnlimitBytes(scene, page, width, true, (WxMaCodeLineColor) null, false));
//        SuccessResponseData successResponseData = new SuccessResponseData();
//        String dateToStr = DateUtils.dateToStr(new Date(), "yyyyMMddhhmmss");
//        String pictureName = "code" + dateToStr + UUID.randomUUID().toString().split("-")[4] + ".jpg";
//        String qrcodeUrl = qiniuService.uploadFile(inputStream, pictureName);
//        successResponseData.setData(qrcodeUrl);
//        loginWxUser.setQrcode(qrcodeUrl);
//        wxUserService.updateById(loginWxUser);
//        return successResponseData;
//    }

    @GetMapping("/unlimitCreate")
    @ApiOperation("接口B: 获取小程序码")
    public Object createWxaCodeUnlimit(String scene, String page, int width, HttpServletResponse response) throws IOException, WxErrorException {
        final WxMaQrcodeService qrcodeService = WxMaConfiguration.getMaService(appid).getQrcodeService();
        // 生成二维码图片字节流
        byte[] wxaCodeUnlimitBytes = qrcodeService.createWxaCodeUnlimitBytes(scene, page, width, true, (WxMaCodeLineColor) null, false);
        String pictureName = UUID.randomUUID().toString() + ".jpg" ;
        File file = new File(gunsProperties.getFileUploadPath() + pictureName);
        FileOutputStream fops = new FileOutputStream(file);
        fops.write(wxaCodeUnlimitBytes);
        fops.flush();
        fops.close();
        SuccessResponseData successResponseData = new SuccessResponseData();
        successResponseData.setData("https://www.xbdzmp.com/api/file/" + pictureName);
        return successResponseData;
    }

}
