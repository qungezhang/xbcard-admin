package cn.stylefeng.guns.modular.dto;

import cn.stylefeng.guns.modular.system.model.WxUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qunge
 */
@Data
public class WXLoginResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private WxUser user;


}
