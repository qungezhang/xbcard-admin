package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.dto.MaterialInfoDto;
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.dao.MaterialMapper;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 素材 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-12-18
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Override
    public List<MaterialInfoDto> getMaterialByPid(Integer cardId, Integer pid, Page page) {
        return baseMapper.getMaterialByPid(cardId, pid, page);
    }

    @Override
    public List<MaterialInfoDto> getNewMaterialByCardId(Integer cardId, Page page) {

        return baseMapper.getNewMaterialByCardId(cardId, page);
    }

    @Override
    public List<Material> selectChildIdNew(Integer pid) {
        return baseMapper.selectChildIdNew(pid);
    }
}
