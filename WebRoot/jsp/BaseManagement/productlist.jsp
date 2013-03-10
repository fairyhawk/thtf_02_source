<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品列表</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
		.rowselected {
		  	background-color: #868686;
		}
-->
</style>
<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:odd").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
						$(this).click( function(){
				            if( $(this).hasClass("rowselected") ){
				                $(this).removeClass("rowselected");
				            }else{
				                $(this).addClass("rowselected");
				            }
			            });
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
		
		
		var XMLHttpReq=false;
	var currentSort;
	//创建XMLHttpRequest对象 
	function createXMLHttpRequest() {
	  if(window.XMLHttpRequest) { //Mozilla 浏览器
	      XMLHttpReq = new XMLHttpRequest();
	  }else if (window.ActiveXObject) { // IE浏览器
	     try {
	        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	     } catch (e) {
	     try {
	        XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	     } catch (e)
	     {
	      window.alert("创建XMLHttpRequest对象出错"+e);
	     }
	                  }
	         }
	         if(!(XMLHttpReq)){
	           window.alert("创建XMLHttpRequest对象异常！");
	         }
	}
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
	 document.getElementById("sname").length=1;
	 XMLHttpReq.onreadystatechange=cityList;
	 var url = "${ctx}/product.do?method=querySerieByTypeId&typeId="+objVal;
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//获取返回状态
	function cityList() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	parseXML(XMLHttpReq.responseXML);
	}
	}
	}
//解析XML信息，添加系列
	function parseXML(xmlDoc){
	var seireName = xmlDoc.getElementsByTagName("serieName");
	var citySel = document.getElementById("sname");
	for(var i=0;i<seireName.length;i++){
	var opt = document.createElement("OPTION");
	opt.text = xmlDoc.getElementsByTagName("serieName")[i].firstChild.data;
	opt.value=xmlDoc.getElementsByTagName("serieIdNo")[i].firstChild.data;
	citySel.add(opt);
	}
	}
		
		var myArray=new Array();
		var mark1;
		var mark;
		function removeBefore(a) {
			for(var i=0;i<myArray.length;i++){
				if(myArray[i]==a.value){
					mark=0;
					mark1=i;
				}
			}
			if(mark!=0){
				myArray.push(a.value);
			}
			if(mark==0){
				myArray.splice(mark1,1);
			}
			var mark1=0;
			var mark=0;
		};
		function shan(){
		if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
					document.forms[0].action='${ctx}/product.do?method=productDelete&id='+ myArray.join(',');
					document.forms[0].submit();
			}
		};
		
		var formId = 0;
		//控件名
		var checknNames = [ "product.tname", "product.sname", "product.productcode","product.name","product.type"];
		//提示语
		var descriptions = ["产品分类","产品系列","产品编码","产品名称", "规格型号"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "null", "null", "notspace", "notspace" ,"notspace"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = ["", "", "", "",""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = ["", "", "", "80","80"];
		function form0submit() {
			 if(checkForm()==false){
			 return ;
			  }
			var form = document.getElementsByTagName("Form")[0];
			if($("#productcode").val()!='' && $.trim($("#productcode").val()).length < 11){alert("产品编码格式不正确");return;}
			form.submit();
		};
		</script>
</head>
<body>
<html:form action="/product" method="post">
  <input type="hidden" name="method" value="productAll" />
  <input type="hidden" id="oldPageByProduct" value="cutPage" />
  <logic:present name="tlist">
        <logic:iterate id="tlist1" name="tlist">
        	<logic:equal value="${pinfo.tno}" name="tlist1" property="no">
				<input type="hidden" name="mappingTname" id="mappingTname" value="${tlist1.idNo}"/>
           </logic:equal>
  		</logic:iterate>
  </logic:present>
 <logic:present name="slist">
       <logic:iterate id="slist1" name="slist">
           <logic:equal value="${pinfo.sno}" name="slist1" property="no">
           		<input type="hidden" name="mappingSname" id="mappingSname" value="${slist1.ssIdNo}"/>
           </logic:equal>
      </logic:iterate>
  </logic:present> 
  
  <input type="hidden" name="mappingProductcode" id="mappingProductcode" value="${pinfo.productcode}"/>
  <input type="hidden" name="mappingName" id="mappingName" value="${pinfo.name}"/>
  <input type="hidden" name="mappingType" id="mappingType" value="${pinfo.type}"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 基础信息管理 &gt;&gt; 产品信息管理 </td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center"><br />
        <div class="mo_wp">
          <div style="display: ; " class="mo_con" align="left">
            <table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="biao3">
              <tr>
                <td class="td_01" width="20%"> 产品分类名称 </td>
                <td class="td_02" width="30%"><label>
                  <select name="product.tname" id="tname"
													onchange="proChange(this.value);" style=" width:126px">
                    <option value="0,00" selected="selected"> --请选择-- </option>
                    <logic:present name="tlist">
                      <logic:iterate id="tlist" name="tlist">
                        <logic:equal value="${pinfo.tno}" name="tlist"
																property="no">
                          <option value="${tlist.idNo}" selected="selected"> ${tlist.name} </option>
                        </logic:equal>
                        <logic:notEqual value="${pinfo.tno}" name="tlist"
																property="no">
                          <option value="${tlist.idNo}"> ${tlist.name} </option>
                        </logic:notEqual>
                      </logic:iterate>
                    </logic:present>
                  </select>
                  </label>
                </td>
                <td class="td_01" width="20%"> 产品系列名称 </td>
                <td class="td_02" width="30%"><label>
                  <select name="product.sname" id="sname" style=" width:132px">
                    <option value="0,000" selected="selected"> --请选择-- </option>
                    <logic:present name="slist">
                      <logic:iterate id="slist" name="slist">
                        <logic:equal value="${pinfo.sno}" name="slist"
																property="no">
                          <option value="${slist.ssIdNo}" selected="selected"> ${slist.name} </option>
                        </logic:equal>
                        <logic:notEqual value="${pinfo.sno}" name="slist"
																property="no">
                          <option value="${slist.ssIdNo}"> ${slist.name} </option>
                        </logic:notEqual>
                      </logic:iterate>
                    </logic:present>
                  </select>
                  </label>
                </td>
              </tr>
              <tr>
                <td class="td_01"> 产品编码 </td>
                <td class="td_02"><input type="text" id="productcode" name="product.productcode"
												value="${pinfo.productcode}" style="width:120px;" />
                </td>
                <td class="td_01"> 产品名称 </td>
                <td class="td_02"><input type="text" id="name" name="product.name" value="${pinfo.name}" style="width:240px;" maxlength="40"/>
                </td>
              </tr>
              <tr>
                <td class="td_01"> 规格型号 </td>
                <td class="td_02"><input type="text" id="type" name="product.type" value="${pinfo.type}"
												style="width:240px;" maxlength="40"/>
                </td>
                <td class="td_01">&nbsp;</td>
                <td class="td_02">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="4" align="center" height="30px"><a href="javascript:form0submit();"> <img
													src="${ctx}/images/btn_JianSuo.gif" /> </a> </td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
            <div style="text-align:center"> <img id="imgShang" src="${ctx}/images/shang_sj.png" /> </div>
          </div>
        </div></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
    
    <td>&nbsp;</td>
    <td align="center"><br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        <tr>
          <th nowrap="nowrap" width="41px"> 选择 </th>
          <th nowrap="nowrap"> 产品编码 </th>
          <th nowrap="nowrap"> 产品名称 </th>
          <th nowrap="nowrap"> 规格型号 </th>
          <th nowrap="nowrap"> 单位 </th>
          <th nowrap="nowrap"> 产品系列名称 </th>
          <th nowrap="nowrap"> 产品分类名称 </th>
          <th nowrap="nowrap"> 产品部门名称 </th>
          <th nowrap="nowrap"> 限价</th>
          <th nowrap="nowrap" width="40px"> 操作 </th>
        </tr>
        <tr>
          <logic:present name="productlist">
          <logic:iterate id="productAll" name="productlist"
										property="records">
        <tr>
          <td>&nbsp;<input type="checkbox" name="ss" value="${productAll.id}"
													onclick="javascript:removeBefore(this);">
          </td>
          <td width="96px"> ${productAll.tno}.${productAll.sno}.${productAll.no} </td>
          <td> ${productAll.name} </td>
          <td width="240px"> ${productAll.type}&nbsp; </td>
          <td width="48px"> ${productAll.unit}&nbsp; </td>
          <td width="96px"> ${productAll.sname}&nbsp; </td>
          <td width="96px"> ${productAll.tname}&nbsp; </td>
          <td width="96px"> ${productAll.dname}&nbsp; </td>
          <td width="96px"  style="text-align:right"> <fmt:formatNumber value="${productAll.limitPrice}" pattern="#,##0.00"/>&nbsp;</td>
          <td><a href="javascript:window.location = '${ctx}/product.do?method=showProductUpdate&id=${productAll.id}';">修改</a> </td>
        </tr>
        </logic:iterate>
        
        </logic:present>
        
        </tr>
        
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="30px" align="left">&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);"></td>
          <td width="30px"> 全选 </td>
          <td width="100px"><a href="javascript:shan();"> <img
											src="${ctx}/images/btnDelete.gif" /> </a> </td>
          <td width="100px"><a
										href="javascript:window.location = '${ctx}/product.do?method=showProductAdd';"><img
											src="${ctx}/images/btnAdd.gif" /> </a> </td>
          <td align="right">
          	<%@ include file="/jsp/common/page.jsp"%>
          </td>
        </tr>
      </table>
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
</html:form>
</body>
</html>
