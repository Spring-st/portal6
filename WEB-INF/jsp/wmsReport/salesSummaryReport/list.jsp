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
<html:form action="/listSalesSummaryReport.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesPreshiporder.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.customerCode" desc="客户编码"/>
	<input type="hidden" name="fields" value="salesPreshiporder.shPrintDate" desc="打印日期"/>
	<input type="hidden" name="fields" value="salesPreshiporder.matchStatus" desc="是否匹配（0-已匹配，1-未匹配）"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listSalesSummaryReport.do" method="post">
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
			<th>需求计划单号</th>
			<th>客户编码</th>
			<th>打印日期</th>
			<th>发货数量</th>
			<th>金额</th>
			<th>是否已匹配</th>
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
