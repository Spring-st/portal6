<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
	var url = "viewCreateSalesPreShipOrderPlan.do?id=" + id;
	window.location.href = url;
}

function printExcel(){
	window.location.href = "listShippingOrder.do?isExcel=true";
}

function addSalesPreshiporder(){
	var url = "newSalesPreshiporderPlan.do";
	window.location.href = url;
}
</script>
<html:form action="listSalesPreshiporderPlan" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesPreshiporder.code" desc="预发货单号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.arrivaldate" desc="发货时间"/>
	<input type="hidden" name="fields" value="salesPreshiporder.remark" desc="备注"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	<input type="hidden" id="statusCheck" name="statusCheck"/>
	
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listSalesPreshiporderPlan.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
														
				<th width="25%">
					预发货单号
				</th>
														
				<th width="25%">
					发货时间
				</th>
				<th width="20%">
					<bean:message key="Remark"/> 
				</th>
				<th width="30%">
					<bean:message key="portalShipOrder.status"/> 
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
						<td width="25%" align="center">
								<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
						</td>
						<td width="25%" align="center">
							<c:if test="${X_OBJECT.arrivaldate!= null }">
								<bean:write name="X_OBJECT" property="arrivaldate" format="yyyy/MM/dd" />
							</c:if>
						</td>
						<td width="20%" align="center">
							${X_OBJECT.remark}
						</td>
						<td width="30%" align="center">
								<c:if test="${X_OBJECT.status!=null}">
									<span style="color:${X_OBJECT.status.color}"> 
									<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
								</c:if>
						</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



