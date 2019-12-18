package cn.stylefeng.guns.modular.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
public class MaterialTopPlistDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页 默认1")
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量 默认10")
    private Integer pageSize;
    @NotNull(message = "名片id不可为空")
    @ApiModelProperty(value = "名片id", required = true)
    private Integer cardId;


}
