package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 素材评论
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@Data
public class MaterialCommentReplyAddDto {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @NotNull(message = "评论id不可为空")
    @ApiModelProperty(value = "评论id", required = true)
    private Integer commentId;
    /**
     * 用户(回复者)id
     */
    @NotNull(message = "用户(回复者)id不可为空")
    @ApiModelProperty(value = "用户(回复者)id", required = true)
    private Integer userId;
    /**
     * 回复内容
     */
    @NotBlank(message = "回复内容不可为空")
    @ApiModelProperty(value = "回复内容", required = true)
    private String content;



}


