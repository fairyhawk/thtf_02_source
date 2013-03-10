<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>返厂单评审</title>
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
				
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交		
				
			});
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
$(function(){
          //通过
		$('#pass').click(function(){
		   var textArea ;
		   var CheckCount=0; 
		   var checkbox = $(":checkbox[value='1']:checked:enabled").length;
		   var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
		
			if("${USERLOGIN.roleId}"==10){
				textArea = $('#proMajText').val(); 
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 2){
					alert("评审意见不可为空！");
					return false;
				}
				if(nocheckbox==0){
				$("#assessResult").val("pass");
			    buyBackAssess.submit();
			}else{
				alert("评审意见必须为同意！");
				return false;
			}
				
			}
			if("${USERLOGIN.roleId}"==11){
				textArea = $('#buyManText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					return false;
				}
				if(nocheckbox==0){
				$("#assessResult").val("pass");
			    buyBackAssess.submit();
			}else{
				alert("评审意见必须为同意！");
				return false;
			}
			}
			if("${USERLOGIN.roleId}"==17){
				textArea = $('#opeMajText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					return false;
				}
				if(nocheckbox==0){
				$("#assessResult").val("pass");
			    buyBackAssess.submit();
			}else{
				alert("评审意见必须为同意！");
				return false;
			}
			}
			
		});
		
		//未通过
		$('#nopass').click(function(){
			var result = submitCheck();
		    var textarea ="";
			if(result == false){
				return false;
			}
			
          if(${USERLOGIN.roleId=="10"}){
              textarea = document.getElementById('buyBackAssess.proMajText').value;
            }
          if(${USERLOGIN.roleId=="11"}){
              textarea = document.getElementById('buyBackAssess.buyManText').value;       
            }
           if(${USERLOGIN.roleId=="17"}){
              textarea = document.getElementById('buyBackAssess.opeMajText').value;       
            }
             textarea=$.trim(textarea);   
          if(textarea == null || textarea ==""){
               alert("补充说明不可为空！");
				return false;			                       									
			}
			$("#assessResult").val("nopass");
			buyBackAssess.submit();
		});
		
		//checkbox 单选
		$(":checkbox:enabled").each(function(i,assessItem){
			$(assessItem).bind("click", function() {
					if (assessItem.value == "1" ) {						
						$(":checkbox[name='" + assessItem.name + "'][value='0']").attr("checked", false);
					} else {						
						$(":checkbox[name='" + assessItem.name + "'][value='1']").attr("checked", false);
					}
			});//bind			
		});//each
		
		//判断checkbox是否可选  产品总监
		if("${USERLOGIN.roleId}"==10){
			$('#buyManIdea1').attr("disabled",true);
			$('#buyManIdea0').attr("disabled",true);
			$('#opeMajIdea1').attr("disabled",true);
			$('#opeMajIdea0').attr("disabled",true);
			buyManIdeaCheck();
			opeMajIdeaCheck();
		}
		//判断checkbox是否可选  采购主管、运营总监
		if("${USERLOGIN.roleId}"==11||"${USERLOGIN.roleId}"==17){
			$('#proMajIdea1').attr("disabled",true);
			$('#proMajIdea0').attr("disabled",true);
			$('#proMajIdea2').attr("disabled",true);
			$('#proMajIdea3').attr("disabled",true);
			proMajIdeaCheck();
			if("${USERLOGIN.roleId}"==11){
				//采购主管
				$('#opeMajIdea1').attr("disabled",true);
				$('#opeMajIdea0').attr("disabled",true);
				opeMajIdeaCheck();
			}else{ 
				//运营总监
				$('#buyManIdea1').attr("disabled",true);
				$('#buyManIdea0').attr("disabled",true);
				buyManIdeaCheck();
			}
		}
});

        //产品总监评审意见
		function proMajIdeaCheck(){
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
		}
		//采购主管评审意见
		function buyManIdeaCheck(){
	        if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
		}
		//运营总监评审意见
		function opeMajIdeaCheck(){
	        if('${assessDto.opeMajIdea}'=='1'){
	        	$('#opeMajIdea1').attr("checked",'true');
	        }
	        if('${assessDto.opeMajIdea}'=='0'){
	        	$('#opeMajIdea0').attr("checked",'true');
	        }
		}


     function submitCheck(){
			var result = true;
			var textArea ;
			var CheckCount=0; 
			if("${USERLOGIN.roleId}"==10){
				textArea = $('#proMajText').val(); 
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 2){
					alert("评审意见不可为空！");
					result = false;
				}
				
			}
			if("${USERLOGIN.roleId}"==11){
				textArea = $('#buyManText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			if("${USERLOGIN.roleId}"==17){
				textArea = $('#opeMajText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			return result ;
		}
//还原金额   
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	} 
	
	
	
	 $(function(){ 
		  //隐藏姓名和身份证号码 
		 if(${modifyBuyBackGoodsInfoDto.transportWay== 1}){ 
						  $("#nameAndId").show();  
					}else{
						$("#nameAndId").hide(); 
		} 
		 
	 });
	 
	  $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });

</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<html:form action="getBuyBackAssess.do" styleId="buyBackAssess" >
<input type="hidden" id="assessResult" value="" name = "assessResult"/>
<input type="hidden" id="buyBackAssess.id" value="${assessDto.id}" name="buyBackAssess.id"/>
<input type="hidden" id="buyBackAssess.buyManId" value="${assessDto.buyManId}" name="buyBackAssess.buyManId"/>
<input type="hidden" id="buyBackAssess.opeMajId" value="${assessDto.opeMajId}" name="buyBackAssess.opeMajId"/>
<input type="hidden" id="buyBackAssess.stkAdmId" value="${assessDto.stkAdmId}" name="buyBackAssess.stkAdmId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购返厂管理 &gt;&gt; 返厂单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">产品分类名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.productTypeName}&nbsp;</td>
          <td class="td_01" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">产品合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.productContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.companyContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">库房名称</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.stockRoomName}&nbsp;</td>
          <td class="td_01">入库单号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.instockId}&nbsp;</td>
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
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">已返厂数</th>
          <th nowrap="nowrap">返厂数</th>
          <th nowrap="nowrap">返厂金额</th>
          <th nowrap="nowrap">库存数</th>
        </tr>
        <logic:iterate id="backGoogsCreateInfo" name="backGoogsCreateInfoList" indexId="indexId">
        <tr>
          <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">&nbsp;${indexId+1}</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.code}&nbsp;</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.productName}&nbsp;</td>
          <td nowrap="nowrap" width="64">${backGoogsCreateInfo.productType}&nbsp;</td>
          <td nowrap="nowrap" >${backGoogsCreateInfo.productUnit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${backGoogsCreateInfo.price}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${backGoogsCreateInfo.inStockMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${backGoogsCreateInfo.paymentMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${backGoogsCreateInfo.receiveMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${backGoogsCreateInfo.alreadyBackCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; " width="82px">${backGoogsCreateInfo.backCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${backGoogsCreateInfo.backMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${backGoogsCreateInfo.stockNum}&nbsp;</td>
        </tr>
        </logic:iterate>
        <tr>
          <td height="18" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="9" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">返厂金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF"><fmt:formatNumber value="${modifyBuyBackGoodsInfoDto.money}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><span style=" border:1px solid #FFFFFF; background-color:#FFFFFF">元</span></td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" >${modifyBuyBackGoodsInfoDto.receiveName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" >${modifyBuyBackGoodsInfoDto.supplierAddress}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierLinkman}&nbsp;</td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierPostcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.supplierTel}&nbsp;</td>
          <td class="td_01">手机</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.supplierMobile}&nbsp;</td>
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
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.takeName}&nbsp;</td>
          <td class="td_01">身份证号码</td>
          <td class="td_02" >${modifyBuyBackGoodsInfoDto.takeNumber}&nbsp;</td>
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
          <td height="20px" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.proMajIdea" id="proMajIdea1" value="1" 
              <c:if test='${assessDto.proMajIdea == "1"}'>checked="checked"</c:if>  
          />符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.proMajIdea" id="proMajIdea0" value="0" 
              <c:if test='${assessDto.proMajIdea == "0"}'>checked="checked"</c:if>  
            />不符合
         </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">2.返厂地址是否符合</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.proMajIdea1" id="proMajIdea2" value="1" 
              <c:if test='${assessDto.proMajIdea == "1"}'>checked="checked"</c:if>  
          />符合
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.proMajIdea1" id="proMajIdea3" value="0" 
              <c:if test='${assessDto.proMajIdea == "0"}'>checked="checked"</c:if>  
            />不符合
        </td>
        </tr>
        
        <c:if test="${USERLOGIN.roleId == 10}">
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                <textarea name="buyBackAssess.proMajText" id="buyBackAssess.proMajText" cols="60" rows="3"></textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：</td>
           <input type="hidden" name="buyBackAssess.proMajName"  value="${assessDto.proMajName}"/>
          <td>日期：</td>
           <input type="hidden" name="buyBackAssess.proMajDate"  value="${assessDto.proMajDate}"/>
        </tr>
        </c:if>
        
        <c:if test="${USERLOGIN.roleId != 10}">
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.proMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.proMajName}</td>
           <input type="hidden" name="buyBackAssess.proMajName"  value="${assessDto.proMajName}"/>
          <td>日期：${assessDto.proMajDate}</td>
          <input type="hidden" name="buyBackAssess.proMajDate"  value="${assessDto.proMajDate}"/>
        </tr>
        </c:if>
        
        
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.buyManIdea" id="buyManIdea1" value="1" 
              <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.buyManIdea" id="buyManIdea0" value="0" 
              <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if>  
            />不同意
        </td>
        </tr>
        
         <c:if test="${USERLOGIN.roleId == 11}">
         <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                 <textarea name="buyBackAssess.buyManText" id="buyBackAssess.buyManText" cols="60" rows="3"></textarea>
                &nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：</td>
           <input type="hidden" name="buyBackAssess.buyManName"  value="${assessDto.buyManName}"/>
          <td>日期：</td>
           <input type="hidden" name="buyBackAssess.buyManDate"  value="${assessDto.buyManDate}"/>
        </tr>
        </c:if>
        
        <c:if test="${USERLOGIN.roleId != 11}">
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.buyManText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.buyManName}</td>
           <input type="hidden" name="buyBackAssess.buyManName"  value="${assessDto.buyManName}"/>
          <td>日期：${assessDto.buyManDate}</td>
           <input type="hidden" name="buyBackAssess.buyManDate"  value="${assessDto.buyManDate}"/>
        </tr>
        </c:if>
        
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >运营总监评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="buyBackAssess.opeMajIdea" id="opeMajIdea1" value="1" 
              <c:if test='${assessDto.opeMajIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="buyBackAssess.opeMajIdea" id="opeMajIdea0" value="0" 
              <c:if test='${assessDto.opeMajIdea == "0"}'>checked="checked"</c:if>  
          />不同意
        </td>
        </tr>
        
        <c:if test="${USERLOGIN.roleId == 17}">
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                 <textarea name="buyBackAssess.opeMajText" id="buyBackAssess.opeMajText" cols="60" rows="3"></textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：</td>
          <input type="hidden" name="buyBackAssess.opeMajName"  value="${assessDto.opeMajName}"/>
          <td>日期：</td>
          <input type="hidden" name="buyBackAssess.opeMajDate"  value="${assessDto.opeMajDate}"/>
        </tr>
        </c:if>
        
        <c:if test="${USERLOGIN.roleId != 17}">
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.opeMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.opeMajName}</td>
          <input type="hidden" name="buyBackAssess.opeMajName"  value="${assessDto.opeMajName}"/>
          <td>日期：${assessDto.opeMajDate}</td>
          <input type="hidden" name="buyBackAssess.opeMajDate"  value="${assessDto.opeMajDate}"/>
        </tr>
        </c:if>
        
        
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
         <c:set var="disabled" value="disabled" scope="page"></c:set>
		<c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==0}">
          <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==1}">
          <c:set value="checked" var="productSpecPass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==0}">
          <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==1}">
          <c:set value="checked" var="productQualityPass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==0}">
          <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==1}">
          <c:set value="checked" var="productSumPass" scope="page"></c:set>
        </c:if>           
        
      
        <tr>
          <td height="20px" colspan="2" >库房管理员意见：</td>
        </tr>
        <tr>
          <td nowrap="nowrap">1.产品规格是否符合</td>
          <td height="20px" nowrap="nowrap">
          <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
        </td>
        </tr>
        <tr>
          <td nowrap="nowrap">2.产品质量是否符合</td>
            <td height="20px" nowrap="nowrap">
            <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
            <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">3.产品数量是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
          <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(assessDto.stkAdmText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}</td>
          <td>日期：${assessDto.stkAdmDate}</td>
        </tr>
      </table>
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnTG.gif" width="65" height="20" id="pass"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img src="${ctx}/images/btnWTG.gif" width="65" height="20" id="nopass"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/getBuyBackGoodsList.do"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
