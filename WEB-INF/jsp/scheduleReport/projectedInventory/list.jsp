<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

</script>
<html:form action="/listProjectedInventoryReportAction.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.part.id" desc="物料编码"/>
	<input type="hidden" name="fields" value="entity.part.name" desc="物料名称"/>
	<input type="hidden" name="fields" value="entity.part.vend" desc="供应商代码"/>
	<input type="hidden" name="fields" value="entity.part.highQty" desc="高储库存"/>
	<input type="hidden" name="fields" value="entity.part.lowQty" desc="低储库存"/>
	<input type="hidden" name="fields" value="entity.part.securityQty" desc="安全库存"/>
	<input type="hidden" name="fields" value="entity.currentQty" desc="当前库存"/>
	<input type="hidden" name="fields" value="entity.part.unit" desc="单位"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProjectedInventoryReportAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%"><page:order order="id" style="text-decoration:none">
					<bean:message key="projectedinventory.part" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
					</page:order></th>
				    <th><bean:message key="projectedinventory.part.name" /></th>
				    <th>供应商编码</th>
				    <th>高储库存</th>
				    <th>低储库存</th>
				    <th>安全库存</th>
				    <%--<th><bean:message key="projectedinventory.location" /></th>
				    --%><th><bean:message key="projectedinventory.currentqty" /></th>
				    <th><bean:message key="all.part.unit" /></th>
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

