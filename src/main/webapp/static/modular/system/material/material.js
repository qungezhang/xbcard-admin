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
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '父级id', field: 'pid', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'imgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '简称', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '名片id', field: 'cardId', visible: true, align: 'center', valign: 'middle'},
            {title: '类别id', field: 'categoryId', visible: true, align: 'center', valign: 'middle'},
            {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '父级编码', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
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
    queryData['condition'] = $("#condition").val();
    Material.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Material.initColumn();
    var table = new BSTable(Material.id, "/material/list", defaultColunms);
    table.setPaginationType("client");
    Material.table = table.init();
});
