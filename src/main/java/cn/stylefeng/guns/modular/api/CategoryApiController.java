package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.Category;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.ICategoryService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
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

import java.util.Date;

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

    @Autowired
    private IWxUserService wxUserService;

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
    @ApiOperation("新增")
    public Object add(@RequestBody Category category) {
        Integer userId = JwtTokenUtil.getUserId();
        WxUser wxUser = wxUserService.selectById(userId);
        if (wxUser == null || wxUser.getIsvip() == 0) {
            return new ErrorResponseData("用户异常或不是VIP，不可分类");
        }
        category.setUserId(userId);
        category.setPid(category.getPid() == null ? 0 : category.getPid());
        category.setIsDeleted(0);//是否删除（0否，1是）
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryService.insert(category);
        return SUCCESS_TIP;
    }




    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    @ApiOperation("删除")
    public Object delete(@RequestParam Integer categoryId) {
        categoryService.deleteById(categoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @ApiOperation("修改")
    public Object update(@RequestBody Category category) {
        categoryService.updateById(category);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{categoryId}")
    @ResponseBody
    @ApiOperation("详情")
    public Object detail(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.selectById(categoryId);
    }
}
