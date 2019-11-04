package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.service.ICategoryService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author qunge
 * @Date 2019-11-04 21:59:05
 */
@Controller
@RequestMapping("/api/category")
@Api(tags = "类别")
public class CategoryApiController extends BaseController {

    @Autowired
    private ICategoryService categoryService;


    /**
     * 获取列表
     */
    @PostMapping(value = "/pList")
    @ApiOperation("获取分页列表")
    @ResponseBody
    public ResponseData pList(@RequestBody PageListDTO<Category> pageListDTO) {
        Page<Category> page = new PageFactory<Category>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
        PageUtils pageUtils = new PageUtils(categoryService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody())));
        return new SuccessResponseData(pageUtils);
    }
    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(Category category) {
        categoryService.insert(category);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer categoryId) {
        categoryService.deleteById(categoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Object update(Category category) {
        categoryService.updateById(category);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{categoryId}")
    @ResponseBody
    public Object detail(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.selectById(categoryId);
    }
}