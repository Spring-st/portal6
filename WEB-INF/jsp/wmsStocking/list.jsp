<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>

<script type="text/javascript">
<!--
	function add() {
		 window.location.href = "newWmsStocking.do";
	}

	function edit(id) {
		window.location.href="editWmsStocking.do?id=" + id;
	}
	function editByPart(id) {
		window.location.href="editWmsStockingByPart.do?id=" + id;
	}
	function editAll(id) {
		window.location.href="editWmsStockingAll.do?id=" + id;
	}
	function view(id) {
		window.location.href="viewWmsStocking.do?id=" + id; 
	}
	function deleteAll(id) {
		var sign=confirm("是否确认删除该盘点计划及所有的数据?");
		if(sign){
			window.location.href="deletemsStocking.do?id=" + id;			
		}
	}
	 function exportEXCEL(){
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
  		    	alert('请选择打印批次!');
  		    	return false;
  		    } 
  		
	  var url = "exportWmsStockingEXCELAll.do?ids="+str.toString();
   	  window.location.href = url;
   }
//-->
</script>
<html:form action="/listWmsStocking.do">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="sk.code" desc="库房盘存编号"/>
	<input type="hidden" name="fields" value="sk.operation.name" desc="创建人"/>
	<input type="hidden" name="fields" value="sk.start_date" desc="盘点开始时间"/>
	<input type="hidden" name="fields" value="sk.end_date" desc="盘点结束时间"/>
	<input type="hidden" name="fields" value="sk.plan_sumQty" desc="计划盘点总数"/>
	<input type="hidden" name="fields" value="sk.actual_sumQty" desc="实际盘点总数"/>
	<input type="hidden" name="fields" value="sk.differences_sumQty" desc="盘点差异总数"/>
	<input type="hidden" name="fields" value="sk.name" desc="备注"/>
	
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
		<table width="90%">
		<tr>
			<td>
				<html:errors />
			</td>
		</tr>
		<tr>
			<td>
				<div class="message">
					<html:messages id="x_message" message="true">
						${x_message}<br>
					</html:messages>
				</div>
			</td>
		</tr>
	</table>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="listWmsStocking.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" id="checkAll" onclick="selectAll();" /></th>
				<th><page:order order="id" style="text-decoration:none">
					<bean:message key="wmsStocking.id" />
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				   </page:order></th>
					<th>创建人</th>
					<th>盘点开始时间</th>
					<th>盘点结束时间</th>
					<th>计划盘点总数</th>
					<th>实际盘点总数</th>
					<th>盘点差异总数</th>
					<th>状态</th>
					<th>盘点类型</th>
					<th>备注</th>
					<th>操作</th>
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

