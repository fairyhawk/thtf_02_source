<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<title>销售助理修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		-->
		</style>
		<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
var myArray1=new Array();
	function newWindow1123(){
		window.open('searchUser.do?method=salesAssistantAddBefore', '添加', 'height=510, width=600, top=200, left=400, toolbar=no, scrollbars=yes,resizable=yes,location=no, status=no');
	};
	function newWindow2_(myArray,regionalName){
		var table = document.getElementById("table");
		
		for(var i=table.rows.length-1;i>0;i--){ 
	        table.deleteRow(i); 
	    } 
		
		for(var i=0;i<myArray.length;i++){
			var customer_ = myArray[i];
			var customer = customer_.split("^");
			var customer_1 = customer[1].split("%");
			//添加一行
	        var newTr = table.insertRow();
	        //添加两列
	        var newTd0 = newTr.insertCell();
	        var newTd1 = newTr.insertCell();
	        var newTd2 = newTr.insertCell();
	        //设置列内容和属性
	        newTd0.innerHTML = '&nbsp;<input  type="checkbox" checked="checked" name="grqwrewq" value="'+customer[0]+'">';
	        newTd1.innerText = customer_1[1];
	        newTd2.innerText = customer_1[0];
		}
		if($("#table")){
			$("#table tr:even").addClass("treven");
			$("#table tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
	};
	
	function newWindow2(myArray,regionalName){
		var myArray_=new Array();
		
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			myArray_.push(arr[0]+"^"+arr[1]+"%"+arr[2]+"");
		}

		if(myArray.length==0){
			if(myArray1.length==0){
				newWindow2_(new Array(),regionalName);
				myArray1=new Array();
			}else{
				newWindow2_(myArray1,regionalName);
				myArray1=myArray1;
			}
		}else{
			if(myArray1.length==0){
				newWindow2_(myArray_,regionalName);
				myArray1=myArray_;
			}else{
				newWindow2_(unique(myArray_.concat(myArray1)),regionalName);
				myArray1=unique(myArray_.concat(myArray1));
			}
		}
	};
	
	function che2(a) {
		var all=document.getElementsByName("grqwrewq");
		if(a.checked==true){
       			 for(i=0;i<all.length;i++){
            		all[i].checked=true;
            		myArray.push(all[i].value);
       		 }
   			 }else if(a.checked==false){
        		for(i=0;i<all.length;i++){
            		all[i].checked=false;
        		}
        		myArray=new Array();
   	 		 }
	};
	var formId = 0;
	//控件名
	var checknNames =  [ "id",			"mail", 	"name", 	"newpassword" ,	"tel",		"mobile",	"msn",		"qq"];
	//提示语
	var descriptions = [ "用户名",       "邮箱", 		"用户名", 	"密码",			"电话",		"手机",		"MSN",		"QQ"];
	//是否非空验证,如果非空验证填写notnull,如果只验证 开头和结尾的空格填写 notspace，如果不需要非空验证传空参
	var checkNulls =   [ "notnull",		"notnull",	"notnull", 	"notspace",		"notnull",	"notnull",	 "",		""];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,只能填字母和数字  “abcnum”，    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”,
	var checkTypes =   [ "loginname", 	"email", 	"", 		"", 			"phone",	"num",		"email",	"num"];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "20", 			"30", 		"8", 		"x6",			"20",		"12",		"30",		"12"];
	
	function clickSubmit() {
		if(checkForm()==false){
			return ;
		}
		document.getElementsByTagName('Form')[0].submit();
	};
	
	function unique(data){ 
	    data = data || []; 
	    var a = {}; //声明一个对象，JavaScript中的对象可以当哈希表用
	    for (var i=0; i <data.length; i++) {
	        a[data[i]] = true;  //设置标记，把数组的值当下标，这样就可以去掉重复的值
	    }; 
	    data.length=0; 
	    for (var i in a){ //遍历对象，把已标记的还原成数组
	        data[data.length] = i; 
	    } 
	    return data; 
	};
	
	function fanren_1() {
		var customers = document.getElementsByName("grqwrewq")
		for(var i=0;i<customers.length;i++){
			var obj = customers[i];
			var id =obj.value;
			var name =obj.parentNode.parentNode.childNodes[2].innerHTML;
			var name1 =obj.parentNode.parentNode.childNodes[1].innerHTML;
			myArray1.push([id+"^"+" "+name+"%"+name1]);
		}
	};
	
	function fanren_() {
		var customers = document.getElementsByName("grqwrewq")
		for(var i=0;i<customers.length;i++){
			var obj = customers[i];
			var id =obj.value;
			var name =obj.parentNode.parentNode.childNodes[2].innerHTML;
			var name1 =obj.parentNode.parentNode.childNodes[1].innerHTML;
			myArray1.push([id+"^"+""+name+"%"+name1]);
		}
	};
</script>
</head>
<body onLoad="fanren_()">
<form action="searchUser.do">
<input type="hidden" name="method" value="changeUser">
<input type="hidden" name="changeid" value="6">
<input type="hidden" name="id" value="${view.id}">
<input type="hidden" name="roleid" value="${view.role_id}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 用户信息管理 &gt;&gt; 用户修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br />
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01">登录名</td>
              <td class="td_02" >${view.id}</td>
                <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;Email</td>
              <td class="td_02" ><input type="text" name="mail" value="${view.mail}" style="width:240px;" maxlength="30"/></td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;用户名</td>
              <td class="td_02"><input type="text" name="name" value="${view.name}" style="width:120px;" maxlength="4"/></td>
                <td class="td_01">密码</td>
              <td class="td_02">
				<input type="text" name="newPassword" style="width:120px;" maxlength="40"/>&nbsp;<span style="color:#FF0000">*如填写将变更用户密码</span><input type="hidden" name="password" value="${view.password}"/>
			  </td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;电话</td>
              <td class="td_02"><input type="text" name="tel" value="${view.tel}" style="width:120px;" maxlength="20"/></td>
                <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;手机</td>
              <td class="td_02"><input type="text" name="mobile" value="${view.mobile}" style="width:120px;" maxlength="12"/></td>
            </tr>
            <tr>
            	<td class="td_01" width="20%">MSN</td>
                <td class="td_02" width="30%"><input type="text" name="msn" value="${view.msn}" style="width:240px;" maxlength="30"/></td>
                <td class="td_01" width="20%">QQ</td>
                <td class="td_02" width="30%"><input type="text" name="qq" value="${view.qq}" style="width:120px;" maxlength="12"/></td>
            </tr>
            <tr>
           	  <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;使用状态</td>
            <td class="td_02">
            	<select name="enable" style=" width:126px">
	              	<c:if test="${view.enable==1}">
		              <option value="1" selected="selected">使用</option>
					</c:if>
					<c:if test="${view.enable!=1}">
		              <option value="1">使用</option>
					</c:if>
					<c:if test="${view.enable==0}">
		              <option value="0" selected="selected">停用</option>
		            </c:if>
		            <c:if test="${view.enable!=0}">
		              <option value="0">停用</option>
		            </c:if>
	              </select>
            </td>
              <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;人员部门</td>
            <td class="td_02">
            	<select name="user_dept_id" style="width:126px">
              				<logic:iterate id="li" name="userDept">
              					<c:if test="${view.user_dept_id==li.dept_id}">
	                				<option value="${li.dept_id}" selected="selected">${li.dept_name}</option>
	                			</c:if>
	                			<c:if test="${view.user_dept_id!=li.dept_id}">
	                				<option value="${li.dept_id}">${li.dept_name}</option>
	                			</c:if>
	                		</logic:iterate>
              </select>
            </td>
            </tr>
            <tr>
           	  <td class="td_01" height="18px">职务</td>
	            <td class="td_02">
	            	${view.role_name}
	            </td>
	            <td class="td_01">&nbsp;</td>
	            <td class="td_02">&nbsp;</td>
            </tr>
            
        </table>
     </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <div class=" div_tiao" style="font-size:12px">&nbsp;负责区域产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        	<tr>
            	<th nowrap="nowrap" width="41">选择</th>
                <th nowrap="nowrap">区域名称</th>
                <th nowrap="nowrap">产品分类名称</th>
            </tr>
            <logic:iterate id="RegionalProduct" name="RegionalProduct">
	            <tr>
	            	<td align="right">&nbsp;<input name="grqwrewq" checked="checked" type="checkbox" value="${RegionalProduct.product_type_id},${RegionalProduct.user_area_id}"></td>
	                <td align="right">${RegionalProduct.area_name}</td>
	                <td align="right">${RegionalProduct.type_name}</td>
	            </tr>
            </logic:iterate>
        </table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
  <tr>
    <td width="41px" align="left">&nbsp;&nbsp;<input name="input" type="checkbox" onClick="javascript:che2(this);"></td>
    <td width="50px" style="font-size:12px">全选</td>
    <td width="100px"><a href="javascript:newWindow1123();"><img src="${ctx}/images/btnAdd.gif" /></a></td>
    <td align="right">&nbsp;</td>
  </tr>
</table>
</td>
    <td >&nbsp;</td>
  </tr>
  <tr><td></td><td height="50px" align="center" valign="bottom"><a href="javascript:clickSubmit();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
