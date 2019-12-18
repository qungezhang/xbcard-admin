/**
 * 初始化素材详情对话框
 */
var MaterialInfoDlg = {
    materialInfoData : {}
};

/**
 * 清除数据
 */
MaterialInfoDlg.clearData = function() {
    this.materialInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialInfoDlg.set = function(key, val) {
    this.materialInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MaterialInfoDlg.close = function() {
    parent.layer.close(window.parent.Material.layerIndex);
}

/**
 * 收集数据
 */
MaterialInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pid')
    .set('imgUrl')
    .set('simplename')
    .set('description')
    .set('num')
    .set('userId')
    .set('cardId')
    .set('categoryId')
    .set('code')
    .set('pcode')
    .set('isDeleted')
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
MaterialInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/material/add", function(data){
        Feng.success("添加成功!");
        window.parent.Material.table.refresh();
        MaterialInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MaterialInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/material/update", function(data){
        Feng.success("修改成功!");
        window.parent.Material.table.refresh();
        MaterialInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialInfoData);
    ajax.start();
}

$(function() {

});
