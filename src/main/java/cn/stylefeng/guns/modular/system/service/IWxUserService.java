package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.WxUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信用户 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
public interface IWxUserService extends IService<WxUser> {

    /**
     * 获取小程序登录者
     * @return
     */
    WxUser getLoginWxUser();

    /**
     * 根据条件查询用戶
     *
     * @return
     */
    List<Map<String, Object>> selectUsers(String pCode,Integer id);
}
