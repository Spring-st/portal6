<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
var tr=0;
   function selectVal(val){
	   document.getElementById("rqcId").value = val;
	   if(val==1){
		   document.getElementById("div1").style.display = 'inline';
		   document.getElementById("notQualified").style.display = 'inline';
	   }else{
		   document.getElementById("div1").style.display = 'none';
		   document.getElementById("notQualified").style.display = 'none';
	   }
   }
 	function selectLocation(val) {
		 document.getElementById("locationCode").value=val;
		 if(val == ""){
			 document.getElementById("locationMaxQty").innerHTML = 0;
			 document.getElementById("createLocationQty").innerHTML = 0;
		 }else{
			$.ajax({
			type : "POST", //请求方式        
			url : "lookForLocationQtyByAjax.do", //请求路径        
			cache : false,
			data : "str=" + val,      
			dataType : 'json', //返回值类型        
			success : function(json) {
				var maxQty = json.maxQty;
				var sumQty = json.sumQty;
				document.getElementById("locationMaxQty").innerHTML = maxQty;
				document.getElementById("createLocationQty").innerHTML = sumQty;
           }
	  });
	}
}
 	function selectCode(val) {
		 document.getElementById("locationCode").value=val;
		 document.boxQueryForm.submit();
		window.parent.returnValue = true;
		window.parent.close();

	}
   function add(){ 
	 var trString = "<tr class='aboveRow'>";
	 trString += "<td style=\"display: none;\"><input type=\"checkbox\"  checked=\"checked\"/></td>";
	 trString += "<td align='center'><input type='text' size='40'  readonly='readonly' id='reasons_"+tr+"' name='reasons'/><a href='javascript:listBadReasonsSelect("+tr+");' ><img src='images/select.gif' border='0'/></a></td>"
	 trString += "<td align='center' style=\"display: none;\"><input type=\"text\" id='reasonsSapn_"+tr+"'/></td>";
	 trString += "<td align='center' style=\"display: none;\"><input type=\"text\" id='number_"+tr+"'/></td>";
	 trString += "<td><a href=\"javascript:void(0)\" onclick='detailDeleteRow(this)'>删除</a></td>"
	 trString += "</tr>";
	 $("#datatable1").append(trString).show();
	 var ttype="${x_type}";
	 if(ttype=='3'){
		 applyRowStyle(document.all('datatable1'));
	 }
	 tr++;
  }
   function listBadReasonsSelect(val) {
		v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&listBadReasonsSelect.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("reasonsSapn_"+val).value = v['id'];
			document.getElementById("reasons_"+val).value = v['describe'];
		 }
	}
    function detailDeleteRow(obj){
		var oldRow=obj.parentNode.parentNode;
		var ttbody=document.all('datatable1');
		ttbody.deleteRow(oldRow.sectionRowIndex);
		tr--;
	}
    
    function validateBoxQueryForm(form){
    	var ttype="${x_type}";
    	if(ttype=='3'){
    	var judgeFruitTxt = document.getElementById("judgeFruit").value;
    	if(judgeFruitTxt==1){
    	var str = "";
    	var qty = 0;
    	for(var i = 0; i < tr; i++){   
  			var part = document.getElementById('reasonsSapn_'+i).value;//不合格原因
  			var number = document.getElementById('number_'+i).value; //不合格数量
  			 
  			str = str + part + "," + number + ";";
  			
  			qty = parseInt(qty) + parseInt(number);
  		}
  		 
    	var v=document.getElementById("rqcId").value;
    	var v1=document.getElementById("rqcsize").value;
    	//if(v==1){
    	//	if(v1>1){
    	//		alert("当判定不合格时必须选择一条进行判断");
    	//		return false;
    	//	}
    	//}
    	
    	var s=str.split(",");
    	var b=s[0];
    	if(b==""){
    		alert("请填写不合格原因！");
    		return false;
    	}
    	
    	if(str == ""){
    		alert("请填写不合格原因！");
    		return false;
    	}
    	if(form.type.value == ""){
    		alert("请选择出库类型！");
    		return false;
    	}
    	 
    	if(qty > ${x_sum}){
    		alert("不合格数量不能大于条码数量！");
    		return false;
    	};
    	 
    	
    	
    	$('#strList').attr("value", str);
    	}
    	}
    	return true;
    }
</script>
<html:form action="/updatePurchaseOrderRqcDetermine.do" onsubmit="return validateBoxQueryForm(this)">
    <input type="hidden" value="${x_arrays}" name="arrays" />
    <input type="hidden" name="rqcType" id="rqcId"/>
    <input type="hidden" name="location" id="locationCode"/>
    <input type="hidden" name="type" value="${x_type}"/>
    <input type="hidden" id="strList" name="strList" />
    <input type="hidden" id="rqcsize" value="${x_objsize}" />
    <c:if test="${x_type=='4'}">
    <div style="font-size: 20px; font-weight: bold; text-align: center; color: blue; margin: 15px; 0px;">请选择入库库位</div>
    </c:if>
    <c:if test="${x_type=='5'}">
    <div style="font-size: 20px; font-weight: bold; text-align: center; color: blue; margin: 15px; 0px;">帐外不良品入库</div>
    </c:if>
    <c:if test="${x_type=='3'}">
    <div style="font-size: 18px;font-weight: bolder;text-align: center;color: blue;margin: 10px 0px;">品质质检判定</div>
    </c:if>
    <table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable2" >
			 <c:forEach var="x_object" items="${x_boxList}">
			        <tr>
                       <td>${x_object.lot.id}</td>
                       <td>${x_object.part.id}</td>
                       <td>${x_object.part.describe1}</td>	
                       <td>${x_object.number}</td>
                       </tr>		
			  </c:forEach>
		</tbody>
	</table>

    <!-- 采购入库start -->
    <c:if test="${x_type=='4'}">
		<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">库位:</td>
			<td>
        	 	 <select onchange="selectLocation(this.value)">
        	 	 	<option value="">请选择</option>
        	 	 	<c:forEach var="X_OBJ" items="${x_partLocations}">
        	 	 		<option value="${X_OBJ.code}">${X_OBJ.code}</option>
        	 	 	</c:forEach>
        	 	 </select>
			 </td>
		</tr>
		<tr>
			<td class="bluetext">最大库存:</td>
			<td id="locationMaxQty">&nbsp;0</td>
			<td class="bluetext">当前库存:</td>
			<td id="createLocationQty">&nbsp;0</td>
		</tr>
	</table>
    </c:if>
    <!-- 采购入库end -->
   <c:if test="${x_type=='5'}">
    <table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>库位代码</th>	
			<th>所属库房</th>
			<th>地址</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="x_blpLocations">
				 <tr>
				 	<td>${X_OBJECT.code}</td>
				 	<td>${X_OBJECT.basic_storeroom_id.code}</td>
				 	<td>${X_OBJECT.address}</td>
				 	
				 	<td><a href='javascript:selectCode("${X_OBJECT.code}")'>选择</a></td>
				 </tr>
			</logic:iterate>
		</tbody>
		</table>
    </c:if>
    <!-- 采购质检start -->
    <c:if test="${x_type=='3'}">
    <table width=100% border=0 cellpadding=4 cellspacing=0>
        <tr>
	        <td class="bluetext">判定结果:</td>
	        <td>
	        	<select onchange="selectVal(this.value)" id="judgeFruit">
	        	<option value="">请选择</option>
	        	<option value="0">合格</option>
	        	<option value="1">不合格</option>
	        </select>
	        </td>
	    </tr>
	  </table>
	  <div id="div1" style="display: none">
    	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>不合格原因</th>	
				<%--<th>不合格数量</th>
				--%><th>操作</th>
			</tr>
		</thead>
		<tbody id="datatable1">
		</tbody>
	</table>
	</div>
<!-- 采购质检end -->
    </c:if>
    <!-- 采购收货start -->
    <c:if test="${x_type=='2'}">
    <table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th>库位代码</th>	
			<th>所属库房</th>
			<th>地址</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="x_list">
				 <tr>
				 	<td>${X_OBJECT.code}</td>
				 	<td>${X_OBJECT.basic_storeroom_id.code}</td>
				 	<td>${X_OBJECT.address}</td>
				 	<td><a href='javascript:selectCode("${X_OBJECT.code}")'>选择</a></td>
				 </tr>
			</logic:iterate>
		</tbody>
		</table>
    </c:if>
    <!-- 采购收货end -->
     <hr />
	<div style="margin: 10px 10px;" align="center">
	 	<c:if test="${(x_type != '2') && (x_type != '5')}">
			<html:submit><bean:message key="all.save" /></html:submit>
		</c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
		<div id="notQualified" style="display: none;">
		<c:if test="${x_type=='3'}">
			<input type="button" value="新增不合格原因" onclick="add()"/>		
		</c:if>
		</div>
	</div>
</html:form>	
<script type="text/javascript">
var ttype="${x_type}";
	 if(ttype=='2'){
    applyRowStyle(document.all('datatable'));
    }
</script>
