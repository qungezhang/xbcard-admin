/**
 * 初始化小程序用户详情对话框
 */
var WxUserInfoDlg = {
    wxUserInfoData : {}
};

/**
 * 清除数据
 */
WxUserInfoDlg.clearData = function() {
    this.wxUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxUserInfoDlg.set = function(key, val) {
    this.wxUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WxUserInfoDlg.close = function() {
    parent.layer.close(window.parent.WxUser.layerIndex);
}

/**
 * 收集数据
 */
WxUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('mobile')
    .set('openid')
    .set('wechatNumber')
    .set('unionid')
    .set('qrcode')
    .set('nickName')
    .set('headimgurl')
    .set('sex')
    .set('channel')
    .set('country')
    .set('province')
    .set('city')
    .set('area')
    .set('address')
    .set('language')
    .set('empId')
    .set('isDeleted')
    .set('isvip')
    .set('freeze')
    .set('lastLoginTime')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy')
    .set('flag1')
    .set('flag2');
}

/**
 * 提交添加
 */
WxUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.WxUser.table.refresh();
        WxUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WxUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.WxUser.table.refresh();
        WxUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxUserInfoData);
    ajax.start();
}

$(function() {

});
