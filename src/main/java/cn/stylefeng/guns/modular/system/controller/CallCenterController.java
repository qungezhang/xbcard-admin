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
import cn.stylefeng.guns.modular.system.model.CallCenter;
import cn.stylefeng.guns.modular.system.service.ICallCenterService;

import java.util.Date;
import java.util.Map;

/**
 * 客服中心记录控制器
 *
 * @author fengshuonan
 * @Date 2019-12-04 23:48:41
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
        CallCenter center = new CallCenter();
        center.setId(callCenterId);
        Map<String, Object> map = callCenterService.selectMap(new EntityWrapper<>(center));
        Object createTime = map.get("createTime");
        Object replyTime = map.get("replyTime");
        if (ToolUtil.isNotEmpty(createTime)) {
            String s = createTime + "";
            map.put("createTime", s.substring(0, s.length() - 2));
        }
        if (ToolUtil.isNotEmpty(replyTime)) {
            String s = replyTime + "";
            map.put("replyTime", s.substring(0, s.length() - 2));
        }
//        CallCenter callCenter = callCenterService.selectById(callCenterId);
        model.addAttribute("item",map);
        LogObjectHolder.me().set(map);
        return PREFIX + "callCenter_edit.html";
    }

    /**
     * 获取客服中心记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        CallCenter callCenter = new CallCenter();
        if (ToolUtil.isNotEmpty(condition)) {
            callCenter.setMobile(condition);
        }
        return callCenterService.selectList(new EntityWrapper<>(callCenter));
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
        callCenter.setStatus(1);
        callCenter.setReplyTime(new Date());
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
