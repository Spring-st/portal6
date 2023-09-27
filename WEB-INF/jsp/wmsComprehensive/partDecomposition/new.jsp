<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">

	function selectSupplier(partId) {
	
	var supplier="";
	var supplierPart="";
	//var partId = document.getElementById("partId").innerHTML;
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&listSupplierByPart.do?partId='+partId+'&supplierPart='+supplierPart, 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		
		if(v){
			document.getElementById("name_"+partId).innerHTML=v['code'];
		}
	}
	
	function decomposed(){
	var lot=document.getElementById("lotId").innerHTML;//获取批次号
	var parentPart = document.getElementById("parentPart").innerHTML;//获取父物料的物料号
	var el = document.getElementsByName('ids');
		  var len = el.length;   
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value+",";//保存子物料的物料号
				    var qty=document.getElementById("qty_"+el[i].value).value;
				    var supplier = document.getElementById("name_"+el[i].value).innerHTML;
				    if(supplier ==null || supplier ==""){
				    	alert("请选择供应商！");
				    	return false;
				    }
				   var box =document.getElementById("box_"+el[i].value).value;
				    if(box ==null || box ==""){
				    	alert("请输入包装箱容量！");
				    	return false;
				    }
				    str=str+qty+","
				    str=str+supplier+",";
				    str=str+box+";";
					count++;
				}
  			}   
  		}
  		 if(str==null || str==""){
  			 alert("请选择要回收的物料！")
  			 return false;
  		 }
  		 
  		window.location.href="decomposedParts.do?str="+str+"&id="+lot;
}
	
	function uselessPart(){
		var lot=document.getElementById("lotId").innerHTML;//获取批次号

	var parentPart = document.getElementById("parentPart").innerHTML;//获取父物料的物料号
	

	var el = document.getElementsByName('ids');
		  var len = el.length;   
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value+",";//保存子物料的物料号
				    var qty=document.getElementById("qty_"+el[i].value).value;
				    var supplier = document.getElementById("name_"+el[i].value).innerHTML;
				    if(supplier ==null || supplier ==""){
				    	alert("请选择供应商！");
				    	return false;
				    }
				   var box =document.getElementById("box_"+el[i].value).value;
				    if(box ==null || box ==""){
				    	alert("请输入包装箱容量！");
				    	return false;
				    }
				    str=str+qty+","
				    str=str+supplier+",";
				    str=str+box+";";
					count++;
				}
  			}   
  		}
  		 if(str==null || str==""){
  			 alert("请选择要回收的物料！")
  			 return false;
  		 }
  		 
  		window.location.href="uselessdPart.do?str="+str+"&id="+lot+"&parentPart="+parentPart;
	}
	

//-->
</script>

<html:form action="/listPartDecomposition">
	<html:hidden property="id" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">批次号:
			</td>
			<td id="lotId">${x_box.lot.id}</td>
			<td class="bluetext">物料号:
			</td>
			<td id ="parentPart">${x_box.part.id}</td>
		</tr>
		<tr>
			<td class="bluetext">供应商: </td>
			<td>${x_box.psoItem.poipItem.poip_number.supplier.code}</td>
			<td class="bluetext">数量: </td>
			<td>${x_box.number}</td>
		</tr>
	</table>
	<hr/>
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>物料号</th>
				<th>描述一</th>
				<th>描述二</th>
				<th>数量</th>
				<th>包装箱容量</th>
				<th>已回收数量</th>
				<th>供应商</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			<c:forEach var="x_obj" items="${x_listBom}">
				<c:if test="${x_obj.child_part !=null}">
					<tr>
					<c:if test="${x_obj.unit_qty * x_box.number - map[x_obj.child_part.id] ==0}">
						<td><input type="checkbox" name="ids" value="${x_obj.child_part.id}" onclick="productbox_click();" disabled="disabled"/></td>
					</c:if>
					<c:if test="${x_obj.unit_qty * x_box.number - map[x_obj.child_part.id] !=0}">
						<td><input type="checkbox" name="ids" value="${x_obj.child_part.id}" onclick="productbox_click();" /></td>
					</c:if>
						<td id="partId">${x_obj.child_part.id}</td>
						<td>${x_obj.child_part.describe1}</td>
						<td>${x_obj.child_part.describe2}</td>
						<td><input type="text" value="${x_obj.unit_qty * x_box.number - map[x_obj.child_part.id]}" id ="qty_${x_obj.child_part.id}"></td>
						<td><input type ="text" id="box_${x_obj.child_part.id}"/></td>
						<td>${map[x_obj.child_part.id]}</td>
						<td><span id="name_${x_obj.child_part.id}"></span>
  							<a href="javascript:selectSupplier(${x_obj.child_part.id});"><img src="images/select.gif" border="0"/></a>
  						</td>
					
					</tr>
				</c:if>
			</c:forEach>	
		</tbody>
	</table>
	
	<div align="center"> 
    <input type="button" value="回收" onclick ="decomposed()"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="报废" onclick="uselessPart()"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	<input type="button" value="返回" onclick="history.go(-1)"/>
    </div>  
</html:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
    applyRowStyle(document.all('datatable1'));
</script>
