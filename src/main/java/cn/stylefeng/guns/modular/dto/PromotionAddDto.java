package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 宣传文案
 * </p>
 *
 * @author qunge
 * @since 2020-01-05
 */
@Data
public class PromotionAddDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 描述
     */
    @NotBlank(message = "描述不可为空")
    private String description;
    /**
     * 名片id
     */
    @NotNull(message = "名片id不可为空")
    private Integer cardId;
    /**
     * userId
     */
    @NotNull(message = "userId不可为空")
    private Integer userId;
}
