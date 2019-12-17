/**
 * 初始化素材评论回复详情对话框
 */
var MaterialCommentReplyInfoDlg = {
    materialCommentReplyInfoData : {}
};

/**
 * 清除数据
 */
MaterialCommentReplyInfoDlg.clearData = function() {
    this.materialCommentReplyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialCommentReplyInfoDlg.set = function(key, val) {
    this.materialCommentReplyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialCommentReplyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MaterialCommentReplyInfoDlg.close = function() {
    parent.layer.close(window.parent.MaterialCommentReply.layerIndex);
}

/**
 * 收集数据
 */
MaterialCommentReplyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('commentId')
    .set('userId')
    .set('headImg')
    .set('nickname')
    .set('replyUserId')
    .set('replyNickname')
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
MaterialCommentReplyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialCommentReply/add", function(data){
        Feng.success("添加成功!");
        window.parent.MaterialCommentReply.table.refresh();
        MaterialCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialCommentReplyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MaterialCommentReplyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialCommentReply/update", function(data){
        Feng.success("修改成功!");
        window.parent.MaterialCommentReply.table.refresh();
        MaterialCommentReplyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialCommentReplyInfoData);
    ajax.start();
}

$(function() {

});
