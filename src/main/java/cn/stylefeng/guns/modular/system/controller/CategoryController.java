package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.service.ICategoryService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 21:59:05
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private String PREFIX = "/system/category/";

    @Autowired
    private ICategoryService categoryService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "category.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/category_add")
    public String categoryAdd() {
        return PREFIX + "category_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/category_update/{categoryId}")
    public String categoryUpdate(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.selectById(categoryId);
        model.addAttribute("item",category);
        LogObjectHolder.me().set(category);
        return PREFIX + "category_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return categoryService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Category category) {
        categoryService.insert(category);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer categoryId) {
        categoryService.deleteById(categoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Category category) {
        categoryService.updateById(category);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{categoryId}")
    @ResponseBody
    public Object detail(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.selectById(categoryId);
    }
}
