/**
 * Created by Administrator on 08/12/2017.
 */



$(function () {

    var init = {

        data:{
            //请求的url
            //添加
            addUserUrl:"/employee/add",
            //删除
            removeUserUrl:"/employee/remove",
            //分页
            queryAllUrl:'/employee/show'

        },
        //所有的dom
        dom:{
            //数据表jqDom
            dataGrid:{},
            //添加用户的弹窗
            dialog:{}

        },
        //下拉框
        /**
         *
         * @param dom 传入一个JQ对象
         */
        combobox:function (dom) {

            dom.combobox({
                url:'/department/list',
                valueField:'id',
                textField:'name'
            });

        },
        //处理函数
        handleFunction:{
           datagrid:{
            // 添加用户
               saveFunction:function () {

                   //初始部门下拉列表
                   init.combobox($('#cc'));

                   //打开弹窗
                   init.dom.dialog.dialog("open");

               },
               //删除用户
               removeFunction:function () {

                   var selectedRow =init.dom.dataGrid.datagrid('getSelected');  //获取选中行

                   $.ajax({
                       url:init.data.removeUserUrl,
                       data:{
                           id:selectedRow.id
                       },
                       type:"post",
                       success:function (result) {

                           $.messager.alert('提醒',result.message);

                       }
                   });

               },
               //编辑用户
               editFunction:function () {

                   var selectedRow =init.dom.dataGrid.datagrid('getSelected');  //获取选中行

                   console.log(selectedRow);

                   //打开弹窗
                   init.dom.dialog.dialog("open");

                   //初始部门下拉列表
                   init.combobox($('#cc'));

                   //回显部门
                   if (selectedRow.dept){

                       selectedRow["dept.id"] = selectedRow.dept.id;
                   }

                   //回显数据
                   init.dom.dialog.form('load',selectedRow);

               }
           },
            dialog:{
                //保存或更新用户
                saveFunction:function(){

                    $.ajax({
                        url:init.data.addUserUrl,
                        data:init.dom.dialog.serialize(),
                        type:"post",
                        success:function (result) {

                            $.messager.alert('提醒',result.message);

                            init.dom.dialog.dialog("close");
                            init.dom.dataGrid.datagrid("load");

                        }
                    });


                },
                //弹窗取消
                cancelFunction:function(){

                    init.dom.dialog.dialog("close");

                }
            },
            formatter: {
                deptValve:function(value,row,index){

                   if (row.dept.name){
                       return row.dept.name;
                   } else {
                       return "无";
                   }
                },
                stateValue:function (value,row,index) {

                    if (row.state === 1){
                        return "<span style='color:green'>在职</span>";
                    } else {
                        return  "<span style='color:red'>离职</span>";
                    }

                }
            }

        }


    };


    //把JQ对象放入init对象中
    init.dom.dialog=$("#dd");
    init.dom.dataGrid=$('#dg');


    //初始化弹窗
    init.dom.dialog.dialog({
        title: '添加',
        width: 320,
        height: 380,
        closed: true,
        cache: false,
        buttons:[{
            text:'保存',
            iconCls: 'icon-save',
            handler:init.handleFunction.dialog.saveFunction
        },{
            text:'关闭',
            iconCls: 'icon-cancel',
            handler:init.handleFunction.dialog.cancelFunction
        }]
    });


    //初始化页面
    init.dom.dataGrid.datagrid({
        url:init.data.queryAllUrl,
        columns:[[
            {field:'username',title:'username',width:100},
            {field:'realName',title:'realName',width:100},
            {field:'tel',title:'tel',width:100},
            {field:'email',title:'email',width:100},
            {field:'dept',title:'dept',width:100,formatter:init.handleFunction.formatter.deptValve},
            {field:'state',title:'state',width:100,formatter:init.handleFunction.formatter.stateValue}
        ]],
        pagination:true,//显示分页
        pageSize:5,//分页大小
        pageList:[5,10,15,20],//每页的个数
        fit:true,//自动补全
        fitColumns:true,
        singleSelect:true,
        title:"用户管理",
        toolbar: [
        {
            text: '添加',
            iconCls: 'icon-add',
            handler:init.handleFunction.datagrid.saveFunction
        },
        {
            text: '删除',
            id:"u_del",
            iconCls: 'icon-remove',
            handler: init.handleFunction.datagrid.removeFunction
        },
        {
            text: '编辑',
            id:"u_edit",
            iconCls: 'icon-edit',
            handler: init.handleFunction.datagrid.editFunction
        }
        ],
        onCheck:function (index,row) {

            if (row.state === 0 ){

                $("#u_edit").linkbutton('disable');
                $("#u_del").linkbutton('disable');
            }else{

                $("#u_edit").linkbutton('enable');
                $("#u_del").linkbutton('enable');

            }


        }
    });

    //高级查询
    var search =  $("#searcher");
    var clear = $("#clear");
    var form = $("#searchForm");


    search.unbind("click");
    clear.unbind("click");


    search.bind("click",function () {

                init.dom.dataGrid.datagrid("load",form.serializeJson());

    });

    clear.bind("click",function () {

        form.form("clear");

    });

});
