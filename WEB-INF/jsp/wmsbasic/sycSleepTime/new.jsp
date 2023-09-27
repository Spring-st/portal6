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
	if(type==null||type==""){
		alert("请选择同步类型");
	}else{
		$.ajax({
			url:"AJAXType.do",
			cache : false,
			type : "post",
			data : {"type":type},
			dataType : "json",
			success:function(result){
				if(result!=0){
					alert("同步类型已经存在");
				}else if(result==0){
					sycSleepTimeForm.submit();
				}
			}
		});
	}
  }
</script>

<html:javascript formName="sycSleepTimeForm" staticJavascript="false" />
<html:form action="/insertSycSleepTimeMaintenanceAction">
	<table width=90% border=0 cellpadding=4 cellspacing=0>	
		<tr>
			<td class="bluetext"><bean:message key="workCenter.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>		
		<tr>
			<td class="bluetext">类型:</td>
			
			<td><html:select property="type" styleId="type">
				<html:option value="物料信息">物料信息</html:option>
				<html:option value="供应商信息">供应商信息</html:option>
				<html:option value="采购单信息">采购单信息</html:option>
				<html:option value="BOM信息">BOM信息</html:option>
				<html:option value="成品/半成品信息">成品/半成品信息</html:option>
				<html:option value="日计划信息">日计划信息</html:option>
				<html:option value="库存信息">库存信息</html:option>
				<html:option value="mrp信息">mrp信息</html:option>
				<html:option value="发货单实收数量信息">发货单实收数量信息</html:option>
				<html:option value="ASN号与总成对应关系信息">ASN号与总成对应关系信息</html:option>
				<html:option value="72小时预计需求报表信息">72小时预计需求报表信息</html:option>
				<html:option value="自动分解信息">自动分解信息</html:option>
				<html:option value="送货单信息">送货单信息</html:option>
				<html:option value="Jit物料计划自动合并">Jit物料计划自动合并</html:option>
			</html:select><span class="required">*</span></td>
		</tr>	
		<tr>
			<td class="bluetext">时间(分钟):</td>
			<td><html:text property="sleepTime" /><span class="required">*</span></td>
		</tr>
		
		<tr>
			<td colspan="3" align="right" style="border-top: 1px solid black;">
				<input type="button" onclick="AJAXCode();" value="保存"/>
			</td>
			<!--<td colspan="3" align="right" style="border-top: 1px solid black;">
				<html:submit><bean:message key="all.save" /></html:submit>
				
			</td>-->
			<td colspan="1" align="right" style="border-top: 1px solid black;">
				<input type="button" value="关闭" onclick="window.close();"/>
			</td>
		</tr>
		
	</table>
	
</html:form>
