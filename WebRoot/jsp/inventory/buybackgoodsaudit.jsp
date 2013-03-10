<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>返厂单（评审）</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
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
				if(($('#stkAdIdea1').attr('checked')==false && $('#stkAdIdea2').attr('checked')==false)
					|| ($('#stkAdIdea3').attr('checked')==false && $('#stkAdIdea4').attr('checked')==false)
					|| ($('#stkAdIdea5').attr('checked')==false && $('#stkAdIdea6').attr('checked')==false)){
					alert("请选择评审意见！");
				}else if($('#stkMajText').val()==""){
					alert("请填写补充说明！");
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

function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
		$(function(){
		//产品总监评审意见
	        if('${ProMajIdea[0]}'=='1'){
	        	$('#proMajIdea1').attr("checked",'true');
	        }
	        if('${ProMajIdea[0]}'=='0'){
	        	$('#proMajIdea0').attr("checked",'true');
	        }
	        if('${ProMajIdea[1]}'=='1'){
	        	$('#proMajIdea2').attr("checked",'true');
	        }
	        if('${ProMajIdea[1]}'=='0'){
	        	$('#proMajIdea3').attr("checked",'true');
	        }
		//采购主管评审意见
	        if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
		//运营总监评审意见
	        if('${assessDto.opeMajIdea}'=='1'){
	        	$('#opeMajIdea1').attr("checked",'true');
	        }
	        if('${assessDto.opeMajIdea}'=='0'){
	        	$('#opeMajIdea0').attr("checked",'true');
	        }
		});
		
		
		
	 $(function(){ 
		  //隐藏姓名和身份证号码 
		 if(${modifyBuyBackGoodsInfoDto.transportWay== 1}){ 
						  $("#nameAndId").show();  
					}else{
						$("#nameAndId").hide(); 
		} 
		 
	 });

</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<html:form action="getStockBuyBackGoodsAudit.do" styleId="auditkForm">
<input type="hidden" id="assessResult" value="" name = "assessResult"/>
<input type="hidden" id="buyBackAssess.id" value="${assessDto.id}" name="buyBackAssess.id"/>
<input type="hidden" id="buyBackAssess.buyManId" value="${assessDto.buyManId}" name="buyBackAssess.buyManId"/>
<input type="hidden" id="buyBackAssess.opeMajId" value="${assessDto.opeMajId}" name="buyBackAssess.opeMajId"/>
<input type="hidden" id="buyBackAssess.stkAdmId" value="${assessDto.stkAdmId}" name="buyBackAssess.stkAdmId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 发货管理 &gt;&gt; 返厂单查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">产品分类名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.productTypeName}</td>
          <td class="td_01" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierName}</td>
        </tr>
        <tr>
          <td class="td_01" height="18">产品合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.productContractCode}</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.companyContractCode}</td>
        </tr>
        <tr>
          <td class="td_01" height="18">库房选择</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.stockRoomName}</td>
          <td class="td_01">入库单号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.instockId}</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
		  <c:if test="${roleId!=12&&roleId!=13}">
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
		  </c:if>
          <th nowrap="nowrap">已返厂数</th>
          <th nowrap="nowrap">返厂数</th>
		  <c:if test="${roleId!=12&&roleId!=13}">
          <th nowrap="nowrap">返厂金额</th>
		  </c:if>
          <th nowrap="nowrap">库存数</th>
        </tr>
        <logic:iterate id="backGoogsCreateInfo" name="backGoogsCreateInfoList" indexId="indexId">
        <tr>
          <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${indexId+1}&nbsp;</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.productId}&nbsp;</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.productName}&nbsp;</td>
          <td nowrap="nowrap" width="64">${backGoogsCreateInfo.productType}&nbsp;</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.productUnit}&nbsp;</td>
		  <c:if test="${roleId!=12&&roleId!=13}">
          <td nowrap="nowrap" >${backGoogsCreateInfo.price}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${backGoogsCreateInfo.inStockMoney}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${backGoogsCreateInfo.paymentMoney}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;" >${backGoogsCreateInfo.receiveMoney}&nbsp;</td>
		  </c:if>
          <td nowrap="nowrap" style="text-align:right;">${backGoogsCreateInfo.alreadyBackCount}&nbsp;</td>
          <td nowrap="nowrap" width="82px" style="text-align:right;">${backGoogsCreateInfo.backCount}&nbsp;</td>
		  <c:if test="${roleId!=12&&roleId!=13}">
          <td nowrap="nowrap" style="text-align:right;">${backGoogsCreateInfo.backMoney}&nbsp;</td>
		  </c:if>
          <td nowrap="nowrap" style="text-align:right;">${backGoogsCreateInfo.stockNum}&nbsp;</td>
        </tr>
        </logic:iterate>
		<c:if test="${roleId!=12&&roleId!=13}">
        <tr>
          <td height="18" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="9" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">返厂金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF">${modifyBuyBackGoodsInfoDto.money}&nbsp;</td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><span style=" border:1px solid #FFFFFF; background-color:#FFFFFF">元</span></td>
        </tr>
		</c:if>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" >${modifyBuyBackGoodsInfoDto.receiveName}</td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" >${modifyBuyBackGoodsInfoDto.supplierAddress}</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierLinkman}</td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierPostcode}</td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.supplierTel}</td>
          <td class="td_01">手机</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.supplierMobile}</td>
        </tr>
        <tr>
          <td class="td_01" height="18">要求发货日期</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.requestDate}</td>
          <td class="td_01">货运方式</td>
          <td class="td_02" >
                    <logic:equal value="1" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		          自提
            	    </logic:equal>
				    <logic:equal value="2" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           快递
            	    </logic:equal>
            	    <logic:equal value="3" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           汽运
            	    </logic:equal>
            	    <logic:equal value="4" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           铁运
            	    </logic:equal>
            	    <logic:equal value="5" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           航空
            	    </logic:equal>
                    <logic:equal value="6" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           海运
            	    </logic:equal>
            	    <logic:equal value="7" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           中铁
            	    </logic:equal>
            	    <logic:equal value="8" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           市内送货
            	    </logic:equal>
            	    <logic:equal value="9" property="transportWay" name="modifyBuyBackGoodsInfoDto">
            		           市内快递
            	    </logic:equal>
          &nbsp;</td>
        </tr>
        <tr id="nameAndId">
          <td class="td_01" height="18">提货人姓名</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.takeName}</td>
          <td class="td_01">身份证号码</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.takeNumber}</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="20px" colspan="2" >产品总监评审意见：</td>
        </tr>
        <tr>
          <td nowrap="nowrap">1.返厂数量是否符合</td>
          <td height="20px" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.proMajIdea" id="proMajIdea1" value="1" disabled="disabled"
              <c:if test='${assessDto.proMajIdea == "1"}'>checked="checked"</c:if>  
          />符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.proMajIdea" id="proMajIdea0" value="0" disabled="disabled"
              <c:if test='${assessDto.proMajIdea == "0"}'>checked="checked"</c:if>  
            />不符合
        </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">2.返厂地址是否符合</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.proMajIdea1" id="proMajIdea2" value="1" disabled="disabled"
              <c:if test='${assessDto.proMajIdea == "1"}'>checked="checked"</c:if> 
          />符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.proMajIdea1" id="proMajIdea3" value="0" disabled="disabled"
              <c:if test='${assessDto.proMajIdea == "0"}'>checked="checked"</c:if>  
            />不符合
            </td>
        </tr>
        
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.proMajText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.proMajName}</td>
          <input type="hidden" name="buyBackAssess.proMajName"  value="${assessDto.proMajName}"/>
          <td>日期：${assessDto.proMajDate}</td>
          <input type="hidden" name="buyBackAssess.proMajDate"  value="${assessDto.proMajDate}"/>
        </tr>
       
        
        
        
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.buyManIdea" id="buyManIdea1" value="1" disabled="disabled"
              <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if> 
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.buyManIdea" id="buyManIdea0" value="0" disabled="disabled"
              <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if>  
            />不同意
            </td>
        </tr>
        
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                 ${tf:replaceBr(assessDto.buyManText)}
              &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.buyManName}</td>
          <input type="hidden" name="buyBackAssess.buyManName"  value="${assessDto.buyManName}"/>
          <td>日期：${assessDto.buyManDate}</td>
          <input type="hidden" name="buyBackAssess.buyManDate"  value="${assessDto.buyManDate}"/>
        </tr>
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        
        
        
        
        <tr>
          <td height="20px" colspan="2" >运营总监评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.opeMajIdea" id="opeMajIdea1" value="1" disabled="disabled"
              <c:if test='${assessDto.opeMajIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.opeMajIdea" id="opeMajIdea0" value="0" disabled="disabled"
              <c:if test='${assessDto.opeMajIdea == "0"}'>checked="checked"</c:if>  
          />不同意
            </td>
        </tr>
        
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.opeMajText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.opeMajName}</td>
          <input type="hidden" name="buyBackAssess.opeMajName"  value="${assessDto.opeMajName}"/>
          <td>日期：${assessDto.opeMajDate}</td>
          <input type="hidden" name="buyBackAssess.opeMajDate"  value="${assessDto.opeMajDate}"/>
        </tr>
        
        
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
           
        <tr>
          <td height="20px" colspan="2" >库房管理员意见：</td>
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
			<input type="hidden" name="id" value="${modifyBuyBackGoodsInfoDto.id}"/>
			<input type="hidden" name="audit" value="true" />
			<input type="hidden" id="stkMajIdea" name="stkMajIdea"/>
			<input type="hidden" name="orderId" value="${modifyBuyBackGoodsInfoDto.id}"/>
      </table>
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" id="btnTG"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/stockOrder.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
</html:form>
</body>
</html>
