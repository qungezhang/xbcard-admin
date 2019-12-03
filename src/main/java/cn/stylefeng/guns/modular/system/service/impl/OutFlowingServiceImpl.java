package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.model.OutFlowing;
import cn.stylefeng.guns.modular.system.dao.OutFlowingMapper;
import cn.stylefeng.guns.modular.system.service.IOutFlowingService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 平台支出流水 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
@Service
public class OutFlowingServiceImpl extends ServiceImpl<OutFlowingMapper, OutFlowing> implements IOutFlowingService {

    @Override
    public Map<String, Object> getTotalOut(Integer userId) {
        EntityWrapper<OutFlowing> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("sum(out_fee) as outTotal");
        wrapper.eq("user_id", userId).eq("is_deleted", 0).groupBy("user_id");
        return this.selectMap(wrapper);
    }
}
