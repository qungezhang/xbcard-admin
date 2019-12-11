package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 微信用户
 * </p>
 *
 * @author qunge
 * @since 2019-11-23
 */
@Data
public class WxUserTreeDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private WxUserDto data;
    private List<WxUserTreeDto> children;
}
