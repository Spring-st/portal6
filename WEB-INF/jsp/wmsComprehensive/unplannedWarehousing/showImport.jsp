<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
function upPurchase(){
	var myFile = document.getElementById('myFile').value;
	if(myFile==""){
		alert("请填写导入文件路径!");
		return false;
	} 
	
	document.wmsUnplannedWarehousingQueryForm.submit();
}       
//-->
</script>
<html:form action="/upLoadFileWmsUnplannedWarehousing.do" enctype="multipart/form-data">
<input type="hidden" value="${x_wmsuwid}" name="id"/>
<input type="hidden" value="${x_type}" name="type"/>

<div align="center"><h3 style="color:blue">非计划入库导入</h3></div>

 <html:file property="myFile" size="40"></html:file>
<input type="button" value="导入" onclick="upPurchase();" id="start"/>

<div align="center" style="margin: 20px 20px;">
<input type="button" onclick="window.close();" value="关闭"/>
</div>
</html:form>
