<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script type="text/javascript" src="includes/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function printExcel() {
		window.location.href = "listShippingOrderReport.do?isExcel=true";
	}
</script>
<html:form action="listShippingOrderReport">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="po.code" desc="订单编号" />
	<input type="hidden" name="fields" value="po.supplier.name" desc="供应商" />
	<input type="hidden" name="fields" value="po.createDate" desc="创建日期" />
	<input type="hidden" name="fields" value="po.arrivalDate" desc="计划到货日期" />
	<input type="hidden" name="fields" value="po.realDate" desc="实际到货日期" />
	<input type="hidden" name="fields" value="po.status" desc="订单状态" />


<html:hidden property="order" />
	<html:hidden property="descend" />

	<input type="hidden" id="ExportType" name="exportType"
		style="display: none" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="listShippingOrderReport.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>序号</th>
				<th>订单编号</th>
				<th>供应商</th>
				<th>订单日期</th>
				<th>计划到货日期</th>
				<th>实际到货日期</th>
				<th>订单状态</th>
				<th>差异</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<c:forEach items="${X_RESULTLIST }" var="X_OBJECT"
				varStatus="statusIndex">
				<tr>
					<td width="3%" align="center">${statusIndex.index+1 }</td>
					<td width="20%" align="center">${X_OBJECT.code}</td>
					<td width="25%" align="center">${X_OBJECT.supplier.name}</td>
					<td width="10%" align="center">
						<c:if test="${X_OBJECT.createDate!= null }">
							<bean:write name="X_OBJECT" property="createDate"
								format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td width="10%" align="center"><c:if
							test="${X_OBJECT.arrivalDate!= null }">
							<bean:write name="X_OBJECT" property="arrivalDate"
								format="yyyy/MM/dd" />
						</c:if></td>

					<td width="10%" align="center"><c:if
							test="${X_OBJECT.realDate!= null }">
							<bean:write name="X_OBJECT" property="realDate"
								format="yyyy/MM/dd" />
						</c:if></td>
					<td width="10%" align="center"><c:choose>
							<c:when test="${(not empty X_OBJECT.realDate) && (not empty X_OBJECT.arrivalDate) }">
								<c:choose>
									<c:when test="${X_OBJECT.arrivalDate eq X_OBJECT.realDate }">
										正常
									</c:when>
									<c:when test="${X_OBJECT.arrivalDate lt X_OBJECT.realDate }">
										延期
									</c:when>
									<c:when test="${X_OBJECT.arrivalDate gt X_OBJECT.realDate }">
										提前
									</c:when>
								</c:choose>
							</c:when>
							
						</c:choose></td>
					<td width="10%" align="center">
						<c:if test="${(not empty X_OBJECT.realDate) && (not empty X_OBJECT.arrivalDate) }">
							<fmt:formatNumber value="${X_OBJECT.differenceCount}" maxFractionDigits="0" minFractionDigits="0" />
						</c:if>		
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
	applyRowStyle(document.all('datatable'));
</script>
