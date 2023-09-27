<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
  	 function select(id, code, capacity) {
		var result = [];
		result['id'] = id;
		result['code'] = code;
		result['capacity'] = capacity;
		
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/selectTool.do">
	<input type="hidden" value="${x_arrays}" name="arrays"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="ct.code" desc="器具编码"/>
	<input type="hidden" name="fields" value="ct.capacity" desc="容量"/>
	<input type="hidden" name="fields" value="ct.date" desc="日期"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/selectTool.do" method="post">
	<html:hidden property="id" />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>器具编码</th>
				<th>器具容量</th>
				<th>日期</th>
				<th>状态</th>
				<th>选择器具</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				 <tr id="r${X_OBJECT.id}">
					<td>${X_OBJECT.code}</td>
					<td> ${X_OBJECT.capacity} </td>
					<td> ${X_OBJECT.date} </td>
					<td> ${X_OBJECT.status.chnShortDescription} </td>
					<td><a href='javascript:select("${X_OBJECT.id}", "${X_OBJECT.code}", "${X_OBJECT.capacity}");'>确认</a></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

