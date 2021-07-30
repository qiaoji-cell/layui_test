<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 2021-07-27 0027
  Time: 上午 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>用户管理</title>
    <style>
      /*body{*/
      /*  font-size: 18px !important;*/
      /*}*/
      .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
        top: 50%;
        transform: translateY(30%);
      }
    </style>
  </head>
<%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">--%>
  <%@ include file="_layouts/home.jsp"%>

  <body>
    <div class="layui-container">
      <fieldset class="layui-elem-field layui-field-title">
        <legend>所有用户</legend>
      </fieldset>
      <div class="layui-main">
        <div class="tableSearch">
              搜索用户名:
                <input type="text" class=layui-input" name="username" id="username" >
          <button class="layui-btn layui-btn-radius" data-type="reload" style="height:25px;line-height: 25px;" lay-anim="">搜索</button>
        </div>
        <table class="layui-table" id="user" lay-filter="users">
        </table>
      </div>
    </div>


    <script type="text/html" id="operation">
        <a  class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a  class="layui-btn layui-btn-xs" lay-event="more">更多<i class="layui-icon layui-icon-down"></i></a>
    </script>
  <script>

    // config的设置是全局的
    layui.config({
      base: './js/' //假设这是你存放拓展模块的根目录
    })
    //你也可以忽略 base 设定的根目录，直接在 extend 指定路径（主要：该功能为 layui 2.2.0 新增）
    // layui.extend({
    //   mymod: '{/}http://cdn.xxx.com/lib/mod2' // {/}的意思即代表采用自有路径，即不跟随 base 路径
    // })
    //使用拓展模块
    layui.use(['context'],function(){
      let context = layui.context

      let path = context.projectPath(); //弹出 Hello World!
      // alert(path)
    });

    layui.use(function () {
        let form = layui.form;
        let table = layui.table;
        let layer = layui.layer;
        let dropdown = layui.dropdown;
        let layPage = layui.laypage;
        let $=layui.$;

       let userTable = table.render({
          elem:"#user",
         totalRow:true,//开启合计行
          height:420
          // ,cellMinWidth: 200
          ,page:true
          ,limit:10 //页码控制
          ,limits:[10,3,6],//每页显示条数
          url:path+"/ServletQueryAllUser"
          ,parseData:function (page) {
                    return {
                      "code":0, //解析接口状态
                      "msg":"获得数据", //解析提示文本
                      "count":page.totalCount, //解析数据长度
                      "data":page.dataList //解析数据列表
                    };
          }
         ,btns:['now','confirm']
          ,cols:[[
           {type:"checkbox",fixed:'left',totalRowText: '总计'},
            {field:'aUid',title:"编号", align:'center',sort: true,totalRow:true},
            {field:'aUname',title:"用户名",align:'center',sort: true},
            {field:'aUpwd',title:"密码",align:'center',sort: true},
            {field:'aSex',title:"性别",align:'center',sort: true,templet:function(d){
              return d.aSex==1?"男":"女"
              }},
            {field:'role',title:"身份",align:'center',sort: true,templet:function(d){
              return d.role.roleName
              }},
            ,{title:'操作',fixed: 'right', width: 150, align:'center',toolbar:"#operation"}
          ]],
          toolbar:"default",
          id:'testReload',
         even:true,//隔行变色
         done:function (res,curr) {
            // res 当前页的 数据量 和 数据  curr 当前页码
            if(curr>1&&res.data.length===0){
              table.reload('testReload',{
                page:{
                  curr:curr-1
                }
              })
            }
         }
        })
      //编辑用户
      function openUpdateWind(data){
         layer.open({
           type:2,
           title:'编辑用户',
           shadeClose:true,
           maxmin:true,
           area:['800px','500px'],
           content:'edit.jsp',
           success:function (layero,index) {
             let user = data
             let body = layer.getChildFrame('body',index);
             let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();

             body.find('input[name=userName]').val(user.aUname)
             body.find('input[name=pwd]').val(user.aUpwd)
             body.find('select[name=roleId]').val(user.role.roleId)
             body.find('input[name=sex][value='+user.aSex+']').attr('checked','checked');
             body.find('input[name=uid]').val(user.aUid);

              if(iframeWin.load !== undefined){
                iframeWin.load()
              }
           }
           ,end:function () {
             userTable=table.reload('testReload',{
               page:{
                 curr:userTable.config.page.curr
               }
             })
           }
         })
      }

      function openSeeWindow(data){
         let sex = data.aSex==1?'男':'女'
         layer.open({
           title:"查看详情",
           type:2,
           shadeClose:true,
           maxmin:true,
           content:'see.jsp',
           area:['800px','500px'],
           success:function (layero,index) {
             let body = layer.getChildFrame('body',index)
             let iframeWin = window[layero.find('iframe')[0]['name']]

             body.find('[name=userName]').val(data.aUname)
             body.find('[name=pwd]').val(data.aUpwd)
             body.find('dd:contains('+data.role.roleName+')').click()
             body.find('select[name=roleId]').val(data.role.roleId)
             body.find('input[name=sex][value='+data.aSex+']').prop('checked','checked');

             iframeWin.load();
           }
         })
      }

        table.on('toolbar(users)',function (obj) {
          let checkStatus = table.checkStatus(obj.config.id)//查看指定表格索引的选中状态
          let data = checkStatus.data;
          let ids = [];
          $(data).each(function (index,obj) {
              ids.push(obj.aUid);
          })
            switch (obj.event) {
               case 'add':
                 layer.open({
                   type:2,
                   title:'添加用户',
                   shadeClose:true,
                   maxmin:true,
                   area:['800px','500px'],
                   content:'add.jsp',
                   end:function () {
                     userTable=table.reload('testReload',{
                       page:{
                         curr:userTable.config.page.curr
                       }
                     })
                   }
                 })
                 break;
                   case 'update':
                           if(data.length===0){
                              layer.msg('请选择一行')
                           }else if(data.length>1){
                             layer.msg('只能同时编辑一行')
                           }else{
                             layer.msg('开始编辑',{
                               icon:1,
                             })
                             openUpdateWind(data[0])
                           }
                    break;
                      case 'delete':
                              if(data.length===0){
                                layer.alert("选择一行数据",{
                                  icon:2,
                                  skin:'layui-layer-moly',
                                  closeBtn:2,
                                  anim:3
                                })
                              }else{
                                layer.confirm('真的要删除么',{
                                  icon:1,
                                  skin:'layui-layer-moly',
                                  closeBtn: 2,
                                  anim:1
                                },function (index) {
                                  $.post(path+"/ServletDeleteUser?id="+JSON.stringify(ids),function (res) {
                                    if(res === "true"){
                                      layer.msg('删除成功！')
                                      //重新加载表格
                                      userTable=table.reload('testReload',{
                                        page:{
                                          curr:userTable.config.page.curr
                                        }
                                      })
                                    }
                                  })
                                  layer.close(index);
                                })

                              }
                        break;
            }
        })
      table.on('tool(users)',function (obj) {
        let data=obj.data,
                layEvent=obj.event;
        if(layEvent==="detail"){
          openSeeWindow(data);
        }else if(layEvent ==="more"){
          //下拉菜单
          dropdown.render({
            elem:this,
            show:true,
            data:[{
              title:'编辑',
              id:"edit",
            },
              {
                title:'删除',
                id:'del'
              }]
            ,click:function (menudata) {
              if(menudata.id === 'del'){
                let ids = [];
                ids.push(data.aUid)
                layer.confirm('真的删除行么',function (index) {
                    obj.del();//删除对应行(tr)的DOM结构
                    layer.close(index);//关闭指定弹窗
                    $.post(path+"/ServletDeleteUser?id="+JSON.stringify(ids),function (res) {
                        if(res==="true"){
                          // 重新加载表格
                         userTable=table.reload('testReload',{
                            page:{
                              curr:userTable.config.page.curr
                            }
                          })
                          layer.msg('删除成功！')
                        }
                    })
                })
              }else if(menudata.id === 'edit'){
                layer.msg('编辑操作,当前行ID:'+data.aUid);
                console.log(data)
                openUpdateWind(data);
              }
            },align:'right'//靠右显示
            ,style:'box-shadow:1px 1px 10px rgb(0 0 0 / 12%);'//设置额外样式
          })
        }
      })

      //表格重载
      let active={
          reload:function () {
              let username = $('#username');
              //执行重载
            table.reload('testReload',{
              page:{
                curr:1//从第一页开始
              },
              where:{
                  userName:username.val()
              }
            })
          }
      }
      //按钮监听
      $('.tableSearch .layui-btn').on('click',function () {
          let type=$(this).data('type');
          active[type]?active[type].call(this):"";
      })



    })
  </script>
  </body>
</html>
