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
		window.location.href="listJitProductionPlanViewAction.do?productionId="+id;
	}

	function decomposition (){
		$.ajax({
			url:"decompositionJitProductionAction.do",
	    	    contentType : "application/x-www-form-urlencoded; charset=utf-8",
	    	    type:"POST",
	    	    dataType:"JSON",
	    	    success:function(result){
	    	    	if(result!=""){
	    	    		alert(result);
	    	    	}else{
	    	    		window.location.href="listEdiProductionAction.do";
	    	    	}
	    	    }
		});
	}
//-->
</script>
<html:form action="/listEdiProductionErrorLog.do">
	 <input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.asnNo" desc="ALC"/>
	<input type="hidden" name="fields" value="entity.productlinecode" desc="生产线代码"/>
	<%--<input type="hidden" name="fields" value="entity.models" desc="车型代码"/>--%>
	<input type="hidden" name="fields" value="entity.shiftcode" desc="班次代码"/>
	<input type="hidden" name="fields" value="entity.staffcode" desc="员工代码"/>
	<input type="hidden" name="fields" value="entity.number" desc="序号"/>
	<input type="hidden" name="fields" value="entity.taskDate" desc="任务日期"/>
	<input type="hidden" name="fields" value="entity.time" desc="时间"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fields" value="entity.syncTime" desc="同步时间"/>
	
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
<page:form action="/listEdiProductionErrorLog.do" method="post">
	<table class="data" >
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%" ><page:order order="id" style="text-decoration:none">
					<bean:message key="ediproduction.asnno" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<%--<th><bean:message key="ediproduction.models" /></th>--%>
					<th><bean:message key="ediproduction.productlinecode" /></th>
				<th><bean:message key="ediproduction.shiftcode" /></th>
				<th><bean:message key="ediproduction.staffcode" /></th>
				<th><bean:message key="ediproduction.number" /></th>
				<th><bean:message key="ediproduction.taskdate" /></th>
				<th><bean:message key="ediproduction.time" /></th>
				<th><bean:message key="ediproduction.qty" /></th>
				<th><page:order order="syncTime" style="text-decoration:none">
					<bean:message key="ediproduction.synctime" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th>错误信息</th>
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

