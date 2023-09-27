<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
var tr=0;
 function add(){
	 var trString = "<tr class='aboveRow' style=\"border: 1px solid;\">";
	 trString += "<td style=\"display: none;\"><input type=\"checkbox\"  checked=\"checked\"/></td>";
	 trString += "<td align='center' width='10%'><input type=\"text\" id='number_"+tr+"'/></td>";
	 trString += "<td width='10%' align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
	 trString += "</tr>"; 
	 $("#datatable1").append(trString).show();	
	 applyRowStyle(document.all('datatable1'));
	 tr++;
  }
 
 function numberCount(value){ 
	 if (value == ''){
		 document.getElementById('textfield').value='请填写需拆分个数'
		 return false;
		 }
	 var countTxt = document.getElementById('countId').innerHTML;
	 if(value != ""){
		$("#datatable1").html("");
		var str1 = countTxt / value; 
		var str2 = countTxt % value; 
		//小于零
		if(str1 < 1){
			addTrtd(countTxt, 1);
			return;
		}
		if(str2 == 0){
			addTrtd(str1, value);	 
			return;
		}
		if( str1>=1 && str2 != 0){
			//var num = divisibleNumber / value;
			str1 = String(str1);
			var num=Number(str1.substring(0,str1.indexOf(".")));
			addTrtd(num, value-1);
			endaddTrtd(countTxt-num*(value-1));
			return;
		}
	 }
 }
 function addTrtd(countTxt, num){
	 var number = parseInt(num);
	 for(var n = 0; n < number; n++){
		 var trString = "<tr class='aboveRow' style=\"border: 1px solid;\">";
		 trString += "<td style=\"display: none;\"><input type=\"checkbox\"  checked=\"checked\"/></td>";
	 	 trString += "<td align='center' width='10%'><input type=\"text\" id='number_"+tr+"' value='"+countTxt+"'/></td>";
		 trString += "<td width='10%' align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
	 	 trString += "</tr>"; 
		 $("#datatable1").append(trString).show();		
		 applyRowStyle(document.all('datatable1'));
	 	 tr++;
	 }
 }
 function endaddTrtd(countTxt){
		 var trString = "<tr class='aboveRow' style=\"border: 1px solid;\">";
		 trString += "<td style=\"display: none;\"><input type=\"checkbox\"  checked=\"checked\"/></td>";
	 	 trString += "<td align='center' width='10%'><input type=\"text\" id='number_"+tr+"' value='"+countTxt+"'/></td>";
		 trString += "<td width='10%' align='center'><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
	 	 trString += "</tr>"; 
		 $("#datatable1").append(trString).show();		
		 applyRowStyle(document.all('datatable1'));
	 	 tr++;
 }
 
 	function detailDeleteRow(obj){
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable1');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		tr--;
	}
 
  function validateWmsBatchAdjustmentForm(){ 
	  var r = ${x_type=='0'};
	  var dtb = document.getElementById("datatable1");
	  //tr = dtb.getElementsByTagName("tr").length;
	  if(r){ 
		  tr=dtb.getElementsByTagName("tr").length;
		 var conf = confirm("确定拆分成-->"+tr+"<--个批次？");
	  if(conf){
	  var str = "";
	  var count = 0;
    	for(var i=0; i<tr; i++){   
  			var number = document.getElementById('number_'+i).value; 
  			if(number!=""){
  			    str = str+number+",";
  				count++;
  			}
  		}
    	 
    	$('#strList').attr("value",str);
    	//判断拆分数量
    	if("${x_type}"=='0'){
    		var count = document.getElementById("countId").innerHTML;
    		var sumAmount = 0;
    		for(i=0;i<tr;i++){
    			var amount = document.getElementById("number_"+i).value
    			sumAmount = parseFloat(sumAmount)+parseFloat(amount);
    		}
    		if(parseFloat(sumAmount)>parseFloat(count)){
    			alert("拆分数量不能大于原数量,请重新填写!");
    			return false;
    		};
    		if(parseFloat(sumAmount)!=parseFloat(count)){
    			alert("还没拆分完毕,请把剩余的---"+(parseFloat(count)-parseFloat(sumAmount)+"---拆分完毕!"));
    			return false;
    		};
    	}
    		return true;
    	}else
    		return false;
	  }else{
		  var sign2 = confirm("确认合并该批次吗？");
		  if(sign2)
			  return true;
		  else
			 return false; 
	  }
  }
//-->
</script>
<html:form action="/insertBatchAdjustment.do" onsubmit="return validateWmsBatchAdjustmentForm()">
	<html:hidden property="id" />
	<input type="hidden" name="str" id="strList"/>
	<html:hidden property="type" value="${x_type}"/>
	
	<div style="text-align: center;"><c:if test="${x_type=='0'}"> <h3 style="color:blue">拆分物料</h3> </c:if>
	<c:if test="${x_type=='1'}"><h3 style="color:blue">合并物料</h3> </c:if></div>
	<br/>
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>数量</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			<c:forEach var="x_obj" items="${x_list}">
				<tr>
					<td style="display: none;"><input type="checkbox" checked="checked" name="lot" value="${x_obj.lot.id}"/></td>
					<td>${x_obj.lot.id}</td>
					<td>${x_obj.part.id}</td>
					<td>${x_obj.part.describe1}</td>
					<td>${x_obj.part.describe2}</td>
					<td id="countId">${x_obj.number}</td>
					<td>${x_obj.status.chnShortDescription}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
	<c:if test="${x_type=='0'}">	
		<div style="margin: 10px 0px;">
		<input type="button" value="新增拆分数量" onclick="add()"/> 
		<input name="textfield" type="text" id="count" value="请填写需拆分个数" onmouseover="this.style.borderColor='#FF6600'" 
		onmouseout="this.style.borderColor=''"  onFocus="if (value =='请填写需拆分个数'){value ='';}" onBlur="numberCount(this.value)" />
	</div> 
	<table width="40%">
		<thead>
			<tr style="background-color: orange">
				<th >拆分数量</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody id="datatable1" >
			 
		</tbody>
	</table>
	</c:if>
	<div align="center"> 
    <input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	<input type="button" value="关闭" onclick="window.close();"/>
   	<c:if test="${x_type=='0'}">
    <input name="bequery" type="button" value="重新填写" onclick="location = location;"/> 
    </c:if>
    </div>  
</html:form>

<script type="text/javascript">
	 var r = ${x_type=='1'};
	 if(r){
		 applyRowStyle(document.all('datatable'));
		 
	 }else{
		applyRowStyle(document.all('datatable1'));
	 }
</script>
