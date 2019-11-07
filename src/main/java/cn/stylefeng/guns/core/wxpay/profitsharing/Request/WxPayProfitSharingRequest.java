package cn.stylefeng.guns.core.wxpay.profitsharing.Request;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.util.SignUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.StringUtils;

import static com.github.binarywang.wxpay.constant.WxPayConstants.SignType.ALL_SIGN_TYPES;

/**
 * <pre>
 * 商户分账请求参数对象.
 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
 * Created by qunge on 2019/10/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPayProfitSharingRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 4611350167813931828L;

  /**
   * <pre>
   * 字段名：微信订单号.
   * 是否必填：是
   * 类型：String(32)
   * 示例值：4208450740201411110007820472
   * 描述：微信支付订单号
   * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
   * </pre>
   */
  @XStreamAlias("transaction_id")
  @Required
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 是否必填：是
   * 类型：String(32)
   * 示例值：P20150806125346
   * 描述：商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），
   * 同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
   * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
   * </pre>
   */
  @XStreamAlias("out_order_no")
  @Required
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：分账接收方列表
   * 是否必填：是
   * 类型：String(10240)
   * 示例值：
   * [
   *     {
   *          "type": "MERCHANT_ID",
   *          "account":"190001001",
   *          "amount":100,
   *          "description": "分到商户"
   * },
   *     {
   *          "type": "PERSONAL_WECHATID",
   *          "account":"86693952",
   *          "amount":888,
   *          "description": "分到个人"
   * }
   * ]
   * 描述：分账接收方列表，使用Json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
   *
   *     type String 必填 32 -分账接收方类型
   *      MERCHANT_ID：商户ID
   *      PERSONAL_WECHATID：个人微信
   *      号PERSONAL_OPENID：个人openid
   *
   *    account String 必填 32 分账接收方帐号
   *      类型是MERCHANT_ID时，是商户ID
   *      类型是PERSONAL_WECHATID时，是个人微信号
   *      类型是PERSONAL_OPENID时，是个人openid
   *
   *    amount String 必填 256 分账金额
   *      分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
   *
   *    description Int 必填 分账描述
   *    分账的原因描述，分账账单中需要体现
   *
   * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
   * </pre>
   */
  @XStreamAlias("receivers")
  private String receivers;


  @Override
  protected void checkConstraints() throws WxPayException {

  }

}
