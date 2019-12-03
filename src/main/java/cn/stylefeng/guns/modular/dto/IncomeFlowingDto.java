package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class IncomeFlowingDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //总提成金额
    private Integer inTotal;
    //已提现金额
    private Integer outTotal;
    //余款金额
    private Integer restTotal;
    //预备金额
    private Integer preTotal;
    //可提现金额
    private Integer validRestTotal;

}
