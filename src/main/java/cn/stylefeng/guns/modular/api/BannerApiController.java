package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.qiniu.QiniuService;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.dto.BannerAddDto;
import cn.stylefeng.guns.modular.system.model.Banner;
import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.service.IBannerService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qiniu.common.QiniuException;
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
 * 轮播
 *
 * @author fengshuonan
 * @Date 2019-11-13 19:46:17
 */
@RestController
@RequestMapping("/api/banner")
@Api(tags = "轮播")
public class BannerApiController extends BaseController {
    @Autowired
    private IBannerService bannerService;
    @Autowired
    private QiniuService qiniuService;
    /**
     * 获取首页轮播列表
     */
    @GetMapping(value = "/list")
    @ApiOperation("获取首页轮播列表")
    public Object list(@RequestParam("cardId") Integer cardId) {
        Banner banner = new Banner();
        banner.setIsDeleted(0);
        banner.setCardId(cardId);
        banner.setUserId(JwtTokenUtil.getUserId());
        SuccessResponseData responseData = new SuccessResponseData();
        responseData.setData(bannerService.selectList(new EntityWrapper<>(banner).orderBy("create_time", false)));
        return responseData;
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public Object add(@RequestBody @Valid BannerAddDto addDto) {
        Banner banner = BeanMapperUtil.objConvert(addDto, Banner.class);
        banner.setUserId(JwtTokenUtil.getUserId());
        //是否删除（0否，1是）
        banner.setIsDeleted(0);
        banner.setCreateTime(new Date());
        banner.setUpdateTime(new Date());
        bannerService.insert(banner);
        SuccessResponseData success = SUCCESS_TIP;
        success.setData(banner);
        return success;
    }

    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public Object delete(@RequestParam Integer bannerId) {
        bannerService.deleteById(bannerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public Object update(@RequestBody Banner banner) throws QiniuException {
        Integer id = banner.getId();
        if (id == null || banner.getCardId() == null) {
            return new ErrorResponseData("id和cardId不可为空");
        }

        String imgUrl = banner.getImgUrl();
        if (ToolUtil.isNotEmpty(imgUrl)) {
            Banner selectById = bannerService.selectById(id);
            if (ToolUtil.isNotEmpty(selectById)) {
                String qiniKeyByUrled = StringUtil.getQiniKeyByUrl(selectById.getImgUrl());
                if (ToolUtil.isNotEmpty(qiniKeyByUrled) && !qiniKeyByUrled.equals(StringUtil.getQiniKeyByUrl(imgUrl))) {
                    qiniuService.delete(qiniKeyByUrled);
                }
            } else {
                return new ErrorResponseData("信息不存在");
            }
        }

        bannerService.updateById(banner);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{bannerId}")
    @ApiOperation("详情")
    public Object detail(@PathVariable("bannerId") Integer bannerId) {
        return bannerService.selectById(bannerId);
    }
}
