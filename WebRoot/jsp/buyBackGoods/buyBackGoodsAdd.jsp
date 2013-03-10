<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建返厂单</title>
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
 			$("#addBuyBackGoogsDto").validate({
				rules: { 
					"addBuyBackGoogsDto.stockroomId":"required",
					"addBuyBackGoogsDto.requestDate":"required",
					"addBuyBackGoogsDto.transportWay":"required"
	
				},
				messages: {
					"addBuyBackGoogsDto.stockroomId":"请选择库房", 
					"addBuyBackGoogsDto.requestDate":"请输入要求发货日期",
					"addBuyBackGoogsDto.transportWay":"请选择货运方式"
					
				} 
			});
			
			
			//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
			
		});
			
			
		$(document).ready(function(){
			 
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
					//没有变化时退出
					if ( $("#lastStockId").val()==$("#stockroomId").get(0).selectedIndex){
					  return;
					}
					//选择的库房变化时清空
					if (confirm("更换库房将清空已有的返厂信息，请确认")){
						$("#lastStockId").val($("#stockroomId").get(0).selectedIndex);
						//删除行
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
			var priceobj = document.getElementById("price"+i);
			var countobj = document.getElementById("count"+i);
			if(box!=null){
				box.id="choose"+(lastDeleteIndex);
				priceobj.id="price"+(lastDeleteIndex);
				countobj.id="count"+(lastDeleteIndex);
				lastDeleteIndex=lastDeleteIndex+1;
			}
			 
		} 
		
	};
	
	
	//验证返厂数量是否是正整数
	function checkcount(obj){
	
	  var backcount =obj.value;
	  	
	  if(/^[1-9]\d{0,10}$/.test(backcount) ){
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
	  		if (tmpcheckFCMoney1<0){
	  			//alert(tnumRK-tnumZD-tnumFC-tnumPrice*backcount); 
	  			alert('返厂金额需<=入库金额-指定金额-已返厂金额!');
	  			return false;
	  		}
	  		var tmpcheckFCMoney2=FloatSub(FloatSub(FloatSub(tnumRK,tnumSP),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney2<0){
	  			//alert(tnumRK-tnumSP-tnumFC-tnumPrice*backcount);
	  			alert('返厂金额需<=入库金额-收票金额-已返厂金额!');
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
	//******
	//验证返厂数量是否是正整数
	function checkcountforSub(obj){
	
	  var backcount =obj.value;
	  if (backcount==""){
	  	alert('请输入返厂数！');
	    return false;
	  }
	  if(/^[1-9]\d{0,10}$/.test(backcount)){
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
	  		if (tmpcheckFCMoney3<0){
	  			//alert(tnumRK-tnumZD-tnumFC-tnumPrice*backcount);
	  			alert('返厂金额需<=入库金额-指定金额-已返厂金额!');
	  			return false;
	  		}
	  		var tmpcheckFCMoney4=FloatSub(FloatSub(FloatSub(tnumRK,tnumSP),tnumFC) ,FloatMul(tnumPrice,backcount));
	  		if (tmpcheckFCMoney4<0){
	  			//alert(tnumRK-tnumSP-tnumFC-tnumPrice*backcount);
	  			alert('返厂金额需<=入库金额-收票金额-已返厂金额!');
	  			return false;
	  		}
	  		return true;  
	  }else{
			alert("返厂数必须是正整数");
			return false;
	  } 
	}
		//*******
    //判断所有的返厂数是否正确 
 	function checkcountAllforSub(){ 
 		 
		var tableObj = document.getElementById("tableInStock");
		var tlength = tableObj.rows.length;
		var tmptotle=0;  
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var countobj= document.getElementById(countid); 
		    var backcount =countobj.value; 
		   if  (checkcountforSub(countobj)){ 
		   }else{
		   			countobj.focus();
		   		return false;
		   };  
		 }
		return true;
	}
	//*******************************
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
		    vtotle = FloatAdd(vtotle,tmptotle);
		 }
		$("#totalmoney").html(fmoney(vtotle,5)+'&nbsp;');
		$("#money").attr("value", vtotle);
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
		$("#subFlag").attr("value", subFlag); 
		 
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
				if ($.trim($('#text').val()).length==0){
					  alert("请输入特殊说明");
					  return false;
					};
			}
				
		
		if ($('#text').val().length>200){
		  alert("特殊说明长度过长");
		  return false;
		}; 	
		
	
			
		if ((subFlag=="save") || (subFlag="submit")){
		  $("#addBuyBackGoogsDto").submit();
		}else{
		  alert("非法提交！");
		}
	}
	
	//-------------------------------入库明细选择
	//进入 入库明细选择小画面
	function	stockroomSelect(){  
		 var inStockId = $("#inStockId").val(); 
		 var stockroomId = document.getElementById('stockroomId').value;
		 if (stockroomId==""){
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
		insertCell(myArrayInStock);
	};
	
	var countRow=0;//新增的行数,也是checkbox的id值
	var lastDeleteIndex=-1;
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
	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'" value="'+arr[0]+'">';
	        newTd1.innerHTML = '';
	        newTd2.innerHTML = '<input type="hidden"  name="productIds" value="'+arr[0]+'">'+''+arr[11];
	        newTd3.innerHTML = ''+arr[1];
	        newTd4.innerHTML = ''+arr[2];
	        newTd5.innerHTML = ''+arr[3]; 
	        newTd1.style.textAlign="center";
	        newTd6.style.textAlign="right";
	        newTd7.style.textAlign="right";
	        newTd8.style.textAlign="right";
	        newTd9.style.textAlign="right";
	        newTd10.style.textAlign="right";
	        newTd12.style.textAlign="right";
	        newTd13.style.textAlign="right";
	        
	        newTd6.innerHTML = '<input type="hidden"   name="prices" id="price'+countRow+'" value="'+rmoney(arr[4])+'">'+''+arr[4] + '&nbsp;'; 
	        newTd7.innerHTML = ''+arr[5] + '&nbsp;';
			newTd8.innerHTML = ''+arr[6] + '&nbsp;'; 
	        newTd9.innerHTML = ''+arr[7] + '&nbsp;';
	        newTd10.innerHTML = ''+arr[8] + '&nbsp;';
	        newTd11.innerHTML = '<input type="text" style="width:86px;" name="counts" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >';
	        newTd12.innerHTML = '0.00000'+ '&nbsp;';
	        newTd13.innerHTML = ''+arr[10]+ '&nbsp;';

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
	
 
//还原金额   
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	}	
	
//货运方式下拉	
	function changeTransportWay(){
	
	
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
	
		$("#nameAndId").hide(); 
	
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

</script>
 
</head>
<body>
<html:form action="addBuyBackGoogs.do"    styleId="addBuyBackGoogsDto">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img  src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购返厂管理 &gt;&gt; 新建返厂单</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">产品分类名称</td>
          <td class="td_02" width="30%">${backGoodsInfoDto.productTypeName}&nbsp;</td>
          <input type="hidden" name="addBuyBackGoogsDto.productTypeId" value="${backGoodsInfoDto.productTypeId}">
          <td class="td_01" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${backGoodsInfoDto.supplierName}&nbsp;</td>
          <input type="hidden" name="addBuyBackGoogsDto.supplierId" id="supplierId" value="${backGoodsInfoDto.supplierId}">
           <input type="hidden" name="addBuyBackGoogsDto.supplierName" value="${backGoodsInfoDto.supplierName}">
        </tr>
        <tr>
          <td class="td_01" height="18">产品合同号</td>
          <td class="td_02">${backGoodsInfoDto.productContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${backGoodsInfoDto.companyContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01">库房名称</td>
          <td class="td_02">
          
          <html:select property="addBuyBackGoogsDto.stockroomId"  styleId="stockroomId" style=" width:264px">
          	  <html:option value="">--请选择--</html:option>
	          <logic:iterate id="stockroomEntitie" name="stockroomEntities">
	          	<html:option value="${stockroomEntitie.id}">${stockroomEntitie.name} </html:option>
	          </logic:iterate>
          </html:select>
        <input type="hidden" id="lastStockId" value="0">
           </td>
          <td class="td_01">入库单号</td>
          <td class="td_02">${backGoodsInfoDto.id}&nbsp;</td>
          <input  type="hidden" name="addBuyBackGoogsDto.inStockId" value="${backGoodsInfoDto.id}" id="inStockId"/>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
       
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="tableInStock" >
        <tr >
          <th nowrap="nowrap" width="30" >选择</th>
          <th nowrap="nowrap">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap" >采购单价</th>
          <th nowrap="nowrap" >入库金额</th>
          <th nowrap="nowrap" >指定金额</th>
          <th nowrap="nowrap" >收票金额</th>
          <th nowrap="nowrap" >已返厂数</th>
          <th nowrap="nowrap" style="width:106px;text-align:center">返厂数</th>
          <th nowrap="nowrap" >返厂金额</th>
          <th nowrap="nowrap" width="82">库存数</th>
        </tr> 
      </table>
      
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
				<td width="40">&nbsp;<input type="checkbox" name="checkbox3" id="chckbxSelectAllProduct" onclick="checkAll(this);"/>
				</td>
				<td width="40">
					全选
				</td>
				<td width="100" align="center">
					<a href="javascript:removeProduct();"><img src="${ctx}/images/btnDelete.gif" /></a>
				</td>
				<td width="100" align="center">
					<a href="javascript:stockroomSelect();"><img src="${ctx}/images/btnAdd.gif" /></a>
				</td>
				<td align="right">
					返厂金额合计
				</td>
				<input type="hidden" name="addBuyBackGoogsDto.money" value="0.00000" id="money">
				<td align="right" width="100" id="totalmoney"  width="93">
					0.00&nbsp;
				</td>
				
				<td align="left" height="25" width="82">
					&nbsp;元&nbsp;
				</td>
			</tr>
		</table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
       <a href="#" onclick="sendAddressSelect()"> 
		<img src="${ctx}/images/btnFHDZ.gif" /></a>
		<input type="hidden" name="addBuyBackGoogsDto.supplierAddressId" id="supplierAddressId"  >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" id="supplierName">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" id="supplierAddress">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%" id="supplierLinkman">&nbsp;</td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%" id="supplierPostcode">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" id="supplierTel">&nbsp;</td>
          <td class="td_01">手机</td>
          <td class="td_02" id="supplierMobile">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 要求发货日期</td>
          <td class="td_02" ><input  type="text" name="addBuyBackGoogsDto.requestDate" id="requestDate" style="width:120px;"  onfocus="calendarMinToday()"/></td>
          <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
          <td class="td_02" >
          <html:select property="addBuyBackGoogsDto.transportWay" value="" onchange="changeTransportWay()" styleId="transportWay">
          
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
          <td class="td_02" ><input type="text" name="addBuyBackGoogsDto.takeName" id="takeName" style="width:120px;" maxlength="4"/>&nbsp;</td>
          <td class="td_01"><span class="STYLE1">*</span> 身份证号码</td>
          <td class="td_02" ><input type="text" name="addBuyBackGoogsDto.takeNumber" id="takeNumber" style="width:120px;" maxlength="20"/>&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%"><span class="STYLE1">*</span> 特殊说明</td>
          <td class="td_04" ><textarea name="addBuyBackGoogsDto.text" id="text" cols="100" rows="4"></textarea>
          </td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <a href="${ctx}/instockList.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
    <input type="hidden" id="subFlag" value="${subFlag}" name ="subFlag"/>
  </tr>
</table>
</html:form>
</body>
</html>
