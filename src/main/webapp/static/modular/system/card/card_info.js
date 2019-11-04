/**
 * 初始化详情对话框
 */
var CardInfoDlg = {
    cardInfoData : {}
};

/**
 * 清除数据
 */
CardInfoDlg.clearData = function() {
    this.cardInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardInfoDlg.set = function(key, val) {
    this.cardInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CardInfoDlg.close = function() {
    parent.layer.close(window.parent.Card.layerIndex);
}

/**
 * 收集数据
 */
CardInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('mobile')
    .set('company')
    .set('position')
    .set('address')
    .set('email')
    .set('fax')
    .set('logo')
    .set('userId')
    .set('isDeleted')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy')
    .set('flag1')
    .set('flag2')
    .set('flag3')
    .set('flag4');
}

/**
 * 提交添加
 */
CardInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/card/add", function(data){
        Feng.success("添加成功!");
        window.parent.Card.table.refresh();
        CardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CardInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/card/update", function(data){
        Feng.success("修改成功!");
        window.parent.Card.table.refresh();
        CardInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardInfoData);
    ajax.start();
}

$(function() {

});
