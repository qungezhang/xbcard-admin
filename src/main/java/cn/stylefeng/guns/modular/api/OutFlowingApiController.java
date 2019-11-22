package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.system.model.OutFlowing;
import cn.stylefeng.guns.modular.system.service.IOutFlowingService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台支出流水控制器
 *
 * @author fengshuonan
 * @Date 2019-11-22 19:31:37
 */
@RestController
@RequestMapping("/api/outFlowing")
public class OutFlowingApiController extends BaseController {

    @Autowired
    private IOutFlowingService outFlowingService;

    /**
     * 获取平台支出流水列表
     */
    @GetMapping(value = "/list")
    public Object list(String condition) {
        return outFlowingService.selectList(null);
    }

    /**
     * 新增平台支出流水
     */
    @PostMapping(value = "/add")
    public Object add(@RequestBody OutFlowing outFlowing) {
        outFlowingService.insert(outFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 删除平台支出流水
     */
    @GetMapping(value = "/delete")
    public Object delete(@RequestParam Integer outFlowingId) {
        outFlowingService.deleteById(outFlowingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改平台支出流水
     */
    @PostMapping(value = "/update")
    public Object update(@RequestBody OutFlowing outFlowing) {
        outFlowingService.updateById(outFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 平台支出流水详情
     */
    @GetMapping(value = "/detail/{outFlowingId}")
    public Object detail(@PathVariable("outFlowingId") Integer outFlowingId) {
        return outFlowingService.selectById(outFlowingId);
    }
}
