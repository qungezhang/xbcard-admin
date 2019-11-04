/**
 * 管理初始化
 */
var Card = {
    id: "CardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Card.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '公司', field: 'company', visible: true, align: 'center', valign: 'middle'},
            {title: '职位', field: 'position', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '传真', field: 'fax', visible: true, align: 'center', valign: 'middle'},
            {title: 'logo', field: 'logo', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag1', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag2', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag3', visible: true, align: 'center', valign: 'middle'},
            {title: '预留字段', field: 'flag4', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Card.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Card.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Card.openAddCard = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/card/card_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Card.openCardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/card/card_update/' + Card.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Card.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/card/delete", function (data) {
            Feng.success("删除成功!");
            Card.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cardId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Card.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Card.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Card.initColumn();
    var table = new BSTable(Card.id, "/card/list", defaultColunms);
    table.setPaginationType("client");
    Card.table = table.init();
});
