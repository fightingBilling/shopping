<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>添加类别</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("类别名称不能为空！");
		form.name.focus();
		return false;
	}
	if (byteLength(form.note.value)>200){
		alert("备注不能大于100字！");
		form.note.focus();
		return false;
	}	
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form action="/control/product/type/manage!add" method="post"  onsubmit="return checkfm(this)">
<s:hidden name="parentid"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">添加类别：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
    	<s:textfield name="type.name" size="50" maxlength="80" label="类别名称" />
    </tr>
	<tr bgcolor="f5f5f5"> 
		<s:textfield name="type.note" size="80" maxlength="100" label="备注(100字以内)" />
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
</s:form>
<br>
</body>
</html>