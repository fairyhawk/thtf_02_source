<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退款修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>

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
	      var msg = "${err}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
		<!--
			$(document).ready(function(){
				if($("#table1")){
					$("#table1 tr:odd").addClass("treven");
					$("#table1 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
				
				
			});
		//-->
		</script> 
				
<script language="JavaScript"> 
	// 根据联系人Id获取联系人的相关信息
	function  getLinkMsgByLinkManId() {
		var customerLinkmanId = $("#customerLinkmanId option:selected").val();
		if (customerLinkmanId != ""){
			$.getJSON("${ctx}/getLinkManById.do?customerLinkmanId=" + customerLinkmanId, function(LinkManMsg){
				 
				$("#tel").empty();
				$("#tel").append("<span>" + LinkManMsg[0].tel + "</span>&nbsp;");
				$("#fax").empty();
				$("#fax").append("<span>" + LinkManMsg[0].fax + "</span>&nbsp;");
				 
			});
		}else{
			$("#tel").empty();
			$("#tel").append( "&nbsp;");
			$("#fax").empty();
			$("#fax").append("&nbsp;");
			
		}
		
	};
	//提交
	function sub(subFlag){
	
		$("#subFlag").attr("value", subFlag); 
		$("#paraTel").attr("value", document.getElementById('tel').innerText); 
		$("#paraFax").attr("value", document.getElementById('fax').innerText);  
		
		if ((subFlag=="save") || (subFlag="submit")){
		  $("#modifySellBackForm").submit();
		}else{
		  alert("非法提交！");
		} 
	}; 
	//画面验证
	//产品分类可退款金额
	jQuery.validator.addMethod("backMoneyCheckProCanBackMoney",
    function(value, element) {  
          return Number(value)<=Number($('#proCanBackMoney').val()); 
    });	
	//回款可退金额
	jQuery.validator.addMethod("backMoneyCheckMreturnCanBackMoney",
    function(value, element) {  
          return Number(value)<=Number($('#mreturnCanBackMoney').val()); 
    });
    //退款金额大于0
    jQuery.validator.addMethod("backMoneyCheckZero",
    function(value, element) {  
          return Number(value)>0; 
    });
    
    
 	$(document).ready(function() {
			$("#modifySellBackForm").validate({
				rules: { 
					"modifySellBackForm.customerLinkmanId":"required",
					"modifySellBackForm.backWay":"required", 
					"modifySellBackForm.money":{required:true,number:true,backMoneyCheckProCanBackMoney:true,
					backMoneyCheckMreturnCanBackMoney:true,backMoneyCheckZero:true}
				},
				messages: {
					"modifySellBackForm.customerLinkmanId":"请选择联系人",
					"modifySellBackForm.backWay":"请选择退款方式", 
					"modifySellBackForm.money":{required:"请输入退款金额",number:"退款金额必须是数字",
					"backMoneyCheckProCanBackMoney":"不能大于产品分类可退款金额",
					"backMoneyCheckMreturnCanBackMoney":"不能大于回款可退金额",
					"backMoneyCheckZero":"退款金额需大于零"
					}
				} 
			});
	})
	 
	 	 	
</script>	


</head>
<body>
<html:form action="modifySellBack.do" styleId="modifySellBackForm">
<input type="hidden" name="modifySellBackForm.id" value="${sellBackId}">
<input type="hidden" name="modifySellBackForm.sellMajId" value="${assessDto.sellMajId}">
<input type="hidden" name="modifySellBackForm.opeMajId" value="${assessDto.opeMajId}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退款管理 &gt;&gt; 退款修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;客户信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01">客户名称</td>
          <td class="td_02">${assessDto.customerName}&nbsp;</td>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;联系人</td>
          <td class="td_02">
          
          	<html:select property="modifySellBackForm.customerLinkmanId" styleId="customerLinkmanId" value="${assessDto.customerLinkmanId}" style="width:126px" onchange="getLinkMsgByLinkManId();" >
				<html:option value="">--请选择--</html:option>
				<logic:iterate id="customerLinkmanEntity" name="customerLinkmanList" > 
					<html:option value="${customerLinkmanEntity.id}">${customerLinkmanEntity.name}</html:option> 
				</logic:iterate>		 
			</html:select>      
           
          </td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">省份</td>
          <td class="td_02" width="30%">${assessDto.province}&nbsp;</td>
          <td class="td_01" width="20%">电话</td>
          <td class="td_02" width="30%"  id="tel">${assessDto.tel}&nbsp;</td>
          <input type="hidden" name="paraTel" id="paraTel"/>
        </tr>
        <tr>
          <td class="td_01" height="18px">城市</td>
          <td class="td_02">${assessDto.city}&nbsp;</td>
          <td class="td_01">传真</td>
          <td class="td_02"  id="fax">${assessDto.fax}&nbsp;</td>
          <input type="hidden" name="paraFax" id="paraFax"/>
        </tr>
        <tr>
          <td class="td_01" height="18px">汇款银行名称</td>
          <td class="td_02">${assessDto.remitBankName}&nbsp;</td>
          <td class="td_01">汇款银行帐号</td>
          <td class="td_02">${assessDto.remitBankAccount}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;回款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">回款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.mreturnMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">指定金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.detailMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">合同预收金额</td>
          <td class="td_02"><fmt:formatNumber value="${sellBackDto.contMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01">产品分类预收金额</td>
          <td class="td_02"><fmt:formatNumber value="${sellBackDto.prodMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">已退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${sellBackDto.backSuccessmoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01">回款可退款金额</td>
          <td class="td_02"><fmt:formatNumber value="${sellBackDto.mreturnCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="mreturnCanBackMoney" id="mreturnCanBackMoney" value="${sellBackDto.mreturnCanBackMoney}">
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品分类信用信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">产品分类预收金额合计</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.prodMoneySum}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">产品分类可退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.proCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <input type="hidden" name="proCanBackMoney" id="proCanBackMoney" value="${sellBackDto.proCanBackMoney}">
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
        <tr>
          <th nowrap="nowrap" >产品分类名称</th>
          <th nowrap="nowrap" >信用类型名称</th>
          <th nowrap="nowrap">项目名称</th>
          <th nowrap="nowrap">帐期</th>
          <th nowrap="nowrap">信用额度</th>
          <th nowrap="nowrap">已用金额</th>
        </tr>
       <logic:iterate id="customerProInfoListDto" name="customerProInfoListDtos" indexId="indexId"> 
         <tr>   
      	  <td nowrap="nowrap" height="18px">${customerProInfoListDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${customerProInfoListDto.creditTypeName}&nbsp;</td>
          <td nowrap="nowrap" >${customerProInfoListDto.projectName}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;">${customerProInfoListDto.arterm}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.climit}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.canUseLimit}" pattern="#,##0.00"/>&nbsp;</td>
	          
         </tr>
       </logic:iterate>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;退款方式</td>
          <td class="td_02" width="30%">
          
           <html:select property="modifySellBackForm.backWay" value="${assessDto.backWay}" style=" width:126px">
				<html:option value="">--请选择--</html:option>
				 <html:option value="2">支票</html:option>
			    <html:option value="3">网银</html:option>
			    <html:option value="4">电汇</html:option>
			    <html:option value="5">银行承兑</html:option>
			    <html:option value="7">其它</html:option>
			</html:select>
			
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;退款金额</td>
          <td class="td_02" width="30%"><input type="text" name="modifySellBackForm.money" id="modifySellBackForm.money" style="width:120px;" value="${assessDto.money}"/>
            元</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" ><textarea name="modifySellBackForm.text" id="modifySellBackForm.text" cols="100" rows="4" >${assessDto.text}</textarea>
          </td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="18px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
          <td width="320">&nbsp;</td>
          <td width="150" height="20px"><input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled"
          	<c:if test="${assessDto.sellMajIdea==1}" > checked="checked"  </c:if> />
          
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled"
            <c:if test="${assessDto.sellMajIdea==0}" > checked="checked"  </c:if> />
             
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.sellMajText)}
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.sellMajName}</td>
          <td>日期：${assessDto.sellMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td height="20px"><input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled"
            <c:if test="${assessDto.opeMajIdea==1}"> checked="checked" </c:if>
          />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled"
              <c:if test="${assessDto.opeMajIdea==0}" > checked="checked"  </c:if> />
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.opeMajText)}
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.opeMajName}</td>
          <td>日期：${assessDto.opeMajDate}</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
        <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <a href="${ctx}/getSellBackList.do"><img src="${ctx}/images/btnBack.gif" /></a>
        <input type="hidden" id="subFlag" value="${subFlag}" name ="subFlag"/>
    </td>
    <td></td>
  </tr>
</table>
</body>
</html:form>
</html>
