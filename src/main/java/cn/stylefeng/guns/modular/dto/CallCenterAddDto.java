package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 客服中心记录
 * </p>
 *
 * @author qunge
 * @since 2019-11-13
 */
@Data
public class CallCenterAddDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 内容
     */
    @NotBlank(message = "内容不可为空")
    private String content;
    /**
     * 内容图片
     */
    private String contentImg;

    @ApiModelProperty(value = "当前登录用户id", required = true)
    @NotNull(message = "当前登录用户id不可为空")
    private Integer userId;

}
