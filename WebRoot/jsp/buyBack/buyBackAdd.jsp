<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退款录入</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>



</head>

<script type="text/javascript">



	  $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
			if(msg!=""){
			alert(msg);
			}
		  //禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
		  //禁用回车事件，防止提交
		
		
		
	 });
	 function sub(){
	  $("#addBuyBackForm").submit();
	  }
	//退款金额不能大于可退金额
	jQuery.validator.addMethod("backMoneyCheck",
    function(value, element) {  
          return Number(value)<=Number($('#canBackMoney').val()); 
    }); 
    jQuery.validator.addMethod("backMoneyCheckZero",
    function(value, element) {  
          return Number(value)>0; 
    }); 
    
  	$(document).ready(function() {
			$("#addBuyBackForm").validate({
				rules: { 
					"addBuyBackForm.backDate":"required",
					"addBuyBackForm.backWay":"required", 
					"addBuyBackForm.no":"required",
					"addBuyBackForm.money":{required:true,number:true,backMoneyCheck:true,backMoneyCheckZero:true}
				},
				messages: {
					"addBuyBackForm.backDate":"请输入退款日期",
					"addBuyBackForm.no":"请输入退款编号",
					"addBuyBackForm.backWay":"请选择退款方式", 
					"addBuyBackForm.money":{required:"请输入退款金额",number:"退款金额必须是数字",
					"backMoneyCheck":"不能大于可退款金额",
					"backMoneyCheckZero":"退款金额需大于零"
					}
				} 
			});
	})
	
	
</script>
<body>
<html:form action="addBuyBack.do" styleId="addBuyBackForm">

<input type="hidden" name="addBuyBackForm.productTypeId" value="${paymentInfo.productTypeId}">		
<input type="hidden" name="addBuyBackForm.supplierId" value="${paymentInfo.supplierId}">	
<input type="hidden" name="addBuyBackForm.supplierName" value="${paymentInfo.supplierName}">	 
<input type="hidden" name="addBuyBackForm.paymentId" value="${paymentInfo.id}">	
<input type="hidden" name="addBuyBackForm.productTypeName" value="${paymentInfo.productTypeName}">	 
     
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退款管理 &gt;&gt; 采购退款录入</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br/>
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">付款单号</td>
          <td class="td_02" width="30%">${paymentInfo.id}&nbsp;</td>
          <td class="td_01" width="20%">付款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${paymentInfo.paymentMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="paymentMoney" value="${paymentInfo.paymentMoney}">	
        </tr>
        <tr>
          <td class="td_01" height="18">产品分类名称</td>
          <td class="td_02">${paymentInfo.productTypeName}&nbsp;</td>
          <td class="td_01">供货商名称</td>
          <td class="td_02">${paymentInfo.supplierName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">已指定金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfo.alreadyAppointMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="alreadyAppointMoney" value="${paymentInfo.alreadyAppointMoney}">	
          <td class="td_01">已退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfo.alreadyBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="alreadyBackMoney" value="${paymentInfo.alreadyBackMoney}">
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <input type="hidden" name="addBuyBackForm.id" value="${backAddDto.id}">
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;退款日期</td>
          <td class="td_02" width="30%"><input type="text" onfocus="calendarMinToday()" name="addBuyBackForm.backDate" id="addBuyBackForm.backDate" style="width:120px;" maxlength="20" value="${backAddDto.backDate}"/></td>
          <td class="td_01" width="20%"><span style="color:#FF0000">* </span>退款编号</td>
          <td class="td_02" width="30%"><input type="text" name="addBuyBackForm.no" id="addBuyBackForm.no" style="width:120px;" maxlength="10" value="${backAddDto.no}"/></td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;退款方式</td>
          <td class="td_02">  
          
          	<html:select property="addBuyBackForm.backWay" value="${backAddDto.backWay}" style="width:126px">
				<html:option value="">--请选择--</html:option>
				<html:option value="1">现金</html:option>
				<html:option value="2">支票</html:option>
				<html:option value="3">网银</html:option>
				<html:option value="4">电汇</html:option>
				<html:option value="5">银行承兑</html:option> 
				<html:option value="7">其它</html:option>

		 
			</html:select>
				
           </td>
          <td class="td_01">凭证号</td>
          <td class="td_02"><input type="text" name="addBuyBackForm.number" id="addBuyBackForm.number" style="width:120px;" maxlength="20" value="${backAddDto.number}"/></td>
        </tr>
        <tr>
          <td class="td_01">可退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${paymentInfo.canBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="canBackMoney" value="${paymentInfo.canBackMoney}">
          <input type="hidden" name="canBackMoney" id="canBackMoney" value="${paymentInfo.canBackMoney}">
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;退款金额</td>
          <td class="td_02"><input type="text" name="addBuyBackForm.money" id="addBuyBackForm.money" style="width:120px;" maxlength="13" value="${backAddDto.money}"/>
            元</td>
        </tr>
        <tr>
          <td class="td_01">特殊说明</td>
          <td colspan="3" class="td_02"><textarea name="addBuyBackForm.text" id="addBuyBackForm.text" cols="100" rows="4"  value="${backAddDto.text}"></textarea>
          </td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td height="50px" align="center" valign="bottom"><img src="${ctx}/images/btnSubmit.gif" onclick="sub()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <img src="${ctx}/images/btnBack.gif" width="65" height="20"  onclick="javasrcipt:window.location.href='buyPayment.do'"/>
   </td>
    <td >&nbsp;</td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
