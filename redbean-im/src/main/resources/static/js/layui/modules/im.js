var $;

layui.define(['jquery', 'layim', 'layer'], function (exports) {
  $ = layui.$; //重点处
  var layim = layui.layim;
  var im = {};
  var layerIndex;//选人弹出层index

  /**.
   * 初始化IM
   */
  im.init = function initIm(user) {
    //基础配置
    layim.config({
//        isAudio: true,
//        isVideo: true,
      voice: false,
      title: "即时通信",
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
      var bizId = getBizDiv(to.id).find("input[name='bizId']").val();
      var message = {
        id: mine.id,
        type: to.type,
        content: mine.content,
        username: mine.username,
        avatar: mine.avatar,
        fromId: 1,
        toUserId: to.id,
        bizId: bizId
      };

      stompClient.send("/im-ws/talk-to-friend", {},
          JSON.stringify(message));
    });
  };

  /**
   * 弹出选人的弹出层
   * @param bizId
   */
  im.openChooseUser = function (bizId) {
    console.log(bizId);

    $.post('/config/get-all-friends', {}, function (res) {
      var str = "";
      var users = res.data;
      $(users).each(function (index, user) {
        str += "<a href='javascript:layui.im.openChat(\"" + user.id + "\",\""
            + bizId + "\");' >" + user.username + "</a></br>"
      });
      layerIndex = layer.open({
        type: 1,
        content: str //注意，如果str是object，那么需要字符拼接。
      });
    });
  };

  /**.
   * 获取业务框DIV
   */
  function getBizDiv(userId) {
    var resDiv;
    $(".mgp_win").each(function (index, div) {
      var _div = $(div);
      var cltid = _div.attr("cltid");
      if (cltid === userId) {
        resDiv = _div;
      }
    });
    return resDiv;
  }

  /**
   * 打开对话框
   */
  im.openChat = function (userId, bizId) {
    $.ajax({
      url: "/rest/get-user-by-id",
      dataType: "JSON",
      method: "get",
      data: "userId=" + userId,
      success: function (data) {

        if ("0" == data.code) {
          var user = data.data;
          //打开对话框
          layim.chat({
            name: user.username
            , type: 'friend'
            , avatar: user.avatar
            , id: userId
          });
          //添加业务页面
          var _div = getBizDiv(userId);
          var html = "<label>业务ID：</label><input readonly='true' name='bizId' value='"
              + bizId + "'/>";
          _div.html(html);
        }
        layer.close(layerIndex);
      }
    });
  };

  exports('im', im);
});