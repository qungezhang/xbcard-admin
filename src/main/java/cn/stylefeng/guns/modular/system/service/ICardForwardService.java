package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.CardForward;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 名片转发打开记录 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
public interface ICardForwardService extends IService<CardForward> {

    List<CardForward> getByUserIdAndType( Integer userId, Integer type, Page page);


}
