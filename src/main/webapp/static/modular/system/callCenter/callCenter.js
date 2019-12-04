/**
 * 客服中心记录管理初始化
 */
var CallCenter = {
    id: "CallCenterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CallCenter.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '顺序', field: 'rank', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '身份类型（0客户，1平台客服）', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '头像', field: 'headImg', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CallCenter.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CallCenter.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客服中心记录
 */
CallCenter.openAddCallCenter = function () {
    var index = layer.open({
        type: 2,
        title: '添加客服中心记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/callCenter/callCenter_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客服中心记录详情
 */
CallCenter.openCallCenterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客服中心记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/callCenter/callCenter_update/' + CallCenter.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客服中心记录
 */
CallCenter.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/callCenter/delete", function (data) {
            Feng.success("删除成功!");
            CallCenter.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("callCenterId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客服中心记录列表
 */
CallCenter.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CallCenter.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CallCenter.initColumn();
    var table = new BSTable(CallCenter.id, "/callCenter/list", defaultColunms);
    table.setPaginationType("client");
    CallCenter.table = table.init();
});
