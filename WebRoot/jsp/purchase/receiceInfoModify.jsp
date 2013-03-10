<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货信息修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css"> 
.treven {
			background-color: #f3fbff;
		}
.over {
	background-color: #ECECEC;
}
table tr{
	height:22px;
}
.STYLE1 {
	color: #FF0000
}
a{
	cursor:pointer
}
 		<c:if test="${requestScope.typeOflook}">
 			.delOfA{
				display:none
			}
 		</c:if>
</style>
</head>
<script>
function delReceiveAddress(obj,companyType,companyId,addressId){//删除
		if(confirm('确认删除该条地址信息及此信息的所有产品？')){}else{return false;}
var returnVlaue="{companyType:"+companyType+",companyId:"+companyId+",addressId:"+addressId+",buyContractId:"+$("#buyContractId").val()+"}";
	$.ajax({
			type:"post",
			async:false,
			url:"${ctx}/deleteReceiveOfBuyContract.do",
			data:"returnVlaue="+returnVlaue,
			success:function(html){
				//disable=true;
				if(html){
					$("#receiveAddressOfTable"+companyType+addressId+companyId).next().next().remove();
					$("#receiveAddressOfTable"+companyType+addressId+companyId).next().next().remove();
					$("#receiveAddressOfTable"+companyType+addressId+companyId).next().remove();
					$("#receiveAddressOfTable"+companyType+addressId+companyId).next().remove();
					$("#receiveAddressOfTable"+companyType+addressId+companyId).remove();
					$(obj).remove();
				}else{
					alert("删除失败");return;
				}
			}	
	});
}

function showInfo(id,objectOfa){
	if($(objectOfa).attr("title")=='展开'){
	$("#div"+id).attr("height",$("#"+id).height());
	$("#div"+id).height($("#"+id).height());
	$("#div"+id).animate({height:"0px"},{duration: 1200 });
	$("#div"+id).queue(function(){
	$("#div"+id).css("display","none");
	$(objectOfa).text("展开");
	$(objectOfa).attr("title","收回");
	$("#div"+id).dequeue();
});
}else{
	$("#div"+id).css("display","");
	var height=$("#"+id).height()+"px";
	$("#div"+id).animate({height:height},{duration: 1000 });
	$("#div"+id).queue(function(){
	$(objectOfa).text("收回");
	$(objectOfa).attr("title","展开");
	$("#div"+id).dequeue();
});
}
	
}
	$(document).ready(function(){
		var jsonDate=${requestScope.jsonDate};
		for(var i=0;i<jsonDate.resultSum;i++){
			var infoDate=jsonDate.rows[i];
			var tableId="receiveAddressOfTable"+infoDate.companyType+infoDate.addressId+infoDate.companyId;

			$("#info").append("<div style='padding-bottom:4px'><a href='#' class='delOfA' onclick='return delReceiveAddress(this,"+jsonDate.rows[i].companyType+","+jsonDate.rows[i].companyId+","+jsonDate.rows[i].addressId+")'><img src='${ctx}/images/btnSHDZSC.gif'/></a></div><table width='100%' border='0' cellpadding='0' cellspacing='0' class='biao3' align='center' id='"+tableId+"'></table>");
			$("#"+tableId).append("<tr><td class='td_01'>名称</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].receiveName+"&nbsp;&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>货物接收单位名称</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].goodsName+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>发货地址</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].address+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01' >联系人</td><td class='td_02'>"+jsonDate.rows[i].linkman+"&nbsp;</td><td class='td_01'>邮编</td><td class='td_02'>"+jsonDate.rows[i].postcode+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>电话</td><td class='td_02'>"+jsonDate.rows[i].tel+"&nbsp;</td><td class='td_01'>手机</td><td class='td_02'>"+jsonDate.rows[i].mobile+"&nbsp;</td></tr>");
  		$("#info").append("<br/><div style='text-align:right;width:90%'><a id='a"+tableId+"' title='收回' onclick=\"showInfo('"+tableId+"list',this)\">展开</a></div><div id='div"+tableId+"list' style='display:none;height:0px'><table width='100%' border='0' cellpadding='0' cellspacing='0' class='biao1' id='"+tableId+"list'><tr><th nowrap='nowrap'>序号</th><th nowrap='nowrap'>产品编码</th><th nowrap='nowrap'>产品名称</th><th nowrap='nowrap'>规格型号</th><th nowrap='nowrap'>单位</th><th nowrap='nowrap'>采购数</th><th nowrap='nowrap'>收货数</th></tr></table></div><br />");
  		for(var j=0;j<jsonDate.rows[i].resultSum;j++){
  			$("#"+tableId+"list").append("<tr><td style='padding-right:5px;text-align:right'>"+(j+1)+"</td><td>"+jsonDate.rows[i].rows[j].prodCode+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodName+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodType+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodUnit+"&nbsp;</td><td style='padding-right:5px;text-align:right'>"+jsonDate.rows[i].rows[j].buyCount+"&nbsp;</td><td style='padding-right:5px;text-align:right'>"+jsonDate.rows[i].rows[j].receiveCount+"&nbsp;</td></tr>");
  		}
  		if($("#"+tableId+"list")){
				$("#"+tableId+"list tr:even").addClass("treven");
				$("#"+tableId+"list tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}
		}
		$("#receiveAddressOfa").click(function(){
			window.open("${ctx}/goReceiveDetailAddSub.do?buyContractId="+$("#buyContractId").val()+"",'newprodwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=500');
		});
		$("#backOfA").click(function(){
			$("#backOfA").attr("href","${ctx}/modifyBuyContractOfShow.do?id="+$("#buyContractId").val()+"");
		})
	});
	function addReceiveInfo(html,tableIdOfAddress,listInfo){
		if($("#"+tableIdOfAddress).attr("id")==null){//客户信息
			
			$("#lastDom").before("<table id='"+tableIdOfAddress+"' width='100%' border='0' cellpadding='0' cellspacing='0' class='biao3'>"+html+"</table>");
			$("#"+tableIdOfAddress).before("<div style='padding-bottom:4px'>"+$("#spanid").html()+"<div>");
			$("#spanid").css("display","none").attr("id","disuse");
			$("#"+tableIdOfAddress).after("<br/><div style='text-align:right;width:90%'><a id='a"+tableIdOfAddress+"' title='展开' onclick=\"showInfo('"+tableIdOfAddress+"list',this)\">收回</a></div><div id='div"+tableIdOfAddress+"list' style=''><table width='100%' border='0' cellpadding='0' cellspacing='0' class='biao1' id='"+tableIdOfAddress+"list'><tr><th nowrap='nowrap'>序号</th><th nowrap='nowrap'>产品编码</th><th nowrap='nowrap'>产品名称</th><th nowrap='nowrap'>规格型号</th><th nowrap='nowrap'>单位</th><th nowrap='nowrap'>采购数</th><th nowrap='nowrap'>收货数</th></tr>"+listInfo+"</table></div><br />");
			$("#"+tableIdOfAddress+"list input:[type='text']").each(function(i){//地址信息列表
				var trid=$(this).parent().parent().attr("id");
				$("#"+trid+" td:nth-child(1)").text(i+1);
				$("#"+trid+" td:nth-child(7)").remove();
				$("#"+trid).attr("id","disuse");
				$(this).parent().text($(this).val());
			});
			
		  if($("#"+tableIdOfAddress+"list")){
				$("#"+tableIdOfAddress+"list tr:even").addClass("treven");
				$("#"+tableIdOfAddress+"list tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}
		}
	}
</script>
<body>
<c:choose>
	<c:when test="${requestScope.type=='add'}"><c:set var="title" value="采购合同添加 &gt;&gt; 收货信息修改"></c:set></c:when>
	<c:when test="${requestScope.type=='modify'}"><c:set var="title" value="采购合同修改 &gt;&gt; 收货信息修改"></c:set></c:when>
	<c:when test="${requestScope.type=='syndic'}"><c:set var="title" value="采购合同评审 &gt;&gt; 收货信息查看"></c:set></c:when>
	<c:otherwise><c:set var="title" value="采购合同查看 &gt;&gt; 收货信息查看"></c:set></c:otherwise>
</c:choose>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 采购合同管理 &gt;&gt;${title }</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;收货信息 </div> 
      <div id="info"></div>
      <span id="lastDom"></span>
	</td>
    <td >&nbsp;</td>
  </tr>
  <input type="hidden" id="singlePorkerFace" value="bingo"/>
  <input type="hidden" name="buyContractId" id="buyContractId"  value="${requestScope.buyContractId }${param.buyContractId }"/>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	<c:choose>
    		<c:when test="${requestScope.typeOflook}">
    			<a href="#" onclick="javascript:history.back()"><img src="${ctx}/images/btnBack.gif" /></a>
    		</c:when>
    		<c:otherwise>
    			<a href="#" id="receiveAddressOfa"><img src="${ctx}/images/btnSHDZTJ.gif" width="113" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" id="backOfA"><img src="${ctx}/images/btnBack.gif" /></a>
    		</c:otherwise>
    	</c:choose>
    	</td>
    <td></td>
  </tr>
</table>
<br />
</body>

</html>