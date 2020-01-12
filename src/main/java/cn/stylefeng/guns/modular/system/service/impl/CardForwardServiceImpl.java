package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.CardForward;
import cn.stylefeng.guns.modular.system.dao.CardForwardMapper;
import cn.stylefeng.guns.modular.system.service.ICardForwardService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 名片转发打开记录 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@Service
public class CardForwardServiceImpl extends ServiceImpl<CardForwardMapper, CardForward> implements ICardForwardService {

    @Override
    public List<CardForward> getByUserIdAndType(Integer userId, Integer type, Page page) {
        return this.baseMapper.getByUserIdAndType(userId, type, page);
    }
}
