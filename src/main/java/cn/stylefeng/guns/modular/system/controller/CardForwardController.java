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
import cn.stylefeng.guns.modular.system.model.CardForward;
import cn.stylefeng.guns.modular.system.service.ICardForwardService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2019-11-07 22:00:14
 */
@Controller
@RequestMapping("/cardForward")
public class CardForwardController extends BaseController {

    private String PREFIX = "/system/cardForward/";

    @Autowired
    private ICardForwardService cardForwardService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cardForward.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/cardForward_add")
    public String cardForwardAdd() {
        return PREFIX + "cardForward_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/cardForward_update/{cardForwardId}")
    public String cardForwardUpdate(@PathVariable Integer cardForwardId, Model model) {
        CardForward cardForward = cardForwardService.selectById(cardForwardId);
        model.addAttribute("item",cardForward);
        LogObjectHolder.me().set(cardForward);
        return PREFIX + "cardForward_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return cardForwardService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CardForward cardForward) {
        cardForwardService.insert(cardForward);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardForwardId) {
        cardForwardService.deleteById(cardForwardId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CardForward cardForward) {
        cardForwardService.updateById(cardForward);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{cardForwardId}")
    @ResponseBody
    public Object detail(@PathVariable("cardForwardId") Integer cardForwardId) {
        return cardForwardService.selectById(cardForwardId);
    }
}
