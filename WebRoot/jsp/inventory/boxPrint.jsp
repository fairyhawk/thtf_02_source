<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>装箱单打印</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style> 
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
@media   print   {   
  		.buttonNoPrint {display:none;}   
  		}
.STYLE1 {color: #FF0000}
 
.ticket{margin-left:auto; margin-right:auto; padding:5px 0px 3px 55px;}
#caption{font-size:18px; font-weight:700; position:relative; left:60px;}
#nextCaption{font-weight:700;position:relative; margin-left:230px;}
#caption span{font-size:12px; position:relative; left:50px;}
.instockTitle{font-size:20px;width:210px; text-align:center; float:left;}
#page{font-size:13px;}
#page span{position:relative; left:20px;}
.space{line-height:8px; clear:both;}
.thirdCellOfKey{float:left;}
.thirdCellOfValue{float:left;border-bottom-style:dotted;border-bottom-width:thin; margin-left:5px; margin-right:8px; font-weight:normal;}
.key{ font-size:13px; font-weight:bold;}
.bottomOfValue{float:left;margin-left:5px; margin-right:8px; font-weight:normal}
table{ width:655px; font-size:12px; font-weight:bold; border-width: medium; border-collapse:collapse; }
tr{ height:15px;}
td{ height:15px;white-space:nowrap; text-align:center; margin:1px;border-width:1;border:solid #CDD5D8;border-width:thin;}
.info{ font-size:13px; direction:inherit; overflow:auto; width:680px; font-weight:600}
#ink div{font-size:14px}
</style>
<script language="JavaScript"> 
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
function printPreview(){   
	// 打印页面预览    
	wb.execwb(7,1);
		  
}    
function printSetup(){    
	// 打印页面设置    
	wb.execwb(8,1);    
}

function  printIt(){ 
	wb.execwb(6,6); 
} 

function closeWindow(){
	wb.execwb(45,1); 
}
</script>
</head>

<body>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
	<c:set var="thisPage" value="1"/>
	<c:set var="countNum" value="0"/>
	<c:if test="${boxProductList!=null}"> 
	<div class="ticket">
    	<div id="caption">
        	同方股份有限公司&nbsp;物联网应用产业本部&nbsp;物流管理部 
            <span>No.JL-WZ-002</span>
        </div>
        <div class="space">&nbsp;</div>
    	<div id="nextCaption">
        	<div class="instockTitle">装箱单</div>
            <div id="page">&nbsp;&nbsp;总页码:&nbsp;&nbsp;${productPageCount}
            	<span>当前页:&nbsp;&nbsp;${thisPage}<c:set value="${thisPage+1}" var="thisPage"/></span>
            </div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">购货单位:</div>
            <div style="width:275px;" class="thirdCellOfValue">&nbsp;${box.customerName}</div>
            <div class="thirdCellOfKey">日期:</div>
            <div style="width:90px;" class="thirdCellOfValue">&nbsp;${box.date}</div>
            <div class="thirdCellOfKey">编号:</div>
            <div style="width:110px; font-weight: bold; font-size:15px" class="thirdCellOfValue">&nbsp;${box.id}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">发站:</div>
            <div style="width:50px;" class="thirdCellOfValue">&nbsp;${box.stockroomName}</div>
            <div class="thirdCellOfKey">总件数:</div>
            <div style="width:65px;text-align:center" class="thirdCellOfValue">&nbsp;${box.count}</div>
            <div class="thirdCellOfKey">运输方式:</div>
            <div style="width:70px; text-align:center" class="thirdCellOfValue">
				<c:if test="${box.transportWay==0}">无指定</c:if>
				<c:if test="${box.transportWay==1}">自提</c:if>
				<c:if test="${box.transportWay==2}">快递</c:if>
				<c:if test="${box.transportWay==3}">汽运</c:if>
				<c:if test="${box.transportWay==4}">铁运</c:if>

				<c:if test="${box.transportWay==5}">航空</c:if>
				<c:if test="${box.transportWay==6}">海运</c:if>
				<c:if test="${box.transportWay==7}">中铁</c:if>
				 
				<c:if test="${box.transportWay==8}">市内送货</c:if>
				<c:if test="${box.transportWay==9}">市内快递</c:if>
			</div>
            <div class="thirdCellOfKey">货运公司:</div>
            <div style="width:203px; text-align:center" class="thirdCellOfValue">&nbsp;${box.logisticsName}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">收货人:</div>
        	<div class="thirdCellOfValue" style="width:80px">&nbsp;${box.linkman}${box.takeName}</div>
			<div class="thirdCellOfKey">电话:</div> 
			<div class="thirdCellOfValue" style="width:260px">&nbsp;${box.tel}&nbsp;&nbsp;${box.mobile}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">地址:</div> 
        	<div class="thirdCellOfValue" style="width:605px">&nbsp;${box.customerAddress}&nbsp;${box.takeNumber}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div>
        	<table>
            	<tr>
                	<td width="8%">行号</td>
                    <td width="22%">产品名称</td>
                    <td width="50%">规格型号</td>
                    <td width="8%">单位</td>
                    <td width="11%">数量</td>
                    <td width="10%">件数小记</td>
                </tr>
		<logic:iterate id="product" name="boxProductList" indexId="index">
                <tr>
                	<td height="18px" style='border-left:1px solid #c2c2c2;'>${index%10+1}</td> 
					<td nowrap="nowrap" >${product.productName}</td> 
					<td nowrap="nowrap" >${product.productType}</td>
					<td nowrap="nowrap" >${product.productUnit}</td>
					<td nowrap="nowrap">${product.count}</td>
                    <td>&nbsp;</td>
					<c:set value="${countNum+product.count}" var="countNum"/>
                </tr>
				<c:if test="${(index+1)%10==0&&(index+1)!=productListSize}">
				   
            </table>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
            <div class="thirdCellOfKey">&nbsp;</div>
            <div class="thirdCellOfKey" style="padding-left:25px;">装箱:</div>
            <div style="width:55px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey">&nbsp;</div>
            <div style="width:110px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey">制单:</div>
            <div style="width:80px;" class="bottomOfValue">&nbsp;${box.userName}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="info">
        	&nbsp;&nbsp;&nbsp;注：收货人认真核对货物的品种规格、数量，对此单据所填写的内容无异议后签字，签字后出现品种规格、数量问题本公司概不负责。储运部联系电话：${box.stockroomTel}
        </div>
        <div style="line-height:10px" class="space">&nbsp;</div>
        <div class="key" id="ink">
            <div class="thirdCellOfKey">货代:</div>
            <div style="width:100px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey" >客户签收:</div>
            <div style="width:150px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey" >身份证号吗:</div>
            <div style="width:120px;" class="bottomOfValue">&nbsp;</div>
        </div>
        <div class="space">&nbsp;</div>
    </div>
	<div style="PAGE-BREAK-AFTER: always"></div> 

	<div class="ticket">
    	<div id="caption">
        	同方股份有限公司&nbsp;物联网应用产业本&nbsp;部物流管理部 
            <span>No.JL-WZ-002</span>
        </div>
        <div class="space">&nbsp;</div>
    	<div id="nextCaption">
        	<div class="instockTitle">装箱单</div>
            <div id="page">&nbsp;&nbsp;总页码:&nbsp;&nbsp;${productPageCount} 
            	<span>当前页:&nbsp;&nbsp;${thisPage}<c:set value="${thisPage+1}" var="thisPage"/></span>
            </div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">购货单位:</div>
            <div style="width:275px;" class="thirdCellOfValue">&nbsp;${box.customerName}</div>
            <div class="thirdCellOfKey">日期:</div>
            <div style="width:90px;" class="thirdCellOfValue">&nbsp;${box.date}</div>
            <div class="thirdCellOfKey">编号:</div>
            <div style="width:110px; font-weight: bold; font-size:15px" class="thirdCellOfValue">&nbsp;${box.id}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">发站:</div>
            <div style="width:50px;" class="thirdCellOfValue">&nbsp;${box.stockroomName}</div>
            <div class="thirdCellOfKey">总件数:</div>
            <div style="width:65px;text-align:center" class="thirdCellOfValue">&nbsp;${box.count}</div>
            <div class="thirdCellOfKey">运输方式:</div>
            <div style="width:70px; text-align:center" class="thirdCellOfValue">
				<c:if test="${box.transportWay==0}">无指定</c:if>
				<c:if test="${box.transportWay==1}">自提</c:if>
				<c:if test="${box.transportWay==2}">快递</c:if>
				<c:if test="${box.transportWay==3}">汽运</c:if>
				<c:if test="${box.transportWay==4}">铁运</c:if>

				<c:if test="${box.transportWay==5}">航空</c:if>
				<c:if test="${box.transportWay==6}">海运</c:if>
				<c:if test="${box.transportWay==7}">中铁</c:if> 
				<c:if test="${box.transportWay==8}">市内送货</c:if>
				<c:if test="${box.transportWay==9}">市内快递</c:if>
			</div>
            <div class="thirdCellOfKey">货运公司:</div>
            <div style="width:203px; text-align:center" class="thirdCellOfValue">&nbsp;${box.logisticsName}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">收货人:</div>
        	<div style="width:80px" class="thirdCellOfValue">${box.linkman}${takeName}</div>
			<div class="thirdCellOfKey">电话:</div> 
			<div style="width:260px" class="thirdCellOfValue">${box.tel}&nbsp;&nbsp;${box.mobile}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
        	<div class="thirdCellOfKey">地址:</div> 
        	<div class="thirdCellOfValue" style="width:605px">&nbsp;${box.customerAddress}&nbsp;${box.takeNumber}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div>
        	<table>
            	<tr>
                	<td width="8%">行号</td>
                    <td width="22%">产品名称</td>
                    <td width="50%">规格型号</td>
                    <td width="8%">单位</td>
                    <td width="11%">数量</td>
                    <td width="10%">件数小记</td>
                </tr>
				</c:if>
		</logic:iterate>
                 
            </table>
        </div>
        <div class="space">&nbsp;</div>
        <div class="key">
            <div class="thirdCellOfKey" style="padding-left:25px;">装箱:</div>
            <div style="width:55px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey">&nbsp;</div>
            <div style="width:110px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey">制单:</div>
            <div style="width:80px;" class="bottomOfValue">&nbsp;${box.userName}</div>
        </div>
        <div class="space">&nbsp;</div>
        <div class="info">
        	&nbsp;&nbsp;&nbsp;注：收货人认真核对货物的品种规格、数量，对此单据所填写的内容无异议后签字，签字后出现品种规格、数量问题本公司概不负责。储运部联系电话：${box.stockroomTel}
        </div>
        <div style="line-height:10px" class="space">&nbsp;</div>
        <div class="key">
            <div class="thirdCellOfKey" >货代:</div>
            <div style="width:100px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey" >客户签收:</div>
            <div style="width:150px;" class="bottomOfValue">&nbsp;</div>
            <div class="thirdCellOfKey" >身份证号吗:</div>
            <div style="width:120px;" class="bottomOfValue">&nbsp;</div>
        </div>
        <div class="space">&nbsp;</div>
    </div>
    </c:if>
    <br/>
        <div style="margin-left:auto; margin-right:auto;text-align:center">
    	<a href=#><img src="/cms/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt();"/></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href=#><img src="/cms/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href=#><img src="/cms/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" /></a>
    	
    </div>
</body>
</html>
