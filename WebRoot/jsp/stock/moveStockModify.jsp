<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移库单修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
.STYLE1 {
	color: #FF0000
}
-->
</style>
<script language="JavaScript">
	$(document).ready(function(){
	    var msg = "${err}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
<!--
$(document).ready(function(){

	if  (countRow==0){
		   countRow=$("#table tr").length-1;
		} 
		//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
				
		$("#lastStockId").val($("#outStockroomId").get(0).selectedIndex); 
		$("#lastProductId").val($("#productTypeId").get(0).selectedIndex); 
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
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->

$(function(){
		$("#modifyTableIdea :checkbox").attr("disabled", true);
	
	});
</script>

<script language="JavaScript"> 	
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
				
	

	$("#modifyMoveStockDto").validate({
				rules: { 
					"modifyMoveStockDto.outStockroomId":"required",
					"modifyMoveStockDto.productTypeId":"required",
					"modifyMoveStockDto.inStockroomId":"required",
					"modifyMoveStockDto.requestDate":"required" ,
					"modifyMoveStockDto.transportWay":"required"  
				},
				messages: {
					"modifyMoveStockDto.outStockroomId":"请选择移出库房",
					"modifyMoveStockDto.productTypeId":"请选择产品分类",
					"modifyMoveStockDto.inStockroomId":"请选择移入库房",
					"modifyMoveStockDto.requestDate":"请输入要求发货日期",
					"modifyMoveStockDto.transportWay":"请选择货运方式" 
					
				} 
			});
	
	});
	
	//提交保存
	function sub(subFlag){
	 
		$("#method").attr("value", subFlag);
		
		
		//表格中没有数据不提示
		if ($("#table tr").length<=1){
		  $("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
		  
		  alert("请选择产品");
		  return false;
		}else{
		   //如果有数据判断是否为空和是否是数字
		   if(checkcountAll(true)==false){
		     return false;
		   }
		
		};  
		//发货地址
		if ($("#stockroomAddressAddressId").val()==""){
		  alert("请选择发货地址");
		  return false;
		} 
		var outStockroomId=$("#outStockroomId").val(); 
		var inStockroomId=$("#inStockroomId").val();
		if  (outStockroomId==inStockroomId){
			alert('移出库房和移入库房不能相同！');
			return false;
		}
		//判断是否是同一个部门的库房
		//var stockroomId = document.getElementById('outStockroomId').value;
		 //if($("#outStockroomId").get(0).selectedIndex ==0){ 
	 	 //  alert('请选择移出库房');
	 	 //  $("#inStockroomId").get(0).selectedIndex=0;
	 	 //  return false;
	 	 //};
	 	 var outstoomdeptid='stockroom'+document.getElementById('outStockroomId').value;
	 	 var instoomdeptid='stockroom'+document.getElementById('inStockroomId').value;
	  
	 	 var outstoomdept=document.getElementById(outstoomdeptid).value;
	 	 var instoomdept=document.getElementById(instoomdeptid).value;
	 	 if (outstoomdept!=instoomdept){
	 	   alert('移入库房和移出库房必须为同一部门');
	 	   //$("#inStockroomId").get(0).selectedIndex=0;
	 	   return false;
	 	 }
	 	//判断是否是同一个部门的库房
		if ($("#text").val().length>200){
		  alert("特殊说明长度过长");
		  return false;
		}; 			
		if ((subFlag=="save") || (subFlag="submit")){
		  $("#createMoveStockForm").submit();
		}else{
		  alert("非法提交！");
		}
		
		
				 
		
					
		if ((subFlag=="save") || (subFlag="submit")){
		    $("#modifyMoveStockDto").submit();
		}else{
		    alert("非法提交！");
		}
	}
	
	
//验证移库数量是否是正整数
	function checkcount(obj){
	   var backcount =obj.value; 
	   if (backcount==""){ 
	     return true;
	    }
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){ 
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
			//获得移库可用数
	  		var tnum =  tbobj.rows[row].cells[cell+1].innerText;
			 
	  		 if (Number(backcount)>Number(tnum)){
	  		 	alert("移库数不能大于移库可用数");
	  		 	return false;
	  		 }; 
	  }else{
			alert("移库数必须是正整数");
			return false;
	  }
	  return true;
	}
	
   //判断所有的移库数量是否正确 
 	function checkcountAll(){ 

		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var tmptotle=0; 
		
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var countobj= document.getElementById(countid);
		    var backcount =countobj.value;
		    if (backcount=="" ||backcount==0){
		     alert('请输入移库数！'); 
		     countobj.focus();
	         return false;
		    }
		    if  (checkcount(countobj)){ 
		    }else{
		   		countobj.focus();
		   		return false;
		   };   
			if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){ 
			}else{
				countobj.focus();
				return false;
			}  
		 }
		return true;
	}
	
	
	
</script>



<script type="text/javascript">
    //进入发货地址页面
	function sendAddressSelect(){        
		 var stockroomId = document.getElementById('inStockroomId').value;
		 if (stockroomId==""){
		 	alert("请先选择移入库房");
		 	return;
		 }
		var win1=window.open('${ctx}/getSendGoodsAddressByStockroomId.do?stockroomId='+stockroomId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(win1);        
    };
</script>

<script language="JavaScript">			
			
 	// 全选
	function checkAll(record){
   		var allCheck = document.getElementsByName("productCheckbox");
    		 if(record.checked==true){
       			 for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=true;
       		 }
   			 }else if(record.checked==false){
        		for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=false;
        		}
   	 		 }
	};
	
  
	// 刪除所选
	function removeProduct(){
		var productAll = document.getElementsByName("productCheckbox");
		if(productAll.length==0){
			alert("没有可删除的数据");
			return;
		}
		var table = document.getElementById("table");
		//删除选中行
		if  (countRow==0){
		   countRow=$("#table tr").length-1;
		} 
		
		var oldCount = countRow+1;
		for(var i=countRow;i>0;i--){
			var checkId = document.getElementById("choose"+i);
			if(checkId.checked==true){
				table.deleteRow(i);
				countRow--;
				lastDeleteIndex=i-1;
				indexRow--;
			}
		}
		
		//重新设置checkbox的自增ID
		lastDeleteIndex=1;
		for(i=lastDeleteIndex;i<oldCount;i++){
			var box = document.getElementById("choose"+i);
			var countobj = document.getElementById("count"+i);
			if(box!=null){
				box.id="choose"+(lastDeleteIndex);
				countobj.id="count"+(lastDeleteIndex);
				lastDeleteIndex=lastDeleteIndex+1;
			}
			 
		} 
		
	};



//库房变化时删除表格中的数据----------
	function removeStockroomChange(){
	closeSubWin();
	//未选择时不提示 
		if ($("#lastStockId").val()=="0"){
			$("#lastStockId").val($("#outStockroomId").get(0).selectedIndex); 
			return;
		} 
		//表格中没有数据不提示
		if ($("#table tr").length<=1){
		  $("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
		  return;
		} 
		//没有变化时退出
		if ( $("#lastStockId").val()==$("#outStockroomId").get(0).selectedIndex){
		  return;
		}
		if (confirm("更换库房将清空已有的产品信息，请确认")){
			$("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
			//删除行
			var oldCount = countRow+1;
			if  (countRow==0){
		   				   countRow=$("#table tr").length-1;
						} 
			var table = document.getElementById("table"); 
			for(var i=countRow;i>0;i--){ 
				table.deleteRow(i);
				countRow--;
				indexRow--; 
			}
			
		 }else {
			$("#outStockroomId").get(0).selectedIndex=$("#lastStockId").val(); 
			return;
		}    	
				   		
	} 
	//库房变化时删除表格中的数据----------



//产品分类变化时删除表格中的数据-----------
	function removeProductChange(flag){
	   closeSubWin();
		//-未选择时不提示
		 
			if ($("#lastProductId").val()=="0"){
				$("#lastProductId").val($("#productTypeId").get(0).selectedIndex); 
				return;
			}
			
			//表格中没有数据不提示
			if ($("#table tr").length<=1){
			  $("#lastProductId").val($("#productTypeId").get(0).selectedIndex);
			  return;
			} 
			//没有变化时退出
			if ( $("#lastProductId").val()==$("#productTypeId").get(0).selectedIndex){
			  return;
			}
			//选择的产品变化时清空
			if (confirm("更换产品分类将清空已有的产品信息，请确认")){
				$("#lastProductId").val($("#productTypeId").get(0).selectedIndex);
				//删除行
				var oldCount = countRow+1;
				var table = document.getElementById("table"); 
				for(var i=countRow;i>0;i--){ 
					table.deleteRow(i);
					countRow--;
					indexRow--; 
				}
				
			 }else {
				$("#productTypeId").get(0).selectedIndex=$("#lastProductId").val();
				return;
			}  
	  
				   		
	}  
	//产品分类变化时删除表格中的数据-----------	
	
</script>

<script language="JavaScript">

	//进入产品选择画面
	function productTypeSelect(){ 
	
	 	 if($("#outStockroomId").get(0).selectedIndex ==0){ 
	 	   alert('请选择移出库房');
	 	   return;
	 	 };
	 	 
	 	 if($("#productTypeId").get(0).selectedIndex ==0){ 
	 	   alert('请选择产品分类');
	 	   return;
	 	 };
	 	 
        var outStockroomId=$("#outStockroomId").val(); 
        var productTypeId=$("#productTypeId").val(); 
        
        var productTypeName=$("#productTypeId").find("option:selected").text();
		var win2 =window.open('${ctx}/getMoveProductSelectView.do?type=1&outStockroomId='+outStockroomId+'&productTypeId='+productTypeId+'&productTypeName='+encodeURI(productTypeName,"UTF-8"),
					'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(win2);
	};
	
	//去除重复选择的项
	function newWindow(myArrayInStock){
		var productIds = document.getElementsByName("productIds");
		for(var i=0;i<productIds.length;i++){
			for(var j=0;j<myArrayInStock.length;j++){
				var id = myArrayInStock[j];
				if(productIds[i].value==id[0]){
					myArrayInStock.splice(j,1);
				}
			}
		}
		insertCell(myArrayInStock);
	};
	
	
	var countRow=0;//新增的行数,也是checkbox的id值 
	var indexRow=0;//序号
	
	//从产品选择小页面返回的信息,显示
	function insertCell(myArray){
		 
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			var table = document.getElementById("table"); 
			//添加行
	        var newTr = table.insertRow();
	       
	        countRow++;
	       
	        indexRow++;
	        
	        //添加列
	        var newTd0 = newTr.insertCell();
	        var newTd1 = newTr.insertCell();
	        var newTd2 = newTr.insertCell();
	        var newTd3 = newTr.insertCell();
	        var newTd4 = newTr.insertCell();
	        var newTd5 = newTr.insertCell();
	        var newTd6 = newTr.insertCell();
	        var newTd7 = newTr.insertCell(); 
	       
	        
	         //设置列内容和属性
	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'">'; 
	        newTd1.innerHTML = '<input type="hidden"  name="productIds" value="'+arr[0]+'">'+''+arr[1];
	        newTd2.innerHTML = ''+arr[2];
	        newTd3.innerHTML = ''+arr[3];
	        newTd4.innerHTML = ''+arr[4];
	        newTd5.innerHTML = ''+arr[5]+''+'<input type="hidden" name="timeStamps" value="'+arr[7]+'" id="timeStamps'+countRow+'" >'+'&nbsp;'; 
	        newTd5.style.textAlign="right"; 
	          
	        newTd6.innerHTML = '<input type="text" style="width:90px;"  name="counts" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >';
			newTd7.innerHTML = '<input type="hidden" value="'+arr[6]+'" id="usercount'+countRow+'" >'+''+arr[6]+'&nbsp;';
            newTd7.style.textAlign="right"; 

		} 
		$("#tableLength").attr("value", countRow);
		
			
	}	
	//显示结束/----------------------/
</script>

	<script language="JavaScript">
	//移入库房变化时清空信息
    function inStockroomIdChange(){
        closeSubWin();
    	document.getElementById("stockroomAddressAddressId").value = "" 
				document.getElementById("stockroomAddressName").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressAddress").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressLinkman").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressPostcode").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressTel").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressMobile").innerHTML = "&nbsp;";
      
    }
    </script>
	
	
</head>
<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">

<html:form action="modifyMoveStock.do" styleId="modifyMoveStockDto" >  
  
<input type="hidden" name="lastStockId" id="lastStockId" value="0">
<input type="hidden" name="lastProductId" id="lastProductId" value="0">
<input type="hidden" name="modifyMoveStockDto.buyManId" value="${assessDto.buyManId}" id="buyManId"/>
<input type="hidden" id="method" value="" name = "method"/>
<input type="hidden" name="modifyMoveStockDto.id" value="${assessDto.id}"/>
  <tr>
    <td class="ye_header_left">&nbsp;</td>

    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 移库管理 &gt;&gt; 移库单修改</td>

    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;移出库房信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%"><span class="STYLE1">*</span> 移出库房名称</td>
          <td class="td_02" width="30%">
          	<html:select property="modifyMoveStockDto.outStockroomId" value="${assessDto.outStockroomId}" styleId="outStockroomId" onchange="removeStockroomChange()"  style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
			</html:select>
			
			<logic:iterate id="stockroomEntiy" name="stockroomEntitiesList" > 
          	<html:hidden  property="stockroom${stockroomEntiy.id}"  styleId="stockroom${stockroomEntiy.id}" value="${stockroomEntiy.productDeptId}" />
          	</logic:iterate>        
          
          </td>
          <td class="td_01" width="20%"><span class="STYLE1">*</span> 产品分类名称</td>
          <td class="td_02" width="30%">
          	<html:select property="modifyMoveStockDto.productTypeId" value="${assessDto.productTypeId}" style="width:126px" styleId="productTypeId" onchange="removeProductChange()">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productTypeEntity" name="productTypeEntitiesList" >
							   <html:option value="${productTypeEntity.id}"> ${productTypeEntity.name}</html:option> 
							</logic:iterate>	
			</html:select>
          </td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>       
          <th nowrap="nowrap">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">库存单价</th>
          <th nowrap="nowrap" width="106px">移库数</th>
          <th nowrap="nowrap" width="106px">移库可用数</th>
        </tr>
        <logic:iterate id="moveOutStockDto" name="list" indexId="indexId">
        <tr>
        	<td width="30" nowrap="nowrap"><input type="checkbox" id="choose${indexId+1}" name="productCheckbox"
		  
		  value="${moveOutStockDto.id}#${moveOutStockDto.productName}#${moveOutStockDto.type}#
		  ${moveOutStockDto.unit}#${moveOutStockDto.price}#${moveOutStockDto.count}#
		  ${moveOutStockDto.useCount}" />&nbsp;</td>       
        
        
          <td nowrap="nowrap" height="18px">${moveOutStockDto.code}&nbsp;
          <input type="hidden"  name="productIds" value="${moveOutStockDto.id}" id="productId${indexId+1}"></td>
          <td nowrap="nowrap">${moveOutStockDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${moveOutStockDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${moveOutStockDto.unit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${moveOutStockDto.price}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" width="100">  <input type="text" style="width:86px;" name="counts" maxlength="20" onblur="checkcount(this,${indexId+1})" value="${moveOutStockDto.count}" id="count${indexId+1}">&nbsp;</td>
          <td nowrap="nowrap" width="100" style=" text-align:right;">${moveOutStockDto.useCount}&nbsp;</td>
          <input type="hidden" name="timeStamps"  value="${moveOutStockDto.timeStamp}" id="timeStamps${indexId+1}">
          <input type="hidden" name="usercount"  value="${moveOutStockDto.useCount}" id="usercount${indexId+1}">
        </tr>
        </logic:iterate>
                
      </table>
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="40" height="25">&nbsp;<input type="checkbox" name="checkbox5" id="checkbox4" onclick="checkAll(this)"/></td>
          <td>全选&nbsp;&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnDelete.gif" align="absmiddle" onclick="removeProduct()"/>&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnAdd.gif" width="65" height="20" align="absbottom" onclick="productTypeSelect()"/> </td>
        </tr>
      </table><br/>
      <div class="div_tiao"> &nbsp;&nbsp;移入库房信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%"><span class="STYLE1">*</span> 移入库房名称</td>
          <td class="td_04" >
          <html:select property="modifyMoveStockDto.inStockroomId" value="${assessDto.inStockroomId}" styleId="inStockroomId" onchange="inStockroomIdChange()"  style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
		  </html:select> 
          </td>
        </tr>
      </table>
      <div style="padding:3px 0px 2px 0px">&nbsp;&nbsp;<a href="#" onclick="sendAddressSelect()"><img src="${ctx}/images/btnFHDZ.gif" /></a></div>
        
	   <input type="hidden" name="modifyMoveStockDto.stockroomAddressId" id="stockroomAddressAddressId" value="${assessDto.stockroomAddressId}">
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
          <td class="td_02" width="30%" id="stockroomAddressName">${assessDto.goodsReceiveUnitName}&nbsp;</td>
          <td class="td_01" width="20%" >联系人</td>
          <td class="td_02" width="30%" id="stockroomAddressLinkman">${assessDto.linkman}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td  class="td_02" id="stockroomAddressAddress">${assessDto.address}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" id="stockroomAddressPostcode">${assessDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">手机</td>
          <td class="td_02" id="stockroomAddressMobile">${assessDto.mobile}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02" id="stockroomAddressTel">${assessDto.tel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" ><span class="STYLE1">*</span> 要求发货日期</td>
          <td class="td_02" ><input type="text" name="modifyMoveStockDto.requestDate"  style="width:120px;" value="${assessDto.requestDate}" onfocus="calendarMinToday()"/></td>
          <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
          <td class="td_02" >
          <html:select property="modifyMoveStockDto.transportWay"  value="${assessDto.transportWay}"   style=" width:126px">
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
          </html:select>
          </td>
        </tr>
       
      </table><br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" ><textarea name="modifyMoveStockDto.text" id="text" cols="100" rows="4">${assessDto.text}</textarea></td>
        </tr>
      </table>
     
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0" id="modifyTableIdea" cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
                <td height="20px" colspan="2" >采购主管评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="moveStockAssess.buyManIdea" id="buyManIdea1" value="1" 
                                 <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if>   
                />符合
                  &nbsp;
                          <input type="checkbox" name="moveStockAssess.buyManIdea" id="buyManIdea0" value="0" 
                          <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if>  
                  />不符合 
                  </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${tf:replaceBr(assessDto.buyManText)}&nbsp;
                          </td>
                        </tr>
                	</table>                    </td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.buyManName}</td>
                <input type="hidden" name="moveStockAssess.buyManName"  value="${assessDto.buyManName}"/>
                <td>日期：${assessDto.buyManDate}</td>
                <input type="hidden" name="moveStockAssess.buyManDate"  value="${assessDto.buyManDate}"/>
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
                <td height="20px" colspan="2">移出库房管理员意见：</td>
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
                  <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass} />符合&nbsp;&nbsp;
                  <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
               </td>
              </tr>
              <tr>
                <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;">
                         ${tf:replaceBr(assessDto.stkAdmText)}&nbsp;
                      </td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.stkAdmName}</td>
                <td>日期：${assessDto.stkAdmDate}</td>
              </tr>
              <tr>
                <td height="20px" colspan="2">&nbsp;</td>
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
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getMoveStockList.do' "/>
    </td>
    <td></td>
  </tr>
</table>
</html:form>
<br />
</body>
</html>
