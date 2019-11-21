package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.CardForward;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.ICardForwardService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 名片转发打开记录
 *
 * @author fengshuonan
 * @Date 2019-11-07 22:00:14
 */
@Controller
@RequestMapping("/api/cardForward")
@Api(tags = "名片转发打开记录")
public class CardForwardApiController extends BaseController {
    @Autowired
    private ICardForwardService cardForwardService;
    @Autowired
    private IWxUserService wxUserService;
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/pList")
//    @ResponseBody
//    @ApiOperation("获取分页列表")
//    public ResponseData pList(@RequestBody PageListDTO<CardForward> pageListDTO) {
//        Page<CardForward> page = new PageFactory<CardForward>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
//        Page<CardForward> pageList = cardForwardService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody()).orderBy("create_time", false));
//        PageUtils pageUtils = new PageUtils(pageList);
//        return new SuccessResponseData(pageUtils);
//    }
    /**
     * 获取列表
     */
    @PostMapping(value = "/List")
    @ResponseBody
    @ApiOperation("获取列表")
    public ResponseData List(@RequestBody CardForward cardForward) {
        List<CardForward> cardForwards = cardForwardService.selectList(new EntityWrapper<>(cardForward).orderBy("create_time", false));
        return new SuccessResponseData(cardForwards);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody CardForward cardForward) {
        if (cardForward.getCardId() == null || cardForward.getForwarderId() == null || cardForward.getType() == null) {
            return new ErrorResponseData("名片ID,转发者ID,类型 不可为空");
        }
        WxUser loginWxUser = wxUserService.getLoginWxUser();
        if (loginWxUser == null) {
            return new ErrorResponseData("请授权登录");
        }
        cardForward.setUserId(Long.valueOf(loginWxUser.getId()));
        cardForward.setHeadImg(loginWxUser.getHeadimgurl());
        cardForward.setNickname(loginWxUser.getNickName());
        cardForwardService.insert(cardForward);
        return SUCCESS_TIP;
    }

//    /**
//     * 删除
//     */
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam Integer cardForwardId) {
//        cardForwardService.deleteById(cardForwardId);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(CardForward cardForward) {
//        cardForwardService.updateById(cardForward);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 详情
//     */
//    @RequestMapping(value = "/detail/{cardForwardId}")
//    @ResponseBody
//    public Object detail(@PathVariable("cardForwardId") Integer cardForwardId) {
//        return cardForwardService.selectById(cardForwardId);
//    }
}
