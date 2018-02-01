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
                             <th class="text-center">点单号  </th>
                             <th class="text-center">姓名    </th>
                             <th class="text-center">手机号  </th>
                             <th class="text-center">地址    </th>
                             <th class="text-center">金额    </th>
                             <th class="text-center">订单状态 </th>
                             <th class="text-center">支付状态 </th>
                             <th class="text-center">创建时间 </th>
                             <th colspan="2" class="text-center">操作</th>
                         </tr>
                         </thead>
                         <tbody>
                         <#list orderMasterDTOPage.content as content>
                         <tr>
                             <td> ${content.orderId} </td>
                             <td> ${content.buyerName} </td>
                             <td> ${content.buyerPhone} </td>
                             <td> ${content.buyerAddress} </td>
                             <td> ${content.buyerName} </td>
                             <td> ${content.getOrderMasterEnum().msg}</td>
                             <td> ${content.getPayStatusEnum().msg} </td>
                             <td> ${content.createTime} </td>
                             <td><a href="/sell/seller/order/detail?orderId=${content.orderId}">详情</a></td>
                             <td>
                                 <#if content.getOrderMasterEnum().msg == "新订单">
                                     <a href="/sell/seller/order/cancle?orderId=${content.orderId}">取消</a>
                                 </#if>
                                 <#if content.getOrderMasterEnum().msg == "订单结束">
                                     <a href="javascript:void(0);">订单结束</a>
                                 </#if>
                                 <#if content.getOrderMasterEnum().msg == "订单取消">
                                     <a href="javascript:void(0);">订单已取消</a>
                                 </#if>
                             </>
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
                             <li><a href="/sell/seller/order/list?page=${contentPage-1}&size=2">上一页</a></li>
                         </#if>
                         <#list 1..orderMasterDTOPage.getTotalPages() as index>
                                 <#if contentPage == index><!--等于-->
                                        <li class="disabled"><a href="/sell/seller/order/list?page=${index}&size=2">${index}</a></li>
                                     <#else>
                                          <li><a href="/sell/seller/order/list?page=${index}&size=2">${index}</a></li>
                                 </#if>
                         </#list>
                             <#if contentPage gte orderMasterDTOPage.getTotalPages()><!--大于等于-->
                                     <li class="disabled"><a href="#" >下一页</a></li>
                                 <#else >
                                     <li ><a href="/sell/seller/order/list?page=${contentPage+1}&size=2" >下一页</a></li>
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