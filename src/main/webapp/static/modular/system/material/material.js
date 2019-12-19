/**
 * 素材管理初始化
 */
var Material = {
    id: "MaterialTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Material.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        // {title: '父级id', field: 'pid', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人电话', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {
            title: '图片地址', field: 'imgUrl', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {//显示图片
                var s;
                if (row.imgUrl != null) {
                    var url = row.imgUrl;
                    s = '<a class = "view"  href="javascript:void(0)"><img style="width:50px;height:50px;"   src="' + url + '" /></a>';
                }
                return s;
            }, events: operateEvents


        },
        {title: '简称', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
        {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: 'Button', visible: true, align: 'center', valign: 'middle',
            formatter: operateFormatter, events: operateEvents
        },
        // {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle'},
        // {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        // {title: '名片id', field: 'cardId', visible: true, align: 'center', valign: 'middle'},
        // {title: '类别id', field: 'categoryId', visible: true, align: 'center', valign: 'middle'},
        // {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
        // {title: '父级编码', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
        // {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
        //
        // {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
        // {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'},
        // {title: '预留字段', field: 'flag1', visible: true, align: 'center', valign: 'middle'},
        // {title: '预留字段', field: 'flag2', visible: true, align: 'center', valign: 'middle'}
    ];
};
function operateFormatter(value, row, index) {
    if (row.isDeleted == 0) {
        return [
            '<input type="button" value="屏蔽" id="update1" class="btn btn-danger btn-sm"  >'
        ].join('');
    } else {
        return [
            '<input type="button" value="开启" id="update2" class="btn btn-primary btn-sm" >'
        ].join('');
    }
}
// function cellStylesales(value, row, index) {
//     var gmt_mondified = row.gmt_mondified ? row.gmt_mondified : '-';
//     var gmt_create = row.gmt_create;
//     if (gmt_mondified == '-') {
//         gmt_mondified = new Date();
//     }
//     var data_strat = Date.parse(gmt_create);
//     var data_end = Date.parse(gmt_mondified);
//     var times = Math.abs(data_end - data_strat);
//     var days = times / (1000 * 60 * 60 * 24);
//     if (days >= 3 && days < 6) {
//         return {css: {background: '#ffeb3b', color: '#000'}};
//     } else if (days >= 6) {
//         return {css: {background: '#f44336', color: '#000'}};
//     } else {
//         return {css: {background: '#8bc34a', color: '#000'}};
//     }
// }
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        var url =  row.imgUrl;
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

    'click #update1': function (e, value, row, index) {
        var ajax = new $ax(Feng.ctxPath + "/material/updateStatus", function (data) {
            Feng.success("屏蔽成功!");
            Material.table.refresh();
        }, function (data) {
            Feng.error("屏蔽失败!" + data.responseJSON.message + "!");
        });
        ajax.set("materialId",row.id);
        ajax.set("isDeleted", 1);
        ajax.start();
    },
    'click #update2': function (e, value, row, index) {
        var ajax = new $ax(Feng.ctxPath + "/material/updateStatus", function (data) {
            Feng.success("开启成功!");
            Material.table.refresh();
        }, function (data) {
            Feng.error("开启失败!" + data.responseJSON.message + "!");
        });
        ajax.set("materialId",row.id);
        ajax.set("isDeleted", 0);
        ajax.start();
    }
};
/**
 * 检查是否选中
 */
Material.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Material.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加素材
 */
Material.openAddMaterial = function () {
    var index = layer.open({
        type: 2,
        title: '添加素材',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/material/material_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看素材详情
 */
Material.openMaterialDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '素材详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/material/material_update/' + Material.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除素材
 */
Material.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/material/delete", function (data) {
            Feng.success("删除成功!");
            Material.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("materialId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询素材列表
 */
Material.search = function () {
    var queryData = {};
    queryData['mobile'] = $("#mobile").val();
    Material.table.refresh({query: queryData});
};
Material.resetSearch = function () {
    $("#mobile").val("");
    Material.search();
};
$(function () {
    var queryData = {
        "cardId": $("#cardId").val()
    };
    var defaultColunms = Material.initColumn();
    var table = new BSTable(Material.id, "/material/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(queryData);
    Material.table = table.init();
});
