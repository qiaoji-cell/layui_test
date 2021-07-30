<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 2021-07-28 0028
  Time: 上午 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    let path = window.location.pathname;
    let slash = path.lastIndexOf("/");
    path = path.substring(0,slash);
</script>