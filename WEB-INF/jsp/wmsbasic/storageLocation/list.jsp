<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script type="text/javascript">
<!--
	function add() {
		v = window.showModalDialog(
			'showDialog.do?title=StorageLocation.new.title&newStorageLocation.do' , 
			null, 'dialogWidth:690px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			var table =  window.location.href = "listStorageLocation.do";
			appendRow(table, v);
		    applyRowStyle(table);
		   
		};
	}

	function edit(id) {
		v = window.showModalDialog(
			'showDialog.do?title=StorageLocation.edit.title&editStorageLocation.do?id=' + id , 
			null, 'dialogWidth:650px;dialogHeight:450px;status:no;help:no;scroll:no');
		if (v) {
			updateRow(document.all('r' + id), v);
		};
		
	}
	
	 function printLocation(){  //打印页跳转 
	   var check = document.getElementsByName('ids');
	   var str = "";
	   var j=0;
	   for(i=0;i<check.length;i++){
		   if(check[i].checked == true){
			   str = str + check[i].value + ",";
			   j++;
		   }
	   }
	    if(j==0){
	    	alert('请选择要打印库位！');
	    	return false;
	    }
	    
	    window.location.href = "listStorageLocationPrint.do?array="+str;
	}; 
//-->
</script>
<html:form action="/listStorageLocation.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sl.code" desc="库位编码"/>
	<input type="hidden" name="fields" value="sl.address" desc="地址"/>
	<input type="hidden" name="fields" value="sl.max_inventory" desc="最大库存"/>
	<input type="hidden" name="fields" value="sl.basic_storeroom_id.code" desc="所属库房"/>
	<input type="hidden" name="fields" value="sl.description" desc="描述"/>
	<input type="hidden" name="fields" value="sl.freeae_status" desc="冻结状态(0-已冻结,1-未冻结)"/>
	<input type="hidden" name="fields" value="sl.enabled" desc="状态(0-可用,1-不可用)"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	

	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/listStorageLocation.do">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			 <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
				<th width="10%"><page:order order="id" style="text-decoration:none">
					库位编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				    </page:order></th>
					<th>地址</th>
					<th>最大库存</th>
					<th>所属库房 </th>
					<th>推荐状态 </th>
					<th>冻结状态 </th>
					<th>状态 </th>
					<th>先进先出状态 </th>
					<th>描述</th>
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

