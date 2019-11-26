package cn.stylefeng.guns.modular.api;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.stylefeng.guns.config.WxMaConfiguration;
import cn.stylefeng.guns.config.properties.WxMaProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 微信小程序二维码
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Controller
@RequestMapping("/api/WxCode")
@Api(tags = "微信小程序二维码")
public class WxCodeUnilimitController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
     * @param autoColor 默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     * @param lineColor auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
     * @param isHyaline 是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
     * @return 文件对象
     * @throws WxErrorException 异常
     */
    @GetMapping("/unlimitCreate")
    public File createWxaCodeUnlimit(String scene, String page, int width, boolean autoColor,
                                       WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException {
        final WxMaQrcodeService qrcodeService = WxMaConfiguration.getMaService(appid).getQrcodeService();
        File codeUnlimit = qrcodeService.createWxaCodeUnlimit(scene, page, width, autoColor, lineColor, isHyaline);
        return codeUnlimit;
    }

}
