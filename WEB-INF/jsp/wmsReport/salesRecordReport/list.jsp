<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	 
</script>
<html:form action="/listSalesRecordReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipId.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipItemId.customerPlanId.customer.name1" desc="客户名称"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipItemId.customerPlanId.operation" desc="业务员"/>
	<input type="hidden" name="fields" value="salesWorkorder.lotSer.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.count" desc="数量"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipItemId.customerPlanId.unitPrice" desc="单价"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipItemId.customerPlanId.sotaxc" desc="税率"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipItemId.customerPlanId.curr" desc="币种"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listSalesRecordReport.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>
				<page:order order="ship_id" style="text-decoration:none">
					<bean:message key="salesWorkorder.shipId" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
			</th>
			<th>客户名称</th>
			<th>业务员</th>
			<th>
				<page:order order="lotSer_id" style="text-decoration:none">
					<bean:message key="salesWorkorder.lotSer" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
			</th>
			
			<th><bean:message key="salesWorkorder.part.id" /></th>
			<th><bean:message key="salesWorkorder.part.oldCode" /></th>	
			<th>数量</th>
			<th>单价</th>
			<th>税率</th>
			<th>币种</th>
		</tr>
	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
				<bean:define id="sumamount" value="${sumamount+(X_OBJECT.count)}"/>
				<fmt:formatNumber value="${X_OBJECT.shipItemId.customerPlanId.unitPrice * X_OBJECT.count}" maxFractionDigits="2" pattern="#0.00#" minFractionDigits="2" var="price"/>
				<fmt:formatNumber var="sumPrice" value="${sumPrice + price}" pattern="#0.00#" maxFractionDigits="2" minFractionDigits="2" />
			</logic:iterate>
		</tbody>
		</table>
		<table width=100% border=0 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
			<tr align="left">
				<td class="bluetext">合计数量:&nbsp;&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="2" minFractionDigits="2"/></td>
			</tr>
			<tr align="left">
				<td class="bluetext">合计金额:&nbsp;
					<c:if test="${curr ne '1'}">
						<fmt:formatNumber value="${sumPrice}" maxFractionDigits="2" minFractionDigits="2"/>			
					</c:if>
				</td>
			</tr>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
