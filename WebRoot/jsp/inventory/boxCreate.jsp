<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>装箱单新建修改</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
	<style type="text/css">
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		.STYLE1 {color: #FF0000}
		-->
	</style>
	<script language="JavaScript"> 
		$(document).ready(function(){ 
			var subwin;
			window.onbeforeunload=function()
		    { 
		        if(subwin!=undefined){
		            subwin.close();
		        }
		    }
			//
			if("${box.id}"!=null && "${box.customerTransportWay}"==1){
				$("#own").show();
			}
 
			//打开选择页面
			$("#orderAdd").click(function(){
				var productTypeId = $("#productTypeSelect").val();

				if(productTypeId!=""){ 
					subwin = window.open("${ctx}/getOrder.do?addPara.productTypeId="+productTypeId+"&addPara.productTypeName="+encodeURI($("#productTypeName").val()),"newwindow","toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=350");
				}else{
					alert("请先选择产品分类！");
				}
			  
			});
			
			//传产品分类名称
			$("#productTypeSelect").change(function(){
				 
				 if($("#orderTable tr").length==0){ 
					$("#productTypeId").val($(this).val()); 
				 }else{ 
					if( $("#productTypeId").val() != $("#productTypeSelect").val()){
						alert("已选择发货单不能改变产品分类！");
						//document.getElementById('stockroomSelect').selectedIndex = 2;
						//$("#stockroomSelect").get(0).selectedIndex = 2;
						 
						$("#productTypeSelect").attr('value',$("#productTypeId").val());
					}
				 } 
				 
				 $("#productTypeName").val($(this).find("option:selected").text()); 
			});

			//全选
			 $("#orderAll").click(function(){ 
				if($(this).attr("checked")){
					$("#orderTable :checkbox").attr("checked", true);
				}else{
					$("#orderTable :checkbox").attr("checked", false);
				} 
			 });

			 //删除 产品
			 $("#delOrder").click(function(){  

				 if($("#orderTable input:checked").length==0){
					alert("请先选择要删除的产品！");
				 }else{
					if(confirm("将删除此产品所属发货单的所有产品,确认是否删除？")){  
						var orderArr = new Array();
						$("#orderTable :checkbox").each(function(j){  
							  if($(this).attr("checked")){ 
								orderArr.push($("#orderTable tr:eq("+j+")").children("td:eq(1)").text()); 
							  } 
						}); 
						
						 
						$("#orderTable tr").each(function(z){  
							for(var i=0;i<orderArr.length;i++){ 
								if($(this).children("td:eq(1)").text()==orderArr[i]){
									$(this).remove(); 
								}
							} 
						});
					 
						$("#orderAll").attr("checked", false); 
					}
				 }
			 });

			 $("#addParaTransportWay").change(function(){
				if($("#addParaTransportWay").val()=="1"){
					$("#star").hide();
				}else{ 
					$("#star").show();
				} 

			 });

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

			 //验证小数点后最多两位
			 $.validator.addMethod("checkPoint", function(value, element) {
				var result = false; 
				var rule=/^[0-9]+([.]{1}[0-9]{1,2})?$/;
				
				if(rule.test(value)){ 
					result = true;
				}  

				return this.optional(element) || result;
			 }, "发货费用小数点后最多有两位");


			 $("#addForm").validate({
				rules: { 
					"count": {
						digits: true,
						min: 0
					},
					"addPara.count": {
						digits: true,
						min: 0
					},
					"addPara.money": {
						checkPoint: true,
						min: 0
					},
					"addPara.money":{
						max:10000
					}
				},
				messages: {
					"count": "配货数必须为自然数",
					"addPara.count": "装箱件数必须为自然数" ,
					"addPara.money":"请输入正确的发货费用"
						} ,
				errorClass: "invalid",
				onchange: true
			 }); 

			 if("${errorMsg}" != ""){
				alert("${errorMsg}");
			 }
			
			changeColor();
		}); 

		//提交验证
		function checkSubmit(){

			if($("#orderTable tr").length==0){ 
				alert("请先添加发货单！");
				return false;
			}

			if($("#addParaTransportWay").val()==""){ 
				alert("请选择货运方式！");
				return false;
			}
			 
			
			if($("#addParaTransportWay").val()!="1" && $("#logisticsId").val()==""){ 
				alert("请选择物流公司！");
				return false;
			} 
			
			if($("#addParaCount").val()==""){ 
				alert("请填写装箱件数！");
				return false;
			}
			//if($("#addParaMoney").val()){ 
			//	alert("请填写预计发货费用！");
			//	return false;
			//}
			if($("#addParaMoney").val()==""){ 
				$("#addParaMoney").val(0);
			}
			
			var result = true;
			if( $("#submitType").val()!=1){
				$("#orderTable tr").each(function(j){   
					var count = $(this).children("td:eq(8)").find("input").val();
					var sendCount = $(this).children("td:eq(7)").text();
					 
					if(count*1!=sendCount*1){
						alert("配货数必须等于发货数！");
						result = false;
						return false;
					}
				});
			}
			

			if(result){
				$("#addForm").submit(); 
			} 
			 
		} 

		//判断小页面传过来的发货单是否存在
		function checkSendGoods(orderId,addr){ 
			if($("#orderTable tr").length==0){
				return false;
			}else{ 
				var result = false;
				$("#orderTable tr").each(function(){   
					 if($(this).children("td:eq(1)").text()==orderId||$.trim($("#customerAddress").text())!=$.trim(addr)){
						result = true;
					 }
					 
				}); 
				return result;
			}
		}

		//判断是否有产品
		function checkSendGoodsNum(){ 
			if($("#orderTable tr").length==0){
				return false;
			}else{  
				return true;
			}
		}

		var num =0;
		if("${productListSize}" != ""){
			i = "${productListSize}"*1;
		}

		//添加合同
		function addOrder(orderArr) {  
			$.each(orderArr.split("&"),function(k,order){

				var tr = $("<tr></tr>").appendTo("#orderTable");

				tr.append("<td style='width:30px'><input type='checkbox' id='orderCh'"+num+"/></td>"); 
				
				$.each(order.split(","),function(j,td){  
					if(j==0){
						tr.append("<input type='hidden'  name='addPara.orderTypeArr' value='"+td+"'/>"); 
					}else if(j==1){
						tr.append("<input type='hidden'  name='addPara.orderIdArr' value='"+td+"'/>");
						tr.append("<td>"+td+"</td>");
					}else if(j==8){  
						tr.append("<td width='92px'><input type='text' name='count' id='count'"+num+" value='0' style='width:80px'/>&nbsp;</td>"); 
						tr.append("<td style='text-align:right;'>"+td+"&nbsp;</td>");  
					}else if(j==7){
						tr.append("<td style='text-align:right;'>"+td+"&nbsp;</td>");  
					}else{
						tr.append("<td>"+td+"&nbsp;</td>"); 
					}
				});  

				num++;
				 
			}); 

			//隔行换色
			changeColor();

		}

		//隔行换色
		function changeColor(){
			if($("#orderTable")){
				$("#orderTable tr:odd").addClass("treven");
				$("#orderTable tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			} 
		}
			
	</script> 
</head>
<body>
<html:form method="post" action="addBox" styleId="addForm">
<input type="hidden" id="submitType" name="addPara.submitType"/>
<input type="hidden" id="stockroomId" name="addPara.stockroomId" value="${box.stockroomId}"/>
<input type="hidden" name="addPara.id" value="${box.id}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center">
		<img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 装箱单管理 &gt;&gt; <c:if test="${box.id==null}">新建</c:if>装箱单<c:if test="${box.id!=null}">修改</c:if>
		
	</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 产品分类名称</td>
          <td class="td_02">
			<input type="hidden" id="productTypeName" value="${box.productTypeName}"/>
			<input type="hidden" id="productTypeId" value="${box.productTypeId}"/>
			<html:select styleId="productTypeSelect" property="addPara.productTypeId" value="${box.productTypeId}" style=" width:126px">
				<html:option value="">--请选择--</html:option> 
				<html:options collection="productTypeList" property="id" labelProperty="name"/>
			</html:select>
          </td>
          <td class="td_01"></td>
          <td class="td_02"></td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1">
		<thead>
			<tr>
			  <th nowrap="nowrap" width="30px">选择</th>
			  <th nowrap="nowrap">发货单号</th>
			  <th nowrap="nowrap">库房名称</th>
			  <th nowrap="nowrap">产品编码</th>
			  <th nowrap="nowrap">产品名称</th>

			  <th nowrap="nowrap">规格型号</th>
			  <th nowrap="nowrap">单位</th>
			  <th nowrap="nowrap">发货数</th>
			  <th nowrap="nowrap" width="92px">配货数</th>
			  <th nowrap="nowrap">库存数</th>
			</tr>
		</thead>
		<tbody id="orderTable">
			<c:if test="${boxProductList!=null}">
			<logic:iterate id="product" name="boxProductList" indexId="index">
				<tr>
					<input type='hidden' name='addPara.orderTypeArr' value='${product.orderType}'/>
					<input type='hidden' name='addPara.orderIdArr' value='${product.orderId}'/>
					<td ><input type='checkbox' id='orderCh${index}'/></td> 
					<td nowrap="nowrap" >${product.orderId}</td>
					<td nowrap="nowrap" class='productCode'>${product.stockroomName}&nbsp;</td>
					<td nowrap="nowrap" >${product.productCode}&nbsp;</td>
					<td nowrap="nowrap" >${product.productName}&nbsp;</td>

					<td nowrap="nowrap" >${product.productType}&nbsp;</td>
					<td nowrap="nowrap">${product.productUnit}&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;">${product.count}&nbsp;</td>
					<td width="92px"><input type='text' name='count' id='count${index}' value='0' style='width:80px'/>&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;">${product.num}&nbsp;</td>
				</tr>
			</logic:iterate>
			</c:if> 
		</tbody>
      </table>
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="40" height="25">&nbsp;<input type="checkbox" name="checkbox5" id="orderAll" /></td>
          <td>全选&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/btnDelete.gif" id="delOrder" align="absmiddle" />&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/btnAdd.gif" id="orderAdd" width="65" height="20" align="absmiddle" /></td>
        </tr>
      </table><br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
	  <input type="hidden" name="addPara.customerName" id="addParaCustomerName" value="${box.customerName}"/>
	  <input type="hidden" name="addPara.customerTransportWay" id="addParaCustomerTransportWay" value="${box.customerTransportWay}"/>
	  <input type="hidden" name="addPara.takeName" id="addParaTakeName" value="${box.takeName}"/>
	  <input type="hidden" name="addPara.takeNumber" id="addParaTakeNumber" value="${box.takeNumber}"/>
	  <input type="hidden" name="addPara.customerAddressName" id="addParaCustomerAddressName" value="${box.customerAddressName}"/>

	  <input type="hidden" name="addPara.customerAddress" id="addParaCustomerAddress" value="${box.customerAddress}"/>
	  <input type="hidden" name="addPara.linkman" id="addParaLinkman" value="${box.linkman}"/>
	  <input type="hidden" name="addPara.postcode" id="addParaPostcode" value="${box.postcode}"/>
	  <input type="hidden" name="addPara.tel" id="addParaTel" value="${box.tel}"/>
	  <input type="hidden" name="addPara.mobile" id="addParaMobile" value="${box.mobile}"/>

      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">客户名称</td>
          <td class="td_02" width="30%" id="customerName">${box.customerName}&nbsp;</td>
          <td class="td_01" width="20%">货运方式</td>
          <td class="td_02" width="30%" id="transportWay">
			<c:if test="${box.customerTransportWay==0}">无指定</c:if>
			<c:if test="${box.customerTransportWay==1}">自提</c:if>
			<c:if test="${box.customerTransportWay==2}">快递</c:if>
			<c:if test="${box.customerTransportWay==3}">汽运</c:if>
			<c:if test="${box.customerTransportWay==4}">铁运</c:if>

			<c:if test="${box.customerTransportWay==5}">航空</c:if>
			<c:if test="${box.customerTransportWay==6}">海运</c:if>
			<c:if test="${box.customerTransportWay==7}">中铁</c:if>
		 
			<c:if test="${box.customerTransportWay==8}">市内送货</c:if>
			<c:if test="${box.customerTransportWay==9}">市内快递</c:if>&nbsp;
		</td>
        </tr>  
        <tr id="own" style="display:none">
          <td class="td_01" height="18px">提货人姓名</td>
          <td class="td_02" id="takeName">${box.takeName}&nbsp;</td>
          <td class="td_01">身份证号码</td>
          <td class="td_02" id="takeNumber">${box.takeNumber}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02" id="customerAddressName">${box.customerAddressName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td colspan="3" class="td_02" id="customerAddress">${box.customerAddress}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td class="td_02" id="linkman">${box.linkman}&nbsp;</td>
          <td class="td_01" >邮编</td>
          <td class="td_02" id="postcode">${box.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">电话</td>
          <td class="td_02" id="tel">${box.tel}&nbsp;</td>
          <td class="td_01">手机</td>
          <td class="td_02" id="mobile">${box.mobile}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span id="star" style="color:#FF0000">*</span> 物流公司名称</td>
          <td class="td_02" >
			<html:select styleId="logisticsId" property="addPara.logisticsId" value="${box.logisticsId}" style=" width:288px">
				<html:option value="">--请选择--</html:option> 
				<html:options collection="logisticsResult" property="id" labelProperty="name"/>
			</html:select>
		  </td>
          <td class="td_01"><span style="color:#FF0000">*</span> 要求货运方式</td>
          <td class="td_02" >
			<html:select styleId="addParaTransportWay" property="addPara.transportWay" value="${box.transportWay}" style=" width:126px">
				<html:option value="">--请选择--</html:option>  
				<html:option value="1">自提</html:option>
				<html:option value="2">快递</html:option>
				<html:option value="3">汽运</html:option> 
				<html:option value="4">铁运</html:option>
				<html:option value="5">航空</html:option>

				<html:option value="6">海运</html:option> 
				<html:option value="7">中铁</html:option>
				<html:option value="8">市内送货</html:option>
				<html:option value="9">市内快递</html:option>
			</html:select>&nbsp;库房内移动,请选择自提
		  </td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span> 装箱件数</td>
          <td class="td_02" ><input type="text" id="addParaCount" name="addPara.count" value="${box.count}" style="width:120px;" maxlength="4" />
            件</td>
          <td class="td_01">发货费用</td>
          <td class="td_02" ><input type="text" id="addParaMoney" name="addPara.money" value="${box.money}" style="width:120px;" />
            元</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
		<img id="btnSave"  src="${ctx}/images/btnSave.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img id="btnSubmit" src="${ctx}/images/btnFH.jpg" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="${ctx}/boxList.do"><img src="${ctx}/images/btnBack.gif" /></a>
	</td>
    <td></td>
  </tr>
</table>
</html:form>
</body>
</html>
