package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.dao.MaterialMapper;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 素材 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

}
