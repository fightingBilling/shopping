<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>添加产品</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script type="text/javascript" src="/js/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">
tinyMCE.init({
	language : "zh_cn",
	mode : "textareas",
	theme : "advanced",
	//width : "500",
	plugins : "table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,zoom,flash,searchreplace,print,contextmenu",
	theme_advanced_buttons1_add_before : "save,separator",
	theme_advanced_buttons1_add : "fontselect,fontsizeselect",
	theme_advanced_buttons2_add : "separator,insertdate,inserttime,preview,zoom,separator,forecolor,backcolor",
	theme_advanced_buttons2_add_before: "cut,copy,paste,separator,search,replace,separator",
	theme_advanced_buttons3_add_before : "tablecontrols,separator",
	theme_advanced_buttons3_add : "emotions,iespell,flash,advhr,separator,print",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	theme_advanced_path_location : "bottom",
	plugin_insertdate_dateFormat : "%Y-%m-%d",
	plugin_insertdate_timeFormat : "%H:%M:%S",
	extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]",
	external_link_list_url : "example_data/example_link_list.js",
	external_image_list_url : "example_data/example_image_list.js",
	flash_external_list_url : "example_data/example_flash_list.js"
});

function Formfield(name, label){
	this.name=name;
	this.label=label;
}
function verifyForm(objForm){
	tinyMCE.triggerSave();//手动把iframe的值赋给textarea表单元素
	var list  = new Array(new Formfield("productInfo.name", "产品名称"),new Formfield("v_type_name", "产品类型"),
	new Formfield("productInfo.baseprice", "产品底价"),new Formfield("productInfo.marketprice", "产品市场价")
	,new Formfield("productInfo.salesprice", "产品销售价"),new Formfield("productInfo.description", "产品描述"),
	new Formfield("stylename", "产品图片的样式"));
	for(var i=0;i<list.length;i++){
		var objfield = eval("objForm."+ list[i].name);
		if(trim(objfield.value)==""){
			alert(list[i].label+ "不能为空");
			if(objfield.type!="hidden" && objfield.focus()) objfield.focus();
			return false;
		}
	}
	var imagefile = objForm.imagefile.value;
	var ext = imagefile.substring(imagefile.length-3).toLowerCase();
	if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
		alert("只允许上传gif、jpg、bmp、png！");
		return false; 
	}
    return true;
}
function SureSubmit(objForm){
	/* if (verifyForm(objForm)) */
		objForm.submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form action="/control/product/manage!add" enctype="multipart/form-data" method="post">
<s:hidden name="productTypeId" />
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">添加产品：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <s:textfield label="产品名称" required="true" requiredposition="right" name="productInfo.name" size="50" maxlength="40"/>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品类别<font color="#FF0000">*</font>  ：</div></td>
      <td width="75%"> <input type="text" id="v_type_name" name="v_type_name" disabled="disabled" size="30"/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<s:url value="/control/product/manage!selectUI" />','列表',600,400)">(<a href="<s:url value='/control/product/type/manage!addUI' />">添加产品类别</a>)
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="底(采购)价" required="true" requiredposition="right" name="productInfo.baseprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()" />
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="市场价 " required="true" requiredposition="right" name="productInfo.marketprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="销售价 " required="true" requiredposition="right" name="productInfo.salesprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <s:textfield label="货号(注:供货商提供的便于产品查找的编号) " name="productInfo.code" size="20" maxlength="30"/>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品图片 ：</div></td>
      <td width="75%"> 样式名称：<input name="stylename" type="text" size="10">样式图片<input type="file" name="imagefile" size="30"></td>
    </tr>
	<tr bgcolor="f5f5f5">
	<td width="25%"> 
	<s:select label="品牌"
      name="selectedBrandId"
       headerKey="-1" headerValue="Select Brand"
       list="brands"
       listKey="code"
       listValue="name"
       required="true" />
      </td><td><a href='<s:url value="/control/product/brand/manage!addUI" />'>添加品牌</a></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:select label="适用性别" list="sex" listValue="getName()">   
		</s:select>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="型号" name="productInfo.model" size="35" maxlength="30"/>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="重量:克" name="productInfo.weight" size="10" maxlength="10" onkeypress="javascript:InputIntNumberCheck()"/>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textfield label="购买说明" name="productInfo.buyexplain" size="35" maxlength="30" />
    </tr>
	<tr bgcolor="f5f5f5"> 
      <s:textarea label="产品简介" name="productInfo.description" required="true" cols="120" rows="23" />
	</tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="button" name="Add" value=" 确 认 " class="frm_btn" onClick="javascript:SureSubmit(this.form)">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</s:form>
<br>
</body>
</html>