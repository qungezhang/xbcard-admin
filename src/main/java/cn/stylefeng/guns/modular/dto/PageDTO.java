package cn.stylefeng.guns.modular.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="分页",description="分页")
public class PageDTO {
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "条数")
    private Integer pageSize;
    @ApiModelProperty(value = "当前登录用户id", required = true)
    private Integer userId;

}
