<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<title></title>
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
		 
			$(document).ready(function(){
				if($("#table0")){
					$("#table0 tr:even").addClass("treven");
					$("#table0 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}

				if($("#areaListTable")){
					$("#areaListTable tr:even").addClass("treven");
					$("#areaListTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				} 
				
				//全选
				$("#areaAll").click(function(){ 
					if($(this).attr("checked")){
						$("#areaListTable :checkbox").attr("checked", true);
					}else{
						$("#areaListTable :checkbox").attr("checked", false);
					} 
				});
				
			});
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
	$(function() {
		var err = "${err}";		
		if (err != "") {
			alert(err);
		}	
	})
	var myArray1=new Array();
	var myArray2=new Array();
	var myArray3=new Array();
	
	function newWindow11231(){
		window.open('searchUser.do?method=treasuryManagerAddBefore', '添加', 'height=510, width=600, top=200, left=400, toolbar=no, scrollbars=yes,resizable=yes,location=no, status=no');
	};
	function newWindow21_(myArray,regionalName){
		var table = document.getElementById("table3");
		
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
	        newTd0.innerHTML = '&nbsp;<input type="checkbox" checked="checked" name="treasuryProduct" value="'+customer[0]+'">';
	        newTd1.innerText = customer_1[1];
	        newTd2.innerText = customer_1[0];
		}
		if($("#table3")){
					$("#table3 tr:even").addClass("treven");
					$("#table3 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
	};

	function newWindow21(myArray,regionalName){
	
		//传过来的myArray要进行处理
		var myArray_=new Array();
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			myArray_.push(arr[0]+"^"+arr[1]+"%"+arr[2]+"");
		}
		
		if(myArray_.length==0){
			if(myArray2.length==0){
				newWindow21_(new Array(),regionalName);
				myArray2=new Array();
			}else{
				newWindow21_(myArray2,regionalName);
				myArray2=myArray2;
			}
		}else{
			if(myArray2.length==0){
				newWindow21_(myArray_,regionalName);
				myArray2=myArray_;
			}else{
				
				newWindow21_(unique(myArray_.concat(myArray2)),regionalName);
				myArray2=unique(myArray_.concat(myArray2));
			}
		}
	};

	var formId = 0;
	//控件名
	var checknNames =  [ "id",			"mail", 	"name", 	"password" ,	"tel",		"mobile",	"msn",		"qq"];
	//提示语
	var descriptions = [ "用户名",       "邮箱", 		"用户名", 	"密码",			"电话",		"手机",		"MSN",		"QQ"];
	//是否非空验证,如果非空验证填写notnull,如果只验证 开头和结尾的空格填写 notspace，如果不需要非空验证传空参
	var checkNulls =   [ "notnull",		"notnull",	"notnull", 	"notnull",		"notnull",	"notnull",	 "",		""];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,只能填字母和数字  “abcnum”，    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”,
	var checkTypes =   [ "loginname", 	"email", 	"", 		"", 			"phone",	"num",		"email",	"num"];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "20", 			"30", 		"8", 		"x6",			"20",		"12",		"30",		"12"];
	
	function clickSubmit() {
		var role_id = document.getElementById("role_id").value;
		var user_dept_id = document.getElementById("user_dept_id").value;
		var logistics = document.getElementById("logistics").value;
		var user_area_id = document.getElementById("user_area_id").value;
		if(role_id==14){
		//验证物流公司
			document.getElementById("user_dept_id").options[0].selected = true;
			document.getElementById("user_area_id").options[0].selected = true;
			if(logistics == ''){
				alert('必添项不能为空！');
				return;
			}
		}else{
			//验证部门
			document.getElementById("logistics").options[0].selected = true;
			if(user_dept_id == ''){
				alert('必添项不能为空！');
				return;
			}
			//验证区域
			if(role_id==4||role_id==9){
				if(user_area_id == ''){
					alert('必添项不能为空！');
					return;
				}
			}else{
				document.getElementById("user_area_id").options[0].selected = true;
			}
		}
		if(role_id==0){
			alert("必添项不能为空！");
			return ;
		}
		if(checkForm()==false){
			return ;
		}
		document.getElementsByTagName('Form')[0].submit();
	}
	

	function newWindow(){
		window.open('searchUser.do?method=salesManagerAddCustomer1', '添加客户', 'height=420, width=600, top=200, left=400, toolbar=no, scrollbars=yes,resizable=yes,location=no, status=no');
	};
	
	function newWindow1123(){
		window.open('searchUser.do?method=salesAssistantAddBefore', '添加', 'height=510, width=600, top=200, left=400, toolbar=no, scrollbars=yes,resizable=yes,location=no, status=no');
	};
	
	function newWindow1_(myArray){
		var table = document.getElementById("table1");
		
		for(var i=table.rows.length-1;i>0;i--){ 
	        table.deleteRow(i); 
	    }
		
		for(var i=0;i<myArray.length;i++){
			var customer_ = myArray[i];
			var customer = customer_.split(",");
			//添加一行
	        var newTr = table.insertRow();
	        //添加两列
	        var newTd0 = newTr.insertCell();
	        var newTd1 = newTr.insertCell();
	        //设置列内容和属性
	        newTd0.innerHTML = '&nbsp;<input type="checkbox" checked="checked" name="customer" value="'+customer[0]+'">';
	        newTd1.innerText = customer[1];
		}
	};
	
	function newWindow1(myArray,regionalName){
		var myArray_=new Array();
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			myArray_.push(arr[0]+","+arr[1]+"");
		}
		
		if(myArray_.length==0){
			if(myArray3.length==0){
				newWindow1_(new Array(),regionalName);
				myArray3=new Array();
			}else{
				newWindow1_(myArray3,regionalName);
				myArray3=myArray3;
			}
		}else{
			if(myArray3.length==0){
				newWindow1_(myArray_,regionalName);
				myArray3=myArray_;
			}else{
				newWindow1_(unique(myArray_.concat(myArray3)),regionalName);
				myArray3=unique(myArray_.concat(myArray3));
			}
		}
		if($("#table1")){
			$("#table1 tr:even").addClass("treven");
			$("#table1 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		} 
	};
	
	function newWindow2_(myArray,regionalName){
		var table = document.getElementById("table2");
		
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
	        newTd0.innerHTML = '&nbsp;<input type="checkbox" checked="checked" name="regionalProduct" value="'+customer[0]+'">';
	        newTd1.innerText = customer_1[1];
	        newTd2.innerText = customer_1[0];
		}
		if($("#table2")){
			$("#table2 tr:even").addClass("treven");
			$("#table2 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		} 
	};
	function che(a) {
		var all=document.getElementsByName("product_type_id");
		if(a.checked==true){
   			 for(i=0;i<all.length;i++){
        		all[i].checked=true;
   		 }
		 }else if(a.checked==false){
    		for(i=0;i<all.length;i++){
        		all[i].checked=false;
    		}
 		 }
	};
	function che1(a) {
		var all=document.getElementsByName("customer");
		if(a.checked==true){
   			 for(i=0;i<all.length;i++){
        		all[i].checked=true;
   		 }
		 }else if(a.checked==false){
    		for(i=0;i<all.length;i++){
        		all[i].checked=false;
    		}
 		 }
	};
	function che2(a) {
		var all=document.getElementsByName("regionalProduct");
		if(a.checked==true){
   			 for(i=0;i<all.length;i++){
        		all[i].checked=true;
   		 }
		 }else if(a.checked==false){
    		for(i=0;i<all.length;i++){
        		all[i].checked=false;
    		}
 		 }
	};
		function che3(a) {
		var all=document.getElementsByName("treasuryProduct");
		if(a.checked==true){
   			 for(i=0;i<all.length;i++){
        		all[i].checked=true;
   		 }
		 }else if(a.checked==false){
    		for(i=0;i<all.length;i++){
        		all[i].checked=false;
    		}
 		 }
	};
	function change(obj){
		var user_dept_id_td1 = document.getElementById("user_dept_id_1");
		var user_dept_id_td2 = document.getElementById("user_dept_id_2");//部门
		var user_area_id_td1 = document.getElementById("user_area_id_1");
		var user_area_id_td2 = document.getElementById("user_area_id_2");//区域
		var logistics_tr = document.getElementById("logistics_tr");//所属物流
		var tr1 = document.getElementById("product11");//负责产品
		var tr2 = document.getElementById("customer11");//负责客户
		var tr3 = document.getElementById("regionalProduct11");//负责区域产品
		var tr4 = document.getElementById("treasuryProduct11");//负责库房产品

		var tr5 =  document.getElementById("rArea");//负责区域

		if(obj=='13'){//库房主管
			tr4.style.display="none"; 
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none";
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="none";
			tr5.style.display="none";
		}else if(obj=='5'||obj=='6'||obj=='8'||obj=='10'||obj=='11'){//销售总监 信用专员 采购专员 产品总监 采购主管
			tr4.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none";
			tr1.style.display="block";
			tr2.style.display="none";
			tr3.style.display="none";
			tr5.style.display="none";
		}else if(obj=='9'){//区域总监
			tr4.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="block";
			user_area_id_td2.style.display="block";
			logistics_tr.style.display="none";
			tr1.style.display="block";
			tr2.style.display="none";
			tr3.style.display="none";
			tr5.style.display="none";
		}else if(obj=='4'){//销售经理
			tr4.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="block";
			user_area_id_td2.style.display="block";
			logistics_tr.style.display="none";
			tr1.style.display="block";
			tr2.style.display="block";
			tr3.style.display="none";
			tr5.style.display="none";
		}else if(obj=='14'){//物流管理员
			tr4.style.display="none";
			user_dept_id_td1.style.display="none";
			user_dept_id_td2.style.display="none";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="block";
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="none";
			tr5.style.display="none"; 
		}else if(obj=='3'){//销售助理
			tr4.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none";
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="block";
			tr5.style.display="none";
		}else if(obj=='12'){//库房管理员
			tr5.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none";
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="none";
			tr4.style.display="block";
		}else if(obj=='7'||obj=='15'||obj=='16'||obj=='17'||obj=='18'||obj=='2'){//信用主管 法务专员 运营助理 运营 信息管理员
			tr4.style.display="none"; 
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none";
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="none";
			tr5.style.display="none";
		}else if(obj=='19'||obj=='20'){//高级区域总监 大区经理
			tr4.style.display="none";
			user_dept_id_td1.style.display="block";
			user_dept_id_td2.style.display="block";
			user_area_id_td1.style.display="none";
			user_area_id_td2.style.display="none";
			logistics_tr.style.display="none"; 
			tr1.style.display="none";
			tr2.style.display="none";
			tr3.style.display="none";
			tr3.style.display="none";
			tr5.style.display="block";
		}
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
	
</script>
</head>
<body >
<form action="searchUser.do">
<input type="hidden" name="method" value="addUser">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 用户信息管理 &gt;&gt; 用户添加</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br />
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;登录名</td>
              <td class="td_02" ><input type="text" name="id"  maxlength="20" style="width:120px;" ></td>
                <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;Email</td>
              <td class="td_02" ><input type="text" name="mail" value="${map["mail"]}" maxlength="30" style="width:240px;" /></td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;用户名</td>
              <td class="td_02"><input type="text" name="name" value="${map["name"]}" maxlength="4" style="width:120px;" /></td>
                <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;密码</td>
              <td class="td_02"><input type="text" name="password" value="${map["password"]}" maxlength="40" style="width:120px;" /></td>
            </tr>
            <tr>
            	<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;电话</td>
              <td class="td_02"><input type="text" name="tel" value="${map["tel"]}" maxlength="20" style="width:120px;" /></td>
                <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;手机</td>
              <td class="td_02"><input type="text" name="mobile" value="${map["mobile"]}" maxlength="12" style="width:120px;" /></td>
            </tr>
            <tr>
            	<td class="td_01">MSN</td>
                <td class="td_02"><input type="text" name="msn" value="${map["msn"]}" maxlength="30" style="width:240px;" /></td>
                <td class="td_01">QQ</td>
                <td class="td_02"><input type="text" name="qq" value="${map["qq"]}" maxlength="12" style="width:120px;" /></td>
            </tr>
            <tr>
           	  <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;使用状态${map["enable"]}</td>
              <td class="td_02">
            <select name="enable" style=" width:126px">
            	<option value="1">使用</option>           	
              	<option value="0">停用</option>
            </select></td>
            <td id="user_dept_id_1" class="td_01"><span style="color:#FF0000">*</span>&nbsp;人员部门</td>
            <td id="user_dept_id_2" class="td_02">
	            <select name="user_dept_id" id="user_dept_id" style=" width:126px">
	            	<option value="">--请选择--</option>
	     			<logic:iterate id="li" name="userDept">
	          			<option value="${li.dept_id}">${li.dept_name}</option>
	          		</logic:iterate>
	            </select>&nbsp;
            </td>
            </tr>
            <tr>
           	  <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;职务</td>
            <td class="td_02"><select name="role_id" id="role_id" style=" width:126px" onchange="javascript:change(this.value);">
              <option value="">--请选择--</option>
              <option value="2">信息管理员</option>
              <option value="3">销售助理</option>
              <option value="4">销售经理</option>
              <option value="5">销售总监</option>
              <option value="6">信用专员</option>
              <option value="7">信用主管</option>
              <option value="8">采购专员</option>
              <option value="9">区域总监</option>
              <option value="10">产品总监</option>
              <option value="11">采购主管</option>
              <option value="12">库房管理员</option>
              <option value="13">库房主管</option>
              <option value="14">物流管理员</option>
              <option value="15">法务专员</option>
              <option value="16">运营总监助理</option>
              <option value="17">运营总监</option>
              <option value="18">总经理</option>
			  <option value="19">区域经理</option>
			  <option value="20">大区经理</option>
			  <option value="21">开票专员</option>
            </select>&nbsp;</td>
              <td class="td_01" id="user_area_id_1" style="display: none;"><span style="color:#FF0000">*</span>&nbsp;所属区域</td>
              <td class="td_02" id="user_area_id_2" style="display: none;"><select name="user_area_id" id="user_area_id" style=" width:126px">
              				<option value="">--请选择--</option>
              				<logic:iterate id="li" name="userArea">
	                				<option value="${li.area_id}">${li.area_name}</option>
	                		</logic:iterate>
            </select>&nbsp;</td>
            </tr>
            
            <tr id="logistics_tr" style="display: none;">
              <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;所属物流公司</td>
              <td class="td_02">
              	<select name="logistics" id="logistics" style=" width:288px">
      				<option value="">--请选择--</option>
              		<logic:iterate id="logistics" name="logistics">
              			<option value="${logistics.id}">${logistics.name}</option>
              		</logic:iterate>
              	</select>&nbsp;
              </td>
           	
            </tr>
            
        </table>
     </td>
    <td >&nbsp;</td>
  </tr>
  <tr id="product11" style="display: none;">
    <td >&nbsp;</td>
    <td align="center">
    <br/><div class="div_tiao" style="font-size:12px">&nbsp;负责产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table0">
        	<tr>
            	<th nowrap="nowrap" width="41">选择</th>
                <th nowrap="nowrap">产品分类名称</th>
            </tr>
            <logic:iterate id="product" name="product">
	            <tr>
	            	<td align="right">
	            		&nbsp;<input name="product_type_id" type="checkbox" value="${product.id}">
	            	</td>
	                <td align="right">${product.name}</td>
	            </tr>
            </logic:iterate>
        </table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
        	<tr>
            	<td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" onclick="javascript:che(this);"></td>
              	<td width="50px" align="left" style="font-size:12px">全选</td>
              	<td>&nbsp;<!-- <img src="${ctx}/images/btnDelete.gif" /> --></td>
                <td>&nbsp;<!-- <a href="#" onclick=""><img src="${ctx}/images/btnAdd.gif" /></a> --></td>
          </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr id="customer11" style="display: none;">
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <div class="div_tiao" style="font-size:12px">&nbsp;负责客户</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
        	<tr>
            	<th nowrap="nowrap" width="41">选择</th>
                <th nowrap="nowrap">客户名称</th>
            </tr>
        </table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
        	<tr>
            	<td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" onclick="javascript:che1(this);"></td>
                <td width="50px" align="left" style="font-size:12px">全选</td>
                <td align="left"><a href="javascript:newWindow();" onclick=""><img src="${ctx}/images/btnAdd.gif" /></a></td>
                <td align="left" width="100px"><!-- <img src="${ctx}/images/btnDelete.gif" /> --></td>
            </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  
  <tr id="regionalProduct11" style="display: none;">
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <div class="div_tiao" style="font-size:12px">&nbsp;负责区域产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table2">
        	<tr>
            	<th nowrap="nowrap" width="41">选择</th>
                <th nowrap="nowrap">区域名称</th>
                <th nowrap="nowrap">产品分类名称</th>
            </tr>
        </table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
        	<tr>
            	<td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" onclick="javascript:che2(this);"></td>
                <td width="50px" align="left" style="font-size:12px">全选</td>
                <td align="left"><a href="javascript:newWindow1123();" onclick=""><img src="${ctx}/images/btnAdd.gif" /></a></td>
              	<td align="left" width="100px"><!-- <img src="${ctx}/images/btnDelete.gif" /> --></td>
            </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  
  <tr id="treasuryProduct11" style="display: none;">
    <td >&nbsp;</td>
    <td align="center">
    <br/><div class="div_tiao" style="font-size:12px">&nbsp;负责库房产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table3">
          <tr>
            <th nowrap="nowrap" width="41">选择</th>
            <th nowrap="nowrap">库房名称</th>
            <th nowrap="nowrap">产品分类名称</th>
          </tr>
        </table>
    	<br />
        <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
        	<tr>
            	<td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" onclick="javascript:che3(this);"></td>
              <td width="50px" align="left" style="font-size:12px">全选</td>
                <td align="left"><a href="javascript:newWindow11231();"><img src="${ctx}/images/btnAdd.gif" /></a></td>
                <td align="left" width="100px"><!-- <img src="${ctx}/images/btnDelete.gif" /> --></td>
          </tr>
        </table>    </td>
    <td>&nbsp;</td>
  </tr>

	<tr id="rArea" style="display: none;">
		<td>&nbsp;</td>
		<td align="center">
		<br/>
		<div class="div_tiao" style="font-size:12px">&nbsp;负责区域</div>
		<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="areaListTable">
			<tr>
				<th nowrap="nowrap" width="41">选择</th>
				<th nowrap="nowrap">区域名称</th> 
			</tr>
			<logic:iterate id="li" name="userArea">
	            <tr>
	            	<td align="right">
	            		&nbsp;<input name="areaList" type="checkbox" value="${li.area_id}">
	            	</td>
	                <td align="right">${li.area_name}</td>
	            </tr>
            </logic:iterate>
		</table>
		<br />
        <table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
			<tr>
				<td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" id="areaAll"></td>
              	<td width="50px" align="left" style="font-size:12px">全选</td>
              	<td>&nbsp;</td>
                <td>&nbsp;</td>
			</tr>
        </table>
		</td>
		<td>&nbsp;</td>
	</tr>

  <tr>
  	<td></td>
    <td height="50px" align="center" valign="bottom">
    <a href="javascript:clickSubmit();"><img src="${ctx}/images/btnAdd.gif" /></a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" /></a></td>
  	<td>&nbsp;</td>
  </tr>
</table>
<br />
</form>
</body>
</html>
