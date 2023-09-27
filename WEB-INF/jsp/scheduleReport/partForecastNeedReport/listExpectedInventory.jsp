<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>
<html:form action="/listExpectedInventoryReport.do"
	styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pfnr.part.vend" desc="供应商" />
	<input type="hidden" name="fields" value="pfnr.part.productSpecifications" desc="车型" />
	<input type="hidden" name="fields" value="pfnr.part.id" desc="零件号" />
	<input type="hidden" name="fields" value="pfnr.part.productClass" desc="物料类型" />
	<input type="hidden" name="fields" value="pfnr.part.name " desc="零件名称" />
	<input type="hidden" name="fields" value="pfnr.part.highQty" desc="最大库存" />
	<input type="hidden" name="fields" value="pfnr.part.lowQty" desc="最小库存" />
	<input type="hidden" name="fields" value="pfnr.part.securityQty" desc="安全库存" />
	<input type="hidden" name="fields" value="pfnr.currentQty" desc="当前库存" />
	<!--  <input type="hidden" name="fields" value="pfnr.part.productClass" desc="产品类" />-->
	<!-- <input type="hidden" name="fields" value="pfnr.part.productClass" desc="库存状态" /> -->
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/listExpectedInventoryReport.do" method="post">
	<table class="data" width="4500px;" word-break="keep-all;">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="100px;">序号</th>
				<th width="100px;"><page:order order="partId"
						style="text-decoration:none">
					供应商代码
					<page:desc>
							<img src="images/down.gif" border="0" />
						</page:desc>
						<page:asc>
							<img src="images/up.gif" border="0" />
						</page:asc>
					</page:order></th>
				<th width="100px;">零件号</th>
				<th width="100px;">零件名称</th>
				<th width="100px;">车型</th>
				<th width="100px;">物料类型</th>
				<th width="100px;">Min(ea)</th>
				<th width="100px;">Max(ea)</th>
				<th width="100px;">安全库存(ea)</th>
				<th width="100px;">当前库存</th>
				<c:forEach var="dateStr" items="${dateList}">
					<th>${dateStr}</th>
				</c:forEach>
			</tr>

		</thead>

		<tbody id="datatable">
			<c:forEach items="${X_RESULTLIST }" var="X_OBJECT"
				varStatus="indexStatus">
				<tr id="a${X_OBJECT.part.id}" align="center">
					<td nowrap="nowrap">${indexStatus.index+1}</td>
					<td nowrap="nowrap">${X_OBJECT.part.vend }</td>
					<td nowrap="nowrap">${X_OBJECT.part.id }</td>
					<td nowrap="nowrap">${X_OBJECT.part.name }</td>
					<td nowrap="nowrap">${X_OBJECT.part.productSpecifications }</td>
					<td nowrap="nowrap">${X_OBJECT.part.productClass }</td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.lowQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.highQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.securityQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.currentQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected0Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected0Qty) && (X_OBJECT.projected0Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected0Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected0Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected1Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected1Qty) && (X_OBJECT.projected1Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected1Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected1Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected2Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected2Qty) && (X_OBJECT.projected2Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected2Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected2Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected3Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected3Qty) && (X_OBJECT.projected3Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected3Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected3Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected4Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected4Qty) && (X_OBJECT.projected4Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected4Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected4Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected5Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected5Qty) && (X_OBJECT.projected5Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected5Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected5Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected6Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected6Qty) && (X_OBJECT.projected6Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected6Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected6Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected7Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected7Qty) && (X_OBJECT.projected7Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected7Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected7Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected8Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected8Qty) && (X_OBJECT.projected8Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected8Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected8Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected9Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected9Qty) && (X_OBJECT.projected9Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected9Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected9Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected10Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected10Qty) && (X_OBJECT.projected10Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected10Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected10Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected11Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected11Qty) && (X_OBJECT.projected11Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected11Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected11Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected12Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected12Qty) && (X_OBJECT.projected12Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected12Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected12Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected13Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected13Qty) && (X_OBJECT.projected13Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected13Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected13Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected14Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected14Qty) && (X_OBJECT.projected14Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected14Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected14Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected15Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected15Qty) && (X_OBJECT.projected15Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected15Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected15Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected16Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected16Qty) && (X_OBJECT.projected16Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected16Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected16Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected17Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected17Qty) && (X_OBJECT.projected17Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected17Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected17Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected18Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected18Qty) && (X_OBJECT.projected18Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected18Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected18Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected19Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected19Qty) && (X_OBJECT.projected19Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected19Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected19Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected20Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected20Qty) && (X_OBJECT.projected20Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected20Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected20Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected21Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected21Qty) && (X_OBJECT.projected21Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected21Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected21Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected22Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected22Qty) && (X_OBJECT.projected22Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected22Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected22Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected23Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected23Qty) && (X_OBJECT.projected23Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected23Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected23Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected24Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected24Qty) && (X_OBJECT.projected24Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected24Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected24Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected25Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected25Qty) && (X_OBJECT.projected25Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected25Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected25Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected26Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected26Qty) && (X_OBJECT.projected26Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected26Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected26Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected27Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected27Qty) && (X_OBJECT.projected27Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected27Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected27Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected28Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected28Qty) && (X_OBJECT.projected28Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected28Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected28Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected29Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected29Qty) && (X_OBJECT.projected29Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected29Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected29Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected30Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected30Qty) && (X_OBJECT.projected30Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected30Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected30Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected31Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected31Qty) && (X_OBJECT.projected31Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected31Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected31Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected32Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected32Qty) && (X_OBJECT.projected32Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected32Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected32Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected33Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected33Qty) && (X_OBJECT.projected33Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected33Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected33Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected34Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected34Qty) && (X_OBJECT.projected34Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected34Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected34Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected35Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected35Qty) && (X_OBJECT.projected35Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected35Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected35Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected36Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected36Qty) && (X_OBJECT.projected36Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected36Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected36Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected37Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected37Qty) && (X_OBJECT.projected37Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected37Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected37Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected38Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected38Qty) && (X_OBJECT.projected38Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected38Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected38Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected39Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected39Qty) && (X_OBJECT.projected39Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected39Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected39Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected40Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected40Qty) && (X_OBJECT.projected40Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected40Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected40Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected41Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected41Qty) && (X_OBJECT.projected41Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected41Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected41Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected42Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected42Qty) && (X_OBJECT.projected42Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected42Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected42Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected43Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected43Qty) && (X_OBJECT.projected43Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected43Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected43Qty }</fmt:formatNumber>
					</td>
					<td nowrap="nowrap"
						<c:if test="${X_OBJECT.projected44Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
						<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected44Qty) && (X_OBJECT.projected44Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
						<c:if test="${X_OBJECT.projected44Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
						<fmt:formatNumber>${X_OBJECT.projected44Qty }</fmt:formatNumber>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
	applyRowStyle(document.all('datatable'));
</script>

