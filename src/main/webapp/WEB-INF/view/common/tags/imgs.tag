@/*
图片展示的说明:
imgUrl : imgUrl
content : 文字
@*/

<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
        <img src="${imgUrl}" alt="${imgUrl}" onclick="Feng.bigImage('${imgUrl}')"/>
        <div class="caption">
            @if(isNotEmpty(content)){
            <h3>商品描述</h3>
            <p>${content}</p>
            @}
        </div>
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}