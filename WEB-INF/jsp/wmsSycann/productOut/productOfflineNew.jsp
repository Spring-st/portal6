<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
 function selectWmsPart() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectPoHighLine.do', 
			null, 'dialogWidth:1024px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("partId").value=v['part'];
			document.getElementById("partSpan").innerHTML=v['part'];
			document.getElementById("shcodeId").value=v['test3'];
			document.getElementById("shcode").innerHTML=v['test3'];
		};
	}

	function selectTool() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectTool.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("capacityId").innerHTML=v['capacity'];
			document.getElementById("toolId").value=v['id'];
			document.getElementById("toolSpan").innerHTML=v['code'];
		};
	}
//-->
</script>
<html:form action="/insertProductOffline.do">
<div style="font-size: 20px; color: blue; text-align: center; margin: 10px 0px;">成品下线</div>
<table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th>物料编号</th>
				<th>描述一</th>
				<th>库位</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			 <c:forEach var="x_object" items="${x_listMap}">
			        <tr>
                       <td>${x_object.part.id}</td>
                       <td>${x_object.part.describe1}</td>
                       <td>生成线上</td>
                       <td>${x_object.sumqty}</td>
                    </tr>		
			  </c:forEach>
		</tbody>
	</table>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		 <tr>
			<td class="bluetext">器具编号:</td>
			<td><input type="hidden" id="toolId" name="tool"/><span id="toolSpan"></span>
			<a href="javascript:selectTool();"><img src="images/select.gif" border="0"/></a>
			</td>
			<td class="bluetext">包装量：</td>
			<td id="capacityId" style="color: red; font-weight: bolder;"></td>
			</tr>
		 <tr>
			<td class="bluetext">总成编号:</td>
			<td><input type="hidden" id="partId" name="part"/>
			<span id="partSpan"></span>
			<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a>
			</td>
			<td class="bluetext">赛赫条码:</td>
			<td>
			<input type="hidden" id="shcodeId" name="shcodeId"/>
			<span id="shcode"></span></td>
			</tr>
			<tr>
			<td class="bluetext">数量:</td>
			<td><input type="text" name="amount" value="1" readonly="readonly"/></td>
			</tr>
	</table>
	<hr/>

	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
