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
function printLot() {
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
	window.location.href = "purchaseOrderBoxIdPrint.do?arrays=" + str;

}
</script>
<html:form action="/listPurchaseOrderLotPrint.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号" />
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号" />
	<input type="hidden" name="fields" value="pb.po_line" desc="行号" />
	<input type="hidden" name="fields" value="pb.psoItem.portalShipOrder.code" desc="发货单号" />
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号" />
	<input type="hidden" name="fields" value="pb.part.dpiNo" desc="DPI" />
	<input type="hidden" name="fields" value="pb.part.oldCode" desc="原厂编号" />
	<input type="hidden" name="fields" value="pb.po_supp" desc="供应商号" />
	<input type="hidden" name="fields" value="pb.part.describe1" desc="描述一" />
	<input type="hidden" name="fields" value="pb.number" desc="数量" />
	<input type="hidden" name="fields" value="pb.po_date" desc="时间" />
		
	<%--<input type="hidden" name="fields" value="pb.single.code" desc="调料单号" />--%>
	<html:hidden property="order" />
	<html:hidden property="descend" />
<input type="hidden" id="ExportType" name="exportType"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/listPurchaseOrderLotPrint" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff" align="center">
				<th width="2%">
					<input type="checkbox" value="0" id="checkAll"
						onclick="selectAll();" />
				</th>
				<th width="15%">
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
					数量
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
					批次状态
				</th>
				<th>
					打印状态
				</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<tr id="r${X_OBJECT.id}" align="center">
					<td>
						<%--<input type="checkbox" name="ids" value="${X_OBJECT.lot.id}"
							onclick="productbox_click();" />--%>
						<input type="checkbox" name="ids" value="${X_OBJECT.id}"
							onclick="productbox_click();" />		
					</td>
					<td>
						${X_OBJECT.lot.id}
					</td>
					<td>
						${X_OBJECT.po_number}
					</td>
					<td>
						${X_OBJECT.po_line}
					</td>
					<td>
					<c:if test="${X_OBJECT.psoItem!=null}">
						${X_OBJECT.psoItem.portalShipOrder.code}
					</c:if>
					<c:if test="${X_OBJECT.single!=null}">
						${X_OBJECT.single.code}
					</c:if>
					</td>
					<td>
						${X_OBJECT.part.id}
					</td>
					<td>
						${X_OBJECT.part.dpiNo}
					</td>
					<td>
						${X_OBJECT.part.oldCode}
					</td>
					<td>
						${X_OBJECT.po_supp}
					</td>
					<td>
						${X_OBJECT.part.describe1}
					</td>
					<td>
						${X_OBJECT.number}
					</td>
					<td>
						${X_OBJECT.po_date}
					</td>
					<td>
						<c:if test="${X_OBJECT.status_rqc==null}">未质检</c:if>
						<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
					</td>
					<td>
						${X_OBJECT.status_freeze.chnShortDescription}
					</td>
					<td>
						${X_OBJECT.status.chnShortDescription}
						<input type="hidden" value="${X_OBJECT.status}"
							id="${X_OBJECT.id}_status" />
					</td>
					<c:if test="${X_OBJECT.isPrint.chnDescription=='No'}">
						<td>
							<span style="color: red;">未打印</span>
						</td>
					</c:if>
					<c:if test="${X_OBJECT.isPrint.chnDescription=='Yes'}">
						<td>
							<span style="color: green;">已打印</span>
						</td>
					</c:if>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>
