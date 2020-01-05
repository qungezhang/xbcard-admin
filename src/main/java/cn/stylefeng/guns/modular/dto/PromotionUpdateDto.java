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
public class PromotionUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不可为空")
    private Integer id;
    /**
     * 描述
     */
    private String description;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 名片id
     */
    private Integer cardId;

}