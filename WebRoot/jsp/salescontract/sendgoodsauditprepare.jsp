<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>备货评审</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
	
	function iderChecked(){
		var checkbox = $(":checkbox[value='1']:checked:enabled").length;
		var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
		$("#iderpd")[0].value=1;
		var textarea = $.trim($("#textarea").val().replace(/[\u3000]/g," "));
		$("#textarea").val(textarea);
		if(textarea.length>200){
			alert("补充说明不能大于200个字符！");
			return;
		}
		if(checkbox==1){
			submitChecked(checkbox,nocheckbox);
		}else{
			alert("评审意见必须全为符合！");
		}
	}
	function noiderChecked(){
		var checkbox = $(":checkbox[value='1']:checked:enabled").length;
		var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
		var textarea = $.trim($("#textarea").val().replace(/[\u3000]/g," "));
		$("#textarea").val(textarea);
		if(textarea == null || textarea == ""){
			alert("补充说明不可以为空！");
			return;
		}else if(textarea.length>200){
			alert("补充说明不能大于200个字符！");
			return;
		}
		$("#iderpd")[0].value=0;
		submitChecked(checkbox,nocheckbox);
	}
	function submitChecked(checkbox,nocheckbox){
		if(checkbox==0&&nocheckbox==0){
			alert("评审意见不可以为空！");
		}else{
			$.post("stockingAssessment.do",$("#stockingAssessmentForm").serializeArray() ,waitResult,"html");
		}
	}
	function waitResult(result){
		if(result == 'true'){
			window.location ='${ctx}/getSendGoodsList.do';
		}else{
			alert("备货单评审失败！");
		}
	}
	$(function() {
		$.ajaxSetup({ 
  			async: false 
  		}); 	
		$(":checkbox:enabled").each(function(i,assessItem){
			
			$(assessItem).bind("click", function() {
				
					if (assessItem.value == "1" ) {						
						$(":checkbox[name='" + assessItem.name + "'][value='0']").attr("checked", false);
					} else {						
						$(":checkbox[name='" + assessItem.name + "'][value='1']").attr("checked", false);
					}
						
			});//bind			
			
		});//each	
		
		if("${roleId}"==5){
			$('#AreaMajIdea1').attr("disabled",true);
			$('#AreaMajIdea0').attr("disabled",true);
			sendAreaMajIdeaCheck();
		}
		if("${roleId}"==9){
			$('#proMajIder1').attr("disabled",true);
			$('#proMajIder0').attr("disabled",true);
			opeMajIderCheck();
		}
	});
	//区域总监评审意见
	function sendAreaMajIdeaCheck(){
        if('${sge.sendAreaMajIdea}'=='1'){
        	$('#AreaMajIdea1').attr("checked",'true');
        }
        if('${sge.sendAreaMajIdea}'=='0'){
        	$('#AreaMajIdea0').attr("checked",'true');
        }
	}
	//销售总监评审意见
	function opeMajIderCheck(){
        if('${sge.sendProMajIdea}'=='1'){
        	$('#opeMajIder1').attr("checked",'true');
        }
        if('${sge.sendProMajIdea}'=='0'){
        	$('#opeMajIder0').attr("checked",'true');
        }
	}
</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style></head>

<body>
<form action="stockingAssessment.do" method="post" name="stockForm" id="stockingAssessmentForm" >
<input type="hidden" name="iderpd" value="1" id="iderpd"></input>
<input type="hidden" name="productTypeId" id="productTypeId" value="${sge.sendProductTypeId}"></input>
<input type="hidden" name="id" value="${sge.id}" id="id"></input>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 发货管理 &gt;&gt; 备货评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;备货单信息 </div>
   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">预计发货日期</td>
            <td class="td_02" >${sge.sendRequestDate}&nbsp;</td>
            <td class="td_01">
            <td class="td_02">
          </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">产品合同号</td>
            <td class="td_02">${sge.productContractCode}&nbsp;</td>
            <td class="td_01 STYLE1">公司合同号</td>
            <td class="td_02" >${sge.companyContarctCode}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">客户名称</td>
            <td class="td_02">${sge.sendCustomerName}&nbsp;</td>
            <td class="td_01">要求发货日期</td>
            <td class="td_02">${sge.requestSendDate}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">人员名称</td>
            <td class="td_02">${sge.userName}&nbsp;</td>
            <td class="td_01">人员区域</td>
            <td class="td_02">${sge.areaname}&nbsp;</td>
          </tr>
		  <tr>
			<td class="td_01" height="18px">项目名称</td>
			<td class="td_02">${sge.contractProName}&nbsp;</td>
			<td class="td_01">&nbsp;</td>
			<td class="td_02">&nbsp;</td>
		  </tr>
      </table>
    <br />
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" height="18px">付款方式</td>
				<td class="td_02">
					<c:if test="${sge.paymentWay==0}">全部现结&nbsp;</c:if>
					<c:if test="${sge.paymentWay==1}">全部信用&nbsp;</c:if>
					<c:if test="${sge.paymentWay==2}">部分现结部分信用&nbsp;</c:if>
				</td>
				<c:if test="${sge.paymentWay==2}">
					<td class="td_01">现结金额</td>
					<td class="td_02" ><fmt:formatNumber value="${sge.cashMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				</c:if>
				<c:if test="${sge.paymentWay!=2}">
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</c:if>
				
			</tr>
			<c:if test="${sge.paymentWay==1||sge.paymentWay==2}">
				<tr>
					<td class="td_01" height="18px">分批方式</td>
					<td class="td_02" >
						<c:if test="${sge.batchWay==0}">不分批&nbsp;
							</td><td class="td_01">&nbsp;</td><td class="td_02">&nbsp;
						</c:if>
						<c:if test="${sge.batchWay==1}">分批&nbsp;
							</td>
							<td class="td_01">最大单批金额</td>
							<td class="td_02"><fmt:formatNumber value="${sge.batchMaxMoney}" pattern="#,##0.00#"/>&nbsp;
						</c:if>
					</td>
				</tr>
			</c:if>
		</table>
		<div style="width:100%; text-align:right">单位：元</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
				<tr>
					<th>信用类型名称</th>
					<th>项目名称</th>
					<th>帐期</th>
					<th>信用额度</th>
					<th>可用额度</th>
					<th>回款预收金额</th>
					<th>在途预收金额</th>
				</tr>
				<tr>
					<td height="18px">${sge.creditname}&nbsp;</td>
					<td>${sge.sendProjectName}&nbsp;</td>
					<td style="text-align:right">${sge.arterm}&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.climit}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.free}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.sendPrepayMoney}" pattern="#,##0.00#"/>&nbsp;</td>
					<td style="text-align:right"><fmt:formatNumber value="${sge.sendTransitMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				</tr>
			</table>
			<br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" height="18px">库房名称</td>
                <td class="td_02">${sge.sroomName}&nbsp;</td>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">${sge.ptName}&nbsp;</td>
            </tr>
   	  </table>
	  <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style=" border:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="40" style=" border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售数</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">销售金额</th>
				<th nowrap="nowrap">备货数</th>
				<th nowrap="nowrap">其它库房备货数</th>
				<th nowrap="nowrap">本库房备货数</th>
				<th nowrap="nowrap">备货金额</th>
				<th nowrap="nowrap">退货数</th>
				<th nowrap="nowrap">备货可用数</th>
			</tr>
				<logic:present name="list">
				<logic:iterate id="ssp" name="list" indexId="index">
					<tr>
						<td nowrap="nowrap" style=" border-left:1px solid #c2c2c2;" height="18px">${index+1}</td>
						<td nowrap="nowrap" >${ssp.productcode}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.productname}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.producttype}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.productunit}&nbsp;</td>
						<td nowrap="nowrap" width="130px" style="text-align:right">${ssp.count}&nbsp;</td>
						<td nowrap="nowrap" width="130px" style="text-align:right"><fmt:formatNumber value="${ssp.price}" pattern="#,##0.00#"/>&nbsp;</td>
						<td nowrap="nowrap" width="80px" style="text-align:right"><fmt:formatNumber value="${ssp.money}" pattern="#,##0.00#"/>&nbsp;</td>
						<td width="80px" nowrap="nowrap" style="text-align:right">${ssp.yfhnum}&nbsp;</td>
						<td width="80px" nowrap="nowrap" style="text-align:right">${ssp.qtkfnum}&nbsp;</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${ssp.bkfnum}&nbsp;</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${ssp.bfmoney}" pattern="#,##0.00#"/>&nbsp;</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${ssp.htthsnum}&nbsp;</td>
						<td nowrap="nowrap" width="80px" style="text-align:right">${ssp.fhkynum}&nbsp;</td>
					</tr>
				</logic:iterate>
	   		 </logic:present>
			<tr>
				<td colspan="3" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="8" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 备货金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
					<fmt:formatNumber value="${sge.money}" pattern="#,##0.00#"/>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
				<td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
			</tr>
		</table>
   	  <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04" style="padding:5px 100px 5px 10px">&nbsp;${tf:replaceBr(sge.sendtext)}</td>
        </tr>
    </table>
     <br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
                <td height="20px" colspan="2" >区域总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="AreaMajIdea" id="AreaMajIdea1" value="1"/>
                  同意
                  &nbsp;&nbsp;
                          <input type="checkbox" name="AreaMajIdea" id="AreaMajIdea0" value="0"/>
                  不同意 </td>
              </tr>
              <tr>
                <td colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <c:if test="${roleId == 9}">
	                    <tr>
	                      <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
	                      <td style="padding:5px 0 5px 0;"><textarea name="textarea" id="textarea" cols="60" rows="3"></textarea></td>
	                    </tr>
                    </c:if>
                    <c:if test="${roleId != 9}">
	                    <tr>
	                      <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
	                      <td style="padding:5px 0 5px 0;">${sge.sendAreaMajText}</td>
	                    </tr>
                    </c:if>
                </table></td>
              </tr>
              <tr>
                <td height="20px" colspan="2" >&nbsp;</td>
              </tr>
              <tr>
                <td height="20px" colspan="2" >销售总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="proMajIder" id="proMajIder1" value="1"/>
                  同意
                  &nbsp;&nbsp;
                          <input type="checkbox" name="proMajIder" id="proMajIder0" value="0"/>
                  不同意 </td>
              </tr>
				<tr>
                <td colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                     <c:if test="${roleId == 5}">
	                    <tr>
	                      <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
	                      <td style="padding:5px 0 5px 0;"><textarea name="textarea" id="textarea" cols="60" rows="3"></textarea></td>
	                    </tr>
                    </c:if>
                     <c:if test="${roleId != 5}">
	                    <tr>
	                      <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
	                      <td style="padding:5px 0 5px 0;">${sge.opeMajText}</textarea></td>
	                    </tr>
                    </c:if>
                </table></td>
              </tr>
            </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
	    <a id="tj" href="javascript:iderChecked();"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <a id="btj" href="javascript:noiderChecked();"><img src="${ctx}/images/btnWTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <a id="fh" href="javascript:window.location ='${ctx}/getSendGoodsList.do'"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
