<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
	 
</script>
<html:form action="/listOutStorageDetailReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesWorkorder.shipId.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="salesWorkorder.lotSer.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.oldCode" desc="原厂编号"/>
	<input type="hidden" name="fields" value="salesWorkorder.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="salesWorkorder.count" desc="数量"/>
	<input type="hidden" name="fields" value="salesWorkorder.outDate" desc="出库时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listOutStorageDetailReport.do" method="post">
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
			<th><bean:message key="salesWorkorder.part.dpiNo" /></th>
			<th><bean:message key="salesWorkorder.part.oldCode" /></th>	
			<th><bean:message key="salesWorkorder.part.describe1" /></th>
			<th>
				<page:order order="out_date" style="text-decoration:none">
					<bean:message key="salesWorkorder.outDate" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order>
			</th>
			<th><bean:message key="salesWorkorder.isSync" /></th>
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
