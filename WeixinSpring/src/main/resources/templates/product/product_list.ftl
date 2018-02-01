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
                            <th class="text-center">商品id  </th>
                            <th class="text-center">名称    </th>
                            <th class="text-center">图片    </th>
                            <th class="text-center">单价    </th>
                            <th class="text-center">库存    </th>
                            <th class="text-center">描述     </th>
                            <th class="text-center">类目 </th>
                            <th class="text-center">创建时间 </th>
                            <th class="text-center">修改时间 </th>
                            <th colspan="2" class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as content>
                        <tr>
                            <td> ${content.productId} </td>
                            <td> ${content.productName} </td>
                            <td> <img width="100" height="45" src='${content.productIcon}' alt=""> </td>
                            <td> ${content.productPrice} </td>
                            <td> ${content.productStock} </td>
                            <td> ${content.productDescribtion}</td>
                            <td> ${content.categoryType} </td>
                            <td> ${content.createTime} </td>
                            <td> ${content.updateTime} </td>
                            <td><a href="/sell/seller/product/index?productId=${content.productId}">修改</a></td>
                            <td>
                                <#if content.getProductInfoEnum().msg == "上架商品">
                                    <a href="/sell/seller/product/offSale?productId=${content.productId}">下架</a>
                                </#if>
                                <#if content.getProductInfoEnum().msg == "下架商品">
                                    <a href="/sell/seller/product/onSale?productId=${content.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <!--分页-->
                    <div class="col-md-12 column" style="text-align: right">
                        <ul class="pagination">
                            <#if contentPage lte 1><!--小于等于-->
                            <li class="disabled"><a href="#" >上一页</a></li>
                        <#else >
                            <li><a href="/sell/api/seller/list?page=${contentPage-1}&size=2">上一页</a></li>
                        </#if>
                        <#list 1..productInfoPage.getTotalPages() as index>
                                <#if contentPage == index><!--等于-->
                                <li class="disabled"><a href="/sell/api/seller/list?page=${index}&size=2">${index}</a></li>
                            <#else>
                                <li><a href="/sell/api/seller/list?page=${index}&size=2">${index}</a></li>
                            </#if>

                        </#list>
                            <#if contentPage gte productInfoPage.getTotalPages()><!--大于等于-->
                            <li class="disabled"><a href="#" >下一页</a></li>
                        <#else >
                            <li ><a href="/sell/api/seller/list?page=${contentPage+1}&size=2" >下一页</a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>