package cn.stylefeng.guns.core.wxpay.profitsharing;

import cn.stylefeng.guns.core.wxpay.profitsharing.Request.WxPayProfitSharingRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 商户分账接口
 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
 * Created by qunge on 2019/10/25.
 * </pre>

 */
public interface WxPayProfitSharingService {

    /**
     * 单次分账
     * 单次分账请求按照传入的分账接收方账号和资金进行分账，同时会将订单剩余的待分账金额解冻给本商户。故操作成功后，订单不能再进行分账，也不能进行分账完结。
     * @return
     */
    BaseWxPayResult profit(WxPayProfitSharingRequest sharingRequest) throws WxPayException;

    /**
     * 多次分账
     * ● 微信订单支付成功后，商户发起分账请求，将结算后的钱分到分账接收方。多次分账请求仅会按照传入的分账接收方进行分账，不会对剩余的金额进行任何操作。故操作成功后，在待分账金额不等于零时，订单依旧能够再次进行分账。
     *
     * ● 多次分账，可以将本商户作为分账接收方直接传入，实现释放资金给本商户的功能
     *
     * ● 对同一笔订单最多能发起20次多次分账请求
     * @return
     */
    Object multiProfit();

    /**
     * 查询分账结果
     * 发起分账请求后，可调用此接口查询分账结果；发起分账完结请求后，可调用此接口查询分账完结的执行结果。
     */
    Object sharingQuery();
    /**
     * 添加分账接收方
     * 商户发起添加分账接收方请求，后续可通过发起分账请求将结算后的钱分到该分账接收方。
     */
    Object addReceiver();
    /**
     * 删除分账接收方
     * 商户发起删除分账接收方请求，删除后不支持将结算后的钱分到该分账接收方。
     */
    Object removeReceiver();
    /**
     * 完结分账
     *
     1、不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给本商户
     2、调用多次分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户
     3、已调用请求单次分账后，剩余待分账金额为零，不需要再调用此接口。
     */
    Object sharingFinish();
    /**
     * 分账回退
     * ● 对订单进行退款时，如果订单已经分账，可以先调用此接口将指定的金额从分账接收方（仅限商户类型的分账接收方）回退给本商户，然后再退款。
     *
     * ● 回退以原分账请求为依据，可以对分给分账接收方的金额进行多次回退，只要满足累计回退不超过该请求中分给接收方的金额。
     *
     * ● 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果
     *
     * ● 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用。
     */
    Object sharingReturn();

    /**
     * 回退结果查询
     * ● 商户需要核实回退结果，可调用此接口查询回退结果。
     *
     * ● 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
     */
    Object returnQuery();


}
