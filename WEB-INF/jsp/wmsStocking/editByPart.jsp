<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
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
	function itemview(part) {
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.edit.title&listWmsStockingItemByPart.do?part='+part+"&stocking=${x_wmsStocking.id}", 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}

    function confirmEnd(){
    	var result = confirm("确定结束本次盘库吗？");
    	if(result){
    		var form = document.wmsStockingForm;
			    form.action = "wmsStockingfinish.do";
			    form.submit();
    	}
    }
    
    function confirm2(){
    	var r=confirm('<bean:message key="Confirm receipt"/>') 		
	  	if (r==true) { 
   		var url = "wmsStockingFindDifferences.do";
   		window.location.href = url;
      	} 
    }
    
   function exportEXCEL(){
	  var url = "exportWmsStockingEXCELAll.do?id=${x_wmsStocking.id}";
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
		   if(value == "1"){//盘盈
			  <c:forEach var="x_obj" items="${x_list}">
			  		var differenceTxt = "${x_obj.difference}";
			  		if(parseFloat(differenceTxt) > 0){
			  			$(".difference_${x_obj.part.id}").get(0).style.display='inline';
			  		}
			  </c:forEach>
		}else{//盘亏
			 <c:forEach var="x_obj" items="${x_list}">
			  		var differenceTxt = "${x_object.difference}";
			  		if(parseFloat(differenceTxt) < 0){
			  			$(".difference_${x_obj.part.id}").get(0).style.display='inline';;
			  		}
			 </c:forEach>
		}
  	 }else{
  		  for(var i = 0;i < datatableStr.length; i++)
			   datatableStr[i].style.display='inline';
  	 }
 }
   
  function window.onload()    {
		//var type = document.getElementById('type').value;
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
		//document.all("viewpane2").style.display="none";//隐藏按钮 
		document.all("viewpane3").style.display="none";//隐藏按钮 
		var selElements = document.getElementsByTagName('td');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'mxck') {
			selElements[i].style.display='none';
			}
		}
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		//document.all("viewpane2").style.display="";//显示 
		document.all("viewpane3").style.display="";//显示 
		var selElements = document.getElementsByTagName('td');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'mxck') {
			selElements[i].style.display='';
			}
		}
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		//document.all("viewpane2").style.display="none";//隐藏按钮 
		document.all("viewpane3").style.display="none";//隐藏按钮 
		var selElements = document.getElementsByTagName('td');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'mxck') {
			selElements[i].style.display='none';
			}
		}
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		//document.all("viewpane2").style.display="";//显示 
		document.all("viewpane3").style.display="";//显示 
		var selElements = document.getElementsByTagName('td');
		for (var i = 0; i < selElements.length; i++) {
			if (selElements[i].className == 'mxck') {
			selElements[i].style.display='';
			}
		}
	}
	function goTo(){
		window.location.href="listWmsStocking.do";
	}
	function selectScanRecord(id){
		window.location.href="selectScanRecord.do?id="+id;
	}
//-->
</script>
<style type="text/css">
ul li{display: inline;}
</style>
<html:form action="/updateWmsStocking.do" >
	<html:hidden property="id" />
	<html:hidden property="code" value="${x_wmsStocking.code}"/>
	<div align="center" style="font-size: 25px; font-weight: bolder; margin: 10px 0px;">盘点计划单</div>
	<div align="center" style="margin: 10px 0px;">
	<img width=380 src='${path}/CreateBarCode?msg=${x_wmsStocking.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=10&width=500&moduleWidth=500'>
	</div>
	<div id="viewpane3">
	<table border="0" width="100%">
	  	<tr>
			<td width="10%"><h3 style="color:blue">盘库明细</h3></td>
			<td>
				 <div>
<%--		<ul style="margin: 10px 0px; list-style: none; padding: 0px 0px; ">--%>
<%--			<li class="bluetext">物料:</li>--%>
<%--			<li style="margin: 0px 20px;">--%>
<%--				<select onchange="selectpart(this.value)">--%>
<%--					<option value="">全部</option>--%>
<%--					<c:forEach var="x_object" items="${x_InventoryCountReport}">--%>
<%--					<option value="${x_object.part.id}">${x_object.part.id}</option>--%>
<%--					</c:forEach>--%>
<%--				</select>--%>
<%--			</li>--%>
<%--			<li class="bluetext">状态:</li>--%>
<%--			<li style="margin: 0px 20px;">--%>
<%--				<select onchange="selectStatus(this.value)">--%>
<%--					<option value="">全部</option>--%>
<%--					<option value="1">已完成</option>--%>
<%--					<option value="2">未完成</option>--%>
<%--					<option value="3">盘盈</option>--%>
<%--				</select>--%>
<%--			</li>--%>
<%--		</ul>--%>
	</div>
</td>
	  	</tr>
	  </table>
	  </div>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
			<td colspan="2">
				<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th>序号</th>
						<th>物料代码</th>
						<th><bean:message key="wmsStocking.plan.count" /></th>
						<th><bean:message key="wmsStocking.count" /></th>
						<th>计划扫描个数</th>
						<th>已扫个数</th>
						<th><bean:message key="purchaseOrder.operate" /></th>
					</tr>
				</thead>
				<bean:define id="sumaplan_sum_qty" value="0"/>
				<bean:define id="sumactual_sum_qty" value="0"/>
				<bean:define id="sumplan_num" value="0"/>
				<bean:define id="sumactual_num" value="0"/>
				
				<tbody id="datatable">	
					<c:forEach var="report" items="${x_InventoryCountReport}" varStatus="status">
					<tr align="center" class="shares_${report.part.id}_${report.id}" id="shares_${report.part.id}">
						<td>${status.index+1}</td>
						<td>${report.part.id}</td>
						<td><fmt:formatNumber value="${report.plan_sum_qty}" maxFractionDigits="0" minFractionDigits="0"/> </td>
						<td><fmt:formatNumber value="${report.actual_sum_qty}" maxFractionDigits="0" minFractionDigits="0"/> </td>
						<td>${report.plan_num}</td>
						<td>${report.actual_num}</td>
						<td class="mxck" ><a href='javascript:itemview("${report.part.id}")'><bean:message key="wmsStocking.item.view" /></a></td>
					</tr>
					<bean:define id="sumaplan_sum_qty" value="${sumaplan_sum_qty+(report.plan_sum_qty)}"/>
					<bean:define id="sumactual_sum_qty" value="${sumactual_sum_qty+(report.actual_sum_qty)}"/>
					<bean:define id="sumplan_num" value="${sumplan_num+(report.plan_num)}"/>
					<bean:define id="sumactual_num" value="${sumactual_num+(report.actual_num)}"/>
					</c:forEach>
				</tbody>	
				</table>
			</td>
		</tr>
	</table>
	<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		<tr align="right">
			<td>计划数量合计:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumaplan_sum_qty}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>扫描数量合计:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumactual_sum_qty}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>计划扫描个数:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumplan_num}" maxFractionDigits="0" minFractionDigits="0"/></td>
			<td>已扫描个数:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumactual_num}" maxFractionDigits="0" minFractionDigits="0"/></td>
		</tr>
	</table>
	<%--<div id="viewpane2">
	<table border="0" width="100%">
	  	<tr>
			<td width="10%"><h3 style="color:blue">差异列表</h3></td>
			<td>
		 <div>
	</div>
</td>
</tr>
</table>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
			<td colspan="2">
				<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th>序号</th>
						<th>库位</th>
						<th>物料代码</th>
						<th>描述</th>
						<th>规格</th>
						<th>批次</th>
						<th>库存量</th>
						<th>盘点数量</th>
						<th>差异</th>
					</tr>
				</thead>
				
				<bean:define id="sumamount" value="0"/>
				<bean:define id="sumstockingAmount" value="0"/>
				<bean:define id="sumdifference" value="0"/>
				
				<tbody id="datatable1">
					<c:forEach var="x_object" items="${x_list}" varStatus="status">
					<c:if test="${x_object.wmsPart!=null}">
					<tr class="difference_${x_object.difference}">
					   <td style="display: none;">
					   <input type="checkbox" checked="checked" name="ids" value="${x_object.lotSer},${x_object.location},${x_object.amount},${x_object.stockingAmount},${x_object.difference},${x_object.wmsPart.id}" onclick="setCount();"></td>
					   <td>${status.index + 1}</td>
					   <td>${x_object.location}</td>
					   <td>${x_object.wmsPart.id}</td>
					   <td>${x_object.wmsPart.describe1}</td>
					   <td>${x_object.wmsPart.describe2}</td>
					   <td>${x_object.lotSer}</td>
					   <td>${x_object.amount}</td>
					   <td>${x_object.stockingAmount}</td>
					   <td>${x_object.difference}</td>
					</tr>
					</c:if>
					<bean:define id="sumamount" value="${sumamount+(x_object.amount)}"/>
					<bean:define id="sumstockingAmount" value="${sumstockingAmount+(x_object.stockingAmount)}"/>
					<bean:define id="sumdifference" value="${sumdifference+(x_object.difference)}"/>
					</c:forEach>
				</tbody>	
				</table>
			</td>
		</tr>
	</table>
	--%><%--<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 20px 0px;">
		<tr align="right">
			<td>库存合计:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumamount}" maxFractionDigits="4" minFractionDigits="4"/></td>
			<td>盘点合计:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumstockingAmount}" maxFractionDigits="4" minFractionDigits="4"/></td>
			<td>差异合计:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumdifference}" maxFractionDigits="4" minFractionDigits="4"/></td>
		</tr>
	</table>
	<div style="margin: 10px 10px;">
导出：<img src="images/excel.gif" border=0/>&nbsp;<a href="#" onclick="exportEXCEL();">EXCEL</a> 
</div>
	</div>--%>
	<div align="center" id="viewpanel" style="margin: 10px 0px;">
	<table border="0" width="100%">
		<tr>
			<td width="20%"><%--<input type="button" value="返回" onclick="history.go(-1);"/>--%>
				<input type="button" value="返回" onclick="goTo();"/>
			</td>
			<td width="50%" align="center"><input type="button" value="打印盘点计划" onclick="printTure()"/>
				<input type="button" value="打印预览" onclick="printView()"/>
			</td>
			<td width="20%" align="center"><input type="button" value="查看扫描记录" onclick="selectScanRecord('${x_wmsStocking.id}')"/></td>
			<td align="right"><c:if test="${x_wmsStocking.status=='0' && mfg}">
	 			<input type="button" value="确认结束" onclick="confirmEnd()"/>
				</c:if>
	 		</td>
		</tr>
	</table>
	 
<%--	 <input type="button" value="刷新" onclick="location = location"/>--%>
<%--	 <input type="button" value="调用扫描枪接口" onclick="testscanningGunInterfaceForBadStocking();"/>--%>
	</div>
</html:form>
<script type="text/javascript">
    //applyRowStyle(document.all('datatable1'));
    function testscanningGunInterfaceForBadStocking(){
    	var url = "testscanningGunInterfaceForBadStocking.do";
   		window.location.href = url;
    }
</script>