<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.aof.model.metadata.RoleType" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript" src="includes/selection.js"></script>
<script type="text/javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function selectWmsPart() {
	var supplier="";
	var supplierPart="";
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPart.do?supplier='+supplier+'&supplierPart='+supplierPart, 
			null, 'dialogWidth:840px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			$.ajax({         
                type:"POST", //请求方式        
                url:"supplierPartProportionByAjax.do", //请求路径        
                cache: false,           
                data:"part="+v['id']+"&code=${X_supplierList.supplierId.code}",  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	if(date == "true"){
                		$('#saveId').get(0).style.display = 'none';
                		$('#disabledId').get(0).style.display = 'inline';
                		alert("已存在该物料信息！");
                	}else{
                		$("#proportionSpanOld").get(0).style.display = "none"; 
                		$("#proportionSpan").get(0).style.display = "inline"; 
                   		$("#spanMfg").attr("innerHTML", parseFloat(date));
                    	$("#spanMfgBFB").get(0).style.display = "inline";
                    	var sxd = 100 - parseFloat(date);
                    	$("#proportion").attr("value", parseFloat(sxd))
                	}
			   } 
            })
		};
			document.getElementById("name").innerHTML=v['id'];
			document.getElementById("partId_id").value=v['id'];
	}
//-->
</script>
<html:javascript formName="supplierPartForm" staticJavascript="false" />
<html:form action="updateSupplierPart.do" onsubmit="return validateSupplierPartForm(this);">
<html:hidden property="id" />
<html:hidden property="supplierId_id" />

<div style="font-size: 20px; font-weight: bold;margin: 20px 0px;" align="center">
${X_supplierList.supplierId.code}供应商物料信息修改
</div>

<table border="0" cellpadding="4" cellspacing="0">
<tr>
  <td colspan="2" class="warningMsg"><html:errors/></td>
</tr>
<tr>
  <td class="bluetext"><bean:message key="SupplierPart.supplierId.code"/>:</td>
   <td>
   <html:hidden property="supplierId_id" />
   		${X_supplierList.partId.id}
    </td>
     <td class="bluetext"><bean:message key="SupplierPart.partId.code"/>:</td>
  	<td><span id="name">${X_supplierList.partId.id}</span>
 	<html:hidden property="partId_id" styleId="partId_id"/><span class="required">*</span>
  	<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a>
  </td>
</tr>
<tr>
<td class="bluetext"><bean:message key="SupplierPart.supplierPart" />:</td>
<td><html:text property="supplierPart" /></td>
<td class="bluetext">单位:</td>
	<td><html:text property="supplierUnit" /></td>
</tr>
<tr>
	<td class="bluetext">包装箱容量:</td>
	<td><html:text property="supplierCapacity" /></td>
	<td class="bluetext"><bean:message key="SupplierPart.line" />:</td>
	<td><html:text property="line" /></td>
</tr>
<tr>
	<td class="bluetext"><bean:message key="SupplierPart.factory" />:</td>
	<td><html:text property="factory" /></td>
</tr>
<tr>
	<td class="bluetext"><bean:message key="SupplierPart.conversionRatio"/>sss:</td>
	<td><input style="text" value="${X_supplierList.conversionRatio}"/></td>
</tr>
<tr>
	<td class="bluetext">所占比例:</td>
	<td colspan="3"><html:text property="proportion" styleId="proportion"/>
	<span style="color: red; display: none;" id="proportionSpan">已占比例&nbsp;&nbsp;
	<span style="color: red;" id="spanMfg"></span>
	<span style="color: red; display: none;" id="spanMfgBFB">%</span>
	</span>
	</td>
</tr>
</table>
<hr/>
<div align="center">
<html:submit styleId="saveId"><bean:message key="all.save"/></html:submit>
<input type="button" value="保存" onclick="alert('已存在该物料信息！');" disabled="disabled" style="display: none;" id="disabledId"/>
<input type="button" value="<bean:message key="all.cancel"/>" onclick="window.parent.close();"/>
</div>
</html:form>

