<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建采购合同</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<style>
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.load {
	background-image:url(${ctx}/images/load.gif);
	background-color:#6699FF;
	background-repeat:repeat-y;
	background-position:right
}
</style>
</head>
<script type="text/javascript">
var w=null;
function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}
  function getNowFormatDate()
{
   var day = new Date();

   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间   
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
  
   CurrentDate += Year + "-";
  
   if (Month >= 10 )
   {
    CurrentDate += Month + "-";
   }
   else
   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }
   else
   {
    CurrentDate += "0" + Day ;
   }
   return CurrentDate;
} 
function CheckIfNum(String,dot){//判断是否数字
		
			    var Letters = "1234567890"+dot;
			     var i;
			     var c;
			     var n=0;
			     for( i = 0; i < String.length; i ++ )
			     {
			          c = String.charAt( i );
				  if (Letters.indexOf( c ) < 0){
				     return false;
				     }
				     if(c=='.'){n+=1;}
				     if(dot!=null && n>1){
				     	return false;
				     }
			     }
			     return true;
			}
function CheckAll()//判断全选
  {
     for (var i=0;i<document.forms[0].elements.length;i++)
     {
       document.forms[0].elements[i].checked=document.forms[0].checkAll.checked;
     }
  }
function showFile(){//文件名显示
	$("#fileName").val($("#file").val());
	$("#file").attr("class","");
	$("#fileNameError").text("");
}
function validateOfBuyContract(id){
var issuccess=true;
  	if($("#templateType1").attr("checked")){//非模板
	if($("#fileName").val()==""){
		$("#file").focus().attr("class","invalid");
		$("#fileNameError").text("请选择文件！");
		$("#"+id).attr("href","#");
		return false;}
		var uploadFileName=$("#fileName").val();
		uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf(".")+1,uploadFileName.length).toLowerCase();
		var prec=["txt","pdf","xml","rtf","doc","docx","xls","xlsx"];
		var j=0;
		for(var i=0;i<prec.length;i++){
			if(uploadFileName==prec[i]){
				j=1;
				break;
			}
		}
		if(j==0){
			$("#file").focus().attr("class","invalid");
			$("#fileNameError").text("格式只能为txt、pdf、xml、rtf、doc、docx、xls、xlsx类型！");
			$("#"+id).attr("href","#divreturnContractInfo");
			return false;
			
			}		
	}
	if($("#templateType0").attr("checked")){//模板
		if($("#quality").val()==''){
			$("#quality").focus().attr("class","invalid");
			$("#qualityError").text("请输入质量标准！");
			$("#"+id).attr("href","#");
			return false;
		}else{
			$("#quality").attr("class","");
			$("#qualityError").text("");
		}
		if($("#checkLimit").val()==''){//验收期限
			$("#checkLimit").focus().attr("class","invalid");
			$("#checkLimitError").text("请输入验收期限！");
			$("#"+id).attr("href","#");return false;
		}else{
			if(!CheckIfNum($("#checkLimit").val(),null) || $("#checkLimit").val()*1>999){
				$("#checkLimit").focus().attr("class","invalid");
				$("#checkLimitError").text("请输入数字！且小于999日");
				$("#"+id).attr("href","#");return false;
			}
			$("#checkLimit").attr("class","");
			$("#checkLimitError").text("");
		}
		if($("#protectLimit").val()==''){
			$("#protectLimit").focus().attr("class","invalid");
			$("#protectLimitError").text("请输入保质期！");
			$("#"+id).attr("href","#");return false;
		}else{
			$("#protectLimit").attr("class","");
			$("#protectLimitError").text("");
		}
	}
	if($("#supplierId").val()==''){//供货商
		alert("请选择供货商");
		$("#"+id).attr("href","#");return false;
	}
	if($("#supplierLinkmanId").val()==''){//联系人
		$("#supplierLinkmanId").focus().attr("class","invalid");
		$("#supplierLinkmanIdError").text("请选择联系人");		
		$("#"+id).attr("href","#");return false;
	}else{
		$("#supplierLinkmanId").attr("class","");
		$("#supplierLinkmanIdError").text("");	
	}
	if($("#productTypeId").val()==''){//产品分类名称
		$("#productTypeId").focus().attr("class","invalid");
		$("#productTypeIdError").html("<font style='font-size:12px' color='red'>请选择产品分类名称!</font>").css("display","");
		$("#"+id).attr("href","#divProductInfo");return false;
	}
	if($("#table1 tr").length==2){alert("请选择产品！");$("#"+id).attr("href","#divProductInfo");return false;}//产品信息
	if(!$("#deliveryTerms").attr("checked") && !$("#deliveryTerms1").attr("checked")){$("#"+id).attr("href","#divProductInfo");$("#deliveryTermsError").text("请选择发货方式");return false;}
	else{$("#deliveryTermsError").text("");}
	$("#"+id).attr("href","#divProductInfo");
	$("#table1 tr").each(function(i){//产品信息 退货合同数
		if(i!=0 && i!=$("#table1 tr").length-1){//判断是否第一个和最后一个tr		
			var id=$(this).attr("id").substring(2,$(this).attr("id").length);//获取退票合同数的id
			if($("#buyCount"+id).val()==0){$("#buyCount"+id).attr("class","invalid");$("#"+id).attr("href","#del");issuccess=false;return false;}
			if($("#buyPrice"+id).val()==0){$("#buyPrice"+id).attr("class","invalid");$("#"+id).attr("href","#del");issuccess=false;return false;}
			}
	});    
	if($("#paymentWay").val()==''){//付款方式
		$("#paymentWay").focus().attr("class","invalid");
		$("#paymentWayError").text("请选择付款方式！");
		$("#"+id).attr("href","#del");return false;
	}else{
		if($("#paymentWay").val()==5){//账期
			if(!CheckIfNum($("#arterm").val(),null)){
				$("#arterm").focus().attr("class","invalid");
				$("#spanArtermError").text("请填写数字！");
				$("#"+id).attr("href","#saveOfA");return false;
			}else{
				$("#arterm").attr("class","");
				$("#spanArtermError").text("");
			}
		}
		$("#paymentWay").attr("class","");
		$("#paymentWayError").text("");
	}
	if($("#paymentType").val()==''){//付款类型
		$("#paymentType").focus().attr("class","invalid");
		$("#paymentTypeError").text("请选择付款类型！");
		$("#"+id).attr("href","#del");return false;
	}else{
		if($("#paymentType").val()=='0'){//付款类型  全部预付
			if($("#contractPaymentTime").val()==""){
				$("#contractPaymentTime").focus().attr("class","invalid");
				$("#contractPaymentTimeError").text("请填写工作日！");
				$("#"+id).attr("href","#del");return false;
			}
			if(!CheckIfNum($("#contractPaymentTime").val(),null)){
				$("#contractPaymentTime").focus().attr("class","invalid");
				$("#contractPaymentTimeError").text("请填写数字！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#contractPaymentTime").attr("class","");
				$("#contractPaymentTimeError").text("");
			}
		}
		if($("#paymentType").val()=='1'){//付款类型  部分预付
			if($("#contractPaymentTime").val()==""){
				$("#contractPaymentTime").focus().attr("class","invalid");
				$("#contractPaymentTimeError").text("请填写工作日！");
				$("#"+id).attr("href","#del");return false;
			}else if($("#prepayMoney").val()==""){
				$("#contractPaymentTime").attr("class","");
				$("#contractPaymentTimeError").text("");
				$("#prepayMoney").focus().attr("class","invalid");
				$("#prepayMoneyError").text("请填写金额！");
				$("#"+id).attr("href","#del");return false;
			}else if($("#sendPaymentTime").val()==""){
				$("#prepayMoney").attr("class","");
				$("#prepayMoneyError").text("");
				$("#sendPaymentTime").focus().attr("class","invalid");
				$("#sendPaymentTimeError").text("请填写工作日！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#sendPaymentTime").attr("class","");
				$("#sendPaymentTimeError").text("");
				$("#contractPaymentTime").attr("class","");
				$("#prepayMoney").attr("class","");
			}
			if(!CheckIfNum($("#contractPaymentTime").val(),null)){
				$("#contractPaymentTime").focus().attr("class","invalid");
				$("#contractPaymentTimeError").text("请填写数字！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#contractPaymentTime").attr("class","");
				$("#contractPaymentTimeError").text("");
				$("#sendPaymentTime").attr("class","");
				$("#sendPaymentTimeError").text("");
				$("#prepayMoney").attr("class","");
				$("#prepayMoneyError").text("");
			}
			if(!CheckIfNum($("#prepayMoney").val(),".")){
				$("#prepayMoney").focus().attr("class","invalid");
				$("#prepayMoneyError").text("请填写数字！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#prepayMoney").attr("class","");
				$("#prepayMoneyError").text("");
			}
			if(!CheckIfNum($("#sendPaymentTime").val(),null)){
				$("#sendPaymentTime").focus().attr("class","invalid");
				$("#sendPaymentTimeError").text("请填写数字！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#sendPaymentTime").attr("class","");
				$("#sendPaymentTimeError").text("");
			}
		}
		if($("#paymentType").val()=='2'){//付款类型  货到付款
			if($("#sendPaymentTime").val()==""){
				$("#sendPaymentTime").focus().attr("class","invalid");
				$("#sendPaymentTimeError").text("请填写工作日！");
				$("#"+id).attr("href","#del");return false;
			}
			if(!CheckIfNum($("#sendPaymentTime").val(),null)){
				$("#sendPaymentTime").focus().attr("class","invalid");
				$("#sendPaymentTimeError").text("请填写数字！");
				$("#"+id).attr("href","#del");return false;
			}else{
				$("#sendPaymentTime").attr("class","");
				$("#sendPaymentTimeError").text("");
			}		
		}
		$("#paymentType").attr("class","");
		$("#paymentTypeError").text("");
	}	
	if($("#requestDate").val()==''){//要求供货日期
		$("#requestDate").focus().attr("class","invalid");
		$("#requestDateError").text("请填写供货日期！");
		$("#"+id).attr("href","#del");return false;
	}else if(new Date($("#requestDate").val().replace("-",",")).getTime()<new Date(getNowFormatDate().replace("-",",")).getTime()){
		$("#requestDate").attr("class","invalid");
			$("#requestDateError").text("供货日期必须是今日以后的时间！");
			$("#"+id).attr("href","#del");
		return false;
	}else{	
		$("#requestDate").attr("class","");
		$("#requestDateError").text("");
	}
	if(checkTextAreaLen(200) == false){
				return false;
			}
	return issuccess;
  }
function getJsonValue(){
 	 var sumId=$("#table1 tr").length-2;
 	var returnVlaue="{resultSum:"+sumId+",rows:[";
 	$("#table1 tr").each(function(i){
		if(i!=0 && i!=$("#table1 tr").length-1){//判断是否第一个和最后一个tr	
			var id=$(this).attr("id").substring(2,$(this).attr("id").length);//产品id
			var buyCount=$("#buyCount"+id).val();//采购金额
			var buyMoney=$("#buyPrice"+id).val();//采购单价
			returnVlaue+="{productId:"+id+",buyCount:'"+buyCount+"',buyMoney:'"+buyMoney+"'},"
			}
	});
	returnVlaue=returnVlaue.substring(0,returnVlaue.lastIndexOf(","));
	returnVlaue+="]}";
	$("#returnValueToServer").val(returnVlaue);
}
$(document).ready(function(){
if($("#table2")){
				$("#table2 tr:even").addClass("treven");
				$("#table2 tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}
if($("#table1")){
				$("#table1 tr:even").addClass("treven");
				$("#table1 tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}
$("#receiveGoods").click(function(){
	var isSuccess=validateOfBuyContract("receiveGoods");
	if(isSuccess){
		$("#submitType").val("receiveGoods");
		getJsonValue();
		if($("#type").val()=='modify'){
			$("#buyContractForm").attr("action","${ctx}/modifyBuyContractOfTransact.do");
		}
		$("#buyContractForm").submit();
	}

});
$("#submitOfA").click(function(){
var jsonOfSum=${requestScope.jsonOfSum};
	var isSuccess=validateOfBuyContract("submitOfA");
	var isSubmit=true;
	if(isSuccess){
		$("#submitType").val("submit");
		getJsonValue();
		if($("#table2 tr").length==2){
			alert("请添加收货地址！");$("#submitOfA").attr("href","#del");return;
		}
		else if(jsonOfSum.resultSum!=$("#table1 tr").length-2){
			alert("收货地址的收货数要与采购数相等！");
			isSubmit=false;
			return;
		} else{
			$("#table1 tr").each(function(i){//产品信息 退货合同数
			if(i!=0 && i!=$("#table1 tr").length-1){//判断是否第一个和最后一个tr
	
			var tamp=0;	
			var id=$(this).attr("id").substring(2,$(this).attr("id").length);//获取退票合同数的id
			for(var k=0;k<jsonOfSum.resultSum;k++){
				if(id==jsonOfSum.rows[k].productId){
					tamp=1;
					if($("#buyCount"+id).val()!=jsonOfSum.rows[k].sum){
						alert("收货地址的收货数要与采购数相等！");
						isSubmit=false;
						return;
					}
					}
					if(!isSubmit)return;
			}
			if(tamp==0){
				alert("收货地址的收货数要与采购数相等！");
				isSubmit=false;
				return;
			}
			}
			});
			$("#buyContractForm").attr("action","${ctx}/modifyBuyContractOfTransact.do");
		}
		if(!isSubmit)return;
		$("#buyContractForm").submit();
	}

});
$("#saveOfA").click(function(){
	var isSuccess=validateOfBuyContract("saveOfA");
	if(isSuccess){
		$("#submitType").val("save");
		getJsonValue();
		if($("#type").val()=='modify'){
			$("#buyContractForm").attr("action","${ctx}/modifyBuyContractOfTransact.do");
		}
		$("#buyContractForm").submit();
	}
});
$("#paymentType").change(function(v){
	$("#trPayInfo").css("display","");
	if($("#paymentType").val()==''){}
	if($("#paymentType").val()=='0'){
		$("#tdPayAll").css("display","");
		$("#tdPayPart").css("display","none");
		$("#tdPayPart1").css("display","none");
		$("#trPayReceive").css("display","none");
		$("#prepayMoney").val("");$("#sendPaymentTime").val("");
		
	}
	if($("#paymentType").val()=='1'){
		$("#tdPayAll").css("display","");
		$("#trPayReceive").css("display","");
		$("#tdPayPart1").css("display","");
		$("#tdPayPart").css("display","");
	}
	if($("#paymentType").val()=='2'){
		$("#trPayInfo").css("display","none");
		$("#trPayReceive").css("display","");
		$("#contractPaymentTime").val("");$("#prepayMoney").val("");
	}

});
$("#paymentWay").change(function(){
	if($("#paymentWay").val()=='5'){
		$("#spanArterm").css("display","");
		$("#spanArterm1").css("display","");
	}else{
		$("#spanArterm").css("display","none");
		$("#spanArterm1").css("display","none");	
		$("#arterm").val("");	
	}
})
	$("#templateType0").click(function(){
		$("#tr_MT1").css("display","");
		$("#tr_MT2").css("display","");
		$("#tr_MF").css("display","none");
	});
	$("#templateType1").click(function(){
		$("#tr_MT1").css("display","none");
		$("#tr_MT2").css("display","none");
		$("#tr_MF").css("display","");
		
		$("#quality").val("");$("#checkLimit").val("");$("#protectLimit").val("");
		    
	});
	$("#supplierLinkmanId").change(function(){
	
		var id=$("#supplierLinkmanId").val();
		var returnValue=eval('('+$("#linkManInfoDiv").text()+')');
		if(id==""){	
			$("#tdtel").text(" ");$("#tdfax").text(" ");
		}else{
		for(var i=0;i<returnValue.resultCount;i++){
			if(returnValue.rows[i].id==id){
				$("#tdtel").text(returnValue.rows[i].tel+" ");
				$("#tdfax").text(returnValue.rows[i].fax+" ");
				return;
			}
		}
}
	});
	$("#del").click(function(){
		if($("#table1 tr").length-2==0){alert("请选择产品信息！");return;}
		$("#table1 input:checked[id!='checkAll']").each(function(y){
			$(this).parent().parent().remove();
		});
		var indexSum=$("#table1 tr").length-1;//取和
		var makeInvoiceSum=0;
		$("#table1 tr").each(function(i){
			if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
				$("#"+$(this).attr("id")+" td:nth-child(2)").text(i);//重新排序
				makeInvoiceSum=FloatAdd($("#"+$(this).attr("id")+" td:nth-child(14)").text(),makeInvoiceSum);
				var buyCountId= "buyCount"+$(this).attr("id");//获取id
				if(i%2==0){$("#"+$(this).attr("id")).addClass("treven");}else{$("#"+$(this).attr("id")).removeClass("treven");}
				}
		});
				$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(5),5));//重新取和
				$("#money").val(makeInvoiceSum.toFixed(5));

	});
});
function getLinkMan(id){//
		$.ajax({
			type: "POST",
			url:"${ctx}/getLinkman.do?id="+id,
			success:function(val){
				$("#linkManInfoDiv").text(val);
				var returnValue=eval('('+val+')');
				$("#supplierLinkmanId").empty();
				$("#tdtel").text(" ");$("#tdfax").text(" ");
				$("#supplierLinkmanId").append("<option value=''>---请选择---</option>");
				for(var i=0;i<returnValue.resultCount;i++){
					$("#supplierLinkmanId").append("<option value='"+returnValue.rows[i].id+"'>"+returnValue.rows[i].name+"</option>");
				}
			}
		});
}
function selProdType(Obj){
var oldval=$("#productTypeIdOfHidden").val();

if(oldval!=""){
if(confirm('确认改变分类名称？')){
	if(w!=null)w.close();
	$(Obj).attr("class","");
	$("#productTypeIdError").html("").css("display","none");

	var id=Obj.selectedIndex;
	
	var s=Obj.options[id].value;
	
	var seldeptname=document.getElementsByName("prodDeptNames")[0];
	var deptname="";
	for(var i=0;i<seldeptname.length;i++){
		if(seldeptname.options[i].value==s){
			deptname=seldeptname.options[i].text;
		}
	}
	$("#prodDeptName").text(deptname);
	//document.getElementById("prodDeptName").innerText=deptname;
	$("#table1 tr").each(function(){
	if($(this).attr("id")!='lastTr' && $(this).attr("id")!='firstTr'){
		$(this).remove();
		$("#makeInvoiceSum").text("0.00");
		$("#money").val("0");
	}
	
		});

}else{
	$("#productTypeId option[value="+oldval+"]").attr("selected","selected");
}
}else{
$(Obj).attr("class","");
	$("#productTypeIdError").html("").css("display","none");

	var id=Obj.selectedIndex;
	
	var s=Obj.options[id].value;
	
	var seldeptname=document.getElementsByName("prodDeptNames")[0];
	var deptname="";
	for(var i=0;i<seldeptname.length;i++){
		if(seldeptname.options[i].value==s){
			deptname=seldeptname.options[i].text;
		}
	}
	
}
$("#prodDeptName").text(deptname);$("#productTypeIdOfHidden").val(Obj.value);
	}
	
function getProdByProdType(){	
	if($("#productTypeId").val()==""){
		  	$("#productTypeId").focus().attr("class","invalid");
  			$("#productTypeIdError").html("<font style='font-size:12px' color='red'>请选择产品分类名称!</font>").css("display","");
  			return;
	}else if($("#supplierId").val()==''){alert("请选择供货商");return;}else{$("#productTypeId").attr("class","");$("#productTypeIdError").css("display","none")}
	w=window.open("${ctx}/getProdInfoByCondition.do?prodTypeId="+$("#productTypeId").val()+"&prodTypeName="+encodeURI($("#productTypeId").find("option:selected").text()),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=500')
}
function getOpenerValOfProduct(html,id){

	var trLength=$("#table1 tr").length-1;
		var trLength=$("#table1 tr").length-1;//获得tr的个数（序号）
		if($("input[id="+id+"]").attr("id")==null){//判断是否有重复id标示
			$("#lastTr").before("<tr id=tr"+id+">"+html+"</tr>");//插入到最后一行前
			$("#tdNum").text(trLength).css("display","").attr("id","discard");//加入序号列
			$("#buyCount").css("display","").attr("id","discard");//列隐藏
			$("#buyPrice").css("display","").attr("id","discard");;//列隐藏
			$("#buyMoney").css("display","").attr("id","discard");//列隐藏
			$("#promotion").css("display","").attr("id","discard");//列隐藏
			//var makeInvoiceSum=parseFloat($("#makeInvoiceSum").text())+parseFloat($("#tdInvoiceMoney").text());//求和
			//$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2));//重新取和
			//$("#money").val(makeInvoiceSum.toFixed(2));
		}
	if($("#table1")){
			$("#table1 tr:even").addClass("treven");
			$("#table1 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}	
}
function buyCountBlur(buyCount){
	if(!CheckIfNum(buyCount.value,null)){
		alert("只能填写数字");buyCount.select();
		$("#"+buyCount.id).focus().attr("class","invalid");
		}else if(buyCount.value==''){
			alert("请填写采购数");buyCount.select();
			$("#"+buyCount.id).focus().attr("class","invalid");
		}else{
			$("#"+buyCount.id).attr("class","");
			getSumMoney(buyCount);
			}
}
function buyPriceBlur(buyPrice){
		var m =new RegExp(/^\d{0,10}[.]?\d{0,5}$/);
		if(!CheckIfNum(buyPrice.value,".")){
		alert("只能填写数字和小数点且小数点只能有一个！");buyPrice.select();
		$("#"+buyPrice.id).focus().attr("class","invalid");
		}else if(!m.test(buyPrice.value)){
			alert("采购单价格式不对,小数点后只能有5位");buyPrice.select();
			$("#"+buyPrice.id).focus().attr("class","invalid");
		}else if(buyPrice.value=='' || buyPrice.value=='.'){
			alert("请填写采购单价");buyPrice.select();
			$("#"+buyPrice.id).focus().attr("class","invalid");
		}else{$("#"+buyPrice.id).attr("class","");
			getSumMoney(buyPrice);
		}
}
function getSumMoney(obj){
		var trId=$(obj).parent().parent().attr("id");//tr的id
		var id=trId.substring(2,trId.length);
		var buyCount=$("#buyCount"+id).val();
		var buyPrice=parseFloat($("#buyPrice"+id).val());
		$("#"+trId+" td:nth-child(9)").text(formatMoney((FloatMul(buyPrice,buyCount)).toFixed(5),5));//采购金额
		$("#"+trId+" td:nth-child(14)").text((FloatMul(buyPrice,buyCount)).toFixed(5));//采购金额
		if(obj.id.indexOf("P")>0){
			if($("#"+trId+" td:nth-child(15)").text()*1==0){$("#"+trId+" td:nth-child(13)").text("0");}
			else{
			var invoiceType=$("#invoiceTypeOfHidden").val()*1;
			var tax=$("#taxRateOfHidden").val()*1;
				if(invoiceType==0){
					$("#"+trId+" td:nth-child(13)").text(((((buyPrice-$("#"+trId+" td:nth-child(15)").text()).toFixed(5))/parseFloat($("#"+trId+" td:nth-child(15)").text()))*100).toFixed(2));//增长率
				}else{
				buyPrice=buyPrice/(1+tax/100);
				var priceOfStock=$("#"+trId+" td:nth-child(15)").text()*1;///(1+tax/100);
				$("#"+trId+" td:nth-child(13)").text((((buyPrice-priceOfStock).toFixed(5)/parseFloat(priceOfStock))*100).toFixed(2));//增长率
				}
			}
			
		}
		var makeInvoiceSum=0;
		var indexSum=$("#table1 tr").length-1;//取和
		$("#table1 tr").each(function(i){
			if(i!=0 && i!=indexSum){//判断是否第一个和最后一个tr
			makeInvoiceSum=FloatAdd(parseFloat($("#"+$(this).attr("id")+" td:nth-child(14)").text()),makeInvoiceSum);
			}
		});
		$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(5),5));//重新取和
		$("#money").val(makeInvoiceSum.toFixed(5));
}


//选中文本框内所有
function range(obj){
  obj.select();
 }
</script>
<body>
<c:if test="${param.type=='add'}">
	<c:set var="title" value="新建采购合同"></c:set>
</c:if>
<c:if test="${param.type=='modify'}">
	<c:set var="title" value="采购合同修改"></c:set>
</c:if>
<c:if test="${param.roleError}">
	<script>alert("无操作权限！");</script>
</c:if>
<c:if test="${param.qualityError}">
	<script>alert("质量标准必须填写");</script>
</c:if>
<c:if test="${param.protectLimitError}">
	<script>alert("保质期必须填写");</script>
</c:if>
<c:if test="${param.checkLimitError}">
	<script>alert("验收期限必须填写且为数字");</script>
</c:if>
<c:if test="${param.fileExistError}">
	<script>alert("请选择上传文件");</script>
</c:if>
<c:if test="${param.fileMaxError}">
	<script>alert("${param.msg}");</script>
</c:if>

<c:if test="${param.fileError}">
	<script>alert("文件上传失败,格式只能为txt、pdf、xml、rtf、doc、docx、xls、xlsx类型!");</script>
</c:if>
<c:if test="${param.supplierIdError}">
	<script>alert("请供货商");</script>
</c:if>
<c:if test="${param.supplierLinkmanIdError}">
	<script>alert("请选择联系人");</script>
</c:if>
<c:if test="${param.productTypeIdError}">
	<script>alert("请选择产品分类名称");</script>
</c:if>
<c:if test="${param.productInfoError}">
	<script>alert("请选择产品信息");</script>
</c:if>
<c:if test="${param.notFindError}">
	<script>alert("无采购主管");</script>
</c:if>
<c:if test="${param.buyCountError}">
	<script>alert("采购数量不能为0，且必须为数字");</script>
</c:if>
<c:if test="${param.buyMoneyError}">
	<script>alert("采购金额不能为0，且必须为数字，只能有一个小数点");</script>
</c:if>
<c:if test="${param.paymentWayError}">
	<script>alert("请选择付款方式");</script>
</c:if>
<c:if test="${param.artermError}">
	<script>alert("账期必须为数字");</script>
</c:if>
<c:if test="${param.paymentTypeError}">
	<script>alert("请选择付款类型");</script>
</c:if>
<c:if test="${param.contractPaymentTimeError}">
	<script>alert("合同签订天数必须为数字");</script>
</c:if>
<c:if test="${param.prepayMoneyError}">
	<script>alert("预付金额必须为数字且只能有一个小数点");</script>
</c:if>
<c:if test="${param.sendPaymentTimeError}">
	<script>alert("验收合格天数必须为数字");</script>
</c:if>
<c:if test="${param.requestDateError}">
	<script>alert("请选择供货日期");</script>
</c:if>
<c:if test="${param.addError && param.type=='add'}">
	<script>alert("添加失败");</script>
</c:if>
<c:if test="${param.addError && param.type=='modify'}">
	<script>alert("修改失败");</script>
</c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<c:if test="${param.Error}">
	<script>alert("请正常操作！");</script>
</c:if>
<html:form action="addBuyContractOfTransact" method="post" styleId="buyContractForm" enctype="multipart/form-data">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="ye_header_left">&nbsp;</td>
			<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购合同 &gt;&gt; 采购合同管理 &gt;&gt;
				<c:out value="${title}"></c:out></td>
			<td class="ye_header_right">&nbsp;</td>
		</tr>
		<tr>
			<td >&nbsp;</td>
			<td valign="bottom"><br />
				<div class="div_tiao" > &nbsp;&nbsp;合同信息</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;合同类型</td>
						<td class="td_02" width="30%" ><html:select property="buyContractOfAddDto.contractType" styleId="contractType" style="width:126px">
								<html:option value="0">国内</html:option>
								<html:option value="1">国外</html:option>
							</html:select>
						</td>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;模版类型</td>
						<td class="td_02" width="30%" ><html:radio property="buyContractOfAddDto.templateType" value="0" styleId="templateType0">模版</html:radio>
							<html:radio property="buyContractOfAddDto.templateType" value="1" styleId="templateType1">非模版</html:radio>
						</td>
					</tr>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.templateType==0 || param.type=='add'}">
						<c:set var="dispaly0" value="display:none"/>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.templateType==1 }">
						<c:set var="dispaly1" value="display:none"/>
					</c:if>
					<tr id="tr_MT1" style="${dispaly1 }">
						<td class="td_01" width="20%">签订地点</td>
						<td class="td_02" width="30%"> ${buyContractOfAddForm.buyContractOfAddDto.place }
							&nbsp;
							<html:hidden property="buyContractOfAddDto.place"></html:hidden>
						</td>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;质量标准</td>
						<td class="td_02" width="30%"><html:text property="buyContractOfAddDto.quality" style="width:120px;" styleId="quality" maxlength="20"></html:text>
							<span id="qualityError" style="font-size:12px;color:#F00"></span> </td>
					</tr>
					<tr id="tr_MT2" style="${dispaly1 }">
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;验收期限</td>
						<td class="td_02" width="30%"><html:text property="buyContractOfAddDto.checkLimit" style="width:120px;" styleId="checkLimit"></html:text>
							日 <span id="checkLimitError" style="font-size:12px;color:#F00"></span> </td>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;保质期</td>
						<td class="td_02" width="30%"><html:text property="buyContractOfAddDto.protectLimit" maxlength="20" style="width:120px;" styleId="protectLimit"></html:text>
							<span id="protectLimitError" style="font-size:12px;color:#F00"></span> </td>
					</tr>
					<tr id="tr_MF" style="${dispaly0 }">
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;选择文件</td>
						<td class="td_02" colspan="3" width="80%"><html:file property="buyContractOfAddDto.file" styleId="file" onchange="showFile()"></html:file>
							<SPAN id="fileNameError" style="font-size:12px;color:#F00"></SPAN> </td>
						<input type="hidden" name="fileName" id="fileName" value="${buyContractOfAddForm.buyContractOfAddDto.fileName}"/>
					</tr>
				</table>
				<br />
				<div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
				  <a href="#" onclick="javascript:window.open('supplierList.do','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=500');"><img src="${ctx}/images/btnGHSXZ.gif" width="89" height="20" /></a>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%">&nbsp;供货商名称</td>
						<td class="td_02" width="30%" id="supplierName" >${buyContractOfAddForm.buyContractOfAddDto.supplierName}&nbsp;</td>
						<html:hidden property="buyContractOfAddDto.supplierName" styleId="supplierNameOfHidden"></html:hidden>
						<html:hidden property="buyContractOfAddDto.supplierId" styleId="supplierId"></html:hidden>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;联系人</td>
						<td class="td_02" width="30%" ><html:select property="buyContractOfAddDto.supplierLinkmanId" styleId="supplierLinkmanId">
								<html:option value="">---请选择---</html:option>
								<c:if test="${param.type=='modify'}">
									<html:options collection="linkMan" property="id" labelProperty="name"/>
								</c:if>
							</html:select>
							<SPAN id="supplierLinkmanIdError" style="font-size:12px;color:#F00"></SPAN> </td>
					</tr>
					<div id="linkManInfoDiv" style="display:none">${requestScope.jsonDate }</div>
					<tr>
						<td class="td_01" width="20%" height="18px">省份</td>
						<td class="td_02" width="30%" id="province">${buyContractOfAddForm.buyContractOfAddDto.province}&nbsp;</td>
						<td class="td_01" width="20%" >电话</td>
						<td class="td_02" width="30%" id="tdtel">${buyContractOfAddForm.buyContractOfAddDto.linkmanTel}&nbsp;</td>
					</tr>
					<tr>
						<td class="td_01" width="20%" height="18px">城市</td>
						<td class="td_02" width="30%" id="city">${buyContractOfAddForm.buyContractOfAddDto.city}&nbsp;</td>
						<td class="td_01" width="20%">传真</td>
						<td class="td_02" width="30%" id="tdfax">${buyContractOfAddForm.buyContractOfAddDto.linkmanFox}&nbsp;</td>
					</tr>
					<tr>
						<td class="td_01" width="20%" height="18px">开户行</td>
						<td class="td_02" width="30%" id="remitBankName">${buyContractOfAddForm.buyContractOfAddDto.remitBankName}&nbsp;</td>
						<td class="td_01" width="20%">帐号</td>
						<td class="td_02" width="30%" id="remitBankAccount">${buyContractOfAddForm.buyContractOfAddDto.remitBankAccount}&nbsp;</td>
					</tr>
					<tr>
						<td class="td_01" width="20%" height="18px">发票类型</td>
						<td class="td_02" width="30%" id="invoiceType"><c:if test="${buyContractOfAddForm.buyContractOfAddDto.invoiceType==0}">普通</c:if>
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.invoiceType==1}">增值税</c:if>
							&nbsp;</td>
						<td class="td_01" width="20%">增值税税率</td>
						<td class="td_02" width="30%" id="taxRate">${buyContractOfAddForm.buyContractOfAddDto.taxRate}%&nbsp;</td>
					</tr>
					<html:hidden property="buyContractOfAddDto.invoiceType" styleId="invoiceTypeOfHidden"></html:hidden>
					<html:hidden property="buyContractOfAddDto.taxRate" styleId="taxRateOfHidden"></html:hidden>
				</table>
				<br />
				<div class="div_tiao" id="divProductInfo"> &nbsp;&nbsp;产品信息 </div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%" height="18px">产品分类名称</td>
						<td class="td_02" width="30%" ><html:select property="buyContractOfAddDto.productTypeId" styleId="productTypeId" onchange="selProdType(this)" style="width:126px">
								<html:option value="">--请选择--</html:option>
								<html:options collection="productList" property="prodId" labelProperty="prodName"/>
							</html:select>
							<input type="hidden" id="productTypeIdOfHidden" value="${buyContractOfAddForm.buyContractOfAddDto.productTypeId }"/>
							<span id="productTypeIdError"></span>
							<html:select property="prodDeptNames" value="" style="display:none" >
								<html:options collection="productList" property="prodId" labelProperty="prodDeptName"/>
							</html:select>
						</td>
						<td class="td_01" width="20%">产品部门名称</td>
						<td class="td_02" width="30%" id="prodDeptName">&nbsp;${buyContractOfAddForm.buyContractOfAddDto.productDeptName}</td>
					</tr>
					<tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;发货方式</td>
						<td colspan="3" class="td_02">
							<html:radio property="buyContractOfAddDto.deliveryTerms" value="0" styleId="deliveryTerms">允许分批交货</html:radio>
							<html:radio property="buyContractOfAddDto.deliveryTerms" value="1" styleId="deliveryTerms1">不允许分批交货</html:radio>
							<SPAN id="deliveryTermsError" style="font-size:12px;color:#F00"></SPAN> </td>
					</tr>
				</table>
				<div style="width:100%; padding:5px 0px 1px 0px;">
					<div style="float:left;">&nbsp;&nbsp;<a href="#divProductInfo" onclick="getProdByProdType()"><img src="${ctx}/images/btnCPXZ.gif" width="70" height="20" /></a></div>
					<div style=" float:right; padding-top:10px;">单位：元</div>
				</div>
				<table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" style="border-left:1px solid #FFFFFF;">
					<tr id="firstTr">
						<th width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
						<th nowrap="nowrap" width="40" >序号</th>
						<th nowrap="nowrap">产品编码</th>
						<th nowrap="nowrap">产品名称</th>
						<th nowrap="nowrap">规格型号</th>
						<th nowrap="nowrap">单位</th>
						<th nowrap="nowrap">采购数</th>
						<th nowrap="nowrap">采购单价</th>
						<th width="84" nowrap="nowrap">采购金额</th>
						<th width="84" nowrap="nowrap">库存单价</th>
						<th width="84" nowrap="nowrap">实际库存总数</th>
						<th width="84" nowrap="nowrap">需求数</th>
						<th width="84" nowrap="nowrap">增长率(%)</th>
					</tr>
					<logic:present name="buyContractProducts">
						<logic:iterate id="prodInfoList" name="buyContractProducts" indexId="buyContractProductindex">
							<tr id="tr${prodInfoList.prodId}">
								<td style="border-left:1px solid #c2c2c2;"><input type="checkbox" name="id" id="${prodInfoList.prodId}"
												value="${prodInfoList.prodId}">
								</td>
								<td nowrap="nowrap" style="text-align:right;padding-right:5px">${buyContractProductindex+1}</td>
								<td nowrap="nowrap" width="66px"> ${tf:replaceHTML(prodInfoList.prodCode)} </td>
								<td nowrap="nowrap" width="240px"> ${tf:replaceHTML(prodInfoList.prodName)} </td>
								<td nowrap="nowrap" width="240px"> ${tf:replaceHTML(prodInfoList.prodType)} </td>
								<td nowrap="nowrap" width="24px"> ${tf:replaceHTML(prodInfoList.prodUnit)} </td>
								<td nowrap="nowrap"><input type="text" value="${prodInfoList.buyCount}" id="buyCount${prodInfoList.prodId}" maxlength="14" onclick="range(this)" onblur="buyCountBlur(this)" size="6" style="width:80px"/>
									&nbsp; </td>
								<td nowrap="nowrap"><input type="text" value="${prodInfoList.buyPrice}" id="buyPrice${prodInfoList.prodId}" maxlength="14" onclick="range(this)" onblur="buyPriceBlur(this)" size="6" style="width:80px"/>
									&nbsp; </td>
								<td nowrap="nowrap" style="text-align:right;padding-right:5px"><fmt:formatNumber value="${prodInfoList.buyCount*prodInfoList.buyPrice}" type="number" pattern="#,##0.00000"/>
								</td>
								<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px"><fmt:formatNumber value="${prodInfoList.stockUnitPrice}" type="number" pattern="#,##0.00"/>
								</td>
								<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px"> ${prodInfoList.stockTotalCount} </td>
								<td nowrap="nowrap" width="84px" style="text-align:right;padding-right:5px"> ${prodInfoList.demandCount} </td>
								<td nowrap="nowrap" style="text-align:right;padding-right:5px"><c:choose>
										<c:when test="${prodInfoList.stockUnitPrice*1==0}"> 0 </c:when>
										<c:when test="${buyContractOfAddForm.buyContractOfAddDto.invoiceType==0}">
											<fmt:formatNumber value="${(prodInfoList.buyPrice-prodInfoList.noTaxOfPrice)*100/prodInfoList.noTaxOfPrice}" type="number" pattern="0.00"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${(prodInfoList.buyPrice/(1+buyContractOfAddForm.buyContractOfAddDto.taxRate/100)-prodInfoList.noTaxOfPrice)*100/prodInfoList.noTaxOfPrice}" type="number" pattern="0.00"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td nowrap="nowrap" style="display:none"> ${prodInfoList.buyCount*prodInfoList.buyPrice} </td>
								<td nowrap="nowrap" style="display:none"> ${prodInfoList.noTaxOfPrice} </td>
							</tr>
						</logic:iterate>
					</logic:present>
					<tr id="lastTr">
						<td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="checkbox" name="checkAll" onclick="CheckAll()" id="checkAll" /></td>
						<td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">全选</td>
						<td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF"><a href="#divProductInfo" id="del"><img src="${ctx}/images/btnDelete.gif" width="65" height="20" /></a></td>
						<td colspan="5" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" > 采购金额合计 </td>
						<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="makeInvoiceSum"><c:choose>
								<c:when test="${buyContractOfAddForm.buyContractOfAddDto.money!=null}">
									<fmt:formatNumber value="${buyContractOfAddForm.buyContractOfAddDto.money}" type="number" pattern="#,##0.00000"/>
								</c:when>
								<c:otherwise>0.00</c:otherwise>
							</c:choose>
						</td>
						<td colspan="4" nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
					</tr>
				</table>
				<html:hidden property="buyContractOfAddDto.money" styleId="money"/>
				<div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;付款方式</td>
						<td class="td_02" width="30%" ><html:select property="buyContractOfAddDto.paymentWay" styleId="paymentWay" style=" width:126px">
								<html:option value="">----请选择----</html:option>
								<html:option value="2">支票</html:option>
								<html:option value="3">网银</html:option>
								<html:option value="4">电汇</html:option>
								<html:option value="5">银行承兑</html:option>
								<html:option value="6">信用证</html:option>
								<html:option value="7">其它</html:option>
							</html:select>
							<SPAN id="paymentWayError" style="font-size:12px;color:#F00"></SPAN> </td>
						<c:if test="${buyContractOfAddForm.buyContractOfAddDto.paymentWay!=5 || param.type=='add'}">
							<c:set var="dispalyOfArterm" value="display:none"/>
						</c:if>
						<td class="td_01" width="20%">&nbsp;<span id="spanArterm" style="${dispalyOfArterm }">账期</span></td>
						<td class="td_02" width="30%" ><span id="spanArterm1" style="${dispalyOfArterm }">
							<html:text property="buyContractOfAddDto.arterm" styleId="arterm" style="width:120px;" maxlength="3"></html:text>
							天 </span> <span id="spanArtermError" style="font-size:12px;color:#F00"></span>&nbsp; </td>
					</tr>
					<c:choose>
						<c:when test="${param.type=='add'}">
							<c:set var="dispalyOfPaymentType" value="display:none"/>
						</c:when>
						<c:otherwise>
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.paymentType==0 }">
								<c:set var="trPayInfo" value=""/>
								<c:set var="tdPayPart" value="display:none"/>
								<c:set var="trPayReceive" value="display:none"/>
							</c:if>
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.paymentType==1}">
								<c:set var="trPayInfo" value=""/>
								<c:set var="tdPayPart" value=""/>
								<c:set var="trPayReceive" value=""/>
							</c:if>
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.paymentType==2}">
								<c:set var="trPayInfo" value="display:none"/>
								<c:set var="tdPayPart" value="display:none"/>
								<c:set var="trPayReceive" value=""/>
							</c:if>
						</c:otherwise>
					</c:choose>
					<tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;付款类型</td>
						<td colspan="3" class="td_02"><input type="hidden" name="submitType" id="submitType"/>
							<html:select property="buyContractOfAddDto.paymentType" style=" width:126px" styleId="paymentType">
								<html:option value="">----请选择----</html:option>
								<html:option value="0">全部预付</html:option>
								<html:option value="1">部分预付</html:option>
								<html:option value="2">货到付款</html:option>
							</html:select>
							<SPAN id="paymentTypeError" style="font-size:12px;color:#F00"></SPAN> </td>
					</tr>
					<tr class="trHIDDEN" style="${dispalyOfPaymentType }${trPayInfo }" id="trPayInfo">
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;合同签订后</td>
						<td class="td_02" width="30%" colspan="" id="tdPayAll"><html:text property="buyContractOfAddDto.contractPaymentTime" style="width:120px;" styleId="contractPaymentTime" maxlength="3"></html:text>
							个工作日内 <span id="contractPaymentTimeError" style="font-size:12px;color:#F00"></span> </td>
						<td class="td_01" width="20%" >&nbsp;<span id="tdPayPart" style="${dispalyOfPaymentType }${tdPayPart }"><span style="color:#FF0000">*</span>&nbsp;预付金额</span></td>
						<td class="td_02" width="30%">&nbsp;<span id="tdPayPart1" style="${dispalyOfPaymentType }${tdPayPart }">
							<html:text property="buyContractOfAddDto.prepayMoney" style="width:120px;" styleId="prepayMoney" maxlength="11"></html:text>
							元&nbsp; </span> <span id="prepayMoneyError" style="font-size:12px;color:#F00"></span> </td>
					</tr>
					<tr class="trHIDDEN" id="trPayReceive" style="${dispalyOfPaymentType }${trPayReceive }">
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;验收合格后</td>
						<td colspan="3" class="td_02" ><html:text property="buyContractOfAddDto.sendPaymentTime" style="width:120px;" styleId="sendPaymentTime" maxlength="3"></html:text>
							个工作日内 <span id="sendPaymentTimeError" style="font-size:12px;color:#F00"></span> </td>
					</tr>
				</table>
				<br/>
				<div class="div_tiao" id="divSendGoods"> &nbsp;&nbsp;发货信息 </div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_01" width="20%" height="18px"><span style="color:#FF0000">*</span>&nbsp;要求供货日期</td>
						<td class="td_02" width="30%" ><html:text property="buyContractOfAddDto.requestDate" styleId="requestDate" onfocus="calendarMinTomorrow()" readonly="readonly" style="width:120px;"></html:text>
							<span id="requestDateError" style="font-size:12px;color:#F00"></span> </td>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;货运方式</td>
						<td class="td_02" width="30%"><html:select property="buyContractOfAddDto.transportWay" style="width:120px;">
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
				</table>
				<input type="hidden" value="" id="returnValueToServer" name="returnValueToServer" />
				<br />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table2" style="border-left:1px solid #FFFFFF;">
					<tr>
						<th width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;">名称</th>
						<th nowrap="nowrap">货物接受单位</th>
						<th nowrap="nowrap">收货地址</th>
						<th nowrap="nowrap">邮编</th>
						<th nowrap="nowrap">联系人</th>
						<th nowrap="nowrap">电话</th>
						<th nowrap="nowrap">手机</th>
					</tr>
					<logic:present name="buyContractReceivings">
						<logic:iterate id="buyContractReceiving" name="buyContractReceivings" indexId="buyContractReceivingindex">
							<tr>
								<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">&nbsp;${tf:replaceHTML(buyContractReceiving.receiveName)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.goodsName)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.address)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.postcode)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.linkman)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.tel)}</td>
								<td nowrap="nowrap" >&nbsp;${tf:replaceHTML(buyContractReceiving.mobile)}</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<tr>
						<td colspan="7" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF"><a href="#divSendGoods" id="receiveGoods"><img src="${ctx}/images/btnSHDZ.gif"  height="20"/></a></td>
					</tr>
				</table>
				<br/>
				<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr>
						<td class="td_03" width="20%">特殊说明</td>
						<td class="td_04" ><html:textarea property="buyContractOfAddDto.text" cols="100" rows="4" styleId="textarea"></html:textarea>
						</td>
					</tr>
				</table>
				<br />
				<c:if test="${param.type!='add' }">
				<div class="div_tiao">&nbsp;&nbsp;评审意见</div>
				<table border="0" cellpadding="0" cellspacing="0" align="center" width="460">
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.templateType==1}">
						<tr class="legalIdeaDIV">
							<td height="20px" colspan="2"> 法务专员评审意见： </td>
						</tr>
						<tr class="legalIdeaDIV">
							<td> 法律法规是否符合 </td>
							<td height="20px"><input type="checkbox" name="salesContract.legalIdea"
					id="checkbox4" disabled="disabled"
					
								<c:if test="${buyContractOfAddForm.buyContractOfAddDto.legalIdea== 1}">checked="checked"</c:if>
								/>
								符合 &nbsp;&nbsp; <input type="checkbox" name="salesContract.legalIdea"
					id="checkbox4" disabled="disabled"
					
								<c:if test="${buyContractOfAddForm.buyContractOfAddDto.legalIdea == 0}">checked="checked"</c:if>
								/>
								不符合 </td>
						</tr>
						<tr class="legalIdeaDIV">
							<td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap> 补充说明： </td>
										<td style="padding:5px 0 5px 0;" width="330px"> ${tf:replaceBr(buyContractOfAddForm.buyContractOfAddDto.legalText)} </td>
									</tr>
								</table></td>
						</tr>
						<tr class="legalIdeaDIV">
							<td height="20px"> 签字：${buyContractOfAddForm.buyContractOfAddDto.legalName} </td>
							<td> 日期：${buyContractOfAddForm.buyContractOfAddDto.legalDate} </td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;</td>
						</tr>
					</c:if>
					<c:set var="disabled" value="disabled" scope="page"></c:set>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,0,1)==0}">
						<c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,0,1)==1}">
						<c:set value="checked" var="productSpecPass" scope="page"></c:set>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,1,2)==0}">
						<c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,1,2)==1}">
						<c:set value="checked" var="productQualityPass" scope="page"></c:set>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,2,3)==0}">
						<c:set value="checked" var="productSumUnpass" scope="page"></c:set>
					</c:if>
					<c:if test="${buyContractOfAddForm.buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddForm.buyContractOfAddDto.buyManIdea,2,3)==1}">
						<c:set value="checked" var="productSumPass" scope="page"></c:set>
					</c:if>
					<tr class="proMajIdeaDIV">
						<td height="20px" colspan="2"> 采购主管评审意见： </td>
					</tr>
					<tr class="proMajIdeaDIV">
						<td width="320" nowrap="nowrap"> 1.产品要求是否符合（数量、规格、技术、质量） </td>
						<td height="20px" width="140" nowrap="nowrap"><input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>
							符合&nbsp;&nbsp;
							<input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>
							不符合 </td>
					</tr>
					<tr class="proMajIdeaDIV">
						<td> 2.付款方式是否符合 </td>
						<td height="20px"><input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>
							符合&nbsp;&nbsp;
							<input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>
							不符合 </td>
					</tr>
					<tr class="proMajIdeaDIV">
						<td> 3.售前服务是否符合 </td>
						<td height="20px"><input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>
							符合&nbsp;&nbsp;
							<input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>
							不符合 </td>
					</tr>
					<tr class="proMajIdeaDIV">
						<td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap> 补充说明： </td>
									<td style="padding:5px 0 5px 0;" width="330px"> ${tf:replaceBr(buyContractOfAddForm.buyContractOfAddDto.buyManText)} </td>
								</tr>
							</table></td>
					</tr>
					<tr class="proMajIdeaDIV">
						<td height="20px"> 签字：${buyContractOfAddForm.buyContractOfAddDto.buyManName} </td>
						<td> 日期：${buyContractOfAddForm.buyContractOfAddDto.buyManDate} </td>
					</tr>
					<tr>
						<td height="20px" colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td height="20px" colspan="2"> 运营总监评审意见： </td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td height="20px"><input type="checkbox" name="salesContract.opeMajIdea"
					id="checkbox4" disabled="disabled"
					
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.opeMajIdea == 1}">checked="checked"</c:if>
							/>
							同意 &nbsp;&nbsp; <input type="checkbox" name="salesContract.opeMajIdea"
					id="checkbox4" disabled="disabled"
					
							<c:if test="${buyContractOfAddForm.buyContractOfAddDto.opeMajIdea == 0}">checked="checked"</c:if>
							/>
							不同意 </td>
					</tr>
					<tr>
						<td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap> 补充说明： </td>
									<td style="padding:5px 0 5px 0;" width="330px"> ${tf:replaceBr(buyContractOfAddForm.buyContractOfAddDto.opeMajText)} </td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="20px"> 签字：${buyContractOfAddForm.buyContractOfAddDto.opeMajName} </td>
						<td> 日期：${buyContractOfAddForm.buyContractOfAddDto.opeMajDate} </td>
					</tr>
				</table></td>
			<td >&nbsp;</td>
		</tr>
		</c:if>
		<tr>
			<td></td>
			<td height="50px" align="center" valign="bottom"><a href="#saveOfA" id="saveOfA"> <img src="${ctx}/images/btnSave.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#submitOfA" id="submitOfA"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx }/buyContractList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a></td>
			<td></td>
		</tr>
	</table>
	<br />
	<input type="hidden" name="type" id="type" value="${param.type }"/>
	<input type="hidden" name="oldFile" value="${buyContractOfAddForm.buyContractOfAddDto.fileName}"/>
	<html:hidden property="buyContractOfAddDto.id"></html:hidden>
</html:form>
</body>
</html>
