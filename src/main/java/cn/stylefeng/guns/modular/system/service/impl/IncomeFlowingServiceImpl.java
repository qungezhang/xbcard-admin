package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.dto.IncomeFlowingDto;
import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.dao.IncomeFlowingMapper;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;
import cn.stylefeng.guns.modular.system.service.IOutFlowingService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Autowired
    private IOutFlowingService outFlowingService;

    @Override
    public IncomeFlowing getOneIncomeFlowingDesc(Integer userId) {
        EntityWrapper<IncomeFlowing> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId).eq("is_deleted", 0).orderBy("create_time", false).last("limit 1");
        return this.selectOne(wrapper);
    }

    @Override
    public IncomeFlowingDto byUserId(Integer userId) {
        IncomeFlowing flowingDesc = this.getOneIncomeFlowingDesc(userId);
        Map<String, Object> daysAgoTotalFee = this.baseMapper.get7DaysAgoTotalFee(userId);
        Map<String, Object> totalOut = outFlowingService.getTotalOut(userId);
        int outTotal = totalOut != null ? Integer.parseInt(String.valueOf(totalOut.get("outTotal"))) : 0;
        int get7DaysAgoTotalFee = daysAgoTotalFee != null ? Integer.parseInt(String.valueOf(daysAgoTotalFee.get("totalFee"))) : 0;
        int inTotal = flowingDesc != null ? flowingDesc.getMyselfTotalFee() : 0;
        IncomeFlowingDto dto = new IncomeFlowingDto();
        dto.setInTotal(inTotal);
        dto.setOutTotal(outTotal);
        dto.setRestTotal(inTotal-outTotal);
        dto.setPreTotal(inTotal-get7DaysAgoTotalFee);
        dto.setValidRestTotal(get7DaysAgoTotalFee-outTotal);
        return dto;
    }
}
