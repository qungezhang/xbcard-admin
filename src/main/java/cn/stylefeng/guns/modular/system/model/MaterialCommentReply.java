package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 素材评论回复
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@TableName("wx_material_comment_reply")
public class MaterialCommentReply extends Model<MaterialCommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论id
     */
    @TableField("comment_id")
    private Integer commentId;
    /**
     * 用户(回复者)id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 回复者头像
     */
    @TableField("head_img")
    private String headImg;
    /**
     * 回复者昵称
     */
    private String nickname;
    /**
     * 被回复人id（回复张三,此处即为张三id）
     */
    @TableField("reply_user_id")
    private Integer replyUserId;
    /**
     * 被回复人昵称
     */
    @TableField("reply_nickname")
    private String replyNickname;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 点赞数
     */
    @TableField("prase_count")
    private Integer praseCount;
    /**
     * 状态（0待审核，1通过 ，2不通过）
     */
    private Integer status;
    /**
     * 是否删除（0否，1是）
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 名片id
     */
    @TableField("card_id")
    private Integer cardId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField("update_by")
    private Date updateBy;
    /**
     * 预留字段
     */
    private String flag1;
    /**
     * 预留字段
     */
    private String flag2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyNickname() {
        return replyNickname;
    }

    public void setReplyNickname(String replyNickname) {
        this.replyNickname = replyNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraseCount() {
        return praseCount;
    }

    public void setPraseCount(Integer praseCount) {
        this.praseCount = praseCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MaterialCommentReply{" +
        ", id=" + id +
        ", commentId=" + commentId +
        ", userId=" + userId +
        ", headImg=" + headImg +
        ", nickname=" + nickname +
        ", replyUserId=" + replyUserId +
        ", replyNickname=" + replyNickname +
        ", content=" + content +
        ", praseCount=" + praseCount +
        ", status=" + status +
        ", isDeleted=" + isDeleted +
        ", cardId=" + cardId +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        ", flag1=" + flag1 +
        ", flag2=" + flag2 +
        "}";
    }
}
