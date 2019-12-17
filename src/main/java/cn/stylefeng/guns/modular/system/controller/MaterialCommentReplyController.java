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
import cn.stylefeng.guns.modular.system.model.MaterialCommentReply;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentReplyService;

/**
 * 素材评论回复控制器
 *
 * @author fengshuonan
 * @Date 2019-12-17 19:56:19
 */
@Controller
@RequestMapping("/materialCommentReply")
public class MaterialCommentReplyController extends BaseController {

    private String PREFIX = "/system/materialCommentReply/";

    @Autowired
    private IMaterialCommentReplyService materialCommentReplyService;

    /**
     * 跳转到素材评论回复首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "materialCommentReply.html";
    }

    /**
     * 跳转到添加素材评论回复
     */
    @RequestMapping("/materialCommentReply_add")
    public String materialCommentReplyAdd() {
        return PREFIX + "materialCommentReply_add.html";
    }

    /**
     * 跳转到修改素材评论回复
     */
    @RequestMapping("/materialCommentReply_update/{materialCommentReplyId}")
    public String materialCommentReplyUpdate(@PathVariable Integer materialCommentReplyId, Model model) {
        MaterialCommentReply materialCommentReply = materialCommentReplyService.selectById(materialCommentReplyId);
        model.addAttribute("item",materialCommentReply);
        LogObjectHolder.me().set(materialCommentReply);
        return PREFIX + "materialCommentReply_edit.html";
    }

    /**
     * 获取素材评论回复列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return materialCommentReplyService.selectList(null);
    }

    /**
     * 新增素材评论回复
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MaterialCommentReply materialCommentReply) {
        materialCommentReplyService.insert(materialCommentReply);
        return SUCCESS_TIP;
    }

    /**
     * 删除素材评论回复
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer materialCommentReplyId) {
        materialCommentReplyService.deleteById(materialCommentReplyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改素材评论回复
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MaterialCommentReply materialCommentReply) {
        materialCommentReplyService.updateById(materialCommentReply);
        return SUCCESS_TIP;
    }

    /**
     * 素材评论回复详情
     */
    @RequestMapping(value = "/detail/{materialCommentReplyId}")
    @ResponseBody
    public Object detail(@PathVariable("materialCommentReplyId") Integer materialCommentReplyId) {
        return materialCommentReplyService.selectById(materialCommentReplyId);
    }
}
