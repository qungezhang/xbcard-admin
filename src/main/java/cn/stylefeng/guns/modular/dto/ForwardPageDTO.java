package cn.stylefeng.guns.modular.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="进入/收藏记录",description="进入/收藏记录")
public class ForwardPageDTO {
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "条数")
    private Integer pageSize;
    @ApiModelProperty(value = "类型：1进入我 2收藏我 3我收藏", required = true)
    private Integer type;
    @ApiModelProperty(value = "当前登录用户id", required = true)
    private Integer userId;

}
