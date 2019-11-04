package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.dao.CategoryMapper;
import cn.stylefeng.guns.modular.system.service.ICategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类别 服务实现类
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
