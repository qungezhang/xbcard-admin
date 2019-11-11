package cn.stylefeng.guns.modular.dto;

import lombok.Data;

@Data
public class WXLoginDTO {
    private String code;
    private String signature;
    private String rawData;
    private String encryptedData;
    private String iv;
    private Integer empId;

}
