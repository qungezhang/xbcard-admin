/**
 * 素材评论管理初始化
 */
var MaterialComment = {
    id: "MaterialCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MaterialComment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '素材id', field: 'materialId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户(评论者)id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '评论者头像', field: 'headImg', visible: true, align: 'center', valign: 'middle'},
            {title: '评论者昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '评论内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '点赞数', field: 'praseCount', visible: true, align: 'center', valign: 'middle'},
            {title: '状态（0待审核，1通过 ，2不通过）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '名片id', field: 'cardId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag1', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag2', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MaterialComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MaterialComment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加素材评论
 */
MaterialComment.openAddMaterialComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加素材评论',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/materialComment/materialComment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看素材评论详情
 */
MaterialComment.openMaterialCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '素材评论详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/materialComment/materialComment_update/' + MaterialComment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除素材评论
 */
MaterialComment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/materialComment/delete", function (data) {
            Feng.success("删除成功!");
            MaterialComment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("materialCommentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询素材评论列表
 */
MaterialComment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MaterialComment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MaterialComment.initColumn();
    var table = new BSTable(MaterialComment.id, "/materialComment/list", defaultColunms);
    table.setPaginationType("client");
    MaterialComment.table = table.init();
});
