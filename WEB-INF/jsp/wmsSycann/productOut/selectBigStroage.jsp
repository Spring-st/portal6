<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
<!--
function choose(id,partId){ 
	var createDate = new Date();
	var year = createDate.getYear();
	var month = createDate.getMonth();
	var date = createDate.getDate();
	var type =document.getElementById("type").value;
	var str =id+","+type;
	var dd=year+"-"+month+"-"+date;
			var qty = document.getElementById("qty_"+id).value;
			var desc1 = document.getElementById("desc1_"+id).innerHTML;
			var desc2 =document.getElementById("desc2_"+id).innerHTML;
			var location = document.getElementById("loc_"+id).innerHTML;
			var user = document.getElementById("user").value;
		    var result = [];
		    
			result['id'] = id;
			result['partId'] = partId;
			result['desc1'] = desc1;
			result['desc2'] = desc2;
			result['location'] = location;
			result['qty']=qty;
			result['user']=user;
			result["date"] =dd;
			result['type'] =type;
			result['str'] =str;
			
		    window.parent.returnValue = result;
		    window.parent.close();
	}
//-->
</script>
<c:if test="${other ==0}">
<html:form action="listProductBelowLine.do" >

	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="produce.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="produce.part.describe1" desc="描述"/>
	<input type="hidden" name="fields" value="produce.location.code" desc="库位号"/>
	<input type ="hidden" name ="type" value="0" id ="type">
	 
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr>
<div>
	<input type="hidden" id ="user" value="${user.loginName}">
</div>
<page:form action="/listProductBelowLine.do">

<table border="1" width="100%" cellpadding="2" cellspacing="0">
	<thead>
		<tr bgcolor="#9999ff">
			<c:if test="${other==1}">
				<th width="9%">器具号</th>
			</c:if>
			<th>物料号</th>
			<th>描述一</th>
			<th>描述二</th>
			<th>库位</th>
			<th>出库数量</th>
			<th>操作</th>
			
		</tr>
	</thead>
	<tbody id="item_datatable">
		<c:forEach var="X_OBJECT" items="${X_RESULTLIST}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="itemRow.jsp" />
		</c:forEach>
	
		
	</tbody>

</table>
</page:form>
</c:if>
<c:if test ="${other ==1}">
<html:form action="listProductBelowLine.do" >
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="produce.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="produce.part.describe1" desc="描述"/>
	<input type="hidden" name="fields" value="produce.location.code" desc="库位号"/>
	<input type="hidden" name="fields" value="produce.tool.code" desc="器具号"/>
	<input type ="hidden" name ="type" value="1" id="type">
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<hr>
<div>
	<input type="hidden" id ="user" value="${user.loginName}">
</div>
<page:form action="/listProductBelowLine.do">

<table border="1" width="100%" cellpadding="2" cellspacing="0">
	<thead>
		<tr bgcolor="#9999ff">
		
			<th width="9%">器具号</th>
			<th width="9%">物料号</th>
			<th width="5%">描述一</th>
			<th width="9%">描述二</th>
			<th width="8%">库位</th>
			<th width="8%">出库数量</th>
			<th width="8%">操作</th>
			
		</tr>
	</thead>
	<tbody id="item_datatable">
		<c:forEach var="X_OBJECT" items="${X_RESULTLIST}">
			<c:set var="X_OBJECT" scope="request" value="${X_OBJECT}"/>
			<jsp:include page="itemRow.jsp" />
		</c:forEach>
	
		
	</tbody>

</table>
</page:form>














</c:if>



<script type="text/javascript">
    applyRowStyle(document.all('item_datatable'));
</script>
