<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">

function view(id) {
	var url = "viewCreateCustomerPlanPreShipOrder.do?id=" + id;
	window.location.href = url;
}

function addCustomerPlanPreshiporder(){
	var url = "newCustomerPlanPreshiporder.do";
	window.location.href = url;
}
function deleteCustomerPlanPreshiporder(id){
	var stamp = document.getElementById("scyhd"+id); 
	stamp.disabled=true;
	var url = "deleteCustomerPlanPreShipOrder.do?id=" + id;
	window.location.href = url;
}
function verificationCustomerPlanPreshiporder(id){
	var stamp = document.getElementById("scyhd"+id); 
	stamp.disabled=true;
		$.ajax({         
		type:"POST", //请求方式        
		url:"verificationCustomerPlanPreshiporderAJAX.do", //请求路径     
		cache: false,   
       	data:"id=" + id,  //传参        
        dataType: 'json',   //返回值类型        
        success:function(date){ 
        	if(date.result == "3"){
        		alert("单号"+date.order+"存在已发货批次:"+date.count+"个 ,不能删除!"); 
        		stamp.disabled=false;
        		return false;
        	}else if(date.result == "2"){
        		alert("单号"+date.order+"存在已备货批次:"+date.count+"个 ,不能删除!"); 
        		stamp.disabled=false;
        		return false;
        	}else if(date.result=="1"){
              		window.location.href = "deleteCustomerPlanPreShipOrder.do?id=" + id;
            	}
        	}
		});
}

</script>
<html:form action="listCustomerPlanPreshiporder" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="salesPreshiporder.code" desc="预发货单号"/>
	<input type="hidden" name="fields" value="salesPreshiporder.arrivaldate" desc="发货时间"/>
	<input type="hidden" name="fields" value="salesPreshiporder.remark" desc="备注  "/>
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
<page:form action="listCustomerPlanPreshiporder.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
														
				<th width="20%">
					预发货单号
				</th>
														
				<th width="20%">
					发货时间
				</th>
				<th width="20%">
					<bean:message key="Remark"/> 
				</th>
				<th width="20%">
					<bean:message key="portalShipOrder.status"/> 
				</th>
				<th width="10%">
					是否打印
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<tr>
					<td  align="center">
							<a href='javascript:view("${X_OBJECT.id}")'>${X_OBJECT.code}</a>
					</td>
					<td  align="center">
						<c:if test="${X_OBJECT.arrivaldate!= null }">
							<bean:write name="X_OBJECT" property="arrivaldate" format="yyyy/MM/dd" />
						</c:if>
					</td>
					<td  align="center">
						${X_OBJECT.remark}
					</td>
					<td align="center">
							<c:if test="${X_OBJECT.status!=null}">
								<span style="color:${X_OBJECT.status.color}"> 
								<bean:write name="X_OBJECT" property="status.${x_lang}ShortDescription" /> </span>
							</c:if>
					</td>
					<td align="center""> 
			 			<c:if test="${X_OBJECT.isPrint=='0'}">
			 				<span style="color: red;">已打印</span>
			 			</c:if>
			 			<c:if test="${X_OBJECT.isPrint=='1'}">
			 				未打印
			 			</c:if>
			 		</td>
			 		<td align="center""> 
			 				<input type="button"   value="删除" id="scyhd${X_OBJECT.id}" onclick="verificationCustomerPlanPreshiporder(${X_OBJECT.id});" />
			 			<%--<c:if test="${X_OBJECT.isPrint=='1'}">
			 				<input type="button"   value="删除" id="scyhd${X_OBJECT.id}" onclick="deleteCustomerPlanPreshiporder(${X_OBJECT.id});" />
			 			</c:if>
			 		--%></td>
				</tr>
			</logic:iterate>
		</tbody>
	</table>

</page:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>



