@/*
图片展示的说明:
name : 名称
id : 按钮的id
imgUrl : 按钮的id
@*/

<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${name}</label>
        <div class="col-sm-7">
            <div id="${id}PreId">
                <div><img width="110px" height="110px" onclick="Feng.bigImage2('${imgUrl}')"
                          src="${imgUrl}"></div>
            </div>
        </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}