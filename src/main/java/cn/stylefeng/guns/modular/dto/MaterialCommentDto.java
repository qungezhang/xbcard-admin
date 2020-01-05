package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 素材评论
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@Data
public class MaterialCommentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
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
     * 评论者头像
     */
    private String headImg;
    /**
     * 评论者昵称
     */
    private String nickname;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 名片id
     */
    private Integer cardId;
    /**
     * 创建时间
     */
    private Date createTime;

    private List<MaterialCommentReplyDto> replyList;

}


