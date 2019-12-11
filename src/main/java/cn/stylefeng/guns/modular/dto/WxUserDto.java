package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 微信用户
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@Data
public class WxUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 当前绑定的名片id
     */
    private Integer cardId;
    /**
     * 微信unionid
     */
    private String unionid;
    /**
     * 微信小程序二维码
     */
    private String qrcode;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 性别：（1:男性；2:女性；0:未知）
     */
    private Integer sex;
    /**
     * 渠道（0自主 ）
     */
    private Integer channel;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 地区
     */
    private String area;
    /**
     * 地址详情
     */
    private String address;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 导购用户id
     */
    private Integer empId;
    /**
     * 是否删除（0否，1是）
     */
    private Integer isDeleted;
    /**
     * 是否是会员（0否，1是）
     */
    private Integer isvip;
    /**
     * 冻结 0未冻结 1已冻结
     */
    private Integer freeze;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 预留字段
     */
    private String flag1;
    /**
     * 预留字段
     */
    private String flag2;
    private String concatIsVip;
    private Integer childCount;
    private Integer vipNum;
    private Integer opNum;


}
