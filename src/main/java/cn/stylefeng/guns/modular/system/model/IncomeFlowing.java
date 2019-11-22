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
 * 收入流水
 * </p>
 *
 * @author qunge
 * @since 2019-11-22
 */
@TableName("wx_income_flowing")
public class IncomeFlowing extends Model<IncomeFlowing> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商户收入金额（分）
     */
    @TableField("merchant_fee")
    private Integer merchantFee;
    /**
     * 商户收入总金额（分）
     */
    @TableField("merchant_total_fee")
    private Integer merchantTotalFee;
    /**
     * 个人收入金额（分）
     */
    @TableField("myself_fee")
    private Integer myselfFee;
    /**
     * 个人收入总金额（分）
     */
    @TableField("myself_total_fee")
    private Integer myselfTotalFee;
    /**
     * 名片id
     */
    @TableField("card_id")
    private Integer cardId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 客户id
     */
    @TableField("customer_id")
    private Integer customerId;
    /**
     * 微信用户标识
     */
    private String openid;
    /**
     * 支付描述
     */
    private String body;
    /**
     * 内部订单号
     */
    @TableField("out_trade_no")
    private String outTradeNo;
    /**
     * 通知地址
     */
    @TableField("notify_url")
    private String notifyUrl;
    /**
     * 支付数据包
     */
    @TableField("package_value")
    private String packageValue;
    /**
     * 支付类型（1微信，2支付宝，3银联）
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 支付状态（1成功，2失败，3进行中）
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     * 是否删除（0否，1是）
     */
    @TableField("is_deleted")
    private Integer isDeleted;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(Integer merchantFee) {
        this.merchantFee = merchantFee;
    }

    public Integer getMerchantTotalFee() {
        return merchantTotalFee;
    }

    public void setMerchantTotalFee(Integer merchantTotalFee) {
        this.merchantTotalFee = merchantTotalFee;
    }

    public Integer getMyselfFee() {
        return myselfFee;
    }

    public void setMyselfFee(Integer myselfFee) {
        this.myselfFee = myselfFee;
    }

    public Integer getMyselfTotalFee() {
        return myselfTotalFee;
    }

    public void setMyselfTotalFee(Integer myselfTotalFee) {
        this.myselfTotalFee = myselfTotalFee;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "IncomeFlowing{" +
        ", id=" + id +
        ", merchantFee=" + merchantFee +
        ", merchantTotalFee=" + merchantTotalFee +
        ", myselfFee=" + myselfFee +
        ", myselfTotalFee=" + myselfTotalFee +
        ", cardId=" + cardId +
        ", userId=" + userId +
        ", customerId=" + customerId +
        ", openid=" + openid +
        ", body=" + body +
        ", outTradeNo=" + outTradeNo +
        ", notifyUrl=" + notifyUrl +
        ", packageValue=" + packageValue +
        ", payType=" + payType +
        ", payStatus=" + payStatus +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
