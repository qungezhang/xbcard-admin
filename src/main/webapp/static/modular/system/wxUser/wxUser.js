/**
 * 小程序用户管理初始化
 */
var WxUser = {
    id: "WxUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WxUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {
            title: '用户头像', field: 'headimgurl', visible: true, align: 'center', width: '70px', valign: 'middle',
            formatter: function (value, row, index) {//显示图片
                var s;
                if (row.headimgurl != null) {
                    var url = row.headimgurl;
                    s = '<a class = "view"  href="javascript:void(0)"><img style="width:50px;height:50px;"   src="' + url + '" /></a>';
                }
                return s;
            }, events: 'operateEvents', cellStyle: cellStylesales

        },
        {title: '昵称', field: 'nickName', visible: true, align: 'center', valign: 'middle', width: '260px'},

        {title: '手机号', field: 'mobile', visible: true, align: 'center', width: '120px', valign: 'middle'},
        // {title: '微信openid', field: 'openid', visible: true, align: 'center', valign: 'middle'},
        // {title: '微信号', field: 'wechatNumber', visible: true, align: 'center', valign: 'middle'},
        // {title: '微信unionid', field: 'unionid', visible: true, align: 'center', valign: 'middle'},
        // {title: '微信二维码', field: 'qrcode', visible: true, align: 'center', valign: 'middle'},
        // {title: '性别', field: 'sexName', visible: true, align: 'center', valign: 'middle', width: '70px'},
        {title: '吸粉数', field: 'childCount', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.childCount+'</span>';
            }
        },
        {title: 'VIP数', field: 'vipNum', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: red;display:inline-block" >'+row.vipNum+'</span>';
            }
        },
        {title: '免费数', field: 'notVipNum', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.notVipNum+'</span>';
            }
        },
        {title: '总提成', field: 'inTotal', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.inTotal+'</span>';
            }
        },
        {title: '已提现', field: 'outTotal', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.outTotal+'</span>';
            }
        },
        {title: '预备金', field: 'preTotal', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.preTotal+'</span>';
            }
        },
        {title: '可提现', field: 'validRestTotal', visible: true, align: 'center', valign: 'middle', width: '60px'
            , formatter: function (value, row, index) {
                return '<span style="font-weight: bold;font-size:large;color: black;display:inline-block" >'+row.validRestTotal+'</span>';
        }

        },
        // {title: '渠道（0自主 ）', field: 'channel', visible: true, align: 'center', valign: 'middle'},
        // {title: '地址', field: 'addressAll', visible: true, align: 'center', width: '200px', valign: 'middle'},
        // {title: '国家', field: 'country', visible: true, align: 'center', width:'60px', valign: 'middle'},
        // {title: '省份', field: 'province', visible: true, align: 'center', valign: 'middle'},
        // {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
        // {title: '地区', field: 'area', visible: true, align: 'center', valign: 'middle'},
        // {title: '地址详情', field: 'address', visible: true, align: 'center', valign: 'middle'},
        // {title: '用户的语言，简体中文为zh_CN', field: 'language', visible: true, align: 'center', valign: 'middle'},
        // {title: '导购用户id', field: 'empId', visible: true, align: 'center', valign: 'middle'},
        // {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        // {title: '是否删除（0否，1是）', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
        {
            title: '是否VIP', field: 'isvip', visible: true, align: 'center', width: '80px',background:'red', valign: 'middle',
            formatter: function (value, row, index) {
                var s;
                if (row.isvip != null) {
                    var temp = row.isvip;
                    if (temp == 1) {
                        s = '<span style="font-weight: bold;font-size:large;color:yellow;background:red;"  >是</span>';
                    } else {
                        s = '<span style="font-weight: bold;font-size:large;color:black;" >否</span>';
                    }
                }
                return s;
            },

        },
        // {title: '冻结 0未冻结 1已冻结', field: 'freeze', visible: true, align: 'center', valign: 'middle'},
        // {title: '最后登录时间', field: 'lastLoginTime', visible: true, align: 'center', valign: 'middle', width: '150px'},
        // {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', width: '150px'},
        // {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
        {title: '成为VIP时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle', width: '150px'},
        // {title: '修改人', field: 'updateBy', visible: true, align: 'center', valign: 'middle'},
        // {title: '预留字段', field: 'flag1', visible: true, align: 'center', valign: 'middle'},
        // {title: '预留字段', field: 'flag2', visible: true, align: 'center', valign: 'middle'}
    ];
};

function cellStylesales(value, row, index) {
    var gmt_mondified = row.gmt_mondified ? row.gmt_mondified : '-';
    var gmt_create = row.gmt_create;
    if (gmt_mondified == '-') {
        gmt_mondified = new Date();
    }
    var data_strat = Date.parse(gmt_create);
    var data_end = Date.parse(gmt_mondified);
    var times = Math.abs(data_end - data_strat);
    var days = times / (1000 * 60 * 60 * 24);
    if (days >= 3 && days < 6) {
        return {css: {background: '#ffeb3b', color: '#000'}};
    } else if (days >= 6) {
        return {css: {background: '#f44336', color: '#000'}};
    } else {
        return {css: {background: '#8bc34a', color: '#000'}};
    }
}

window.operateEvents = {
    'click .view': function (e, value, row, index) {
        var url =  row.headimgurl;
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
WxUser.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WxUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加小程序用户
 */
WxUser.openAddWxUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加小程序用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wxUser/wxUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看小程序用户详情
 */
WxUser.openWxUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小程序用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wxUser/wxUser_update/' + WxUser.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
* 打开查看用户名片详情
*/
WxUser.openWxUserCardList = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户名片列表详情',
            area: ['1300px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/card?userId=' + WxUser.seItem.id,
        });
        layer.full(index);
    }
    this.layerIndex = index;
};

/**
 * 打开查看用户素材详情
 */
WxUser.openWxUserMaterialDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户商品详情',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wxUser/material_info/' + WxUser.seItem.id
        });
        layer.full(index);
    }
    this.layerIndex = index;
};

/**
 * 删除小程序用户
 */
WxUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wxUser/delete", function (data) {
            Feng.success("删除成功!");
            WxUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wxUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询小程序用户列表
 */
WxUser.search = function () {
    var queryData = {};
    queryData['mobile'] = $("#mobile").val();
    queryData['nickName'] = $("#nickName").val();
    WxUser.table.refresh({query: queryData});
};
WxUser.resetSearch = function () {
    $("#mobile").val("");
    $("#nickName").val("");
    WxUser.search();
};

// var _expandFlag_all = false;
// WxUser.expandAllBtn = function () {
//     if (_expandFlag_all) {
//         WxUser.table.expandAllBtn();
//     } else {
//         WxUser.table.collapseAllBtn();
//     }
//     _expandFlag_all = !_expandFlag_all;
// };


// $(function () {
//     var defaultColunms = WxUser.initColumn();
//     var table = new BSTable(WxUser.id, "/wxUser/list", defaultColunms);

//     WxUser.table = table.init();
// });
$(function () {
    // var defaultColunms = WxUser.initColumn();
    // var table = new BSTreeTable(WxUser.id, "/wxUser/list",defaultColunms);
    // table.setExpandColumn(2);
    // table.setIdField("id");
    // table.setCodeField("id");
    // table.setParentCodeField("empId");
    // table.setExpandAll(true);
    // // table.set("mobile", "18838214579");
    // // table.setPaginationType("client");
    // table.init();
    // WxUser.table= table;
    var defaultColunms = WxUser.initColumn();
    var table = new BSTreeTable(WxUser.id, "/wxUser/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("empId");
    table.setExpandAll(false);
    table.setExpandFirst(true);
    table.init();
    WxUser.table = table;

});
