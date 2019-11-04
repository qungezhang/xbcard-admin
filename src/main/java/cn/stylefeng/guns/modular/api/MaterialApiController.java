package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
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
 * @Date 2019-11-04 21:58:16
 */
@Controller
@RequestMapping("/api/material")
@Api(tags = "素材")
public class MaterialApiController extends BaseController {


    @Autowired
    private IMaterialService materialService;

    /**
     * 获取列表
     */
    @PostMapping(value = "/pList")
    @ApiOperation("获取分页列表")
    @ResponseBody
    public ResponseData pList(@RequestBody PageListDTO<Material> pageListDTO) {
        Page<Material> page = new PageFactory<Material>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
        PageUtils pageUtils = new PageUtils(materialService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody())));
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody Material material) {
        materialService.insert(material);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    @ApiOperation("删除")
    public Object delete(@RequestParam Integer materialId) {
        materialService.deleteById(materialId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @ApiOperation("修改")
    public Object update(@RequestBody Material material) {
        materialService.updateById(material);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{materialId}")
    @ResponseBody
    @ApiOperation("详情")
    public Object detail(@PathVariable("materialId") Integer materialId) {
        return materialService.selectById(materialId);
    }
}
