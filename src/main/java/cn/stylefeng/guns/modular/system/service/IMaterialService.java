package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.dto.MaterialInfoDto;
import cn.stylefeng.guns.modular.system.model.Material;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 素材 服务类
 * </p>
 *
 * @author qunge
 * @since 2019-12-18
 */
public interface IMaterialService extends IService<Material> {


    List<MaterialInfoDto> getMaterialByPid(Integer cardId, Integer pid, Page page);

    /**
     * 最新动态 三天内的
     *
     * @param cardId
     * @param page
     * @return
     */
    List<MaterialInfoDto> getNewMaterialByCardId(Integer cardId, Page page);

}
