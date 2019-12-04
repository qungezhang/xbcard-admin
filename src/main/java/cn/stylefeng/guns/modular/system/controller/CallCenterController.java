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
import cn.stylefeng.guns.modular.system.model.CallCenter;
import cn.stylefeng.guns.modular.system.service.ICallCenterService;

/**
 * 客服中心记录控制器
 *
 * @author fengshuonan
 * @Date 2019-12-04 17:40:17
 */
@Controller
@RequestMapping("/callCenter")
public class CallCenterController extends BaseController {

    private String PREFIX = "/system/callCenter/";

    @Autowired
    private ICallCenterService callCenterService;

    /**
     * 跳转到客服中心记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "callCenter.html";
    }

    /**
     * 跳转到添加客服中心记录
     */
    @RequestMapping("/callCenter_add")
    public String callCenterAdd() {
        return PREFIX + "callCenter_add.html";
    }

    /**
     * 跳转到修改客服中心记录
     */
    @RequestMapping("/callCenter_update/{callCenterId}")
    public String callCenterUpdate(@PathVariable Integer callCenterId, Model model) {
        CallCenter callCenter = callCenterService.selectById(callCenterId);
        model.addAttribute("item",callCenter);
        LogObjectHolder.me().set(callCenter);
        return PREFIX + "callCenter_edit.html";
    }

    /**
     * 获取客服中心记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return callCenterService.selectList(null);
    }

    /**
     * 新增客服中心记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CallCenter callCenter) {
        callCenterService.insert(callCenter);
        return SUCCESS_TIP;
    }

    /**
     * 删除客服中心记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer callCenterId) {
        callCenterService.deleteById(callCenterId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客服中心记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CallCenter callCenter) {
        callCenterService.updateById(callCenter);
        return SUCCESS_TIP;
    }

    /**
     * 客服中心记录详情
     */
    @RequestMapping(value = "/detail/{callCenterId}")
    @ResponseBody
    public Object detail(@PathVariable("callCenterId") Integer callCenterId) {
        return callCenterService.selectById(callCenterId);
    }
}
