/**
 * Created by Administrator on 10/12/2017.
 */
/**
 * Created by Administrator on 08/12/2017.
 */
$(function () {

    var init = {

        data:{
            //请求的url
            addRole:"/role/add",
            show:"/role/show"
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

           /* dom.combobox({
                url:'/department/list',
                valueField:'id',
                textField:'name'
            });*/

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

                    //回显数据
                    $("#role_dialog_form").form("load",selectedRow);

                    //权限回显
                    init.dom.dialog.find(".p_role").datagrid("load",{
                        id:selectedRow.id
                    });

                }
            },
            dialog:{
                //保存或更新用户
                saveFunction:function(){

                    var roleDom = init.dom.dialog.find(".p_role");

                    var data = roleDom.datagrid("getRows");


                   var json = $("#role_dialog_form").serializeJson();

                    var arr = [];

                    for (var i=0;i<data.length;i++){

                        arr.push({
                            "id":data[i].id
                        });
                    }

                    json["permission"] = arr;

                   $.ajax({
                        url:init.data.addRole,
                        data: JSON.stringify(json),
                        type:"post",
                        traditional:true,
                        contentType: 'application/json;charset=utf-8',
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

            }

        }


    };




    //把JQ对象放入init对象中
    init.dom.dialog=$("#dialog");
    init.dom.dataGrid=$('#dg');



    //初始化弹窗
    init.dom.dialog.dialog({
        title: '添加',
        width: 600,
        height: 480,
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


    //初始化弹窗中的数据表
    var roleDom = init.dom.dialog.find(".p_role");
    var permissionDom = init.dom.dialog.find(".permission");

    roleDom.datagrid({
        url:"/role/query/permission",
        columns:[[
            {field:'name',title:'name',width:1}
        ]],
        title:"用户管理",
        singleSelect:true,
        fitColumns:true,
        width:240,
        height:320,
        onDblClickRow:function (index) {

            roleDom.datagrid("deleteRow",index);

        }
    });

    permissionDom.datagrid({
        url:"/permission/query/name",
        columns:[[
            {field:'name',title:'权限',width:1}
        ]],
        title:"用户管理",
        singleSelect:true,
        fitColumns:true,
        width:240,
        height:320,
        pagination:true,//显示分页
        pageSize:5,//分页大小
        pageList: [5],
        onDblClickRow:function (index, data) {

            var row =  roleDom.datagrid("getRows");

            //检查当前容器中是否已经含有对应的数据
            for (var i = 0;i<row.length;i++){

                if (data.id === row[i].id){

                     return ;
                }

            }

            roleDom.datagrid("appendRow",data);

        }
    });

    permissionDom.datagrid("getPager").pagination({
        showPageList:false,
        showRefresh:false,
        displayMsg:''
    });


    //初始化页面
    init.dom.dataGrid.datagrid({
        url:init.data.show,
        columns:[[
            {field:'name',title:'username',width:100},
            {field:'sn',title:'realName',width:100}
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