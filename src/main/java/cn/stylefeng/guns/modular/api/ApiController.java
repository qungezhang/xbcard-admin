/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.RedisUtil;
import cn.stylefeng.guns.core.util.UUIDUtill;
import cn.stylefeng.guns.modular.dto.TestDto;
import cn.stylefeng.guns.modular.dto.WxUserTreeDto;
import cn.stylefeng.guns.modular.system.dao.UserMapper;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.github.binarywang.wxpay.util.SignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static cn.stylefeng.guns.core.util.JwtTokenUtil.getUserId;

/**
 * 接口控制器提供
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/api")
@Api(tags = "api临时token")
public class ApiController extends BaseController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    IWxUserService iWxUserService;

//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private GunsProperties gunsProperties;
//    /**
//     * api登录接口，通过账号密码获取token
//     */
//    @PostMapping("/auth")
//    @ApiOperation("api登录接口")
//    public Object auth(@RequestParam("username") String username,
//                       @RequestParam("password") String password) {
//
//        //封装请求账号密码为shiro可验证的token
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
//
//        //获取数据库中的账号密码，准备比对
//        User user = userMapper.getByAccount(username);
//
//        String credentials = user.getPassword();
//        String salt = user.getSalt();
//        ByteSource credentialsSalt = new Md5Hash(salt);
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
//                new ShiroUser(), credentials, credentialsSalt, "");
//
//        //校验用户账号密码
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
//        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
//        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
//                usernamePasswordToken, simpleAuthenticationInfo);
//
//        if (passwordTrueFlag) {
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
//            return result;
//        } else {
//            return new ErrorResponseData(500, "账号密码错误！");
//        }
//    }
//
//    /**
//     * 测试接口是否走鉴权
//     */
//    @PostMapping(value = "/test")
//    @ApiOperation("测试接口是否走鉴权")
//    public Object test(@RequestBody TestDto testDto) {
//        SuccessResponseData successResponseData = new SuccessResponseData();
//        String name = getUserId();
//        User user = userMapper.selectById(name);
//        successResponseData.setData(user);
//        return successResponseData;
//    }

    @GetMapping("/auth")
    @ApiOperation("api临时token")

    public Object devAuth(Integer userId) {
        SuccessResponseData successResponseData = new SuccessResponseData();
        successResponseData.setData("Bearer " + JwtTokenUtil.generateToken(String.valueOf(userId)));
        return successResponseData;
    }

//    @GetMapping("/sign")
//    @ApiOperation("sign")
//    public Object sign() {
//
//        Map<String, String> configMap = new HashMap<>();
//        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
//        String nonceStr = String.valueOf(System.currentTimeMillis());
//        String packageValue = "prepay_id=wx2017033010242291fcfe0db70013231072";
//        configMap.put("timestamp", timestamp);
//        configMap.put("noncestr", nonceStr);
//        configMap.put("appid", "appId");
//        configMap.put("package", packageValue);
//
//        String sign = SignUtils.createSign(configMap, "SignType", "mchKey", null);
//        return sign;
//    }

//    @GetMapping("/cache")
//    @ApiOperation("cache")
//    public Object cache() {
//        String s = "2222ssss";
//        redisUtil.set(s, "dhwehwwwwwwwwww2222222222222222222222222222222222222wwwwwwwwwwdddddddddddddddddddddmmccm23232k323jkjkjkj32k3jk23k23jk3kdd");
//        Object o = redisUtil.get(s);
//        System.out.println(o);
//        return o;
//    }

//    @GetMapping("/userTreeDto")
//    @ApiOperation("userTreeDto")
//    public Object cache() {
//        WxUserTreeDto userTreeDto = iWxUserService.spacetreeUsers();
//        return userTreeDto;
//    }

}

