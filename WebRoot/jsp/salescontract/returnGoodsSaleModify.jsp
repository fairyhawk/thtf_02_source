<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退货单修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
		<!--
			$(document).ready(function(){
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
				});
			
		//-->
		$(function(){ 
			$.ajaxSetup({ 
  					async: false 
  				}); 
			//全选
				 $("#allchecked").click(function(){ 
					if($(this).attr("checked")){
						$("#table :checkbox").attr("checked", true);
					}else{
						$("#table :checkbox").attr("checked", false);
					} 
				 });
				// 删除
			$("#deleteBtn").click(function(){ 
				 if($("#table input:checked").length==0){
						alert("请先选择要删除的产品");
					 }else{  
						var i = 1;
						$("#table :checkbox").each(function(){  
							  if($(this).attr("checked")){
								$("#table tr:eq("+(i--)+")").remove();
							  }
							  i++;
						}); 

						moneyChange();
						$("#allchecked").attr("checked", false);

						if($("#table")){
							$("#table tr").removeClass("treven");
							$("#table tr:even").addClass("treven"); 
						}
					 }
			});
			// 添加发货明细
			$("#addBtn").click(function(){ 
				var id = "${sendGoodsId}";
				subwin = window.open('${ctx}/getReturnGoodsProductSelect.do?id='+id,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=300');
			});
			//返回
				$('#backBtn').click(function(){
					document.location.href='saleReturns.do';
				});
				// 保存  
				$("#bcsubmit").click(function(){ 
					$("#pd").val(0);
					var pd = checkMoney();
					if(pd == false){
						return false;
					}
					var repeat = $("#repeat").val()*1;
					if(repeat == 1){
						$.post('modifyReturnGoodsSale.do',$('#newReturnGoodsForm').serializeArray() ,waitResult,'html');
					}
				});
				// 提交   
				$("#tjsubmit").click(function(){
					$("#pd").val(1);
					var pd = checkMoney();
					if(pd == false){
						return false;
					}
					var repeat = $("#repeat").val()*1;
					if(repeat == 1){
						$.post('modifyReturnGoodsSale.do',$('#newReturnGoodsForm').serializeArray() ,waitResult,'html');
					}
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
				//默认库房
				$('#stockroomId option[value="${returnGoods.stockroomId}"]').attr('selected',true);
				//换库房
				$('#stockroomId').change(function(){ 
					$('#goodsName').text(' ');
					$('#goodsAddress').text(' ');
					$('#postcode').text(' ');
					$('#linkman').text(' ');
					$('#tel').text(' ');
					$('#mobile').text(' ');
					$("#stockRoomAddressId").val(' ');
				});
				// 打开收货地址选择页面 
				$("#selectAddress").click(function(){ 
					var stockroomId = $("#stockroomId").val();
					if(stockroomId!=""){
						subproduct = window.open('${ctx}/getAddressSelect.do?stockroomId='+stockroomId,'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370,width=1000,height=400');
					}else{
						alert("请先选择一个库房！");
					}
				});
				moneyChange();
			});
			
			// 退货金额   
			function moneyChange(){
				 var thSumMoney = 0;
				 $("#table tr:gt(0)").each(function(i){  
					var xsmoney = rmoney($(this).children("td:eq(6)").text());
					var thsNum = $(this).children("td:eq(12)").find("input").val(); 
					var thmoney = $(this).children("td:eq(13)");
					thmoney.text(formatMoney(FloatMul(xsmoney,thsNum),2));
					thSumMoney = FloatAdd(thSumMoney,FloatMul(xsmoney,thsNum));
					$(this).children("td:eq(1)").text(i+1);
				});
				$("#thSumMoney").text(formatMoney(thSumMoney,2));
			}
		// 接收小页面信息
		function addAdderss(str){
			if(str.length>0){
				var strAdderss = str.split("&");
				$("#goodsName").text(strAdderss[0]+" ");
				$("#goodsAddress").text(strAdderss[1]+" ");
				$("#linkman").text(strAdderss[2]+" ");
				$("#postcode").text(strAdderss[3]+" ");
				$("#tel").text(strAdderss[4]+" ");
				$("#mobile").text(strAdderss[5]+" ");
				$("#stockRoomAddressId").val(strAdderss[6]);
			}
		}
		//  金额验证 
		function checkMoney(){
			if($("#table tr").length==1){
				alert("请先添加产品");
				return false; 
			}
			var pd = $("#pd").val();
			var pdths = false;
			var pdths1 = true;
			$("#table tr:gt(0)").each(function(i){
				var thsNum = $(this).children("td:eq(12)").find("input").val()*1; 
				var xsmoney = rmoney($(this).children("td:eq(6)").text())*1;
				var ffcount = $(this).children("td:eq(7)").text()*1;
				var zdmoney = rmoney($(this).children("td:eq(9)").text())*1;
				var kpmoney = rmoney($(this).children("td:eq(10)").text())*1;
				var ythcount = $(this).children("td:eq(11)").text()*1;
				var pd1 = (xsmoney*ffcount)- zdmoney - (xsmoney*ythcount);
				var pd2 = (xsmoney*ffcount)- kpmoney - (xsmoney*ythcount);
				var pd = (thsNum*xsmoney)*1;
				var regExp = /^[0-9]\d*/;
				if (regExp.test(thsNum) == false) {
					alert('退货数只能输入自然数！');
					pdths1 = false;
				}
				if(thsNum == 0){
					
				}else{
					pdths = true;
				}
				if(pd*1>pd1*1){
				alert("该发货单必须无指定金额及开票金额！");
				pdths1 = false;
				return false;
				}
				if(pd*1>pd2*1){
					alert("该发货单必须无指定金额及开票金额！");
					pdths1 = false;
					return false;
				}
			});
			if(pdths1 == false){
				return false;
			}
			if(pdths == false && pdths1 == true){
				alert("退货数不能全为0");
				return false;
			}
			pdths = pdths1;
			var stockroomId = $("#stockroomId").val();
			if(stockroomId == ""){
				alert("请选择库房！");
				return false;
			}
			var stockRoomAddressId =$("#stockRoomAddressId").val();
			if(stockRoomAddressId==" "||stockRoomAddressId==""||stockRoomAddressId==null){
				alert("请选择收货地址！");
				return false;
			}
			var textarea = $.trim($("#textarea").val().replace(/[\u3000]/g," "));
			$("#textarea").val(textarea);
			if(textarea == null||textarea==""){
				alert("特殊说明不能为空！");
				return false;
			}else if(textarea.length > 200){
				alert("特殊说明不能大于200个汉字！");
				return false;
			}
			return pdths;
		}
		//添加产品
			function addProduct(prodArr) { 
				$.each(prodArr,function(i,product){ 
					var tr = $("<tr></tr>").appendTo("#table");
					tr.append("<td><input type='checkbox' id='prodCh'"+i+"/></td>").append("<td>"+(i+1)+"</td>"); 
					$.each(product.split(","),function(j,td){
						if(j==0){
							tr.append("<input  type='hidden'  name='productId' id='productId' value='"+td+"' style='width:60px;' />");
						}else if(j==1){
							tr.append("<td nowrap='nowrap' class='productCode'>"+td+"</td>");
						}else if(j==2){
							tr.append("<td nowrap='nowrap' >"+td+"&nbsp;</td>");
						}else if(j==3){
							tr.append("<td nowrap='nowrap' >"+td+"&nbsp;</td>");
						}else if(j==4){
							tr.append("<td nowrap='nowrap' >"+td+"&nbsp;</td>");
						}else if(j==5){
							tr.append("<td id='prices' nowrap='nowrap' style='text-align:right;'>"+formatMoney(td,2)+"&nbsp;<input  type='hidden'  name='price' id='price' value='"+td+"' style='width:60px;' /></td>");
						}else if(j==6){
							tr.append("<td nowrap='nowrap' style='text-align:right;'>"+td+"&nbsp;</td>");
						}else if(j==7){
							tr.append("<td id='fhmoney' nowrap='nowrap' style='text-align:right;'>"+formatMoney(td,2)+"&nbsp;</td>");
						}else if(j==8){
							tr.append("<td id='zdmoney' nowrap='nowrap' style='text-align:right;'>"+formatMoney(td,2)+"&nbsp;</td>");
						}else if(j==9){
							tr.append("<td id='kpmoney' nowrap='nowrap' style='text-align:right;'>"+formatMoney(td,2)+"&nbsp;</td>");
						}else if(j==10){
							tr.append("<td nowrap='nowrap' style='text-align:right;'>"+td+"&nbsp;</td>");
							tr.append("<td nowrap='nowrap' ><input type='text' onchange='moneyChange();' name='thcount' id='thcount' value='0' style='width:60px;' />&nbsp;</td>");
							tr.append("<td id='thmoney' nowrap='nowrap' style=' text-align:right; padding-right:12px; width:96px;'>"+0.00+"&nbsp;</td>");
						}
					}); 
					 
				});
				 moneyChange();
				 if($("#table")){
					$("#table tr").removeClass("treven"); 
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
			}
			
			//判断小页面传过来的产品是否存在
			function checkProduct(prod){ 
				if($("#table td.productCode").length==0){
					return false;
				}else{  
					var result = false;
					$("#table td.productCode").each(function(){  
						 if($(this).text()==prod.split(",")[1]){
						 	result = true;
						 }
					}); 
					return result;
				}
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
		//获取服务器返回结果
		function waitResult(result){
			if(result == 'false'){
				alert("退货单修改失败！");
			}else{
				document.location.href = 'saleReturns.do';
			}
		}
		</script>
</head>
<body>
<form id ="newReturnGoodsForm" name ="newReturnGoodsForm" action="modifyReturnGoodsSale.do" method="post">
  <input type="hidden" name="backId" id="backId" value="${returnGoods.id}" >
  </input>
  <input type="hidden" name="repeat" id="repeat" value="1" ></input>
  <input type="hidden" name="sendGoodsId" id="sendGoodsId" value="${returnGoods.sendGoodsId}" >
  </input>
  <input type="hidden" name="stockRoomAddressId" id="stockRoomAddressId" value="${returnGoods.stockroomAddressId}" >
  </input>
  <input type="hidden" name="pd" id="pd" value="1" >
  </input>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退货管理 &gt;&gt; 退货单修改</td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td><br/>
        <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%">产品分类名称</td>
            <td class="td_02" width="30%">${returnGoods.productTypeName}<input type="hidden" name="productTypeId" id="productTypeId" value="${returnGoods.productTypeId}" >&nbsp;</td>
            <td height="18" class="td_01" width="20%" >客户名称</td>
            <td class="td_02" width="30%">${returnGoods.customeName}
              <input type="hidden" name="customeId" id="customeId" value="${returnGoods.customeId}" >
              </input>
              <input type="hidden" name="customeName" id="customeName" value="${returnGoods.customeName}" >
              </input>
              &nbsp;</td>
          </tr>
        </table>
        <div style="width:100%; text-align:right">单位：元</div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
          <tr>
            <th nowrap="nowrap">选择</th>
            <th nowrap="nowrap">序号</th>
            <th nowrap="nowrap">产品编码</th>
            <th nowrap="nowrap">产品名称</th>
            <th nowrap="nowrap">规格型号</th>
            <th nowrap="nowrap">单位</th>
            <th nowrap="nowrap">销售单价</th>
            <th nowrap="nowrap">发货数</th>
            <th nowrap="nowrap">发货金额</th>
            <th nowrap="nowrap">指定金额</th>
            <th nowrap="nowrap">开票金额</th>
            <th nowrap="nowrap">已退货数</th>
            <th nowrap="nowrap">退货数</th>
            <th nowrap="nowrap" >&nbsp;&nbsp;&nbsp;退货金额&nbsp;&nbsp;&nbsp;</th>
          </tr>
          <logic:present name="list">
            <logic:iterate id="ReturnsProduct" name="list" indexId="index">
              <tr>
                <input type="hidden" name="productId" id="productId" value="${ReturnsProduct.productId}" >
                </input>
                <td nowrap="nowrap" width="30px"><input type="checkbox" name="checkbox" id="checkbox" /></td>
                <td nowrap="nowrap" >${index+1}&nbsp;</td>
                <td nowrap="nowrap" class='productCode'>${ReturnsProduct.productCode}</td>
                <td nowrap="nowrap" >${ReturnsProduct.productName}&nbsp;</td>
                <td nowrap="nowrap" >${ReturnsProduct.productType}&nbsp;</td>
                <td nowrap="nowrap" >${ReturnsProduct.productUnit}&nbsp;</td>
                <td nowrap="nowrap" style='text-align:right;'><fmt:formatNumber value="${ReturnsProduct.price}" pattern="#,##0.00#"/>
                  &nbsp;
                  <input  type="hidden"  name="price" id="price" value="${ReturnsProduct.price}" style="width:60px;" /></td>
                <td nowrap="nowrap" style='text-align:right;'>${ReturnsProduct.ffcount}&nbsp;</td>
                <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${ReturnsProduct.ffMoney}" pattern="#,##0.00#"/>
                  &nbsp;</td>
                <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${ReturnsProduct.zdmoney}" pattern="#,##0.00#"/>
                  &nbsp;</td>
                <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${ReturnsProduct.kpmoney}" pattern="#,##0.00#"/>
                  &nbsp;</td>
                <td nowrap="nowrap" style='text-align:right;'>${ReturnsProduct.yBackCount}&nbsp;</td>
                <td nowrap="nowrap" ><input type="text" onchange="moneyChange();" name="thcount" id="thcount" value="${ReturnsProduct.thcount}" style="width:60px;" />
                  &nbsp; </td>
                <td id="thmoney" nowrap="nowrap" style=" text-align:right; padding-right:12px; width:96px;">0.00</td>
              </tr>
            </logic:iterate>
          </logic:present>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="moneyTable" style="padding-top:5px">
          <tr>
            <td nowrap="nowrap" align="left" width="30px">&nbsp;
              <input type="checkbox" name="checkbox" id="allchecked" /></td>
            <td nowrap="nowrap" align="left">&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp; <a href="#"><img id="deleteBtn" src="${ctx}/images/btnDelete.gif" width="65" height="20" align="absmiddle" /></a>&nbsp;&nbsp;&nbsp; <a href="#"><img id="addBtn" src="${ctx}/images/btnAdd.gif" width="65" height="20" align="absmiddle"/></a> </td>
            <td nowrap="nowrap" align="right">退货金额合计&nbsp;&nbsp;&nbsp;</td>
            <td nowrap="nowrap" width="96px" align="right"><span id="thSumMoney" >0.00</span>元</td>
          </tr>
        </table>
        <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">库房名称</td>
            <td class="td_02" colspan="3" width="30%"><select id="stockroomId" name="stockroomId" >
                <logic:iterate id="stockroomList" name="stockroomList" indexId="index">
                  <option value="${stockroomList.stockRoomId}">${stockroomList.stockRoomName}</option>
                </logic:iterate>
              </select>
              <div style="float:right;padding-right:50%"><a href="#"><img id="selectAddress" src="${ctx}/images/btnSHDZ.gif" align="absmiddle"/></a></div></td>
          </tr>
          <tr>
            <td class="td_01" width="20%" height="18px" >货物接收单位名称</td>
            <td colspan="3" class="td_02" id="goodsName">${returnGoods.untiName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" width="20%" height="18px" >收货地址</td>
            <td colspan="3" class="td_02" id="goodsAddress">${returnGoods.address}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" width="20%" height="18px" >联系人</td>
            <td class="td_02" width="30%" id="linkman">${returnGoods.linkman}&nbsp;</td>
            <td class="td_01" width="20%" >邮编</td>
            <td class="td_02" width="30%" id="postcode">${returnGoods.postcode}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" width="20%" height="18px">电话</td>
            <td class="td_02" width="30%" id="tel">${returnGoods.tel}&nbsp;</td>
            <td class="td_01" width="20%">手机</td>
            <td class="td_02" width="30%"  id="mobile">${returnGoods.mobile}&nbsp;</td>
          </tr>
        </table>
        <br/>
        <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04" ><textarea name="textarea" id="textarea" cols="100" rows="4">${returnGoods.text}&nbsp;</textarea></td>
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
              同意
              &nbsp;&nbsp;
              <input type="checkbox" name="sellMajIdea0" id="sellMajIdea0" disabled="true" />
              不同意</td>
          </tr>
          <tr>
            <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
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
              同意
              &nbsp;&nbsp;
              <input type="checkbox" name="opeMajIdea0" id="opeMajIdea0" disabled="true" />
              不同意</td>
          </tr>
          <tr>
            <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                  <td style="padding:5px 0 5px 0;">${tf:replaceBr(returnGoods.opeMajText)}&nbsp;</td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="20px">签字：${returnGoods.opeMajName}</td>
            <td>日期：${returnGoods.opeMajDate}</td>
          </tr>
          <tr>
            <td height="20px" colspan="2">&nbsp;</td>
          </tr>
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
                  <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
                  <td style="padding:5px 0 5px 0;">${tf:replaceBr(returnGoods.stkMajText)}&nbsp;</td>
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
    <tr>
    <tr>
      <td></td>
      <td height="50px" align="center" valign="bottom">
      <img id="bcsubmit" src="${ctx}/images/btnSave.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      <img id="tjsubmit" src="${ctx}/images/btnSubmit.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      <a id="fh" href="javascript:window.location = '${ctx}/saleReturns.do'" ><img src="${ctx}/images/btnBack.gif" /></a> </td>
      <td></td>
    </tr>
  </table>
</form>
<br />
</body>
</html>
