<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<script src="includes/jquery-1.3.2.js"></script>

<style type="text/css" id="style1">
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px}
	</style>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
	function itemview(locationCode, part) {
		var site="${x_wmsStocking.site.id}"
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.edit.title&listWmsStockingItem.do?site='+site+"&locationId="+locationCode+"&stocking=${x_wmsStocking.id}"+"&part="+part, 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}
    
    function confirm2(){
    	var r=confirm('<bean:message key="Confirm receipt"/>') 		
	  	if (r==true) { 
   		var url = "wmsStockingFindDifferences.do";
   		window.location.href = url;
      	} 
    }
   function exportEXCEL(){
	  var url = "exportWmsStockingEndEXCEL.do?id=${x_wmsStocking.id}";
   	  window.location.href = url;
   }
   
    function baiscDatatableStr(datatableStr,type){
	   if(type == "1")
		   for(var i = 0;i < datatableStr.length; i++)
			 datatableStr[i].style.display='none';
	   else
		   for(var i = 0;i < datatableStr.length; i++)
			 datatableStr[i].style.display='inline';
   }
  
   function selectpart(value){
	   var datatableStr=$('#datatable tr');
	   if(value != ""){ 
		   baiscDatatableStr(datatableStr, 1);
		   
		   <c:forEach var="x_obj" items="${x_InventoryCountReport}">
			  		 var shareStr = $(".shares_"+value+"_${x_obj.id}");
		   			 for(var j = 0; j < shareStr.length; j++){
				 	 shareStr[j].style.display='inline';
		   			}
			  </c:forEach>
	   }else{
		    baiscDatatableStr(datatableStr, 2);
	   }
   }
   
   function selectStatus(value){
	   var datatableStr=$('#datatable tr');
	   if(value != ""){ 
			baiscDatatableStr(datatableStr, 1);
		   if(value == "1"){//已完成
			  <c:forEach var="x_obj" items="${x_InventoryCountReport}">
			  		var plan_sum_qtyTxt = "${x_obj.plan_sum_qty}";
			  		var actual_sum_qtyTxt = "${x_obj.actual_sum_qty}";
			  		if(parseFloat(plan_sum_qtyTxt) == parseFloat(actual_sum_qtyTxt) || parseFloat(plan_sum_qtyTxt) < parseFloat(actual_sum_qtyTxt)){
			  			$(".shares_${x_obj.part.id}_${x_obj.id}").get(0).style.display='inline';;
			  		}
			  </c:forEach>
		   }else if(value == "2"){//未完成
			   <c:forEach var="x_obj" items="${x_InventoryCountReport}">
					var plan_sum_qtyTxt = "${x_obj.plan_sum_qty}";
			  		var actual_sum_qtyTxt = "${x_obj.actual_sum_qty}";
			  		if(parseFloat(plan_sum_qtyTxt) > parseFloat(actual_sum_qtyTxt)){
			  			$(".shares_${x_obj.part.id}_${x_obj.id}").get(0).style.display='inline';;
			  		}
			  </c:forEach>
		   }else{//盘盈
			    <c:forEach var="x_obj" items="${x_InventoryCountReport}">
					var plan_sum_qtyTxt = "${x_obj.plan_sum_qty}";
			  		var actual_sum_qtyTxt = "${x_obj.actual_sum_qty}";
			  		if(parseFloat(plan_sum_qtyTxt) < parseFloat(actual_sum_qtyTxt)){
			  			$(".shares_${x_obj.part.id}_${x_obj.id}").get(0).style.display='inline';;
			  		}
			  </c:forEach>
		   }
		   
	   }else{ 
		    for(var i = 0;i < datatableStr.length; i++)
			   datatableStr[i].style.display='inline';
	   }
   }
   
   function selectDifference(value){
	   var datatableStr=$('#datatable1 tr');
	   if(value != ""){ 
			baiscDatatableStr(datatableStr, 1);
		     $.ajax({         
                type:"POST", //请求方式        
                url:"dressingWmsStockingData.do", //请求路径        
                cache: false,           
                data:"type="+value+"&id=${x_wmsStocking.id}",  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	
                for(i=0;i<date.length;i++){
				 
				var trString = "<tr class='aboveRow'>";   
		        trString += "<td align='center'>"+(date[i].lin)+"</td>";
		        trString += "<td align='center'>"+(date[i].code)+"</td>";
		        trString += "<td align='center'>"+(date[i].part)+"</td>";
		        trString += "<td align='center'>"+(date[i].describe1)+"</td>";
		        trString += "<td align='center'>"+(date[i].describe2)+"</td>";
		        trString += "<td align='center'>"+(date[i].lotser)+"</td>";
		        trString += "<td align='center' >"+(date[i].inventoryAmount)+"</td>";
		        trString += "<td align='center'>"+(date[i].stockAmount)+"</td>";
		        trString += "<td align='center'>"+(date[i].differenceAmount)+"</td>";
		        trString += "<td align='center'>"+(date[i].type)+"</td>";
		        
		        trString += "</tr>"; 
		        
		        $("#datatable1").append(trString).show();
		         applyRowStyle(document.all('datatable1'));
				}
			  } 
         })
  	 } 
 }
    function window.onload()    {
//		var type = document.getElementById('type').value;
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 1.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 1.0;        
		factory.printing.bottomMargin = 1.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
		document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		document.all("viewpane2").style.display="";//显示 
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		document.all("viewpane2").style.display="";//显示 
	}
	function confirmResult(id){
		$.ajax({         
        	type:"POST", //请求方式        
            url:"verifyWmsStockingDifference.do", //请求路径        
            cache: false,           
            data:"id="+id,  //传参        
            dataType: 'json',   //返回值类型        
            success:function(date){
            	if (date == "true") {
            		var result = confirm("确定本次盘库结果吗？");
	  				if(result){
   						var url = "updateWmsStockingConfirm.do?id="+id;
   						window.location.href = url;
      				}
	  			}else{
	  				alert("还有未选择的处理方式的盘点差异记录");
					return false;
	  			}
			} 
		})
	}
	function updateRecordsByType(type){ 
			if(type!=""){
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
  				alert('选勾选处理的批次！');
  			    return false;
  			}
 			$.ajax({         
        		type:"POST", //请求方式        
            	url:"updateRecordsByType.do", //请求路径        
            	cache: false,           
            	data:"boxId="+str+"&type="+type+"&stockingId=${x_wmsStocking.id}",  //传参        
            	dataType: 'json',   //返回值类型        
            	success:function(date){
            		window.location.href="viewDifferenceByPart.do?id=${x_wmsStocking.id}";
				} 
			})
		}
	}
	function selectAll(){
		var ctlBox = document.getElementById("checkAll");
		var idArr = document.getElementsByName('ids');
		for(var i=0;i<idArr.length;i++){
			idArr.item(i).checked = ctlBox.checked;
		}
	}
	
	function getCheckedNum(){
	var count = 0;
	var proEles = document.getElementsByName('ids');
	for(var i = 0;i<proEles.length;i++){
		if(proEles.item(i).checked){
			count++;
		}
	}
	return count;
}

function productbox_click(boxid,id){
	var proEles = document.getElementsByName('ids');
	var checkId  = document.getElementById("Id"+id);
	if(checkId.checked){
		for (var i = 0; i < proEles.length; i++) {
			if (proEles[i].className == "check"+boxid) {
				proEles[i].checked = true;
			}
		}
	}else{
		for (var i = 0; i < proEles.length; i++) {
			if (proEles[i].className == "check"+boxid) {
				proEles[i].checked = false;
			}
		}
	}
	
	var ctlbox  = document.getElementById("checkAll");
	if(proEles.length == getCheckedNum()){
		ctlbox.checked = true;
	} else {
		ctlbox.checked = false;
	}
}
	
//-->
</script>
<style type="text/css">
ul li{display: inline;}
</style>
	<div align="center" style="font-size: 25px; font-weight: bolder; margin: 10px 0px;">盘点计划单</div>
	<div align="center" style="margin: 10px 0px;">
	<img width=380 src='${path}/CreateBarCode?msg=${x_wmsStocking.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=10&width=500&moduleWidth=500'>
	</div><%--
		<table border="0" width="100%">
		<tr>
			<td width="10%"><h3 style="color:blue">差异列表</h3></td>
			<td>
				<div>
					<ul style="margin: 10px 0px; list-style: none; padding: 0px 0px; ">
						<li class="bluetext">差异状态:</li>
						<li style="margin: 0px 20px;">
							<select onchange="selectDifference(this.value)">
								<option value="">全部</option>
								<option value="1">正常</option>
								<option value="2">盘盈</option>
								<option value="3">盘亏</option>
							</select>
						</li>
					</ul>
				</div>
		</td>
	   </tr>
	</table>
	--%>
	<html:form action="/viewDifferenceByPart.do" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sr.box.location.code" desc="系统库位"/>
	<input type="hidden" name="fields" value="sr.box.part.id" desc="物料代码"/>
	<%--<input type="hidden" name="fields" value="sr.box.part.dpiNo" desc="DPI"/>
	<input type="hidden" name="fields" value="sr.box.part.oldCode" desc="原厂编号"/>--%>
	<input type="hidden" name="fields" value="sr.box.po_supp" desc="采购供应商编码"/>
	<input type="hidden" name="fields" value="sr.box.part.describe1" desc="描述一"/>
	<input type="hidden" name="fields" value="sr.box.part.describe2" desc="描述一"/>
	<input type="hidden" name="fields" value="sr.box.lot.id" desc="批次"/>
	<input type="hidden" name="fields" value="sr.stocking_qty" desc="库存量"/>
	<input type="hidden" name="fields" value="sr.inventory_qty" desc="盘点数量"/>
	<input type="hidden" name="fields" value="sr.differences" desc="差异"/>
	
	<input type="hidden" id="ExportType" name="exportType"/>
	<input name="id" type="hidden" value="${x_wmsStocking.id}"/>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
	</html:form>
	<page:form action="/viewDifferenceByPart.do">
	<input name="id" type="hidden" value="${x_wmsStocking.id}"/>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
			<td colspan="2">
				<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
						<th>序号</th>
						<th>系统库位</th>
						<th>物料代码</th>
						<%--<th>DPI</th>
						<th>原厂编号</th>--%>
						<th>采购供应商编码</th>
						<th>描述一</th>
						<th>描述二</th>
						<th>批次</th>
						<th>库存量</th>
						<th>盘点数量</th>
						<th>差异</th>
						<th>类型</th>
						<th>处理方式</th>
					</tr>
				</thead>
				
				<bean:define id="sumamount" value="0"/>
				<bean:define id="sumstockingAmount" value="0"/>
				<bean:define id="sumdifference" value="0"/>
				
				<tbody id="datatable1">
					<c:forEach var="x_obj" items="${x_records}" varStatus="satust">
					  <tr class="difference_${x_object.difference}" align="center">
					  	<td><input type="checkbox" name="ids" class="check${x_obj.box.id}" id="Id${x_obj.id}"  value="${x_obj.box.id}" onclick="productbox_click('${x_obj.box.id}','${x_obj.id}');"/></td>
					     <td>${satust.index+1}</td>
					      <td>${x_obj.box.location.code}</td>
					     <td>${x_obj.box.part.id}</td>
					      <%--<td>${x_obj.box.part.dpiNo}</td>
					     <td>${x_obj.box.part.oldCode}</td>--%>
					     <td>${x_obj.box.po_supp}</td>
					     <td>${x_obj.box.part.describe1}</td>
					     <td>${x_obj.box.part.describe2}</td>
					     <td>${x_obj.box.lot.id}</td>
					     <td><fmt:formatNumber value="${x_obj.stocking_qty}" maxFractionDigits="0" minFractionDigits="0"/></td>
					     <td><fmt:formatNumber value="${x_obj.inventory_qty}" maxFractionDigits="0" minFractionDigits="0"/></td>
					     <td><fmt:formatNumber value="${x_obj.differences}" maxFractionDigits="0" minFractionDigits="0"/></td>
					     <td>
					     	<c:if test="${x_obj.differences>0}">
                        <span class="bluetext">盘盈</span>
                         </c:if>
                         <c:if test="${x_obj.differences<0}">
                          <span style="color: red;">盘亏</span> 
                         </c:if>  
					     </td>
					     <td>
					     	<c:if test="${x_obj.type=='1'}">
                       			人工处理
                         	</c:if>
                         	<c:if test="${x_obj.type=='2'}">
                          		系统处理
                         	</c:if>  
					     </td>
					  </tr><%--		
					  <bean:define id="sumamount" value="${sumamount+(x_obj.inventory_qty)}"/>
					  <bean:define id="sumstockingAmount" value="${sumstockingAmount+(x_obj.stocking_qty)}"/>
					  <bean:define id="sumdifference" value="${sumdifference+(x_obj.differences)}"/>	
					--%></c:forEach>
				</tbody>	
				</table>
			</td>
		</tr>
	</table>
	</page:form>
	<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 20px 0px;">
		<tr align="right">
			<%--<td>库存合计:&nbsp;<fmt:formatNumber value="${sumstockingAmount}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>盘点合计:&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>差异合计:&nbsp;<fmt:formatNumber value="${sumdifference}" maxFractionDigits="0" minFractionDigits="0"/></td>
		--%>
			<td>库存合计:&nbsp;<fmt:formatNumber value="${x_wmsStocking.plan_sumQty }" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>盘点合计:&nbsp;<fmt:formatNumber value="${x_wmsStocking.actual_sumQty}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>差异合计:&nbsp;<fmt:formatNumber value="${x_wmsStocking.differences_sumQty}" maxFractionDigits="0" minFractionDigits="0"/></td>
		</tr>
	</table>
	<div id="viewpane2">
	<%--<div style="margin: 10px 10px;">
导出：<img src="images/excel.gif" border=0/>&nbsp;<a href="#" onclick="exportEXCEL();">EXCEL</a> 
</div>
--%></div>
	<div align="center" id="viewpanel" style="margin: 10px 0px;">
	<table border="0" width="100%">
		<tr>
			<td width="10%"><input type="button" value="返回" onclick="history.go(-1);"/></td><%--
			<td width="80%" align="center"><input type="button" value="打印盘点计划" onclick="printTure()"/>
				<input type="button" value="打印预览" onclick="printView()"/>
			</td>
			--%><td align="right" width="10%">
			<c:if test="${x_wmsStocking.confirmStatus!='0'}">
				<input type="button" value="确认盘点结果" onclick="confirmResult('${x_wmsStocking.id}')"/>
			</c:if>
			</td>
		</tr>
	</table>
	</div>
<script type="text/javascript">
     //applyRowStyle(document.all('datatable'));
     applyRowStyle(document.all('datatable1'));
</script>
