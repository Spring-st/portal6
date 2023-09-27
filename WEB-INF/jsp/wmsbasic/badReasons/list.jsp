<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function addBadReasons() {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.new.title&newBadReasons.do' , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			var table = document.all('datatable');
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=StoreRoom.edit.title&editBadReasons.do?id=' + id , 
			null, 'dialogWidth:500px;dialogHeight:400px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
	}
	
//-->
</script>
<html:form action="/listBadReasons.do" styleId="formId">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="br.describe" desc="不合格原因"/>
	<input type="hidden" name="fields" value="br.type.chnShortDescription" desc="物料类型"/>
	<input type="hidden" name="fields" value="br.user.name" desc="维护人"/>
	<input type="hidden" name="fields" value="br.status.chnShortDescription" desc="状态"/>
	<input type="hidden" name="fields" value="br.remark" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listBadReasons.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%"><page:order order="name"
					style="text-decoration:none">
					不合格原因
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>物料类型</th>
				<th>维护人</th>
				<th>状态</th>
				<th>备注</th>
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

