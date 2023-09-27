<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
		function select(id, describe) {
		var result = [];
		result['id'] = id;
		result['describe'] = describe;
		
		window.parent.returnValue = result;
		window.parent.close();
	}
//-->
</script>
<html:form action="/listBadReasonsSelect.do" styleId="formId">
<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="psoi.portalShipOrder.code" desc="不合格原因"/>
	<input type="hidden" name="fields" value="psoi.poipItem.poip_number.po_number" desc="时间"/>
	<input type="hidden" name="fields" value="psoi.poipItem.itemNumber.id" desc="备注"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listBadReasonsSelect.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					id
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>

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
				<th>时间</th>
				<th>维护人</th>
				<th>状态</th>
				<th>备注</th>
				<th></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="r${X_OBJECT.id}">
					<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.id}</a></td>
					<td>${X_OBJECT.describe }</td>
					<td>${X_OBJECT.date }</td>
					<td>${X_OBJECT.user.name}</td>
					<td>${X_OBJECT.status}</td>
					<td>${X_OBJECT.remark}</td>
					<td><a href='javascript:select("${X_OBJECT.id}", "${X_OBJECT.describe}");'><bean:message key="all.select"/></a></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

