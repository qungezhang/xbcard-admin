package cn.stylefeng.guns.modular.dto;

import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import lombok.Data;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/26 20:37
 */
@Data
public class WxaCodeUnlimitDto {
    private String scene;
    private String page;
    private Integer width;
    private Boolean autoColor;
    private WxMaCodeLineColor lineColor;
    private Boolean isHyaline;
}
