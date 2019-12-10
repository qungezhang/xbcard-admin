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
        {
            title: 'logo', field: 'logo', visible: true, align: 'center', width: '70px', valign: 'middle',
            formatter: function (value, row, index) {//显示图片
                var s;
                if (row.logo != null) {
                    var url =  row.logo;
                    s = '<a class = "view"  href="javascript:void(0)"> <img style="width:50px;height:50px;" src="' + url + '" /></a>';
                }
                return s;
            }, events: 'operateEvents'
        },
        {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle',width:'100px'},
        {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle',width:'120px'},
        {title: '公司', field: 'company', visible: true, align: 'center', valign: 'middle',width:'300px'},
        {title: '职位', field: 'position', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle',width:'300px'},
        {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
        {title: '传真', field: 'fax', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',width:'150px'},

    ];
};
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        var url =  row.logo;
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: ['auto', 'auto'],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: '<img style="max-width:600px;height:auto;" src="'+url+'"/>',
            success: function(layero, index) {
                layer.iframeAuto(index);
            }
        });
    },
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
 * 打开查看用户名片详情
 */
Card.openWxUserCardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '名片详情',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/card/card_info/' + Card.seItem.id
        });
        // layer.full(index);
    }
    this.layerIndex = index;
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
    queryData['mobile'] = $("#mobile").val();
    Card.table.refresh({query: queryData});
};
Card.resetSearch = function () {
    $("#mobile").val("");
    Card.search();
};
$(function () {
    var queryData = {
        "userId": $("#userId").val()
    };
    var defaultColunms = Card.initColumn();
    var table = new BSTable(Card.id, "/card/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Card.table = table.init();
});
