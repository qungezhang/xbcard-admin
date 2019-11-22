/**
 * 初始化收入流水详情对话框
 */
var IncomeFlowingInfoDlg = {
    incomeFlowingInfoData : {}
};

/**
 * 清除数据
 */
IncomeFlowingInfoDlg.clearData = function() {
    this.incomeFlowingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IncomeFlowingInfoDlg.set = function(key, val) {
    this.incomeFlowingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IncomeFlowingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
IncomeFlowingInfoDlg.close = function() {
    parent.layer.close(window.parent.IncomeFlowing.layerIndex);
}

/**
 * 收集数据
 */
IncomeFlowingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('merchantFee')
    .set('merchantTotalFee')
    .set('myselfFee')
    .set('myselfTotalFee')
    .set('cardId')
    .set('userId')
    .set('customerId')
    .set('openid')
    .set('body')
    .set('outTradeNo')
    .set('notifyUrl')
    .set('packageValue')
    .set('payType')
    .set('payStatus')
    .set('isDeleted')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy');
}

/**
 * 提交添加
 */
IncomeFlowingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/incomeFlowing/add", function(data){
        Feng.success("添加成功!");
        window.parent.IncomeFlowing.table.refresh();
        IncomeFlowingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.incomeFlowingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
IncomeFlowingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/incomeFlowing/update", function(data){
        Feng.success("修改成功!");
        window.parent.IncomeFlowing.table.refresh();
        IncomeFlowingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.incomeFlowingInfoData);
    ajax.start();
}

$(function() {

});
