package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收入流水控制器
 *
 * @author fengshuonan
 * @Date 2019-11-22 15:01:30
 */
@RestController
@RequestMapping("/api/incomeFlowing")
public class IncomeFlowingApiController extends BaseController {

    @Autowired
    private IIncomeFlowingService incomeFlowingService;


    /**
     * 获取收入流水列表
     */
    @GetMapping(value = "/list")
    public Object list(String condition) {
        return incomeFlowingService.selectList(null);
    }

    /**
     * 新增收入流水
     */
    @PostMapping(value = "/add")
    public Object add(@RequestBody IncomeFlowing incomeFlowing) {
        incomeFlowingService.insert(incomeFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 删除收入流水
     */
    @GetMapping(value = "/delete")
    public Object delete(@RequestParam Integer incomeFlowingId) {
        incomeFlowingService.deleteById(incomeFlowingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改收入流水
     */
    @PostMapping(value = "/update")
    public Object update(@RequestBody IncomeFlowing incomeFlowing) {
        incomeFlowingService.updateById(incomeFlowing);
        return SUCCESS_TIP;
    }

    /**
     * 收入流水详情
     */
    @GetMapping(value = "/detail/{incomeFlowingId}")
    public Object detail(@PathVariable("incomeFlowingId") Integer incomeFlowingId) {
        return incomeFlowingService.selectById(incomeFlowingId);
    }
}
