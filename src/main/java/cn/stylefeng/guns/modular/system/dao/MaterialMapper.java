package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.dto.MaterialInfoDto;
import cn.stylefeng.guns.modular.system.model.Material;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 素材 Mapper 接口
 * </p>
 *
 * @author qunge
 * @since 2019-12-18
 */
public interface MaterialMapper extends BaseMapper<Material> {

    List<MaterialInfoDto> getMaterialByPid(@Param("cardId") Integer cardId, @Param("pid") Integer pid, Page page);

}
