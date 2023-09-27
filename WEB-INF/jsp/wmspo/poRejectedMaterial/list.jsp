<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script type="text/javascript">
<!--
	 function uncheckAll() {  
        var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox")&&(el[i].checked==true)){
				el[i].checked= false;
  			}   
  		}
   } 
   function checkedInput(obj){
	   if(obj.checked==true)
		   checkAll();
	   else
		   uncheckAll();
	    
   }
   function checkAll() {  
		var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==false))   
  			{
				el[i].checked= true;
  			}   
  		}
    }  
   
   
   function check(){
	   var el = document.getElementsByName('ids');   
  		var len = el.length;   
  		var count=0;
  		for(var i=0; i<len; i++)   
  		{   
  			if((el[i].type=="checkbox")&&(el[i].checked==true))   
  			{
				count++;
  			}   
  		}
  		
  		if(count ==len){
  			document.getElementById("checkAll").checked=true;
  		}else{
  			document.getElementById("checkAll").checked=false;
  		}
   }
   function RecordPrint(){
		var el = document.getElementsByName('ids');
		var len = el.length;   
  		var str="";
  		var suppCode="";
  		var count=0;
  		for(var i=0; i<len; i++){   
  			if((el[i].type=="checkbox") && (el[i].checked== true)){
				if(el[i].id!="checkAll"){
					if(suppCode==""){
					suppCode=document.getElementById("s"+el[i].value).value;
				}else{
					if(suppCode!=document.getElementById("s"+el[i].value).value){
						alert("必须是同一个供应商的退货记录!");
						return false;
					}
				}
					str=str+el[i].value+",";
					count++;
				}
  			}   
  		}
  		if(count==0){
  			alert('选勾选要打印的记录！');
  			return false;
  			}
  			window.location.href = "RecordPrint.do?array="+str;
}		
//-->
</script>
<html:form action="/listProduceRejectedMaterial.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="prm.box.po_supp" desc="供应商编号"/>
	<input type="hidden" name="fields" value="prm.box.lot.id" desc="条码编号"/>
	<input type="hidden" name="fields" value="prm.box.part.id" desc="物料号"/>
	<input type="hidden" name="fields" value="prm.date" desc="退货日期"/>
	<input type="hidden" name="fields" value="prm.createUser.loginName" desc="操作用户"/>
	<input type="hidden" name="fields" value="prm.qty" desc="退货数量"/>
	<input type="hidden" name="fields" value="prm.location.code" desc="退货库位"/>
	<input type="hidden" name="fields" value="prm.box.remark" desc="备注"/>
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
	 
	<table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
	
</html:form>
<hr />
<page:form action="/listProduceRejectedMaterial.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
			<th width="2%"><input type="checkbox" value="0" id="checkAll"  onclick="checkedInput(this)"/></th>
			<th><page:order order="id" style="text-decoration:none">
					供应商编号
					<page:desc>
						<img src="images/down.gif" border="0" />
					</page:desc>
					<page:asc>
						<img src="images/up.gif" border="0" />
					</page:asc>
				    </page:order></th>
				    <th>条码编号</th>
				    <th>物料号</th>
					<th>退货日期</th>
					<th>操作用户</th>
					<th>退货数量</th>
					<th>退货库位</th>
					<th>质检状态</th>
					<th>不合格原因</th>
					<th>备注</th>
					<th>是否打印</th>
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