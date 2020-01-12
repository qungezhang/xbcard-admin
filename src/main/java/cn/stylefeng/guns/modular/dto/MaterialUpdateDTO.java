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
public class MaterialUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "id不可为空")
    @ApiModelProperty(value = "商品图id", required = true)
    private Integer id;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imgUrl;
//    @ApiModelProperty(value = "价格")
//    private String price;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 名片id
     */
    @ApiModelProperty(value = "名片id")
     private Integer cardId;
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Integer pid;

    @ApiModelProperty(value = "当前登录用户id", required = true)
    @NotNull(message = "当前登录用户id不可为空")
    private Integer userId;
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
