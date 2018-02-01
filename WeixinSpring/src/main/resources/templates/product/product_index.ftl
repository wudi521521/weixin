<html>
<meta charset="utf-8">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
        <#--边栏sidebar-->
        <#include "../common/nav.ftl">
        <#--页面内容-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" method="post" action="/sell/seller/product/addAndUdpate">
                                <div class="form-group">
                                    <label>名称</label>
                                    <input class="form-control"  type="text" name="productName" value="${(productInfoBase.productName)!''}" style="width:450px;" placeholder="请输入商品名称" />
                                </div>
                                <div class="form-group">
                                    <label>价格</label>
                                    <input class="form-control" type="text" name="productPrice" value="${(productInfoBase.productPrice)!''}" style="width: 450px;" placeholder="请输入商品价格"  />
                                </div>
                                <div class="form-group">
                                    <label>库存</label>
                                    <input class="form-control"  type="number" name="productStock" value="${(productInfoBase.productStock)!''}" style="width: 450px;" placeholder="请输入商品库存"/>
                                </div>
                                <div class="form-group">
                                    <label>描述</label>
                                    <input class="form-control"  type="text" name="productDescribtion" value="${(productInfoBase.productDescribtion)!''}" style="height: 120px;width: 450px;" placeholder="请输入商品描述"/>
                                </div>
                                <div class="form-group">
                                    <label >图片<img width="45" height="45" src=${(productInfoBase.productIcon)!''}></label>
                                   <#-- <input  type="file" placeholder="请上传图片" name="productIcon" value="${(productInfoBase.productIcon)!''}"/>-->
                                    <input  type="text" placeholder="请上传图片" name="productIcon" value="${(productInfoBase.productIcon)!''}"/>
                                </div>
                               <div class="form-group">
                                    <label>类目</label>
                                    <select name="categoryType">
                                        <#list contentCategoryAll as content>

                                                  <option value="${content.categoryType}"
                                                            <#if (productInfoBase.categoryType)?? && productInfoBase.categoryType == content.categoryType>
                                                                      selected
                                                            </#if>
                                        >${content.categoryName}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" />Check me out</label>
                                </div>
                                <div>
                                    <input hidden type="text" name="id" value="${(productInfoBase.productId)!''}">
                                </div>
                                <button type="submit" class="btn btn-default">提交按钮</button>
                            </form>
                        </div>
                    </div>
            </div>
        </div>
</div>
</body>
</html>