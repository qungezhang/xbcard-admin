package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.warpper.WxUserWarpper;
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
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IWxUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 小程序用户控制器
 *
 * @author fengshuonan
 * @Date 2019-11-23 20:10:42
 */
@Controller
@RequestMapping("/wxUser")
public class WxUserController extends BaseController {

    private String PREFIX = "/system/wxUser/";

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 跳转到小程序用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxUser.html";
    }

    @RequestMapping("demo_v4")
    public String demo_v4() {
        return PREFIX + "demo_v4.html";
    }
    /**
     * 跳转到添加小程序用户
     */
    @RequestMapping("/wxUser_add")
    public String wxUserAdd() {
        return PREFIX + "wxUser_add.html";
    }

    /**
     * 跳转到修改小程序用户
     */
    @RequestMapping("/wxUser_update/{wxUserId}")
    public String wxUserUpdate(@PathVariable Integer wxUserId, Model model) {
        WxUser wxUser = wxUserService.selectById(wxUserId);
        model.addAttribute("item",wxUser);
        LogObjectHolder.me().set(wxUser);
        return PREFIX + "wxUser_edit.html";
    }

    /**
     * 获取小程序用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String mobile,String nickName) {
        Object wrap = null;
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (ToolUtil.isNotEmpty(mobile) || ToolUtil.isNotEmpty(nickName)) {
            EntityWrapper<WxUser> wrapper = new EntityWrapper<>();
            wrapper.eq(ToolUtil.isNotEmpty(mobile), "mobile", mobile);
            wrapper.like(ToolUtil.isNotEmpty(nickName), "nick_name", nickName);
            Map<String, Object> map = wxUserService.selectMap(wrapper);
            if (ToolUtil.isNotEmpty(map)) {
                mapList.add(map);
                String pCode = "[" + map.get("id") + "]";
                EntityWrapper<WxUser> entityWrapper = new EntityWrapper<>();
                entityWrapper.like(ToolUtil.isNotEmpty(pCode), "flag1", pCode);
                entityWrapper.orderBy("update_time",false);
                List<Map<String, Object>> maps = wxUserService.selectMaps(entityWrapper);
                if (ToolUtil.isNotEmpty(maps)) {
                    mapList.addAll(maps);
                }
                wrap = new WxUserWarpper(mapList).wrap();
            }
        } else {
            List<Map<String, Object>> selectMaps = wxUserService.selectMaps(new EntityWrapper<WxUser>().orderBy("update_time", false));
            wrap = new WxUserWarpper(selectMaps).wrap();
        }

        return wrap;
    }

    /**
     * 新增小程序用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WxUser wxUser) {
        wxUserService.insert(wxUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除小程序用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wxUserId) {
        wxUserService.deleteById(wxUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改小程序用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WxUser wxUser) {
        wxUserService.updateById(wxUser);
        return SUCCESS_TIP;
    }

    /**
     * 小程序用户详情
     */
    @RequestMapping(value = "/detail/{wxUserId}")
    @ResponseBody
    public Object detail(@PathVariable("wxUserId") Integer wxUserId) {
        return wxUserService.selectById(wxUserId);
    }
}
