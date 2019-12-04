/**
 * 初始化客服中心记录详情对话框
 */
var CallCenterInfoDlg = {
    callCenterInfoData : {}
};

/**
 * 清除数据
 */
CallCenterInfoDlg.clearData = function() {
    this.callCenterInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CallCenterInfoDlg.set = function(key, val) {
    this.callCenterInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CallCenterInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CallCenterInfoDlg.close = function() {
    parent.layer.close(window.parent.CallCenter.layerIndex);
}

/**
 * 收集数据
 */
CallCenterInfoDlg.collectData = function() {
    this
    .set('id')
    .set('content')
    .set('description')
    .set('rank')
    .set('userId')
    .set('type')
    .set('headImg')
    .set('nickname')
    .set('isDeleted')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy');
}

/**
 * 提交添加
 */
CallCenterInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/callCenter/add", function(data){
        Feng.success("添加成功!");
        window.parent.CallCenter.table.refresh();
        CallCenterInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.callCenterInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CallCenterInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/callCenter/update", function(data){
        Feng.success("修改成功!");
        window.parent.CallCenter.table.refresh();
        CallCenterInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.callCenterInfoData);
    ajax.start();
}

$(function() {

});
