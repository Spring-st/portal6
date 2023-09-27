<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="includes/jquery-1.3.2.js"></script>
<script> 
	function selectAll(){
		var ctlBox = document.getElementById('ctlBox');
		var idArr = document.getElementsByName('ids');
		for(var i=0;i<idArr.length;i++){
			idArr.item(i).checked = ctlBox.checked;
		}
	}
	
	function getCheckedNum(){
	var count = 0;
	var proEles = document.getElementsByName("ids");
	for(var i = 0;i<proEles.length;i++){
		if(proEles.item(i).checked){
			count++;
		}
	}
	return count;
}

   function productbox_click(value){
	var proEles = document.getElementsByName("ids");
	var ctlbox  = document.getElementById("ctlBox");
	if(proEles.length == getCheckedNum()){
		ctlbox.checked = true;
	} else {
		ctlbox.checked = false;
	}
}

	function selectBox(){
		  var el = document.getElementsByName('ids');
		  var len = el.length;  
		  var result = []; 
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
  			  alert("出库批次不能为空！");
  			  return false;
  		  }
  		result['idList'] = str;
  		window.parent.returnValue = result;
		window.parent.close();
	}
</script>
<html:form action="selectGoOutBox.do" method="post" >
    	<table  border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="please.enter.the.lotSet.number" />:
			</td>
			<td>
			    <html:text property="lotSet" />  
			</td>
			<td class="bluetext"><bean:message key="part.code" />:
			</td>
			<td>
			    <html:text property="partCode" />  
			</td>
		</tr> 
		<tr>
			<td class="bluetext"><bean:message key="supplier.code" />:
			</td>
			<td>
				<html:text property="supplierCode" />  
			</td>
			<td class="bluetext"><bean:message key="supplier.name" />:
			</td>
			<td>
			    <html:text property="supplierName" /> 
			</td>
		</tr> 
		<tr>
				<td class="bluetext"><bean:message key="material.storage.time"/>:</td>
			<td>
				<html:text property="datetime" size="5"></html:text>
					<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',false,'datetime',null,null,'poIpiBoxQueryForm')"><img
						align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" />
					</a>
				<html:text property="endtime" size="5"></html:text>
					<a onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg2',false,'endtime',null,null,'poIpiBoxQueryForm')"><img
						align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" />
					</a>
			</td>
		</tr>
	</table>
	<div>
		<html:submit> <bean:message key="all.query" /> </html:submit> 
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="确认选择"  onclick="selectBox();"/>
	</div>
</html:form>
<hr />
<page:form action="selectGoOutBox.do" method="post">
<jsp:include page="../../pageHead.jsp"/>
	<table class="data" width="100%">
		<thead>
			<tr bgcolor="#9999ff">
			    <th width="3%"><input type="checkbox" value="0" id="ctlBox" onclick="selectAll();"/></th>
				<th width="15%"><bean:message key="wmsUnplannedWarehousing.lotSet" /></th>
				<th width="15%"><bean:message key="wmsUnplannedWarehousing.blanket" /></th>
<%--				<th width="10%"><bean:message key="supplier.code" /></th>--%>
<%--				<th width="15%"><bean:message key="supplier.name" /></th>--%>
                <th width="10%"><bean:message key="part.code" /></th>
				<th width="15%"><bean:message key="supplier.standard" /></th>
				<th width="7%"><bean:message key="material.storage.time" /></th>
				<th width="5%"><bean:message key="wmsUnplannedWarehousing.amount" /></th>
				<th width="5%"><bean:message key="wmsUnplannedWarehousing.unit" /></th>
				<th width="5%"><bean:message key="wmsUnplannedWarehousing.unit" /></th>
			</tr>
		</thead>
		<tbody id="datatable" >
			 <logic:iterate id="X_OBJECT" name="X_RESULTLIST">
			        <tr align="center">
			           <td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click(this.value);"/></td>
                       <td>${X_OBJECT.lotSer.id}</td>	
                       <td>${X_OBJECT.blanketMark}</td>
<%--                       <td>${X_OBJECT.wmsUWItem.supplierCode}</td>		--%>
<%--                       <td>${X_OBJECT.wmsUWItem.supplierName}</td>--%>
                       <td>${X_OBJECT.wmsPart.id}</td>
                       <td>${X_OBJECT.wmsPart.describe2}</td>
                       <td><fmt:formatDate value="${X_OBJECT.date}" pattern="yyyy-MM-dd"/> </td>
                       <td>${X_OBJECT.count}</td>
                       <td>${X_OBJECT.wmsPart.unit}</td>	
			        </tr>
			 </logic:iterate>
		</tbody>
	</table>	
</page:form>	
	<table border="0" align="center">
			<tr align="center">
				<td colspan="3">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="确认选择"  onclick="selectBox();"/></c:if>&nbsp;
                <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="<bean:message key="all.confirm" />"  onclick="selectBox();"/></c:if>&nbsp;
				</td>
				<td><input align="right" type="button" value="关闭" onclick="window.close()"/></td>
			</tr>
	</table>
<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
