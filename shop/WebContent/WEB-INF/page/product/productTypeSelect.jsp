<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title> 类别选择 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<SCRIPT language=JavaScript src="/js/xmlhttp.js"></SCRIPT>

<SCRIPT language=JavaScript>
function checkIt(){
	var objForm = document.forms[0];
	var form = opener.document.forms[0];
	window.opener.document.getElementById("v_type_name").value = objForm.dicName.value;
	if (form){
		form.productTypeId.value = objForm.dicId.value;
		form.v_type_name.value = objForm.dicName.value;
	}
	window.close();
}
function getDicName(dicId,strDicName){
	var objForm = document.forms[0];
	objForm.dicId.value = dicId;
	objForm.dicName.value = strDicName;
}
function getTypeList(typeid){
		var typecontent = document.getElementById('typecontent');
		if(typecontent){
			typecontent.innerHTML= "数据正在加载...";
			send_request(function(value){typecontent.innerHTML=value}, '<html:rewrite action="/control/product/type/manage"/>?method=gettypelist&typeid='+ typeid, true);
		}
}
</SCRIPT>
<style>
<!--
.inputText{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	border: 1px solid #999999;
}
body {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 12px;
	color: #666666;
}
-->
</style>
</head>

<body>
<s:set name="menuout" value="" />
<s:iterator value="#{menutypes }" var="menu">
	<s:set name="menuout" value="&gt;&gt;<a href='/control/product/manage!selectUI&parentTypeId=#{menu.typeid }'>#{menu.name }</a>"/>
</s:iterator>
<s:debug />
产品类别列表,请选择分类:<br>
导航:<a href='<s:url value="/control/product/manage!selectUI" />'>顶级目录</a> <s:property value="menuout" escapeXml="false"/>
<form method="post" name="main" action="">
  <input type="hidden" name="dicId">
  <input type="hidden" name="dicName">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
	<tr><td id="typecontent">
	<table width="100%" border="0">
	
	<tr>
	<s:iterator value="types" var="entry" status="rowstatus">	
	    <td>
		<s:if test="#entry.childtypes.size()>0">
		<a href='<s:url value="/control/product/manage!selectUI" />?parentTypeId=${entry.typeid}'><b>${entry.name}</b></a>
		</s:if>
		<s:if test="#entry.childtypes.size()==0"> <INPUT TYPE="radio" NAME="type" onclick="getDicName('${entry.typeid}','${entry.name}')">${entry.name}</s:if>
		</td>
		<s:if test="#rowstatus.count%5==0"> </tr><tr> </s:if>
	</s:iterator>
	</tr>
	</table>
	</td></tr>
	<tr><td colspan="2" align="center">
		<input type="button" name="create" value=" 确 认 " onClick="javascript:checkIt()">
		<input type="button" name="cancel" onClick="javaScript:window.close()" value=" 取 消 "> 
    </td></tr>
</table>
</form>
</body>
</html>