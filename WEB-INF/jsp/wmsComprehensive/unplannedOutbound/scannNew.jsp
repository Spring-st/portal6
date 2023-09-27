<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
 
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
	 function validateunplannedWarehousingScannForm(){
		 var codeTxt = document.getElementById("locationId");
		 if(codeTxt.value == ""){
			 alert("请选择入库库位！");
			 return false;
		 }
			  var el = document.getElementsByName('ids');
		 	  var len = el.length;   
  			  var count=0;
  			  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll") count++;
  			}   
  		}
  		        if(count==0){
  			    	alert('选勾选要扫描入库的批次！');
  			    	return false;
  			    }
  		        
		 return true;
	 }
	 function selectLocation() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.storageLocation.title&selectStorageLocation.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("locationId").value=v['id'];
			document.getElementById("locationSpan").innerHTML=v['code'];
			document.getElementById("locationCode").value=v['code'];
		};
	}
</script>
<html:form action="/unplannedWarehousingScann.do" onsubmit="return validateunplannedWarehousingScannForm();">
<input type="hidden" value="${x_warehousing.id}" name="code"/>
<div align="center"><span style="font-size: 20px;"><strong>勾选批次入库</strong></span></div>
    	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2" align="center"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>批次编号</th>
				<th>物料号</th>
				<th>描述一</th>
				<th>数量</th>
				<th>库位</th>
				<th>时间</th>
			</tr>
		</thead>

		<tbody id="datatable">
			 <c:forEach items="${x_list}" var="x_obj">
			 	<tr>
			 		<td><input type="checkbox" name="ids" value="${x_obj.box_id.lot.id}" onclick="productbox_click();"/></td>
			 		<td>${x_obj.box_id.lot.id}</td>
			 		<td>${x_obj.box_id.part.id}</td>
			 		<td>${x_obj.box_id.part.describe1}</td>
			 		<td>${x_obj.box_id.number}</td>
			 		<td>${x_obj.box_id.location.code}</td>
			 		<td>${x_obj.unplanned_warehousing_id.date}</td>
			 	</tr>
			 </c:forEach>
		</tbody>
	</table>
	<div>
		<table>
			<tr>
				<td>请选择入库库位：</td>
				<td>
  					<span id="locationSpan"></span><a href="javascript:selectLocation();"><img src="images/select.gif" border="0"/></a>
  					<input type="hidden" id="locationId" name="location"/>
  					<input type="hidden" id="locationCode" name="locationCode1" />
  				</td>
			</tr>
		</table>
	</div>
<hr />
<div align="center">
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="submit"" value="保存" /></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="submit" value="保存" /></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="取消" onclick="window.close();"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="取消" onclick="window.close();"/></c:if>&nbsp;
</div>
</html:form>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>