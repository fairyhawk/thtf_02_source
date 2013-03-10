<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>发货单选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
			.treven {
				background-color: #f3fbff;
			}
			.over {
				background-color: #ECECEC;
			}
			.STYLE1 {color: #FF0000} 
		</style>
		<script language="JavaScript"> 
			$(document).ready(function(){
				if($("#orderTable")){
					$("#orderTable tr:even").addClass("treven");
					$("#orderTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}

				//提交
				$("#orderAdd").click(function(){
					var orderId = "";
					//var orderType ="";
					var checkResult = true;
					var addr;

					var isfirst=true;
					var firstTr;

					$('#orderTable input:checked').each(function(){  
						var tr = $(this).parent().parent(); 
						 
						if(isfirst){ 
							firstTr = tr;
							isfirst =false;
						}

						if(addr){
							 
							checkResult = (addr == tr.children("td:eq(6)").text());
							 
							if(checkResult){
								addr = tr.children("td:eq(6)").text();
							}else{
								return false;
							}
						}else{ 
							addr = tr.children("td:eq(6)").text();
						}

						orderId = orderId + $(this).val() + ",";
						
						//orderType = orderType + tr.children("td:eq(2)").text() + ","; 

					});
					 
					if(checkResult){

						if(orderId){ 
							$.ajax({
								type: "POST",
								url: "${ctx}/getOrderDetail.do",
								async: false,
								data: "addPara.orderId="+orderId,
								success: function(msg){ 
									if(msg){ 
										opener.addOrder(msg);
										
										if(opener.checkSendGoodsNum){
											var str = firstTr.children("td:eq(0)").find("input:hidden").val().split(",");
										  
											$("#customerName",window.opener.document).text(firstTr.children("td:eq(4)").text());
											$("#transportWay",window.opener.document).text(firstTr.children("td:eq(7)").text());
 
 
											if(firstTr.children("td:eq(7)").text()=="自提 "){   
												 
												$("#own",window.opener.document).show();
												$("#takeName",window.opener.document).text(str[0]);
												$("#takeNumber",window.opener.document).text(str[1]);
											}else{ 
												 
												$("#own",window.opener.document).hide();
												$("#takeName",window.opener.document).text("");
												$("#takeNumber",window.opener.document).text("");
											}

											$("#customerAddressName",window.opener.document).text(firstTr.children("td:eq(5)").text());
											$("#customerAddress",window.opener.document).text(firstTr.children("td:eq(6)").text());
											
											$("#postcode",window.opener.document).text(str[2]);
											$("#linkman",window.opener.document).text(str[3]);
											$("#tel",window.opener.document).text(str[4]);
											$("#mobile",window.opener.document).text(str[5]);

											$("#stockroomId",window.opener.document).attr("value",str[6]);


											$("#addParaCustomerName",window.opener.document).attr("value",firstTr.children("td:eq(4)").text());
											$("#addParaCustomerTransportWay",window.opener.document).attr("value",str[7]);
											$("#addParaTakeName",window.opener.document).attr("value",str[0]);
											$("#addParaTakeNumber",window.opener.document).attr("value",str[1]);
											$("#addParaCustomerAddressName",window.opener.document).attr("value",firstTr.children("td:eq(5)").text());

											$("#addParaCustomerAddress",window.opener.document).attr("value",firstTr.children("td:eq(6)").text());
											$("#addParaLinkman",window.opener.document).attr("value",str[3]);
											$("#addParaPostcode",window.opener.document).attr("value",str[2]);
											$("#addParaTel",window.opener.document).attr("value",str[4]);
											$("#addParaMobile",window.opener.document).attr("value",str[5]);

										}
										//选择完毕关闭小页面
										window.close();
									}else{
										alert("提交错误！");
									}
								}
							});  
						}else{
							alert("未选择任何发货单！");
						} 
					}else{
						alert("请选择相同的发货地址！");
					}
				});

				$("input:checkbox").click(function(){   
					if($(this).attr("checked")){  
						if(opener.checkSendGoods($(this).val(),$(this).parent().parent().children("td:eq(6)").text())){
							alert("此发货单已存在或发货地址不相同！");
							$(this).attr("checked",false);
						} 
					}
				});


			}); 
		</script> 
	</head>
<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td><br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="50%" height="18">产品分类名称</td>
          <td class="td_04">${productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="orderTable">
        <tr>
          <th nowrap="nowrap">选择</th>
          <th nowrap="nowrap">单据类型</th>
          <th nowrap="nowrap">发货单号</th>
          <th nowrap="nowrap">库房名称</th>
          <th nowrap="nowrap">客户名称</th>

          <th nowrap="nowrap">货物接收单位名称</th>
          <th nowrap="nowrap">发货地址</th>
          <th nowrap="nowrap">货运方式</th>
          <th nowrap="nowrap">申请日期</th>
          <th nowrap="nowrap">要求发货日期</th>

          <th nowrap="nowrap">人员名称</th>
        </tr>
        <logic:iterate id="order" name="resultList" indexId="index">
	  <tr>
		<td>
			<input type="checkbox" id="checkbox7" value="${order.orderId}"/>&nbsp;
			<input type="hidden" id="takeName${index}" value="${order.takeName},${order.takeNumber},${order.postcode},${order.linkman},${order.tel},${order.mobile},${order.stockroomId},${order.transportWay}"/> 
		</td>
		<td nowrap="nowrap" width="72px" height="18px">
			<c:if test="${order.orderType==1}">发货单</c:if>
			<c:if test="${order.orderType==2}">返厂单</c:if>
			<c:if test="${order.orderType==3}">移库单</c:if>
			<c:if test="${order.orderType==4}">样品借出单</c:if>
		</td>
		<td nowrap="nowrap" width="86px">${order.orderId}</td>
		<td nowrap="nowrap" title="${order.stockroomName}"><div class="ellipsis_div" style="width:86px;">${order.stockroomName}</div></td> 
		<td nowrap="nowrap" title="${order.customerName}"><div class="ellipsis_div" style="width:86px;">${order.customerName}</div></td>

		<td nowrap="nowrap" title="${order.customerAddressName}"><div class="ellipsis_div" style="width:103px;">${order.customerAddressName}</div></td>
		<td nowrap="nowrap" title="${order.customerAddress}"><div class="ellipsis_div" style="width:96px;">${order.customerAddress}</div></td>
		<td nowrap="nowrap" width="60px">
			<c:if test="${order.transportWay==0}">无指定</c:if>
			<c:if test="${order.transportWay==1}">自提</c:if>
			<c:if test="${order.transportWay==2}">快递</c:if>
			<c:if test="${order.transportWay==3}">汽运</c:if>
			<c:if test="${order.transportWay==4}">铁运</c:if>

			<c:if test="${order.transportWay==5}">航空</c:if>
			<c:if test="${order.transportWay==6}">海运</c:if>
			<c:if test="${order.transportWay==7}">中铁</c:if>
			<c:if test="${order.transportWay==8}">发货单</c:if>
			<c:if test="${order.transportWay==9}">市内送货</c:if>
			<c:if test="${order.transportWay==10}">市内快递</c:if> 
		</td>
		<td nowrap="nowrap" width="73px">${order.date}</td>
		<td nowrap="nowrap" width="77px">${order.requestDate}</td>

		 
		<td nowrap="nowrap" width="56px">${order.userName}</td>
	  </tr>
      </logic:iterate>
      </table>
    </td>
  </tr>
  <tr>
    <td height="50px" align="center" valign="bottom">
		<img src="${ctx}/images/btnChoice.gif" id="orderAdd" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	</td>
  </tr>
</table>
</body>
</html>
