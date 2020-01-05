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
 * 名片转发打开记录
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@TableName("wx_card_forward")
public class CardForward extends Model<CardForward> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名片ID
     */
    @TableField("card_id")
    private Long cardId;
    @TableField("card_logo")
    private String cardLogo;

    @TableField("card_position")
    private String cardPosition;
    @TableField("card_name")
    private String cardName;
    @TableField("card_company")
    private String cardCompany;


    @TableField("forwarder_img")
    private String forwarderImg;
    /**
     * 类型 1转发 2进入 3收藏
     */
    private Integer type;
    /**
     * 转发/进入ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 参与人头像
     */
    @TableField("head_img")
    private String headImg;
    /**
     * 参与人昵称
     */
    private String nickname;
    /**
     * 转发者ID
     */
    @TableField("forwarder_id")
    private Long forwarderId;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 公司ID
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardLogo() {
        return cardLogo;
    }

    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }

    public String getForwarderImg() {
        return forwarderImg;
    }

    public void setForwarderImg(String forwarderImg) {
        this.forwarderImg = forwarderImg;
    }

    public String getCardPosition() {
        return cardPosition;
    }

    public void setCardPosition(String cardPosition) {
        this.cardPosition = cardPosition;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(Long forwarderId) {
        this.forwarderId = forwarderId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CardForward{" +
        ", id=" + id +
        ", cardId=" + cardId +
        ", type=" + type +
        ", userId=" + userId +
        ", headImg=" + headImg +
        ", nickname=" + nickname +
        ", forwarderId=" + forwarderId +
        ", openid=" + openid +
        ", companyId=" + companyId +
        ", createTime=" + createTime +
        "}";
    }
}
