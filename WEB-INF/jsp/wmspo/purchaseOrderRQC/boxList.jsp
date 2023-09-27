<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>
<script type="text/javascript" src="includes/basicJS.js">
</script>
<script type="text/javascript" src="includes/jquery-1.3.2.js">
</script>
<script type="text/javascript">
function poRqc(type) {
	if (type != "") {
		var r = false;
		var el = document.getElementsByName('ids');
		var len = el.length;
		var str = "";
		var count = 0;
		for ( var i = 0; i < len; i++) {
			if ((el[i].type == "checkbox") && (el[i].checked == true)) {
				if (el[i].id != "checkAll") {
					str = str + el[i].value + ",";
					count++;
				}
			}
		}
		if (count == 0) {
			alert('选勾选要扫描的批次！');
			return false;
		}

		r = confirm("已选中批次确认采购质检吗？");
		if (r) {
			v = window
					.showModalDialog(
							'showDialog.do?title=purchaseOrderRqcDetermine.title&purchaseOrderRqcDetermine.do?arrays='
									+ str + "&type=3", null,
							'dialogWidth:500px;dialogHeight:300px;status:no;help:no;scroll:no');
			if (v) {
				window.location.href = 'purchaseOrderRqcBoxList.do';
			}
			;
		}
	}
}
</script>
<html:form action="/purchaseOrderRqcBoxList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号" />
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号" />
	<input type="hidden" name="fields" value="pb.po_line" desc="行号" /><%--
	<input type="hidden" name="fields" value="pb.single.code" desc="调料单号" />--%>
	<input type="hidden" name="fields" value="pb.psoItem.portalShipOrder.code" desc="发货单号" />
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号" />
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pb.po_supp" desc="供应商编号"/>
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="pb.part.unit" desc="单位" />
	<input type="hidden" name="fields" value="pb.date" desc="时间" />

	<html:hidden property="order" />
	<html:hidden property="descend" />
    <input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/purchaseOrderRqcBoxList.do" method="post">
	<table class="data" style="word-break: keep-all; width: 1200px;">
		<thead>
			<tr bgcolor="#9999ff" align="center">
				<th width="2%">
					<input type="checkbox" value="0" id="checkAll"
						onclick="selectAll();" />
				</th>
				<th>
					条码编号
				</th>
				<th>
					采购单号
				</th>
				<th>
					行号
				</th>
				<th>
					发货单号
				</th>
				<th>
					物料号
				</th>
				<th>
					DPI
				</th>
				<th>
					原厂编号
				</th>
				<th>
					供应商编号
				</th>
				<th>
					描述一
				</th>
				<th>
					库位
				</th>
				<th>
					数量
				</th>
				<th>
					单位
				</th>
				<th>
					时间
				</th>
				<th>
					质检状态
				</th>
				<th>
					冻结状态
				</th>
				<th>
					条码状态
				</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="boxlist">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="boxRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>
