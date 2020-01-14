package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.dto.IncomeFlowingDto;
import cn.stylefeng.guns.modular.dto.WxUserTreeDto;
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
import cn.stylefeng.guns.modular.system.warpper.MenuWarpper;
import cn.stylefeng.guns.modular.system.warpper.WxUserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
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
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private IIncomeFlowingService incomeFlowingService;

    /**
     * 跳转到小程序用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxUser.html";
    }

    @RequestMapping("spaceTreeUser")
    public String spaceTreeUser(Model model) {
//        WxUserTreeDto userTreeDto = wxUserService.spacetreeUsers();
//        String o = JSONObject.toJSONString(userTreeDto);
//        model.addAttribute("userTree", o);
//        LogObjectHolder.me().set(userTreeDto);
        return PREFIX + "spacetreeUser.html";
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
     * 素材详情
     */
    @RequestMapping("/material_info/{wxUserId}")
    public String materialInfo(@PathVariable Integer wxUserId, Model model) {
        EntityWrapper<Material> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", wxUserId).orderBy("create_time", false);
        List<Material> materials = materialService.selectList(wrapper);
        model.addAttribute("materials",materials);
        LogObjectHolder.me().set(materials);
        return PREFIX + "material_info.html";
    }
    @RequestMapping(value = "/spaceTreeUsersData")
    @ResponseBody
    public Object spaceTreeUsers() {
        return wxUserService.spacetreeUsers();
    }
    /**
     * 获取小程序用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String mobile,String nickName) {
//        WxUserWarpper wrap = null;
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        if (ToolUtil.isNotEmpty(mobile) || ToolUtil.isNotEmpty(nickName)) {
            EntityWrapper<WxUser> wrapper = new EntityWrapper<>();
            wrapper.eq(ToolUtil.isNotEmpty(mobile), "mobile", mobile);
            wrapper.like(ToolUtil.isNotEmpty(nickName), "nick_name", nickName);
            WxUser selectOne = wxUserService.selectOne(wrapper);
            if (ToolUtil.isNotEmpty(selectOne)) {
                selectOne.setEmpId(0);
//
//                map.put("empId", 0);
//                mapList.add(map);
                Integer id = selectOne.getId();
                String pCode = "[" + id + "]";
//                EntityWrapper<WxUser> entityWrapper = new EntityWrapper<>();
//                entityWrapper.like(ToolUtil.isNotEmpty(pCode), "flag1", pCode);
//                entityWrapper.orderBy("update_time",false);
//                List<Map<String, Object>> maps = wxUserService.selectMaps(entityWrapper);
                resultMapList= wxUserService.selectUsers(pCode, id);
                for (Map<String, Object> map : resultMapList) {
                    if (map.get("id").equals(id)) {
                        map.put("empId", 0);
                    }
                }
//                wrap = new WxUserWarpper(mapList);
            }
        } else {
//            List<Map<String, Object>> selectMaps = wxUserService.selectMaps(new EntityWrapper<WxUser>());
            resultMapList= wxUserService.selectUsers(null, null);
//            wrap = new WxUserWarpper(selectMaps);
        }

        if (ToolUtil.isNotEmpty(resultMapList)) {
            for (Map<String, Object> map : resultMapList) {
                Object concatIsVip = map.get("concatIsVip");
                Integer vipNum =0;
                if (ToolUtil.isNotEmpty(concatIsVip)) {
                    String[] isVips = String.valueOf(concatIsVip).split(",");
                    for (String s : isVips) {
                        if (s.equals("1")) {
                            vipNum++;
                        }
                    }
                }
                Integer childCount = Integer.valueOf(map.get("childCount") + "");
                map.put("vipNum", vipNum);
                map.put("notVipNum", childCount - vipNum);
                Integer inTotal = 0;
                Integer outTotal = 0;
                Integer restTotal = 0;
                Integer preTotal = 0;
                Integer validRestTotal = 0;
                if (vipNum > 0) {
                    IncomeFlowingDto incomeFlowingDto = incomeFlowingService.byUserId((Integer) map.get("id"));
                    inTotal = incomeFlowingDto.getInTotal();
                    outTotal = incomeFlowingDto.getOutTotal();
                    restTotal = incomeFlowingDto.getRestTotal();
                    preTotal = incomeFlowingDto.getPreTotal();
                    validRestTotal = incomeFlowingDto.getValidRestTotal();
                }
                map.put("inTotal", inTotal);
                map.put("outTotal", outTotal);
                map.put("restTotal", restTotal);
                map.put("preTotal", preTotal);
                map.put("validRestTotal", validRestTotal);
                if (Integer.parseInt(map.get("isvip") + "") == 0) {
                    map.put("vipStartTime", null);
                    map.put("vipEndTime", null);
                }
            }
            return super.warpObject(new WxUserWarpper(resultMapList));
        } else {
            return null;
        }
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
     * 修改vip
     */
    @RequestMapping(value = "/updateIsVip")
    @ResponseBody
    public Object updateIsVip(@RequestParam Integer userId, @RequestParam Integer isVip) {
        WxUser entity = new WxUser();
        entity.setId(userId);
        if (isVip.equals(1) ) {
            WxUser oldWxUser = wxUserService.selectById(userId);
            if (ToolUtil.isEmpty(oldWxUser.getVipStartTime())||(oldWxUser.getVipEndTime() != null && oldWxUser.getVipEndTime().getTime() < System.currentTimeMillis())) {
                entity.setVipStartTime(new Date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.YEAR, 1);  //在当前时间基础上加一年
                entity.setVipEndTime(cal.getTime());
            }

        }
        entity.setIsvip(isVip);
        wxUserService.updateById(entity);
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
