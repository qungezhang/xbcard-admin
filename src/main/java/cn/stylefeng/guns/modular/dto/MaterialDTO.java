package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 素材
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class MaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片地址
     */
    @NotNull(message = "图片地址不可为空")
    @ApiModelProperty(value = "图片地址", required = true)
    private String imgUrl;

    @ApiModelProperty(value = "价格")
    private String price;
    /**
     * 描述
     */
    @ApiModelProperty(value = "图片描述")
    private String description;
    /**
     * 名片id
     */
    @NotNull(message = "名片id不可为空")
    @ApiModelProperty(value = "名片id", required = true)
     private Integer cardId;
    /**
     * 父级id
     */
    @NotNull(message = "父级id不可为空默认为0")
    @ApiModelProperty(value = "父级id不可为空默认为0", required = true)
    private Integer pid;


//    /**
//     * 简称
//     */
//    private String simplename;

//    /**
//     * 排序
//     */
//    private Integer num;
//    /**
//     * 名片id
//     */
//    @NotNull(message = "名片id不可为空")
//     private Integer cardId;


}
