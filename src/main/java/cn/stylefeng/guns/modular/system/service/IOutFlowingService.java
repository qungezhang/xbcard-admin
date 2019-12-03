package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.OutFlowing;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 平台支出流水 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
public interface IOutFlowingService extends IService<OutFlowing> {

    Map<String, Object> getTotalOut(Integer userId);
}
