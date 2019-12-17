package cn.stylefeng.guns.modular.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 素材评论
 * </p>
 *
 * @author qunge
 * @since 2019-12-17
 */
@TableName("wx_material_comment")
public class MaterialCommentDto extends Model<MaterialCommentDto> {

    private static final long serialVersionUID = 1L;
// TODO: 2019/12/17

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 素材id
     */
    @TableField("material_id")
    private Integer materialId;
    /**
     * 用户(评论者)id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 评论者头像
     */
    @TableField("head_img")
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

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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
        return "MaterialComment{" +
        ", id=" + id +
        ", materialId=" + materialId +
        ", userId=" + userId +
        ", headImg=" + headImg +
        ", nickname=" + nickname +
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
