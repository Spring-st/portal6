<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
function upPurchase(){
	$('#start').get(0).style.display = "none";
	$('#over').get(0).style.display = "inline";
	
	var myFile = document.getElementById('myFile').value;
	if(myFile==""){
		alert("请填写导入文件路径!");
		return false;
	} 
	
	document.wmsUnplannedWarehousingQueryForm.submit();
}       
//-->
</script>
<html:form action="/upLoadFileWmsUW.do" enctype="multipart/form-data">
<input type="hidden" value="${type}" name="type"/>

<c:if test="${type=='1'}"><div align="center"><h3 style="color:blue">原材料计划外导入</h3></div></c:if>
<c:if test="${type=='2'}"><div align="center"><h3 style="color:blue">成品计划外导入</h3></div></c:if>

<input type="hidden" value="${x_wmsuwid}" name="wmsuwid"/>
<html:file property="myFile" size="40"></html:file>
<input type="button" value="导入" onclick="upPurchase();" id="start"/>
<input type="button" value="导入" disabled="disabled" id="over" style="display: none;"/>

<div align="center" style="margin: 20px 20px;">
<input type="button" onclick="window.close();" value="关闭"/>
</div>
</html:form>
