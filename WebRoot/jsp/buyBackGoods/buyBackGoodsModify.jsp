<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>返厂单修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
-->
</style>
<script language="JavaScript">
<!--
$(document).ready(function(){
				if($("#tableInStock")){
					$("#tableInStock tr:even").addClass("treven");
					$("#tableInStock tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
				
				
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

		$(document).ready(function(){
 			$("#modifyBuyBackGoodsDto").validate({
				rules: { 
					"modifyBuyBackGoodsDto.stockroomId":"required",
					"modifyBuyBackGoodsDto.requestDate":"required",
					"modifyBuyBackGoodsDto.transportWay":"required"
	
				},
				messages: {
					"modifyBuyBackGoodsDto.stockroomId":"请选择库房", 
					"modifyBuyBackGoodsDto.requestDate":"请输入要求发货日期",
					"modifyBuyBackGoodsDto.transportWay":"请选择货运方式"
					
				} 
			});
		});
			
			
		$(document).ready(function(){
			 	$("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
				//------------------库房变化时清空表格中的信息--------------------
				$("#stockroomId").change(function(v) { 
					closeSubWin();
					//未选择时不提示
					if ($("#lastStockId").val()=="0"){
						$("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
						return;
					}
					//表格中没有数据不提示
					if ($("#tableInStock tr").length<=1){
					  $("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
					  return;
					} 
					if ( $("#lastStockId").val()==$("#stockroomId").get(0).selectedIndex){
					  return;
					}
					//选择的库房变化时清空
					if (confirm("更换库房将清空已有的返厂信息，请确认")){
						$("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
						//删除行
						
						if  (countRow==0){
		   				   countRow=$("#tableInStock tr").length-1;
						} 
						var oldCount = countRow+1;
						
						var table = document.getElementById("tableInStock"); 
						for(var i=countRow;i>0;i--){ 
							table.deleteRow(i);
							countRow--;
							lastDeleteIndex=i-1;
							indexRow--; 
						}
						totleMoneyMethod();
					 }else {
						$("#stockroomId").get(0).selectedIndex=$("#lastStockId").val();
						return;
					}  
				   		
				});  
				//------------------库房变化时清空表格中的信息---end-----------------
				changeIndex();	
				if($("#table2")){
					$("#table2 tr:odd").addClass("treven");
					$("#table2 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		 
			//---reday----------
			
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
	
	//删除 
	function removeProduct(){
		judgeRemovePro();
		// 自动添加索引
		changeIndex(); 
		//计算返厂金额合计
	    totleMoneyMethod();
	}
	
	// 刪除所选
	function judgeRemovePro(){
		var productAll = document.getElementsByName("productCheckbox");
		
		if(productAll.length==0){
			alert("没有可删除的数据");
			return;
		}
		
		var table = document.getElementById("tableInStock");
		//删除选中行
		if  (countRow==0){
		   countRow=$("#tableInStock tr").length-1;
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
		 
		var newIndex=1;
		for(i=1;i<oldCount;i++){
			var box = document.getElementById("choose"+i); 
			var priceobj = document.getElementById("price"+i);
			var countobj = document.getElementById("count"+i);
			if(box!=null){
				box.id="choose"+(newIndex);
				priceobj.id="price"+(newIndex);
				countobj.id="count"+(newIndex);
				newIndex=newIndex+1;
			}
			 
		} 
		
	};
	
	 
	//验证返厂数量是否是正整数
	function checkcount(obj){
	
	  var backcount =obj.value;
	  
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
			
			//获得库存数
	  		var tnumKuCun =  rmoney(tbobj.rows[row].cells[cell+2].innerText);
	  		
			//获得入库金额
	  		var tnumRK =  rmoney(tbobj.rows[row].cells[cell-4].innerText);
	  		//获得指定金额
	  		var tnumZD =  rmoney(tbobj.rows[row].cells[cell-3].innerText);
	  		//获得收票金额
	  		var tnumSP =  rmoney(tbobj.rows[row].cells[cell-2].innerText); 
	  		//获得已返厂数
	  		var tnumFCS =  rmoney(tbobj.rows[row].cells[cell-1].innerText);
	  		
	  		//获得库存单价
	  		var tnumPrice =  rmoney(tbobj.rows[row].cells[cell-5].innerText);
	  		
	  		//获得已返金额
	  		var tnumFC = FloatMul(tnumFCS,tnumPrice);
	  		
	  		if (backcount==""){
	  			backcount="0";
	  			//计算返厂金额 
	  			tbobj.rows[row].cells[cell+1].innerHTML=fmoney(FloatMul(tnumPrice,backcount),5)+'&nbsp;';
	  			//计算总的返厂金额
				if (checkcountAll(false)){
			   		totleMoneyMethod();
				} 
				return true;
	  		}
	  		
	  		if (Number(backcount)>Number(tnumKuCun)){
	  			alert('返厂数不能大于库存数!');
	  			return false;
	  		}  
	  		var tmpcheckFCMoney1=FloatSub(FloatSub(FloatSub(tnumRK,tnumZD),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney1<0){//tnumRK-tnumZD-tnumFC-tnumPrice*backcount
	  			alert('返厂金额需<=入库金额-指定金额-已返厂金额!');
	  			return false;
	  		}
	  		var tmpcheckFCMoney2=FloatSub(FloatSub(FloatSub(tnumRK,tnumSP),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney2<0){//tnumRK-tnumSP-tnumFC-tnumPrice*backcount
	  			alert('返厂金额需<=入库金-收票金额-已返厂金额!');
	  			return false;
	  		}
	  		//计算返厂金额 
	  		tbobj.rows[row].cells[cell+1].innerHTML=fmoney(FloatMul(tnumPrice,backcount),5)+'&nbsp;';
	  		
			//计算总的返厂金额
			if (checkcountAll(false)){
			   totleMoneyMethod();
			} 
	  }else{
			alert("返厂数必须是正整数");
	  } 
	}
	
    //判断所有的返厂数是否正确  
 	function checkcountAll(flag){ 
 		//flag为true时是提交时判断
		var tableObj = document.getElementById("tableInStock");
		var tlength = tableObj.rows.length;
		var tmptotle=0; 
		
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var countobj= document.getElementById(countid); 
		    var backcount =countobj.value;
		    //提交时用
		    if 	(flag==true){
		      if (backcount==""){
		        return false;
		      }
		    } 
			if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
			 
			}else{
				return false;
			}  
		 }
		return true;
	}
 
	//计算返厂金额合计
	function totleMoneyMethod(){
		var tableObj = document.getElementById("tableInStock");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var tmptotle=0; 
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var priceid = "price"+i;
		    var countobj= document.getElementById(countid);
		    var backcount =countobj.value;
		    
		    var num =  $("#"+countid).val() ; //返厂数量
		    var price =   $("#"+priceid).val() ; //采购单价
		    tmptotle=FloatMul(num,price);
		    vtotle =FloatAdd( vtotle,tmptotle);
		 }
		$("#totalmoney").html(fmoney(vtotle,5)+'&nbsp;');
		$("#money").attr("value",vtotle);
	}
	
	
	//******
	//验证返厂数量是否是正整数
	function checkcountforSub(obj){
	
	  var backcount =obj.value;
	  
	  if (backcount==""||backcount==0){
	  	alert('请输入返厂数！');
	    return false;
	  }
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
			
			//获得库存数
	  		var tnumKuCun =  rmoney(tbobj.rows[row].cells[cell+2].innerText);
	  		
			//获得入库金额
	  		var tnumRK =  rmoney(tbobj.rows[row].cells[cell-4].innerText);
	  		//获得指定金额
	  		var tnumZD =  rmoney(tbobj.rows[row].cells[cell-3].innerText);
	  		//获得收票金额
	  		var tnumSP =  rmoney(tbobj.rows[row].cells[cell-2].innerText); 
	  		//获得已返厂数
	  		var tnumFCS =  rmoney(tbobj.rows[row].cells[cell-1].innerText);
	  		
	  		//获得库存单价
	  		var tnumPrice =  rmoney(tbobj.rows[row].cells[cell-5].innerText);
	  		
	  		//获得已返金额
	  		var tnumFC = FloatMul(tnumFCS,tnumPrice);
	  		
	  		if (Number(backcount)>Number(tnumKuCun)){
	  			alert('返厂数不能大于库存数!');
	  			return false;
	  		}  
	  		var tmpcheckFCMoney3=FloatSub(FloatSub(FloatSub(tnumRK,tnumZD),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney3<0){//tnumRK-tnumZD-tnumFC-tnumPrice*backcount
	  			alert('返厂金额需<=入库金额-指定金额-已返厂金额!');
	  			return false;
	  		} 
	  		var tmpcheckFCMoney4=FloatSub(FloatSub(FloatSub(tnumRK,tnumSP),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney4<0){//tnumRK-tnumSP-tnumFC-tnumPrice*backcount
	  			alert('返厂金额需<=入库金-收票金额-已返厂金额!');
	  			return false;
	  		} 
	  		return true;
	  		  
	  }else{
			alert("返厂数必须是正整数");
			return false;
	  } 
	}
	    //判断所有的返厂数是否正确 
 	function checkcountAllforSub(){  
		var tableObj = document.getElementById("tableInStock");
		var tlength = tableObj.rows.length; 
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
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
	
</script>

<script language="JavaScript">

	 //产品信息自动添加序号
    function changeIndex(){
    	var table = document.getElementById("tableInStock");
    	var tlength = table.rows.length;
    	for(var i=1;i<tlength;i++){
    		table.rows[i].cells[1].innerHTML = i;
    	}
    } 
    	//进入发货地址页面
	function sendAddressSelect(){ 
        var supplierId=$("#supplierId").val(); 
		var winadd = window.open('${ctx}/getSendAddressSelect.do?supplierId='+supplierId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(winadd);     
	};
	//提交保存
	function sub(subFlag){
	 
		$("#method").attr("value", subFlag);
		 
		//表格中没有数据不提示
		if ($("#tableInStock tr").length<=1){
		  $("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
		  alert("请选择入库明细");
		  return false;
		}else{
		   //如果有数据判断是否为空和是否是数字
		    if( checkcountAllforSub()==false){ 
		     return false;
		   }
		   totleMoneyMethod();
		
		};  
		//发货地址
		if ($("#supplierAddressId").val()==""){
		  alert("请选择发货地址");
		  return false;
		} 
		
		
		 if(!$("#nameAndId").is(":hidden")){ 

				      if($("#takeName").val()==""){
						 alert("请填写提货人姓名");
						 return false;
					  } 
					  if($("#takeNumber").val()==""){
					     alert("请填写身份证号码"); 
						 return false;
					  } 
		}
		
		//采购专员时特殊说明必需填写 
			if(${USERLOGIN.roleId==8}){
				if ($.trim($('#stext').val()).length==0){
					  alert("请输入特殊说明");
					  return false;
					};
			}
		
		//特殊说明
		if ($("#stext").val().length>=200){
		  alert("特殊说明过长");
		  return false;
		}
					
		if ((subFlag=="save") || (subFlag="submit")){
		  $("#modifyBuyBackGoodsDto").submit();
		}else{
		  alert("非法提交！");
		}
	}
	
	//-------------------------------入库明细选择
	//进入 入库明细选择小画面
	function	stockroomSelect(){  
		 var inStockId = $("#inStockId").val(); 
		 var stockroomId = document.getElementById('stockroomId').value;
		 if (stockroomId==""||stockroomId==0){
		 	alert("请先选择库房");
		 	return;
		 }
		var win1 = window.open('${ctx}/getStockroomSelect.do?inStockId='+inStockId+'&stockroomId='+stockroomId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(win1);
	}
	
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
		if  (countRow==0){
			countRow=$("#tableInStock tr").length-1;
		} 
		
		insertCell(myArrayInStock);
	};
	
	var countRow=0;//新增的行数,也是checkbox的id值
	var lastDeleteIndex=0;
	var indexRow=0;//序号
	
	//从入库明细小页面返回的信息,显示
	function insertCell(myArray){
		var averagePrice = 0;
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			var table = document.getElementById("tableInStock"); 
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
	        var newTd8 = newTr.insertCell();
	        var newTd9 = newTr.insertCell();
	        var newTd10 = newTr.insertCell();
	        var newTd11 = newTr.insertCell();
	        var newTd12 = newTr.insertCell();
	        var newTd13 = newTr.insertCell();
	        
	        //设置列内容和属性
	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'" value="'+arr[0]+'">&nbsp;';
	        newTd1.innerHTML = '';
	        newTd2.innerHTML = '<input type="hidden"  name="productIds" value="'+arr[0]+'">'+''+arr[11]+'&nbsp;';
	        newTd3.innerHTML = ''+arr[1]+'&nbsp;';
	        newTd4.innerHTML = ''+arr[2]+'&nbsp;';
	        newTd5.innerHTML = ''+arr[3]+'&nbsp;';
	        newTd1.style.textAlign="center";
	        newTd6.style.textAlign="right";
	        newTd7.style.textAlign="right";
	        newTd8.style.textAlign="right";
	        newTd9.style.textAlign="right";
	        newTd10.style.textAlign="right";
	        newTd12.style.textAlign="right";
	        newTd13.style.textAlign="right";
	        newTd6.innerHTML = '<input type="hidden"   name="prices" id="price'+countRow+'" value="'+rmoney(arr[4])+'">'+''+arr[4]+'&nbsp;'; 
	        newTd7.innerHTML = ''+arr[5]+'&nbsp;';
			newTd8.innerHTML = ''+arr[6]+'&nbsp;'; 
	        newTd9.innerHTML = ''+arr[7]+'&nbsp;';
	        newTd10.innerHTML = ''+arr[8]+'&nbsp;';
	        newTd11.innerHTML = '<input type="text" name="counts" style="width:86px;" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >'+'&nbsp;';
	        newTd12.innerHTML = '0.00000'+'&nbsp;';
	        newTd13.innerHTML = ''+arr[10]+'&nbsp;';

		}
		changeIndex();
		$("#tableLength").attr("value", countRow);
		if($("#tableInStock")){
					$("#tableInStock tr:even").addClass("treven");
					$("#tableInStock tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			
	}	
	
 //货运方式下拉
	function changeTransportWay(){
	    if($("#transportWay").val()=="1"){
	    	$("#nameAndId").show();  
		}else{
			$("#nameAndId").hide(); 
	    }
	
	}

 $(function(){
	  //隐藏姓名和身份证号码 
	 if($("#transportWay").val() == "1" ){ 
			 $("#nameAndId").show();  
	 }else{
			$("#nameAndId").hide(); 
	 }
 });
 
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
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });

</script>
</head>
<body>
<html:form action="modifyBuyBackGoods.do" styleId="modifyBuyBackGoodsDto">
<input type="hidden" name="modifyBuyBackGoodsDto.id" value="${modifyBuyBackGoodsInfoDto.id}"/>
<input type="hidden" name="modifyBuyBackGoodsDto.supplierAddressId" value="${modifyBuyBackGoodsInfoDto.supplierAddressId}" id="supplierAddressId"/>
<input type="hidden" name="modifyBuyBackGoodsDto.proMajId" value="${modifyBuyBackGoodsInfoDto.proMajId}" id="proMajId"/>
<input type="hidden" name="modifyBuyBackGoodsDto.productTypeId" value="${modifyBuyBackGoodsInfoDto.productTypeId}" id="productTypeId"/>
<input type="hidden" id="method" value="" name = "method"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td  class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购返厂管理 &gt;&gt; 返厂单修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">产品分类名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.productTypeName}&nbsp;</td>
          <td class="td_01" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${modifyBuyBackGoodsInfoDto.supplierName}&nbsp;</td>
           <input type="hidden" name="modifyBuyBackGoodsDto.supplierId" id="supplierId" value="${modifyBuyBackGoodsInfoDto.supplierId}">
        </tr>
        <tr>
          <td class="td_01" height="18">产品合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.productContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.companyContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01">库房名称</td>
          <td class="td_02">
           <html:select property="modifyBuyBackGoodsDto.stockroomId"  value="${modifyBuyBackGoodsInfoDto.stockroomId}" styleId="stockroomId"  style="width:264px">
          	  <html:option value="0">--请选择--</html:option>
	          <logic:iterate id="stockroomEntitie" name="stockroomEntities">
	          	<html:option value="${stockroomEntitie.id}">${stockroomEntitie.name} </html:option>
	          </logic:iterate>
           </html:select>
           <input type="hidden" id="lastStockId" value="0">
          </td>
          <td class="td_01">入库单号</td>
          <td class="td_02">${modifyBuyBackGoodsInfoDto.instockId}&nbsp;</td>
          <input  type="hidden" name="modifyBuyBackGoodsDto.inStockId" value="${modifyBuyBackGoodsInfoDto.instockId}" id="inStockId"/>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableInStock" >
        <tr id="firstTline">
          <th nowrap="nowrap" width="30" >选择</th>
          <th nowrap="nowrap">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">已返厂数</th>
          <th nowrap="nowrap" style="width:106px;">返厂数</th>
          <th nowrap="nowrap" width="93">返厂金额</th>
          <th nowrap="nowrap" width="82">&nbsp;&nbsp;&nbsp;库存数&nbsp;&nbsp;&nbsp;</th>
        </tr>
       <logic:iterate id="GoogsCreateInfoDto" name="backGoogsCreateInfoList" indexId="indexId">
       
       
     	 <tr>
		  <td width="30" nowrap="nowrap"><input type="checkbox" id="choose${indexId+1}" name="productCheckbox"
		  
		  value="${GoogsCreateInfoDto.productId}#${GoogsCreateInfoDto.productName}#${GoogsCreateInfoDto.productType}#
		  ${GoogsCreateInfoDto.productUnit}#${GoogsCreateInfoDto.price}#${GoogsCreateInfoDto.inStockMoney}#
		  ${GoogsCreateInfoDto.paymentMoney}#${GoogsCreateInfoDto.receiveMoney}#
		  ${GoogsCreateInfoDto.alreadyBackCount}#${GoogsCreateInfoDto.backMoney}#
		  ${GoogsCreateInfoDto.stockNum}#${GoogsCreateInfoDto.code}" />&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:center; ">&nbsp;</td>
          <td nowrap="nowrap" >${GoogsCreateInfoDto.code}
          	<input type="hidden"  name="productIds" value="${GoogsCreateInfoDto.productId}" id="productId${indexId+1}">&nbsp;</td>
          
          <td nowrap="nowrap" >${GoogsCreateInfoDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${GoogsCreateInfoDto.productType}&nbsp;</td>
          <td nowrap="nowrap" >${GoogsCreateInfoDto.productUnit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.price}" pattern="#,##0.00000"/>&nbsp;<input type="hidden"   name="prices"  value="${GoogsCreateInfoDto.price}" id="price${indexId+1}"></td>
          
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${GoogsCreateInfoDto.inStockMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${GoogsCreateInfoDto.paymentMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${GoogsCreateInfoDto.receiveMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;" >${GoogsCreateInfoDto.alreadyBackCount}&nbsp;</td>
          <td nowrap="nowrap" ><input type="text" style="width:86px;" name="counts" maxlength="20" onblur="checkcount(this)" value="${GoogsCreateInfoDto.backCount}" id="count${indexId+1}">&nbsp;</td>          
          <td nowrap="nowrap" style=" text-align:right;" width="93"><fmt:formatNumber value="${GoogsCreateInfoDto.backMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;" ><div style="width:82">${GoogsCreateInfoDto.stockNum}&nbsp;</div></td>        
    	  </tr> 
       </logic:iterate>
      </table>
       <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
				<td width="40px">&nbsp;<input type="checkbox" name="checkbox3" id="chckbxSelectAllProduct" onclick="checkAll(this);"/></td>
				<td width="40px">
					全选
				</td>
				<td width="90" align="center">
					<a href="javascript:removeProduct();"><img src="${ctx}/images/btnDelete.gif" /></a>
				</td>
				<td width="100" align="center">
					<a href="javascript:stockroomSelect();"><img src="${ctx}/images/btnAdd.gif" /></a>
				</td>      
				<td align="right">
					返厂金额合计
				</td>
				<input type="hidden" name="modifyBuyBackGoodsDto.money" value="0.00000" id="money">
				<td align="right" id="totalmoney"  width="93"><fmt:formatNumber value="${modifyBuyBackGoodsInfoDto.money}" pattern="#,##0.00000"/>&nbsp;</td>
				<td align="left" height="25" width="83">元</td>
			</tr>
		</table>
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
         <a href="#" onclick="sendAddressSelect()">
		<img src="${ctx}/images/btnFHDZ.gif" /></a>
		<input type="hidden" name="modifyBuyBackGoodsDto.supplierAddressId" id="supplierAddressId">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" id="supplierName">${modifyBuyBackGoodsInfoDto.receiveName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" id="supplierAddress">${modifyBuyBackGoodsInfoDto.supplierAddress}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%" id="supplierLinkman">${modifyBuyBackGoodsInfoDto.supplierLinkman}&nbsp;</td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%" id="supplierPostcode">${modifyBuyBackGoodsInfoDto.supplierPostcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" id="supplierTel">${modifyBuyBackGoodsInfoDto.supplierTel}&nbsp;</td>
          <td class="td_01">手机</td>
          <td class="td_02" id="supplierMobile">${modifyBuyBackGoodsInfoDto.supplierMobile}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 要求发货日期</td>
          <td class="td_02" ><input type="text" name="modifyBuyBackGoodsDto.requestDate"  style="width:120px;" value="${modifyBuyBackGoodsInfoDto.requestDate}" onfocus="calendar()"/></td>
          <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
          <td class="td_02" >         
           <html:select property="modifyBuyBackGoodsDto.transportWay" value="${modifyBuyBackGoodsInfoDto.transportWay}" styleId="transportWay" onchange="changeTransportWay()">          
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
        </tr>
        <tr id="nameAndId">
          <td class="td_01"><span class="STYLE1">*</span> 提货人姓名</td>
          <td class="td_02" ><input type="text" name="modifyBuyBackGoodsDto.takeName" id="takeName" style="width:120px;" value="${modifyBuyBackGoodsInfoDto.takeName}" maxlength="4"/>&nbsp;</td>
          <td class="td_01"><span class="STYLE1">*</span> 身份证号码</td>
          <td class="td_02" ><input type="text" name="modifyBuyBackGoodsDto.takeNumber" id="takeNumber" style="width:120px;" value="${modifyBuyBackGoodsInfoDto.takeNumber}" maxlength="20"/>&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao">&nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%"><span class="STYLE1">*</span> 特殊说明</td>
          <td class="td_04" ><textarea name="modifyBuyBackGoodsDto.text" id="stext" cols="100" rows="4">${modifyBuyBackGoodsInfoDto.text}</textarea>
          </td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table id="modifyTableIdea" border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
      
        <tr>
          <td height="20px" colspan="2" >产品总监评审意见：</td>
        </tr>
       
        <tr>
          <td nowrap="nowrap">1.返厂数量是否符合</td>
          <td height="20px" nowrap="nowrap"><input type="checkbox" name="checkbox3" id="checkbox3" value="1" 
              <c:if test='${modifyBuyBackGoodsInfoDto.proMajIdea1 == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox3" id="checkbox3" value="0" 
              <c:if test='${modifyBuyBackGoodsInfoDto.proMajIdea1 == "0"}'>checked="checked"</c:if>  
            />不同意
         </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">2.返厂地址是否符合</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="checkbox3" id="checkbox3" value="1" 
              <c:if test='${modifyBuyBackGoodsInfoDto.proMajIdea2 == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox3" id="checkbox3" value="0" 
              <c:if test='${modifyBuyBackGoodsInfoDto.proMajIdea2 == "0"}'>checked="checked"</c:if>  
            />不同意
        </td>
        </tr>
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(modifyBuyBackGoodsInfoDto.proMajText)}&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${modifyBuyBackGoodsInfoDto.proMajName}</td>
          <td>日期：${modifyBuyBackGoodsInfoDto.proMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="checkbox3" id="checkbox3" value="1" 
              <c:if test='${modifyBuyBackGoodsInfoDto.buyManIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox3" id="checkbox3" value="0" 
              <c:if test='${modifyBuyBackGoodsInfoDto.buyManIdea == "0"}'>checked="checked"</c:if>  
            />不同意
        </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(modifyBuyBackGoodsInfoDto.buyManText)}&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${modifyBuyBackGoodsInfoDto.buyManName}</td>
          <td>日期：${modifyBuyBackGoodsInfoDto.buyManDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >运营总监评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="checkbox3" id="checkbox3" value="1" 
              <c:if test='${modifyBuyBackGoodsInfoDto.opeMajIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="checkbox3" id="checkbox3" value="0" 
              <c:if test='${modifyBuyBackGoodsInfoDto.opeMajIdea == "0"}'>checked="checked"</c:if>  
          />不同意
        </td>
        </tr>
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                 ${tf:replaceBr(modifyBuyBackGoodsInfoDto.opeMajText)}&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${modifyBuyBackGoodsInfoDto.opeMajName}</td>
          <td>日期：${modifyBuyBackGoodsInfoDto.opeMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <c:set var="disabled" value="disabled" scope="page"></c:set>
		<c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,0,1)==0}">
          <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,0,1)==1}">
          <c:set value="checked" var="productSpecPass" scope="page"></c:set>
        </c:if>
        <c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,1,2)==0}">
          <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,1,2)==1}">
          <c:set value="checked" var="productQualityPass" scope="page"></c:set>
        </c:if>
        <c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,2,3)==0}">
          <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
        </c:if>
        <c:if test="${modifyBuyBackGoodsInfoDto.stkAdmIdea!=null && fn:substring(modifyBuyBackGoodsInfoDto.stkAdmIdea,2,3)==1}">
          <c:set value="checked" var="productSumPass" scope="page"></c:set>
        </c:if>           
        
      
        <tr>
          <td height="20px" colspan="2" >库房管理员意见：</td>
        </tr>
        <tr>
          <td nowrap="nowrap">1.产品规格是否符合</td>
          <td height="20px" nowrap="nowrap">
          <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
        </td>
        </tr>
        <tr>
          <td nowrap="nowrap">2.产品质量是否符合</td>
            <td height="20px" nowrap="nowrap">
            <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
            <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">3.产品数量是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
          <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td height="20px" colspan="2" ><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                ${tf:replaceBr(modifyBuyBackGoodsInfoDto.stkAdmText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${modifyBuyBackGoodsInfoDto.stkAdmName}</td>
          <td>日期：${modifyBuyBackGoodsInfoDto.stkAdmDate}</td>
        </tr>
      </table>
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="50px" align="center" valign="bottom">
    	  <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
   		  <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  		  <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getBuyBackGoodsList.do' "/>
    </td>
    <td>&nbsp;</td>
  </tr> 

</table>
<br />
</html:form>
</body>
</html>
