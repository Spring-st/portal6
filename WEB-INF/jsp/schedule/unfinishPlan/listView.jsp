<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
function goTo(){
		var productionId = document.getElementsByName('productionId')[0].value;
		window.location.href="listUnfinishPlan.do?productionId="+productionId;
	}
</script>
<html:form action="/listJitUnfinishPlanViewAction.do">
	<input type="hidden" name="productionId" id="productionId" value="${productionId}"/>
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	 <input type="hidden" name="fields" value="entity.childPart.id" desc="组件物料编码"/>
	<input type="hidden" name="fields" value="entity.childPart.describe1" desc="物料描述"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fields" value="entity.childPart.unit" desc="单位"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr />
<page:form action="/listJitUnfinishPlanViewAction.do" method="post">
<input type="hidden" name="productionId" id="productionId" value="${productionId}"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th><bean:message key="qadoredi.qadpart" /></th>
				<th><page:order order="id" style="text-decoration:none">
					<bean:message key="jitproductionplan.childpart.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><bean:message key="jitproductionplan.childpart.describe1" /></th>
				<th><bean:message key="ediproduction.qty" /></th>
				<th><bean:message key="ediproduction.unit" /></th>
				<%--<th>2小时需求数量</th>
				<th>4小时需求数量</th>
				<th>6小时需求数量</th>
				<th>8小时需求数量</th>
				<th>10小时需求数量</th>
				<th>12小时需求数量</th>
				<th>14小时需求数量</th>
				<th>16小时需求数量</th>
				<th>18小时需求数量</th>
				<th>20小时需求数量</th>
				<th>22小时需求数量</th>
				<th>24小时需求数量</th>--%>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowView.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>
<table border="0" width="100%">
	<tr>
		<td width="90%" align="right">
			<input type="button" value="返回" onclick="goTo();"/>
		</td>
		<td width="10%">
		</td>
	</tr>
</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

