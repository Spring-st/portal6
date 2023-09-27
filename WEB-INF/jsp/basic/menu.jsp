<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<link type="text/css" href="includes/slashc.two-tier-menu.css" rel="stylesheet">
<script language="javascript" src="includes/tree/tree.js"></script>
<script language="javascript" src="includes/tree/tree_tpl.js"></script>
<script type="text/javascript" src="includes/3rdparty/jquery-1.5.min.js"></script>
<script type="text/javascript" src="includes/3rdparty/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="includes/jquery.slashc.two-tier-menu.min.js"></script>
	<style type="text/css">
		html { background: #111; }
	</style>
	
<table cellpadding="5" cellspacing="0" cellpadding="10" border="1" width="100%" height="100%" bgcolor="#EEF2FB">
<tr>
	<td valign="top" > 
	<script language="javascript">
<logic:iterate id="m" name="X_MENULIST">
		new tree([${m}], TREE_TPL);
</logic:iterate>
	</script>

	<script language="javascript">
		//new tree(TREE_GLOBAL_ADMIN, TREE_TPL);
		//new tree(TREE_SITE_ADMIN, TREE_TPL);
		//new tree(TREE_ENDUSER, TREE_TPL);
	</script>
	</td>
</tr>
</table>

 