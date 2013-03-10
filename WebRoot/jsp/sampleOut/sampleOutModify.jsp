<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品借出单修改</title>
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

 	 $(function(){
 	 totleMoneyMethod();
	 
	  //隐藏姓名和身份证号码 
	 if($("#transportWay").val() == 1 && $("#companyType").val()!=1){ 
					  $("#nameAndId").show();  
				}else{
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
		//隐藏归还日期 	 
		if(${assessDto.type == 0}){
		  $("#tdindate").html('&nbsp');  
		// $("#textindate").html('<input type="hidden" name="modifySampleOutDto.inDate" value=""/>&nbsp')  ;
		$("#inDate").hide(); 
	}else if (${assessDto.type == 1}){
		  $("#tdindate").html('<span class="STYLE1">*</span> 预计归还日期');  
		// $("#textindate").html('<input type="text" name="modifySampleOutDto.inDate" id="name15" style="width:120px;" value="${assessDto.inDate}" onfocus="calendarMinToday()"/>')
		$("#inDate").show();
	 }   
	 $(':radio[name=modifySampleOutDto.type]').click(function(){   
			 
			var va = $("input[name='modifySampleOutDto.type']:checked").val();
				if( va == 0 ){ 
					 $("#tdindate").html('&nbsp');  
					 //$("#textindate").html('<input type="hidden" name="modifySampleOutDto.inDate" value=""/>&nbsp')  ;
					 $("#inDate").hide(); 
				}else if (va == 1){
					 $("#tdindate").html('<span class="STYLE1">*</span> 预计归还日期');  
					// $("#textindate").html('<input type="text" name="modifySampleOutDto.inDate" id="inDate" style="width:120px;" value="${assessDto.inDate}" onfocus="calendarMinToday()"/>')
				$("#inDate").show(); 
				
				}
			  
		 });	 
	///隐藏归还日期 
		 
		 
	 });
	  
	$(document).ready(function(){
		if  (countRow==0){
		   countRow=$("#table tr").length-1;
		} 
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
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
		$("#modifySampleOutDto").validate({
				rules: {  
					"modifySampleOutDto.inDate" :{
	                    required: {
	                        depends: function(element) {
	                            var va=$("input[name='modifySampleOutDto.type']:checked").val();
	                            return (va == 1);
	                        }
	                    }
	                 },
	                 
					"modifySampleOutDto.custosName":"required",
					"modifySampleOutDto.productTypeId":"required",
					"modifySampleOutDto.outStockroomId":"required" ,					
					"modifySampleOutDto.requestDate":"required" ,
					"modifySampleOutDto.transportWay":"required"
				},
				messages: {
					"modifySampleOutDto.inDate":"请选择预计归还日期",
					"modifySampleOutDto.custosName":"请选择保管人",
					"modifySampleOutDto.productTypeId":"请选择产品分类",
					"modifySampleOutDto.outStockroomId":"请选择库房",
					"modifySampleOutDto.requestDate":"请选择要求发货日期" ,
					"modifySampleOutDto.transportWay":"请选择货运方式"

				} 
			});	
			
		});
			
			
		//var s=	$("#tdxxxxxid").parent().attr("id");
		//$("#"+s" td:nth-child(8)").text('ffff');
			
		//提交保存
		function sub(subFlag){
	 
			$("#method").attr("value", subFlag);
			
			
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
			
			if ($("#textarea").val().length>200){
			  alert("特殊说明长度过长");
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
			if ((subFlag=="save") || (subFlag="submit")){
				$("#modifySampleOutDto").submit();
			}else{
			  alert("非法提交！");
			}
		
		}	
			
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
		 	//获得借出可用数
	  		var tnum =  tbobj.rows[row].cells[cell+2].innerText;
	  		 
	  		 if (Number(backcount)>Number(tnum) && backcount>0){
	  		 	alert("借出数不能大于借出可用数");
	  		 	return false;
	  		 }; 
	  		 //计算借出金额
	  		 var num =  obj.value ; //借出数量
		     var priceTax = rmoney(tbobj.rows[row].cells[cell-1].innerText); //库存单价
		     var tmptotle=FloatMul(num,priceTax);
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
	  		var tnum =  tbobj.rows[row].cells[6].innerText;
	  		 
	  		 if (Number(backcount)>Number(tnum)){
	  		 	alert("借出数不能大于借出可用数");
	  		 	return false;
	  		 };
	  		 
	  	}
	  	
	  		 
	  }else{
			alert("借出数必须是正整数");
			
	  }
	}
	
	
	//计算借出金额合计
	function totleMoneyMethod(){
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var tmptotle=0; 
		var tmptax =FloatMul(FloatDiv(FloatAdd($("#companyTax").val(),100),100),1);  
		for(var i=1;i<tlength;i++){
		    var countid = "count"+i; 
		    var countobj= document.getElementById(countid);
		     
		    //获得行
	   		var row = countobj.parentNode.parentNode.rowIndex;
	   		//获得列
			var cell = countobj.parentNode.cellIndex;
		 	//借出数量
	  		var num =  countobj.value; 
		    if (num==""){num=0}; 
		    //var price = rmoney(tableObj.rows[row].cells[cell-1].innerText); //库存单价
		    
		    var priceNo ="#"+"priceTax"+i;
		    var priceTax = rmoney($(priceNo).val());
		    //var priceTax=rmoney(fmoney(FloatMul(price,tmptax),2));
		    tmptotle=rmoney(fmoney(FloatMul(num,priceTax),2));
		    vtotle = FloatAdd(vtotle,tmptotle);
		    
		 } 
		
		$("#totalmoney").html(fmoney(vtotle,2)+'&nbsp;'); 
		$("#money").val(rmoney(fmoney(FloatDiv(vtotle,tmptax),2)));
		
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
				
 

	$(function(){
		$("#modifyTableIdea :checkbox").attr("disabled", true);
	
	});
	
		
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

	//打开客户选择画面
	function openCustomSelect(){ 
		var wincus = window.open('${ctx}/getSampleOutCoustomerSelect.do','newwindow', 
			'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
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
		var wincus = window.open('${ctx}/getSampleOutSupplierSelect.do','newwindow', 
			'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
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
		var wincus = window.open('${ctx}/getSampleOutProductSelect.do?type=1&outStockroomId='+outStockroomId+'&productTypeId='+productTypeId+'&productTypeName='+encodeURI(productTypeName,"UTF-8"),
					'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		arrSubWin.push(wincus);
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
	        newTd5.innerHTML = '<input type="hidden"   name="priceTaxs" id="priceTax'+countRow+'" value="'+arr[5]+'">'+''+fmoney(arr[5],2)+'&nbsp;'+'<input type="hidden" name="timeStamps" value="'+arr[7]+'" id="timeStamps'+countRow+'" >'+'<input type="hidden"   name="prices" id="price'+countRow+'" value="'+arr[8]+'">';
	         newTd6.innerHTML = '<input type="text" style="width:86px;"  name="counts" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >';
			newTd7.style.textAlign="right";
			newTd7.innerHTML = ' 0.00'+'&nbsp;';
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
	        //newTd4.innerHTML = ''+arr[4];
	        //newTd5.style.textAlign="right"; 
	        newTd4.innerHTML = '<input type="hidden"   name="priceTaxs" id="priceTax'+countRow+'" value="'+arr[5]+'">'+''+arr[4]
	        +'&nbsp;'+'<input type="hidden" name="timeStamps" value="'+arr[7]+'" id="timeStamps'+countRow+'" >'
	        +'<input type="hidden"   name="prices" id="price'+countRow+'" value="'+arr[8]+'">';
	         
	         newTd5.innerHTML = '<input type="text" style="width:86px;"  name="counts" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >';
			 
			 
			newTd6.style.textAlign="right";
			newTd6.innerHTML = '<input type="hidden" value="'+arr[6]+'" id="usercount'+countRow+'" >'+''+arr[6];
			
			
			//******************************
			    //添加列
	      
			//****

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
		//	var outMoneyObj = document.getElementById("outMoney"+i);
			var priceobj = document.getElementById("priceTax"+i);
			if(box!=null){
				box.id="choose"+(lastDeleteIndex);
				countobj.id="count"+(lastDeleteIndex);
				//outMoneyObj.id ="outMoney"+lastDeleteIndex;
				priceobj.id="priceTax"+lastDeleteIndex;
				lastDeleteIndex=lastDeleteIndex+1;
			}
			 
		} 
		totleMoneyMethod();
		
	};
	
	//发货地址清空
	function  clearAdddressSelect(t){
		 
		if ($("#companyType").val()==t){
		 return true;
		} 
		
		 $("#companyType").val(t) ;
		 if ($("#companyType").val()==1){
		 	$("#disCompanyType").html('公司&nbsp;');
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

//发货地址选择 
	function  openAdddressSelect(){ 
		//销售经理时为客户发货地址
		if (${USERLOGIN.roleId==4}){ 
			var customerId= $("#companyId").val();
		 
			if($("#companyId").val() ==""){ 
				alert("请选择客户"); 
				return false; 
			}
				//打开客户发货地址
				var wincus = window.open('${ctx}/getSampleOutCustomerAddressList.do?customerId='+customerId, 'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(wincus);
			
		}
		
		//采购专业时根据类型
		if (${USERLOGIN.roleId==8}){ 
			var companyType = $("#companyType").val();
			if (companyType==""){
		 		alert("请选择借出单位类型");
			} 
			if  (companyType==1){
					//打开公司发货地址 
				var wincus = window.open('${ctx}/getSampleOutCompanyAddressList.do','newwindow', 
				'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(wincus);
			} else if (companyType==2){
				//打开客户发货地址
				var customerId= $("#companyId").val();
				if($("#companyId").val() ==""){ 
					alert("请选择客户"); 
					return false; 
				} 
				var wincus = window.open('${ctx}/getSampleOutCustomerAddressList.do?customerId='+customerId, 'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(wincus);
			}else if(companyType==3){
			var  supplierId = $("#companyId").val();
				if (supplierId==""){
				  alert("请选择供应商！");
				  return false;
				}
				//打开供应商发货地址
				var wincus = window.open('${ctx}/getSampleOutSupplierAddressList.do?supplierId='+supplierId, 'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
				arrSubWin.push(wincus);
			}
		}  	 
		 
	}
			
</script>

<script language="JavaScript">
	//库房变化时删除表格中的数据----------
    function inStockroomIdChange(){
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
		if (confirm("更换库房将清空已有的借出信息，请确认")){
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
			if (confirm("更换产品分类将清空已有的借出信息，请确认")){
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
   
   
   
   
   //******
	//验证借出数量是否是正整数
	function checkcountforSub(obj){
	
	  var backcount =obj.value;
	  
	  if (backcount==""||backcount=="0"){
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
			var cell  = obj.parentNode.cellIndex;
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
	  		var tnum =  tbobj.rows[row].cells[6].innerText;
	  	
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
	    //判断所有的借出数是否正确 
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
      //$("td[name='priceTaxTd']").hide();
  	  //$("td[name='moneyTd']").hide();
  	  //$("#priceth").hide();
  	  //$("#moneyth").hide();
  	//  $("td[name='needhide']").hide();
  //	}
//});
 
   
    
</script>


</head>
<body>
<html:form action="modifySampleOut.do" styleId="modifySampleOutDto" >  
<input type="hidden" name="modifySampleOutDto.companyId" id="companyId" value="${assessDto.companyId}">
<input type="hidden" name="lastStockId" id="lastStockId" value="0">
<input type="hidden" name="lastProductId" id="lastProductId" value="0">
<input type="hidden" id="method" value="" name = "method"/>
<input type="hidden" name="modifySampleOutDto.id" value="${assessDto.id}"/>
<input type="hidden" name="modifySampleOutDto.sellMajId" value="${assessDto.sellMajId}" id="sellMajId"/>
<input type="hidden" name="modifySampleOutDto.buyManId" value="${assessDto.buyManId}" id="buyManId"/>
<input type="hidden" name="modifySampleOutDto.addressId" id="addressId" value="${assessDto.addressId}" />
<input type="hidden" id="money" name="modifySampleOutDto.money" value="${assessDto.moneyTotal}" />   
<input type="hidden" name="companyTax" id="companyTax" value="${companyEntity.taxRate}">      		                 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品借出管理 &gt;&gt; 样品借出单修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%"><span class="STYLE1">*</span> 借出单类型</td>
          
          <td class="td_02" width="30%">
          <input type="radio" name="modifySampleOutDto.type" id="radio" value="0" <c:if test='${assessDto.type == "0"}'>checked="checked"</c:if> />
            不归还&nbsp;&nbsp;&nbsp;
          <input type="radio" name="modifySampleOutDto.type"  id="radio2" value="1" <c:if test='${assessDto.type == "1"}'>checked="checked"</c:if>  />
            归还</td>
            
          <td class="td_01" width="20%" id="tdindate"><span class="STYLE1">*</span> 预计归还日期</td>
          <td class="td_02" width="30%" id="textindate"><input type="text" name="modifySampleOutDto.inDate" id="inDate" style="width:120px;" value="${assessDto.inDate}" onfocus="calendarMinToday()"/>&nbsp;</td>
        </tr>
        
        <c:if test="${USERLOGIN.roleId==8}">
        <tr>
          <td class="td_01">
	         <span class="STYLE1">*</span> 借出单位类型选择
          </td>
          <td  class="td_02" >
           <input type="hidden" name="companyType" id="companyType" value="${assessDto.companyType}" >
           
          <input type="radio" onclick="clearAdddressSelect(1)" name="modifySampleOutDto.companyType" id="radio" value="1" <c:if test='${assessDto.companyType == "1"}'>checked="checked"</c:if> />
            公司&nbsp;&nbsp;&nbsp;
          <input type="radio" onclick="clearAdddressSelect(2)" name="modifySampleOutDto.companyType" id="radio2" value="2" <c:if test='${assessDto.companyType == "2"}'>checked="checked"</c:if>  />
            客户&nbsp;&nbsp;&nbsp;
          <input type="radio" onclick="clearAdddressSelect(3)" name="modifySampleOutDto.companyType" id="radio3" value="3" <c:if test='${assessDto.companyType == "3"}'>checked="checked"</c:if>  />
            供应商      
          
          </td>
          <td class="td_01">保管人</td>         
          <td class="td_02" ><input type="text" name="modifySampleOutDto.custosName"  value="${assessDto.custosName}" maxlength="4"/> &nbsp;</td>        
          
        </tr>
        <tr>
          <td class="td_01" id="disCompanyType" height="18">
          <c:if test="${assessDto.companyType==1}">
          公司
           </c:if>
          	<c:if test="${assessDto.companyType==2}">
	         <img src="${ctx}/images/btnKHXZ.gif" width="72" height="20" onclick="openCustomSelect()"/>
	        </c:if>
	         <c:if test="${assessDto.companyType==3}">
	         <img src="${ctx}/images/btnGHSXZ.gif" width="89" height="20" onclick="openSupplierSelect()"/>
	         </c:if>
	         &nbsp;
          </td>
          <td  class="td_02" id="disPlayName" >
          <c:if test="${assessDto.companyType==1}">${companyEntity.name}&nbsp;</c:if>
          <c:if test="${assessDto.companyType==2||assessDto.companyType==3}">${assessDto.companyName}</c:if>   
          &nbsp;       
          </td>
          <td class="td_01">&nbsp;</td>
          <td class="td_02" >&nbsp;</td>
        </tr>
        </c:if>
         
        <c:if test="${USERLOGIN.roleId==4}">
        <tr>
          <td class="td_01">
	         <span class="STYLE1">*</span> <img src="${ctx}/images/btnKHXZ.gif" width="72" height="20" onclick="openCustomSelect()"/>
          </td>    
          <td  class="td_02" id="disPlayName">${assessDto.companyName}&nbsp;</td>     
          <td class="td_01">保管人</td>
          <td class="td_02" ><input type="hidden" name="modifySampleOutDto.custosName"  value="${assessDto.custosName}"/>${assessDto.custosName}&nbsp;</td>
        </tr>    
        </c:if>          
          
         
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 产品分类名称</td>
          <td class="td_02" >
          <html:select property="modifySampleOutDto.productTypeId" value="${assessDto.productTypeId}" styleId="productTypeId" style="width:126px" onchange="removeProductChange()">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productTypeEntity" name="productTypeEntitiesList" >
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
          <td class="td_04" >
          <html:select property="modifySampleOutDto.outStockroomId" value="${assessDto.outStockroomId}" styleId="outStockroomId" onchange="inStockroomIdChange()" style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
		  </html:select>          
          
          </td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap" width="30">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <c:if test="${USERLOGIN.roleId!=4 }">
          <th nowrap="nowrap" id="priceth">库存单价</th>
          </c:if>
          <th width="98" nowrap="nowrap">借出数</th>
          <c:if test="${USERLOGIN.roleId!=4 }">
          <th nowrap="nowrap" width="96" id="moneyth">&nbsp;&nbsp;&nbsp;借出金额&nbsp;&nbsp;&nbsp;</th>
          </c:if>
          <th nowrap="nowrap" width="80">&nbsp;借出可用数&nbsp;</th>
        </tr>
       <logic:iterate id="sampleOutProductInfoDto" name="list" indexId="indexId">
        <tr id="td${sampleOutProductInfoDto.id}">
          <td width="30" nowrap="nowrap"><input type="checkbox" id="choose${indexId+1}" name="productCheckbox"
		  
		  value="${sampleOutProductInfoDto.id}#${sampleOutProductInfoDto.productName}#${sampleOutProductInfoDto.type}#
		  ${sampleOutProductInfoDto.unit}#${sampleOutProductInfoDto.priceTax}#${sampleOutProductInfoDto.count}#
		  ${sampleOutProductInfoDto.outUseCount}"#${sampleOutProductInfoDto.price} />&nbsp;</td>  
		  
          <td nowrap="nowrap">${sampleOutProductInfoDto.code}&nbsp;</td>
          <td nowrap="nowrap">${sampleOutProductInfoDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.unit}&nbsp;</td>
          <c:if test="${USERLOGIN.roleId!=4 }">
          <td nowrap="nowrap" style=" text-align:right" name="priceTaxTd"> <fmt:formatNumber value="${sampleOutProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td>
          </c:if>
          <input type="hidden"   name="priceTaxs"  value="${sampleOutProductInfoDto.priceTax}" id="priceTax${indexId+1}">
          <td  width="98" nowrap="nowrap">
          <input type="text" style="width:86px;" name="counts" maxlength="20" onblur="checkcount(this)" value="${sampleOutProductInfoDto.count}" id="count${indexId+1}">&nbsp;</td>
          <c:if test="${USERLOGIN.roleId!=4 }">
          <td nowrap="nowrap" style="text-align:right" name="moneyTd"><fmt:formatNumber value="${sampleOutProductInfoDto.money}" pattern="#,##0.00"/>&nbsp;</td>
          </c:if>
          <td nowrap="nowrap" style="text-align:right" >${sampleOutProductInfoDto.outUseCount}&nbsp;</td>
          <input type="hidden" name="usercount" value="${sampleOutProductInfoDto.outUseCount}" id="usercount${indexId+1}"/>
          <input type="hidden" id="productIds" value="${sampleOutProductInfoDto.id}" name="productIds"/>
          <input type="hidden" name="timeStamps"  value="${sampleOutProductInfoDto.timeStamp}" id="timeStamps${indexId+1}">
          <input type="hidden"   name="prices"  value="${sampleOutProductInfoDto.price}" id="price${indexId+1}">
        </tr>
        </logic:iterate>
      </table>
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="40" height="25">&nbsp;<input type="checkbox" name="checkbox5" id="checkbox4" onclick="checkAll(this)"/></td>
          <td>全选&nbsp;&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnDelete.gif" align="absmiddle" onclick="removeProduct()"/>&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnAdd.gif" width="65" height="20" align="absbottom" onclick="productTypeSelect()"/> </td>
          <td>&nbsp;</td>
          <c:if test="${USERLOGIN.roleId!=4 }">
          <td style=" text-align:right;" name="needhide">借出金额合计</td>
          <td   style=" text-align:right;width:96px" id="totalmoney" name="needhide"><fmt:formatNumber value="${assessDto.moneyTotal}" pattern="#,##0.00"/>&nbsp;</td>
          <td style="width:80px" name="needhide">&nbsp;元</td>
          </c:if>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
        <a href="#" onclick="openAdddressSelect()"><img src="${ctx}/images/btnFHDZ.gif" width="99" height="20" /></a>
      
     
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02" id="receiveName">${addressDto.goodsReceiveUnitName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td colspan="3"  class="td_02" id="address">${addressDto.address}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td class="td_02" id="linkman">${addressDto.linkman}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" id="postcode">${addressDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">电话</td>
          <td class="td_02" width="30%" id="tel">${addressDto.tel}&nbsp;</td>
          <td class="td_01" width="20%">手机</td>
          <td class="td_02" width="30%" id="mobile">${addressDto.mobile}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 要求发货日期</td>
          <td class="td_02" ><input type="text" name="modifySampleOutDto.requestDate" id="name2" style="width:120px;" value="${assessDto.requestDate}" onfocus="calendarMinToday()"/></td>
          <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
          <td class="td_02" >
            <html:select property="modifySampleOutDto.transportWay"  value="${assessDto.transportWay}"   style=" width:126px" styleId="transportWay">
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
          <td class="td_02" ><input type="text" name="modifySampleOutDto.takeName"  id="takeName" style="width:120px;" maxlength="4" value="${assessDto.takeName}" /></td>
          <td class="td_01"><span class="STYLE1">*</span> 身份证号码</td>
          <td class="td_02" ><input type="text" id="takeNumber"  name="modifySampleOutDto.takeNumber" maxlength="20" style="width:120px;" value="${assessDto.takeNumber}"/></td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" ><textarea name="modifySampleOutDto.text" id="textarea" cols="100" rows="4">${assessDto.text}</textarea></td>
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
                /> 符合
                  &nbsp;&nbsp;
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
                	</table>                   
                 </td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.buyManName}</td>
                <td>日期：${assessDto.buyManDate}</td>
              </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="150" nowrap="nowrap"><input type="checkbox"  id="sellMajIdea1" value="1" 
                                 <c:if test='${assessDto.sellMajIdea == "1"}'>checked="checked"</c:if>   
                /> 符合
                  &nbsp;&nbsp;
                          <input type="checkbox" id="sellMajIdea0" value="0" 
                          <c:if test='${assessDto.sellMajIdea == "0"}'>checked="checked"</c:if>  
                  />不符合 
                  </td>
              </tr>
              <tr>
                <td colspan="2" valign="top">
                	<table width="98%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;"> ${tf:replaceBr(assessDto.sellMajText)}&nbsp;
                          </td>
                        </tr>
                	</table>                    </td>
              </tr>
              <tr>
                <td height="20px">签字：${assessDto.sellMajName}</td>
                <td>日期：${assessDto.sellMajDate}</td>
              </tr>
        <tr>
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
          <td height="20px" colspan="2">库房管理员核对意见：</td>
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
           <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
           <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
           </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
               ${tf:replaceBr(assessDto.stkAdmText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}</td>
          <td>日期：${assessDto.stkAdmDate}</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getSampleOutList.do' "/>
    </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
