<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	 
//-->
</script>
<html:form action="/listPurchaseOrderPutInStorage.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pop.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pop.line" desc="行号"/>
	<input type="hidden" name="fields" value="pop.lotSer.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pop.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pop.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="pop.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="pop.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="pop.part.describe2" desc="描述二"/>
	<input type="hidden" name="fields" value="pop.supper.code" desc="供应商代码"/>
	<input type="hidden" name="fields" value="pop.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="pop.poipItem.capacity" desc="包装量"/>
	<input type="hidden" name="fields" value="pop.date" desc="入库时间"/>
	<input type="hidden" name="fields" value="pop.receipts_qty" desc="采购数量"/>
	<input type="hidden" name="fields" value="pop.poipItem.receiptQty" desc="收货数量"/>
	<input type="hidden" name="fields" value="pop.qty" desc="入库数量"/>

	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	 
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPurchaseOrderPutInStorage.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
					<th>采购单号</th>
					<th>行号</th>
					<th>条码编号</th>
					<th>物料号</th>
					<th>DPI</th>
					<th>原厂编号</th>
					<th>描述一</th>
					<th>描述二</th>
					<th>供应商代码</th>
					<th>库位</th>
					<th>包装量</th>
					<th>入库时间</th>
					<th>采购数量</th>
					<th>收货数量</th>
					<th>入库数量</th>
					<th>是否同步</th>
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

