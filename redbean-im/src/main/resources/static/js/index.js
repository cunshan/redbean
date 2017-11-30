layui.config({
  base: '/js/layui/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('im'); //加载入口

layui.use(['jquery', 'layer', 'im'], function () {

  var $ = layui.$; //重点处
  var im = layui.im;

  //登录
  $.ajax({
    method: "GET",
    url: "/rest/get-login-user",
    dataType: "json",
    data: {},
    success: function (data) {
      console.log(data);
      if('0' == data.code){
        //登录成功后初始化IM
        im.init(data.data);
      }else {
        layer.msg("登录失败，请重试！");
      }
    },
    error: function () {
      layer.alert("登录失败，请稍后再试！");
    }
  });

});