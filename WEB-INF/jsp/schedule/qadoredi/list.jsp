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
			'showDialog.do?title=all.new&newQadOrEdiAction.do' , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			//var table = document.all('datatable');
			//appendRow(table, v);
		    // applyRowStyle(table);
		    window.location.href="listQadOrEdiAction.do";
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=all.modify&editQadOrEdiAction.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			//updateRow(document.all('r' + id), v);
			window.location.href="listQadOrEdiAction.do";
		};
	}
	function remove(id) {
		window.location.href="deleteQadOrEdiAction.do?id="+id;
	}
//-->
</script>
<html:form action="/listQadOrEdiAction.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.models" desc="ALC"/>
	<input type="hidden" name="fields" value="entity.des" desc="DES"/>
	<input type="hidden" name="fields" value="entity.qadPart.id" desc="父件编码"/>
	<input type="hidden" name="fields" value="entity.createDate" desc="创建日期"/>
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
<page:form action="/listQadOrEdiAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%"><page:order order="id" style="text-decoration:none">
					ALC
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>DES</th>
				<th><bean:message key="qadoredi.qadpart" /></th>
				<th>创建时间</th>
				<th>数量</th>
				<th><bean:message key="all.operate" /></th>
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

