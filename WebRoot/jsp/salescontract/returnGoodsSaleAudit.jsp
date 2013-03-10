<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退货单评审</title>
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
				$.ajaxSetup({ 
  					async: false 
  				});
				//各行换色
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
				//返回
				$('#backBtn').click(function(){
					document.location.href='saleReturns.do';
				});
				//判断销售总监评审意见
				if('${roleId}'==5)
				{	
					$('#sellMajIdea0').attr("disabled",false);
					$('#sellMajIdea1').attr("disabled",false);
					$('#sellMajText').html('<textarea name="sellMajText" id="sellMajText1" cols="60" rows="3"></textarea>');
					$('#sellMajName').text('签字：');
					$('#sellMajDate').text('日期：');
				}else{
					if('${returnGoods.sellMajIdea}'=='0'){
						$('#sellMajIdea0').attr("checked",true);
					}
					if('${returnGoods.sellMajIdea}'=='1'){
						$('#sellMajIdea1').attr('checked',true);
					}
				}
				
				//运营总监评审意见
				if('${roleId}'==17){
					$('#opeMajIdea0').attr("disabled",false);
					$('#opeMajIdea1').attr("disabled",false);
					$('#opeMajText').html('<textarea name="opeMajText" id="opeMajText1" cols="60" rows="3"></textarea>');
					$('#opeMajName').text('签字：');
					$('#opeMajDate').text('日期：');
				}else{
					if('${returnGoods.opeMajIdea}'=='0'){
					$('#opeMajIdea0').attr("checked",true);
					}
					if('${returnGoods.opeMajIdea}'=='1'){
						$('#opeMajIdea1').attr('checked',true);
					}
				}
				
				//库房管理员评审意见
				var returnGoodsArray = new Array();
				returnGoodsArray = '${returnGoods.stkMajIdea}'.split('');
				if(returnGoodsArray[0]=='0'){
					$('#skIdea00').attr('checked','true');
				}else if(returnGoodsArray[0]=='1'){
					$('#skIdea00').attr('checked','true');
				}
				if(returnGoodsArray[1]=='0'){
					$('#skIdea10').attr('checked','true');
				}else if(returnGoodsArray[1]=='1'){
					$('#skIdea11').attr('checked','true');
				}
				if(returnGoodsArray[2]=='0'){
					$('#skIdea20').attr('checked','true');
				}else if(returnGoodsArray[2]=='1'){
					$('#skIdea21').attr('checked','true');
				}
				//控制单选
				$('#sellMajIdea0').click(function(){
					$('#sellMajIdea1').attr('checked',false);
					$(this).attr('checked',true);
					$('#auditIdea').val('true');
				});
				$('#sellMajIdea1').click(function(){
					$('#sellMajIdea0').attr('checked',false);
					$(this).attr('checked',true);
					$('#auditIdea').val('true');
				});
				$('#opeMajIdea0').click(function(){
					$('#opeMajIdea1').attr('checked',false);
					$(this).attr('checked',true);
					$('#auditIdea').val('true');
				});
				$('#opeMajIdea1').click(function(){
					$('#opeMajIdea0').attr('checked',false);
					$(this).attr('checked',true);
					$('#auditIdea').val('true');
				});
				//提交
				$('#btnTG').click(function(){
					if($('#auditIdea').val()!='true'){
						alert('请选择评审意见');
						return false;
					}
					var textarea="";
					if('${roleId}'==5){
						textarea = $.trim($("#sellMajText1").val());
						$("#sellMajText1").val(textarea);
					}
					if('${roleId}'==17){
						textarea = $.trim($("#opeMajText1").val());
						$("#opeMajText1").val(textarea);
					}
					if(textarea.length > 200){
						alert("补充说明不可超过200个汉字！");
						return false;
					}
					$('#auditResult').val('1');
					$.post("auditReturnGoodsSale.do",$("#returnGoodsAuditForm").serializeArray() ,waitResult,"html");
				});
				$('#btnWTG').click(function(){
					if($('#auditIdea').val()!='true'){
						alert('请选择评审意见');
						return false;
					}
					var textarea;
					if('${roleId}'==5){
						textarea = $.trim($("#sellMajText1").val());
						$("#sellMajText1").val(textarea);
					}
					if('${roleId}'==17){
						textarea = $.trim($("#opeMajText1").val());
						$("#opeMajText1").val(textarea);
					}
					if(textarea==null||textarea==""){
						alert("补充说明不可为空！");
						return false;
					}else if(textarea.length > 200){
						alert("补充说明不可超过200个汉字！");
						return false;
					}
					$('#auditResult').val('0');
					$.post("auditReturnGoodsSale.do",$("#returnGoodsAuditForm").serializeArray() ,waitResult,"html");
				});
				
			});
			
	//提交后获取后台新建结果
	function waitResult(data){
	  if(data=='true'){
	  	document.location.href='saleReturns.do';
	  }else if(data == 'validate'){
	  	alert('评审意见必须全为符合！');
	  }else{
	    alert("评审失败！");
	  }
	}
	
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退货管理 &gt;&gt; 退货单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${returnGoods.productTypeName}&nbsp;</td>
          <td height="18" class="td_01" width="20%" >客户名称</td>
          <td class="td_02" width="30%">${returnGoods.customeName}&nbsp;</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">发货数</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">已退货数</th>
          <th nowrap="nowrap">退货数</th>
          <th nowrap="nowrap">退货金额</th>
        </tr>
               <logic:present name="list">
			<logic:iterate id="ReturnsProduct" name="list" indexId="index">
		        <tr>
		          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${index+1}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productCode}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productName}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productType}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productUnit}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${ReturnsProduct.price}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.ffcount}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${ReturnsProduct.ffMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${ReturnsProduct.zdmoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${ReturnsProduct.kpmoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap">${ReturnsProduct.yBackCount}&nbsp;</td>
		          <td nowrap="nowrap" style=" width:66px">${ReturnsProduct.thcount}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; padding-right:12px; width:88px;"><fmt:formatNumber value="${ReturnsProduct.thmoney}" pattern="#,##0.00#"/></td>
		        </tr>
	       </logic:iterate>
	    </logic:present>
        <tr>
          <td colspan="11" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">退货金额合计</td>
          <td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><fmt:formatNumber value="${returnGoods.money}" pattern="#,##0.00#"/>元</td>
        </tr>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%"h eight="18px">库房名称</td>
          <td colspan="3" class="td_02">${returnGoods.stockroomName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02">${returnGoods.untiName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">收货地址</td>
          <td colspan="3" class="td_02">${returnGoods.address}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">联系人</td>
          <td class="td_02" width="30%" >${returnGoods.linkman}&nbsp;</td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%" >${returnGoods.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">电话</td>
          <td class="td_02" width="30%" >${returnGoods.tel}&nbsp;</td>
          <td class="td_01" width="20%">手机</td>
          <td class="td_02" width="30%" >${returnGoods.mobile}&nbsp;</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(returnGoods.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
      <form action="auditReturnGoodsSale.do" name="returnGoodsAuditForm" id="returnGoodsAuditForm" method="post" >
        <tr>
          <td height="20px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
          <td width="320">&nbsp;</td>
          <td width="150" height="20px"><input type="checkbox" name="sellMajIdea1" id="sellMajIdea1" disabled="true" value="1" />
            同意
            &nbsp;&nbsp;
            <input type="checkbox" name="sellMajIdea0" id="sellMajIdea0" disabled="true" value="0" />
            不同意</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                <td style="padding:5px 0 5px 0;" id="sellMajText" name="sellMajText">${tf:replaceBr(returnGoods.sellMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px" id="sellMajName">签字：${returnGoods.sellMajName}</td>
          <td id="sellMajDate">日期：${returnGoods.sellMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td height="20px"><input type="checkbox" name="opeMajIdea1" id="opeMajIdea1" value="1" disabled="true" />
            同意
            &nbsp;&nbsp;
            <input type="checkbox" name="opeMajIdea0" id="opeMajIdea0" value="0" disabled="true" />
            不同意</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                <td style="padding:5px 0 5px 0;" id="opeMajText" name="opeMajText">${tf:replaceBr(returnGoods.opeMajText)}</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px" id="opeMajName">签字：${returnGoods.opeMajName}</td>
          <td id="opeMajDate">日期：${returnGoods.opeMajDate}</td>
        </tr>
        <input type="hidden" id="auditResult" name="auditResult" value="" />
        <input type="hidden" id="auditIdea" name="auditIdea" value="" />
        <input type="hidden" id="id" name="id" value="${returnGoods.id}" />
      </table></form></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	<a id="btnTG"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<a id="btnWTG"><img src="${ctx}/images/btnWTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<a id="backBtn"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a>
    </td>
    <td></td>
  </tr>
</table>
</body>
</html>
