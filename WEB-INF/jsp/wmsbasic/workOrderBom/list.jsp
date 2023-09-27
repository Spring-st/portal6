<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>

<script type="text/javascript">
function addWorkOrderBom() {
	v = window.showModalDialog(
			'showDialog.do?title=WorkOrderBom.new.title&newWorkOrderBom.do',
			null,
			'dialogWidth:400px;dialogHeight:450px;status:no;help:no;scroll:no');
	if (v) {
		var table = document.all('datatable');
		appendRow(table, v);
		applyRowStyle(table);
	}
	;
}

function edit(id) {
	v = window
			.showModalDialog(
					'showDialog.do?title=WorkOrderBom.edit.title&editWorkOrderBom.do?id=' + id,
					null,
					'dialogWidth:400px;dialogHeight:450px;status:no;help:no;scroll:no');
	if (v) {
		updateRow(document.all('r' + id), v);
	}
	;
}
</script>
<html:form action="listWorkOrderBom" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="workOrderBom.workOrderNo" desc="加工单号"/>
	<input type="hidden" name="fields" value="workOrderBom.productNo" desc="产成品号"/>
	<input type="hidden" name="fields" value="workOrderBom.partNo" desc="原料号"/>
	<input type="hidden" name="fields" value="workOrderBom.allNeedQty" desc="组件总需求数量"/>
	<input type="hidden" name="fields" value="workOrderBom.workingOrder" desc="工序"/>
	<input type="hidden" name="fields" value="workOrderBom.workingPosition" desc="工位" />
	<input type="hidden" name="fields" value="workOrderBom.singleNeedQty" desc="单位需求量"/>
	<input type="hidden" name="fields" value="workOrderBom.site" desc="工厂"/>
	<input type="hidden" name="fields" value="workOrderBom.domain" desc="域" />
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />
	<input type="hidden" id="statusCheck" name="statusCheck" />

	<table width=100% border="0" cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="listWorkOrderBom" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%">
					<page:order order="id" style="text-decoration:none">
					加工单号
					<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order>
				</th>
				<th>
					产成品号
				</th>
				<th>
					原料号
				</th>
				<th>
					组件总需求数量
				</th>
				<th>
					工序
				</th>
				<th>
					工位
				</th>
				<th>
					单位需求量
				</th>
				<th>
					工厂
				</th>
				<th>
					域
				</th>
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

