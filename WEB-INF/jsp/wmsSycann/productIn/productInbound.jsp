<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>
<script type="text/javascript" src="includes/basicJS.js">
</script>
<script type="text/javascript" src="includes/jquery-1.3.2.js">
</script>
<script type="text/javascript">
function productInStorage() {
	//获取所有选中的成品id
	var el = document.getElementsByName('ids');
	var ell = document.getElementsByName('hnccodes');
	var len = el.length;
	var str = "";
	var str1 = "";
	var count = 0;
	var count1 = 0;
	var start = 0;
	var startNo = "";
	for ( var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].checked == true)) {
			if (el[i].id != "checkAll") {
				str = str + el[i].value + ",";
				count++;
				str1 = ell[i].value;
				if (start == 0) {
					startNo = ell[i].value;
					start = 1;
				}
				if (startNo != str1) {
					count1++;
				}
			}
		}
	}

	if (count == 0) {
		alert('选勾选要入库的赛赫条码!');
		return false;
	}
	if (count1 != 0) {
		alert('请勾选相同的海纳川成品条码入库!');
		return false;
	}
	//验证库位库存 是否足够
	$.ajax({
		type : "POST", //请求方式        
		url : "checkhncCountByAjax.do", //请求路径        
		cache : false,
		data : "array=" + str, //传参        
		dataType : 'json', //返回值类型        
		success : function(date) {
			if (date == "true") {
				productInStoragea(startNo,str);
			}else{
				alert('该成品号库存不足!');
				return;
			}
		}
	});
//	$.ajax( {
//		type : "POST", //请求方式        
//		url : "checkhncCodeByAjax.do", //请求路径        
//		cache : false,
//		data : "hncCode=" + startNo, //传参        
//		dataType : 'json', //返回值类型        
//		success : function(date) {
//			if (date == "true") {
//				var title = "product.instorage.new";
//				var url = "productInObject.do?array=" + str;
//				var v = dialogAction(url, title, 600, 400);
//				if (v) {
//					window.location.href = "productDownlineList.do";
//				}
//			} else {
//				alert('请维护成品器具的盛放数量！');
//				return;
//			}
//		}
//	});
}
function productInStoragea(startNo,str) {
	$.ajax( {
		type : "POST", //请求方式        
		url : "checkhncCodeByAjax.do", //请求路径        
		cache : false,
		data : "hncCode=" + startNo, //传参        
		dataType : 'json', //返回值类型        
		success : function(date) {
			if (date == "true") {
				var title = "product.instorage.new";
				var url = "productInObject.do?array=" + str;
				var v = dialogAction(url, title, 600, 400);
				if (v) {
					window.location.href = "productDownlineList.do";
				}
			} else {
				alert('请维护成品器具的盛放数量！');
				return;
			}
		}
	});
}
</script>
<html:form action="/productDownlineList.do" styleId="formId">
	<input type="hidden" name="fields" value="" desc="请选择" />
	<input type="hidden" name="fields" value="pg.hncCode" desc="海纳川成品条码" />
	<input type="hidden" name="fields" value="pg.shCode" desc="赛赫条码" />
	<input type="hidden" name="fields" value="pg.partTireCode"
		desc="零件号(轮胎)" />
	<input type="hidden" name="fields" value="pg.partHubCode"
		desc="零件号(轮毂)" />
	<html:hidden property="order" />
	<html:hidden property="descend" />
	<input type="hidden" id="ExportType" name="exportType" />
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td colspan="4"><jsp:include page="../../simQuery.jsp" /></td>
		</tr>
	</table>
</html:form>
<page:form action="/productDownlineList.do" method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff" align="center">
				<th width="2%">
					<input type="checkbox" value="0" id="checkAll"
						onclick="selectAll();" />
				</th>
				<th>
					海纳川成品条码
				</th>
				<th>
					赛赫条码
				</th>
				<th>
					零件号(轮毂)
				</th>
				<th>
					轮毂描述
				</th>
				<th>
					零件号(轮胎)
				</th>
				<th>
					轮胎描述
				</th>
				<th>
					零件号(气门嘴)
				</th>
				<th>
					气门嘴描述
				</th>
				<th>
					数量
				</th>
			</tr>
		</thead>
		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="inRow.jsp" />
			</logic:iterate>
		</tbody>
	</table>
	<hr>
</page:form>

<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>
