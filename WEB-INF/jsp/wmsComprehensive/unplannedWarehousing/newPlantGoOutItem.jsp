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
var flag = {
	"sign" : true
}
	function selectAll(){
		var ctlBox = document.getElementById('ctlBox');
		var idArr = document.getElementsByName('ids');
		for(var i=0;i<idArr.length;i++){
			idArr.item(i).checked = ctlBox.checked;
		}
	}
	
	function getCheckedNum(){
	var count = 0;
	var proEles = document.getElementsByName("ids");
	for(var i = 0;i<proEles.length;i++){
		if(proEles.item(i).checked){
			count++;
		}
	}
	return count;
}

function productbox_click(value){
	var proEles = document.getElementsByName("ids");
	var ctlbox  = document.getElementById("ctlBox");
	if(proEles.length == getCheckedNum()){
		ctlbox.checked = true;
	} else {
		ctlbox.checked = false;
	}
}
	function add(){
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
  			    	alert('<bean:message key="Please select a print batches"/>');
  			    	return false;
  			    }
  		        window.location.href="insertWmsPlanToGoOut.do?itemId="+str.toString();
	}
	
	function selectProducebBox(){
		var v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectProducebBox.do', 
			null, 'dialogWidth:900px;dialogHeight:650px;status:no;help:no;scroll:no');
	     if (v) {
	    	 var ids = v['idList'];
			 var str = "";
			  $.ajax({         
                type:"POST", //请求方式        
                url:"plantGoOutItemByWorkOrderBox.do", //请求路径        
                cache: false,           
                data:"ids="+ids,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	
                for(i=0;i<date.length;i++){
                var poipBoxId = date[i].boxId;
                str = str+poipBoxId+",";
				var supplierStandard = date[i].supplierStandard;
				var part = date[i].part;
				var lotset = date[i].lotset;
				var amount = date[i].amount;
				var unit = date[i].unit;
				var location = date[i].location; 
				var workOrderCode = date[i].workOrderCode;
				var workOrderId = date[i].workOrderId;
				var describe1 = date[i].describe1;
				var describe2 = date[i].describe2;
				var manualNo = date[i].manualNo;
				if(manualNo==null)
					manualNo = "";
				var i ;
				var s=v[i];
				 
				var trString = "<tr class='aboveRow'>";   
				trString += "<td style=\"display: none;\"><input type=\"checkbox\" value="+(poipBoxId)+" name=\"wmsPlantgooutItem\" checked=\"checked\"/></td>";
		        trString += "<td align='center'>"+(lotset)+"</td>";
		        trString += "<td align='center'>"+(workOrderCode)+"</td>";
		        trString += "<td align='center'>"+(workOrderId)+"</td>";
		        trString += "<td align='center'>"+(part)+"</td>";
		        trString += "<td align='center'>"+(describe1)+"</td>";
		        trString += "<td align='center'>"+(describe2)+"</td>";
		        trString += "<td align='center' >"+(amount)+"</td>";
		        trString += "<td align='center'>"+(unit)+"</td>";
		        trString += "<td align='center'>"+(manualNo)+"</td>";
		        trString += "<td align='center'>"+(location)+"</td>";
		        trString += "<td align='center' style=\"display: none;\">"+(poipBoxId)+"</td>";
		        trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow2(this)'>删除</a></td>"
		         
		        trString += "</tr>"; 
		        $("#datatable2").append(trString).show();
		         applyRowStyle(document.all('datatable2'));
				}

                $("#haschosen").attr("value",document.getElementById("haschosen").value+str);
			   } 
            })
	     }
	}

	function selectbox(){
	//var site_id=document.getElementById("site_id").value;
	var haschosenTxt = document.getElementById("haschosen").value;
	var v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectGoOutBox.do?haschosen='+haschosenTxt, 
			null, 'dialogWidth:900px;dialogHeight:650px;status:no;help:no;scroll:no');
	
	     if (v) {
			 var ids = v['idList'];
			 var str = "";
			  $.ajax({         
                type:"POST", //请求方式        
                url:"plantGoOutItemByBox.do", //请求路径        
                cache: false,           
                data:"ids="+ids,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	
                for(i=0;i<date.length;i++){
                var poipBoxId = date[i].boxId;
                str = str+poipBoxId+",";
				var supplierCode = date[i].supplierCode;
				var supplierStandard = date[i].supplierStandard;
				var supplierName = date[i].supplierName;
				var part = date[i].part;
				var lotset = date[i].lotset;
				var blanket = date[i].blanket;
				var amount = date[i].amount;
				var unit = date[i].unit;
				var location = date[i].location;
				var i ;
				var s=v[i];
				
				var trString = "<tr class='aboveRow'>";   
				trString += "<td style=\"display: none;\"><input type=\"checkbox\" value="+(poipBoxId)+" name=\"wmsPlantgooutItem\" checked=\"checked\"/></td>";
				trString += "<td align='center'>"+(lotset)+"</td>";
		        trString += "<td align='center'>"+(supplierCode)+"</td>";
		        trString += "<td align='center'>"+(supplierStandard)+"</td>";
		        trString += "<td align='center'>"+(supplierName)+"</td>";
		        trString += "<td align='center'>"+(part)+"</td>";
		        trString += "<td align='center'>"+(location)+"</td>";
                trString += "<td align='center'>"+(blanket)+"</td>";
		        trString += "<td align='center' >"+(amount)+"</td>";
		        trString += "<td align='center'>"+(unit)+"</td>";
		        
		        trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		        trString += "</tr>"; 
		        $("#datatable").append(trString).show();
		         applyRowStyle(document.all('datatable'));
				}

                $("#haschosen").attr("value",document.getElementById("haschosen").value+str);
			   } 
            }        
         )
	   };
    }
	function detailDeleteALLRow(){
		location = location;
	}
	
	function detailDeleteRow(obj){
		var cell2 = obj.parentNode.parentNode.childNodes[10];
		var number = cell2.innerHTML;//amount
		
		var strnew = "";
		var sumNum = 0;
		var str = document.getElementById('haschosen').value;
		var strs = new Array();
			strs = str.split(",");
		for (var i=0; i<strs.length; i++ ) {
			var strs2 = new Array();
			if(strs[i] != "" && strs[i] != number){
				strnew = strnew + strs[i] + "," 
			}
		}
		
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable');
		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
	
	function detailDeleteRow2(obj){
		var cell2 = obj.parentNode.parentNode.childNodes[10];
		var number = cell2.innerHTML;//amount
		
		var strnew = "";
		var sumNum = 0;
		var str = document.getElementById('haschosen').value;
		var strs = new Array();
			strs = str.split(",");
		for (var i=0; i<strs.length; i++ ) {
			var strs2 = new Array();
			if(strs[i] != "" && strs[i] != number){
				strnew = strnew + strs[i] + "," 
			}
		}
		
		document.getElementById('haschosen').value = "";
		document.getElementById('haschosen').value = strnew;
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable2');
		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
	
	function confir(type){
		var sing = true;
	    var el = document.getElementsByName('wmsPlantgooutItem');   
	    var wmsGoOutid = document.getElementsByName('wmsGoOut_id').value;
  		var str="";
  		var count=0;
  		for(var i=0; i<el.length; i++){   
  		if((el[i].type=="checkbox") && (el[i].checked== true)){
			str=str+el[i].value+",";
			count++;
      }   
  }
  		if(count==0){
  			alert("请选择出库批次!");
  			return false;
  		}
  		$.post("checkWmsPlanToGoOutItemByAmountAjax.do",
  			   {"arrayList":str,"wmsGoOutid":${x_wmsPlantId.id},"type":type},
  			   function(date){
  				   if(date=="[false]"){
  					   alert("出库数量跟计划数量不相符，请重新选择！");
  					   return false;
  				   }else{
  					   var returnValue = confirm("确认提交么！");
					if(returnValue){
  					  var form = document.wmsPlanToGoOut;
  					  form.action = "updateWmsPlantToGoOut.do";
  					  form.submit();
  					  }else{
  						  return false;
  					  }
  				   }
  			   });
  		$("#arrayList").attr("value",str);
}
		function reloadDatatable(){
		 applyRowStyle(document.all('datatable2'));
}
		function recommendLotset(){
			document.getElementById("but1").style.display = "none";
			document.getElementById("but2").style.display = "inline";
			 $.ajax({         
                type:"POST", //请求方式        
                url:"wmsPlantToGoOutRecommendLotset.do", //请求路径        
                cache: false,           
                data:"id=${x_wmsPlantId.id}",  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                
                var str = "";
                for(var i = 0; i < date.length; i++){
                	var sign = date[i].sign;
                	var part = date[i].part;
                	if(sign == false){
                		alert("该"+part+"物料号不符合出库数量，请拆分批次后再做此操作!");
                		return false;
                	}
				var lotset = date[i].lotset;
				var workOrder = date[i].workOrder;
				var workOrderId = date[i].workOrderId;
				var describe1 = date[i].describe1;
				var describe2 = date[i].describe2;
				var amount = date[i].amount;
				var unit = date[i].unit;
				var manualNo = date[i].manualNo;
				var code = date[i].code;
				
                var poipBoxId = date[i].boxId;
                str = str + poipBoxId +",";
				
				var trString = "<tr class='aboveRow'>";   
				trString += "<td style=\"display: none;\"><input type=\"checkbox\" value="+(poipBoxId)+" name=\"wmsPlantgooutItem\" checked=\"checked\"/></td>";
		        trString += "<td align='center'>"+(i)+"</td>";
		        trString += "<td align='center'>"+(lotset)+"</td>";
		        trString += "<td align='center'>"+(workOrder)+"</td>";
		        trString += "<td align='center'>"+(workOrderId)+"</td>";
		        trString += "<td align='center'>"+(part)+"</td>";
		        trString += "<td align='center'>"+(describe1)+"</td>";
		        trString += "<td align='center'>"+(describe2)+"</td>";
                trString += "<td align='center'>"+(amount)+"</td>";
		        trString += "<td align='center' >"+(unit)+"</td>";
		        trString += "<td align='center' >"+(code)+"</td>";
		        trString += "<td align='center'>"+(manualNo)+"</td>";
		        trString += "<td align='center' style=\"display: none;\">"+(poipBoxId)+"</td>";
		        
		        trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow2(this)'>删除</a></td>"
		        trString += "</tr>"; 
		        
		        $("#datatable2").append(trString).show();
		         applyRowStyle(document.all('datatable2'));
				}

                $("#haschosen").attr("value",document.getElementById("haschosen").value+str);
			  } 
            }        
         )};
         
      function manualConfir(type){
    	 var r = confirm("确认出库么!");
    	 if(r){
    		 var id = "${x_wmsPlantId.id}";
    		 $.ajax({         
                type:"POST", //请求方式        
                url:"updateWmsPlantToGoOutByManual.do", //请求路径        
                cache: false,           
                data:"id=${x_wmsPlantId.id}",  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                var validatevalue = date[0]; 
  				   if(validatevalue=="true"){
  					   alert("完成，请审批！");
  					   location = location;
  				  } else 
  					   alert(validatevalue+"物料不足，或者需要拆分批次！");
  				    
            }        
         })
    		// var url = "updateWmsPlantToGoOutByManual.do?id=${x_wmsPlantId.id}";
    		 //window.location.href = url;
    	 }
      }
      
</script>
<html:form action="/updateWmsPlantToGoOut.do" onsubmit="return validateWmsPlanToGoOutForm(this)">
<input type="hidden" id="haschosen" />
<html:hidden property="site_id" value="${x_wmsPlantId.site.id}"/>
<html:hidden property="id" value="${x_wmsPlantId.id}"/>
<input type="hidden" id="arrayList" name="arrayList"/>
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
	<script type="text/javascript">
    applyRowStyle(document.all('datatable1'));
</script>
	<div>
	  <table>
		<c:if test="${x_wmsPlantId.status=='1'}">
		<tr>
		<c:if test="${x_wmsPlantId.type == '0'}">
		<td class="bluetext" style="color: red; font-size: 15px">选择原材料出库条码:</td>
		<td><span id="supplierCode"></span>
		<a href="javascript:selectbox();"><img src="images/select.gif" border="0"/></a></td>
		</c:if> 
		<c:if test="${x_wmsPlantId.type == '1'}">
		<td>
			<input type="button" value="推荐出库条码" onclick="recommendLotset()" id="but1"/>
			<input type="button" value="推荐出库条码" disabled="disabled" id="but2" style="display: none;"/>
		</td>
		<td class="bluetext" style="color: red; font-size: 15px">选择成品出库条码:</td>
		<td><span id="supplierCode"></span>
		<a href="javascript:selectProducebBox();"><img src="images/select.gif" border="0"/></a></td>
		</c:if>
		</tr>
		</c:if>
		</table>
	</div>		
	<c:if test="${x_wmsPlantId.type == '0'}">
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="15%"><bean:message key="wmsUnplannedWarehousing.lotSet" /></th>
				<th width="12%"><bean:message key="supplier.code" /></th>
				<th width="15%"><bean:message key="supplier.standard" /></th>
				<th width="15%"><bean:message key="supplier.name" /></th>
				<th width="10%"><bean:message key="part.code" /></th>
				<th width="7%"><bean:message key="wmsPlanToGoOut.location" /></th>
				<th width="10%"><bean:message key="wmsUnplannedWarehousing.blanket" /></th>
				<th width="5%"><bean:message key="wmsUnplannedWarehousing.amount" /></th>
				<th width="5%"><bean:message key="wmsUnplannedWarehousing.unit" /></th>
				<th>库位</th>
				<th width="3%"></th>
			</tr>
		</thead>
		<tbody id="datatable" >
		    <c:if test="${x_wmsPlantId.status!='1'}">
			     <logic:iterate id="X_OBJECT" name="x_subList">
			        <tr>
                       <td>${X_OBJECT.box.wmsUWItem.supplierCode}</td>		
                       <td>${X_OBJECT.box.wmsUWItem.supplierStandard}</td>
                       <td>${X_OBJECT.box.wmsUWItem.supplierName}</td>
                       <td>${X_OBJECT.box.wmsPart.id}</td>
                       <td>${X_OBJECT.box.location.code}</td>
                       <td>${X_OBJECT.box.blanketMark}</td>
                       <td>${X_OBJECT.box.count}</td>
                       <td>${X_OBJECT.box.wmsPart.unit}</td>	
                       <td>${X_OBJECT.box.lotSer.id}</td>
                       <td></td>
                     </tr>		
			      </logic:iterate>
			 </c:if>
		</tbody>
	</table>	
	<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
	</c:if>
	<c:if test="${x_wmsPlantId.type == '1'}">
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th>序号</th>
				<th>条码</th>
				<th>工单编号</th>
				<th>工单ID</th>
				<th>物料代码</th>
				<th>描述</th>
				<th>规格</th>
				<th>数量</th>
				<th>单位</th>
				<th>库位</th>
				<th>手册号</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="datatable2" >
		    <c:if test="${x_wmsPlantId.status!='1'}">
			     <c:forEach items="${x_subList}" var="x_obj" varStatus="status">
			        <tr>
                       <td>${status.index+1}</td>
                       <td>${x_obj.orderBox.lotSer.id}</td>
                       <td>${x_obj.orderBox.workOrder.workOrder}</td>
                       <td>${x_obj.orderBox.workOrder.workOrderId}</td>
                       <td>${x_obj.orderBox.wmsPart.id}</td>
                       <td>${x_obj.orderBox.wmsPart.describe1}</td>
                       <td>${x_obj.orderBox.wmsPart.describe2}</td>
                       <td>${x_obj.orderBox.count}</td>
                       <td>${x_obj.orderBox.wmsPart.unit}</td>
                       <td>${x_obj.orderBox.manualNo}</td>
                       <td></td>
                       </tr>		
			      </c:forEach>
			 </c:if>
		</tbody>
	</table>	
	<script type="text/javascript">
    applyRowStyle(document.all('datatable2'));
</script>
	</c:if>
	<table border="0" align="center">
			<tr align="center">
				<td colspan="3"> 
                <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="确认" onclick="confir('${x_wmsPlantId.type}');"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="确认" onclick="confir('${x_wmsPlantId.type}');"/></c:if>&nbsp;
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="to.choose" />"  onclick="detailDeleteALLRow();"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="<bean:message key="to.choose" />"  onclick="detailDeleteALLRow();"/></c:if>&nbsp;
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="<bean:message key="all.return" />"  onclick="history.go(-1);"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="<bean:message key="all.return" />"  onclick="history.go(-1);"/></c:if>&nbsp;
				</td>
				<c:if test="${validateManager}"> 
				<c:if test="${x_wmsPlantId.isManual==null}">
				<td>
				<input type="button" value="确认手工出库" onclick="manualConfir('${x_wmsPlantId.type}');" style="color: red;"/></td>
				</c:if>
				</c:if>
			</tr>
	</table>
</html:form>

