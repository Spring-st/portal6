<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td> <input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
	<td><a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.code}</a></td>
	<td>${X_OBJECT.address }</td>
	<td>${X_OBJECT.max_inventory }</td>
	<td>${X_OBJECT.basic_storeroom_id.code }</td>
	<td>
		<c:if test="${X_OBJECT.recommend_status=='1'}">不参与</c:if>
        <c:if test="${X_OBJECT.recommend_status=='0'||X_OBJECT.recommend_status==null}">参与</c:if>
	</td>
	<td>
		<c:if test="${X_OBJECT.freeae_status=='0'}">已冻结</c:if>
        <c:if test="${X_OBJECT.freeae_status=='1'||X_OBJECT.freeae_status==null}">未冻结</c:if>
	</td>
	<td>
	  <span style="color:${X_OBJECT.enabled.color}">
      <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">${X_OBJECT.enabled.engShortDescription}</c:if>
      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">${X_OBJECT.enabled.chnShortDescription}</c:if>
    </span>
	</td>
	<td>
	<c:if test="${X_OBJECT.f_in_f_out_status=='0'}">强制限定</c:if>
        <c:if test="${X_OBJECT.f_in_f_out_status=='1'||X_OBJECT.f_in_f_out_status==null}">不限定</c:if>
	</td>
	<td>${X_OBJECT.description }</td>
</tr>

