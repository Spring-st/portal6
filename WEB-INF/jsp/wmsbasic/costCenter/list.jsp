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
			'showDialog.do?title=CostCenter2.new.title&newCostCenter.do' , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=CostCenter.edit.title&editCostCenter.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
//-->
</script>
<html:form action="/listCostCenter.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	 <input type="hidden" name="fields" value="costCenter.id" desc="ID"/>
	<input type="hidden" name="fields" value="costCenter.code" desc="成本中心名称"/>
	<input type="hidden" name="fields" value="costCenter.description" desc="描述"/>
	<%--<input type="hidden" name="fields" value="costCenter.description" desc="状态"/>--%>
	
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
<page:form action="/listCostCenter.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%"><page:order order="id" style="text-decoration:none">
					<bean:message key="costCenter.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>成本中心</th>
				<th>描述</th>
				<th>状态</th>
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

