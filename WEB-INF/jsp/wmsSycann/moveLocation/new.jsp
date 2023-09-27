<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
 	function selectStorageLocation() { 
 	var v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectStorageLocation.do', 
			null, 'dialogWidth:900px;dialogHeight:650px;status:no;help:no;scroll:no');
	     if (v) {
			  document.getElementById("locationId").value=v['code'];
			  document.getElementById("codeSpan").innerHTML=v['code'];
	   };
		 
	}
</script>
<html:form action="/insertMoveLibrary.do" styleId="formId">
<input type="hidden" name="array" value="${x_array}"/>
	<div style="font-size: 20px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">请选择更换库位</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0 align="center">
		<tr>
			<td class="bluetext" align="center">库位编号:</td>
			<td><input type="hidden" name="location" id="locationId"/>
			<span id="codeSpan"></span>
			<a href="javascript:selectStorageLocation();"><img src="images/select.gif" border="0"/></a>
			<span class="id">*</span></td>
		</tr>
	</table>
	 <hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /> </html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
 
