<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>

<script type="text/javascript">
function addDailyProductPlan() {
	v = window
			.showModalDialog(
					'showDialog.do?title=DailyProductPlan.new.title&newDailyProductPlan.do',
					null,
					'dialogWidth:700px;dialogHeight:450px;status:no;help:no;scroll:no');
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
					'showDialog.do?title=DailyProductPlan.edit.title&editDailyProductPlan.do?id=' + id,
					null,
					'dialogWidth:700px;dialogHeight:450px;status:no;help:no;scroll:no');
	if (v) {
		updateRow(document.all('r' + id), v);
	}
	;
}
</script>
<html:form action="listDailyProductPlan" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="dailyProductPlan.workOrderNo" desc="加工单号"/>
	<input type="hidden" name="fields" value="dailyProductPlan.part" desc="物料号"/>
	<input type="hidden" name="fields" value="dailyProductPlan.orderType" desc="加工单类型"/>
	<input type="hidden" name="fields" value="dailyProductPlan.orderAttribute" desc="工单属性"/>
	<input type="hidden" name="fields" value="dailyProductPlan.site" desc="工厂"/>
	<input type="hidden" name="fields" value="dailyProductPlan.lineNo" desc="生产线" />
	<input type="hidden" name="fields" value="dailyProductPlan.qty" desc="生产数量"/>
	<input type="hidden" name="fields" value="dailyProductPlan.golineDate" desc="上线日期"/>
	<input type="hidden" name="fields" value="dailyProductPlan.offlineDate" desc="下线日期"/>
	<input type="hidden" name="fields" value="dailyProductPlan.procedureCode" desc="工艺代码"/>
	<input type="hidden" name="fields" value="dailyProductPlan.bomCode" desc="BOM代码"/>
	<input type="hidden" name="fields" value="dailyProductPlan.shift" desc="班次(A/B)" />
	<input type="hidden" name="fields" value="dailyProductPlan.bomOutFinish" desc="工单Bom是否转出完成"/>
	<input type="hidden" name="fields" value="dailyProductPlan.domain" desc="域" />
	
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
<page:form action="listDailyProductPlan" method="post">
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
					物料号
				</th>
				<th>
					加工单类型
				</th>
				<th>
					工单属性
				</th>
				<th>
					工厂
				</th>
				<th>
					生产线
				</th>
				<th>
					生产数量
				</th>
				<th>
					上线日期
				</th>
				<th>
					下线日期
				</th>
				<th>
					工艺代码
				</th>
				<th>
					BOM代码
				</th>
				<th>
					班次(A/B)
				</th>
				<th>
					工单Bom是否转出完成
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

