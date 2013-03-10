<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>入库管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
		var subwin;
		var subproduct;
		window.onbeforeunload=function()
	    { 
	        if(subwin!=undefined){
	            subwin.close();
	        }
	        if(subproduct!=undefined){
	        	subproduct.close();
	        }
	    }	 
			$(document).ready(function(){
				//隔行换色
				changeColor(); 
 
				//计算金额
				changePrice();

				//打开选择地址页面
				$("#receiveBtn").click(function(){   
					
					if($("#productTable tr").length==0){

						var receiveId = $("#receiveId").val();

						subwin = window.open("${ctx}/getReceiveGoodsDetail.do?addPara.buyContractId=${buyContract.id}&addPara.receiveId="+receiveId,"newwindow","toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=350"); 
					
					}else{
						 alert("已选择产品后不能改变收货地址！");
					}
				  
				});

				//打开选择产品页面
				$("#productAdd").click(function(){
					var receiveId = $("#receiveId").val();

					if(receiveId!=""){
						var companyType = $("#receiveCompanyType").val();
						var addressId = $("#receiveAddressId").val(); 
	 
						subproduct = window.open("${ctx}/getProductList.do?addPara.buyContractId=${buyContract.id}&addPara.companyType="+companyType+"&addPara.addressId="+addressId,"newwindow","toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=350");
					}else{
						alert("请先选择收货地址！");
					}
				  
				});

				 //全选 产品
				 $("#productAll").click(function(){ 
					if($(this).attr("checked")){
						$("#productTable :checkbox").attr("checked", true);
					}else{
						$("#productTable :checkbox").attr("checked", false);
					} 
				 }); 

				 //删除 产品
				 $("#productDel").click(function(){  

					 if($("#productTable input:checked").length==0){
						alert("请先选择要删除的产品！");
					 }else{  
						var i = 0;
						$("#productTable :checkbox").each(function(){  
							  if($(this).attr("checked")){
								$("#productTable tr:eq("+(i--)+")").remove();
							  }
							  i++;
						}); 

						changePrice();
						$("#productAll").attr("checked", false);
					 }
				 });


				//form验证
				$("#inStockForm").validate({
					rules: { 
						"addPara.productCountArr": {
							min: 1,
							digits: true,
							checkCount: true
						},
						"addPara.factorySendDate": {
							 required:true
						}
						
					},
					messages: {
						"addPara.productCountArr":{
							min: "入库数必须大于0！",
							digits: "入库数必须为整数！"
						},
						"addPara.factorySendDate":{
							required: "请输入厂家发货日期！"
						}
					} ,
					errorClass: "invalid",
					onchange: true 
				});

				//验证合同金额
				$.validator.addMethod("checkCount", function(value, element) {
					var result = false;
				 
					var tr = $(element).parent().parent();
					if(value*1 <= tr.children("td:eq(6)").text()*1 - tr.children("td:eq(9)").text()*1+		tr.children("td:eq(12)").text()*1){ 
						result = true;
					} 

					if(result){
						changePrice();
					} 
					return this.optional(element) || result;
				}, "入库数小于等于应入库数-已入库数+返厂数");

				//保存
				 $("#btnSave").click(function(){   
					 $("#submitType").val("1"); 

					 checkSubmit();
					
				 });
				 //提交
				 $("#btnSubmit").click(function(){   
					 $("#submitType").val("2"); 
					 
					 checkSubmit();
				 }); 

				 if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}
				
			});

			//提交验证
			function checkSubmit(){   
				if($("#productTable tr").length==0){
					alert("请添加产品！");
				}else{
					$("#inStockForm").submit();
				}
			}

			//判断小页面传过来的产品是否存在
			function checkProduct(product){ 
				 
				if($("#productTable tr").length==0){
					return false;
				}else{ 
					var result = false;
					$("#productTable tr").each(function(){  
						 if($(this).find(".productIdArr").val()==product.split("&")[0]){
							result = true;
						 }
						 
					}); 
					return result;
				}
			} 

			var productNum =0;
			if("${productListSize}" != ""){
			    i = "${productListSize}"*1;
			}

		    //添加发货单
			function addProduct(productArr) { 
				 
				$.each(productArr,function(k,product){  
					var tr = $("<tr></tr>").appendTo("#productTable");
					tr.append("<td nowrap='nowrap' style='border-left:1px solid #c2c2c2;'><input type='checkbox' id='productCh'"+productNum+"/></td>").append("<td nowrap='nowrap'>"+(productNum+1)+"</td>");
					 
					$.each(product.split("&"),function(j,td){ 
						
						if(j==0){
							tr.append("<input type='hidden' class='productIdArr' name='addPara.productIdArr' value='"+td+"'/>"); 
						}else if(j==9){
							tr.append("<td><input value='0' id='productCountArr'"+productNum+" name='addPara.productCountArr' onclick='range(this)' type='text' style='text-align:right; padding-right:5px;width:86px;'/></td>"); 

							tr.append("<td style='text-align:right; padding-right:5px'>0.00</td>");  
						}else{ 
							if(j==6){
								tr.append("<td style='text-align:right;'><input type='hidden' name='addPara.productPriceArr' value='"+td+"'/>"+formatMoney(td,5)+"</td>"); 
							}else if(j==5||j==8||j==10){
								tr.append("<td style='text-align:right; padding-right:5px'>"+td+"</td>"); 
							}else if(j==7){
								tr.append("<td style='text-align:right; padding-right:5px'>"+formatMoney(td,5)+"</td>"); 
							}else{
								tr.append("<td>"+td+"</td>"); 
							} 
						}
						
					});  

					productNum++;
					 changePrice();
				}); 

				//隔行换色
				changeColor();
				
			} 

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

			//计算入库金额
			function changePrice(){   
				 
				var sumMoney = 0;
				var indexI = 1;
				$("#productTable tr").each(function(){  
					
					var money = rmoney($(this).children("td:eq(7)").text());

					var count = $(this).children("td:eq(10)").find("input").val(); 
					
					var sum = FloatMul(money,count); 
					
					sumMoney = FloatAdd(sumMoney,sum);

					$(this).children("td:eq(11)").text(formatMoney(sum,5));   
					
					$(this).children("td:eq(1)").text(indexI++);  
				});

				$("#sumMoney").text(formatMoney(sumMoney,5)); 
			 
				$("#instockMoney").val(sumMoney);
				
			}
		//还原金额   
		function rmoney(s){ 
			return parseFloat(s.replace(/[^\d\.-]/g,""));  
		} 

	    //金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
		function formatMoney(s,n){
			n = n>0 && n<=20 ? n : 2;
			s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
			var l = s.split(".")[0].split("").reverse(), 
			r = s.split(".")[1]; 
			t = "";
			for(i = 0;i<l.length;i++){
				t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : ""); 
			}
			return t.split("").reverse().join("")+"."+r;
		}
		
		//选中文本框内所有
		function range(obj){
		  obj.select();
		 }
		</script> 
	</head>
<body>
<html:form method="post" action="addInStock" styleId="inStockForm">
<input type="hidden" id="buyContractId" name="addPara.buyContractId" value="${buyContract.id}" />
<input type="hidden" name="addPara.id" value="${inStockInfo.id}" />
<input type="hidden" id="submitType" name="addPara.submitType" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 入库管理 &gt;&gt; <c:if test="${inStockInfo.id==null}">新建</c:if>入库单<c:if test="${inStockInfo.id!=null}">修改</c:if></td>
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
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">&nbsp;付款方式</td>
		  <td class="td_02">
			<c:if test="${buyContract.paymentWay==2}">支票&nbsp;</c:if>
			<c:if test="${buyContract.paymentWay==3}">网银&nbsp;</c:if>
			<c:if test="${buyContract.paymentWay==4}">电汇&nbsp;</c:if>
			<c:if test="${buyContract.paymentWay==5}">银行承兑&nbsp;</c:if>
			<c:if test="${buyContract.paymentWay==6}">信用证&nbsp;</c:if>
			<c:if test="${buyContract.paymentWay==7}">其它&nbsp;</c:if>
		  </td>
          <td class="td_01" width="20%" height="18px">
			<c:if test="${buyContract.paymentWay==5}">账期</c:if>
		  </td>
          <td class="td_02" width="30%">
			${buyContract.arterm}<c:if test="${buyContract.paymentWay==5}">天</c:if>&nbsp;
		  </td>
        </tr>
        <tr>
          <td class="td_01" height="18px">&nbsp;付款类型</td>
          <td colspan="3" class="td_02" >
			<c:if test="${buyContract.paymentType==0}">全部预付&nbsp;</c:if>
			<c:if test="${buyContract.paymentType==1}">部分预付&nbsp;</c:if>
			<c:if test="${buyContract.paymentType==2}">货到付款&nbsp;</c:if> 
		  </td>
        </tr>
		<c:if test="${buyContract.paymentType==0}">
			<tr>
			  <td class="td_01" height="18px">合同签订后</td>
			  <td colspan="3" class="td_02" >${buyContract.contarctPaymentTime}个工作日内</td>
			</tr>
		</c:if>
		<c:if test="${buyContract.paymentType==1}">
			<tr>
			  <td class="td_01" height="18px">合同签订后</td>
			  <td class="td_02" >${buyContract.contarctPaymentTime}个工作日内</td>
			  <td class="td_01">预付金额</td>
			  <td class="td_02" >${buyContract.prepayMoney}元</td>
			</tr> 
			<tr>
			  <td class="td_01" height="18px">验收合格后</td>
			  <td colspan="3" class="td_02" >${buyContract.sendPaymentTime}个工作日内</td>
			</tr> 
		</c:if>
		<c:if test="${buyContract.paymentType==2}">
			<tr>
			  <td class="td_01" height="18px">验收合格后</td>
			  <td class="td_02" >${buyContract.sendPaymentTime}个工作日内</td>
			  <td class="td_01">&nbsp;</td>
			  <td class="td_02" >&nbsp;</td>
			</tr>
		</c:if>
		<tr>
		  <td class="td_01" height="18px"><span style="color:#FF0000">*</span>厂家发货日期</td>
		  <td class="td_02" >
			<input type="text" name="addPara.factorySendDate" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${inStockInfo.factorySendDate}"/>
		  </td>
		  <td class="td_01">&nbsp;</td>
		  <td class="td_02" >&nbsp;</td>
		</tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;收货信息 </div>
      <a href="#" id="receiveBtn"><img src="${ctx}/images/btnSHDZ.gif" width="99" height="20" /></a>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
		<input type="hidden" id="receiveId" name="addPara.receiveId" value="${inStockInfo.receiveGoodsDetailId}"/> 
		<input type="hidden" id="stockroomId" name="addPara.stockroomId" value="${inStockInfo.stockroomId}"/>
		<input type="hidden" id="instockMoney" name="addPara.money" value="${inStockInfo.money}"/>

		<input type="hidden" id="receiveCompanyType" value="${receiveGoodsDetail.companyType}"/>
		<input type="hidden" id="receiveAddressId" value="${receiveGoodsDetail.addressId}"/> 
		 
		<input type="hidden" name="addPara.supplierId" value="${buyContract.supplierId}"/>
		<input type="hidden" name="addPara.supplierName" value="${buyContract.supplierName}"/>
		<input type="hidden" name="addPara.productTypeId" value="${buyContract.productTypeId}"/>
		<input type="hidden" name="addPara.sendPaymentTime" value="${buyContract.sendPaymentTime}"/>
		

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
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">库房名称</td>
          <td class="td_02" id="receiveStockroomName">${receiveGoodsDetail.stockroomName}&nbsp;</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02">${buyContract.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <thead>
          <th width="35" nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap" width="40px">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>

          <th nowrap="nowrap">应入库数</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">采购金额</th>
          <th nowrap="nowrap">已入库数</th>
          <th nowrap="nowrap" width="102px">入库数</th>

          <th nowrap="nowrap">入库金额</th> 
          <th nowrap="nowrap">返厂数</th>
        </thead>
        <tbody id="productTable">
		<c:if test="${productList!=null}">
		<logic:iterate id="product" name="productList" indexId="index">
		<tr>
		  <input type='hidden' class='productIdArr' name='addPara.productIdArr' value='${product.productId}'/> 
		  <input type='hidden' name='addPara.productPriceArr' value='${product.buyContractDetailPrice}'/>

          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;">
			<input type="checkbox" name="checkbox7" id="productCh${index}" />
		  </td>
		  
          <td nowrap="nowrap" >${index+1}</td>
          <td nowrap="nowrap" >${product.productCode}&nbsp;</td>
          <td nowrap="nowrap" >${product.productName}&nbsp;</td>
          <td nowrap="nowrap" >${product.productType}&nbsp;</td>
          <td nowrap="nowrap" >${product.productUnit}&nbsp;</td>

          <td nowrap="nowrap" style="text-align:right; padding-right:5px">${product.receiveGoodsDetailCount}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right; padding-right:5px"><fmt:formatNumber value="${product.buyContractDetailPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right; padding-right:5px"><fmt:formatNumber value="${product.money}" pattern="#,##0.00000#"/></td>
          <td nowrap="nowrap" style="text-align:right; padding-right:5px">${product.inStockCount}&nbsp;</td>
          <td nowrap="nowrap" >
			<input type="text" id="productCountArr${index}" name='addPara.productCountArr' value="${product.count}"  style="text-align:right; padding-right:5px;width:86px;" onclick="range(this)"/>
		  </td>

          <td nowrap="nowrap" style="text-align:right; padding-right:5px;"><fmt:formatNumber value="${product.count*product.buyContractDetailPrice}" pattern="#,##0.00#"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right; padding-right:5px;">${product.buyBackGoodsCount}&nbsp;</td>
        </tr>
		</logic:iterate>
		</c:if>
        </tbody>
        <tr>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">
			<input type="checkbox" name="checkbox2" id="productAll" />
		  </td>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">全选</td>
          <td nowrap="nowrap" colspan="3" style="border:1px solid #FFFFFF; background-color:#FFFFFF">
			  <img src="${ctx}/images/btnDelete.gif" width="65" height="20" id="productDel"/>&nbsp;&nbsp;&nbsp;
			  <img src="${ctx}/images/btnAdd.gif" width="65" height="20" id="productAdd"/>
		  </td>
          <td colspan="6" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 入库金额合计 </td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="sumMoney">0</td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" ><textarea name="addPara.text" id="textarea" cols="100" rows="4">${inStockInfo.text}</textarea>
          </td>
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
					<input type="checkbox" disabled="true"/>符合&nbsp;&nbsp;
                    <input type="checkbox" disabled="true"/>不符合
					</c:if>
					<c:if test="${inStockInfo.proMajIdea==0}">
					<input type="checkbox" disabled="true"/>符合&nbsp;&nbsp;
                    <input type="checkbox" checked="true" disabled="true"/>不符合
					</c:if>
					<c:if test="${inStockInfo.proMajIdea==1}">
					<input type="checkbox" checked="true" disabled="true"/>符合&nbsp;&nbsp;
                    <input type="checkbox" disabled="true"/>不符合
					</c:if>
				 </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${inStockInfo.proMajText}&nbsp;</td>
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
                      	<c:if test="${requestScope.comment}">
                      	<textarea rows="4" cols="60" name="text"></textarea>
                      	</c:if>${inStockInfo.stkAdmText }
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
    <td height="50px" align="center" valign="bottom"><img src="${ctx}/images/btnSave.gif" width="65" height="20" id="btnSave"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnSubmit.gif" id="btnSubmit" width="65" height="20" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/instockList.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
