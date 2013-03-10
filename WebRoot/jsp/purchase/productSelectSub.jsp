<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        
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
	<script type="text/javascript">
	// 判断是否有产品，有则产品分类默认被选中
	
	$(document).ready(function(){
		if($("#valueList")){
			$("#valueList tr:even").addClass("treven");
			$("#valueList tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
		$("#selectProduct").click(function(){
		if($("#productTypeId",window.opener.document).find("option:selected").text()!="${searchForm.prodTypeName}"){
			alert("产品分类已改变,请重新选择	");
			window.close();
			return;
		}
		
			if($("#valueList input:checked[name='id']").length==0){alert("请选择产品！");return;}
			$("#valueList input:checked[name='id']").each(function(){//循环 chenkbox选中 
			opener.getOpenerValOfProduct($("#tr"+$(this).attr("id")).html(),$(this).attr("id"));
			window.close();
			});
		});
	});

	//检索产品信息
	function searchProdInfo(){
		document.forms[0].action = "getProdInfoByCondition.do?";
		$("#frmProd").submit();
	}
	</script>
	</head>
	<body>
		<form action="getProdInfoByCondition.do?" method="post" id ="frmProd">
			<table width="96%" border="0" cellpadding="0" cellspacing="0"
				align="center" id="checkCondition" style="display:block;">
				<tr>
					<td align="center">
						<br />
						<div class="mo_wp">
							<div style="display: ; " class="mo_con">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="biao3">
									<tr>
										<td class="td_01" width="20%">
											产品分类名称
										</td>
										<td class="td_02" width="30%">
											<input type="hidden" id="prodTypeName" name="prodTypeName" value="${searchForm.prodTypeName}"/>
											<input type="hidden" id="prodTypeId" name="prodTypeId" value="${searchForm.prodTypeId}"/>
												${searchForm.prodTypeName}
										</td>
										<td class="td_01" width="20%">
											产品系列名称
										</td>
										<td class="td_02" width="30%">
											<label>
												<select name="prodSerieId" id="prodSerieId"
													style="width: 132px;" >
													<option value="" selected="selected">
														--请选择--
													</option>
													<logic:present name="prodSerieList">
														<logic:iterate id="prodSerieList" name="prodSerieList">
															<option value=${prodSerieList.id}>${prodSerieList.name}</option>
														</logic:iterate>
													</logic:present>
												</select>
											</label>
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											产品编码
										</td>
										<td class="td_02">
											<input type="text" id = "prodCode" name="prodCode"
											 style="width:120px;"  value="${searchForm.prodCode}"/>
										</td>
										<td class="td_01" width="20%">
											产品名称
										</td>
										<td class="td_02" width="30%">
											<input type="text" id = "prodName" name="prodName" 
												maxlength="40" value="${searchForm.prodName}">
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											规格型号
										</td>
										<td class="td_02" width="30%">
											<input type="text" id="prodType" name="prodType" 
												style="width: 120px;" maxlength="40" value="${searchForm.prodType}">
										</td>
										<td class="td_01" width="20%">
											&nbsp;
										</td>
										<td class="td_02" width="30%">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center" style="height:30px;">
											<a href="#" onclick="searchProdInfo();"><img
													src="${ctx}/images/btn_JianSuo.gif" /> </a>
										</td>
									</tr>
								</table>
								</form>
							</div>
							<div class="mo_title" onclick="">
								<div style="text-align:center">
									<img src="${ctx}/images/shang_sj.png" />
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						<br />
					<c:if test = "${listSize != 0}">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao1" id="valueList" style="border-left:1px solid #ffffff;">
							<tr>
								<th nowrap="nowrap" width="30px" style="border-left:1px solid #c2c2c2;">
									选择
								</th>
								<th nowrap="nowrap">
									产品编码
								</th>
								<th nowrap="nowrap">
									产品名称
								</th>
								<th nowrap="nowrap">
									规格型号
								</th>
								<th nowrap="nowrap">
									单位
								</th>
								<th nowrap="nowrap">
									库存单价
								</th>
								<th nowrap="nowrap">
									库存总数
								</th>
								<th nowrap="nowrap">
									需求数
								</th>							
							</tr>
							<logic:present name="prodInfoList">
								<logic:iterate id="prodInfoList" name="prodInfoList">
									<tr id="tr${prodInfoList.prodId}">
										<td style="border-left:1px solid #c2c2c2;">
											<input type="checkbox" name="id" id="${prodInfoList.prodId}"
												value="${prodInfoList.prodId}">
										</td>
										<td nowrap="nowrap" id="tdNum" style="display:none;text-align:right;padding-right:5px">
											
										</td>
										<td nowrap="nowrap" width="66px">
											${prodInfoList.prodCode}
										</td>
										<td nowrap="nowrap" width="240px">
											${prodInfoList.prodName}
										</td>
										<td nowrap="nowrap" width="240px">
											${prodInfoList.prodType}
										</td>
										<td nowrap="nowrap" width="24px">
											${prodInfoList.prodUnit}
										</td>
										<td nowrap="nowrap" id="buyCount" style="display:none">
											<input type="text" style="width:80px" value="0" id="buyCount${prodInfoList.prodId}"  maxlength="11" onclick="range(this)" onblur="buyCountBlur(this)"/>&nbsp;
										</td>
										<td nowrap="nowrap" id="buyPrice" style="display:none">
											<input type="text" value="0" style="width:80px" id="buyPrice${prodInfoList.prodId}"  maxlength="11" onclick="range(this)" onblur="buyPriceBlur(this)"/>&nbsp;
										</td>
										<td nowrap="nowrap" id="buyMoney" style="display:none;text-align:right;padding-right:5px">
											0.00
										</td>
										<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px">
											<fmt:formatNumber value="${prodInfoList.stockUnitPrice}" type="number" pattern="#,##0.00#"/>
										</td>
										<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px">
											${prodInfoList.stockTotalCount}
										</td>
										<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px">
											${prodInfoList.demandCount}
										</td>
										<td nowrap="nowrap" id="promotion" style="display:none;text-align:right;padding-right:5px">
											0
										</td>
										<td nowrap="nowrap" style="display:none;text-align:right;padding-right:5px">
											0
										</td>
										<td nowrap="nowrap" style="display:none;">
											${prodInfoList.noTaxOfPrice}
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<td align="right">
									<br />
									<%@ include file="/jsp/common/newPage.jsp"%>
									&nbsp;&nbsp;
								</t	d>
							</tr>
						</table>
						<table align="center">
							<tr>
								<td height="45px" colspan="2" align="center" valign="bottom">
									<a href="#" id="selectProduct" > <img
											src="${ctx}/images/btnChoice.gif" width="65" height="20" />
									</a>
								</td>
							</tr>
						</table>
				</c:if>
					</td>
				</tr>
			</table>
	</body>
</html>
