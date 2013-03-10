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
		});
	//将小页面被选中的产品信息带到大页面
	var myArrayProduct=new Array();
	function selectProductList() 
	{
		var productChecked =document.getElementsByName("checkProduct");
		// 定义是否有产品被选中
		var count = 0;
		// 把被选中的产品传入数组
		for(var i=0;i<productChecked.length;i++){
			if(productChecked[i].checked==true){
				toParentsValue(productChecked[i]);
				count = 1 ;
			}
		}

		// 没有产品被选择
		if(count==0){
			alert("请选择产品");
			return;
		}
		var typeid=$("#prodTypeId").val();
		//将值传回父窗口
		opener.newWindow(myArrayProduct,typeid);
		window.close();
		}
    // 把选中产品的一条记录传到父页面
    function toParentsValue(obj) 
    {	
    	var delimiter=$("#delimiter").val();//分隔符
		var records = obj.value;
		var arrProdInfo = new Array();
		arrProdInfo = records.split(delimiter);
		var prodId				= arrProdInfo[0];
		var prodCode			= arrProdInfo[1];
		var prodName			= arrProdInfo[2];
		var prodType            = arrProdInfo[3];
		var prodUnit            = arrProdInfo[4];
		var salesCanUse         = arrProdInfo[5];
		var limitPrice			= arrProdInfo[6];//限价
		if(obj.checked==true) {
			myArrayProduct.push([prodId,prodCode,prodName,prodType,prodUnit,salesCanUse,limitPrice]);
		}
	}


	//检索产品信息
	function searchProdInfo(){
		document.forms[0].action = "getProduct.do?";
		$("#frmProd").submit();
	}
	</script>
	</head>
	<body>
		<form action="getProduct.do?" method="post" id ="frmProd">
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
												style="width:240px;" maxlength="40" value="${searchForm.prodName}">
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											规格型号
										</td>
										<td class="td_02" width="30%">
											<input type="text" id="prodType" name="prodType" 
												style="width:240px;" maxlength="40" value="${searchForm.prodType}">
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
						<input type="hidden" id="delimiter" value="'${date}'"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="valueList">
							<tr>
								<th nowrap="nowrap" width="36px">
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
									销售可用数
								</th>	
								<th nowrap="nowrap">
									限价
								</th>					
							</tr>
							<logic:present name="prodInfoList">
								<logic:iterate id="prodInfoList" name="prodInfoList">
									<tr id="tr${prodInfoList.prodId}">
										<td>
										&nbsp;<input type="checkbox" name="checkProduct"
							value="${prodInfoList.prodId}'${date}'${prodInfoList.prodCode}'${date}'${prodInfoList.prodName}'${date}'${prodInfoList.prodType}'${date}'${prodInfoList.prodUnit}'${date}'${prodInfoList.salesCanUse}'${date}'<fmt:formatNumber value="${prodInfoList.limitPrice}" pattern="#,##0.00"/>"
							>
										</td>
										<td nowrap="nowrap" id="tdNum" style="display:none;border-left:1px solid #c2c2c2;">
											
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
									    <td nowrap="nowrap" width="84px" style="text-align:right">
											${prodInfoList.salesCanUse}&nbsp;
										</td>
										<td nowrap="nowrap" width="84px" style="text-align:right">
											<fmt:formatNumber value="${prodInfoList.limitPrice}" pattern="#,##0.00"/>&nbsp;
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
							<tr>
								<td align="right">
									<%@ include file="/jsp/common/newPage.jsp"%>
								</td>
							</tr>
						</table>
						<table align="center">
							<tr>
								<td height="45px" colspan="2" align="center" valign="bottom">
									<a href="#" id="selectProduct" onClick="selectProductList();" > <img
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
