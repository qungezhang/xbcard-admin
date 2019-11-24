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
import cn.stylefeng.guns.modular.dto.WXLoginResultDTO;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public Object login(@RequestBody WXLoginDTO loginDTO) {
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
            // 解密手机
            WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(session.getSessionKey(), loginDTO.getEncryptedData(), loginDTO.getIv());
            WxUser wxUser = null;
            EntityWrapper<WxUser> wrapper = new EntityWrapper<>();
            wrapper.eq("openid", userInfo.getOpenId()).orderBy("create_time", false).last("limit 1");
            wxUser = wxUserService.selectOne(wrapper);
            if (wxUser != null) {
                setUser(userInfo, phoneNoInfo, wxUser);
                wxUserService.updateById(wxUser);
            } else {
                wxUser = new WxUser();
                setUser(userInfo, phoneNoInfo, wxUser);
                wxUser.setCreateTime(new Date());
                wxUser.setIsvip(0);//是否是会员（0否，1是）
                wxUser.setEmpId(loginDTO.getEmpId() == null ? 0 : loginDTO.getEmpId());//0自主创建
                wxUserSetPids(wxUser);
                wxUserService.insert(wxUser);
            }
            SuccessResponseData responseData = new SuccessResponseData();
            WXLoginResultDTO resultDTO = new WXLoginResultDTO();
            resultDTO.setToken(JwtTokenUtil.generateToken(String.valueOf(wxUser.getId())));
            resultDTO.setUser(wxUser);
            responseData.setData(resultDTO);
            return responseData;
        } catch (WxErrorException e) {
            ErrorResponseData errorResponseData = new ErrorResponseData(e.toString());
            this.logger.error(e.getMessage(), e);
            return errorResponseData;
        }
    }

    private void setUser(WxMaUserInfo userInfo, WxMaPhoneNumberInfo phoneNoInfo, WxUser wxUser) {
        if (phoneNoInfo != null) {
            wxUser.setMobile(phoneNoInfo.getPhoneNumber());
        }
        wxUser.setHeadimgurl(userInfo.getAvatarUrl());
        wxUser.setOpenid(userInfo.getOpenId());
        wxUser.setNickName(userInfo.getNickName());
        wxUser.setLanguage(userInfo.getLanguage());
        wxUser.setSex(Integer.valueOf(userInfo.getGender()));
        wxUser.setUnionid(userInfo.getUnionId());
        wxUser.setCountry(userInfo.getCountry());
        wxUser.setCity(userInfo.getCity());
        wxUser.setProvince(userInfo.getProvince());
        wxUser.setLastLoginTime(new Date());
        wxUser.setUpdateTime(new Date());
    }
    private void wxUserSetPids(WxUser wxUser) {
        Integer pid = wxUser.getEmpId();
        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            wxUser.setEmpId(0);
            wxUser.setFlag1("[0],");
        } else {
            WxUser pWxUser = wxUserService.selectById(pid);
            String pids = pWxUser.getFlag1();
            wxUser.setEmpId(pid);
            wxUser.setFlag1(pids + "[" + pid + "],");
        }
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
