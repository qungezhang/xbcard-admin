package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.ForwardPageDTO;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.model.CardForward;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.ICardForwardService;
import cn.stylefeng.guns.modular.system.service.ICardService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名片转发打开记录
 *
 * @author fengshuonan
 * @Date 2019-11-07 22:00:14
 */
@Controller
@RequestMapping("/api/cardForward")
@Api(tags = "名片转发打开记录")
@Slf4j
public class CardForwardApiController extends BaseController {
    @Autowired
    private ICardForwardService cardForwardService;
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private ICardService cardService;
    /**
     * 获取列表
     */
    @PostMapping(value = "/pList")
    @ResponseBody
    @ApiOperation("获取分页列表")
    public ResponseData pList(@RequestBody ForwardPageDTO dto) {
        if (dto.getType() == null) {
            return new ErrorResponseData("类型不可为空（1查看我 2收藏我 3我收藏）");
        }
        Integer userId = JwtTokenUtil.getUserId();
        EntityWrapper<CardForward> wrapper = new EntityWrapper<>();
        switch (dto.getType()) {
            case 1 :
                wrapper.eq("type",2).eq("forwarder_id", userId).groupBy("user_id");
                break;
            case 2 :
                wrapper.eq("type",3).eq("forwarder_id", userId).groupBy("user_id");
                break;
            case 3 :
                wrapper.eq("type",3).eq("user_id", userId);
                break;
            default: break;
        }

        Page<CardForward> page = new PageFactory<CardForward>().defaultPage(dto.getPageNum(), dto.getPageSize(), null, null);
        Page<CardForward> pageList = cardForwardService.selectPage(page, wrapper.orderBy("create_time",false));
        PageUtils pageUtils = new PageUtils(pageList);
        return new SuccessResponseData(pageUtils);
    }
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/List")
//    @ResponseBody
//    @ApiOperation("获取列表")
//    public ResponseData List(@RequestBody CardForward cardForward) {
//        List<CardForward> cardForwards = cardForwardService.selectList(new EntityWrapper<>(cardForward).orderBy("create_time", false));
//        return new SuccessResponseData(cardForwards);
//    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody CardForward cardForward) {
        Long userId = cardForward.getUserId();
        Long cardId = cardForward.getCardId();
        Integer type = cardForward.getType();
        if (cardId == null || userId == null || cardForward.getForwarderId() == null || type == null) {
            return new ErrorResponseData("名片ID,当前者ID,转发者ID,类型 不可为空");
        }
        SuccessResponseData successTip = SUCCESS_TIP;
        Map<String, Object> map = new HashMap<>();
        int btnStatus = 0;// (0正常 1自己 2已收藏 )
        if ((String.valueOf(userId)).equals(String.valueOf(JwtTokenUtil.getUserId()))) {
            btnStatus = 1;
            map.put("btnStatus", btnStatus);
            successTip.setData(map);
            log.info("++++++++++btnStatus============  " + btnStatus);
            return successTip;
        }
//        WxUser loginWxUser = wxUserService.getLoginWxUser();
        CardForward forward = new CardForward();
        forward.setUserId(userId);
        forward.setForwarderId(cardForward.getForwarderId());
        boolean isNull = true;
        if (type.equals(2)) {//浏览
            List<CardForward> cardForwards = cardForwardService.selectList(new EntityWrapper<>(forward).orderBy("create_time", false));
            if (ToolUtil.isNotEmpty(cardForwards)) {
                for (CardForward cardForward1 : cardForwards) {
                    if (cardForward1.getType().equals(3) && cardForward1.getCardId().equals(cardId)) {//收藏
                        btnStatus = 2;
//                        log.info("收藏标记++++++=====" + cardForward1);
                    }
                    if (cardForward1.getType().equals(type)) {
//                        log.info("已有浏览记录++++++=====" + cardForward1);
                        isNull = false;
                    }
                }
            }
        } else if (type.equals(3)) {//收藏
            forward.setCardId(cardId);
            forward.setType(type);
            CardForward selectOne = cardForwardService.selectOne(new EntityWrapper<>(forward).orderBy("create_time", false).last("limit 1"));
            if (selectOne != null) {
                isNull = false;
//                log.info("已有收藏记录++++++=====" + selectOne);
            } else {
                Card card = cardService.selectById(cardId);
                if (card == null) {
                    return new ErrorResponseData("名片不存在");
                }
                cardForward.setCardLogo(card.getLogo());
                cardForward.setCardCompany(card.getCompany());
                cardForward.setCardName(card.getName());
                cardForward.setCardPosition(card.getPosition());
            }
        }

        if (isNull) {
            WxUser wxUser = wxUserService.selectById(userId);
            if (wxUser == null) {
                return new ErrorResponseData("当前用户不存在 请查看userId参数");
            }
            cardForward.setUserId(userId);
            cardForward.setHeadImg(wxUser.getHeadimgurl());
            cardForward.setNickname(wxUser.getNickName());
            cardForward.setOpenid(wxUser.getOpenid());
            cardForward.setCreateTime(new Date());
            cardForwardService.insert(cardForward);
        }
        map.put("btnStatus", btnStatus);
        log.info("++++++++++btnStatus============  " + btnStatus);
        successTip.setData(map);
        return successTip;
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
