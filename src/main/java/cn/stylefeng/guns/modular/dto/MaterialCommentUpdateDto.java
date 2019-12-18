package cn.stylefeng.guns.modular.dto;

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
public class MaterialCommentUpdateDto {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "id不可为空")
    private Integer id;

    /**
     * 素材id
     */
    private Integer materialId;
    /**
     * 用户(评论者)id
     */
    private Integer userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 名片id
     */
    private Integer cardId;



}


