<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

</script>
<html:form action="/listForecastPeriodInventoryReportAction.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.part.id" desc="物料编码"/>
	<input type="hidden" name="fields" value="entity.part.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="entity.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="entity.location" desc="库位"/>
	<input type="hidden" name="fields" value="entity.currentQty" desc="当前库存"/>
	<input type="hidden" name="fields" value="entity.hour2Qty" desc="未来2小时"/>
	<input type="hidden" name="fields" value="entity.hour4Qty" desc="未来4小时"/>
	<input type="hidden" name="fields" value="entity.hour6Qty" desc="未来6小时"/>
	<input type="hidden" name="fields" value="entity.hour8Qty" desc="未来8小时"/>
	<input type="hidden" name="fields" value="entity.hour10Qty" desc="未来10小时"/>
	<input type="hidden" name="fields" value="entity.hour12Qty" desc="未来12小时"/>
	<input type="hidden" name="fields" value="entity.hour14Qty" desc="未来14小时"/>
	<input type="hidden" name="fields" value="entity.hour16Qty" desc="未来16小时"/>
	<input type="hidden" name="fields" value="entity.hour18Qty" desc="未来18小时"/>
	<input type="hidden" name="fields" value="entity.hour20Qty" desc="未来20小时"/>
	<input type="hidden" name="fields" value="entity.hour22Qty" desc="未来22小时"/>
	<input type="hidden" name="fields" value="entity.hour24Qty" desc="未来24小时"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listForecastPeriodInventoryReportAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="10%"><page:order order="id" style="text-decoration:none">
					<bean:message key="projectedinventory.part" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				    <th><bean:message key="projectedinventory.part.name" /></th>
				    <th><bean:message key="all.part.unit" /></th>
				    <th><bean:message key="projectedinventory.location" /></th>
				    <th><bean:message key="projectedinventory.currentqty" /></th>
				    <th><bean:message key="projectedinventory.hour2qty" /></th>
				    <th><bean:message key="projectedinventory.hour4qty" /></th>
				    <th><bean:message key="projectedinventory.hour6qty" /></th>
				    <th><bean:message key="projectedinventory.hour8qty" /></th>
				    <th><bean:message key="projectedinventory.hour10qty" /></th>
				    <th><bean:message key="projectedinventory.hour12qty" /></th>
				    <th><bean:message key="projectedinventory.hour14qty" /></th>
				    <th><bean:message key="projectedinventory.hour16qty" /></th>
				    <th><bean:message key="projectedinventory.hour18qty" /></th>
				    <th><bean:message key="projectedinventory.hour20qty" /></th>
				    <th><bean:message key="projectedinventory.hour22qty" /></th>
				    <th><bean:message key="projectedinventory.hour24qty" /></th>
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

