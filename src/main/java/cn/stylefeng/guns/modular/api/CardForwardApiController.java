package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.PageUtils;
import cn.stylefeng.guns.modular.dto.PageListDTO;
import cn.stylefeng.guns.modular.system.model.CardForward;
import cn.stylefeng.guns.modular.system.service.ICardForwardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
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

    /**
     * 获取列表
     */
    @PostMapping(value = "/pList")
    @ResponseBody
    @ApiOperation("获取分页列表")
    public ResponseData pList(@RequestBody PageListDTO<CardForward> pageListDTO) {
        Page<CardForward> page = new PageFactory<CardForward>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
        Page<CardForward> pageList = cardForwardService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody()));
        PageUtils pageUtils = new PageUtils(pageList);
        return new SuccessResponseData(pageUtils);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增")
    public Object add(@RequestBody CardForward cardForward) {
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
