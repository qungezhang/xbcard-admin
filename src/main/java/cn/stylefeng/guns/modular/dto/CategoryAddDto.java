package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 类别
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class CategoryAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父部门id
     */
    @NotNull(message = "父部门id不可为空")
    private Integer pid;

    /**
     * 简称
     */
    @NotBlank(message = "简称不可为空")
    private String simplename;
    /**
     * 全称
     */
    private String fullname;

    /**
     * 名片id
     */
    @NotNull(message = "名片id不可为空")
    private Integer cardId;

}
