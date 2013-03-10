<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>回款录入</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript">
	var mreturnPD =''; 
	$(
	function(){
		$.ajaxSetup({ 
  			async: false 
  		}); 
		//返回按钮跳转
		$("#backbtn").click(
			function(){
				window.location.href='mreturn.do';
			}
		);
		//单选回款
		$("#mReturnType0").click(
			function(){
				$("#idText").html('<span style="color:#FF0000">*</span>&nbsp;回款编号');
				$("#idText1").html('<input type="text" name="no" id="no" maxlength="10" style="width:120px;" />');
				$('.returnWay').html('<html:select property="returnWay" value="" style="width:126px">'+
			  '<html:option value="">--请选择--</html:option> '+
			  '<html:option value="1">现金</html:option>'+
              '<html:option value="2">支票</html:option>'+
             ' <html:option value="3">网银</html:option>'+
             ' <html:option value="4">电汇</html:option>'+
             ' <html:option value="5">银行承兑</html:option>'+
           		' <html:option value="7">其它</html:option>'+
			'</html:select>');
			}
		);
		//单选在途款
		$("#mReturnType1").click(
			function(){
				$("#idText").text(' ');
				$("#idText1").text(' ');
				$('.returnWay').html('<html:select property="returnWay" value="" style="width:126px">'+
			  '<html:option value="">--请选择--</html:option> '+
			  '<html:option value="1">现金</html:option>'+
              '<html:option value="2">支票</html:option>'+
              '<html:option value="3">网银</html:option>'+
              '<html:option value="4">电汇</html:option>'+
              '<html:option value="5">银行承兑</html:option>'+
              '<html:option value="6">承诺函</html:option>'+
              '<html:option value="7">其他</html:option>'+
			'</html:select>');
			}
		);
 		//打开客户选择小页面 
		$("#addCustomer").click(function(){   
				window.open('${ctx}/getMReturnCustomerSelect.do','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=400');
		});		
		//提交
		$("#btnSubmit").click(function(){
			mreturnPD = 'tj';
		 	$("#mreturnArriveForm").submit();
		});
		//指定
		$("#btnZD").click(function(){
			mreturnPD = 'zd';
			$("#mreturnArriveForm").submit();
		});
		
		//验证
		$("#mreturnArriveForm").validate({
			rules: { 
				"no":"required",
				"returnWay":"required",
				"ptoductTypeId":"required",
				"customerIdv":"required",
				"returnDate":"required",
				"money":{required:true,max:100000000}
			},
			messages: {
				"no":"请输入回款编号",
				"returnWay":"请选择回款方式",
				"ptoductTypeId":"请选择产品分类",
				"customerIdv":"请选择客户",
				"returnDate":"请选择回款日期",
				"money":{required:"请输入回款金额",max:"请输入正确的回款金额"}
			} ,
			submitHandler:function(){$.post("createMReturn.do",$("#mreturnArriveForm").serializeArray() ,waitResult,"html");},
			errorClass: "invalid"
		});
	});
	//拆分客户
	function addCustomer(customer){
		var array = new Array();
		array = customer.split(",");
		$("#customerNameText").text(array[1]);
		$("#customerId").val(array[0]);
		$("#customerIdv").val(array[0]);
		$("#customerName").val(array[1]);
	}
	
	//判断是指定还是提交
	function waitResult(data){
		if(mreturnPD=='tj'){
			err(data);
		}else if(mreturnPD=='zd'){
			errZD(data);
		}
	}
	//提交后获取后台新建结果
	function err(data){
	  var array = new Array();
	  array = data.split(",");
	  if(array[0]=='true'){
	  	document.location.href='mreturn.do';
	  }else{
	    alert("创建失败！");
	  }
	}
	
	//指定后获取后台新建结果
	function errZD(data){
	  var array = new Array();
	  array = data.split(",");
	  if(array[0]=='true'){
	  	document.location.href='appoint.do?addPara.mreturnId='+array[1];
	  }else{
	    alert("创建失败！");
	  }
	}
	
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 回款管理 &gt;&gt; 录入回款</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    <form id="mreturnArriveForm">
    	<input type="hidden" id="customerId" name="customerId"  />
    	<input type="hidden" id="customerName" name="customerName"  />
    	<input type="hidden" id="id" name="id"  />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;回款类型</td>
          <td class="td_02" width="30%">
          	<input name="returnType" type="radio" id="mReturnType0" value="0" checked="checked" align="absmiddle" />
            回款
            <input name="returnType" type="radio" id="mReturnType1" value="1" align="absmiddle" />
            在途款
          </td>
          <td class="td_01" width="20%" id="idText"><span style="color:#FF0000">*</span>&nbsp;回款编号</td>
          <td class="td_02" width="30%" id="idText1"><input type="text" name="no" id="no" style="width:120px;" maxlength="10" /></td>
        </tr>
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;回款方式</td>
          <td class="td_02" width="30%">
          <div class="returnWay">
          	<html:select property="returnWay" value="" style="width:126px">
			  <html:option value="">--请选择--</html:option> 
			  <html:option value="1">现金</html:option>
              <html:option value="2">支票</html:option>
              <html:option value="3">网银</html:option>
              <html:option value="4">电汇</html:option>
              <html:option value="5">银行承兑</html:option>
              <html:option value="7">其他</html:option>
			</html:select>
		   </div>
			
          </td>
          <td class="td_01" width="20%">凭证号</td>
          <td class="td_02" width="30%"><input type="text" name="number" id="number" maxlength="20" style="width:120px;" /></td>
        </tr>
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;产品分类名称</td>
          <td class="td_02" width="30%">
			<html:select property="ptoductTypeId" value="${ptoductTypeId}" style=" width:126px">
				<html:option value="">--请选择--</html:option> 
				<html:options collection="producttypelist" property="id" labelProperty="name"/>
			</html:select>          
          </td>
          <td class="td_01" width="20%"><a href="#" id="addCustomer" ><img src="${ctx}/images/btnKHXZ.gif" width="72" height="20" align="absmiddle" /></a></td>
          <td class="td_02" width="30%" id="customerNameText"><input type="hidden" id="customerIdv" name="customerIdv"  />&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;回款日期</td>
          <td class="td_02" width="30%">
          	<input type="text" name="returnDate" id="returnDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="" />	
          </td>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;回款金额</td>
          <td class="td_02" width="30%"><input type="text" name="money" id="money" style="width:120px;"   />
            元</td>
        </tr>
        <tr>
          <td class="td_01" width="20%">特殊说明</td>
          <td colspan="3" class="td_02"><textarea name="text" id="textarea" cols="100" rows="4"></textarea>
          </td>
        </tr>
      </table>
      </form>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td height="50" align="right" valign="bottom"><a id="btnSubmit" href="#"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a></td>
          <td align="center" valign="bottom" width="150px"><a id="btnZD" href="#"><img src="${ctx}/images/btnZD.gif" width="65" height="20" /> </a></td>
          <td align="left" valign="bottom" ><a id="backbtn" href="#"><img  src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
