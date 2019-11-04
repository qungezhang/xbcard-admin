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
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-11-04 21:57:52
 */
@Controller
@RequestMapping("/wxUser")
public class WxUserController extends BaseController {

    private String PREFIX = "/system/wxUser/";

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxUser.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/wxUser_add")
    public String wxUserAdd() {
        return PREFIX + "wxUser_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/wxUser_update/{wxUserId}")
    public String wxUserUpdate(@PathVariable Integer wxUserId, Model model) {
        WxUser wxUser = wxUserService.selectById(wxUserId);
        model.addAttribute("item",wxUser);
        LogObjectHolder.me().set(wxUser);
        return PREFIX + "wxUser_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wxUserService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WxUser wxUser) {
        wxUserService.insert(wxUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wxUserId) {
        wxUserService.deleteById(wxUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WxUser wxUser) {
        wxUserService.updateById(wxUser);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{wxUserId}")
    @ResponseBody
    public Object detail(@PathVariable("wxUserId") Integer wxUserId) {
        return wxUserService.selectById(wxUserId);
    }
}
