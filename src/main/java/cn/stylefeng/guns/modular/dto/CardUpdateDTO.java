package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 名片基础信息
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class CardUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 公司
     */
    private String company;
    /**
     * 职位
     */
    private String position;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 传真
     */
    private String fax;
    /**
     * logo
     */
    private String logo;
    /**
     * 模版类型
     */
    private Integer type;
    /**
     * 分享图片
     */
    @ApiModelProperty(value = "分享图片")
    private String shareImgUrl;
    /**
     * 分享标题
     */
    @ApiModelProperty(value = "分享标题")
    private String shareTitle;
    @ApiModelProperty(value = "分享类型")
    private Integer shareType;
    @ApiModelProperty(value = "当前登录用户id", required = true)
    private Integer userId;
//    private List<MaterialDTO> materials;


}
