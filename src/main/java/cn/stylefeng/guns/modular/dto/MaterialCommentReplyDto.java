package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 素材评论回复
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@Data
public class MaterialCommentReplyDto  {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * 用户(回复者)id
     */
    private Integer userId;
    /**
     * 回复者头像
     */
    private String headImg;
    /**
     * 回复者昵称
     */
    private String nickname;

    /**
     * 回复内容
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

}
