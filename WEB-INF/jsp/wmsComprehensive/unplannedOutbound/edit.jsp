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
function printCode(num){		
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
	   window.location.href="pirntWmsUWLotList.do?id=${x_warehousing.id}"+"&wmsUwItem="+str.toString()+"&type="+num;
}

    function confirUnplannedWarehousing(){
    	$('#submitId').click();
    }
    
    //打印计划外入库单
    function printUnplannedWarehousing(){ 
    	var url="wmsPlantToGoOutPrintOrder.do?id=${x_warehousing.id}";
    	window.location.href=url;
    }
    
    function editSaveWmsUWItem(id){
    	var manualNoTxt = document.getElementById('saveBut_'+id).value;
    	$.post("editWmsUWItemByManualNo.do",
    		{"id":id,"manualNoTxt":manualNoTxt},
    		function(date){
    			if(date=="[true]"){
    				alert("修改成功!");
    				location = location;
    			}else{
    				alert("修改失败!");
    				location = location;
    			}
    		});
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
  
     function detailDeleteRow(obj){
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable1');
		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
     
     function selectBox(){
		var v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&selectBox.do?toGoOutId=${x_warehousing.id}', 
			null, 'dialogWidth:900px;dialogHeight:650px;status:no;help:no;scroll:no');
	     if (v) {
	    	 var lotId = v['lotIdList'];
	    	 var lotIdMsg="";
	    	 for(var d=0;d<lotId.length;d++){
	    		  var s=document.getElementById(lotId[d]);
    			if(s){
    				if(lotIdMsg==""){
  						lotIdMsg+=lotId[d]+" <bean:message key="lotId.CanNot.repeat" />";
  					}else{
  						lotIdMsg+="\n"+lotId[d]+" <bean:message key="lotId.CanNot.repeat" />";
  					}
    			}
	    	 }
	    	 if(lotIdMsg!=""){
	    		 lotIdMsg+="\n"+" <bean:message key="please.choose.again" />"+"!";
	    		 alert(lotIdMsg);
	    		 return false;
	    	 }
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
				var part = date[i].part;
				var supplierCode = date[i].supplierCode;
				var lotset = date[i].lot;
				var amount = date[i].amount;
				var unit = date[i].unit;
				var location = date[i].location; 
				var describe1 = date[i].describe1;
				var describe2 = date[i].describe2;
				var i ;
				var s=v[i];
				 
				var trString = "<tr class='aboveRow'>";   
				trString += "<td style=\"display: none;\"><input type=\"checkbox\" value="+(poipBoxId)+" name=\"wmsPlantgooutItem\" checked=\"checked\"/></td>";
				trString += "<td align='center'>"+"<input type=\"hidden\" id=\""+(lotset)+"\" value=\""+(lotset)+"\" />"+(lotset)+"</td>";
		        trString += "<td align='center'>"+(part)+"</td>";
		        trString += "<td align='center'>"+(supplierCode)+"</td>";
		        trString += "<td align='center'>"+(describe1)+"</td>";
		        trString += "<td align='center'>"+(describe2)+"</td>";
		        trString += "<td align='center' name='number' >"+"<input type=\"hidden\" name=\""+(part)+"count\" value=\""+(amount)+"\" />"+(amount)+"</td>";
		        trString += "<td align='center'>"+(unit)+"</td>";
		        trString += "<td align='center'>"+(location)+"</td>";
		        trString += "<td align='center' style=\"display: none;\">"+(poipBoxId)+"</td>";
		        trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
		         
		        trString += "</tr>"; 
		        $("#datatable1").append(trString).show();
		         applyRowStyle(document.all('datatable1'));
				}

                $("#haschosen").attr("value",document.getElementById("haschosen").value+str);
			   } 
            })
	     }
	}
     function detailDeleteRow(obj){
		var cell2 = obj.parentNode.parentNode.childNodes[9];
		var number = cell2.innerHTML;//amount
		//alert(number);
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
		//var ttbody=document.all('datatable2');
		var ttbody=obj.parentNode.parentNode.parentNode;
		ttbody.deleteRow(oldRow.sectionRowIndex);
	}
     function validateWmsPlanToGoOutForm(){
    	 var sing = true;
	    var el = document.getElementsByName('wmsPlantgooutItem');  
	    var number = document.getElementsByName('number'); 
	    var qty = document.getElementsByName('qty'); 
	    var wmsGoOutid = document.getElementsByName('wmsGoOut_id').value;
  		var str="";
  		var count=0;
  		var strnum=0;
  		for(var i=0; i<el.length; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				str=str+el[i].value+",";
				strnum+=number;
				count++;
        	}   
  		}
  		if(count==0){
  			alert("请选择出库批次!");
  			return false;
  		}
  		var msg="";
  		var partId = document.getElementsByName('partId');
  		for(var r=0; r<partId.length; r++){
  			var partSumQty = 0;
  			var partSum = 0
  			var partQty =document.getElementsByName(partId[r].value+"qty");
  			for(var a=0; a<partQty.length; a++){
  				partSumQty=partSumQty + parseInt(partQty[a].value);
  			}
  			var partCount= document.getElementsByName(partId[r].value+"count");
  			for(var b=0; b<partCount.length; b++){
  				partSum=partSum + parseInt(partCount[b].value);
  			}
  			if(parseInt(partSumQty)>parseInt(partSum)){//物料选择的批次数量不足
  				if(msg==""){
  					msg+=partId[r].value+" <bean:message key="part.select.number.deficiency" />";
  				}else{
  					msg+="\n"+partId[r].value+" <bean:message key="part.select.number.deficiency" />";
  				}
  			}else if(parseInt(partSumQty)<parseInt(partSum)){//物料选择的批次数量超出
  				if(msg==""){
  					msg+=partId[r].value+" <bean:message key="part.select.number.overrun" />";
  				}else{
  					msg+="\n"+partId[r].value+" <bean:message key="part.select.number.overrun" />";
  				}
  			}
  		}
  		if(msg!=""){
  			alert(msg);
  			return false;
  		}
  		//alert(strnum)
  		
  		//if(qty){
  			
  		//}
  		$("#arrayList").attr("value",str);
     }
</script>
<style>
.datast{
border: 1px solid #666;
margin: 20px 0 20px 0;
}
</style>
<html:form action="/updateWmsPlantToGoOut.do" onsubmit="return validateWmsPlanToGoOutForm()">
<jsp:include page="../../pageHead2.jsp"/>

<input type="hidden" id="haschosen" />
<html:hidden property="site_id" value="${x_warehousing.site.id}"/>
<html:hidden property="id" value="${x_warehousing.id}"/>
<input type="hidden" id="arrayList" name="arrayList"/>
<html:hidden property="wmsGoOut_id" value="${x_warehousing.id}"/>

    	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">非计划出库单号:
			</td>
			<td>${x_warehousing.code}</td>
			<td class="bluetext">非计划出库时间:
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
	<table width="50%">
		<thead>
			<tr style="background-color: orange" >
				<th>物料号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>单位</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			<c:forEach items="${x_items}" var="x_obj">
			   <tr align="center"> 
				 <td>
				 	${x_obj.part.id}
				 	<input type="hidden" name="partId" value="${x_obj.part.id}"/>
				 </td>
				 <td>${x_obj.part.describe1}</td>
				 <td>${x_obj.part.describe2}</td>
				 <td>${x_obj.part.unit}</td>
				 <td name='qty'>${x_obj.qty}
				 	<input type="hidden" name="${x_obj.part.id}qty" value="${x_obj.qty}"/>
				 </td>
			   </tr>
			</c:forEach>
		</tbody>
	    </table>
 <table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<c:if test="${x_warehousing.status != '1'}">
					<th width="2" align="center"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				</c:if>
				<th><bean:message key="wmsUnplannedWarehousing.lotSet" /></th>
				<th>物料号</th>
				<th>供应商代码</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>数量</th>
				<th>单位</th>
				<th>库位</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="datatable1" >
			     <c:forEach items="${x_subList}" var="X_OBJECT">
			   <tr align='center'> 
			     <td>
					 <input type="checkbox" name="ids" value="${x_obj.id}" onclick="productbox_click();"/>
				 </td>
				 <td>${X_OBJECT.box_id.lot.id}</td>
				 <td>${X_OBJECT.box_id.part.id}</td>
				 <td>${X_OBJECT.box_id.po_supp}</td>
				 <td>${X_OBJECT.box_id.part.describe1}</td>
				 <td>${X_OBJECT.box_id.part.describe2}</td>
				 <td>${X_OBJECT.box_id.number}</td>
				 <td>${X_OBJECT.box_id.part.unit}</td>
				 <td>${X_OBJECT.box_id.location.code}</td>
				 <td></td>
			   </tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="display: none;" border="0">
	<input type="submit" id="submitId"/></div>
     </html:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
<script type="text/javascript">
    applyRowStyle(document.all('datatable1'));
</script>