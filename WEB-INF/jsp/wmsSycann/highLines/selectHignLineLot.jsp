<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
 function selectWmsPart() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPart.do?type=1', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			 document.getElementById("name").innerHTML=v['id'];
			 document.getElementById("partId_id").value=v['id'];
		}; 
	}
 		function validateBoxQueryForm(){
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
  			    	alert('选勾选要扫描的条码！');
  			    	return false;
  			    }
  		        
  		        var partId_idTxt = document.getElementById("partId_id").value;
      		 	if(partId_idTxt == ""){
      		 		alert('请选择总成物料！');
  			    	return false;
      		 	}
      		 	
      		 	 var partname_idTxt = document.getElementById("partname_id").value;
      		 	if(partname_idTxt == ""){
      		 		alert('请填写赛赫条码！');
  			    	return false;
      		 	}
  		        
      			r = confirm("确认上线扫描吗？");
       		    if(r){
       		    	document.getElementById("lotsId").value = str;
       		    	
       		    	//var str = partname_idTxt.substring(21, partname_idTxt.length);
       		    	//alter(str);
       		    	return true;
       			 }
       		   	 else
       				 return false;
 }
</script>
<html:form action="/insertPurchaseOrderHighLineOne.do" onsubmit="return validateBoxQueryForm();">
    <input type="hidden" id="arrays" name="arrays" />
    <input type="hidden" id="lotsId" name="lots" />
    
    <div style="font-size: 20px; font-weight: bold; text-align: center; color: blue; margin: 15px; 0px;">选择总成编号</div>
    <table style="margin: 20px 0px;" width="100%">
		<thead>
			<tr style="background-color: orange">
				<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>条码编号</th>
				<th>物料号</th>
				<th>描述</th>
				<th>数量</th>
			</tr>
		</thead>
		<tbody id="datatable" >
			 <c:forEach var="x_object" items="${x_boxList}">
			        <tr>
			           <td><input type="checkbox" name="ids" value="${x_object.lot.id}" onclick="productbox_click();"/> </td>
                       <td>${x_object.lot.id}</td>
                       <td>${x_object.part.id}</td>
                       <td>${x_object.part.describe1}</td>	
                       <td>${x_object.number}</td>
                    </tr>		
			  </c:forEach>
		</tbody>
	</table>
<div>
<table>
	<tr>
	    <td class="bluetext">选择总成编号:</td>
  		  <td><span id="name"></span>
 		  <input type="text" id="partId_id" name="part" style="display: none;"/><span class="required">*</span>
  		  <a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a>
  		</td>
	</tr>
	<tr>
	    <td class="bluetext">赛赫条码:</td>
  		  <td><span id="name"></span>
 		  <input type="text" id="partname_id" name="partname" size="50"/><span class="required">*</span>
  		</td>
	</tr>
</table>
</div>
    <hr />
     
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>	
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
