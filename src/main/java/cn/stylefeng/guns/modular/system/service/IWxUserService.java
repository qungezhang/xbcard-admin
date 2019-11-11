package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.WxUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 微信用户 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
public interface IWxUserService extends IService<WxUser> {

    WxUser getWxUserByToken();
}
