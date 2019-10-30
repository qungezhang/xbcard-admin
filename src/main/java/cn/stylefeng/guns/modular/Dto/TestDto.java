package cn.stylefeng.guns.modular.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="user对象",description="用户对象user")
public class TestDto {
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty(value="id数组",hidden = true)
    private String[] ids;

    private String xxx;

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
