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
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.service.IMaterialService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 21:58:16
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {

    private String PREFIX = "/system/material/";

    @Autowired
    private IMaterialService materialService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "material.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/material_add")
    public String materialAdd() {
        return PREFIX + "material_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/material_update/{materialId}")
    public String materialUpdate(@PathVariable Integer materialId, Model model) {
        Material material = materialService.selectById(materialId);
        model.addAttribute("item",material);
        LogObjectHolder.me().set(material);
        return PREFIX + "material_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return materialService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Material material) {
        materialService.insert(material);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer materialId) {
        materialService.deleteById(materialId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Material material) {
        materialService.updateById(material);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{materialId}")
    @ResponseBody
    public Object detail(@PathVariable("materialId") Integer materialId) {
        return materialService.selectById(materialId);
    }
}
