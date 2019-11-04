package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.system.model.Card;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 名片基础信息 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
public interface ICardService extends IService<Card> {
    PageUtils queryPage(Map<String, Object> params);
}
