package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.service.ICardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody Card card) {
        cardService.insert(card);
        return SUCCESS_TIP;
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
        return cardService.selectById(cardId);
    }
}
