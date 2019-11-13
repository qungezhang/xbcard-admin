package cn.stylefeng.guns.modular.dto;

import cn.stylefeng.guns.modular.system.model.Material;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/11 14:58
 * @author qunge
 */
@Data
public class CardInfoDTO implements Serializable {
    /**
     * id
     */
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
    private Integer userId;
    /**
     * 是否删除（0否，1是）
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    private Integer isVip;
    private List<Material> materialList;
    private List<CategoryTreeDTO> categoryTrees;
}
