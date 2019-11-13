package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.modular.dto.CategoryTreeDTO;
import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.dao.CategoryMapper;
import cn.stylefeng.guns.modular.system.service.ICategoryService;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CategoryTreeDTO> getTreeList(Integer cardId) {
        Category category = new Category();
        category.setCardId(cardId);
        category.setIsDeleted(0);
        List<Category> categories = this.selectList(new EntityWrapper<>(category));
        return treeList(BeanMapperUtil.mapList(categories, CategoryTreeDTO.class), 0);
    }

    /**
     * explain: 遍历递归
     * @param list 遍历的集合
     * @param pid 关联父集合的id(当然也按照你们公司的规定)
     * @return
     */
    public static List<CategoryTreeDTO> treeList(List<CategoryTreeDTO> list, int pid){
        List<CategoryTreeDTO> pList = new ArrayList<>();
        List<CategoryTreeDTO> otherList = new ArrayList<>();
        for(CategoryTreeDTO treeDTO :list) {
            if (treeDTO.getPid().equals(pid)) {//首先先找到一级集合
                pList.add(treeDTO);
            } else {
                otherList.add(treeDTO);
            }
        }
        List<CategoryTreeDTO> result = new ArrayList<>();
        for(CategoryTreeDTO treeDTO :pList) {//循环遍历归属一级集合的子集
            treeDTO.setChildList(treeList(otherList,treeDTO.getId()));//通过反复调用自身方法循环输出归属上级集合的下级集合
            result.add(treeDTO);
        }
        return result;
    }
}
