package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.dto.PromotionAddDto;
import cn.stylefeng.guns.modular.dto.PromotionUpdateDto;
import cn.stylefeng.guns.modular.system.model.Promotion;
import cn.stylefeng.guns.modular.system.service.IPromotionService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * 宣传文案控制器
 *
 * @author qunge
 * @Date 2020-01-05 20:28:01
 */
@RestController
@RequestMapping("/api/promotion")
@Api(tags = "宣传文案")
public class PromotionApiController extends BaseController {

    @Autowired
    private IPromotionService promotionService;


//    /**
//     * 获取宣传文案列表
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(String condition) {
//        return promotionService.selectList(null);
//    }

    /**
     * 新增宣传文案
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public Object add(@RequestBody @Valid PromotionAddDto addDto) {
        Promotion promotion = BeanMapperUtil.objConvert(addDto, Promotion.class);
        promotion.setUserId(JwtTokenUtil.getUserId());
        //是否删除（0否，1是）
        promotion.setIsDeleted(0);
        promotion.setCreateTime(new Date());
        promotion.setUpdateTime(new Date());
        promotionService.insert(promotion);
        return SUCCESS_TIP;
    }

    /**
     * 删除宣传文案
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Object delete(@RequestParam Integer id) {
        promotionService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改宣传文案
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public Object update(@RequestBody @Valid PromotionUpdateDto updateDto) {
        Promotion promotion = BeanMapperUtil.objConvert(updateDto, Promotion.class);
        promotion.setUpdateTime(new Date());
        promotionService.updateById(promotion);
        return SUCCESS_TIP;
    }

    /**
     * 宣传文案详情
     */
    @GetMapping(value = "/detailByCardId")
    @ApiOperation("详情")
    public Object detail(@RequestParam("cardId") Integer cardId) {
        if (ToolUtil.isEmpty(cardId)) {
            return new ErrorResponseData("cardId 不可为空");
        }
        EntityWrapper<Promotion> wrapper = new EntityWrapper<>();
        wrapper.eq("card_id", cardId)
                .eq("user_id", JwtTokenUtil.getUserId())
                .eq("is_deleted", 0)
                .orderBy("update_time", false)
                .last("limit 1");
        Promotion promotion = promotionService.selectOne(wrapper);
        SuccessResponseData responseData = new SuccessResponseData();
        responseData.setData(promotion);
        return responseData;
    }
}
