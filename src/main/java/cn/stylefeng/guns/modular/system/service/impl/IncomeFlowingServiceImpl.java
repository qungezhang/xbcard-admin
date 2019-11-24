package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.dao.IncomeFlowingMapper;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收入流水 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
@Service
public class IncomeFlowingServiceImpl extends ServiceImpl<IncomeFlowingMapper, IncomeFlowing> implements IIncomeFlowingService {

    @Override
    public IncomeFlowing getOneIncomeFlowingDesc(Integer userId) {
        EntityWrapper<IncomeFlowing> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId).eq("is_deleted", 0).orderBy("create_time", false);
        return this.selectOne(wrapper);
    }
}
