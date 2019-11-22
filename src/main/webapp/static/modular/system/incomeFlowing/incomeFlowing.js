/**
 * 收入流水管理初始化
 */
var IncomeFlowing = {
    id: "IncomeFlowingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
IncomeFlowing.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '商户收入金额（分）', field: 'merchantFee', visible: true, align: 'center', valign: 'middle'},
            {title: '商户收入总金额（分）', field: 'merchantTotalFee', visible: true, align: 'center', valign: 'middle'},
            {title: '个人收入金额（分）', field: 'myselfFee', visible: true, align: 'center', valign: 'middle'},
            {title: '个人收入总金额（分）', field: 'myselfTotalFee', visible: true, align: 'center', valign: 'middle'},
            {title: '名片id', field: 'cardId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '客户id', field: 'customerId', visible: true, align: 'center', valign: 'middle'},
            {title: '微信用户标识', field: 'openid', visible: true, align: 'center', valign: 'middle'},
            {title: '支付描述', field: 'body', visible: true, align: 'center', valign: 'middle'},
            {title: '内部订单号', field: 'outTradeNo', visible: true, align: 'center', valign: 'middle'},
            {title: '通知地址', field: 'notifyUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '支付数据包', field: 'packageValue', visible: true, align: 'center', valign: 'middle'},
            {title: '支付类型（1微信，2支付宝，3银联）', field: 'payType', visible: true, align: 'center', valign: 'middle'},
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
IncomeFlowing.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        IncomeFlowing.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收入流水
 */
IncomeFlowing.openAddIncomeFlowing = function () {
    var index = layer.open({
        type: 2,
        title: '添加收入流水',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/incomeFlowing/incomeFlowing_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收入流水详情
 */
IncomeFlowing.openIncomeFlowingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收入流水详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/incomeFlowing/incomeFlowing_update/' + IncomeFlowing.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除收入流水
 */
IncomeFlowing.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/incomeFlowing/delete", function (data) {
            Feng.success("删除成功!");
            IncomeFlowing.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("incomeFlowingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收入流水列表
 */
IncomeFlowing.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    IncomeFlowing.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = IncomeFlowing.initColumn();
    var table = new BSTable(IncomeFlowing.id, "/incomeFlowing/list", defaultColunms);
    table.setPaginationType("client");
    IncomeFlowing.table = table.init();
});
