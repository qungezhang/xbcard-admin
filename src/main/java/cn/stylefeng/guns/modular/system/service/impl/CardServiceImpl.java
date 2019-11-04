package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.dao.CardMapper;
import cn.stylefeng.guns.modular.system.service.ICardService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 名片基础信息 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        String paramKey = (String)params.get("paramKey");
//
//        EntityWrapper<Object> objectEntityWrapper = new EntityWrapper<>();
//        Page<Card> cardPage = this.selectPage(objectEntityWrapper);
        return new PageUtils(null);
    }
}
