<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function add() {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.new.title&newUnplannedReasons.do' , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.edit.title&editUnplannedReasons.do?id=' + id , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
//-->
</script>
<html:form action="/listUnplannedReasons.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="br.instructions" desc="原因代码"/>
	<input type="hidden" name="fields" value="br.describe" desc="描述"/>
	<input type="hidden" name="fields" value="br.expenses_course" desc="费用科目"/>
	<input type="hidden" name="fields" value="br.department_cost" desc="费用部门"/>
	<input type="hidden" name="fields" value="br.date" desc="时间"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listUnplannedReasons.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>原因代码</th>
				<th>描述</th>
				<th>费用科目</th>
				<th>费用部门</th>
				<th>时间</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
