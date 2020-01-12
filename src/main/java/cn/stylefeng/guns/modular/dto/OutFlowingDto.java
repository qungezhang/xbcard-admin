package cn.stylefeng.guns.modular.dto;

import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 提现流水
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
@Data
public class OutFlowingDto extends EntPayRequest {

    @ApiModelProperty(value = "当前登录用户id", required = true)
    private Integer userId;

}

