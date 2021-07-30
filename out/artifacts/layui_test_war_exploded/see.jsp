<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 2021-07-28 0028
  Time: 上午 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="_layouts/home.jsp"%>
</head>
<body>
<div class="layui-container" style="margin-top:50px;">
    <form class="layui-form " id="addForm">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="required" name="userName" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" lay-verify="required" name="pwd" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-block">
                <select name="roleId" id="role" lay-filter="role">
                    <option value="1">管理员</option>
                    <option value="2">超级管理员</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">性别：</label>
            <div class="layui-input-block" id="sex" >
                <input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit"  class="layui-btn" id="confirmAdd" lay-submit lay-filter="addUser" disabled>添加</button>
                <button type="reset" class="layui-btn layui-btn-danger" disabled>清空</button>
            </div>
        </div>
    </form>
    <script>
        layui.use(function () {
            let layer = layui.layer;
            let $ = layui.$;
            let form = layui.form;
            let table = layui.table;
//添加用户
            form.on('submit(addUser)',function (data) {
                let queryStr=$('#addForm').serialize();
                $.post(path+"/ServletAddUser?"+queryStr,function (res) {
                    if(res=="true"){
                        layer.alert('添加成功！',{
                            icon:1,
                            skin:'layui-layer-molv',
                            closeBtn:0
                            ,anim:4
                        },function(index){
                            layer.close(index)//关闭当前显示层
                            let pIndex = parent.layer.getFrameIndex(window.name);//父显示层索引
                            parent.layer.close(pIndex)//关闭父显示层
                            // //刷新表格
                            // table.reload('users',{
                            //     curr:1
                            // })
                        })
                    }else{
                        layer.alert('失败')
                    }
                })
                //阻止刷新页面
                return false;
            })
        })
        function load() {
            let form = layui.form
            form.render();
        }
    </script>
</div>
</body>
</html>
