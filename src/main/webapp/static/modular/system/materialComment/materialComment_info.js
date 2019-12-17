/**
 * 初始化素材评论详情对话框
 */
var MaterialCommentInfoDlg = {
    materialCommentInfoData : {}
};

/**
 * 清除数据
 */
MaterialCommentInfoDlg.clearData = function() {
    this.materialCommentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialCommentInfoDlg.set = function(key, val) {
    this.materialCommentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MaterialCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.MaterialComment.layerIndex);
}

/**
 * 收集数据
 */
MaterialCommentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('materialId')
    .set('userId')
    .set('headImg')
    .set('nickname')
    .set('content')
    .set('praseCount')
    .set('status')
    .set('isDeleted')
    .set('cardId')
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
MaterialCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialComment/add", function(data){
        Feng.success("添加成功!");
        window.parent.MaterialComment.table.refresh();
        MaterialCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialCommentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MaterialCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialComment/update", function(data){
        Feng.success("修改成功!");
        window.parent.MaterialComment.table.refresh();
        MaterialCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialCommentInfoData);
    ajax.start();
}

$(function() {

});
