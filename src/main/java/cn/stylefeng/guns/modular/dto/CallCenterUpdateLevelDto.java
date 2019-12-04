package cn.stylefeng.guns.modular.dto;

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
public class CallCenterUpdateLevelDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 内容
     */
    @NotNull(message = "id不可为空")
    private Integer id;
    /**
     * 评估等级
     */
    @NotNull(message = "评估等级不可为空")
    private Integer valuationLevel;


}
