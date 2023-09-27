<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
		function scannOutbound(type){ 
			  var r = confirm("确认扫描出库吗？");
			  if(r){
				 var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  		 	  var str="";
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value+",";
					count++;
				}
  			}   
  		}
  			  
  			  window.location.href = "scanningProductOutbound.do?array="+str;
	}
}	      
</script>
<html:form action="/productOutbound.do" styleId="formId">
    <table width=100% border=0 cellpadding=4 cellspacing=0>
        <tr>
	        <td class="bluetext"><bean:message key="produceWorkOrderMaterial.batchNumber" />:</td>
	        <td><html:text property="lotser" /></td>
	        <td class="bluetext">物料号:</td>
	        <td><html:text property="part" /></td>
	    </tr>
	    <tr>
	        <td class="bluetext">时间:</td>
	        <td><html:text property="startPoDate" size="6"/>
	        <a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'startPoDate',null,null,'purchaseOrderRqcQueryForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			&nbsp;~&nbsp; <html:text property="endPoDate" size="6" /> 
				<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg2',false,'endPoDate',null,null,'purchaseOrderRqcQueryForm')"><img
				align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /></a>
	        </td>
	         <td class="bluetext">描述一:</td>
	        <td><html:text property="descripte1" /></td>
	    </tr>
</table>
<div style="margin: 10px 0px;">
	 <html:submit><bean:message key="all.query" /></html:submit> 
 	 <input type="button" value="扫描出库" onclick="scannOutbound()" />
</div>
</html:form>
<hr/>
<page:form action="/productOutbound.do" method="post">
<jsp:include page="../../pageHead.jsp"/>
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th width="15%">条码编号</th>	
			<th>物料号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>数量</th>
			<th>单位</th>
			<th>时间</th>
			<th>冻结状态</th>
			<th>打印状态</th>
			<th>批次状态</th>
		</tr>
	</thead>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="boxlist">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="boxRow.jsp" />
			</logic:iterate>
		</tbody>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
