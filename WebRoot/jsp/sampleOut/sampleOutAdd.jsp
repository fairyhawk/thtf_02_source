<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
.STYLE1 {color: #FF0000}
-->
</style>
<script language="JavaScript">
	 $(function(){
	   //隐藏姓名和身份证号码 
	 if($("#transportWay").val() !=1   ){
		$("#nameAndId").hide(); 
	 }
		 //选择自提时 显示姓名和身份证号码
		 $("#transportWay").change(function(){   
			$('option:selected', this).each(function(){ 
			
				if( this.value == 1 && $("#companyType").val()!=1){ 
					  $("#nameAndId").show();  
				}else{
					$("#nameAndId").hide(); 
				}
			}); 
		 }); 
		 //借出类型变化时隐藏归还日期
		 // 
		 $(':radio[name=createSampleOutDto.type]').click(function(){   
			 
			var va = $("input[name='createSampleOutDto.type']:checked").val();
				if( va == 0 ){ 
					 $("#tdindate").html('&nbsp');  
					 $("#textindate").html('<input type="hidden" name="createSampleOutDto.inDate" value=""/>&nbsp')  
				}else if (va==1){
					 $("#tdindate").html('<span class="STYLE1">*</span> 预计归还日期');  
					 $("#textindate").html('<input type="text" name="createSampleOutDto.inDate" id="name15" style="width:120px;" onfocus="calendarMinToday()"/>')
				}
			  
		 }); 
		 //
		 
   });
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
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
				$("#createSampleOutForm").validate({
				rules: { 
				    "createSampleOutDto.type":"required",
				    "createSampleOutDto.inDate" :{
	                    required: {
	                        depends: function(element) {
	                            var va=$("input[name='createSampleOutDto.type']:checked").val();
	                            return (va == 1);
	                        }
	                    }
	                 }, 
				    "createSampleOutDto.companyType":"required",
				    "createSampleOutDto.custosName":"required",
				    "createSampleOutDto.productTypeId":"required",
					"createSampleOutDto.outStockroomId":"required",
					"createSampleOutDto.requestDate":"required",
					"createSampleOutDto.transportWay":"required"
					
				},
				messages: {
				
				    "createSampleOutDto.type":"请选择借出单类型",
				    "createSampleOutDto.inDate":"请输入预计归还日期",
				    "createSampleOutDto.companyType":"请选择借出单位类型",
				    "createSampleOutDto.custosName":"请输入保管人",
				    "createSampleOutDto.productTypeId":"请选择产品分类",
					"createSampleOutDto.outStockroomId":"请选择库房",					
					"createSampleOutDto.requestDate":"请输入要求发货日期",
					"createSampleOutDto.transportWay":"请选择货运方式"
					
				} 
			});
				
				
			});
			
			
	   //提交保存
	 function sub(subFlag){
		$("#subFlag").attr("value", subFlag);
			if($("#companyId").val() ==""){
			  if ($("#companyType").val()==2){
					alert("请选择客户");
					return false;  
				}
				if ($("#companyType").val()==3){
					alert("请选择供应商"); 
					return false;
				}
				$("#companyType").val(1);
			}
			
	 
		//表格中没有数据不提示
		if ($("#table tr").length<=1){
		  $("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
		  
		  alert("请选择产品");
		  return false;
		}else{
		   //如果有数据判断是否为空和是否是数字
		   if( checkcountAllforSub()==false){ 
		     return false;
		   }
		totleMoneyMethod();
		};  
		//发货地址
		if ($("#addressId").val()==""){
		  alert("请选择发货地址");
		  return false;
		} 
		if ($('#text').val().length>200){
		  alert("特殊说明长度过长");
		  return false;
		};
		
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

		if ((subFlag=="save") || (subFlag="submit")){
		  $("#createSampleOutForm").submit();
		}else{
		  alert("非法提交！");
		}
	}
	
			
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
			var priceobj = document.getElementById("price"+i);
			if(box!=null){
				box.id="choose"+(lastDeleteIndex);
				countobj.id="count"+(lastDeleteIndex);
				priceobj.id="price"+lastDeleteIndex;
				lastDeleteIndex=lastDeleteIndex+1;
			}
			 
		}
		totleMoneyMethod();	
	};
	
	//验证借出数量是否是正整数 
	function checkcount(obj){ 
	   
	   var backcount =obj.value; 
	   if (backcount==""){return true}
	    
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){ 
	     if("${USERLOGIN.roleId}"==8){
	     
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出可用数
	  		var tnum =  tbobj.rows[row].cells[cell+2].innerText;
	  		 
	  		 if (Number(backcount)>Number(tnum)){
	  		 	alert("借出数不能大于借出可用数");
	  		 	return false;
	  		 }; 
	  		 //计算借出金额
	  		 var num =  obj.value ; //借出数量
		     var price = rmoney(tbobj.rows[row].cells[cell-1].innerText);//库存单价
		     var tmptotle=FloatMul(num,price);
		     
		     tbobj.rows[row].cells[cell+1].innerHTML=fmoney(tmptotle,2)+'&nbsp;';
		     
	  		 //计算总的借出金额
			if (checkcountAll){
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
		 	//获得借出可用数
	  		var tnum =  tbobj.rows[row].cells[cell+1].innerText;
	  		
	  		 if (Number(backcount)>Number(tnum)){
	  		 	alert("借出数不能大于借出可用数");
	  		 	return false;
	  		 }; 
	  		 
	  	}
	  	
	  		 
	  }else{
			alert("借出数必须是正整数");
			obj.focus();
	  }
	}
	
    //判断所有的借出数量是否正确 
 	function checkcountAll(){ 

		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var tmptotle=0; 
		
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
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
	
	//计算借出金额合计
	function totleMoneyMethod(){
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var tmptotle=0; 
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i;
		    var countobj= document.getElementById(countid);
		    
	  		//获得行
	   		var row = countobj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = countobj.parentNode.cellIndex;
		 	//借出数量
	  		var num =  countobj.value;
	  		 
		    //var price = rmoney(tableObj.rows[row].cells[cell-1].innerText); //库存单价
		    
		    var priceNo ="#"+"price"+i;
		    var price = rmoney($(priceNo).val());
		    tmptotle=rmoney(fmoney(FloatMul(num,price),2));
		    vtotle = FloatAdd(vtotle,tmptotle);
		 } 
		if(${USERLOGIN.roleId!=4}){
		$("#totalmoney").html(fmoney(vtotle,2)+'&nbsp;'); 
		
		}
		
		var tmptax =FloatMul(FloatDiv(FloatAdd($("#companyTax").val(),100),100),1);
		$("#moneyTotal").val(rmoney(fmoney(FloatDiv(vtotle,tmptax),2)));
		
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
		//选择的库房变化时清空
		if (confirm("更换库房将清空已有的返厂信息，请确认")){
			$("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
			//删除行
			var oldCount = countRow+1;
			var table = document.getElementById("table"); 
			for(var i=countRow;i>0;i--){ 
				table.deleteRow(i);
				countRow--;
				lastDeleteIndex=i-1;
				indexRow--; 
			}
			
			
			
		 }else {
			$("#outStockroomId").get(0).selectedIndex=$("#lastStockId").val(); 
			return;
		}    	
		totleMoneyMethod();		   		
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
			if (confirm("更换产品分类将清空已有的返厂信息，请确认")){
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
	  totleMoneyMethod(); 		   		
	 }  
			
	
 
</script>

<script language="JavaScript">

	
	
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
		totleMoneyMethod(); 
	};
	
	
	var countRow=0;//新增的行数,也是checkbox的id值 
	var indexRow=0;//序号
	
	//从产品选择小页面返回的信息,显示
	function insertCell(myArray){
		if("${USERLOGIN.roleId}"==8){
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
	        var newTd8 = newTr.insertCell(); 
	        
	        //设置列内容和属性
	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'">'; 
	        newTd1.innerHTML = '<input type="hidden"  name="productIds" value="'+arr[0]+'">'+''+arr[1];
	        newTd2.innerHTML = ''+arr[2];
	        newTd3.innerHTML = ''+arr[3];
	        newTd4.innerHTML = ''+arr[4];
	        newTd5.style.textAlign="right"; 
	        newTd5.innerHTML ='<input type="hidden" name="prices" value="'+fmoney(arr[5],2)+'"  id="price'+countRow+'">'+ ''+fmoney(arr[5],2)+'&nbsp;';
	        newTd6.style.textAlign="left";  
	        newTd6.innerHTML = '<input type="text" style="width:86px;" name="counts"  onblur="checkcount(this)" id="count'+countRow+'" maxlength="20" >';
			newTd7.style.textAlign="right"; 
			newTd7.innerHTML = '0.00'+'&nbsp;';
			newTd8.style.textAlign="right";  
			newTd8.innerHTML = '<input type="hidden" value="'+arr[6]+'" id="usercount'+countRow+'" >'+''+arr[6]+'&nbsp;';
		  } 
		}
		if("${USERLOGIN.roleId}"==4){
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
	        
	        
	        //设置列内容和属性
	        newTd0.innerHTML = '<input type="checkbox" name="productCheckbox" id="choose'+countRow+'">'; 
	        newTd1.innerHTML = '<input type="hidden"  name="productIds" value="'+arr[0]+'">'+''+arr[1];
	        newTd2.innerHTML = ''+arr[2];
	        newTd3.innerHTML = ''+arr[3];
	        newTd4.innerHTML = ''+arr[4];
	        //newTd5.style.textAlign="right"; 
	        //newTd5.innerHTML ='<input type="hidden" name="prices" value="'+arr[5]+'"  id="price'+countRow+'">'+ ''+fmoney(arr[5],2)+'&nbsp;';
	        newTd5.innerHTML ='<input type="hidden" name="prices" value="'+arr[5]+'"  id="price'+countRow+'">'+ ''+'<input type="text" style="width:86px;" name="counts"  onblur="checkcount(this)" id="count'+countRow+'" maxlength="20" >';
	        newTd6.style.textAlign="right";  
	        newTd6.innerHTML = '<input type="hidden" value="'+arr[6]+'" id="usercount'+countRow+'" >'+''+arr[6];
			
		  } 
		}
		
		
		
		
		$("#tableLength").attr("value", countRow);
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
			
	}	
	//显示结束/----------------------/
</script>

<script language="JavaScript">

	//打开客户选择画面
	function openCustomSelect(){ 
		var wincus =window.open('${ctx}/getSampleOutCoustomerSelect.do','newwindow', 
			'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=432');
		arrSubWin.push(wincus);     
		//变化时清空信息 --发货地址
		document.getElementById("addressId").value = "" 
		document.getElementById("receiveName").innerHTML = "&nbsp;"
		document.getElementById("address").innerHTML = "&nbsp;"
		document.getElementById("linkman").innerHTML = "&nbsp;"
		document.getElementById("postcode").innerHTML = "&nbsp;"
		document.getElementById("tel").innerHTML = "&nbsp;"
		document.getElementById("mobile").innerHTML = "&nbsp;";
	
	}
	
	//打开供应商选择画面
	function openSupplierSelect(){ 
		var wincus =window.open('${ctx}/getSampleOutSupplierSelect.do','newwindow', 
			'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=432');
		arrSubWin.push(wincus);     
		//变化时清空信息 --发货地址
		document.getElementById("addressId").value = "" 
		document.getElementById("receiveName").innerHTML = "&nbsp;"
		document.getElementById("address").innerHTML = "&nbsp;"
		document.getElementById("linkman").innerHTML = "&nbsp;"
		document.getElementById("postcode").innerHTML = "&nbsp;"
		document.getElementById("tel").innerHTML = "&nbsp;"
		document.getElementById("mobile").innerHTML = "&nbsp;";
	
	}
	
	//进入产品选择画面
	function productTypeSelect(){
		 if($("#productTypeId").get(0).selectedIndex ==0){ 
	 	   alert('请选择产品分类');
	 	   return;
	 	 }; 
	
	 	 if($("#outStockroomId").get(0).selectedIndex ==0){ 
	 	   alert('请选择库房');
	 	   return;
	 	 };
	 	
	 	 
        var outStockroomId=$("#outStockroomId").val(); 
        var productTypeId=$("#productTypeId").val(); 
        
        var productTypeName=$("#productTypeId").find("option:selected").text();
		var win1 = window.open('${ctx}/getSampleOutProductSelect.do?type=1&outStockroomId='+outStockroomId+'&productTypeId='+productTypeId+'&productTypeName='+encodeURI(productTypeName,"UTF-8"),
					'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=300, width=800,height=400');
		arrSubWin.push(win1);
	}
	
	
     //发货地址选择 
	function  openAdddressSelect(){ 
		//销售经理时为公司发货地址
		if (${USERLOGIN.roleId==4}){ 
			var customerId= $("#companyId").val();
		 
			if($("#companyId").val() ==""){ 
				alert("请选择客户"); 
				return false; 
			}
			 var win2 =window.open('${ctx}/getSampleOutCustomerAddressList.do?customerId='+customerId,'newwindow', 
				'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
			arrSubWin.push(win2);
		}
		
		//采购专业时根据类型
		if (${USERLOGIN.roleId==8}){ 
			var companyType = $("#companyType").val();
			if($("#companyId").val() ==""){
			  if ($("#companyType").val()==2){
					alert("请选择客户");
					return false;  
				}
				if ($("#companyType").val()==3){
					alert("请选择供应商"); 
					return false;
				}
				$("#companyType").val(1);
			}
			
			if  (companyType==1){
					//打开公司发货地址 
				var win2 = window.open('${ctx}/getSampleOutCompanyAddressList.do','newwindow', 
				'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(win2);
			} else if (companyType==2){
				//打开客户发货地址
				var customerId= $("#companyId").val();
		 
				var win2 = window.open('${ctx}/getSampleOutCustomerAddressList.do?customerId='+customerId, 'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(win2);
			}else if(companyType==3){
			var  supplierId = $("#companyId").val();
				if (supplierId==""){
				  alert("请选择供应商！");
				  return false;
				}
				//打开供应商发货地址
				var win2 = window.open('${ctx}/getSampleOutSupplierAddressList.do?supplierId='+supplierId, 'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(win2);
			}
		}  	 
		 
	}
	
	//发货地址清空
	function  clearAdddressSelect(t){
		 
		if ($("#companyType").val()==t){
		 return true;
		} 
		
		 $("#companyType").val(t) ;
		 if ($("#companyType").val()==1){
		 	$("#disCompanyType").html('公司');
		 	$("#companyId").val(${companyEntity.id});
		 	//变化时清空信息 
			$("#disPlayName").html("${companyEntity.name}&nbsp;");
			closeSubWin();
		 }	     
		 if ($("#companyType").val()==2){
		    $("#disCompanyType").html('<img src="${ctx}/images/btnKHXZ.gif" width="72" height="20" onclick="openCustomSelect()"/>') ;
		    $("#companyId").val(''); 
	     	//变化时清空信息 --客户名称
			$("#disPlayName").html('&nbsp;');
			closeSubWin();
	     }
	     if ($("#companyType").val()==3){
	     	$("#disCompanyType").html('<img src="${ctx}/images/btnGHSXZ.gif" width="89" height="20" onclick="openSupplierSelect()"/>');
	     	$("#companyId").val('');
	     	//变化时清空信息 --供货商名称
			$("#disPlayName").html('&nbsp;');
			closeSubWin();
	     }	
	         
	         
		//变化时清空信息 --发货地址
		document.getElementById("addressId").value = "" 
		document.getElementById("receiveName").innerHTML = "&nbsp;"
		document.getElementById("address").innerHTML = "&nbsp;"
		document.getElementById("linkman").innerHTML = "&nbsp;"
		document.getElementById("postcode").innerHTML = "&nbsp;"
		document.getElementById("tel").innerHTML = "&nbsp;"
		document.getElementById("mobile").innerHTML = "&nbsp;";
		 //选择自提时 显示姓名和身份证号码  
		if( $("#transportWay").val() == 1 && $("#companyType").val()!=1){ 
			  $("#nameAndId").show();  
		}else{
			$("#nameAndId").hide(); 
		}		 
	}
	
	
	 //******
	//验证借出数量是否是正整数
	function checkcountforSub(obj){
	
	  var backcount =obj.value;
	  
	  if (backcount==""){
	  	alert('请输入借出数！');
	    return false;
	  }
	  if(/^[1-9]\d{0,10}$/.test(backcount) || backcount==0){
	  
	      if (${USERLOGIN.roleId==8}){ 
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出可用数
	  		var tnum =  tbobj.rows[row].cells[cell+2].innerText;
	  		 
	  		 if (Number(backcount)>Number(tnum) && backcount>0){
	  		 	alert("借出数不能大于借出可用数");
				obj.focus();
	  		 	return false;
	  		 }; 
	  		return true;
	  	  } 
	  	  
	  	  if (${USERLOGIN.roleId==4}){ 
	  		//获得table
	  		var tbobj = obj.parentNode.parentNode.parentNode;
	  		//获得行
	   		var row = obj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = obj.parentNode.cellIndex;
		 	//获得借出可用数
	  		var tnum =  tbobj.rows[row].cells[cell+1].innerText;
	  		 if (Number(backcount)>Number(tnum) && backcount>0){
	  		 	alert("借出数不能大于借出可用数");
				obj.focus();
	  		 	return false;
	  		 }; 
	  		return true;
	  	  } 
	  }else{
			alert("借出数必须是正整数");
			return false;
	  } 
	}
	    //判断所有的返厂数是否正确 
 	function checkcountAllforSub(){  
		var tableObj = document.getElementById("table");
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
	
	
//隐藏table的一列
//$(document).ready(function(){
	//if("${USERLOGIN.roleId}"==4){
      
  	  //$("#priceth").hide();
  	  //$("#moneyth").hide();
  	  //$("td[name='needhide']").hide();
  	//}
//});

	
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
<html:form action="createSampleOut.do" styleId="createSampleOutForm">
<input type="hidden" name="lastStockId" id="lastStockId" value="0">
<input type="hidden" name="lastProductId" id="lastProductId" value="0">


<table width="100%" border="0" cellpadding="0" cellspacing="0">
 
     
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品借出管理 &gt;&gt; 新建样品借出单</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
	 <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
       <tr>
         <td class="td_01" width="20%"><span class="STYLE1">*</span> 借出单类型</td>
         <td class="td_02" width="30%"><input type="radio" name="createSampleOutDto.type" id="radio" value="0" />
           不归还&nbsp;&nbsp;&nbsp;
           <input name="createSampleOutDto.type" type="radio" id="radio2" value="1" checked="checked" />
           归还</td>
       
         <td class="td_01" width="20%" id="tdindate"><span class="STYLE1">*</span> 预计归还日期</td>
         <td class="td_02" width="30%" id="textindate"><input type="text" name="createSampleOutDto.inDate" id="name15" style="width:120px;" onfocus="calendarMinToday()"/></td>
       </tr>
       
       <input type="hidden" name="companyTax" id="companyTax" value="${companyEntity.taxRate}">
       <c:if test="${USERLOGIN.roleId==8}">
       <input type="hidden" name="createSampleOutDto.companyType" id="companyType" value="1"/>
       <input type="hidden" name="createSampleOutDto.companyId" id="companyId" value="${companyEntity.id}">
       
        <tr>
          <td class="td_01">
	         <span class="STYLE1">*</span> 借出单位类型选择
          </td>
          <td  class="td_02" >
          <input type="radio" onclick="clearAdddressSelect(1)" name="createSampleOutDto.companyType" id="radio" value="1" checked="checked"  />
            公司&nbsp;&nbsp;&nbsp;
          <input type="radio" onclick="clearAdddressSelect(2)" name="createSampleOutDto.companyType" id="radio2" value="2" />
            客户&nbsp;&nbsp;&nbsp;
          <input type="radio" onclick="clearAdddressSelect(3)" name="createSampleOutDto.companyType" id="radio3" value="3"  />
            供应商      
          
          </td>
          <td class="td_01">保管人</td>
          <td class="td_02" ><input type="text" name="createSampleOutDto.custosName" style="width:120px;" value="${USERLOGIN.name}" maxlength="4"/>&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" id="disCompanyType" height="18">
          	公司&nbsp;
          </td>
          <td  class="td_02" id="disPlayName">${companyEntity.name}&nbsp;</td>
          <td class="td_01">&nbsp;</td>
          <td class="td_02" >&nbsp;</td>
        </tr>
        
        </c:if>
         
        <c:if test="${USERLOGIN.roleId==4}">
        <input type="hidden" name="createSampleOutDto.companyType" id="companyType" value="2"/>
        <input type="hidden" name="createSampleOutDto.companyId" id="companyId" value="">
        <tr>
          <td class="td_01">
	         <img src="${ctx}/images/btnKHXZ.gif" width="72" height="20" align="absmiddle" onclick="openCustomSelect()"/>
          </td>    
          <td  class="td_02" id="disPlayName">&nbsp;</td>     
          <td class="td_01">保管人</td>
          <td class="td_02" ><input type="hidden" name="createSampleOutDto.custosName"  value="${USERLOGIN.name}"/>${USERLOGIN.name}&nbsp;</td>
        </tr>    
        </c:if>          
       
      
       <tr>
         <td class="td_01"><span class="STYLE1">*</span> 产品分类名称</td>
         <td class="td_02" >
          <html:select property="createSampleOutDto.productTypeId"  styleId="productTypeId" style="width:126px" onchange="removeProductChange()">
             <html:option value="">--请选择--</html:option>
			 <logic:iterate id="productTypeEntity" name="productTypeEntities" >
					<html:option value="${productTypeEntity.id}"> ${productTypeEntity.name}</html:option> 
			 </logic:iterate>
          </html:select> 
         </td>
         <td class="td_01">&nbsp;</td>
         <td class="td_02" >&nbsp;</td>
       </tr>
     </table>
	 <br />
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
		<tr>
				<td class="td_03" width="50%"><span class="STYLE1">*</span> 库房名称</td>
				<td class="td_04">
					<html:select property="createSampleOutDto.outStockroomId" styleId="outStockroomId"  style="width:264px" onchange="removeStockroomChange()">
						<html:option value="">--请选择--</html:option>
						<logic:iterate id="stockroomEntity" name="stockroomEntities" >
							 <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
						</logic:iterate>
					</html:select> 
				</td>
				</tr>
	</table> 
    <div style="width:100%; text-align:right">单位：元</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
		<tr>
			<th nowrap="nowrap" width="30px">选择</th>
			<th nowrap="nowrap">产品编码</th>
			<th nowrap="nowrap">产品名称</th>
			<th nowrap="nowrap">规格型号</th>
			<th nowrap="nowrap">单位</th>
			<c:if test="${USERLOGIN.roleId!=4 }">
			<th nowrap="nowrap" id="priceth">库存单价</th>
			</c:if>
			<th nowrap="nowrap" width="98">借出数</th>
			<c:if test="${USERLOGIN.roleId!=4 }">
			<th nowrap="nowrap" width="96" id="moneyth">&nbsp;&nbsp;&nbsp;借出金额&nbsp;&nbsp;&nbsp;</th>
			</c:if>
			<th nowrap="nowrap" width="80">&nbsp;借出可用数&nbsp;</th>
			</tr>
	</table>
   
  	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
      <tr>
        <td width="30" height="25">&nbsp;<input type="checkbox" name="checkbox5" id="checkbox4" onclick="checkAll(this)"/></td>
        <td>全选&nbsp;&nbsp;&nbsp;&nbsp;
        <img src="${ctx}/images/btnDelete.gif" align="absmiddle" onclick="removeProduct()"/>&nbsp;&nbsp;&nbsp;
        <img src="${ctx}/images/btnAdd.gif" width="65" height="20" align="absbottom" onclick="productTypeSelect()"/>
        </td>
        <td>&nbsp;</td>
        <c:if test="${USERLOGIN.roleId!=4 }">
        <td style=" text-align:right;" name="needhide">借出金额合计</td>
        <td style=" text-align:right;width:96px" id="totalmoney" name="createSampleOutDto.totalmoney">0.00&nbsp;</td>
        <td style="width:80px" name="needhide">&nbsp;元</td>
        </c:if>
      </tr>
    </table>
  	<br />
  	<div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
	&nbsp;&nbsp;<a href="#" onclick="openAdddressSelect()"><img src="${ctx}/images/btnFHDZ.gif" width="99" height="20" /></a>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
	<input type="hidden" name="createSampleOutDto.addressId" id="addressId">
      <tr>
        <td class="td_01" height="18px">货物接收单位名称</td>
        <td colspan="3" class="td_02" id="receiveName">&nbsp;</td>
      </tr>
      <tr>
        <td class="td_01" height="18px">发货地址</td>
        <td colspan="3"  class="td_02" id="address">&nbsp;</td>
      </tr>
      <tr>
        <td class="td_01" height="18px">联系人</td>
        <td class="td_02" id="linkman">&nbsp;</td>
        <td class="td_01">邮编</td>
        <td class="td_02" id="postcode">&nbsp;</td>
      </tr>
      <tr>
        <td class="td_01" width="20%" height="18px">电话</td>
        <td class="td_02" width="30%" id="tel">&nbsp;</td>
        <td class="td_01" width="20%">手机</td>
        <td class="td_02" width="30%" id="mobile">&nbsp;</td>
      </tr>
      <tr>
        <td class="td_01"><span class="STYLE1">*</span> 要求发货日期</td>
        <td class="td_02" ><input type="text" name="createSampleOutDto.requestDate" id="name2" style="width:120px;" onfocus="calendarMinToday()"/></td>
        <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
        <td class="td_02" > 
           <html:select property="createSampleOutDto.transportWay"  styleId="transportWay"   style=" width:126px">
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
      <tr id="nameAndId">
        <td class="td_01"><span class="STYLE1">*</span> 提货人姓名</td>
        <td class="td_02" ><input type="text" name="createSampleOutDto.takeName" id="takeName" style="width:120px;" maxlength="4"/></td>
        <td class="td_01"><span class="STYLE1">*</span> 身份证号码</td>
        <td class="td_02" ><input type="text" name="createSampleOutDto.takeNumber" id="takeNumber" style="width:120px;" maxlength="20"/></td>
      </tr>
    </table>
	<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="createSampleOutDto.text" id="text" cols="100" rows="4"></textarea></td>
				</tr>
			</table>
        </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/getSampleOutList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
  <input type="hidden" id="subFlag" value="${subFlag}" name ="subFlag"/>
  <input type="hidden" id="moneyTotal" value="0.00" name ="createSampleOutDto.moneyTotal"/>
</table>
<br />
</html:form>
</body>
</html>
