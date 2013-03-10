<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title id="title">入库单</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<style type="text/css"> 
			.treven {
				background-color: #f3fbff;
			}
			.over {
				background-color: #ECECEC;
			}
			 
			.STYLE1 {
				color: #FF0000
			} 
		</style>
		<script language="JavaScript"> 
			function getJsonData(){
				var sumId=$("#tableProduct tr").length-2;
			 	var returnVlaue="{resultSum:"+sumId+",rows:[";
			 	$("#tableProduct tr").each(function(i){
					if(i!=0 && i!=$("#tableProduct tr").length-1){//判断是否第一个和最后一个tr	
						var productId=$("#"+$(this).attr("id")+" td:nth-child(1)").text();
						var  incomeMoney=$("#"+$(this).attr("id")+" td:nth-child(14)").text();//入库sum  入库金额
						var  incomeSum=$("#"+$(this).attr("id")+" td:nth-child(11)").text();//
						returnVlaue+="{productId:"+productId+",incomeSum:"+incomeSum+",incomeMoney:"+incomeMoney+"},"
						}
				});
				returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
				returnVlaue+="]}";
				$("#jsonData").val(returnVlaue);
			} 
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
			}else if($.trim($("#textArea").val())==""){
				alert("请填写补充说明");
				$("#UnpassSubmit").attr("href","#textArea");
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
		$("#InStockOfCommentForm").submit();	
	});																				
				//隔行换色
				changeColor(); 
				//验证合同金额
				$.validator.addMethod("checkCount", function(value, element) {
					var result = false;
				 
					var tr = $(element).parent().parent();
					 
					if(value*1 <= tr.children("td:eq(6)").text()*1 - tr.children("td:eq(9)").text()*1+tr.children("td:eq(12)").text()*1){ 
						result = true;
					} 
					return this.optional(element) || result;
				}, "入库数小于等于应入库数-已入库数+返厂数");
				
			});

			var productNum =0;

			//隔行换色
			function changeColor(){
				if($("#productTable")){
					$("#productTable tr").removeClass("treven");
					$("#productTable tr:odd").addClass("treven");
					$("#productTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				} 
			}

function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}		 
		</script> 
	</head>
<body id="">
<c:if test="${param.stkAdmIdeaError}">
	<script>alert("请选择库房管理员核对意见");</script>
</c:if>
<c:if test="${param.commentError}">
	<script>alert("评审失败");</script>
</c:if>
<c:if test="${param.roleError}">
	<script>alert("你无此权限操作");</script>
</c:if>
<c:if test="${param.billTypeError}">
	<script>alert("无效参数");</script>
</c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<c:choose>
	<c:when test="${requestScope.comment}"><c:set var ="title" value="入库单核对"></c:set></c:when>
	<c:otherwise><c:set var ="title" value="入库单查看"></c:set></c:otherwise>
</c:choose>
<html:form method="post" action="modifyInStockOfincomeStoreComment" styleId="InStockOfCommentForm">
<input type="hidden" id="buyContractId" name="addPara.buyContractId" value="${buyContract.id}" />
<input type="hidden" name="id" value="${inStockInfo.id}" />
<input type="hidden" id="submitType" name="type" />
<input type="hidden" name="billType" value="1"/>
<input type="hidden" name="stockroomId" value="${inStockInfo.stockroomId}"/>
<input type="hidden" name="jsonData" value="" id="jsonData"/>
<input type="hidden" name="taxRate" value="${buyContract.taxRate}"/>
<input type="hidden" name="invoiceType" value="${buyContract.invoiceType}"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 库存管理 &gt;&gt; 入库管理 &gt;&gt; ${title }</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />	
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">产品合同号</td>
          <td class="td_02" width="30%">${buyContract.productContractCode}&nbsp;</td>
          <td class="td_01" width="20%">公司合同号</td>
          <td class="td_02" width="30%">${buyContract.companyContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">供货商名称</td>
          <td class="td_02">${buyContract.supplierName}&nbsp;</td>
          <td class="td_01">合同签订日期</td>
          <td class="td_02">${buyContract.date}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发票类型</td>
          <td class="td_02">
			<c:if test="${buyContract.invoiceType==0}">普通&nbsp;</c:if>
			<c:if test="${buyContract.invoiceType==1}">增值税&nbsp;</c:if> 
		  </td>
          <td class="td_01">增值税税率</td>
          <td class="td_02">${buyContract.taxRate}%</td>
        </tr>
      </table>

      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;收货信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
          <td colspan="3" id="receiveName" class="td_02">${receiveGoodsDetail.name}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">收货地址</td>
          <td colspan="3" id="receiveAddress" class="td_02" >${receiveGoodsDetail.address}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td width="30%" id="receiveLinkman" class="td_02" >${receiveGoodsDetail.linkman}&nbsp;</td>
          <td width="20%" class="td_01">邮编</td>
          <td width="30%" id="receivePostcode" class="td_02" >${receiveGoodsDetail.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">电话</td>
          <td class="td_02" id="receiveTel" >${receiveGoodsDetail.tel}&nbsp;</td>
          <td class="td_01">手机</td>
          <td class="td_02" id="receiveMobile" >${receiveGoodsDetail.mobile}&nbsp;</td>
        </tr>
      </table>
      <br/>
      
      <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
      <div id="printOfDiv">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <div id="showPrint" style="display:none;font-size:20px;font-weight:700;text-align:center">入库单</div>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">厂家</td>
          <td class="td_02" colspan="3">${buyContract.supplierName}&nbsp;</td>
        </tr>
        <tr id="showPrint" style="display:none">
          <td class="td_01" height="18px">入库单号</td>
          <td class="td_02">${inStockInfo.id}&nbsp;</td>
          <td class="td_01">日期</td>
          <td class="td_02">${inStockInfo.stkAdmDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">库房名称</td>
          <td class="td_02" id="receiveStockroomName">${receiveGoodsDetail.stockroomName}&nbsp;</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02">${buyContract.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableProduct" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>

          <th nowrap="nowrap">应入库数</th>
          <th nowrap="nowrap">已入库数</th>
          <th nowrap="nowrap">入库数</th>

          <th nowrap="nowrap">返厂数</th>
        </tr>
        <tbody id="productTable">
		<c:if test="${productList!=null}">
		<logic:iterate id="product" name="productList" indexId="index">
		<tr id="${product.productId}">
          <td nowrap="nowrap" style="display:none">
			${product.productId}
		  </td>
          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;text-align:right;padding-right:5px">${index+1}</td>
          <td nowrap="nowrap"  height="18px">${product.productCode}&nbsp;</td>
          <td nowrap="nowrap" >${product.productName}&nbsp;</td>
          <td nowrap="nowrap" >${product.productType}&nbsp;</td>
          <td nowrap="nowrap" >${product.productUnit}&nbsp;</td>

          <td nowrap="nowrap" style="text-align:right;padding-right:5px">${product.receiveGoodsDetailCount}</td>
          <td nowrap="nowrap" style="text-align:right;padding-right:5px">${product.inStockCount}</td>
          <td nowrap="nowrap" style="text-align:right;padding-right:5px">
			${product.count	 }
		  </td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${product.buyBackGoodsCount}&nbsp;</td>
        </tr>
		</logic:iterate>
		</c:if>
        </tbody>
      </table>
      </div>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(inStockInfo.text)}&nbsp;</td>
        </tr>
      </table>
	  <br/>

	 <c:if test="${inStockInfo.id!=null}">
 <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
                <td height="20px" colspan="2" >产品总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap">
					<c:if test="${inStockInfo.proMajIdea==''}">
					<input type="checkbox" disabled="true"/>同意&nbsp;&nbsp;
                    <input type="checkbox" disabled="true"/>不同意
					</c:if>
					<c:if test="${inStockInfo.proMajIdea==0}">
					<input type="checkbox" disabled="true"/>同意&nbsp;&nbsp;
                    <input type="checkbox" checked="true" disabled="true"/>不同意
					</c:if>
					<c:if test="${inStockInfo.proMajIdea==1}">
					<input type="checkbox" checked="true" disabled="true"/>同意&nbsp;&nbsp;
                    <input type="checkbox" disabled="true"/>不同意
					</c:if>
				 </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${tf:replaceBr(inStockInfo.proMajText)}&nbsp;</td>
                        </tr>
                	</table>
				</td>
              </tr>
              <tr>
                <td height="20px">签字：${inStockInfo.proMajName}&nbsp;</td>
                <td>日期：${inStockInfo.proMajDate}&nbsp;</td>
              </tr>
              <tr>
                <td height="20px" colspan="2">&nbsp;</td>
              </tr>
<c:choose>
	<c:when test="${!requestScope.comment}">
		<c:set var="disabled" value="disabled" scope="page"></c:set>
		<c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,0,1)==0}">
          <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,0,1)==1}">
          <c:set value="checked" var="productSpecPass" scope="page"></c:set>
        </c:if>
        <c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,1,2)==0}">
          <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,1,2)==1}">
          <c:set value="checked" var="productQualityPass" scope="page"></c:set>
        </c:if>
        <c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,2,3)==0}">
          <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${inStockInfo.stkAdmIdea!=null && fn:substring(inStockInfo.stkAdmIdea,2,3)==1}">
          <c:set value="checked" var="productSumPass" scope="page"></c:set>
        </c:if>
	</c:when>
</c:choose>
              <tr>
                <td height="20px" colspan="2">库房管理员核对意见：</td>
              </tr>
              <tr>
                <td>1.产品规格是否符合</td>
                <td height="20px">
				  <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
                  <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
				</td>
              </tr>
              <tr>
                <td>2.产品质量是否符合</td>
                <td height="20px">
				  <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
                  <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
				</td>
              </tr>
              <tr>
                <td>3.产品数量是否符合</td>
                <td height="20px">
				  <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
                  <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
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
                      			<textarea rows="4" cols="60" name="text" id="textArea"></textarea>
                      		</c:when>
                      		<c:otherwise>${tf:replaceBr(inStockInfo.stkAdmText)}&nbsp;</c:otherwise>
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
            </table> 

			</c:if>
	  </td>
    <td >&nbsp;</td>

  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
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
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
