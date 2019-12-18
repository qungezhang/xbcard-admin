package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 名片基础信息
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class CardAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 名片二维码
     */
    private String flag2;
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

//    private List<MaterialDTO> materials;


}
