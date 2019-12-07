package cn.stylefeng.guns.modular.system.controller;

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
import cn.stylefeng.guns.modular.system.model.OutFlowing;
import cn.stylefeng.guns.modular.system.service.IOutFlowingService;

/**
 * 平台支出流水控制器
 *
 * @author fengshuonan
 * @Date 2019-11-22 19:31:37
 */
@Controller
@RequestMapping("/outFlowing")
public class OutFlowingController extends BaseController {

    private String PREFIX = "/system/outFlowing/";

    @Autowired
    private IOutFlowingService outFlowingService;

    /**
     * 跳转到平台支出流水首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "outFlowing.html";
    }

    /**
     * 跳转到添加平台支出流水
     */
    @RequestMapping("/outFlowing_add")
    public String outFlowingAdd() {
        return PREFIX + "outFlowing_add.html";
    }

    /**
     * 跳转到修改平台支出流水
     */
    @RequestMapping("/outFlowing_update/{outFlowingId}")
    public String outFlowingUpdate(@PathVariable Integer outFlowingId, Model model) {
        OutFlowing outFlowing = outFlowingService.selectById(outFlowingId);
        model.addAttribute("item",outFlowing);
        LogObjectHolder.me().set(outFlowing);
        return PREFIX + "outFlowing_edit.html";
    }

    /**
     * 获取平台支出流水列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<OutFlowing> wrapper = new EntityWrapper<>();
        wrapper.eq(ToolUtil.isNotEmpty(condition), "create_by", condition);
        return outFlowingService.selectList(wrapper);
    }

    /**
     * 新增平台支出流水
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OutFlowing outFlowing) {
        outFlowingService.insert(outFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 删除平台支出流水
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer outFlowingId) {
        outFlowingService.deleteById(outFlowingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改平台支出流水
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OutFlowing outFlowing) {
        outFlowingService.updateById(outFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 平台支出流水详情
     */
    @RequestMapping(value = "/detail/{outFlowingId}")
    @ResponseBody
    public Object detail(@PathVariable("outFlowingId") Integer outFlowingId) {
        return outFlowingService.selectById(outFlowingId);
    }
}
