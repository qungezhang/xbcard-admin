package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.service.ICardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @author fengshuonan
 * @Date 2019-11-04 16:18:06
 */
@RestController
@RequestMapping("/api/card")
@Api(tags = "xxxx")
public class CardApiController extends BaseController {



    @Autowired
    private ICardService cardService;

    /**
     * 获取列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("获取分页列表")
    public Object list(Integer pageNum, Integer pageSize) {
        Page<Card> page = new PageFactory<Card>().defaultPage(pageNum, pageSize, null, null);
        Page<Card> cardPage = cardService.selectPage(page, null);
        PageUtils pageUtils = new PageUtils(cardPage);
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Card card) {
        cardService.insert(card);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardId) {
        cardService.deleteById(cardId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Card card) {
        cardService.updateById(card);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{cardId}")
    @ResponseBody
    public Object detail(@PathVariable("cardId") Integer cardId) {
        return cardService.selectById(cardId);
    }
}
