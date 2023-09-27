<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

</script>
<html:form action="/listPartForecastNeedReportAction.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pfnr.part.id" desc="原材料号"/>
	<%--
	<input type="hidden" name="fields" value="pfnr.part.vend" desc="供应商"/>
	<input type="hidden" name="fields" value="pfnr.part.productClass" desc="产品类"/>
	--%>
	<input type="hidden" name="fields" value="pfnr.currentQty " desc="当前库存"/>
	<input type="hidden" name="fields" value="pfnr.part.highQty" desc="高储库存"/>
	<input type="hidden" name="fields" value="pfnr.part.lowQty " desc="低储库存"/>
	<input type="hidden" name="fields" value="pfnr.part.securityQty" desc="安全库存"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listPartForecastNeedReportAction.do" method="post">
	<table class="data" width="4500px;" word-break="keep-all;" >
		<thead>
			<tr bgcolor="#9999ff">
				<th width="100px;">
					<page:order order="part_Id" style="text-decoration:none">
					原材料号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order>
				</th>
				<th width="100px;">中文描述</th>
				<th width="100px;">当前库存</th>
				<th width="100px;">高储库存</th>
				<th width="100px;">低储库存</th>
				<th width="100px;">安全库存</th>
				<th width="100px;"></th>
				<th width="100px;">过去</th>
				<c:forEach var="dateStr" items="${dateList}">
					<th>${dateStr} </th>
				</c:forEach>
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

