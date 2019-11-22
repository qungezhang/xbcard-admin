package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qunge
 */
@Data
public class WXLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String signature;
    private String rawData;
    private String encryptedData;
    private String iv;
    private Integer empId;

}
