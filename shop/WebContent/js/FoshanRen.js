function winOpen (strURL,strName,width,height)
{
    theWindow = window.open (strURL,strName,"width="+width+" height="+height+" scrollbars=yes left="+(1024-width)/2+" top="+(768-height)/2);	
    if (theWindow.opener == null) theWindow.opener = window;
    if (window.focus) theWindow.focus();
}
//楠岃瘉閭欢
function verifyEmailAddress(strEmail){
  var myReg = /^[_a-zA-Z0-9_-_._-]+@([_a-zA-Z0-9_-]+\.)+[a-zA-Z]{2,3}$/;
  return myReg.test(strEmail);
}
/*****************************************************************
****                     鍒ゆ柇鏄惁涓烘棩鏈熸暟鎹� (lhm)       渚嬪瓙:itIsDate("2009-10-7" , "-")    *****
*****************************************************************/
function itIsDate(DateString , Dilimeter) 
{ 
  if (DateString==null) return false; 
  if (Dilimeter=='' || Dilimeter==null) 
   Dilimeter = '-'; 
  var tempy=''; 
  var tempm=''; 
  var tempd=''; 
  var tempArray; 
  if (DateString.length<8 && DateString.length>10) 
    return false;    
  tempArray = DateString.split(Dilimeter); 
  if (tempArray.length!=3) 
   return false; 
  if (tempArray[0].length==4) 
  { 
   tempy = tempArray[0]; 
   tempd = tempArray[2]; 
  } 
  else 
  { 
   tempy = tempArray[2]; 
   tempd = tempArray[1]; 
  } 
  tempm = tempArray[1]; 
  var tDateString = tempy + '/'+tempm + '/'+tempd+' 8:0:0';//鍔犲叓灏忔椂鏄洜涓烘垜浠浜庝笢鍏尯 
  var tempDate = new Date(tDateString); 
  if (isNaN(tempDate)) 
   return false; 
 if (((tempDate.getUTCFullYear()).toString()==tempy) && (tempDate.getMonth()==parseInt(tempm)-1) && (tempDate.getDate()==parseInt(tempd))) 
  { 
   return true; 
  } 
  else 
  { 
   return false; 
  } 
} 

/*****************************************************************
****                   姹傚瓧绗︿覆鐨勫瓧鑺傞暱搴�    (lhm)          *****
*****************************************************************/
function byteLength(paraString) 
{
 var strValue =new String(paraString);
 var strLength = strValue.length;
 var numLength =0;
  for (globle_i =0 ; globle_i<strLength;globle_i++){
    var ASCIIValue =strValue.charCodeAt(globle_i);
    if ( ASCIIValue > 0 && ASCIIValue < 127 )  
      numLength = numLength + 1 
    else
     numLength = numLength + 2
  }
  return numLength;
}

/*****************************************************************
****                     鍘婚櫎绌烘牸     (lhm)                 *****
*****************************************************************/
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
	
function ltrim(stringToTrim) {
	return stringToTrim.replace(/^\s+/,"");
}
		
function rtrim(stringToTrim) {
	return stringToTrim.replace(/\s+$/,"");
}

String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g,"");}
String.prototype.ltrim = function() {return this.replace(/^\s+/,"");}
String.prototype.rtrim = function() {return this.replace(/\s+$/,"");}
/*****************************************************************
****               澶嶉�妗嗙殑鍏ㄩ�涓庡彇娑�    (LHM)              *****
*****************************************************************/
function CheckAll(form){
	var length = form.itemId.length;
	var tocheck = form.chkall.checked;
	if (length)
		for (var i=0; i<length; i++){ 
			if (form.itemId[i].disabled != true){
				form.itemId[i].checked = tocheck;
			} 			
		}
	else {
		if (form.itemId.disabled !=true){
			form.itemId.checked = tocheck;
		}
	}
}

/*****************************************************************
****                     鍒犻櫎澶勭悊     (LHM)                  *****
*****************************************************************/
function del_btn (form,strMsg,actionurl){
  	var result = false;
  	var length = form.itemId.length;	
	if (form.itemId.checked) { //鍙湁涓�潯璁板綍鏃舵墽琛屾璇彞
		result = true;	
	}  		
	for (var i=0; i<length; i++){ 
		if (form.itemId[i].checked){
		  result = true;
		  break;
		}		 		
	}
    if (!result){
		alert ("娌℃湁閫夋嫨浠讳綍椤圭洰!");
		return false;
    }else{
		if (confirm('\n'+strMsg)){
			form.action = actionurl;
			return true;
		}
	    return false;
	} 	
}

/*****************************************************************
****                    杞寲瀛楃涓�    (LHM)                 *****
*****************************************************************/
function conversion_code(paraString)
{
	strResult = "";
	j=0;
	for (i=0;i<paraString.length;i++){ 
		Char = String1.charAt(i);
		if (Char=="'"){
		    strResult = strResult + paraString.substring(j,i)+"\\"+"\'";
		    j=i+1;
		 } 
	return strResult;
	}
}

/*****************************************************************
****                 鏁板瓧杈撳叆鎺у埗澶勭悊     (LHM)              *****
*****************************************************************/
function InputIntNumberCheck(){
	//涓烘敮鎸両E 鎴�Netscape
	var theEvent=window.event || arguments.callee.caller.arguments[0]; 
	var elm ;
	var ver = navigator.appVersion;
	if (ver.indexOf("MSIE") != -1){  // IE
		if ( !((theEvent.keyCode >=48)&&(theEvent.keyCode<=57))){
			theEvent.keyCode=0;
		}
	}else{ // Netscape
		if ( !((theEvent.which >=48)&&(theEvent.which<=57))){
			theEvent.stopPropagation();
			theEvent.preventDefault();
		}
	}
	//
}

/*****************************************************************
****          鏈夊皬鏁扮偣鏁板瓧杈撳叆鎺у埗澶勭悊     (LHM)             *****
*****************************************************************/
function InputLongNumberCheck(){
	if ( !((window.event.keyCode >=48)&&(window.event.keyCode<=57) || window.event.keyCode ==46)){
		window.event.keyCode=0;
	}

	var theEvent=window.event || arguments.callee.caller.arguments[0]; 
	var elm ;
	var ver = navigator.appVersion;
	if (ver.indexOf("MSIE") != -1){  // IE
		if (!((theEvent.keyCode>=48)&&(theEvent.keyCode<=57) || theEvent.keyCode ==46)){
			theEvent.keyCode=0;
		}
	}else{ // Netscape
		if ( !((theEvent.which >=48)&&(theEvent.which<=57) || theEvent.which ==46)){
			theEvent.stopPropagation();
			theEvent.preventDefault();
		}
	}
}

/*****************************************************************
****                        鎹㈤〉澶勭悊                         *****
*****************************************************************/
function toWhichPage(objform, whichPage){
    objform.whichPage.value = whichPage;
    objform.submit();
}

/*************************liuxch   *******************************
****                        鑾峰彇cookie鍐呭                   *****
*****************************************************************/
function getCookie( name ){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ){
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
			endOfCookie = document.cookie.length;
			return unescape( document.cookie.substring( y, endOfCookie ) );
		}
		x = document.cookie.indexOf( " ", x ) + 1;	
		if ( x == 0 ) break;
	}
	return "";
}

/*****************************************************************
****                    璁剧疆cookie鍐呭銆佽繃鏈熸椂闂�             *****
*****************************************************************/
function setCookie( name, value, expiredays ) { 
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" 
} 
/*****************************************************************
****                      妫�煡杈撳叆瀛楃    (lhm)              *****
'//		 islegality锛氳緭鍏ョ殑瀛楃鏄惁涓虹粰瀹氱殑瀛楃
'//杩斿洖鍊硷細bool
*****************************************************************/
function islegality(checkstrpass){
var checkokpass="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
for (i=0; i<checkstrpass.length; i++) {
       ch=checkstrpass.charAt(i);
       for (j=0;j<checkokpass.length; j++){
        if (ch==checkokpass.charAt(j))
        break;
        }
      if (j==checkokpass.length){
	  return false; //鍑芥湁鐗瑰埆瀛楃鏃惰繑鍥瀎alse
      break;
        }
  }
   return true;
}
/**
* 妫�煡杈撳叆鏄惁涓枃
*/
function ck_chinese(value_) {
  return escape(value_).indexOf("%u")!=-1 
}