package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 素材
 * </p>
 *
 * @author qunge
 * @since 2019-12-18
 */
@Data
public class MaterialInfoDto {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Integer pid;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imgUrl;
    @ApiModelProperty(value = "默认图片")
    private String  defaultImg;
    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String simplename;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
//    /**
//     * 排序
//     */
//    private Integer num;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /**
     * 名片id
     */
    @ApiModelProperty(value = "名片id")
    private Integer cardId;
    /**
     * 是否有下一级（0没有  1有）
     */
    @ApiModelProperty(value = "是否有下一级（0没有  1有）")
    private Integer hasChildren;

//    /**
//     * 父级编码
//     */
//    private String pcode;
//    /**
//     * 是否删除（0否，1是）
//     */
//    private Integer isDeleted;
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//    /**
//     * 创建人
//     */
//    private String createBy;
//    /**
//     * 修改时间
//     */
//    private Date updateTime;
//    /**
//     * 修改人
//     */
//    private Date updateBy;
//    /**
//     * 预留字段
//     */
//    private String flag1;
//    /**
//     * 预留字段
//     */
//    private String flag2;

}
