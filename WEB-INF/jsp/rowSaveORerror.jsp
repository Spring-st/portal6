<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
<!--
<%
	String msg=(String)request.getAttribute("x_mfg");
%>
	var mfg = '${x_mfg}';//输出s2给js变量s
	if(mfg!=''&&mfg!="null"&&mfg!=null){
		alert(mfg);
	}
//-->
</script>
<table id="resultTable">
  <tbody>
    <jsp:include page="${X_ROWPAGE}"/>
  </tbody>
</table>
<script language='javascript'>
    window.parent.returnValue = document.all('resultTable').outerHTML;
    window.parent.close();
</script>