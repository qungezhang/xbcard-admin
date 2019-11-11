package cn.stylefeng.guns.modular.dto;

import lombok.Data;

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
public class CardAddDTO{

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

    private List<MaterialDTO> materials;


}
