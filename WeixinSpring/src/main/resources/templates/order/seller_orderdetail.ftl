<html>
<meta charset="UTF-8">
      <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏 sidebar-->
    <#include "../common/nav.ftl">
    <#--页面内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <#--订单总的数据-->
                <div class="col-md-4 column">
                    <table class="table table-bordered table-hover text-center btn-default">
                        <thead>
                        <tr>
                            <th class="text-center">点单id</th>
                            <th class="text-center">订单金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderMasterDto.orderId}</td>
                            <td>${orderMasterDto.buyerAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <#--订单详情-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover text-center btn-default">
                        <thead>
                        <tr>
                            <th class="text-center">商品id</th>
                            <th class="text-center">商户名称</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">数量</th>
                            <th class="text-center">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <#list orderMasterDto.orderDetailList as content>
                            <td>${content.productId}</td>
                            <td>${content.productName}</td>
                            <td>${content.productPrice}</td>
                            <td>${content.productQuantity}</td>
                            <td>${content.productQuantity *content.productPrice}</td>
                        </#list>
                        </tr>
                        </tbody>
                    </table>
                <#if orderMasterDto.getOrderMasterEnum().msg =="新订单">
                    <a href="/sell/seller/order/finish?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancle?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>