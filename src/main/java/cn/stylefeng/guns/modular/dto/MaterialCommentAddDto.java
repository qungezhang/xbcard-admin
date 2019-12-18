package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>
 * 素材评论
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@Data
public class MaterialCommentAddDto {

    private static final long serialVersionUID = 1L;


    /**
     * 素材id
     */
    @NotNull(message = "素材id不可为空")
    @ApiModelProperty(value = "素材id", required = true)
    private Integer materialId;
    /**
     * 用户(评论者)id
     */
    @NotNull(message = "用户(评论者)id不可为空")
    @ApiModelProperty(value = "用户(评论者)id", required = true)
    private Integer userId;
    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不可为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;
    /**
     * 名片id
     */
    @NotNull(message = "名片id不可为空")
    @ApiModelProperty(value = "名片id", required = true)
    private Integer cardId;



}


