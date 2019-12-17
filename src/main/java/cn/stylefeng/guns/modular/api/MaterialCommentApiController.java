package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.system.model.MaterialComment;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 素材评论控制器
 *
 * @author qunge
 * @Date 2019-12-17 19:56:00
 */
@Controller
@RequestMapping("/api/materialComment")
@Api(tags = "素材评论")
public class MaterialCommentApiController extends BaseController {

    @Autowired
    private IMaterialCommentService materialCommentService;


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
