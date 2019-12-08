package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.dao.WxUserMapper;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信用户 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {


    @Override
    public WxUser getLoginWxUser() {
        return this.selectById(JwtTokenUtil.getUserId());
    }

    @Override
    public List<Map<String, Object>> selectUsers(String pCode ,Integer id) {

        return this.baseMapper.selectUsers(pCode,id);
    }

}
