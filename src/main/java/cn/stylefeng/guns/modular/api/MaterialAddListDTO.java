package cn.stylefeng.guns.modular.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class MaterialAddListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品图片地址", required = true)
    private String imgUrl;
    @ApiModelProperty(value = "默认图片")
    private String  defaultImg;
    @ApiModelProperty(value = "图片描述")
    private String description;



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
