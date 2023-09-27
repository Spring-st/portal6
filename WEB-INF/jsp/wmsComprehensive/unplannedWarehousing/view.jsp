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

<script type="text/javascript" src="includes/basicJS.js"></script>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">	 
function printCode(){		
	var el = document.getElementsByName('ids');
	var str="";
	for(var i=0; i<el.length; i++){   
  		 if((el[i].type=="checkbox") && (el[i].checked== true)){
		 if(el[i].id!="checkAll"){
			 str=str+el[i].value+",";
				}
  			}   
  		}
       if(str==""){
    	   alert("请勾选要打印标签的批次！");
    	   return;
       }
	   window.location.href="pirntUnplannedWarehousing.do?str="+str;
}
    //打印计划外入库单
    function printUnplannedWarehousing(){ 
    	var url="unplannedWarehousingPrintOrder.do?id=${x_warehousing.id}";
    	window.location.href=url;
    }
    function producePrintCode(){
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
  		    if(count==0){
  		    	alert('请选择打印批次!');
  		    	return false;
  		    } 
			window.location.href = "produceWorkOrderBoxLotSerPrint.do?ids="+str.toString();
	}
    
    
    function printSpfCode(){
    	var str = "";
		var el = document.getElementsByName("ids");
		for(var i = 0; i < el.length; i++){
			if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str = str+el[i].value+",";
				}
  			} 
		}
		if(str == ""){
			alert("请选择答应你批次！");
			return false;
		}
 
		window.location.href="wmsSfpPrint.do?array="+str;
    }
  
</script>
<style>
.datast{
border: 1px solid #666;
margin: 20px 0 20px 0;
}
</style>
<jsp:include page="../../pageHead2.jsp"/>
<input type="hidden" value="${x_warehousing.id}" id="wmsUWId" name="wmsUWId"/>
<input type="hidden" id="array" name="array"/>
<input type="hidden" id="array_po" name="array_po"/>
    	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">入库单号:
			</td>
			<td>${x_warehousing.code}</td>
			<td class="bluetext">计划入库时间:
			</td>
			<td>${x_warehousing.date}</td>
		</tr>
		<tr>
			<td class="bluetext">状态: </td>
			<td>${x_warehousing.status.chnShortDescription}</td>
		</tr>
		<tr>
			<td class="bluetext">备注: </td>
			<td>${x_warehousing.remark}</td>
		</tr>
	</table>
	<hr />
	<table class="data" width="100%" id="table1">
		<thead>
			<tr bgcolor="#9999ff" align="center">
			    <th width="2" align="center"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>单位</th>
				<th>数量</th>
				<th>库位</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			<c:forEach items="${x_items}" var="x_obj">
			   <tr align="center"> 
			     <td>
					 <input type="checkbox" name="ids" value="${x_obj.box_id.lot.id}" onclick="productbox_click();"/>
				 </td>
				 <td>${x_obj.box_id.lot.id}</td>
				 <td>${x_obj.box_id.part.id}</td>
				 <td>${x_obj.box_id.part.describe1}</td>
				 <td>${x_obj.box_id.part.describe2}</td>
				 <td>${x_obj.box_id.part.unit}</td>
				 <td>${x_obj.box_id.number}</td>
				 <td>${x_obj.box_id.location.code}</td>
				 <td>${x_obj.box_id.enabled.chnShortDescription}</td>
			   </tr>
			</c:forEach>
		</tbody>
	    </table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
