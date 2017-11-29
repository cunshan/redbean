var $;

layui.define(['jquery', 'layim', 'layer'], function(exports){
  $ = layui.$; //重点处
  var layim = layui.layim;
  var im = {};

  /**.
   * 初始化IM
   */
  im.init = function initIm(user) {
    //基础配置
    layim.config({
//        isAudio: true,
//        isVideo: true,
      voice: false,
      title: "PBCS交互",
      min: false,
      //获取主面板列表信息
      init: {
        url: '/config/init', //接口地址（返回的数据格式见下文）
        type: 'get', //默认get，一般可不填
        data: {loginAccount: user.loginAccount} //额外参数
      },
      members: {
        url: '/config/members',
        data: {}
      },
      //可同时配置多个
//        tool: [{
//          alias: 'code' //工具别名
//          , title: '代码' //工具名称
//          , icon: '&#xe64e;' //工具图标，参考图标文档
//        }],
//        msgbox: '/chat/msg-box', //消息盒子页面地址，若不开启，剔除该项即可
//        find: '/chat/find', //发现页面地址，若不开启，剔除该项即可
      chatLog: '/chat/chat-log'
    });

    //监听自定义工具栏点击，以添加代码为例
    // layim.on('tool(code)', function (insert, send, obj) { //事件中的tool为固定字符，而code则为过滤器，对应的是工具别名（alias）
    //   layer.prompt({
    //     title: '插入代码'
    //     , formType: 2
    //     , shade: 0
    //   }, function (text, index) {
    //     layer.close(index);
    //     insert('[pre class=layui-code]' + text + '[/pre]'); //将内容插入到编辑器，主要由insert完成
    //     //send(); //自动发送
    //   });
    //   console.log(this); //获取当前工具的DOM对象
    //   console.log(obj); //获得当前会话窗口的DOM对象、基础信息
    // });

    //连接websocket
    var socket = new SockJS("/redbean-im");
    var stompClient = Stomp.over(socket);
    stompClient.connect('guest', 'guest', function (frame) {
      console.log('Connected: ' + frame);
      //绑定接收信息事件
      stompClient.subscribe("/queue/user." + user.id,
          function (greeting) {
            var data = JSON.parse(greeting.body);
            console.log(data);
            layim.getMessage(data);
          }, {id: user.id});
    }, function (frame) {
      console.log(frame.headers);
      var headers = frame.headers;
      if (headers) {
        layer.msg(headers.message);
        //TODO 修改在线状态

      }

    });
    //绑定发送事件
    layim.on('sendMessage', function (res) {
      var mine = res.mine;
      var to = res.to;

      var message = {
        id: mine.id,
        type: to.type,
        content: mine.content,
        username: mine.username,
        avatar: mine.avatar,
        fromId: 1,
        toUserId: to.id
      };

      stompClient.send("/im-ws/talk-to-friend", {},
          JSON.stringify(message));
    });
  };

  exports('im', im);
});