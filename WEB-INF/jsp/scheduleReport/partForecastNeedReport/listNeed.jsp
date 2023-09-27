<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script language="javascript" src="includes/table.js"></script>
<html:form action="/listPartForecastNeedReportAction.do"
	styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pfnr.part.vend" desc="供应商" />
	<input type="hidden" name="fields" value="pfnr.part.id" desc="车型" />
	<input type="hidden" name="fields" value="pfnr.part.productClass" desc="零件号" />
	<input type="hidden" name="fields" value="pfnr.part.productClass" desc="物料类型" />
	<input type="hidden" name="fields" value="pfnr.part.productClass" desc="库存状态" />

	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />

	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/listPartForecastNeedReportAction.do" method="post">
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
					<td nowrap="nowrap">${indexStatus.index+1 }</td>
					<td nowrap="nowrap">${X_OBJECT.part.vend }</td>
					<td nowrap="nowrap">${X_OBJECT.part.id }</td>
					<td nowrap="nowrap">${X_OBJECT.part.name }</td>
					<td nowrap="nowrap">${X_OBJECT.part.productSpecifications }</td>
					<td nowrap="nowrap">${X_OBJECT.part.partType }</td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.lowQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.highQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.part.securityQty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.currentQty }</fmt:formatNumber></td>
					
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no0Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no1Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no2Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no3Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no4Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no5Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no6Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no7Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no8Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no9Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no10Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no11Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no12Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no13Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no14Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no15Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no16Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no17Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no18Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no19Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no20Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no21Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no22Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no23Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no24Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no25Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no26Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no27Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no28Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no29Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no30Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no31Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no32Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no33Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no34Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no35Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no36Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no37Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no38Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no39Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no40Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no41Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no42Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no43Needqty }</fmt:formatNumber></td>
					<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no44Needqty }</fmt:formatNumber></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
	applyRowStyle(document.all('datatable'));
</script>

