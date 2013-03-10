<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title id="title">销售退货单</title>
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
			function getJsonData(){
				var sumId=$("#tableProduct tr").length-2;
			 	var returnVlaue="{resultSum:"+sumId+",rows:[";
			 	$("#tableProduct tr").each(function(i){
					if(i!=0 && i!=$("#tableProduct tr").length-1){//判断是否第一个和最后一个tr	
						var productId=$("#"+$(this).attr("id")+" td:nth-child(14)").text();
						var  incomeMoney=$("#"+$(this).attr("id")+" td:nth-child(15)").text();//入库sum  入库金额
						var  incomeSum=$("#"+$(this).attr("id")+" td:nth-child(16)").text();//
						returnVlaue+="{productId:"+productId+",incomeSum:"+incomeSum+",incomeMoney:"+incomeMoney+"},"
						}
				});
				returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
				returnVlaue+="]}";
				$("#jsonData").val(returnVlaue);
			} 
		<!--
			$(document).ready(function(){
			$("#prient").click(function(){
				window.open("${ctx}/jsp/stock/printOfIncome.jsp",'newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=100,left=280, width=750,height=480");
			});
			$("#productSpec1").click(function(){
					if($("#productSpec1").attr("checked")){
					$("#productSpec0").attr("checked","");
					}else{
						$("#productSpec0").attr("checked","true");
						}
					});
				$("#productSpec0").click(function(){
					if($("#productSpec0").attr("checked")){
					$("#productSpec1").attr("checked","");
					}else{
						$("#productSpec1").attr("checked","true");
						}
					});					
				$("#productQuality0").click(function(){
					if($("#productQuality0").attr("checked")){
					$("#productQuality1").attr("checked","");
					}else{
						$("#productQuality1").attr("checked","true");
						}
					});
				$("#productQuality1").click(function(){
					if($("#productQuality1").attr("checked")){
					$("#productQuality0").attr("checked","");
					}else{
						$("#productQuality0").attr("checked","true");
						}
					});
				$("#productSum0").click(function(){
					if($("#productSum0").attr("checked")){
					$("#productSum1").attr("checked","");
					}else{
						$("#productSum1").attr("checked","true");
						}
					});
				$("#productSum1").click(function(){
					if($("#productSum1").attr("checked")){
					$("#productSum0").attr("checked","");
					}else{
						$("#productSum0").attr("checked","true");
						}
					});	
				$("#UnpassSubmit").click(function(){  
			if(!$("#productSpec0").attr("checked") && !$("#productSpec1").attr("checked")){
				alert("请选产品规格");
				$("#UnpassSubmit").attr("href","#productSpec0");
				return;
			}else if(!$("#productQuality0").attr("checked") && !$("#productQuality1").attr("checked")){
				alert("请选择产品质量");
				$("#UnpassSubmit").attr("href","#productQuality0");
				return;
			}else if(!$("#productSum0").attr("checked") && !$("#productSum1").attr("checked")){
				alert("请选择产品数量");
				$("#UnpassSubmit").attr("href","#productSum0");
				return;
			}else if($.trim($("#textarea").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#textarea");
				return;
				}
			$("#submitType").val("unpass");
		$("#InStockOfCommentForm").submit();
	});	
	$("#PassSubmit").click(function(){
			if(!$("#productSpec1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productSpec1");
				return;
			}else if(!$("#productQuality1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productQuality1");
				return;
			}else if(!$("#productSum1").attr("checked")){
				alert("请选择符合");
				$("#PassSubmit").attr("href","#productSum1");
				return;
			}	
		$("#submitType").val("pass");
		//getJsonData();
		$("#InStockOfCommentForm").submit();	
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
				if('${returnGoods.sellMajIdea}'=='0'){
					$('#sellMajIdea0').attr("checked",'true');
				}
				if('${returnGoods.sellMajIdea}'=='1'){
					$('#sellMajIdea1').attr('checked','true');
				}
				//运营总监评审意见
				if('${returnGoods.opeMajIdea}'=='0'){
					$('#opeMajIdea0').attr("checked",'true');
				}
				if('${returnGoods.opeMajIdea}'=='1'){
					$('#opeMajIdea1').attr('checked','true');
				}
				//库房管理员评审意见
				var returnGoodsArray = new Array();
				returnGoodsArray = '${returnGoods.stkMajIdea}'.split('');
				if(returnGoodsArray[0]=='0'){
					$('#skIdea00').attr('checked','true');
				}else if(returnGoodsArray[0]=='1'){
					$('#skIdea01').attr('checked','true');
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

			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<c:choose>
	<c:when test="${requestScope.comment}"><c:set var ="title" value="销售退货单核对"></c:set></c:when>
	<c:otherwise><c:set var ="title" value="销售退货单查看"></c:set></c:otherwise>
</c:choose>
<c:if test="${param.stkAdmIdeaError}">
	<script>alert("请选择库房管理员核对意见");</script>
</c:if>
<c:if test="${param.commentError}">
	<script>alert("评审失败");</script>
</c:if>
<html:form action="modifyInStockOfincomeStoreComment" styleId="InStockOfCommentForm" method="post">
<input type="hidden" name="id" value="${returnGoods.id }"/>
<input type="hidden" name="sendGoodsId" value="${returnGoods.sendGoodsId }"/>
<input type="hidden" id="submitType" name="type" />
<input type="hidden" name="billType" value="2"/>
<input type="hidden" name="stockroomId" value="${returnGoods.stockroomId}"/>
<input type="hidden" name="jsonData" value="" id="jsonData"/>
<input type="hidden" name="sendGoodsId" value="${returnGoods.sendGoodsId}" />
<input type="hidden" name="customerCreditId" value="${returnGoods.customerCreditId}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 入库管理 &gt;&gt; ${title }</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br/>
    
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div id="printOfDiv">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <div id="showPrint" style="display:none;font-size:20px;font-weight:700;text-align:center">入库单</div>

        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">销售退货单号</td>
          <td class="td_02">${returnGoods.id}&nbsp;</td>
          <td class="td_01">日期</td>
          <td class="td_02">${returnGoods.stkMajDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${returnGoods.productTypeName}&nbsp;</td>
          <td height="18" class="td_01" width="20%" >客户名称</td>
          <td class="td_02" width="30%">${returnGoods.customeName}&nbsp;</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">&nbsp;</div>
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableProduct" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">发货数</th>
          <th nowrap="nowrap">已退货数</th>
          <th nowrap="nowrap">退货数</th>
        </tr>
               <logic:present name="list">
			<logic:iterate id="ReturnsProduct" name="list" indexId="index">
		        <tr id="tr${ReturnsProduct.productId}">
		          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;text-align:right;" height="18">${index+1}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productCode}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productName}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productType}&nbsp;</td>
		          <td nowrap="nowrap" >${ReturnsProduct.productUnit}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;">${ReturnsProduct.ffcount}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;">${ReturnsProduct.yBackCount}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right;">${ReturnsProduct.thcount}&nbsp;</td>		        </tr>
	       </logic:iterate>
	    </logic:present>
      </table>
      </div>
      <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">库房名称</td>
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
        <tr>
          <td height="20px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
          <td width="320">&nbsp;</td>
          <td width="150" height="20px"><input type="checkbox" name="sellMajIdea1" id="sellMajIdea1" disabled="true" />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="sellMajIdea0" id="sellMajIdea0" disabled="true" />
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(returnGoods.sellMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${returnGoods.sellMajName}</td>
          <td>日期：${returnGoods.sellMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td height="20px"><input type="checkbox" name="opeMajIdea1" id="opeMajIdea1" disabled="true" />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="opeMajIdea0" id="opeMajIdea0" disabled="true" />
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(returnGoods.opeMajText)}</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${returnGoods.opeMajName}</td>
          <td>日期：${returnGoods.opeMajDate}</td>
        </tr>
<c:choose>
	<c:when test="${requestScope.comment}">
		      <tr>
                <td height="20px" colspan="2">库房管理员核对意见：</td>
              </tr>
              <tr>
                <td>1.产品规格是否符合</td>
                <td height="20px">
				  <input type="checkbox" id="productSpec1" value="1" name="productSpec" />符合&nbsp;&nbsp;
                  <input type="checkbox" id="productSpec0" value="0"  name="productSpec" />不符合
				</td>
              </tr>
              <tr>
                <td>2.产品质量是否符合</td>
                <td height="20px">
				  <input type="checkbox"  name="productQuality" id="productQuality1" value="1"/>符合&nbsp;&nbsp;
                  <input type="checkbox" name="productQuality" id="productQuality0" value="0"  />不符合
				</td>
              </tr>
              <tr>
                <td>3.产品数量是否符合</td>
                <td height="20px">
				  <input type="checkbox"  value="1" id="productSum1" name="productSum" />符合&nbsp;&nbsp;
                  <input type="checkbox"  value="0" id="productSum0" name="productSum" />不符合
				</td>
              </tr>
              
              <tr>
                <td colspan="2" valign="top">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                      <td style="padding:5px 0 5px 0;">
                      	<c:choose>
                      		<c:when test="${requestScope.comment}">
                      			<textarea rows="4" cols="60" name="text" id="textarea"></textarea>
                      		</c:when>
                      		<c:otherwise>&nbsp;${tf:replaceBr(inStockInfo.stkAdmText)}</c:otherwise>
                      	</c:choose>
                      </td>
                    </tr>
                </table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：${inStockInfo.stkAdmName}</td>
                <td>日期：${inStockInfo.stkAdmDate}</td>
              </tr>
	</c:when>
	<c:otherwise>
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
        </tr>
        <tr>
          <td>1.产品规格是否符合</td>
          <td height="20px"><input type="checkbox" name="skIdea01" id="skIdea01" disabled="true" />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox7" id="skIdea00" disabled="true" />
            不符合</td>
        </tr>
        <tr>
          <td>2.产品质量是否符合</td>
          <td height="20px"><input type="checkbox" name="skIdea11" id="skIdea11" disabled="true" />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox6" id="skIdea10" disabled="true" />
            不符合</td>
        </tr>
        <tr>
          <td>3.产品数量是否符合</td>
          <td height="20px"><input type="checkbox" name="skIdea21" id="skIdea21" disabled="true" />
            符合
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox4" id="skIdea20" disabled="true" />
            不符合</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(returnGoods.stkMajText)}</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${returnGoods.stkMajName}</td>
          <td>日期：${returnGoods.stkMajDate}</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
    </tr>
	</c:otherwise>
</c:choose>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">
          	    	<div style="text-align:center;"> 
    	 <c:choose>
    	<c:when test="${requestScope.comment}">
			<a href="#" id="PassSubmit"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="UnpassSubmit"><img src="${ctx}/images/btnWTG.gif" width="65" height="20" /></a>	    	
    	</c:when>
    	<c:otherwise>
    		<a href="#" id="prient"><img src="${ctx}/images/btnPrint.gif" width="65" height="20" /></a> 
    	</c:otherwise>
   		 </c:choose>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/getIncomeStockList.do" ><img src="${ctx}/images/btnBack.gif" /></a>
    	</div>
          </td>
        </tr>
</table>
</html:form>
</body>
</html>
