<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">

	function view(id) {
		var url = "viewSalesPreShipOrder.do?id=" + id;
		window.location.href = url;
	}

	function printExcel(){
		window.location.href = "listShippingOrder.do?isExcel=true";
	}
	var sign=false;
	function isMatchStatus(){ 
		var r = false;
		var el = document.getElementsByName('ids');
		var len = el.length;   
		var str="";
		var count=0;
		for(var i=0; i<len; i++){   
			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					str = str + el[i].value+",";
					count++;
				}
			}   
		}
		if(count==0){
			alert('请勾选确认匹配的的发货单！');
			return false;
		}
		if(!sign){
			sign=true;
    		r = confirm("确认匹配发货单吗？");
     		if(r){
     			 window.location.href = 'salesWorkOrderIsMatchStatus.do?arrays='+str;
			}
     	}else{
     		alert("正在匹配,请稍后...");
  			return false;
     	}
	}
</script>
<html:form action="listSalesWorkorder" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesPreshiporder.code" desc="发货单号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.createDate" desc="创建时间"/>
	<input type="hidden" name="fields" value="salesPreshiporder.customerName" desc="客户"/>
	<input type="hidden" name="fields" value="salesPreshiporder.customerCode" desc="客户编号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.arrivaldate" desc="交货日期"/>
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
<page:form action="listSalesWorkorder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>										
				<th>
					<bean:message key="SO.No"/>
				</th>
				<th>
					创建日期
				</th>
				<th>
					客户
				</th>
				<th>
					客户编码
				</th>										
				<th>
					交货日期
				</th>
				<th>
					打印状态
				</th>
				<th>
					匹配状态
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr align="center">
					<td width="3%">
							
						<c:if test="${X_OBJECT.shPrint=='0' && X_OBJECT.matchStatus !='0'}">
							<input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/>
			 			</c:if>
						<c:if test="${X_OBJECT.shPrint!='0' ||  X_OBJECT.matchStatus =='0'}">
							<input type="checkbox" disabled="disabled" />
			 			</c:if>	
					</td>
					<td>
							<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
					</td>
					<td>
						<c:if test="${X_OBJECT.createDate!= null }">
							<bean:write name="X_OBJECT" property="createDate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td>
						${X_OBJECT.customerName}
					</td>
					<td>
						${X_OBJECT.customerCode}
					</td>
					<td>
						<c:if test="${X_OBJECT.arrivaldate!= null }">
							<bean:write name="X_OBJECT" property="arrivaldate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td> 
			 			<c:if test="${X_OBJECT.shPrint=='0'}">
			 				<span style="color: red;">已打印</span>
			 			</c:if>
			 			<c:if test="${X_OBJECT.shPrint!='0'}">
			 				未打印
			 			</c:if>
			 		</td>
			 		<td> 
			 			<c:if test="${X_OBJECT.matchStatus=='0'}">
			 				<span style="color: red;">已匹配</span>
			 			</c:if>
			 			<c:if test="${X_OBJECT.matchStatus!='0'}">
			 				未匹配
			 			</c:if>
			 		</td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>
<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr align="right">
		<td>
			<input type="button" value="确认匹配" onclick="isMatchStatus();" />
		</td>
	</tr>
</table>
</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



