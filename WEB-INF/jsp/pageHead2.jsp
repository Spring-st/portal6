<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<link type="text/css" href="includes/pro_drop_1.css" />
<%--<script src="includes/stuHover.js" type="text/javascript"></script>--%>
<script type="text/javascript">
function selectType(val){
	if(val==1){
		confirUnplannedWarehousing();
	}
	else if(val==2){
		printCode(val);
	}
	else if(val==3){
		printUnplannedWarehousing();
	}
	else if(val==4){
		confir(val);
	}
	else if(val==5){
		producePrintCode();
	}
	else if(val==6){
		printPlanOutOrder(val);
	}
	 
}
function selectEXCEL(value){ 
	if(value==0){
		exportsTemplate();
	}
	else if(value==1){
		uploadfile();
	}
	else if(value==2){
		uploadfileInit();
	}
}
function addLot(){
	selectBox();
}
</script>
<input type="hidden" id="pageSize" />
<table class="pagebanner" border="0">
	<tr>
		<td align="left">
			<table border="0">
				<tr>
					<td style="padding: 0px 0px 0px 20px;">
						操作：
					</td>
					<%--<td>
						<select onchange="selectType(this.value);">
							<option value="">请选择</option>
							<c:if test="${x_warehousing.status == '1'}">
								<option value="1">确认</option>
							</c:if>
							<c:if test="${x_warehousing.status == '2'}">
								<option value="4">取消</option>
								<option value="2">打印标签</option>
								<option value="3">打印单据</option>
							</c:if>
						</select>
					</td>--%>
					<td>
						<select onchange="selectType(this.value);">
							<option value="">请选择</option>
							<c:if test="${x_warehousing.status == '1'}">
								<option value="1">确认</option>
							</c:if>
							<c:if test="${x_warehousing.status != '1'}">
								<%--<option value="4">取消</option>
								--%><c:if test="${x_pageHead2 != '2'}">
									<option value="2">打印标签</option>
								</c:if>
								<option value="3">打印单据</option>
							</c:if>
						</select>
					</td>
					<c:if test="${x_warehousing.status == '1' && x_pageHead2 != '2'}">
						<td style="padding: 0px 0px 0px 20px;">
							导入导出：
						</td>
						<td>
							<%--<select onchange="selectEXCEL(this.value);">
								<option value="">请选择</option>
								<c:if test="${x_warehousing.status == '1'}">
									<option value="0">下载模版</option>
									<option value="1">导入数据</option>
									<option value="2">初始化导入</option>
								</c:if>
							</select>--%>
							<select onchange="selectEXCEL(this.value);">
								<option value="">请选择</option>
								<option value="0">下载模版</option>
								<option value="1">导入数据</option>
								<option value="2">初始化导入</option>
							</select>
						</td>
					</c:if>
					<td>
						<%--<c:if test="${x_pageHead2 == '2'}">
							<input type="button" value="新增出库批次" onclick="addLot();"/>
						</c:if>--%>
						<c:if test="${x_pageHead2 == '2'}">
							<c:if test="${x_warehousing.status == '1'}">
								<input type="button" value="新增出库批次" onclick="addLot();"/>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td align="right"><input type="button" value="返回" onclick="history.go(-1)"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="刷新" onclick="location=location"/></td>
	</tr>
</table> 
