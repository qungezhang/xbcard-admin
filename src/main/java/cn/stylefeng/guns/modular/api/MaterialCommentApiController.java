package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.CommentPlistByMaterialIdDto;
import cn.stylefeng.guns.modular.dto.MaterialCommentAddDto;
import cn.stylefeng.guns.modular.dto.MaterialCommentDto;
import cn.stylefeng.guns.modular.dto.MaterialCommentReplyDto;
import cn.stylefeng.guns.modular.system.model.MaterialComment;
import cn.stylefeng.guns.modular.system.model.MaterialCommentReply;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentReplyService;
import cn.stylefeng.guns.modular.system.service.IMaterialCommentService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 素材评论控制器
 *
 * @author qunge
 * @Date 2019-12-17 19:56:00
 */
@Controller
@RequestMapping("/api/materialComment")
@Api(tags = "商品图片评论")
public class MaterialCommentApiController extends BaseController {

    @Autowired
    private IMaterialCommentService materialCommentService;
    @Autowired
    private IMaterialCommentReplyService replyService;
    @Autowired
    private IWxUserService wxUserService;


//    /**
//     * 获取素材评论列表
//     */
//    @ApiOperation("根据商品图片id获取分页评论")
//    @PostMapping(value = "/getPListByMaterialId")
//    @ResponseBody
//    public Object getCommentPListByMaterialId(@RequestBody CommentPlistByMaterialIdDto dto) {
//        if (ToolUtil.isEmpty(dto.getMaterialId())) {
//            return new ErrorResponseData("商品图片id不可为空");
//        }
//        Page<MaterialComment> page = new PageFactory<MaterialComment>().defaultPage(dto.getPageNum(), dto.getPageSize(), "create_time", "desc");
//        EntityWrapper<MaterialComment> wrapper = new EntityWrapper<>();
//        wrapper.eq("material_id", dto.getMaterialId()).eq("is_deleted", 0);
//        PageUtils pageUtils = new PageUtils(materialCommentService.selectPage(page, wrapper));
//        return new SuccessResponseData(pageUtils);
//    }
    /**
     * 获取素材评论列表
     */
    @ApiOperation("根据商品图片id获取分页评论+所有回复")
    @PostMapping(value = "/getCommentAndReplyPListByMaterialId")
    @ResponseBody
    public Object getCommentPListByMaterialIdAndReply(@RequestBody CommentPlistByMaterialIdDto dto) {
        if (ToolUtil.isEmpty(dto.getMaterialId())) {
            return new ErrorResponseData("商品图片id不可为空");
        }
        Page<MaterialComment> page = new PageFactory<MaterialComment>().defaultPage(dto.getPageNum(), dto.getPageSize(), "create_time", "desc");
        EntityWrapper<MaterialComment> wrapper = new EntityWrapper<>();
        wrapper.eq("material_id", dto.getMaterialId()).eq("is_deleted", 0);
        Page<MaterialComment> commentPage = materialCommentService.selectPage(page, wrapper);
        List<MaterialComment> records = commentPage.getRecords();
        List<MaterialCommentDto> commentResults = new ArrayList<>();
        if (ToolUtil.isNotEmpty(records)) {
            for (MaterialComment record : records) {
                MaterialCommentDto materialCommentDto = BeanMapperUtil.objConvert(record, MaterialCommentDto.class);
                List<MaterialCommentReplyDto> replyList = new ArrayList<>();
                EntityWrapper<MaterialCommentReply> replyEntityWrapper = new EntityWrapper<>();
                replyEntityWrapper.eq("comment_id", record.getId()).eq("is_deleted", 0);
                List<MaterialCommentReply> commentReplyList = replyService.selectList(replyEntityWrapper);
                if (ToolUtil.isNotEmpty(commentReplyList)) {
                    replyList= BeanMapperUtil.mapList(commentReplyList, MaterialCommentReplyDto.class);
                }
                materialCommentDto.setReplyList(replyList);
                commentResults.add(materialCommentDto);
            }
        }
        PageUtils pageUtils = new PageUtils(commentPage);
        pageUtils.setList(commentResults);
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 新增素材评论
     */
    @ApiOperation("新增商品评论")
    @PostMapping(value = "/add")
    @ResponseBody
    public Object addComment(@RequestBody @Valid MaterialCommentAddDto addDto) {
        WxUser wxUser = wxUserService.selectById(addDto.getUserId());
        if (wxUser == null) {
            return new ErrorResponseData("用户(评论者)不存在");
        }
        MaterialComment materialComment = BeanMapperUtil.objConvert(addDto, MaterialComment.class);
        materialComment.setCreateBy(wxUser.getMobile());
        materialComment.setNickname(wxUser.getNickName());
        materialComment.setHeadImg(wxUser.getHeadimgurl());
        materialComment.setCreateTime(new Date());
        materialComment.setUpdateTime(new Date());
        materialComment.setIsDeleted(0);
        materialCommentService.insert(materialComment);
        return SUCCESS_TIP;
    }

    /**
     * 删除素材评论
     */
    @ApiOperation("删除商品评论")
    @GetMapping(value = "/delete")
    @ResponseBody
    public Object deleteComment(@RequestParam Integer materialCommentId) {
        if (ToolUtil.isEmpty(materialCommentId)) {
            return new ErrorResponseData("商品图片评论id不可为空");
        }
        materialCommentService.deleteById(materialCommentId);
        return SUCCESS_TIP;
    }

//    /**
//     * 修改素材评论
//     */
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(@RequestBody @Valid MaterialCommentUpdateDto updateDto) {
//        MaterialComment materialComment = BeanMapperUtil.objConvert(updateDto, MaterialComment.class);
//        materialCommentService.updateById(materialComment);
//        return SUCCESS_TIP;
//    }

//    /**
//     * 素材评论详情
//     */
//    @RequestMapping(value = "/detail/{materialCommentId}")
//    @ResponseBody
//    public Object detail(@PathVariable("materialCommentId") Integer materialCommentId) {
//        return materialCommentService.selectById(materialCommentId);
//    }


}
