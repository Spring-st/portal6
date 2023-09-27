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
	function itemview(locationCode, part) {
		var site="${x_wmsStocking.site.id}"
		v = window.showModalDialog(
			'showDialog.do?title=WmsStocking.edit.title&listWmsStockingItem.do?site='+site+"&locationId="+locationCode+"&stocking=${x_wmsStocking.id}"+"&part="+part, 
			null, 'dialogWidth:700px;dialogHeight:680px;status:no;help:no;scroll:no');
	}

    function confirmEnd(){
    	var result = confirm("确定结束本次盘库吗？");
    	if(result){
    		var form = document.wmsStockingForm;
			    form.action = "updateWmsStocking.do";
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
			  		var planCountTxt = "${x_obj.planCount}";
			  		var realityCountTxt = "${x_obj.realityCount}";
			  		if(parseFloat(planCountTxt) == parseFloat(realityCountTxt) || parseFloat(planCountTxt) < parseFloat(realityCountTxt)){
			  			$(".shares_${x_obj.part.id}_${x_obj.id}").get(0).style.display='inline';;
			  		}
			  </c:forEach>
		   }else if(value == "2"){//未完成
			   <c:forEach var="x_obj" items="${x_InventoryCountReport}">
					var planCountTxt = "${x_obj.planCount}";
			  		var realityCountTxt = "${x_obj.realityCount}";
			  		if(parseFloat(planCountTxt) > parseFloat(realityCountTxt)){
			  			$(".shares_${x_obj.part.id}_${x_obj.id}").get(0).style.display='inline';;
			  		}
			  </c:forEach>
		   }else{//盘盈
			    <c:forEach var="x_obj" items="${x_InventoryCountReport}">
					var planCountTxt = "${x_obj.planCount}";
			  		var realityCountTxt = "${x_obj.realityCount}";
			  		if(parseFloat(planCountTxt) < parseFloat(realityCountTxt)){
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
		var type = document.getElementById('type').value;
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = type;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
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
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id},</c:forEach>";
		
		var url="updateProductOutStatus.do?ids="+ids;
		window.location=url;
		
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
//-->
</script>
<style type="text/css">
ul li{display: inline;}
</style>
	<div align="center" style="font-size: 25px; font-weight: bolder; margin: 10px 0px;">成品出库单</div>
	<div align="center" style="margin: 10px 0px;">
	<img width=380 src='${path}/CreateBarCode?msg=${code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=10&width=500&moduleWidth=500'>
	</div>
	
	<div id="viewpane3">
	<table border="0" width="100%">
	  	<tr>
			<td width="10%"><h3 style="color:blue">出库明细</h3></td>
			
	  	</tr>
	  </table>
	  </div>
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
			<td colspan="2">
				<table width="100%" class="data">
				<thead>
					<tr bgcolor="#9999ff">
						<th>物料号</th>
						<th>物料名称</th>
						<th>描述一</th>
						<th>描述二</th>
						<th>库位</th>
						<th>出库数量</th>
						<th>出库时间</th>
						<th>操作人</th>
					</tr>
				</thead>
				
				<tbody id="datatable">	
					<c:forEach var="X_OBJECT" items="${X_RESULTLIST}" varStatus="status">
					<tr align="center"  >
						<td>${X_OBJECT.part.id}</td>
						<td>${X_OBJECT.part.name}</td>
						<td>${X_OBJECT.part.describe1}</td>
						<td>${X_OBJECT.part.describe2} </td>
						<td>${X_OBJECT.location.code} </td>
						<td>${X_OBJECT.qty}</td>
						<td>${X_OBJECT.date}</td>
						<td>${X_OBJECT.userId.loginName}</td>
					</tr>
					</c:forEach>
				</tbody>	
				</table>
			</td>
		</tr>
	</table>
	
	<div id="viewpane2">
	</div>
	<div align="center" id="viewpanel" style="margin: 10px 0px;">
	<table border="0" width="100%">
		<tr>
			<td width="10%"><input type="button" value="返回" onclick="history.go(-1);"/></td>
			<td width="80%" align="center"><input type="button" value="打印出库单" onclick="printTure()"/>
				<input type="button" value="打印预览" onclick="printView()"/>
			</td>
			<td align="right" width="10%"></td>
		</tr>
	</table>
	</div>
<script type="text/javascript">
     applyRowStyle(document.all('datatable'));
     applyRowStyle(document.all('datatable1'));
</script>
