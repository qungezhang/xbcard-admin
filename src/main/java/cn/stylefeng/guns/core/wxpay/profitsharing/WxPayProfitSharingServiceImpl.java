package cn.stylefeng.guns.core.wxpay.profitsharing;

import cn.stylefeng.guns.core.wxpay.profitsharing.Request.WxPayProfitSharingRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/11/7 15:55
 */
@Service
public class WxPayProfitSharingServiceImpl implements WxPayProfitSharingService {
    @Autowired
    private WxPayService payService;

    @Override
    public BaseWxPayResult profit(WxPayProfitSharingRequest sharingRequest) throws WxPayException {
        sharingRequest.setSignType(WxPayConstants.SignType.HMAC_SHA256);
        sharingRequest.checkAndSign(this.payService.getConfig());
        String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharing";

        String responseContent = this.payService.post(url, sharingRequest.toXML(), true);
        BaseWxPayResult result = BaseWxPayResult.fromXML(responseContent, BaseWxPayResult.class);
        result.checkResult(this.payService, sharingRequest.getSignType(), true);
        return result;
    }

    @Override
    public Object multiProfit() {
        return null;
    }

    @Override
    public Object sharingQuery() {
        return null;
    }

    @Override
    public Object addReceiver() {
        return null;
    }

    @Override
    public Object removeReceiver() {
        return null;
    }

    @Override
    public Object sharingFinish() {
        return null;
    }

    @Override
    public Object sharingReturn() {
        return null;
    }

    @Override
    public Object returnQuery() {
        return null;
    }
}
