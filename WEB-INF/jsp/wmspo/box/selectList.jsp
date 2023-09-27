<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
   function selectBox(){
		  var el = document.getElementsByName('ids');
		  var len = el.length;  
		  var result = []; 
		  var lotId = []; 
  		  var str="";
  		  var count=0;
  		  for(var i=0; i<len; i++){   
  			 if((el[i].type=="checkbox") && (el[i].checked== true)){
				 if(el[i].id!="checkAll"){
				    str=str+el[i].value+",";
				    lotId[i]=el[i].value;
					count++;
				}
  			}   
  		} 
  		  if(count==0){
  			  alert("出库批次不能为空！");
  			  return false;
  		  }
  		result['idList'] = str;
  		result['lotIdList'] = lotId;
  		window.parent.returnValue = result;
		window.parent.close();
	}
</script>
<html:form action="/selectBox.do">
<input id="toGoOutId" name="toGoOutId" value="${toGoOutId}" type="hidden"/>
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.location.code" desc="库位代码"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料编号"/>
	<input type="hidden" name="fields" value="pb.number" desc="数量"/>
	<input type="hidden" name="fields" value="pb.in_date" desc="入库时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/selectBox.do" method="post">
<input id="toGoOutId" name="toGoOutId" value="${toGoOutId}" type="hidden"/>
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			    <th width="3%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th >条码编号</th>
				<th >库位代码</th>
				<th >物料编号</th>
				<th >描述1</th>
				<th >描述2</th>
				<th >数量</th>
				<th >入库时间</th>
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="selectRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
