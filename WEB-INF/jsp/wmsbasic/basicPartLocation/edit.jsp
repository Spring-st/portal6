<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
function selectStorageLocation() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2.expense.selectTA.title&selectStorageLocation.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("location_id").value=v['id'];
			document.getElementById("loc_code").innerHTML=v['code'];
		};
	}
</script>
<html:form action="/updatePartLocation">
	<html:hidden property="id" />
	<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">物料库位修改</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		 <tr>
			<td class="bluetext">物料编号:</td>
			<td><html:hidden property="part_id" />${x_partLoc.part.id}</td>
			<td class="bluetext">库位号:</td>
			<td><html:hidden property="location_id" />
			<span id="loc_code">${x_partLoc.location.code}</span>
			<a href="javascript:selectStorageLocation();"><img src="images/select.gif" border="0"/></a></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="wmsPart.describe1" />:</td>
			<td>${x_partLoc.part.describe1}</td>
			<td class="bluetext"><bean:message key="wmsPart.describe2" />:</td>
			<td>${x_partLoc.part.describe2}</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="wmsPart.unit" />:</td>
			<td>${x_partLoc.part.unit}</td>
			<td class="bluetext">推荐天数:</td>
			<td>${x_partLoc.part.recommend_date}</td>
		</tr>
		<tr>
			<td class="bluetext">产品类型:</td>
			<td>${x_partLoc.part.productClass}</td>
			<td class="bluetext">物料名称:</td>
			<td>${x_partLoc.part.name}</td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /> </html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
