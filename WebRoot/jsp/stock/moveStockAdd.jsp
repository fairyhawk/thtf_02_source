<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建移库单</title>
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
				
			$("#createMoveStockForm").validate({
				rules: { 
					"createMoveStockDto.outStockroomId":"required",
					"createMoveStockDto.productTypeId":"required",
					"createMoveStockDto.inStockroomId":"required",
					"createMoveStockDto.requestDate":"required" ,
					"createMoveStockDto.transportWay":"required"  
				},
				messages: {
					"createMoveStockDto.outStockroomId":"请选择移出库房",
					"createMoveStockDto.productTypeId":"请选择产品分类",
					"createMoveStockDto.inStockroomId":"请选择移入库房",
					"createMoveStockDto.requestDate":"请输入要求发货日期",
					"createMoveStockDto.transportWay":"请选择货运方式" 
					
				} 
			});
			 
			
	});
			
			
			 
			
	//提交保存
	function sub(subFlag){
		$("#subFlag").attr("value", subFlag); 
		 
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
		 // if($("#outStockroomId").get(0).selectedIndex ==0){ 
	 	 //  alert('请选择移出库房');
	 	 //   $("#inStockroomId").get(0).selectedIndex=0;
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
		//库房必须为同一部门
		
		if ($('#text').val().length>200){
		  alert("特殊说明长度过长");
		  return false;
		}; 			
		if ((subFlag=="save") || (subFlag="submit")){
		  $("#createMoveStockForm").submit();
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
			return false;
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
			if(box!=null){
				box.id="choose"+(lastDeleteIndex);
				countobj.id="count"+(lastDeleteIndex);
				lastDeleteIndex=lastDeleteIndex+1;
			}
			 
		} 
		
	};
	
	//验证移库数量是否是正整数
	function checkcount(obj){
	   var backcount =obj.value; 
	   if (backcount==""){ 
	     backcount=0;
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
		    if (backcount==""){
		     alert('请输入移库数！'); 
		     countobj.focus();
	         return false;
		    }
		    if  (checkcount(countobj)){ 
		    }else{
		   		countobj.focus();
		   		return false;
		   }; 
		 }
		return true;
	}
	
	
	
	
	//库房变化时删除表格中的数据----------
	function removeStockroomChange(){
	closeSubWin();
	//未选择时不提示 
		if ($("#lastStockId").val()=="0"){
			$("#lastStockId").val($("#outStockroomId").get(0).selectedIndex); 
			return false;
		}
		//没有变化时退出
		if ( $("#lastStockId").val()==$("#outStockroomId").get(0).selectedIndex){
		  return false;
		} 
		//表格中没有数据不提示
		if ($("#table tr").length<=1){
		  $("#lastStockId").val($("#outStockroomId").get(0).selectedIndex);
		  return false;
		} 
		//选择的库房变化时清空
		if (confirm("更换库房将清空已有的产品信息，请确认")){
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
			return false;
		}    	
				   		
	} 
	//库房变化时删除表格中的数据----------
	 
		
	//产品分类变化时删除表格中的数据-----------
	function removeProductChange(flag){
		closeSubWin();
		//-未选择时不提示
		 
			if ($("#lastProductId").val()=="0"){
				$("#lastProductId").val($("#productTypeId").get(0).selectedIndex); 
				return false;
			}
			
			//表格中没有数据不提示
			if ($("#table tr").length<=1){
			  $("#lastProductId").val($("#productTypeId").get(0).selectedIndex);
			  return false;
			} 
			//没有变化时退出
			if ( $("#lastProductId").val()==$("#productTypeId").get(0).selectedIndex){
			  return false;
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
				return false;
			}  
	  
				   		
	}  
	//产品分类变化时删除表格中的数据-----------	
	
				 
</script>
<script language="JavaScript">

	//进入产品选择画面
	function productTypeSelect(){ 
	
	 	 if($("#outStockroomId").get(0).selectedIndex ==0){ 
	 	   alert('请选择移出库房');
	 	   return false;
	 	 };
	 	 
	 	 if($("#productTypeId").get(0).selectedIndex ==0){ 
	 	   alert('请选择产品分类');
	 	   return false;
	 	 };
	 	 
        var outStockroomId=$("#outStockroomId").val(); 
        var productTypeId=$("#productTypeId").val(); 
        
        var productTypeName=$("#productTypeId").find("option:selected").text();
		var win2 = window.open('${ctx}/getMoveProductSelectView.do?type=1&outStockroomId='+outStockroomId+'&productTypeId='+productTypeId+'&productTypeName='+encodeURI(productTypeName,"UTF-8"),
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
	        
	        newTd5.style.textAlign="right";
	      	newTd7.style.textAlign="right";
	        
	        newTd5.innerHTML ='<input type="hidden" value="'+rmoney(arr[5])+'" id="prices" >'+ ''+arr[5]+ '&nbsp;'; 
	        newTd6.innerHTML = '<input type="text" style="width:86px;"  name="counts" maxlength="20" onblur="checkcount(this)" id="count'+countRow+'" >'+ '&nbsp;';
			newTd7.innerHTML = '<input type="hidden" value="'+arr[6]+'" id="usercount'+countRow+'" >'+''+arr[6]+ '&nbsp;';

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
    //进入发货地址页面
	function sendAddressSelect(){ 
        
       
		 var stockroomId = document.getElementById('inStockroomId').value;
		 if (stockroomId==""){
		 	alert("请先选择移入库房");
		 	$("#inStockroomId").get(0).selectedIndex=0;
	 	   return false;
		 }
	var win1=	window.open('${ctx}/getSendGoodsAddressByStockroomId.do?stockroomId='+stockroomId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');        
    arrSubWin.push(win1);
    };
    //移入库房变化时清空信息
    function inStockroomIdChange(){
    	document.getElementById("stockroomAddressAddressId").value = "" 
				document.getElementById("stockroomAddressName").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressAddress").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressLinkman").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressPostcode").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressTel").innerHTML = "&nbsp;"
				document.getElementById("stockroomAddressMobile").innerHTML = "&nbsp;";
				 closeSubWin();
    }

    
    //还原金额   
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	}
</script>
    
    
    
</head>
<body>
<html:form action="createMoveStock.do" styleId="createMoveStockForm">
<input type="hidden" name="lastStockId" id="lastStockId" value="0">
<input type="hidden" name="lastProductId" id="lastProductId" value="0">

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 移库管理 &gt;&gt; 新建移库单</td>
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
          	<html:select property ="createMoveStockDto.outStockroomId" style=" width:264px" styleId="outStockroomId" onchange="removeStockroomChange()">
	          	<html:option value="">--请选择--</html:option>
	          	<logic:iterate id="stockroomEntiy" name="stockroomEntities">
	          	  <html:option value="${stockroomEntiy.id}">${stockroomEntiy.name}</html:option> 
	          	</logic:iterate>
          	</html:select>
          	<logic:iterate id="stockroomEntiy" name="stockroomEntities">  
          	<html:hidden  property="stockroom${stockroomEntiy.id}"  styleId="stockroom${stockroomEntiy.id}" value="${stockroomEntiy.productDeptId}" />
          	</logic:iterate>
          	
          </td>
          <td class="td_01" width="20%"><span class="STYLE1">*</span> 产品分类名称</td>
          <td class="td_02" width="30%">
          	<html:select property ="createMoveStockDto.productTypeId" style=" width:126px" styleId="productTypeId" onchange="removeProductChange()">
          	<html:option value="">--请选择--</html:option>
          	<logic:iterate id="productTypeEntiy" name="productTypeEntities">
          	  <html:option value="${productTypeEntiy.id}">${productTypeEntiy.name}</html:option>
          	
          	</logic:iterate>
          	</html:select>
          	 
          </td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap" width="40px">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">库存单价</th>
          <th nowrap="nowrap" width="106px">移库数</th>
          <th nowrap="nowrap" width="106px">移库可用数</th>
        </tr> 
      </table>
      
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="40" height="25">&nbsp;<input type="checkbox" name="checkbox5" id="checkbox4" onclick="checkAll(this)"/></td>
          <td>全选&nbsp;&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnDelete.gif" align="absmiddle" onclick="removeProduct()"/>&nbsp;&nbsp;&nbsp; <img src="${ctx}/images/btnAdd.gif" width="65" height="20" align="absbottom" onclick="productTypeSelect()"/> </td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;移入库房信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01"><span class="STYLE1">*</span> 移入库房名称</td>
          <td class="td_02">
          <html:select property ="createMoveStockDto.inStockroomId" style=" width:264px" styleId="inStockroomId" onchange="inStockroomIdChange()">
	          	<html:option value="">--请选择--</html:option>
	          	<logic:iterate id="stockroomEntiy" name="stockroomEntities">
	          	  <html:option value="${stockroomEntiy.id}" >${stockroomEntiy.name}</html:option> 
	          	
	          	</logic:iterate>
          	</html:select>
          </td>
          <td class="td_01">&nbsp;</td>
          <td class="td_02">&nbsp;</td>
        </tr>
      </table>
      <div style="padding:3px 0px 2px 0px">&nbsp;&nbsp;<a href="#" onclick="sendAddressSelect()"><img src="${ctx}/images/btnFHDZ.gif" width="99" height="20" /></a></div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
      <input type="hidden" name="createMoveStockDto.stockroomAddressId" id="stockroomAddressAddressId">
        <tr>
          <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
          <td class="td_02" width="30%" id="stockroomAddressName">&nbsp;</td>
          <td class="td_01" width="20%" >联系人</td>
          <td class="td_02" width="30%" id="stockroomAddressLinkman">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td  class="td_02" id="stockroomAddressAddress">&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" id="stockroomAddressPostcode">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">手机</td>
          <td class="td_02" id="stockroomAddressMobile">&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02" id="stockroomAddressTel">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" ><span class="STYLE1">*</span> 要求发货日期</td>
          <td class="td_02" ><input type="text" name="createMoveStockDto.requestDate" onfocus="calendarMinToday()" id="requestDate" style="width:120px;" /></td>
          <td class="td_01"><span class="STYLE1">*</span> 货运方式</td>
          <td class="td_02" >
          
          
              <html:select property="createMoveStockDto.transportWay"  styleId="transportWay"   style=" width:126px">
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
          <td class="td_04"><textarea name="createMoveStockDto.text" id="text" cols="100" rows="4"></textarea></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/btnSubmit.gif" width="65" height="20"onclick="sub('submit')" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/getMoveStockList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
  <input type="hidden" id="subFlag" value="${subFlag}" name ="subFlag"/>
</table>
<br />
</html:form>
</body>
</html>
