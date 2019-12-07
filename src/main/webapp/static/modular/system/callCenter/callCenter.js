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
        {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '问题内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        // {title: '内容图片', field: 'contentImg', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '回复内容', field: 'replyContent', visible: true, align: 'center', valign: 'middle'},
        {title: '回复时间', field: 'replyTime', visible: true, align: 'center', valign: 'middle'},
        // {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {
            title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                var s;
                if (row.status != null) {
                    var temp = row.status;
                    if (temp == 1) {
                        s = '<div style="color: #006621;"  >已回复</div>';
                    } else {
                        s = '<div style="color: #cc0000;">待回复</div>';
                    }
                }
                return s;
            },
        },
        // {title: '评估等级', field: 'valuationLevel', visible: true, align: 'center', valign: 'middle',
        //     formatter:function(value,row,index){//显示图片
        //         var s = '<input class="rating rating1"   min="0" max="5" step="1" data-size="xs" value="'+row.valuationLevel+'" >';
        //         return s;
        //     }
        //
        // },
        // {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: 'Button', visible: true, align: 'center', valign: 'middle',
            formatter: operateFormatter, events: operateEvents
        },
        // {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
        // {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
        // {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'}
    ];
};
$('.rating').rating({
    min: 0, /*星星的最小数值*/
    max: 5, /*星星的最大数值*/
    step: 1, /*表示增加减少的数量*/
    size: 'xs', /*星星的大小 有lg,sm,xl,xs*/
    showClear: false, /*是否显示清除按钮*/
    disabled: false, /*不可用设置 默认false可以点击  true禁止点击*/
    readonly:true,  /*只读模式 跟上面一样效果，不知道啥区别*/
    // starCaptions: {1: '1分 太坑', 2: '2分 凑合', 3: '3分 一般', 4: '4分 很好', 5: '5分 非常好'},
    starCaptions: {1: '1星', 2: '2星', 3: '3星 ', 4: '4星', 5: '5星'},
    starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
});
function operateFormatter(value, row, index) {
    if (row.status == 0) {
        return [
            '<input type="button" value="回复" id="Ofedit" class="btn btn-warning btn-sm"  >'
        ].join('');
    } else {
        return [
            '<input type="button" value="查看" id="Ofinfo" class="btn btn-success btn-sm" "  data-toggle="modal" style="display:inline"> ',
        ].join('');
    }

}

window.operateEvents = {
    'click #Ofinfo': function (e, value, row, index) {
        // var index = layer.open({
        //     type: 2,
        //     title: '客服中心记录详情',
        //     area: ['800px', '420px'], //宽高
        //     fix: false, //不固定
        //     maxmin: true,
        //     content: Feng.ctxPath + '/callCenter/callCenter_update/' + row.id
        // });
        // this.layerIndex = index;


        CallCenter.openCallCenterDetail(row)
    },
    'click #Ofedit': function (e, value, row, index) {
        // var index = layer.open({
        //     type: 2,
        //     title: '客服中心记录详情',
        //     area: ['800px', '420px'], //宽高
        //     fix: false, //不固定
        //     maxmin: true,
        //     content: Feng.ctxPath + '/callCenter/callCenter_update/' + row.id
        // });
        // this.layerIndex = index;
        CallCenter.openCallCenterDetail(row)
    }
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
CallCenter.openCallCenterDetail = function (row) {

    var index = layer.open({
        type: 2,
        title: '客服中心记录详情',
        area: ['1200px', '650px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/callCenter/callCenter_update/' +row.id
    });
    this.layerIndex = index;
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
CallCenter.resetSearch = function () {
    $("#condition").val("");
    CallCenter.search();
};
$(function () {
    var defaultColunms = CallCenter.initColumn();
    var table = new BSTable(CallCenter.id, "/callCenter/list", defaultColunms);
    table.setPaginationType("client");
    CallCenter.table = table.init();
});
