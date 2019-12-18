package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.CommentReplyPListByCommentIdDto;
import cn.stylefeng.guns.modular.dto.MaterialCommentReplyAddDto;
import cn.stylefeng.guns.modular.system.model.MaterialCommentReply;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentReplyService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * 商品图片评论回复控制器
 *
 * @author qunge
 * @Date 2019-12-17 19:56:00
 */
@Controller
@RequestMapping("/api/materialCommentReply")
@Api(tags = "商品图片评论回复")
public class MaterialCommentReplyApiController extends BaseController {

    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private IMaterialCommentReplyService replyService;


//    /**
//     * 根据评论id获取分页回复
//     */
//    @ApiOperation("根据评论id获取分页回复")
//    @PostMapping(value = "/getPListByCommentId")
//    @ResponseBody
//    public Object getPListByCommentId(@RequestBody CommentReplyPListByCommentIdDto dto) {
//        if (ToolUtil.isEmpty(dto.getCommentId())) {
//            return new ErrorResponseData("评论id不可为空");
//        }
//        Page<MaterialCommentReply> page = new PageFactory<MaterialCommentReply>().defaultPage(dto.getPageNum(), dto.getPageSize(), "create_time", "asc");
//        EntityWrapper<MaterialCommentReply> wrapper = new EntityWrapper<>();
//        wrapper.eq("comment_id", dto.getCommentId()).eq("is_deleted", 0);
//        PageUtils pageUtils = new PageUtils(replyService.selectPage(page, wrapper));
//        return new SuccessResponseData(pageUtils);
//    }

    /**
     * 新增商品评论回复
     */
    @ApiOperation("新增商品评论回复")
    @PostMapping(value = "/add")
    @ResponseBody
    public Object addCommentReply(@RequestBody @Valid MaterialCommentReplyAddDto addDto) {
        WxUser wxUser = wxUserService.selectById(addDto.getUserId());
        if (wxUser == null) {
            return new ErrorResponseData("用户(回复者)不存在");
        }
        MaterialCommentReply commentReply = BeanMapperUtil.objConvert(addDto, MaterialCommentReply.class);
        commentReply.setCreateBy(wxUser.getMobile());
        commentReply.setNickname(wxUser.getNickName());
        commentReply.setHeadImg(wxUser.getHeadimgurl());
        commentReply.setCreateTime(new Date());
        commentReply.setUpdateTime(new Date());
        commentReply.setIsDeleted(0);
        replyService.insert(commentReply);
        return SUCCESS_TIP;
    }

    /**
     * 删除素材评论回复
     */
    @ApiOperation("删除商品评论回复")
    @GetMapping(value = "/delete")
    @ResponseBody
    public Object deleteCommentReply(@RequestParam Integer materialCommentReplyId) {
        if (ToolUtil.isEmpty(materialCommentReplyId)) {
            return new ErrorResponseData("商品图片评论回复id不可为空");
        }
        replyService.deleteById(materialCommentReplyId);
        return SUCCESS_TIP;
    }
}
