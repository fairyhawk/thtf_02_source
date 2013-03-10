<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品归还评审</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
				
					
			$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
			}); 
				
			
			 //通过
		$('#pass').click(function(){
			var textArea ;
			var CheckCount=0; 
			var checkbox = $(":checkbox[value='1']:checked:enabled").length;
			var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
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
				    sampleInAssess.submit();
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
			
		    if(${USERLOGIN.roleId=="11"}){
             textarea = document.getElementById('sampleInAssess.buyManText').value;
            } 
            textarea=$.trim(textarea);    
          if(textarea == null || textarea ==""){
               alert("补充说明不可为空！");
				return false;			                       									
			}
			
			
			$("#assessResult").val("nopass");
			sampleInAssess.submit();
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
		
		if("${USERLOGIN.roleId}"==11){
		       if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
		}

});

		function submitCheck(){
			var result = true;
		    var textArea ;
		    var CheckCount=0; 
		
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
		    return result ;
		}
		
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
<html:form action="getSampleInAssess.do" styleId="sampleInAssess" >
<input type="hidden" id="assessResult" value="" name = "assessResult"/>
<input type="hidden" id="sampleInAssess.id" value="${assessDto.id}" name="sampleInAssess.id"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品归还管理 &gt;&gt; 归还单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
	 <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
       <tr>
         <td class="td_01" width="20%" height="18px">借出单号</td>
         <td class="td_02" width="30%">${assessDto.sampleOutId}&nbsp;</td>
         <td class="td_01" width="20%">借出库房名称</td>
         <td class="td_02" width="30%">${assessDto.outStockroomName}&nbsp;</td>
       </tr>
       <tr>
         <td class="td_01" height="18px">借出单类型</td>
         <td  class="td_02" >
                    <logic:equal value="0" property="type" name="assessDto">
            		          不归还
            	    </logic:equal>
                    <logic:equal value="1" property="type" name="assessDto">
            		          归还
            	    </logic:equal>
         &nbsp;</td>
         <td class="td_01">预计归还日期</td>
         <td class="td_02" >${assessDto.inDate}&nbsp;</td>
       </tr>
       <tr>
         <td class="td_01" height="18px">借出单位类型</td>
         <td class="td_02" >
                   <logic:equal value="1" property="companyType" name="assessDto">
            		          公司
            	    </logic:equal>
                    <logic:equal value="2" property="companyType" name="assessDto">
            		          客户
            	    </logic:equal>
                    <logic:equal value="3" property="companyType" name="assessDto">
            		         供货商
            	    </logic:equal>       
         &nbsp;</td>
         <td class="td_01">发货日期</td>
         <td class="td_02" >${assessDto.sendDate}&nbsp;</td>
       </tr>
       <tr>
         <td class="td_01" height="18px">借出单位</td>
         <td class="td_02" >${assessDto.companyName}&nbsp;</td>
         <td class="td_01">保管人</td>
         <td class="td_02" >${assessDto.custosName}&nbsp;</td>
       </tr>
       <tr>
         <td class="td_01" height="18px">产品分类名称</td>
         <td class="td_02" >${assessDto.productTypeName}&nbsp;</td>
         <td class="td_01">&nbsp;</td>
         <td class="td_02" >&nbsp;</td>
       </tr>
     </table>
	 <br />
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <tr>
        <td class="td_03" width="20%" height="18">库房名称</td>
        <td class="td_04" >${assessDto.inStockroomName}&nbsp;</td>
      </tr>
    </table>
	<div style="text-align:right; width:100%">单位：元</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">序号</th>
        <th nowrap="nowrap">产品编码</th>
        <th nowrap="nowrap">产品名称</th>
        <th nowrap="nowrap">规格型号</th>
        <th nowrap="nowrap">单位</th>
        <th nowrap="nowrap">库存单价</th>
        <th nowrap="nowrap">借出数</th>
        <th nowrap="nowrap">借出金额</th>
        <th nowrap="nowrap">已归还数</th>
        <th nowrap="nowrap">归还数</th>
        <th nowrap="nowrap">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th>
      </tr>
      
      <logic:iterate id="sampleInProductInfoDto" name="list" indexId="indexId">
      <tr>
        <td width="40" nowrap="nowrap" height="18" style="text-align:center;">${indexId+1}</td>
        <td nowrap="nowrap" >${sampleInProductInfoDto.code}&nbsp;</td>
        <td nowrap="nowrap">${sampleInProductInfoDto.productName}&nbsp;</td>
        <td nowrap="nowrap">${sampleInProductInfoDto.type}&nbsp;</td>
        <td nowrap="nowrap" >${sampleInProductInfoDto.unit}&nbsp;</td>
        <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${sampleInProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td>
        <td nowrap="nowrap" style="text-align:right;">${sampleInProductInfoDto.count}&nbsp;</td>
        <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${sampleInProductInfoDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td>
        <td nowrap="nowrap" style="text-align:right;">${sampleInProductInfoDto.alreadyReCount}&nbsp;</td>
        <td nowrap="nowrap" width="92" style="text-align:right;">${sampleInProductInfoDto.reCount}&nbsp;</td>
        <td nowrap="nowrap" width="93" style="text-align:right;padding-right:5px"><fmt:formatNumber value="${sampleInProductInfoDto.returnMoneyTax}" pattern="#,##0.00"/>&nbsp;</td>
      </tr>
      </logic:iterate>
      
    </table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
      <tr>
        <td height="25"  align="center">&nbsp;</td>
        <td  align="center" width="100px">归还金额合计</td>
        <td  style=" text-align:right;width:96px"><fmt:formatNumber value="${assessDto.moneyTotalTax}" pattern="#,##0.00"/></td>
        <td style="width:2px">元</td>
      </tr>
    </table>
	<div class="div_tiao"> &nbsp;&nbsp;收货信息</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18">货物接收单位名称</td>
            <td colspan="3" class="td_02" >${assessDto.goodsReceiveUnitName}&nbsp;</td>
			</tr>
          <tr>
            <td class="td_01" height="18">收货地址</td>
            <td colspan="3"  class="td_02" >${assessDto.address}&nbsp;</td>
			</tr>
          <tr>
            <td class="td_01" height="18">联系人</td>
            <td class="td_02" >${assessDto.linkman}&nbsp;</td>
            <td class="td_01">邮编</td>
            <td class="td_02" >${assessDto.postcode}&nbsp;</td>
          </tr>
		  <tr>
		  	<td class="td_01" width="20%" height="18">电话</td>
		  	<td class="td_02" width="30%">${assessDto.tel}&nbsp;</td>
		  	<td class="td_01" width="20%">手机</td>
		  	<td class="td_02" width="30%">${assessDto.mobile}&nbsp;</td>
	  	</tr>
        </table>
		 <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="20%">特殊说明</td>
                <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.text)}&nbsp;</td>
              </tr>
            </table>
	  <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
			  <tr>
			  	<td height="20px" colspan="2">采购主管评审意见：</td>
		  	</tr>
			<tr>
				<td height="20px">库房名称是否符合</td>
				<td><input type="checkbox" name="sampleInAssess.buyManIdea" id="buyManIdea1" value="1" 
                    <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>  
				/>符合
			  		&nbsp;&nbsp;
					<input type="checkbox" name="sampleInAssess.buyManIdea" id="buyManIdea0" value="0" 
                    <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
					/>不符合
				</td>
			</tr>
			<tr>
				<td height="20px" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" style="padding:5px 0 5px 0; width:62" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
                          <textarea name="sampleInAssess.buyManText" id="sampleInAssess.buyManText" cols="60" rows="3">${assessDto.buyManText}</textarea>
                          </td>
                        </tr>
                	</table>				</td>
				</tr>
			<tr>
                <td width="320" height="20px">签字：</td>
                <td width="140">日期：${assessDto.buyManDate}</td>
                <input type="hidden" name="sampleInAssess.buyManDate"  value="${assessDto.buyManDate}"/>
              </tr>
              
              
			  <tr>
			  	<td height="20px" colspan="2">&nbsp;</td>
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
			  	<td height="20px" colspan="2">库房管理员意见：</td>
			  	</tr>
			  <tr>
			  	<td width="320" height="20px">1.产品规格是否符合</td>
			  	<td width="140" height="20px">
			  	<input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
                <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
                </td>
			  </tr>
			  <tr>
			  	<td height="20px">2.产品质量是否符合</td>
			  	<td height="20px">
			  	<input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
                <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
			  	</td>
			  </tr>
			  <tr>
			  	<td height="20px">3.产品数量是否符合</td>
			  	<td height="20px">
			  	<input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
                <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
			    </td>
			  </tr>
			  <tr>
			  	<td height="20px" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" style="padding:5px 0 5px 0; width:62" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
                           ${tf:replaceBr(assessDto.stkAdmText)}
                          &nbsp;</td>
                        </tr>
                	</table>				</td>
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
    <img src="${ctx}/images/btnTG.gif" width="65" height="20" id="pass"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnWTG.gif" width="65" height="20" id="nopass"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/getSampleInList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
