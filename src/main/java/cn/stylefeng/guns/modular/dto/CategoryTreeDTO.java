package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 类别
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class CategoryTreeDTO  {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 父部门id
     */
    private Integer pid;
    /**
     * 父级ids
     */
    private String pids;
    /**
     * 简称
     */
    private String simplename;
    /**
     * 全称
     */
    private String fullname;
    /**
     * 提示
     */
    private String tips;
    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 名片id
     */
    private Integer cardId;

    private List<CategoryTreeDTO> childList;

}
