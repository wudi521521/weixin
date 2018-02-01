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
                    <form role="form" method="post" action="/sell/seller/category/addAndUdpate">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input class="form-control"  type="text" name="categoryName" value="${(productCategoryPage.categoryName)!''}" style="width:450px;" placeholder="请输入类目名称" />
                        </div>
                        <div class="form-group">
                            <label>类目编码</label>
                            <input class="form-control" type="number" name="categoryType" value="${(productCategoryPage.categoryType)!''}" style="width: 450px;" placeholder="请输入唯一类目编号"  />
                        </div>
                        <div>
                            <input hidden type="text" name="categoryId" value="${(productCategoryPage.categoryId)!''}">
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