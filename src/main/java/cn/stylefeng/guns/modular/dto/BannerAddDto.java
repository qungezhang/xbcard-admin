package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 轮播
 * </p>
 *
 * @author qunge
 * @since 2019-11-13
 */
@Data
public class BannerAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片地址
     */
    @NotBlank(message = "图片地址不可为空")
    private String imgUrl;
    /**
     * 描述
     */
    private String description;

    private String defaultImg;
    /**
     * 名片id
     */
    @NotNull(message = "名片id不可为空")
    private Integer cardId;

}
