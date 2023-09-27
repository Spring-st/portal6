<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">

	function deleteHand(id) {
		if(confirm("<bean:message key="production.delete.confirm" />")) {
			window.location.href = "deleteEdiProduction.do?id="+id;
		}
	}
	function add(){
		v = window.showModalDialog(
			'showDialog.do?title=ediProdution.new.title&newEdiProduction.do' , 
			null, 'dialogWidth:690px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			 window.location.href = "listEdiProductionRawDataAction.do";
		};
	}
	function edit(id){
		v = window.showModalDialog(
			'showDialog.do?title=ediProdution.edit.title&editEdiProduction.do?id='+id , 
			null, 'dialogWidth:690px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			 window.location.href = "listEdiProductionRawDataAction.do";
		};
	}
	function startTimingTask(){
		window.location.href = "startTimingTaskAction.do";
	}
	function decomposition (){
		$.ajax({
			url:"decompositionJitProductionAction.do",
	    	    type:"POST",
	    	    data:"",
	    	     cache: false, 
	    	    dataType:"json",
	    	    success:function(data){
	    	    	if(data.str!=""){
	    	    		alert(data.str);
	    	    		window.location.href="listEdiProductionRawDataAction.do";
	    	    	}else{
	    	    		window.location.href="listEdiProductionRawDataAction.do";
	    	    	}
	    	    }
		});
	}
</script>
<html:form action="/listEdiProductionRawDataAction.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="entity.asnNo" desc="ALC"/>
	<%--<input type="hidden" name="fields" value="entity.models" desc="车型代码"/>--%>
	<input type="hidden" name="fields" value="entity.productlinecode" desc="生产线代码"/>
	<input type="hidden" name="fields" value="entity.shiftcode" desc="班次代码"/>
	<input type="hidden" name="fields" value="entity.staffcode" desc="员工代码"/>
	<input type="hidden" name="fields" value="entity.number" desc="序号"/>
	<input type="hidden" name="fields" value="entity.taskDate" desc="任务日期(yyyy-MM-dd)"/>
	<input type="hidden" name="fields" value="entity.time" desc="时间"/>
	<input type="hidden" name="fields" value="entity.qty" desc="数量"/>
	<input type="hidden" name="fieldsDate" value="entity.syncTime" desc="同步时间"/>
	
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
<page:form action="/listEdiProductionRawDataAction.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%"><page:order order="id" style="text-decoration:none">
					<bean:message key="ediproduction.asnno" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th><%--
				<th><bean:message key="ediproduction.models" /></th>
				--%><th><bean:message key="ediproduction.productlinecode" /></th>
				<th><bean:message key="ediproduction.shiftcode" /></th>
				<th><bean:message key="ediproduction.staffcode" /></th>
				<th><bean:message key="ediproduction.number" /></th>
				<th><bean:message key="ediproduction.taskdate" /></th>
				<th><bean:message key="ediproduction.time" /></th>
				<th><bean:message key="ediproduction.qty" /></th>
				<th width="20%"><page:order order="syncTime" style="text-decoration:none">
					<bean:message key="ediproduction.synctime" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				</page:order></th>
				<th><bean:message key="ediproduction.status" /></th>
				<%--<th>操作</th>
			--%></tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="rowRawData.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

