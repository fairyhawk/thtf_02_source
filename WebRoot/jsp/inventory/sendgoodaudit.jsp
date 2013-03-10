<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货单评审</title>
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

				//控制单选
				$('#stkAdIdea1').click(function(){
					$('#stkAdIdea2').attr('checked',false);
				});
				$('#stkAdIdea2').click(function(){
					$('#stkAdIdea1').attr('checked',false);
				});

				$('#stkAdIdea3').click(function(){
					$('#stkAdIdea4').attr('checked',false);
				});
				$('#stkAdIdea4').click(function(){
					$('#stkAdIdea3').attr('checked',false);
				});

				$('#stkAdIdea5').click(function(){
					$('#stkAdIdea6').attr('checked',false);
				});
				$('#stkAdIdea6').click(function(){
					$('#stkAdIdea5').attr('checked',false);
				});

				//提交
				$('#btnTG').click(function(){ 
					checkSubmit();
				}); 

				if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}
			});

			//提交验证
			function checkSubmit(type){  
				var textarea = $.trim($("#stkMajText").val().replace(/[\u3000]/g," ")); 
				$("#stkMajText").val(textarea);
				if(($('#stkAdIdea1').attr('checked')==false && $('#stkAdIdea2').attr('checked')==false)
					|| ($('#stkAdIdea3').attr('checked')==false && $('#stkAdIdea4').attr('checked')==false)
					|| ($('#stkAdIdea5').attr('checked')==false && $('#stkAdIdea6').attr('checked')==false)){
					alert("请选择评审意见！");
				}else if(textarea=="" ||textarea ==null){
					alert("请填写补充说明！");
				}else if(textarea.length >200){
					alert("补充说明不能大于200个汉字！");
				}else{
					var str = "";
					if($('#stkAdIdea1').attr('checked')==false){
						str = str + "0";
					}else{
						str = str + "1";
					}
					if($('#stkAdIdea3').attr('checked')==false){
						str = str + "0";
					}else{
						str = str + "1";
					}
					if($('#stkAdIdea5').attr('checked')==false){
						str = str + "0";
					}else{
						str = str + "1";
					}
					$('#stkMajIdea').val(str); 
					$("#auditkForm").submit();  
				} 
				
			}
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style></head>

<body>
<html:form method="post" action="stockSendgoodsAudit" styleId="auditkForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt 发货管理 &gt;&gt; 发货单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">产品合同号</td>
            <td class="td_02" width="30%">${sge.productContractCode}</td>
            <td class="td_01" width="20%">公司合同号</td>
            <td class="td_02" width="30%">${sge.companyContarctCode}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">客户名称</td>
            <td class="td_02">${sge.customerName}</td>
            <td class="td_01">要求发货日期</td>
            <td class="td_02">${sge.requestSendDate}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">人员名称</td>
            <td class="td_02">${sge.userName}</td>
            <td class="td_01">人员区域</td>
            <td class="td_02">${sge.areaname}</td>
          </tr>
      </table>
    <br />
	<c:if test="${roleId!=12&&roleId!=13}">
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" width="20%" height="18px">付款方式</td>
				<td class="td_02" width="30%">
					<c:if test="${sge.paymentWay==0}">全部现结</c:if>
					<c:if test="${sge.paymentWay==1}">全部信用</c:if>
					<c:if test="${sge.paymentWay==2}">部分现结部分信用</c:if>
				</td>
				<c:if test="${sge.paymentWay==2}">
					<td class="td_01">现结金额</td>
					<td class="td_02" ><fmt:formatNumber value="${sge.cashMoney}" pattern="#,##0.00#"/></td>
				</c:if>
				<c:if test="${sge.paymentWay!=2}">
					<td class="td_01"></td>
					<td class="td_02"></td>
				</c:if>
				
			</tr>
			<c:if test="${sge.paymentWay==1||sge.paymentWay==2}">
				<tr>
					<td class="td_01" height="18px">分批方式</td>
					<td  class="td_02" >
						<c:if test="${sge.batchWay==0}">不分批
							</td><td  class="td_01"></td><td class="td_02">
						</c:if>
						<c:if test="${sge.batchWay==1}">分批
							</td>
							<td class="td_01">单批最大金额</td>
							<td  class="td_02" ><fmt:formatNumber value="${sge.batchMaxMoney}" pattern="#,##0.00#"/>
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
					<th>帐期(天)</th>
					<th>信用额度</th>
					<th>可用额度</th>
					<th>回款预收金额</th>
					<th>在途预收金额</th>
				</tr>
				<tr>
					<td height="18px">${sge.creditname}&nbsp;</td>
					<td>${sge.sendProjectName}&nbsp;</td>
					<td>${sge.arterm}&nbsp;</td>
					<td>${sge.climit}&nbsp;</td>
					<td>${sge.free}&nbsp;</td>
					<td style="text-align:right">${sge.sendPrepayMoney}&nbsp;</td>
					<td style="text-align:right">${sge.sendTransitMoney}&nbsp;</td>
				</tr>
			</table>
			</c:if>
			<br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" height="18px" width="20%">库房名称</td>
                <td class="td_02" width="30%">${sge.sroomName}</td>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">${sge.ptName}</td>
            </tr>
   	  </table>
<div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售数</th>
				<c:if test="${roleId!=12&&roleId!=13}">
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">销售金额</th>
				</c:if>
				<th nowrap="nowrap">已发货数</th>
				<th nowrap="nowrap">其它库房备货数</th>
				<th nowrap="nowrap">本库房备货数</th>
				<c:if test="${roleId!=12&&roleId!=13}">
				<th nowrap="nowrap">回款指定金额</th>
				<th nowrap="nowrap">在途指定金额</th>
				<th nowrap="nowrap">信用金额</th>
				<th nowrap="nowrap">开票金额</th>
				</c:if>
				<th nowrap="nowrap">本发货单退货数</th>
				<th nowrap="nowrap">发货数</th>
				<c:if test="${roleId!=12&&roleId!=13}">
				<th nowrap="nowrap">发货金额</th>
				</c:if>
				<th nowrap="nowrap">发货可用数</th>
			</tr>
			<logic:present name="svsplist">
				<logic:iterate id="svsp" name="svsplist" indexId="index">
					<tr>
						<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${index+1}</td>
						<td nowrap="nowrap" >${svsp.productcode}</td>
						<td nowrap="nowrap" >${svsp.productname}</td>
						<td nowrap="nowrap" >${svsp.producttype}</td>
						<td nowrap="nowrap" >${svsp.productunit}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.count}</td>
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.price}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.money}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.yfhnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.qtkfnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.bkfnum}</td>
						
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.hkzdjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.ztzdjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.xyjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.kpjemoney}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.thsnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.ffcount}</td>
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.ffmoney}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.fhkynum}</td>
					</tr>
				</logic:iterate>
	   		 </logic:present>
			 <c:if test="${roleId!=12&&roleId!=13}">
			<tr>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="15" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 发货金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
					<fmt:formatNumber value="${sge.money}" pattern="#,##0.00#"/>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
			</c:if>
		</table>
      <br />
        <div class="div_tiao"> &nbsp;&nbsp;货运信息 </div>
  	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td width="50%" class="td_01" height="18px">货物接收单位名称</td>
				<td colspan="3" class="td_02" >${sge.custname}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">发货地址</td>
				<td colspan="3" class="td_02" >${sge.address}</td>
			</tr>
			<tr>
				<td class="td_01" width="20%" height="18px">联系人</td>
				<td class="td_02" width="30%">${sge.linkman}</td>
				<td class="td_01" width="20%">邮编</td>
				<td class="td_02" width="30%">${sge.postcode}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">电话</td>
				<td class="td_02" >${sge.tel}</td>
				<td class="td_01">手机</td>
				<td class="td_02" >${sge.mobile}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">&nbsp;要求发货日期</td>
				<td class="td_02" >${sge.sendRequestDate}</td>
				<td class="td_01" >&nbsp;运货方式</td>
				<td class="td_02" >
				<c:if test="${sge.sendTransportWay==0}">无指定</c:if>
				<c:if test="${sge.sendTransportWay==1}">自提</c:if>
				<c:if test="${sge.sendTransportWay==2}">快递</c:if>
				<c:if test="${sge.sendTransportWay==3}">汽运</c:if>
				<c:if test="${sge.sendTransportWay==4}">铁运</c:if>
				<c:if test="${sge.sendTransportWay==5}">航空</c:if>
				<c:if test="${sge.sendTransportWay==6}">海运</c:if>
				<c:if test="${sge.sendTransportWay==7}">中铁</c:if>
				<c:if test="${sge.sendTransportWay==8}">市内送货</c:if>
				<c:if test="${sge.sendTransportWay==9}">市内快递</c:if>
				</td>
			</tr>
			<c:if test="${sge.sendTransportWay==1}">
			<tr>
				<td class="td_01" height="18px">提货人姓名</td>
				<td class="td_02" >${sge.sendTakeName}</td>
				<td class="td_01" >身份证号码</td>
				<td class="td_02">${sge.sendTakeNumber}</td>
			</tr>
			</c:if>
		</table>
  	    <br/>
        <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04"  style="padding:5px 100px 5px 5px">${tf:replaceBr(sge.sendtext)}&nbsp;</td>
        </tr>
    </table>
    <br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
			  <tr>
			  	<td height="20px" colspan="2">库房管理员意见：</td>
			  	</tr>
			  <tr>
			  	<td width="320" height="20px">1.产品规格是否符合</td>
			  	<td width="140" height="20px"> 
					<input type="checkbox" name="checkbox5" id="stkAdIdea1"/>符合&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="stkAdIdea2"/>不符合 
			  	</td>
			  </tr>
			  <tr>
			  	<td height="20px">2.产品质量是否符合</td>
			  	<td height="20px"> 
					<input type="checkbox" name="checkbox5" id="stkAdIdea3"/>符合&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="stkAdIdea4"/>不符合 
				</td>
			  </tr>
			  <tr>
			  	<td height="20px">3.产品数量是否符合</td>
			  	<td height="20px"> 
					<input type="checkbox" name="checkbox5" id="stkAdIdea5"/>符合&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="stkAdIdea6"/>不符合 
				</td>
			  </tr>
			  <tr>
			  	<td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;"><textarea name="stkMajText" id="stkMajText" cols="60" rows="3"></textarea></td>
                        </tr>
                	</table>
                </td>
			  </tr>
			  <tr>
				<td height="20px">签字：</td>
			  	<td>日期：</td>
		  	</tr>
			<input type="hidden" name="id" value="${sge.id}"/>
			<input type="hidden" name="audit" value="true" />
			<input type="hidden" id="stkMajIdea" name="stkMajIdea"/>
			<input type="hidden" name="orderId" value="${sge.id}"/>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
		<img src="${ctx}/images/btnSubmit.gif" width="65" height="20" id="btnTG"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="${ctx}/stockOrder.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
