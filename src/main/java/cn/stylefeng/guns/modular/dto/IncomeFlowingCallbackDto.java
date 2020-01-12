package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 收入流水
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
@Data
public class IncomeFlowingCallbackDto implements Serializable {

    /**
     * 支付金额
     */
    @NotBlank(message = "支付金额不可为空")
    private String totalFee;
    /**
     * 微信用户标识
     */
    @NotBlank(message = "openid不可为空")
    private String openid;
    /**
     * 支付描述
     */
    private String body;
    /**
     * 内部订单号
     */
    @NotBlank(message = "内部订单号不可为空")
    private String outTradeNo;
    /**
     * 通知地址
     */

    private String notifyUrl;
    /**
     * 支付数据包
     */
    @NotBlank(message = "支付数据包不可为空")
    private String packageValue;

    @ApiModelProperty(value = "当前登录用户id", required = true)
    @NotNull(message = "当前登录用户id不可为空")
    private Integer userId;

}

