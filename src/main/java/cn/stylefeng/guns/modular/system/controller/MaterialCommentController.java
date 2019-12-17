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
import cn.stylefeng.guns.modular.system.model.MaterialComment;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentService;

/**
 * 素材评论控制器
 *
 * @author fengshuonan
 * @Date 2019-12-17 19:56:00
 */
@Controller
@RequestMapping("/materialComment")
public class MaterialCommentController extends BaseController {

    private String PREFIX = "/system/materialComment/";

    @Autowired
    private IMaterialCommentService materialCommentService;

    /**
     * 跳转到素材评论首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "materialComment.html";
    }

    /**
     * 跳转到添加素材评论
     */
    @RequestMapping("/materialComment_add")
    public String materialCommentAdd() {
        return PREFIX + "materialComment_add.html";
    }

    /**
     * 跳转到修改素材评论
     */
    @RequestMapping("/materialComment_update/{materialCommentId}")
    public String materialCommentUpdate(@PathVariable Integer materialCommentId, Model model) {
        MaterialComment materialComment = materialCommentService.selectById(materialCommentId);
        model.addAttribute("item",materialComment);
        LogObjectHolder.me().set(materialComment);
        return PREFIX + "materialComment_edit.html";
    }

    /**
     * 获取素材评论列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return materialCommentService.selectList(null);
    }

    /**
     * 新增素材评论
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MaterialComment materialComment) {
        materialCommentService.insert(materialComment);
        return SUCCESS_TIP;
    }

    /**
     * 删除素材评论
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer materialCommentId) {
        materialCommentService.deleteById(materialCommentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改素材评论
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MaterialComment materialComment) {
        materialCommentService.updateById(materialComment);
        return SUCCESS_TIP;
    }

    /**
     * 素材评论详情
     */
    @RequestMapping(value = "/detail/{materialCommentId}")
    @ResponseBody
    public Object detail(@PathVariable("materialCommentId") Integer materialCommentId) {
        return materialCommentService.selectById(materialCommentId);
    }
}
