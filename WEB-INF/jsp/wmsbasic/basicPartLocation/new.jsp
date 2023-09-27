<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function selectWmsPart() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPart.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("name").innerHTML=v['id'];
			document.getElementById("part_id").value=v['id'];
		};
	}
function selectStorageLocation() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectStorageLocation.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("code").innerHTML=v['code'];
			document.getElementById("location_id").value=v['id'];
		};
	}
</script>
<html:form action="/insertPartLocation.do" >
<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">新增物料库位关联</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">物料编号:</td>
			<td><span id="name"></span>
 				<html:hidden property="part_id" styleId="part_id"/><span class="required">*</span>
  				<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a></td>
		</tr>
		<tr>
			<td class="bluetext">库位:</td>
			<td><span id="code"></span>
				<html:hidden property="location_id" styleId="location_id"/><span class="required">*</span>
  				<a href="javascript:selectStorageLocation();"><img src="images/select.gif" border="0"/></a></td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /> </html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
