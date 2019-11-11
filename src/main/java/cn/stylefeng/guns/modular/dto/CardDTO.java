package cn.stylefeng.guns.modular.dto;

import cn.stylefeng.guns.modular.system.model.Card;
import cn.stylefeng.guns.modular.system.model.Material;
import lombok.Data;

import java.util.List;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/11 14:58
 */
@Data
public class CardDTO {
    private Card card;
    private Integer isVip;
    private List<Material> materialList;
}
