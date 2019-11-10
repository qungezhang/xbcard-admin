//package cn.stylefeng.guns.modular.api;
//
//import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
//import cn.stylefeng.guns.core.util.PageUtils;
//import cn.stylefeng.guns.modular.dto.PageListDTO;
//import cn.stylefeng.guns.modular.system.model.Category;
//import cn.stylefeng.guns.modular.system.model.WxUser;
//import cn.stylefeng.guns.modular.system.service.IWxUserService;
//import cn.stylefeng.roses.core.base.controller.BaseController;
//import cn.stylefeng.roses.core.reqres.response.ResponseData;
//import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 控制器
// *
// * @author qunge
// * @Date 2019-11-04 21:57:52
// */
//@Controller
//@RequestMapping("/api/wxUser")
//@Api(tags = "微信用户")
//public class WxUserApiController extends BaseController {
//
//
//    @Autowired
//    private IWxUserService wxUserService;
//
//    /**
//     * 获取列表
//     */
//    @PostMapping(value = "/pList")
//    @ApiOperation("获取分页列表")
//    @ResponseBody
//    public ResponseData pList(@RequestBody PageListDTO<WxUser> pageListDTO) {
//        Page<WxUser> page = new PageFactory<WxUser>().defaultPage(pageListDTO.getPageNum(), pageListDTO.getPageSize(), null, null);
//        PageUtils pageUtils = new PageUtils(wxUserService.selectPage(page, new EntityWrapper<>(pageListDTO.getBody())));
//        return new SuccessResponseData(pageUtils);
//    }
//    /**
//     * 新增
//     */
//    @PostMapping(value = "/add")
//    @ResponseBody
//    @ApiOperation("新增")
//    public Object add(WxUser wxUser) {
//        wxUserService.insert(wxUser);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 删除
//     */
//    @GetMapping(value = "/delete")
//    @ResponseBody
//    @ApiOperation("删除")
//    public Object delete(@RequestParam Integer wxUserId) {
//        wxUserService.deleteById(wxUserId);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 修改
//     */
//    @PostMapping(value = "/update")
//    @ResponseBody
//    @ApiOperation("修改")
//    public Object update(WxUser wxUser) {
//        wxUserService.updateById(wxUser);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 详情
//     */
//    @GetMapping(value = "/detail/{wxUserId}")
//    @ResponseBody
//    @ApiOperation("详情")
//    public Object detail(@PathVariable("wxUserId") Integer wxUserId) {
//        return wxUserService.selectById(wxUserId);
//    }
//}
