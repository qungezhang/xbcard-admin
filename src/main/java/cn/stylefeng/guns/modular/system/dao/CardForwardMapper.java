package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.CardForward;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 名片转发打开记录 Mapper 接口
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
public interface CardForwardMapper extends BaseMapper<CardForward> {

    List<CardForward> getByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type, Page page);


}
