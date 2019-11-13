package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.CardAddDTO;
import cn.stylefeng.guns.modular.dto.CardDTO;
import cn.stylefeng.guns.modular.dto.CardInfoDTO;
import cn.stylefeng.guns.modular.dto.CategoryTreeDTO;
import cn.stylefeng.guns.modular.dto.MaterialDTO;
import cn.stylefeng.guns.modular.dto.PageListDTO;
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
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
    /**
     * 获取列表
     */
    @PostMapping(value = "/pList")
    @ApiOperation("获取分页列表")
    public ResponseData pList(@RequestBody PageListDTO<Card> pageListDTO) {
        Page<Card> page = new PageFactory<Card>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
        Page<Card> cardPage = cardService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody()));
        PageUtils pageUtils = new PageUtils(cardPage);
        return new SuccessResponseData(pageUtils);
    }
    /**
     * 首页名片
     */
    @GetMapping(value = "/indexCard")
    @ApiOperation("首页名片")
    public ResponseData indexCard() {
        WxUser user = wxUserService.getLoginWxUser();
        if (user == null) {
            return new ErrorResponseData("未授权登录");
        }
        CardDTO cardDto = new CardDTO();
        EntityWrapper<Card> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", user.getId()).orderBy("create_time",false).last("limit 1");
        Card card = cardService.selectOne(wrapper);
        List<Material> materials = new ArrayList<>();
        if (card != null) {
            EntityWrapper<Material> materialEntityWrapper = new EntityWrapper<>();
            materialEntityWrapper.eq("card_id", card.getId());
            materialService.selectList(materialEntityWrapper);
        }
        cardDto.setIsVip(user.getIsvip());
        cardDto.setCard(card);
        cardDto.setMaterialList(materials);
        return new SuccessResponseData(cardDto);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody CardAddDTO addDTO) {
        Integer userId = JwtTokenUtil.getUserId();
//        EntityWrapper<Card> wrapper = new EntityWrapper<>();
//        wrapper.eq("user_id", userId).orderBy("create_time",false).last("limit 1");
//        if (cardService.selectOne(wrapper) != null) {
//            return new ErrorResponseData("不可重复创建名片");
//        }
        Card card = BeanMapperUtil.objConvert(addDTO, Card.class);
        card.setUserId(userId);
        card.setIsDeleted(0);//是否删除（0否，1是）
        card.setCreateTime(new Date());
        card.setUpdateTime(new Date());
        cardService.insert(card);
        return new SuccessResponseData(card);
    }

    /**
     * 新增全部
     */
    @PostMapping(value = "/addAll")
    @ResponseBody
    @ApiOperation("新增全部")
    public Object addAll(@RequestBody CardAddDTO addDTO) {
        Integer userId = JwtTokenUtil.getUserId();
        Card card = BeanMapperUtil.objConvert(addDTO, Card.class);
        card.setUserId(userId);
        card.setIsDeleted(0);//是否删除（0否，1是）
        card.setCreateTime(new Date());
        card.setUpdateTime(new Date());
        cardService.insert(card);
        List<MaterialDTO> materials = addDTO.getMaterials();
        if (!CollectionUtils.isEmpty(materials)) {
            for (MaterialDTO materialDTO : materials) {
                Material material = BeanMapperUtil.objConvert(materialDTO, Material.class);
                material.setUserId(userId);
                material.setCardId(card.getId());
                material.setIsDeleted(0);//是否删除（0否，1是）
                material.setCreateTime(new Date());
                material.setUpdateTime(new Date());
                materialService.insert(material);
            }
        }
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
    public Object update(@RequestBody Card card) {
        cardService.updateById(card);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{cardId}")
    @ResponseBody
    @ApiOperation("详情")
    public Object detail(@PathVariable("cardId") Integer cardId) {
        Card card = cardService.selectById(cardId);
        CardInfoDTO cardInfoDTO = BeanMapperUtil.objConvert(card, CardInfoDTO.class);
        if (card != null) {
            SuccessResponseData responseData = new SuccessResponseData();
            WxUser user = wxUserService.getLoginWxUser();
            Integer isvip = user.getIsvip();
            boolean isMaterialList = false;
            if (isvip != null && isvip.equals(1)) {//是vip
                List<CategoryTreeDTO> treeList = categoryService.getTreeList(cardId);
                if (!CollectionUtils.isEmpty(treeList)) {
                    cardInfoDTO.setCategoryTrees(treeList);
                } else {
                    isMaterialList = true;
                }
            } else {
                isMaterialList = true;
            }
            if (isMaterialList) {
                EntityWrapper<Material> materialEntityWrapper = new EntityWrapper<>();
                materialEntityWrapper.eq("card_id", card.getId());
                List<Material> materials = materialService.selectList(materialEntityWrapper);
                cardInfoDTO.setMaterialList(materials);
            }
            cardInfoDTO.setIsVip(isvip);
            responseData.setData(cardInfoDTO);
            return responseData;
        } else {
            return new ErrorResponseData("未查到有效名片");
        }
    }
}
