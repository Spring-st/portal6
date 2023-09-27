<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">

   function listBadReasonsSelect() {
		v = window.showModalDialog(
			'showDialog.do?title=plangoout.new.title&listBadReasonsSelect.do', 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("reasonsSapn").value = v['id'];
			document.getElementById("reasons").value = v['describe'];
		 }
	}
    
    function validateBoxQueryForm(form){
    	var str = "";
    	var qty = 0;
  		var part = document.getElementById('reasonsSapn').value;//不合格原因
  		//alert(part);
  		var number = document.getElementById('number').value; //不合格数量
  		str = str + part + "," + number + ";";
  		qty = parseInt(qty) + parseInt(number);
    	var v=document.getElementById("rqcId").value;
    	var v1=document.getElementById("rqcsize").value;
    	var s=str.split(",");
    	var b=s[0];
    	if(b==""){
    		alert("请填写退货原因！");
    		return false;
    	}
    	if(str == ""){
    		alert("请填写退货原因！");
    		return false;
    	}
    	$('#strList').attr("value", str);
    	return true;
    }
</script>
<html:form action="/updateProduceRejectedMaterialBoxByReasons.do" onsubmit="return validateBoxQueryForm(this)">
    <input type="hidden" value="${x_arrays}" name="arrays" />
    <input type="hidden" value="${x_ids}" name="ids" />
    <input type="hidden" value="${x_type}" name="type" />
    <input type="hidden" name="rqcType" id="rqcId" value="1"/>
    <input type="hidden" name="location" id="locationCode"/>
    <input type="hidden" id="strList" name="strList" />
    <input type="hidden" id="rqcsize" value="${x_objsize}" />
    <div style="font-size: 18px;font-weight: bolder;text-align: center;color: blue;margin: 10px 0px;">退货及退货原因</div>
    <table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
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

  
    	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th>退货原因</th>	
				<%--<th>不合格数量</th>
				<th>操作</th>--%>
			</tr>
		</thead>
		<tbody id="datatable1">
			<tr class='aboveRow'>
				<td style="display: none;"><input type="checkbox"  checked="checked"/></td>
				<td align='center'><input type='text' size='40'  readonly='readonly' id='reasons' name='reasons'/><a href='javascript:listBadReasonsSelect();' ><img src='images/select.gif' border='0'/></a></td>
				<td align='center' style="display: none;"><input type="text" id='reasonsSapn'/></td>
				<td align='center' style="display: none;"><input type="text" id='number'/></td>
				<%--<td><a href="javascript:void(0)" onclick='detailDeleteRow(this)'>删除</a></td>
	 		--%></tr>
		</tbody>
	</table>
   
     <hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>	
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
    applyRowStyle(document.all('datatable1'));
</script>
