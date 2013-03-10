<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品借出申请</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.STYLE1 {
	color: #FF0000
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
			});
//-->
</script>
<script language="JavaScript">
 //进入发货地址页面
	function openAdddressSelect(){        
		 var inStockroomId = document.getElementById('inStockroomId').value;
		 if (inStockroomId==""){
		 	alert("请先选择库房");
		 	return;
		 }
		var win1 = window.open('${ctx}/getAddressByStockroomId.do?stockroomId='+inStockroomId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(win1);             
    };
    
    //关闭所有打开的子窗口
	var arrSubWin = new Array(); 
	function closeSubWin(){
		for(var i=0; i<arrSubWin.length; i++){
			if(arrSubWin[i] && arrSubWin[i].open) 
			{ 
				arrSubWin[i].close(); 
			} 
		}
		if(arrSubWin.length>0){
			arrSubWin = new Array();
		}
	}
</script>


<script language="JavaScript">
 //提交保存
	function sub(subFlag){
	 
		$("#method").attr("value", subFlag);
		
		if(checkcountzero() ==false){
		  alert("归还数不能都为空!");
		  return false;
		}
		//如果有数据判断归还数是否小于借出数与已归还数之差
		if( checkcountAllforSub()==false){ 
			     return false;
		 }		
		
		
		//发货地址
		if ($("#stockroomAddressAddressId").val()==""){
		  alert("请选择收货地址");
		  return false;
		} 
		
		if ($("#text").val().length>200){
		  alert("特殊说明长度过长");
		  return false;
		}
		
		if ($("#inStockroomId").val()==""){
	      alert('请选择库房！');
	      return false;
	     }
	     	
			//判断是否是同一个部门的库房
	 	 var outstoomdeptid='stockroom'+document.getElementById('outStockroomId').value;
	 	 var instoomdeptid='stockroom'+document.getElementById('inStockroomId').value;
	   	 
	 	 var outstoomdept=document.getElementById(outstoomdeptid).value;
	 	 var instoomdept=document.getElementById(instoomdeptid).value;
	 	 if (outstoomdept!=instoomdept){
	 	   alert('归还库房和借出库房必须为同一部门');
	 	   $("#inStockroomId").get(0).selectedIndex=0;
	 	   return false;
	 	 }
	 	 //判断是否是同一个部门的库房 
	 	 
	 	 	
			
		if ((subFlag=="save") || (subFlag="submit")){
		    $("#modifySampleInDto").submit();
		}else{
		    alert("非法提交！");
		}
	}
	
	
	 //******
	//验证归还数量
	function checkcountforSub(obj){
	
	  var backcount =obj.value;
	  
	  if(checkcountzero() ==false){
		  alert("归还数不能都为空!");
		  return false;
		}
	  
	 
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
	  	if("${USERLOGIN.roleId}"==8){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出数
	  		var tnumlend =  tbobj.rows[row].cells[cell-3].innerText;
	  		//获得已归还数
	  		var tnumback =  tbobj.rows[row].cells[cell-1].innerText;
	  		//借出数-已归还数
	  		var tnumnew = tnumlend-tnumback
	  		 
	  		 if (Number(backcount)>Number(tnumnew)){
	  		 	alert("归还数不能大于借出数与已归还数之差");
	  		 	return false;
	  		 }; 
	  		return true;
	  	}
	  	
	  	if("${USERLOGIN.roleId}"==4){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出数
	  		var tnumlend =  tbobj.rows[row].cells[5].innerText;
	  		//获得已归还数
	  		var tnumback =  tbobj.rows[row].cells[6].innerText;
	  		//借出数-已归还数
	  		var tnumnew = tnumlend-tnumback
	  		 
	  		 if (Number(backcount)>Number(tnumnew)){
	  		 	alert("归还数不能大于借出数与已归还数之差");
	  		 	return false;
	  		 }; 
	  		return true;
	  	}  	
	  	  
	  }else{
			alert("归还必须是正整数");
			return false;
	  } 
	}
	    //判断所有的返厂数是否正确 
 	function checkcountAllforSub(){  
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length; 
		for(var i=1;i<tlength;i++){
		    var countid = "reCount"+i;
		    var countobj= document.getElementById(countid); 
		   if  (checkcountforSub(countobj)){ 
		   }else{
		   		countobj.focus();
		   		return false;
		   };  
		 }
		return true;
	}
	//*******
	
	$(document).ready(function(){
	    var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
	

</script>

<script language="JavaScript">
	//库房变化时删除发货地址数据----------
    function inStockroomIdChange(){
    
    	//变化时清空信息 --发货地址
		document.getElementById("stockroomAddressAddressId").value = "" 
		document.getElementById("stockroomAddressName").innerHTML = "&nbsp;"
		document.getElementById("stockroomAddressAddress").innerHTML = "&nbsp;"
		document.getElementById("stockroomAddressLinkman").innerHTML = "&nbsp;"
		document.getElementById("stockroomAddressPostcode").innerHTML = "&nbsp;"
		document.getElementById("stockroomAddressTel").innerHTML = "&nbsp;"
		document.getElementById("stockroomAddressMobile").innerHTML = "&nbsp;";
		
		closeSubWin();
		//判断是否是同一个部门的库房
	 	 var outstoomdeptid='stockroom'+document.getElementById('outStockroomId').value;
	 	 var instoomdeptid='stockroom'+document.getElementById('inStockroomId').value;
	  
	 	 var outstoomdept=document.getElementById(outstoomdeptid).value;
	 	 var instoomdept=document.getElementById(instoomdeptid).value;
	 	 if (outstoomdept!=instoomdept){
	 	   alert('归还库房和借出库房必须为同一部门');
	 	   $("#inStockroomId").get(0).selectedIndex=0;
	 	   return false;
	 	 }
	 	 //判断是否是同一个部门的库房 	 		   		
	} 
	//库房变化时删除发货地址数据----------
	
</script>

<script language="JavaScript">
	//验证借出数量是否是正整数
	function checkcount(obj){ 
	 
	// var tdid=obj.id;
	//  var trid=	$("#"+tdid).parent().attr("id");
	 
	// $("#"+trid+" td:nth-child(8)").html('ddd');
	   
	   var backcount =obj.value; 
	   if (backcount==""){backcount=0}
	   
		
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){ 
	  	if("${USERLOGIN.roleId}"==8){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出数
	  		var tnumlend =  tbobj.rows[row].cells[cell-3].innerText;
	  		//获得已归还数
	  		var tnumback =  tbobj.rows[row].cells[cell-1].innerText;
	  		//借出数-已归还数
	  		var tnumnew = tnumlend-tnumback
	  		 
	  		 if (Number(backcount)>Number(tnumnew)){
	  		 	alert("归还数不能大于借出数与已归还数之差");
	  		 	return false;
	  		 }; 
	  		 //计算归还金额
	  		 var num =  obj.value ; //归还数
		     var price = rmoney(tbobj.rows[row].cells[cell-4].innerText); //库存单价
		    //不含税的准确的库存单价
		    // var tt="price"+row;      
		    //var price = $("#"+tt).val(); 
		    // alert(price);
		     var tmptotle=FloatMul(num,price);
		     
		     tbobj.rows[row].cells[cell+1].innerHTML=fmoney(tmptotle,2)+'&nbsp;&nbsp;';
		     
	  		 //计算总的借出金额
			if (checkcountAll){
			//	alert("ttt");
			   totleMoneyMethod();
			} 
	  	}
	  	
	  	if("${USERLOGIN.roleId}"==4){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出数
	  		var tnumlend =  tbobj.rows[row].cells[5].innerText;
	  		//获得已归还数
	  		var tnumback =  tbobj.rows[row].cells[6].innerText;
	  		//借出数-已归还数
	  		var tnumnew = tnumlend-tnumback
	  		 
	  		 if (Number(backcount)>Number(tnumnew)){
	  		 	alert("归还数不能大于借出数与已归还数之差");
	  		 	return false;
	  		 }; 
	  		 
		     
	  		 //计算总的归还金额
			if (checkcountAll){
			//	alert("ttt");
			   totleMoneyMethod();
			} 
	  	}
	  	
	  		 
	  }else{
			alert("借出数必须是正整数");
	  }
	}
	
	
	//计算归还金额合计
	function totleMoneyMethod(){
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var tmptotle=0;
		var taxRate = $("#taxRate").val(); 
		for(var i=1;i<tlength;i++){
		    var countid = "reCount"+i;
		    var priceid = "price"+i;
		    var countobj= document.getElementById(countid);
		    var backcount =countobj.value;
		    
		    var num =  $("#"+countid).val() ; //借出数量
		    if(num==""){num =0};
		    var price =   rmoney($("#"+priceid).val()) ; //库存单价
		    tmptotle=FloatMul(num,price);
		    vtotle = FloatAdd(vtotle,tmptotle);
		 } 
		$("#totalmoney").html(fmoney(vtotle,2));
		$("#money").val(FloatDiv(vtotle,taxRate));
		//$("#money").val(rmoney(fmoney(vtotle,2)));
	}

	
	//判断所有的借出数量是否正确 
 	function checkcountAll(){ 

		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var tmptotle=0; 
		
		for(var i=1;i<tlength;i++){
		    var countid = "reCount"+i;
		    var countobj= document.getElementById(countid);
		    var backcount =countobj.value;
		   
		      if (backcount==""){
		        return false;
		      }
		  	
			if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
			 
			}else{
				return false;
			}  
		 }
		return true;
	}	


//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
	function fmoney(s,n){
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
//还原金额   
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	} 		
		
	
	//判断所有的借出数量是否都为0或空 
 	function checkcountzero(){ 

		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		
		for(var i=1;i<tlength;i++){
		    var countid = "reCount"+i;
		    var countobj= document.getElementById(countid);
		    var backcount =countobj.value;
		   
		      if (backcount!="" &&backcount!= 0){
		        return true;
		      }		 
		 }
		return false;
	}	
	
	
//隐藏table的一列
//$(document).ready(function(){
//	if("${USERLOGIN.roleId}"==4){
//        $("#totalmoneytr").hide();
//  	  $("#priceth").hide();
//  	  $("#moneyth").hide();
//  	  $("#inpriceth").hide();
//  	 
//  	  $("td[name='pricetd']").hide();
//  	  $("td[name='moneytd']").hide();
//  	  $("td[name='inpricetd']").hide();
//  	}
//});
  
  
</script>

</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="modifySampleIn.do" styleId="modifySampleInDto" > 
<input type="hidden" id="outStockroomId" value="${assessDto.outStockroomId}">
<input type="hidden" name="modifySampleInDto.buyManId" value="${assessDto.buyManId}" id="buyManId"/>
<input type="hidden" name="modifySampleInDto.productTypeId" value="${assessDto.productTypeId}" id="productTypeId"/>
<input type="hidden" id="method" value="" name = "method"/>
<input type="hidden" id="taxRate" value="${assessDto.taxRate}">
<input type="hidden" name="modifySampleInDto.id" value="${assessDto.id}"/>

  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置：  库存管理 &gt;&gt; 样品归还管理 &gt;&gt; 样品归还单修改</td>
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
            	    </logic:equal>  &nbsp;          
            </td>
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
            	    </logic:equal>  &nbsp; 
          	</td>
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
    <input type="hidden" name="modifySampleInDto.sampleOutId" id="sampleOutId" value="${assessDto.sampleOutId}">
    
		<tr>
				<td class="td_03" width="50%"><span class="STYLE1">*</span> 库房名称</td>
				<td class="td_04" >
					<html:select property="modifySampleInDto.inStockroomId" styleId="inStockroomId" value="${assessDto.inStockroomId}" onchange="inStockroomIdChange()" style="width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <logic:match name="stockroomEntity" value="2" property="type">
								<html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
								</logic:match> 
							</logic:iterate>
					</html:select>
					<logic:iterate id="stockroomEntiy" name="stockroomEntitiesList">  
          			<html:hidden  property="stockroom${stockroomEntiy.id}"  styleId="stockroom${stockroomEntiy.id}" value="${stockroomEntiy.productDeptId}" />
          			</logic:iterate>
				</td>
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
			<!--  <th nowrap="nowrap" id="priceth">库存单价</th>-->
			<c:if test="${USERLOGIN.roleId!=4}"><th nowrap="nowrap" id="priceth">库存单价</th></c:if>				
			<th nowrap="nowrap">借出数</th>
			<!-- <th nowrap="nowrap" id="moneyth">借出金额</th> -->
			<c:if test="${USERLOGIN.roleId!=4}"><th nowrap="nowrap" id="moneyth">借出金额</th></c:if>			
			
			<th nowrap="nowrap">已归还数</th>
			<th nowrap="nowrap">归还数</th>
			<!-- <th nowrap="nowrap" id="inpriceth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th> -->
			<c:if test="${USERLOGIN.roleId!=4}"><th nowrap="nowrap" id="inpriceth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th></c:if>			
			
		</tr>
		
		 <logic:iterate id="sampleInProductInfoDto" name="list" indexId="indexId">
		<tr>
			<td width="40" nowrap="nowrap" style="text-align:center;">${indexId+1}</td>
			<td nowrap="nowrap">${sampleInProductInfoDto.code}&nbsp;</td>
			<td nowrap="nowrap">${sampleInProductInfoDto.productName}&nbsp;</td>
			<td nowrap="nowrap" >${sampleInProductInfoDto.type}&nbsp;</td>
			<td nowrap="nowrap" >${sampleInProductInfoDto.unit}&nbsp;</td>
			<!-- <td nowrap="nowrap" style="text-align:right;" name="pricetd"><fmt:formatNumber value="${sampleInProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4}"><td nowrap="nowrap" style="text-align:right;" name="pricetd"><fmt:formatNumber value="${sampleInProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>		 
			 
			<td nowrap="nowrap" style="text-align:right;">${sampleInProductInfoDto.count}&nbsp;
			 <input type="hidden"   name="prices"  value="${sampleInProductInfoDto.priceTax}" id="price${indexId+1}">
			 <input type="hidden"   name="pricesnotaxs"  value="${sampleInProductInfoDto.price}" id="pricesnotax${indexId+1}">
			</td>
			<!-- <td nowrap="nowrap" width="93" style="text-align:right;" name="moneytd"><fmt:formatNumber value="${sampleInProductInfoDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4}"><td nowrap="nowrap" width="93" style="text-align:right;" name="moneytd"><fmt:formatNumber value="${sampleInProductInfoDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>		 
			
			<td nowrap="nowrap" style="text-align:right;">${sampleInProductInfoDto.alreadyReCount}&nbsp;</td>
			<td nowrap="nowrap" width="92"><input type="text" name="reCounts"  style="width:80px;" value="${sampleInProductInfoDto.reCount}" onblur="checkcount(this)" id="reCount${indexId+1}"/></td>
			<input type="hidden" id="productIds" value="${sampleInProductInfoDto.id}" name="productIds"/>
			<!-- <td nowrap="nowrap" width="93" style="text-align:right;" name="inpricetd"><fmt:formatNumber value="${sampleInProductInfoDto.returnMoneyTax}" pattern="#,##0.00"/>&nbsp;&nbsp;</td> -->
			<c:if test="${USERLOGIN.roleId!=4}"><td nowrap="nowrap" width="93" style="text-align:right;" name="inpricetd"><fmt:formatNumber value="${sampleInProductInfoDto.returnMoneyTax}" pattern="#,##0.00"/>&nbsp;&nbsp;</td></c:if>		 
			
		</tr>
		</logic:iterate>
	</table>
   
  	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
  	  <c:if test="${USERLOGIN.roleId!=4}">
		<tr id="totalmoneytr">
			<td width="40" height="25">&nbsp;</td>
			<td width="80">&nbsp;</td>
			<td align="center" width="80">&nbsp;</td>
			<td  align="center">&nbsp;</td>
			<td style=" text-align:right;">归还金额合计</td>
						<td   style=" text-align:right;width:96px" id="totalmoney" ><fmt:formatNumber value="${assessDto.moneyTotalTax}" pattern="#,##0.00"/></td>
          	<td style="width:2px">元</td>
		</tr>
	  </c:if>
	  <input type="hidden" name="modifySampleInDto.money" value="${assessDto.moneyTotal}" id="money">			 
	  
	</table>
  	<div class="div_tiao"> &nbsp;&nbsp;收货信息</div>
	&nbsp;&nbsp;<a href="#" onclick="openAdddressSelect()"><img src="${ctx}/images/btnSHDZ.gif" width="99" height="20" /></a>
	<input type="hidden" name="modifySampleInDto.stockroomAddressId" id="stockroomAddressAddressId" value="${assessDto.stockroomAddressId}">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">货物接收单位名称</td>
            <td colspan="3" class="td_02" id="stockroomAddressName" >${assessDto.goodsReceiveUnitName}&nbsp;</td>
			</tr>
          <tr>
            <td class="td_01" height="18px">收货地址</td>
            <td colspan="3"  class="td_02" id="stockroomAddressAddress">${assessDto.address}&nbsp;</td>
			</tr>
          <tr>
            <td class="td_01" width="20%" height="18px">联系人</td>
            <td class="td_02" width="30%" id="stockroomAddressLinkman">${assessDto.linkman}&nbsp;</td>
            <td class="td_01" width="20%">邮编</td>
            <td class="td_02" width="30%" id="stockroomAddressPostcode">${assessDto.postcode}&nbsp;</td>
          </tr>
		  <tr>
		  	<td class="td_01" height="18px">电话</td>
		  	<td class="td_02" id="stockroomAddressTel">${assessDto.tel}&nbsp;</td>
		  	<td class="td_01">手机</td>
		  	<td class="td_02" id="stockroomAddressMobile">${assessDto.mobile}&nbsp;</td>
	  	</tr>
        </table>
		 <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="modifySampleInDto.text" id="text" cols="100" rows="4">${assessDto.text}</textarea></td>
				</tr>
			</table>
            <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">库房名称是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
          <input type="checkbox" name="buyManIdea" id="buyManIdea1" value="1" disabled="true"
                    <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>  
				/>符合
			  		&nbsp;&nbsp;
					<input type="checkbox" name="buyManIdea" id="buyManIdea0" value="0" disabled="true"
                    <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
					/>不符合
          
           </td>
        </tr>
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
          <td>日期：${assessDto.buyManDate}&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
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
			  	<td valign="top" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" width="62px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.stkAdmText)}&nbsp;</td>
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
    <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getSampleInList.do' "/>
    </td>
    <td></td>
  </tr>
  
 </html:form>
</table>
</body>
</html>
