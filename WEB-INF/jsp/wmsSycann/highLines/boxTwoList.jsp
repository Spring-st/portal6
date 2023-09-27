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
			function highLine(){ 
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
  			    	alert('选勾选要扫描的批次！');
  			    	return false;
  			    }
      		 	
      			 r = confirm("确认上线扫描吗？");
       		   		 if(r){
       		    		//验证是否符合推荐出库条码
       		    		$.post("validatePurchaseOrderHighLineTwoByAjax.do",
       		    			{"id":str},
       		    			function(date){
       		    				if(date=="[true]"){
       		    					window.location.href = 'insertPurchaseOrderHighLineTwo.do?arrays='+str;
       		    				}else{
       		    					v = window.showModalDialog(
										'showDialog.do?title=purchaseOrderRqcDetermine.title&purchaseOrderHighLineBoxTwoSelect.do?arrays='+str, 
										null, 'dialogWidth:600px;dialogHeight:400px;status:no;help:no;scroll:no');
									if (v) {
										window.location.href = 'purchaseOrderHighTwoLineBoxList.do';
									};
       		    				}
       		    		  })
       		 		}
			}
</script>
<html:form action="/purchaseOrderHighTwoLineBoxList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pb.po_number" desc="采购单号"/>
	<input type="hidden" name="fields" value="pb.po_line" desc="行号"/>
	<input type="hidden" name="fields" value="pb.single.code" desc="调料单号"/>
	<input type="hidden" name="fields" value="pb.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="pb.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="pb.part.unit" desc="单位"/>
	<input type="hidden" name="fields" value="pb.po_date" desc="时间"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>

<page:form action="/purchaseOrderHighTwoLineBoxList.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><input type="checkbox" value="0" id="checkAll" onclick="selectAll();"/></th>
			<th>条码编号</th>	
			<th>物料号</th>	
			<th>供应商编号</th>	
			<th>供应商名称</th>	
			<th>描述一</th>
			<th>库位</th>
			<th>数量</th>
			<th>单位</th>
			<th>入库时间</th>
			<th>入线边库时间</th>
			<th>质检状态</th>
			<th>冻结状态</th>
			<th>批次状态</th>
		</tr>
	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="boxlist">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="boxRow.jsp" />
			</logic:iterate>
		</tbody>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
