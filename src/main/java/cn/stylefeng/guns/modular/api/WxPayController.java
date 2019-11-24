package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.config.properties.WxPayProperties;
import cn.stylefeng.guns.core.util.BeanMapperUtil;
import cn.stylefeng.guns.core.util.IPUtils;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.core.util.OrderNumUtils;
import cn.stylefeng.guns.modular.system.model.IncomeFlowing;
import cn.stylefeng.guns.modular.system.model.OutFlowing;
import cn.stylefeng.guns.modular.system.model.WxUser;
import cn.stylefeng.guns.modular.system.service.IIncomeFlowingService;
import cn.stylefeng.guns.modular.system.service.IOutFlowingService;
import cn.stylefeng.guns.modular.system.service.IWxUserService;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.binarywang.wxpay.bean.entpay.EntPayQueryResult;
import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @author Binary Wang
 */

@RestController
@RequestMapping("/api/pay")
@Api(tags = "微信支付")
@AllArgsConstructor
public class WxPayController {
  @Autowired
  private WxPayService wxService;
  @Autowired
  private WxPayProperties wxPayProperties;
  @Autowired
  private IWxUserService wxUserService;
  @Autowired
  private IIncomeFlowingService incomeFlowingService;
  @Autowired
  private IOutFlowingService outFlowingService;
  /**
   * <pre>
   * 查询订单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
   * ◆ 调用被扫支付API，返回USERPAYING的状态；
   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   */
  @ApiOperation(value = "查询订单")
  @GetMapping("/queryOrder")
  public WxPayOrderQueryResult queryOrder(@RequestParam(required = false) String transactionId,
                                          @RequestParam(required = false) String outTradeNo) throws WxPayException {
    return this.wxService.queryOrder(transactionId, outTradeNo);
  }


  /**
   * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
   * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
   *
   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   */
  @ApiOperation(value = "小程序支付的统一下单接口")
  @PostMapping("/unifiedOrder")
  @Transactional
  public ResponseData unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
    try {
//      Assert.notNull(request.getAppid(), "appid不能为空");
//      Assert.notNull(request.getMchId(), "商户号不能为空");
      Assert.notNull(request.getBody(), "商品描述不能为空");
//      Assert.notNull(request.getOutTradeNo(), "支付单编号不能为空");
      Assert.notNull(request.getOpenid(), "openId不能为空");
      Assert.notNull(request.getNotifyUrl(), "通知地址不能为空");
      Assert.notNull(request.getTotalFee(), "支付金额不能为空");
//      Assert.notNull(request.getTradeType(), "交易类型不能为空");
//      Assert.notNull(request.gets(), "支付秘钥不能为空");
    } catch (Exception e) {
      throw new RuntimeException(String.format("异常信息：%s。", e.getMessage()));
    }
    if(request.getTotalFee() < 1) {
      throw new RuntimeException("支付金额不能小于1分");
    }
    request.setBody(request.getBody());
    request.setTotalFee(request.getTotalFee());
    request.setNotifyUrl(request.getNotifyUrl());
    request.setTradeType(WxPayConstants.TradeType.JSAPI);
    request.setOpenid(request.getOpenid());
    request.setOutTradeNo(new OrderNumUtils().nextId());
    request.setSignType(WxPayConstants.SignType.MD5);
    // TODO: 2019/11/21 以上请求实例
    WxPayMpOrderResult wxPayOrderResult = this.wxService.createOrder(request);
    if (wxPayOrderResult != null) {
      Integer userId = JwtTokenUtil.getUserId();
      WxUser wxUser = wxUserService.selectById(userId);
      if (wxUser == null) {
        return new ErrorResponseData("用户登录异常");
      }
      //将用户设为vip
      wxUser.setIsvip(1);
      wxUser.setUpdateTime(new Date());
      wxUserService.updateById(wxUser);
      //记录收入表
      IncomeFlowing flowinged = incomeFlowingService.getOneIncomeFlowingDesc(wxUser.getEmpId());
      IncomeFlowing flowing = BeanMapperUtil.objConvert(request, IncomeFlowing.class);
      flowing.setUserId(wxUser.getEmpId());
      flowing.setCustomerId(wxUser.getId());
      flowing.setMyselfFee(2000);//20元
      flowing.setMyselfTotalFee(2000 + (flowinged != null ? flowinged.getMyselfTotalFee() : 0));
      flowing.setMerchantFee(request.getTotalFee());
      flowing.setPackageValue(wxPayOrderResult.getPackageValue());
      flowing.setCreateTime(new Date());
      flowing.setUpdateTime(new Date());
      incomeFlowingService.insert(flowing);
    } else {
      return new ErrorResponseData("支付失败");
    }


//    Map<String, String> payInfo = new HashMap<>();
//    payInfo.put("appId", wxPayOrderResult.getAppId());
//    payInfo.put("timeStamp", wxPayOrderResult.getTimeStamp());
//    payInfo.put("nonceStr", wxPayOrderResult.getNonceStr());
//    payInfo.put("package", wxPayOrderResult.getPackageValue());
//    payInfo.put("signType", wxPayOrderResult.getSignType());
//    payInfo.put("paySign",wxPayOrderResult.getPaySign());

    return new SuccessResponseData(wxPayOrderResult);
  }
  /**
   * <pre>
   * 企业付款业务是基于微信支付商户平台的资金管理能力，为了协助商户方便地实现企业向个人付款，针对部分有开发能力的商户，提供通过API完成企业付款的功能。
   * 比如目前的保险行业向客户退保、给付、理赔。
   * 企业付款将使用商户的可用余额，需确保可用余额充足。查看可用余额、充值、提现请登录商户平台“资金管理”https://pay.weixin.qq.com/进行操作。
   * 注意：与商户微信支付收款资金并非同一账户，需要单独充值。
   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers
   * </pre>
   *
   * @param request 请求对象
   */
  @ApiOperation(value = "提现")
  @PostMapping("/entPay")
  @Transactional
  public ResponseData entPay(@RequestBody EntPayRequest request, HttpServletRequest servletRequest) throws WxPayException {
    try {
      Assert.notNull(request.getDescription(), "商品描述不能为空");
      Assert.notNull(request.getOpenid(), "openId不能为空");
      Assert.isTrue((request.getAmount() != null && request.getAmount() > 0), "提取金额不能为空");
    } catch (Exception e) {
      throw new RuntimeException(String.format("异常信息：%s。", e.getMessage()));
    }
    Integer userId = JwtTokenUtil.getUserId();
    WxUser wxUser = wxUserService.selectById(userId);
    if (wxUser == null) {
      return new ErrorResponseData("用户登录异常");
    }
    //收入记录
    IncomeFlowing incomeFlowing = incomeFlowingService.getOneIncomeFlowingDesc(wxUser.getId());
    if (incomeFlowing == null || request.getAmount() > incomeFlowing.getMyselfTotalFee()) {
      return new ErrorResponseData("提取金额不能大于总提成金额");
    }
    request.setPartnerTradeNo(new OrderNumUtils().nextId());
    request.setOpenid(request.getOpenid());
    request.setAmount(request.getAmount());
    request.setSpbillCreateIp(IPUtils.getIpAddr(servletRequest));
    request.setCheckName(WxPayConstants.CheckNameOption.NO_CHECK);
    request.setDescription(request.getDescription());
    // TODO: 2019/11/21 以上请求实例
    EntPayResult entPayResult = this.wxService.getEntPayService().entPay(request);
    if (entPayResult.getResultCode().equals("SUCCESS")) {
      //添加支出记录
      OutFlowing outFlowing = BeanMapperUtil.objConvert(request, OutFlowing.class);
      outFlowing.setUserId(userId);
      outFlowing.setOutFee(request.getAmount());
      outFlowing.setUpdateTime(new Date());
      outFlowing.setCreateTime(new Date());
      outFlowingService.insert(outFlowing);

      //更新收入总金额
      incomeFlowing.setMyselfTotalFee(incomeFlowing.getMyselfTotalFee() - outFlowing.getOutFee());
      incomeFlowing.setUpdateTime(new Date());
      incomeFlowingService.updateById(incomeFlowing);
    } else {
      return new ErrorResponseData("提现失败");
    }
    return new SuccessResponseData(entPayResult);
  }

//  /**
//   * <pre>
//   * 查询企业付款API
//   * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
//   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
//   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo
//   * </pre>
//   *
//   * @param partnerTradeNo 商户订单号
//   */
//  @ApiOperation(value = "查询企业付款到零钱的结果")
//  @GetMapping("/queryEntPay/{partnerTradeNo}")
//  public EntPayQueryResult queryEntPay(@PathVariable String partnerTradeNo) throws WxPayException {
//    return this.wxService.getEntPayService().queryEntPay(partnerTradeNo);
//  }

  /**
   * <pre>
   * 微信支付-申请退款
   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
   * </pre>
   *
   * @param request 请求对象
   * @return 退款操作结果
   */
  @ApiOperation(value = "退款")
  @PostMapping("/refund")
  public WxPayRefundResult refund(@RequestBody WxPayRefundRequest request) throws WxPayException {
    return this.wxService.refund(request);
  }

  /**
   * <pre>
   * 微信支付-查询退款
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
   *  银行卡支付的退款3个工作日后重新查询退款状态。
   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
   * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
   * </pre>
   * 以下四个参数四选一
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户订单号
   * @param outRefundNo   商户退款单号
   * @param refundId      微信退款单号
   * @return 退款信息
   */
  @ApiOperation(value = "退款查询")
  @GetMapping("/refundQuery")
  public WxPayRefundQueryResult refundQuery(@RequestParam(required = false) String transactionId,
                                            @RequestParam(required = false) String outTradeNo,
                                            @RequestParam(required = false) String outRefundNo,
                                            @RequestParam(required = false) String refundId)
    throws WxPayException {
    return this.wxService.refundQuery(transactionId, outTradeNo, outRefundNo, refundId);
  }



}

