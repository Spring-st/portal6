<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
    function checkLocationCode(value){
    	$.post("checkLocationCodeAjax.do",
    		   {"code":value},
    		   function(date){
    			 if(date=='[false]'){
    				  alert("库位代码已存在，请从新填写！");
    			 } 
    	  })
    }
    function validateStorageLocationFrom(){
    	var basic_storeroom_id_idTxt = document.getElementById("basic_storeroom_id_id").value;
    	if(basic_storeroom_id_idTxt==""){
    		alert("请选择所属库房！");
    		return false;
    	} 
    	var codeTxt = document.getElementById("code").value;
    	if(codeTxt==""){
    		alert("请选填写库位代码！");
    		return false;
    	} 
    	var max_inventoryTxt = document.getElementById("max_inventory").value;
    	if(max_inventoryTxt == ""){
    		alert("请选填写库位最大库存！");
    		return false;
    	} 
    	
    	return true;
    }
</script>
<html:form action="/insertStorageLocation" onsubmit="return validateStorageLocationFrom();">
	<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">库位新增</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
			<td class="bluetext"><bean:message key="storageLocation.site" />:</td>
			<td><html:select property="site_id" >
			  <html:options collection="X_SITEID" labelProperty="name" property="id"/>
			  </html:select>
			<span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.describe" />:</td>
			<td><html:text property="description" /><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.code" />:</td>
			<td><html:text property="code" onblur="checkLocationCode(this.value);"/><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.address" />:</td>
			<td><html:text property="address" /><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.max_inventory" />:</td>
			<td><html:text property="max_inventory" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.storeroom_id" />:</td>
			<td><html:select property="basic_storeroom_id_id" >
				<html:option value="">请选择</html:option>
				<html:options collection="X_STOREROOMLIST" labelProperty="code" property="id"/>
			</html:select>
			<span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.enabled" />:</td>
			<td><html:select property="enabled">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_ENABLEDDISABLEDLIST"
						property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_ENABLEDDISABLEDLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
		</tr>
		<tr>
			<td class="bluetext">冻结状态:</td>
			<td><html:select property="freeae_status">
					<html:option value="1">不冻结</html:option>
					<html:option value="0">冻结</html:option>
			</html:select></td>
			<td class="bluetext">推荐状态:</td>
			<td>
			<html:select property="recommend_status" >
				<html:option value="0">参与推荐</html:option>
				<html:option value="1">不参与推荐</html:option>
			</html:select>
			</td>
		</tr>
		<tr>
			<td class="bluetext">先进先出状态:</td>
			<td>
			<html:select property="f_in_f_out_status" >
				<html:option value="0">强制限定</html:option>
				<html:option value="1">不限定</html:option>
			</html:select>
			</td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
