<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function listView(id) {
		window.location.href="listJitUnfinishPlanViewAction.do?productionId="+id;
	}
	function decompositiontUnfinishPlan (){
		var id = document.getElementsByName('productionId')[0].value;
		$.ajax({
 		    async: false, 
      		type:"post", 
      		dataType:"json",
      		url:"decompositiontUnfinishPlanAction.do?productionId="+id,
      		success: function(data){
       			if(data=="true"){
       				//window.location=window.location;
       				window.location.href="listUnfinishPlan.do?productionId="+id;
       			}
     		}
    	});
	}
//-->
</script>
<html:form action="/listUnfinishPlan.do">
<input type="hidden" name="productionId" id="productionId" value="${productionId}"/>
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.models" desc="父件编码"/>
	<input type="hidden" name="fields" value="entity.describe" desc="描述"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fields" value="entity.unit" desc="单位"/>
	
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
<page:form action="/listUnfinishPlan.do" method="post">
<input type="hidden" name="productionId" id="productionId" value="${productionId}"/>
	<table class="data" >
		<thead>
			<tr bgcolor="#9999ff">
				<th  ><page:order order="id" style="text-decoration:none">
					<bean:message key="qadoredi.qadpart" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><bean:message key="ediproduction.describe" /></th>
				<th><bean:message key="ediproduction.qty" /></th>
				<th><bean:message key="ediProduction.date" /></th>
				<th><bean:message key="ediproduction.time" /></th>
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
				<th>24小时需求数量</th>
				--%>
				<th><bean:message key="all.operate" /></th>
				<th><bean:message key="ediproduction.status" /></th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
	<table border="0"  width="98%" >
		<tr>
			<td align="right">
				<input  type="button" value="返回" onclick="window.location.href='listUnfinishPlanGroup.do'">
			</td>
		</tr>
	</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

