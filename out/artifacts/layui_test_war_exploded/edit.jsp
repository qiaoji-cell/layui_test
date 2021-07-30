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
<div class="layui-container" style="margin-top:50px;" id="editUser">
    <form class="layui-form " id="editForm" lay-filter="editUser">
        <input type="hidden" name="uid">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-block">
                <input type="text" id="username" class="layui-input" lay-verify="required" name="userName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-block">
                <input type="password" id="pwd" class="layui-input" lay-verify="required" name="pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-block" >
                <select name="roleId" lay-filter="role" id="role">
                    <option value="1">管理员</option>
                    <option value="2">超级管理员</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">性别：</label>
            <div class="layui-input-block" id="sex">
                <input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit"  class="layui-btn" id="confirmAdd" lay-submit lay-filter="updateUser">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-danger">清空</button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use('form',function () {
        let form = layui.form;
        let $ = layui.$;
        let layer = layui.layer;

        form.on('submit(updateUser)',function (data) {
            $.post(path+'/ServletUpdateUser',$.param(data.field),function (res) {
                if(res==="true"){
                    layer.alert('更新成功！',{
                        icon:1,
                        closeBtn:0,
                        skin:'layui-layer-molv',
                        anim:1,
                    },function (index) {
                        layer.close(index)//关闭当前显示层
                        let pIndex = parent.layer.getFrameIndex(window.name);//父显示层索引
                        parent.layer.close(pIndex)//关闭父显示层
                    })
                }
            })
            return false;
        })
    })
    function load(){
        let form = layui.form;
        form.render(null,'editUser');
    }
</script>
</body>
</html>
