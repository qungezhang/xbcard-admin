/**
 * 平台支出流水管理初始化
 */
var OutFlowing = {
    id: "OutFlowingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OutFlowing.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '支出金额（分）', field: 'outFee', visible: true, align: 'center', valign: 'middle'},
            {title: '名片id', field: 'cardId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '微信用户标识', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '支出描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '支出订单号', field: 'partnerTradeNo', visible: true, align: 'center', valign: 'middle'},
            {title: '提现机器Ip地址', field: 'spbillCreateIp', visible: true, align: 'center', valign: 'middle'},
            {title: '支出类型（1微信，2支付宝，3银联）', field: 'payType', visible: true, align: 'center', valign: 'middle'},
            {title: '支付状态（1成功，2失败，3进行中）', field: 'payStatus', visible: true, align: 'center', valign: 'middle'},
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
OutFlowing.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OutFlowing.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加平台支出流水
 */
OutFlowing.openAddOutFlowing = function () {
    var index = layer.open({
        type: 2,
        title: '添加平台支出流水',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/outFlowing/outFlowing_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看平台支出流水详情
 */
OutFlowing.openOutFlowingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '平台支出流水详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/outFlowing/outFlowing_update/' + OutFlowing.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除平台支出流水
 */
OutFlowing.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/outFlowing/delete", function (data) {
            Feng.success("删除成功!");
            OutFlowing.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("outFlowingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询平台支出流水列表
 */
OutFlowing.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OutFlowing.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OutFlowing.initColumn();
    var table = new BSTable(OutFlowing.id, "/outFlowing/list", defaultColunms);
    table.setPaginationType("client");
    OutFlowing.table = table.init();
});
