package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.dto.CallCenterAddDto;
import cn.stylefeng.guns.modular.dto.CallCenterUpdateLevelDto;
import cn.stylefeng.guns.modular.system.model.CallCenter;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.ICallCenterService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * 客服中心记录控制器
 *
 * @author qunge
 * @Date 2019-12-04 17:40:17
 */
@RestController
@RequestMapping("/api/callCenter")
@Api(tags = "客服中心")
public class CallCenterApiController extends BaseController {

    @Autowired
    private ICallCenterService callCenterService;
    @Autowired
    private IWxUserService wxUserService;

    /**
     * 获取客服中心记录列表
     */
    @GetMapping(value = "/list")
    @ApiOperation("客服中心记录")
    public Object list(@RequestParam("userId") String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ErrorResponseData("用户id不可为空");
        }
        SuccessResponseData responseData = new SuccessResponseData();
        EntityWrapper<CallCenter> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId).eq("is_deleted", 0).orderBy("create_time", true);
        responseData.setData(callCenterService.selectList(wrapper));
        return responseData;
    }

    /**
     * 新增客服中心记录
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public Object add(@RequestBody @Valid CallCenterAddDto dto) {
        WxUser loginWxUser = wxUserService.getLoginWxUser();
        CallCenter callCenter = new CallCenter();
        callCenter.setContent(dto.getContent());
        callCenter.setContentImg(dto.getContentImg());
        callCenter.setMobile(loginWxUser.getMobile());
        callCenter.setStatus(0);
        callCenter.setUserId(loginWxUser.getId());
        callCenter.setIsDeleted(0);
        callCenter.setCreateBy(loginWxUser.getMobile());
        callCenter.setCreateTime(new Date());
        callCenter.setUpdateTime(new Date());
        callCenterService.insert(callCenter);
        return SUCCESS_TIP;
    }

//    /**
//     * 删除客服中心记录
//     */
//    @GetMapping(value = "/delete")
//    public Object delete(@RequestParam Integer callCenterId) {
//        callCenterService.deleteById(callCenterId);
//        return SUCCESS_TIP;
//    }
//
    /**
     * 评价等级
     */
    @PostMapping(value = "/updateLevel")
    @ApiOperation("评价等级")
    public Object updateLevel(@RequestBody @Valid CallCenterUpdateLevelDto updateLevelDto) {
        CallCenter callCenter = new CallCenter();
        callCenter.setId(updateLevelDto.getId());
        callCenter.setValuationLevel(updateLevelDto.getValuationLevel());
        callCenterService.updateById(callCenter);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @GetMapping(value = "/detail/{callCenterId}")
    @ApiOperation("详情")
    public Object detail(@PathVariable("callCenterId") Integer callCenterId) {
        SuccessResponseData responseData = new SuccessResponseData();
        responseData.setData(callCenterService.selectById(callCenterId));
        return responseData;
    }
}
