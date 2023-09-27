<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="includes/jquery-1.3.2.js"></script>
<script> 
	function confirm(){
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
  		        window.location.href="insertWmsPlanToGoOut.do?itemId="+str.toString();
	}
	
	function printPlantGoOut(value,type){
		window.location.href="printPlantGoOut.do?id="+value+"&type="+type;
	}
	    function startscann(){
    	  // var r = alert("由于数据量很大，同步时间可能要持续一段时间，建议在下班的时候在开始此操作!");
    	  
    		 window.location.href="plantToGoOutManualStartTb.do?id=${x_wmsPlantId.id}";
    	   
      }
</script>
<input type="hidden" id="haschosen"/>
<html:hidden property="wmsGoOut_id" value="${x_wmsPlantId.id}"/>
<table width=100% border=0 cellpadding=4 cellspacing=0>
		 <tr>
			<td class="bluetext">
				<bean:message key="travelApplication.site" />:
			</td>
				<td>${x_wmsPlantId.site.name}</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="wmsPlanToGoOut.code" />:
			</td>
			<td>${x_wmsPlantId.code}</td>
			<td class="bluetext"><bean:message key="wmsPlanToGoOut.deliver"/>:</td>
			<td>${x_wmsPlantId.operator.name}</td>
		</tr>
		<tr>
			<td class="bluetext" ><bean:message key="wmsPlanToGoOut.datetime"/>:</td>
			<td>${x_wmsPlantId.outdate}</td>
			<td class="bluetext" >费用部门:</td>
			<td>${x_wmsPlantId.departmentCost}</td>
		</tr>
		<c:if test="${x_wmsPlantId.status=='1'}">
		<tr>
		<td class="bluetext"><bean:message key="wmsPlantGoOutImte.select" />:</td>
		<td><span id="supplierCode"></span>
		<a href="javascript:selectbox();"><img src="images/select.gif" border="0"/></a></td>
		</tr>
		</c:if>
	    </table>
<hr />
<h3 style="color:blue">计划出库明细</h3>
<table width="50%">
		<thead>
			<tr style="background-color: orange">
				<th>物料号</th>
				<th>描述</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable1" >
			 <c:forEach var="x_object" items="${x_goOutItems}">
			        <tr>
                       <td>${x_object.wmsPart.id}</td>
                       <td>${x_object.wmsPart.describe1}</td>	
                       <td>${x_object.amount}</td>
                       </tr>		
			  </c:forEach>
		</tbody>
	</table>
	<c:if test="${x_wmsPlantId.type=='0'}">
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th><bean:message key="wmsUnplannedWarehousing.lotSet" /></th>
				<th><bean:message key="supplier.code" /></th>
				<th><bean:message key="supplier.standard" /></th>
				<th><bean:message key="supplier.name" /></th>
				<th><bean:message key="part.code" /></th>
				<th><bean:message key="wmsPlanToGoOut.location" /></th>
				<th><bean:message key="wmsUnplannedWarehousing.blanket" /></th>
				<th><bean:message key="wmsUnplannedWarehousing.amount" /></th>
				<th><bean:message key="wmsUnplannedWarehousing.unit" /></th>
				<th>库位</th>
				<th>是否出库</th>
			</tr>
		</thead>
		<tbody id="datatable" >
		    <c:if test="${x_wmsPlantId.status!='1'}">
			     <logic:iterate id="X_OBJECT" name="x_subList">
			        <tr>
                       <td>${X_OBJECT.box.lotSer.id}</td>
                       <td>
                       <c:if test="${X_OBJECT.box.wmsUWItem!=null}">${X_OBJECT.box.wmsUWItem.supplierCode}</c:if>
                       <c:if test="${X_OBJECT.box.poritem!=null}">${X_OBJECT.box.poritem.poip_item_id.poip_number.po_number.supplier.code}</c:if>
                       </td>		
                       <td>
                       <c:if test="${X_OBJECT.box.wmsUWItem!=null}">${X_OBJECT.box.wmsUWItem.supplierName}</c:if>
                       <c:if test="${X_OBJECT.box.poritem!=null}">${X_OBJECT.box.poritem.poip_item_id.poip_number.po_number.supplier.name}</c:if>
                       </td>
                       <td>${X_OBJECT.box.wmsPart.describe2}</td>
                       <td>${X_OBJECT.box.wmsPart.id}</td>
                       <td>${X_OBJECT.box.location.code}</td>
                       <td>${X_OBJECT.box.blanketMark}</td>
                       <td>${X_OBJECT.box.count}</td>
                       <td>${X_OBJECT.box.wmsPart.unit}</td>	
                       <td>${X_OBJECT.box.location.code}</td>	
                       <td>${X_OBJECT.box.isPickingOutboundFinish.chnShortDescription}</td>
                       </tr>		
			      </logic:iterate>
			 </c:if>
		</tbody>
	</table>
	</c:if>
	<c:if test="${x_wmsPlantId.type=='1'}">
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th><bean:message key="wmsUnplannedWarehousing.lotSet" /></th>
				<th>工单编号</th>
				<th>工单ID</th>
				<th>物料代码</th>
				<th>描述</th>
				<th>规格</th>
				<th>数量</th>
				<th>单位</th>
				<th>手册号</th>
				<th>是否出库</th>
			</tr>
		</thead>
		<tbody id="datatable" >
		    <c:if test="${x_wmsPlantId.status!='1'}">
			     <logic:iterate id="x_obj" name="x_subList">
			        <tr>
                       <td>${x_obj.orderBox.lotSer.id}</td>
                       <td>${x_obj.orderBox.workOrder.workOrder}</td>
                       <td>${x_obj.orderBox.workOrder.workOrderId}</td>
                       <td>${x_obj.orderBox.wmsPart.id}</td>
                       <td>${x_obj.orderBox.wmsPart.describe1}</td>
                       <td>${x_obj.orderBox.wmsPart.describe2}</td>
                       <td>${x_obj.orderBox.count}</td>
                       <td>${x_obj.orderBox.wmsPart.unit}</td>
                       <td>${x_obj.orderBox.manualNo}</td>
                       <td><c:if test="${x_obj.orderBox.isOutStorage == '0'}"><span style="color: green;">YES</span></c:if>
                       	<c:if test="${x_obj.orderBox.isOutStorage != '0'}"><span style="color: red;">NO</span></c:if>
                       </td>
                     </tr>		
			      </logic:iterate>
			 </c:if>
		</tbody>
	</table>
	</c:if>
	<table border="0" align="center">
			<tr align="center">
				<td colspan="3">
				<c:if test="${x_wmsPlantId.status=='3'}">
                <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="打印出库单" onclick="printPlantGoOut('${x_wmsPlantId.id}','1')"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="打印出库单" onclick="printPlantGoOut('${x_wmsPlantId.id}','1')"/></c:if>&nbsp;
				<input type="button" value="打印出库清单" onclick="printPlantGoOut('${x_wmsPlantId.id}','2')"/>
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="all.return" />"  onclick="window.location.href='listWmsPlanToGoOut.do'"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="<bean:message key="all.return" />"  onclick="window.location.href='listWmsPlanToGoOut.do'"/></c:if>&nbsp;
				</td>
				<c:if test="${validateManager&&x_wmsPlantId.status=='3'&&x_wmsPlantId.isManualSync==null}">
				<td><input type="button" value="开始同步" onclick="startscann();"/></td>
				</c:if>
				<c:if test="${validateManager&&x_wmsPlantId.status=='3'&&x_wmsPlantId.isManualSync!=null}">
				<td><input type="button" value="同步已完成" disabled="disabled"/></td>
				</c:if>
			</tr>
	</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
    applyRowStyle(document.all('datatable1'));
</script>
