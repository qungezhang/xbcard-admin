package cn.stylefeng.guns.modular.dto;

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
    private String imgUrl;
    /**
     * 描述
     */
    private String description;
    /**
     * 类别id
     */
    private Integer categoryId;
    /**
     * 名片id
     */
     private Integer cardId;


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
