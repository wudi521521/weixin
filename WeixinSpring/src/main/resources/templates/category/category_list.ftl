<html>
<meta charset="utf-8">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover text-center btn-default" >
                        <thead >

                        <tr>
                            <th class="text-center">类目id  </th>
                            <th class="text-center">类目名   </th>
                            <th class="text-center">类目编号  </th>
                            <th class="text-center">创建时间   </th>
                            <th class="text-center">修改时间  </th>

                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list productCategoryList as content>
                                    <tr>
                                        <td> ${content.categoryId} </td>
                                        <td> ${content.categoryName} </td>
                                        <td> ${content.categoryType} </td>
                                        <td> ${content.createTime} </td>
                                        <td> ${content.updateTime} </td>
                                        <td><a href="/sell/seller/category/index?categoryId=${content.categoryId}">修改</a></td>
                                    </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>