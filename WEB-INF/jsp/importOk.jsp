<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<body>
<div id="contentDIV">
	批量导入用户成功！<P>
	共导入<%=request.getAttribute("X_RESULTCOUNT")%>个用户<p>
	重复的用户编号：<%=request.getAttribute("X_RESULTLIST")%>
</div>

</body>
</html>