package cn.stylefeng.guns.modular.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 微信公众号支付进行统一下单后组装所需参数的类
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
 * Created by Binary Wang on 2017-9-1.
 * </pre>
 *
 */
@Data
public class WxPayOrderResultDto implements Serializable {
  private static final long serialVersionUID = -7966682379048446567L;

  private String appId;
  private String timeStamp;
  private String nonceStr;
  private String packageValue;
  private String signType;
  private String paySign;
  private String outTradeNo;

}
