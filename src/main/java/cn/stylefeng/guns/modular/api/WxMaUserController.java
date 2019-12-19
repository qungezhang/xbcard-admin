package cn.stylefeng.guns.modular.api;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.stylefeng.guns.config.WxMaConfiguration;
import cn.stylefeng.guns.config.properties.WxMaProperties;
import cn.stylefeng.guns.core.common.constant.RedisConstant;
import cn.stylefeng.guns.core.util.JsonUtils;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.RedisUtil;
import cn.stylefeng.guns.modular.dto.WXLoginDTO;
import cn.stylefeng.guns.modular.dto.WXLoginResultDTO;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
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

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisUtil redisUtil;
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
//            String idkey = RedisConstant.USER_ID + wxUser.getId();
//            String token = redisUtil.getString(idkey);
//            String redisUserId = String.valueOf(wxUser.getId());
//            if(StringUtils.isEmpty(token)) {
//                token = UUID.randomUUID().toString();
//                preLogin(redisUserId);
//                cacheToken(redisUserId, token, wxUser);
//                resultDTO.setToken(token);
//            }else {//如果缓存key不发生变化，直接变更缓存值
//                cacheToken(redisUserId, token, wxUser);
//                resultDTO.setToken(token);
//            }


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
    public void preLogin(String idKey) {
        String userIdKey = redisUtil.getString(RedisConstant.USER_ID + idKey);
        if (userIdKey != null) {
            redisUtil.del(RedisConstant.USER_ID + idKey);
            redisUtil.del(RedisConstant.USER_TOKEN + userIdKey);
        }
    }
    private void cacheToken(String idKey, String token, WxUser userDTO) {
        String idkey = RedisConstant.USER_ID + idKey;
        String toKey = RedisConstant.USER_TOKEN + token;
        redisUtil.setString(idkey, token);
        redisUtil.setString(toKey, JSONObject.toJSONString(userDTO));
        redisUtil.expire(idkey, RedisConstant.TOKNE_TIMEOUT, TimeUnit.DAYS);
        redisUtil.expire(toKey, RedisConstant.TOKNE_TIMEOUT, TimeUnit.DAYS);
    }
    /**
     * <pre>
     * 获取用户
     * </pre>
     */
    @GetMapping("/getInfo")
    @ApiOperation("获取用户")
    public Object getInfo() {
        SuccessResponseData responseData = new SuccessResponseData();
        responseData.setData(wxUserService.getLoginWxUser());
        return responseData;
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
//        wxUser.setUpdateTime(new Date());
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
    @GetMapping("/createWxaCode")
    public File createWxaCodeUnlimit(String scene, String page, int width, boolean autoColor,
                                       WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException {
        final WxMaQrcodeService qrcodeService = WxMaConfiguration.getMaService(appid).getQrcodeService();
        File codeUnlimit = qrcodeService.createWxaCodeUnlimit(scene, page, width, autoColor, lineColor, isHyaline);;
        return codeUnlimit;
    }

}
