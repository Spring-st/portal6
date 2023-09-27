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
<script type="text/javascript">
var tr=0;
 function selectWorkOrder(){
	var v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectWorkOrder.do', 
			null, 'dialogWidth:900px;dialogHeight:650px;status:no;help:no;scroll:no');
	     if (v) {
			 document.getElementById('supplierCode').innerHTML = v['code'];
			 document.getElementById('workOrder_id').value=v['id']
	   };
    }
 function detailDeleteRow(obj){
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		tr--;
	}
 
 function add(){
	 var trString = "<tr class='aboveRow'>";
	 trString += "<td style=\"display: none;\"><input type=\"checkbox\"  checked=\"checked\"/></td>";
	 trString += "<td><input type='text' disabled='disabled' id='partId_"+tr+"' name='part'/><a href='javascript:selectWmsPart("+tr+");' ><img src='images/select.gif' border='0'/></a></td>"
	 trString += "<td align='center'><input type=\"text\" id='number_"+tr+"'/></td>";
	 trString += "<td align='center' id='amount_"+tr+"'></td>";
	 trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
	 trString += "</tr>"; 
	 $("#datatable").append(trString).show();	
	 applyRowStyle(document.all('datatable'));
	 tr++;
	 
  }
 
 function selectWmsPart(val) {
		v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectWmsPart.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			var part = v['id'];
			for(var i = 0; i < tr; i++){   
  				var partId = document.getElementById('partId_'+i).value;//出库物料
  				if(part==partId){
  					alert(part+"：同一物料，不能重复选择!");
  					return;
  				}
  			}
			document.getElementById("partId_"+val).value = part;
		 
			$.ajax({         
                type:"POST", //请求方式        
                url:"lookForStocksByPartAJAX.do", //请求路径        
                cache: false,           
                data:"part="+part,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
				 	document.getElementById("amount_"+val).innerHTML = date.amount;
				 }
			 })
		 }
	}
 
  function validateWmsPlanToGoOutForm(form){ 
    	var str = "";
    	for(var i = 0; i < tr; i++){   
  			var part = document.getElementById('partId_'+i).value;//出库物料
  			var number = document.getElementById('number_'+i).value; //出库数量
  			var amountTxt = document.getElementById('amount_'+i).innerHTML; //库存量
  			if(amountTxt=="") amountTxt = 0;
  			//验证出库数量不能大于库存量parseInt
  			if(part == ""){
  				alert("请选择要出库的物料!");
  				return false;
  			}
  			if(parseInt(number) > parseInt(amountTxt)){
				alert(part + "库存不足!");
				return false;
  			}
  			if(number == ""){
  				alert("请选择出库数量!");
  				return false;
  			}
  			if(part!=""&&number!=""){
  			  str = str+part+","+number+";";
  			  
  			}
  		}
    	$('#strList').attr("value",str);
    	if(str == ""){
    		alert("请选择计划外出库物料！");
    		return false;
    	}
    	//if(form.type.value == ""){
    	//	alert("请选择出库类型！");
    	//	return false;
    	//}

    	
    	 
    	return true;
    }
  function exportsTemplate() {
	  window.location.href="exportsTemplate.do";
   }
  function uploadfile(){
		var url="showImportWmsUW.do";
		var title="purchaseRequest.item.new.title";
		var v=dialogAction(url,title,450,150); 
		if (v) {
			for(var i = 0; i < v.length ; i++){
				var part = v[i][0];
				var amount = v[i][1];
				var it_amount = v[i][2];
				
				var trString = "<tr class='aboveRow'>";
					trString += "<td style=\"display: none;\"><input type=\"checkbox\" checked=\"checked\"/></td>";
					trString += "<td><input type='text' disabled='disabled' id='partId_"+tr+"' value='"+part+"' name='part'/><a href='javascript:selectWmsPart("+tr+");' ><img src='images/select.gif' border='0'/></a></td>"
	 				trString += "<td align='center'><input type=\"text\" id='number_"+tr+"' value='"+amount+"'/></td>";
					trString += "<td align='center' id='amount_"+tr+"'>"+it_amount+"</td>";
					trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
					trString += "</tr>"; 
	 				$("#datatable").append(trString).show();	
	 				applyRowStyle(document.all('datatable'));
					tr++;
			}
		};
	}
  function selectBadReasons() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&listUnplannedReasonsSelect.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			 
		};
			document.getElementById("reasoncode_id").innerHTML=v['code'];
			document.getElementById("reason_code").value=v['id'];
			
			document.getElementById("expenses_course").innerHTML=v['expenses_course'];
			document.getElementById("department_cost").innerHTML=v['department_cost'];
	}
</script>
<html:form action="/insertWmsPlanToGoOut.do" onsubmit="return validateWmsPlanToGoOutForm(this)" >
<input type="hidden" id="strList" name="strList" />
<div align="center"><span style="font-size: 20px;"><strong>非计划出库单</strong></span></div>
    	<table  border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">非计划入库时间:</td>
			<td>
				<html:text property="date" size="10" value="${x_newdate}" ></html:text>
					<a onclick="event.cancelBubble=true;"
						href="javascript:showCalendar('dimg1',false,'date',null,null,'wmsPlanToGoOutForm')"><img
						align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" />
					</a>
			</td>
		</tr>
		<tr>
			<td class="bluetext">原因代码:
			</td>
			<td>
				<span id="reasoncode_id"></span>
				<a href="javascript:selectBadReasons();"><img src="images/select.gif" border="0"/></a>
				<input type="hidden" id="reason_code" name="reason_code"/>
			</td>
		</tr> 
		<tr>
			<td class="bluetext">费用科目:</td>
			<td id="expenses_course"></td>
		</tr> 
		<tr>
			<td class="bluetext">费用部门:</td>
			<td id="department_cost"></td>
		</tr> 
		<tr>
			<td class="bluetext"><bean:message key="wmsPlantoGoOut.reason" />:
			</td>
			<td>
			    <html:textarea property="remark" cols="40" rows="5"/>  
			</td>
		</tr> 
</table>
<hr />
<div><input type="button" value="新增出库物料" onclick="add()" />
<%--
<input name="b" type="button" value="下载模版" onclick="exportsTemplate()"/>
<input type="button" value="导入数据" onclick="uploadfile()"/>
--%>
</div>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="20%">物料号</th>
				<th width="20%">出库数量</th>
				<th width="20%">库存量</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody id="datatable">
			 
		</tbody>
	</table>
<hr />
<table border="0">
	<tr align="center">
	<td colspan="3">
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="submit" value="保存"   /></c:if>&nbsp;
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="submit" value="保存"  /></c:if>&nbsp;
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="取消"  onclick="window.close();"/></c:if>&nbsp;
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="取消"  onclick="window.close();"/></c:if>&nbsp;
	</td>		
</tr>
</table>
</html:form>
 