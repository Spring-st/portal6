<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script>
	function validateForm(form)
	{
		if(!validateQadOrEdiForm(form))
		{
			return false;
		}
		return true;
	}
	function selectWmsPart() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPart.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("name").innerHTML=v['id'];
			document.getElementById("qadPart_id").value=v['id'];
		};
	}
</script>
<html:javascript formName="qadOrEdiForm" staticJavascript="false" />
<html:form action="/updateQadOrEdiAction" onsubmit="return validateForm(this)">
	<html:hidden property="id" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="all.id" />:</td>
			<td>${X_OBJECT.id }</td>
		</tr>
		<tr>
			<td class="bluetext">ALC:</td>
			<td><html:text property="models" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">DES:</td>
			<td><html:text property="des" /></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="qadoredi.qadpart" />:</td>
			<td>
				<span id="name">${X_OBJECT.qadPart.id}</span>
				<html:hidden property="qadPart_id" styleId="qadPart_id"/><span class="required">*</span>
				<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a>
			</td>
		</tr>
		<tr>
			<td  class="bluetext">
					创建日期：
			</td>
			<td class="bluetext">
					<html:text property="createDate" size="20" /> 
					<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',false,'createDate',null,null,'qadOrEdiForm')"><img
					align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			</td>
		</tr>
		<tr>
			<td class="bluetext">数量:</td>
			<td><html:text property="qty" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><html:submit>
				<bean:message key="all.save" />
			</html:submit></td>
		</tr>
	</table>
</html:form>
