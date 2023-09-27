<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script type="text/javascript">
<!--
	function changePassword() {
		v = window.showModalDialog('showDialog.do?title=user.changePassword.title&changePassword.do', null, 'dialogWidth:300px;dialogHeight:200px;status:no;help:no;scroll:no');
	}
//-->
</script>
<link href="images/skin.css" rel="stylesheet" type="text/css">

<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="50%" height="64">
    <a href="blank.do" target="mainFrame">
    <img src="images/logo.gif" height="64">
    </a>
    </td>
    <td width="50%" valign="top">
	    <table align="right" width="100%" border="0" cellspacing="0" cellpadding="0">
	     <tr>
        <c:if test="${X_LOGINED}">
        <td><font color="#FAF4FF">
        <strong><bean:message key="user"/>: ${sessionScope.LOGIN_USER.name}</strong></font>&nbsp;</td>
        <td><a href="javascript:changePassword();"><img src="images/key.gif" width="16" height="16" border="0"/></a></td>
        <td>
    	  <c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
          <a href="switchLocale.do" target="mainFrame"><img id="localeIcon" src="images/zh.gif" width="16" height="16" border="0"/></a>
          </c:if>
          <c:if test="${sessionScope.LOGIN_USER.locale!='en'}">
          <a href="switchLocale.do" target="mainFrame"><img id="localeIcon" src="images/en.gif" width="16" height="16" border="0"/></a>
    	  </c:if>
        </td>
        </c:if>
        <td><a href="help/User Manual for Admin.rar"><img src="images/help.gif" width="16" height="16" border="0"/></a></td>
        <c:if test="${X_LOGINED}">
        <td><a href="logout.do"><img src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        </c:if>
      </tr>
	    </table>
    </td>
  </tr>
</table>