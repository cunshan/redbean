Date.prototype.Format = function (fmt) { //author: meizz
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(RegExp.$1,
          (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(
              ("" + o[k]).length)));
    }
  }
  return fmt;
};

var tableIdx = null;
layui.use('table', function () {
  tableIdx = layui.table.render({
    elem: '#demo'
    , height: 'full-190'
    , url: '/chat-record/rest/page' //数据接口
    , page: {limit: 10, theme: '#c00'} //开启分页
    , cellMinWidth: 80
    , loading: true
    , cols: [[ //表头
      {field: 'id', title: 'ID', fixed: 'left', width: 80}
      , {field: 'fromUserName', title: '发送人', width: 120}
      , {field: 'toUserName', title: '接收人', width: 120}
      , {field: 'content', title: '发送信息', width: 180}
      , {field: 'createdDate', title: '发送时间', width: 160, templet: '#titleTpl'}
      , {field: 'businessId', title: '业务编码', width: 120}
      , {field: 'businessType', title: '业务类型', width: 120}
      , {fixed: 'right', width:150, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
    ]]
  });
});

function search() {
  var param = {};
  layui.jquery.each(layui.jquery("form").serializeArray(), function (index) {
    if (param[this['name']]) {
      param[this['name']] = param[this['name']] + "," + this['value'];
    } else {
      param[this['name']] = this['value'];
    }
  });
  tableIdx.reload({
    where: param
    , page: {
      curr: 1 //重新从第 1 页开始
    }
  });
}

/**
 * 打开对话框
 */
function openChat(id) {
  layui.im.openChooseUser(id);
}