<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>


<script type="text/javascript">
    function AJAXCode(){
	    var type = document.getElementById("type").value;
		var reg = /^[1-9]\d*$/;
	    var sleepTime=$("#sleepTime").val();
	    if(!reg.test(sleepTime)){
	    	$("#sleepTimeCheck").html("请输入正整数");
	    	return false;
	    }else{
	  //  $("#type").attr("disabled","false");
	    	$("#sycSleepTimeForm").submit();
	    }
	   /* var id = document.getElementById("id").value;
	    if(code==null||code==""){
	    	alert("请选择同步类型");
	    }else{
	    	$.ajax({
	    		url:"AJAXEdit.do",
	    	    cache:"false",
	    	    type:"POST",
	    	    data:{"type":type,"id":id},
	    	    dataType:"JSON",
	    	    success:function(result){
	    	    	if(result!=0){
	    	    		alert("同步类型已经存在");
	    	    	}else{
	    	    		sycSleepTimeForm.submit();
	    	    	}
	    	    }
	    	});
	    }*/
    }
   /* $(function(){
    	var type=$("#typeHidden").val();
    	alert(type);
    	 $("#type").find("option[text='"+type+"']").attr("selected",true);
    })*/
    
</script>
<html:javascript formName="sycSleepTimeForm" staticJavascript="true" />
<html:form action="/updateSycSleepTimeMaintenanceAction" styleId="sycSleepTimeForm">
	<html:hidden property="id" />
	<table width=90% border=0 cellpadding=4 cellspacing=0>	
		<tr>
			<td class="bluetext"><bean:message key="workCenter.id" />:</td>
			<td><bean:write name="sycSleepTimeForm" property="id" /></td>
		</tr>		
		<tr>
			<td class="bluetext">类型:</td>
			
			
			<td><html:text property="type" styleId="type" style="width:185px;" readonly="true"/><span class="required" id="typeCheck">*</span></td>
			<!--<td>
				<html:hidden property="type" styleId="typeHidden"/>
				<select property="type" styleId="type" disabled="disabled">
					<option value="物料信息">物料信息</option>
					<option value="供应商信息">供应商信息</option>
					<option value="采购单信息">采购单信息</option>
					<option value="BOM信息">BOM信息</option>
					<option value="成品/半成品信息">成品/半成品信息</option>
					<option value="日计划信息">日计划信息</option>
					<option value="库存信息">库存信息</option>
					<option value="mrp信息">mrp信息</option>
					<option value="发货单实收数量信息">发货单实收数量信息</option>
					<option value="ASN号与总成对应关系信息">ASN号与总成对应关系信息</option>
					<option value="72小时预计需求报表信息">72小时预计需求报表信息</option>
					<option value="自动分解信息">自动分解信息</option>
					<option value="送货单信息">送货单信息</option>
				</select><span class="required" id="typeCheck">*</span></td>
		--></tr>	
		<tr>
			<td class="bluetext">时间:</td>
			<td><html:text property="sleepTime" styleId="sleepTime" style="width:185px;"/><span class="required" id="sleepTimeCheck">*</span></td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<input type="button" value="保存" onclick="AJAXCode();"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
