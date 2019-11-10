package cn.stylefeng.guns.modular.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.stylefeng.guns.config.WxMaConfiguration;
import cn.stylefeng.guns.config.properties.WxMaProperties;
import cn.stylefeng.guns.core.util.JsonUtils;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.dto.WXLoginDTO;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/api/wxuser")
@Api(tags = "微信登录")
public class WxMaUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IWxUserService wxUserService;

    private String appid;
    @Autowired
    public WxMaUserController(WxMaProperties properties) {
        this.appid = properties.getConfigs().get(0).getAppid();
    }

    /**
     * <pre>
     * 登陆接口
     * </pre>
     */
    @PostMapping("/login")
    @ApiOperation("登陆接口")
    public String login(@RequestBody WXLoginDTO loginDTO) {
        if (StringUtils.isBlank(loginDTO.getCode())) {
            return "empty jscode";
        }
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(loginDTO.getCode());
            // 用户信息校验
            if (!wxService.getUserService().checkUserInfo(session.getSessionKey(), loginDTO.getRawData(), loginDTO.getSignature())) {
                return "user check failed";
            }
            // 解密用户信息
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(session.getSessionKey(), loginDTO.getEncryptedData(), loginDTO.getIv());

            WxUser wxUser = new WxUser();// TODO: 2019/11/11 补充
            wxUser.setHeadimgurl(userInfo.getAvatarUrl());
            wxUser.setOpenid(userInfo.getOpenId());
            wxUser.setSex(Integer.valueOf(userInfo.getGender()));
            wxUserService.insert(wxUser);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JwtTokenUtil.generateToken(wxUser.getOpenid());
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
//        return JwtTokenUtil.generateToken("123456");
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

}
