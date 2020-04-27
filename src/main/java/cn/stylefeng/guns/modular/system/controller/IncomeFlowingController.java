package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;

import java.util.List;

/**
 * 收入流水控制器
 *
 * @author fengshuonan
 * @Date 2019-11-22 15:01:30
 */
@Controller
@RequestMapping("/incomeFlowing")
public class IncomeFlowingController extends BaseController {

    private String PREFIX = "/system/incomeFlowing/";

    @Autowired
    private IIncomeFlowingService incomeFlowingService;
    @Autowired
    private IWxUserService iWxUserService;
    /**
     * 跳转到收入流水首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "incomeFlowing.html";
    }

    /**
     * 跳转到添加收入流水
     */
    @RequestMapping("/incomeFlowing_add")
    public String incomeFlowingAdd() {
        return PREFIX + "incomeFlowing_add.html";
    }

    /**
     * 跳转到修改收入流水
     */
    @RequestMapping("/incomeFlowing_update/{incomeFlowingId}")
    public String incomeFlowingUpdate(@PathVariable Integer incomeFlowingId, Model model) {
        IncomeFlowing incomeFlowing = incomeFlowingService.selectById(incomeFlowingId);
        model.addAttribute("item",incomeFlowing);
        LogObjectHolder.me().set(incomeFlowing);
        return PREFIX + "incomeFlowing_edit.html";
    }

    /**
     * 获取收入流水列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Integer> userIdBySysUser = iWxUserService.getWxChildUserIdBySysUser();
        EntityWrapper<IncomeFlowing> wrapper = new EntityWrapper<>();
        wrapper.in(ToolUtil.isNotEmpty(userIdBySysUser), "customer_id", userIdBySysUser);
        wrapper.eq(ToolUtil.isNotEmpty(condition), "create_by", condition);
        return incomeFlowingService.selectList(wrapper);
    }

    /**
     * 新增收入流水
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(IncomeFlowing incomeFlowing) {
        incomeFlowingService.insert(incomeFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 删除收入流水
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer incomeFlowingId) {
        incomeFlowingService.deleteById(incomeFlowingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改收入流水
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(IncomeFlowing incomeFlowing) {
        incomeFlowingService.updateById(incomeFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 收入流水详情
     */
    @RequestMapping(value = "/detail/{incomeFlowingId}")
    @ResponseBody
    public Object detail(@PathVariable("incomeFlowingId") Integer incomeFlowingId) {
        return incomeFlowingService.selectById(incomeFlowingId);
    }
}
