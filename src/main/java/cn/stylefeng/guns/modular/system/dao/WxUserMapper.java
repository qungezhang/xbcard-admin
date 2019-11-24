package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.WxUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信用户 Mapper 接口
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
public interface WxUserMapper extends BaseMapper<WxUser> {

    // TODO: 2019/11/24
    List<Map<String, Object>> getMaps();

}
