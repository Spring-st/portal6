<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	function addWmsTool() {
		v = window.showModalDialog(
			'showDialog.do?title=tool WorkCenter.new.title&newWmsTool.do' , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			var table = window.location.href = "listWmsTool.do";
			appendRow(table, v);
		    applyRowStyle(table);
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=tool WorkCenter.edit.title&editWmsTool.do?id=' + id , 
			null, 'dialogWidth:400px;dialogHeight:380px;status:no;help:no;scroll:no');
		if (v) {
			 window.location.href = "listWmsTool.do";
		};
	}
 	 
      function printCode(){
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
  		    	alert('<bean:message key="Please select a print batches"/>');
  		    	return false;
  		    } 
			window.location.href = "pirntCodeWmsTool.do?str="+str.toString();
	}
	function selectAll(){
		var ctlBox = document.getElementById('checkAll');
		var idArr = document.getElementsByName('ids');
		for(var i=0;i<idArr.length;i++){
			idArr.item(i).checked = ctlBox.checked;
		}
	}
//-->
</script>
<html:form action="/listWmsTool.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="ct.code" desc="器具编码"/>
	<input type="hidden" name="fields" value="ct.capacity" desc="容量"/>
	<input type="hidden" name="fields" value="ct.date" desc="日期"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listWmsTool.do" method="post">
	<html:hidden property="id" />
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th>器具编码</th>
				<th>器具容量</th>
				<th>日期</th>
				<th>状态</th>	
			</tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>

