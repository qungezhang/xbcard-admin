package cn.stylefeng.guns.modular.dto;

import cn.stylefeng.guns.modular.api.MaterialAddListDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 素材
 * </p>
 *
 * @author qunge
 * @since 2019-11-04
 */
@Data
public class MaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名片id", required = true)
    private Integer cardId;
    @ApiModelProperty(value = "父级id不可为空默认为0", required = true)
    private Integer pid;
    @ApiModelProperty(value = "图片对象list", required = true)
    private List<MaterialAddListDTO> imgList;

//    /**
//     * 简称
//     */
//    private String simplename;

//    /**
//     * 排序
//     */
//    private Integer num;
//    /**
//     * 名片id
//     */
//    @NotNull(message = "名片id不可为空")
//     private Integer cardId;


}
