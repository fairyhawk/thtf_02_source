<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>在途款到账</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup({ 
	  			async: false 
	  		});
			$("#submitBtn").click(function(){
				$("#mreturnArriveForm").submit();
			});
			$("#mreturnArriveForm").validate({
			rules: { 
				"mReturnEntity.no":"required",
				"mReturnEntity.returnWay":"required",
				"mReturnEntity.returnDate":"required"
			},
			messages: {
					"mReturnEntity.no":"请输入回款编号",
					"mReturnEntity.returnWay":"请选择回款方式",
					"mReturnEntity.returnDate":"请选择回款日期"
				} ,
				submitHandler:function(){
					if(confirm("警告：确认后无法再次修改，请再次确认！")){
						$.post("newarrive.do",$("#mreturnArriveForm").serializeArray() ,waitResult,"html");
					} 
				},
				errorClass: "invalid"
			});
		});
		function waitResult(result){
			if(result=='true'){
				document.location.href='mreturn.do';
			}else{
				alert("回款到帐失败！");
			}
		}
		</script>
</head>
<body>
<form method="post" action="newarrive.do" id="mreturnArriveForm" name="mreturnArriveForm">
<input type="hidden" name="mReturnEntity.id" value=${mid.id} id="mReturnEntity.id" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 回款管理 &gt;&gt; 在途款到账</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%">&nbsp;回款类型</td>
          <td class="td_02" width="30%">在途&nbsp;</td>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;回款编号</td>
          <td class="td_02" width="30%"><input type="text" name="mReturnEntity.no" id="mReturnEntity.no" style="width:120px;" maxlength="10"/>&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;回款方式</td>
          <td class="td_02">
			<html:select property="mReturnEntity.returnWay" value="${mid.mrreturnway}">
				<html:option value="">--请选择--</html:option>
				<html:option value="1">现金</html:option>
				<html:option value="2">支票</html:option>
				<html:option value="3">网银</html:option>
				<html:option value="4">电汇</html:option>
				<html:option value="5">银行承兑</html:option>
				<html:option value="6">承诺函</html:option>
				<html:option value="7">其它</html:option>
			</html:select>&nbsp;
          </td>
          <td class="td_01">&nbsp;凭证号</td>
          <td class="td_02"><input type="text" name="mReturnEntity.number" id="mReturnEntity.number"  value="${mid.mrnumber}" style="width:120px;" maxlength="20" />&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01">&nbsp;产品分类名称</td>
          <td class="td_02">${mid.productname}&nbsp;</td>
          <td class="td_01">&nbsp;客户名称</td>
          <td class="td_02">${mid.mrcustomername}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;回款日期</td>
          <td class="td_02"><input type="text" name="mReturnEntity.returnDate" id="mReturnEntity.returnDate" smaxlength="12"
									style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${mid.mrreturndate}" /></td>
          <td class="td_01">回款金额&nbsp;</td>
          <td class="td_02"><fmt:formatNumber value="${mid.mrmoney}" pattern="#,##0.00#"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01">&nbsp;特殊说明</td>
          <td colspan="3" class="td_02"><textarea name="mReturnEntity.text" id="mReturnEntity.text" value="" cols="100" rows="4">${mid.mrtest}</textarea>
          </td>
        </tr>
      </table>
      <br />
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="center" valign="bottom">
          	<a id="submitBtn" href="#"><img src="${ctx}/images/btnDZ.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<a id="fh" href="javascript:window.location ='${ctx}/mreturn.do'"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a>
          </td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
