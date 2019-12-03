package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 收入流水 Mapper 接口
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
public interface IncomeFlowingMapper extends BaseMapper<IncomeFlowing> {

    Map<String, Object> get7DaysAgoTotalFee(@Param("userId") Integer userId);

}
