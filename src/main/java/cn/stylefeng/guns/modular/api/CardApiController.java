package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.dto.CardAddDTO;
import cn.stylefeng.guns.modular.dto.CardDTO;
import cn.stylefeng.guns.modular.dto.CardInfoDTO;
import cn.stylefeng.guns.modular.dto.CardUpdateDTO;
import cn.stylefeng.guns.modular.dto.CategoryTreeDTO;
import cn.stylefeng.guns.modular.dto.MaterialDTO;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.model.Material;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.ICardService;
import cn.stylefeng.guns.modular.system.service.ICategoryService;
import cn.stylefeng.guns.modular.system.service.IMaterialService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiniu.common.QiniuException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 控制器
 *
 * @author qunge
 * @Date 2019-11-04 16:18:06
 */
@RestController
@RequestMapping("/api/card")
@Api(tags = "名片基础信息")
public class CardApiController extends BaseController {

    @Autowired
    private ICardService cardService;
    @Autowired
    private IWxUserService wxUserService;
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private QiniuService qiniuService;
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/pList")
//    @ApiOperation("获取分页列表")
//    public ResponseData pList(@RequestBody PageListDTO<Card> pageListDTO) {
//        Page<Card> page = new PageFactory<Card>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
//        Page<Card> cardPage = cardService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody()));
//        PageUtils pageUtils = new PageUtils(cardPage);
//        return new SuccessResponseData(pageUtils);
//    }
    /**
     * 获取列表
     */
    @GetMapping(value = "/list")
    @ApiOperation("获取列表")
    public ResponseData list() {
        WxUser user = wxUserService.getLoginWxUser();
        if (user == null) {
            return new ErrorResponseData("未授权登录");
        }
        EntityWrapper<Card> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", user.getId()).orderBy("create_time", false);
        List<Card> cards = cardService.selectList(wrapper);
        return new SuccessResponseData(cards);
    }

    /**
     * 选择名片
     */
    @GetMapping(value = "/bindingUser")
    @ApiOperation("选择名片")
    public ResponseData bindingUser(@RequestParam("cardId") Integer cardId) {
        WxUser user = wxUserService.getLoginWxUser();
        if (user == null) {
            return new ErrorResponseData("未授权登录");
        }
        user.setCardId(cardId);
        return new SuccessResponseData(wxUserService.updateById(user));
    }
//
//    /**
//     * 首页名片
//     */
//    @GetMapping(value = "/indexCard")
//    @ApiOperation("首页名片")
//    public ResponseData indexCard() {
//        WxUser user = wxUserService.getLoginWxUser();
//        if (user == null) {
//            return new ErrorResponseData("未授权登录");
//        }
//        CardDTO cardDto = new CardDTO();
//        Integer cardId = user.getCardId();
//        if (ToolUtil.isNotEmpty(cardId)) {
//            Card card = cardService.getOneByCardId(cardId);
////            if (card != null) {
////                EntityWrapper<Material> materialEntityWrapper = new EntityWrapper<>();
////                materialEntityWrapper.eq("card_id", card.getId());
////                List<Material> materialList = materialService.selectList(materialEntityWrapper);
////                cardDto.setMaterialList(materialList);
////            }
//            cardDto.setCard(card);
//        }
//        cardDto.setIsVip(user.getIsvip());
//        return new SuccessResponseData(cardDto);
//    }

//    /**
//     * 新增
//     */
//    @PostMapping(value = "/add")
//    @ResponseBody
//    @ApiOperation("新增")
//    public Object add(@RequestBody CardAddDTO addDTO) {
////        Integer userId = JwtTokenUtil.getUserId();
////        EntityWrapper<Card> wrapper = new EntityWrapper<>();
////        wrapper.eq("user_id", userId).orderBy("create_time",false).last("limit 1");
////        if (cardService.selectOne(wrapper) != null) {
////            return new ErrorResponseData("不可重复创建名片");
////        }
//        WxUser loginWxUser = wxUserService.getLoginWxUser();
//        if (loginWxUser == null) {
//            return new ErrorResponseData("用户授权异常");
//        }
//        Card card = BeanMapperUtil.objConvert(addDTO, Card.class);
//        card.setUserId(loginWxUser.getId());
//        card.setIsDeleted(0);//是否删除（0否，1是）
//        card.setCreateTime(new Date());
//        card.setUpdateTime(new Date());
//        cardService.insert(card);
//        loginWxUser.setMobile(card.getMobile());
//        wxUserService.updateById(loginWxUser);
//        return new SuccessResponseData(card);
//    }

    /**
     * 新增全部
     */
    @PostMapping(value = "/addAll")
    @ResponseBody
    @ApiOperation("新增全部")
    public Object addAll(@RequestBody CardAddDTO addDTO) {
        WxUser loginWxUser = wxUserService.getLoginWxUser();
        if (loginWxUser == null) {
            return new ErrorResponseData("用户授权异常");
        }
        EntityWrapper<Card> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", loginWxUser.getId());
        List<Card> cards = cardService.selectList(wrapper);
        if (cards.size() >= 5) {
            return new ErrorResponseData("每人最多可建5张名片");
        }
        Card card = BeanMapperUtil.objConvert(addDTO, Card.class);
        Integer userId = loginWxUser.getId();
        card.setUserId(userId);
        card.setIsDeleted(0);//是否删除（0否，1是）
        card.setCreateTime(new Date());
        card.setUpdateTime(new Date());
        cardService.insert(card);
        loginWxUser.setMobile(card.getMobile());
        loginWxUser.setCardId(card.getId());
        wxUserService.updateById(loginWxUser);
//        List<MaterialDTO> materials = addDTO.getMaterials();
//        if (!CollectionUtils.isEmpty(materials)) {
//            for (MaterialDTO materialDTO : materials) {
//                Material material = BeanMapperUtil.objConvert(materialDTO, Material.class);
//                material.setUserId(userId);
//                material.setCardId(card.getId());
//                material.setIsDeleted(0);//是否删除（0否，1是）
//                material.setCreateTime(new Date());
//                material.setUpdateTime(new Date());
//                materialService.insert(material);
//            }
//        }
        return new SuccessResponseData(card);
    }

    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    @ApiOperation("删除")
    public Object delete(@RequestParam Integer cardId) {
        cardService.deleteById(cardId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @ApiOperation("修改")
    public Object update(@RequestBody CardUpdateDTO updateDTO) throws QiniuException {
        WxUser loginWxUser = wxUserService.getLoginWxUser();
        if (loginWxUser == null) {
            return new ErrorResponseData("用户授权异常");
        }
        Card card = BeanMapperUtil.objConvert(updateDTO, Card.class);
        Integer cardId = card.getId();
        if (card.getId() == null) {
            return new ErrorResponseData("名片Id不可为空");
        }
        String logo = card.getLogo();
        String shareImgUrl = card.getShareImgUrl();
        if (ToolUtil.isNotEmpty(logo)||ToolUtil.isNotEmpty(shareImgUrl)) {
            Card selectById = cardService.selectById(cardId);
            if (ToolUtil.isNotEmpty(selectById)) {
                String qiniKeyByUrled = StringUtil.getQiniKeyByUrl(selectById.getLogo());
                if (ToolUtil.isNotEmpty(qiniKeyByUrled) && !qiniKeyByUrled.equals(StringUtil.getQiniKeyByUrl(logo))) {
                    qiniuService.delete(qiniKeyByUrled);
                }
                String shareImgUrlKey = StringUtil.getQiniKeyByUrl(selectById.getShareImgUrl());
                if (ToolUtil.isNotEmpty(shareImgUrlKey) && !shareImgUrlKey.equals(StringUtil.getQiniKeyByUrl(shareImgUrl))) {
                    qiniuService.delete(shareImgUrlKey);
                }
            } else {
                return new ErrorResponseData("信息不存在");
            }
        }

        card.setUpdateTime(new Date());
        cardService.updateById(card);
        loginWxUser.setMobile(card.getMobile());
        wxUserService.updateById(loginWxUser);


        return new SuccessResponseData(card);
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail")
    @ApiOperation("详情")
    public Object detail(@RequestParam(value = "cardId", required = false) Integer cardId, @RequestParam(value = "userId", required = false) Integer userId) {
        Card card = null;
        WxUser user = null;
        if (ToolUtil.isNotEmpty(cardId)) {
            card = cardService.selectById(cardId);
            if (card == null) {
                return new ErrorResponseData("未查到有效名片");
            }
            user = wxUserService.selectById(card.getUserId());
            if (user == null) {
                return new ErrorResponseData("未查到有效用户");
            }
        } else if (ToolUtil.isNotEmpty(userId)) {
            user = wxUserService.selectById(userId);
            if (user == null) {
                return new ErrorResponseData("未查到有效用户");
            }
            card = cardService.selectById(user.getCardId());
            if (card == null) {
                return new ErrorResponseData("未查到有效名片");
            }
        } else {
            return new ErrorResponseData("userId或cardId二者必传一个");
        }

        CardInfoDTO cardInfoDTO = BeanMapperUtil.objConvert(card, CardInfoDTO.class);
        SuccessResponseData responseData = new SuccessResponseData();
        Integer isvip = user.getIsvip();
        if (isvip == 1 && System.currentTimeMillis() > user.getVipEndTime().getTime()) {
            isvip = 0;
        }
        cardInfoDTO.setIsVip(isvip);
        responseData.setData(cardInfoDTO);
        return responseData;
    }
//    /**
//     * 根据用户id获取首页名片
//     */
//    @GetMapping(value = "/indexCardByCardId")
//    @ApiOperation("根据cardId获取首页名片")
//    public ResponseData indexCardByUserId(@RequestParam("cardId") Integer cardId) {
//        if (ToolUtil.isEmpty(cardId)) {
//            return new ErrorResponseData("名片id不可为空");
//        }
////        WxUser wxUser = wxUserService.selectById(userId);
////        if (ToolUtil.isEmpty(wxUser)) {
////            return new ErrorResponseData("用户不存在");
////        }
//        CardDTO cardDto = new CardDTO();
//        Card card = cardService.getOneByCardId(cardId);
////        cardDto.setIsVip(wxUser.getIsvip());
//        cardDto.setCard(card);
//        return new SuccessResponseData(cardDto);
//    }

//    /**
//     * 根据用户id获取名片详情
//     */
//    @GetMapping(value = "/detailCardByCardId")
//    @ApiOperation("根据用户id获取名片详情")
//    public ResponseData detailCardByUserId(@RequestParam("cardId") Integer cardId) {
//        if (ToolUtil.isEmpty(cardId)) {
//            return new ErrorResponseData("名片id不可为空");
//        }
////        WxUser wxUser = wxUserService.selectById(cardId);
////        if (ToolUtil.isEmpty(wxUser)) {
////            return new ErrorResponseData("用户不存在");
////        }
//        Card card = cardService.getOneByCardId(cardId);
//        CardInfoDTO cardInfoDTO = BeanMapperUtil.objConvert(card, CardInfoDTO.class);
//        if (card != null) {
//            SuccessResponseData responseData = new SuccessResponseData();
//            responseData.setData(cardInfoDTO);
//            return responseData;
//        } else {
//            return new ErrorResponseData("未查到有效名片");
//        }
//    }

}
