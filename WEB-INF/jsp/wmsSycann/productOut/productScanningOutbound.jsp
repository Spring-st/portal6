<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript" src="includes/basicJS.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">

function add(){
	window.location.href="newProductOut.do"
}
function  del(){
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
	   	alert('选勾选要取消出库的物料!');
	   	return false;
     }
    alert(str)
    
    window.location.href="cancleProductOut.do?str="+str;
    
}

function printOutBoundCode(){
	
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
	   	alert('选勾选要出库的物料!');
	   	return false;
     }
	
	window.location.href="printOutBoundCode.do?str="+str;
}
</script>
<html:form action="/listProductScanningOutbound.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="produce.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="produce.location.code" desc="库位"/>
	<input type="hidden" name="fields" value="produce.date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listProductScanningOutbound.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
			<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th>物料号</th>	
			<th>描述一</th>
			<th>描述二</th>
			<th>库位</th>
			<th>出库数量</th>
			<th>操作人</th>
			<th>出库时间</th>
			<th>状态</th>
			
		</tr>
	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESLUTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="outRow.jsp" />
			</logic:iterate>
		</tbody>
		</table>
		<hr>
		<table>
			<tr>
				<td><input type="button" value = "打印出库单" onclick="printOutBoundCode()"></td>
			</tr>
		
		
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
