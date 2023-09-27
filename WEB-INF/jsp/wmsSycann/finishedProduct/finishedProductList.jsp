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
function addProductOutStorage(){
	var title = "product.outstorage.new";
	var url = "productOutObject.do";
	var v = dialogAction(url, title, 600, 400);
	if (v) {
		window.location.href = "productOutStorageList.do";
	};
}
	function getCheckedNum(check){
	var count = 0;
	var proEles = document.getElementsByName('ids');
	for(var i = 0;i<proEles.length;i++){
		if(proEles.item(i).checked){
			count++;
			if(count>1){
				alert("请选择一条数据!")
				check.checked=false;
				return ;
			}
		}
	}
	//return count;
}
function confirmOutStorage(){
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
  			  alert("请选择赛赫条码进行出库");
  			  return;
  		  }
  		  if(count>1){
			alert("请选择一条数据!")
			return ;
		}
  		  var flg=confirm("确认出库操作?");
  		  if(flg){
				 $.ajax({         
           			type:"POST", //请求方式        
           			url:"confirmProductOutStorageAjax.do", //请求路径        
          			cache: false,           
          			data:"ids="+str,  //传参        
           			dataType: 'json',   //返回值类型        
           			success:function(date){ 
        	   		if(date.result == "1"){
        		  		alert(date.hnccode+" 数量不足! 目前可发货数量: "+date.count); 
        		  		return false;
        	   		}else{
                	 window.location.href="confirmProductOutStorage.do?ids="+str;
             	 	}
        	   	}
			})     
  		 }
	}
</script>
<html:form action="/productOutStorageList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择"/>
	<input type="hidden" name="fields" value="pos.saiheCode" desc="赛赫条码"/>
	<input type="hidden" name="fields" value="pos.hncCode" desc="海纳川条码"/>
	<input type="hidden" name="fields" value="pos.date" desc="出库日期"/>
	
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType"/>
    <table width=100% border=0 cellpadding=4 cellspacing=0>
	  	<tr>
	  		<td colspan="4"><jsp:include page="../../simQuery.jsp"/></td>
	  	</tr>
	</table>
</html:form>
<page:form action="/productOutStorageList.do" method="post">
	<table class="data">
		<thead>
		<tr bgcolor="#9999ff" align="center">
		    <th width="2%"><%--<input type="checkbox" value="0" id="checkAll"/>--%></th>
			<th>赛赫条码</th>
			<th>海纳川条码</th>	
			<th>描述</th>
			<th>数量</th>
			<th>出库日期</th>
			<th>要货日期</th>
			<th>是否发货</th>
		</tr>
	</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="finishedProductRow.jsp" />
			</logic:iterate>
		</tbody>
		</table>
</page:form>

<script type="text/javascript">
    applyRowStyle(document.all('datatable'));
</script>
