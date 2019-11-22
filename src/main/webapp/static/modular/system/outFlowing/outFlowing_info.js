/**
 * 初始化平台支出流水详情对话框
 */
var OutFlowingInfoDlg = {
    outFlowingInfoData : {}
};

/**
 * 清除数据
 */
OutFlowingInfoDlg.clearData = function() {
    this.outFlowingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutFlowingInfoDlg.set = function(key, val) {
    this.outFlowingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OutFlowingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OutFlowingInfoDlg.close = function() {
    parent.layer.close(window.parent.OutFlowing.layerIndex);
}

/**
 * 收集数据
 */
OutFlowingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('outFee')
    .set('cardId')
    .set('userId')
    .set('openid')
    .set('description')
    .set('partnerTradeNo')
    .set('spbillCreateIp')
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
OutFlowingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/outFlowing/add", function(data){
        Feng.success("添加成功!");
        window.parent.OutFlowing.table.refresh();
        OutFlowingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outFlowingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OutFlowingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/outFlowing/update", function(data){
        Feng.success("修改成功!");
        window.parent.OutFlowing.table.refresh();
        OutFlowingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.outFlowingInfoData);
    ajax.start();
}

$(function() {

});
