package cn.stylefeng.guns.modular.dto;

import lombok.Data;

/**
 * <p>
 * 素材
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class MaterialDTO  {

    private static final long serialVersionUID = 1L;

    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 简称
     */
    private String simplename;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 名片id
     */
     private Integer cardId;
    /**
     * 类别id
     */
     private Integer categoryId;

}
