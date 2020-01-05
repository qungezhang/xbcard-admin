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
import cn.stylefeng.guns.modular.system.model.Promotion;
import cn.stylefeng.guns.modular.system.service.IPromotionService;

/**
 * 宣传文案控制器
 *
 * @author fengshuonan
 * @Date 2020-01-05 20:28:01
 */
@Controller
@RequestMapping("/promotion")
public class PromotionController extends BaseController {

    private String PREFIX = "/system/promotion/";

    @Autowired
    private IPromotionService promotionService;

    /**
     * 跳转到宣传文案首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "promotion.html";
    }

    /**
     * 跳转到添加宣传文案
     */
    @RequestMapping("/promotion_add")
    public String promotionAdd() {
        return PREFIX + "promotion_add.html";
    }

    /**
     * 跳转到修改宣传文案
     */
    @RequestMapping("/promotion_update/{promotionId}")
    public String promotionUpdate(@PathVariable Integer promotionId, Model model) {
        Promotion promotion = promotionService.selectById(promotionId);
        model.addAttribute("item",promotion);
        LogObjectHolder.me().set(promotion);
        return PREFIX + "promotion_edit.html";
    }

    /**
     * 获取宣传文案列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return promotionService.selectList(null);
    }

    /**
     * 新增宣传文案
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Promotion promotion) {
        promotionService.insert(promotion);
        return SUCCESS_TIP;
    }

    /**
     * 删除宣传文案
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer promotionId) {
        promotionService.deleteById(promotionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改宣传文案
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Promotion promotion) {
        promotionService.updateById(promotion);
        return SUCCESS_TIP;
    }

    /**
     * 宣传文案详情
     */
    @RequestMapping(value = "/detail/{promotionId}")
    @ResponseBody
    public Object detail(@PathVariable("promotionId") Integer promotionId) {
        return promotionService.selectById(promotionId);
    }
}
