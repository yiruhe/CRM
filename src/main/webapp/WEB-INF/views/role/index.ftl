<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <#include "../common/common.ftl" />
    <script src="/views/js/role/role.js"></script>
    <title>Title</title>
    <style>
        #dd .d_ul{
            padding:10px;
        }
            .d_ul li{
                padding:10px 0;
        }
            .d_ul input{

                padding:2px;
                font-size: 8px;
            }

            .d_ul label{
                margin-left:10%;
                display:inline-block;
                width:20%;
                text-align:right;
            }
        #dialog .search{
            padding:4px;
            margin:10px 0;
        }



    </style>
</head>
<body>
<#--显示-->

<table id="dg"></table>

<#--添加-->
   <div id="dialog" style="padding:10px;">
         <form id="role_dialog_form" method="post">
             <input type="hidden" name="id" />
       <table  align="center">
           <tr>
               <td >角色名称:<input class="search" name="name"/></td>
               <td>角色编号:<input class="search" name="sn"/></td>
           </tr>
           <tr>
               <td><table class="p_role" ></table></td>
               <td><table class="permission" ></table></td>
           </tr>
       </table>
     </form>
   </div>


</form>
</body>
</html>