<html>
<meta charset="UTF-8">
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
                        <div class="alert alert-dismissable alert-success">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h5  style="text-align: center;">
                                成功!
                            </h5> <strong>Smile!</strong> ${msg!""}<a href="#" class="alert-link" ><span id="timer">5</span>自动跳转</a>
                        </div>
                    </div>
                </div>
            </div>
         </div>
    </div>
</body>
    <script>
           /* setTimeout("location.href='${url}'",1500);到达1.5s后自动跳转*/
           var time=4;
           //创建定时器,1s自动执行一次
           var name = setInterval(function () {
                                           //获取展示的数据,
                                          var timer =  document.getElementById("timer");
                                           if (time >0){
                                               //重新给展示的数据赋值
                                               timer.innerHTML=time--;
                                           }else{
                                               //清除定时器，进行跳转
                                               clearInterval(name);
                                               console.log("打印路径:"+'${url}')
                                               location.href='${url}';
                                           }
                                     },1000);
    </script>
</html>