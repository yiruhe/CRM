/**
 * Created by Administrator on 07/12/2017.
 */
$(function(){

    var login = {

        init:{
            dom:{
                    form:{}, //form对象
                    button:{}  //提交按钮
            }

        },
        function:{
            submit:function(data){



                login.init.dom.button.unbind();
                login.init.dom.button.bind('click',function () {



                    $.ajax({
                        url:data.url,
                        type: data.type ||"post",
                        data:login.init.dom.form.serialize(),
                        success:data.success,
                        error:function(){

                        }
                    });
                });
            }

        }
    };


    var form =  $("#login-form");

    login.init.dom.form = form;
    login.init.dom.button = form.find("input[type='button']");

    //登陆按钮
    login.function.submit({
        url:'/login/check',
        success:function (result) {

            if (result.success){

                alert("登录成功");

                window.location.href= "/index";
            }else{

                alert("账号或密码错误!");

            }
        }
    });

});



