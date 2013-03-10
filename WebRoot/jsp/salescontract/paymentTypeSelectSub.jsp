<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<title>付款方式选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		-->
		</style>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript">
       	$(document).ready(function(){
				if($("#table")){
					$("#table tr:odd").addClass("treven");
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
       	//根据所选的支付方式判断分批的 显示情况
        $(document).ready(function(){
        		//全部现结
        		$("#allCashPayMentWay").bind("click",function(){
        			$("#batchWayTR").hide();
        			$("#cashMoneyTR").hide();
					$("#batchMaxMoneyDIV").hide();
					clearErr();
        		});
        		//全部信用
        		$("#allCreditPayMentWay").bind("click",function(){
        			$("#batchWayTR").show();
        			$("#cashMoneyTR").hide();
        			$("#batchMaxMoneyDIV").hide();
					clearErr();
        		});
        		//部分现结部分信用
        		$("#partCashAndPartCreditPayMentWay").bind("click",function(){
        			$("#batchWayTR").show();
        			$("#cashMoneyTR").show();
        			$("#batchMaxMoneyDIV").hide();
					clearErr();
        		});
        		//分批
        		$("#batchWay").bind("click",function(){
        			$("#batchMaxMoneyDIV").show();
                    //clearErr();
        		});
        		//不分批
        		$("#notBatchWay").bind("click",function(){
        			$("#batchMaxMoneyDIV").hide();
				    $("#batchMaxMoney").removeClass("invalid");
				    $("#batchMaxMoney").attr("value","");
        		});
        	});

			//清除控件错误式样并清空原有值
			function clearErr(){
				$("#batchMaxMoney").removeClass("invalid");
				$("#batchMaxMoney").attr("value","");
				$("#batchWay").attr("checked","");
				$("#notBatchWay").attr("checked","");
				$("#cashMoney").removeClass("invalid");
				$("#cashMoney").attr("value","");
			}

			var arrCredit = new Array();
			function returnObj(obj){
				var array = obj.value;
				arrCredit = array.split("#");
				//$("#climitmoney").attr("value",arrCredit[6]); //信用可用额度 Del By ChenHJ @2010-07-29
				$("#climitmoney").attr("value",arrCredit[5]); //信用额度 Add By ChenHJ @2010-07-29
			}

			//判断信用类型是否被选中	
			function isSltdCredit(obj){
				var isCheck = 0;
				// 循环获取被选中记录的值
				for(var k=0;k<obj.length;k++){
					if(obj[k].checked==true){
						isCheck = 1;
						break;
					}
				}
				if(isCheck == 0){
					alert("请选择信用类型");
					return false;
				}
			}

			//判断付款方式是否被选中 返回值:-1未选,0全部现结,1全部信用,2部分现结部分信用
			function isSltdPayType(obj){
				var isPayWay = -1;
				for(var i=0;i<obj.length;i++){
					if(obj[i].checked==true){
						isPayWay = obj[i].value;
						break;
					}
				}

				if(isPayWay==-1){
					alert("请选择付款方式");
				}
				return isPayWay;
			}

            //判断付款方式是否为分批 返回值:-1未选,0不分批,1分批
			function isSltdBatch(obj){
				var batchWay=-1;
				for(var i=0;i<obj.length;i++){
					if(obj[i].checked==true){
						batchWay = obj[i].value;
						break;
					}
				}
				if(batchWay == -1){
					alert("请选择分批方式");
				}
				return batchWay;
			}

			//将货币格式还原成数值型
			function revertNumber(obj){
				var tmp = obj.split(",");
				var num = tmp.join("");
				return parseFloat(num);
			}
			// 将小页面的值传回大页面
			function selectPaymentWay(){
			var parentCustName = $("#customerId option:selected",window.opener.document).html();

            var subCustName = "${customerName}";
            if(parentCustName!=subCustName){
			    alert("客户名称已更改，请重新打开付款方式页面");
                window.close();
				return;
			}
			//1.分批最大金额不能大于（销售总额-现结金额）
			var rdoPayType = document.getElementsByName('paymentTypeRadio');//付款方式：全部现结=0,全部信用=1,部分现结部分信用=2
			var rdoBatch   = document.getElementsByName('batchWayRadio');//不分批=0，分批=1
			var ckdCredit  = document.getElementsByName('chooseradio');//选择表格中的记录

			// 获取相关的金额值用于计算
			var climit = $("#climitmoney").val();//信用额度
			var climitNomal = revertNumber(climit);
			
			var totalmoney = revertNumber($("#totalMoney").val());//合同金额
			var nowmoney = revertNumber($("#cashMoney").val());//现结金额
			var batchMaxMoney = revertNumber($("#batchMaxMoney").val());//分批最大金额

            if(isSltdCredit(ckdCredit) == false){ //判断信用类型是否被选中
			    return;
			}

            //付款方式
			var payWayType = isSltdPayType(rdoPayType);

			// 判断是否选择付款方式标记
			if(payWayType == -1){
				return;
			}

            var batchType;//分批方式
            if(payWayType != 0){//付款方式非全部现结
			    batchType = isSltdBatch(rdoBatch);
				if(batchType==-1){
				    return;
				}
			}

            var intPayType = "0";        //付款方式ID
			var strPayTypeName = "";     //付款方式Name
            var intBatchType = "0";      //分批方式ID
			var strBatchTypeName = "";   //分批方式Name
			var dblNowMny = "";          //现结金额、合同金额
			var dblBatchMny = "";        //分批金额
			$("#td_fenpi",window.opener.document).show();
			switch(payWayType){
				case '0': //全部现结
				    strPayTypeName = "全部现结";
                    dblNowMny = totalmoney;
					$("#td_fenpi",window.opener.document).hide();
					break;
				case '1': //全部信用
				    strPayTypeName = "全部信用";
					intPayType = "1";
					dblNowMny = "";
					$("#cashMoney").removeClass("invalid");
					$("#batchMaxMoney").removeClass("invalid");
					if(parseFloat(batchMaxMoney)>parseFloat(climitNomal)){
						alert("信用额度不足,请重新选择信用类型");
						return;
					}

					if(batchType==0){//不分批
                        strBatchTypeName = "不分批";
						if(parseFloat(climitNomal)<parseFloat(totalmoney)){
							alert("信用额度不足");
							return 
						}
					}

					if(batchType==1){//分批
						if(parseFloat(totalmoney)>0&&isNullOrZero(1,batchMaxMoney)==false){
							return;
						}
						if( (parseFloat(batchMaxMoney)>=parseFloat(totalmoney)&&parseFloat(totalmoney)>0)||(parseFloat(batchMaxMoney)>0&&parseFloat(totalmoney)==0)){
							alert("分批最大金额必须小于合同金额");
							$("#batchMaxMoney").addClass("invalid");
							return;					
						}
                        strBatchTypeName = "分批";
						intBatchType = "1";
                        dblBatchMny = batchMaxMoney;
					}
					break;
				case '2': //部分现结部分信用
				    strPayTypeName = "部分现结部分信用";
					intPayType = "2";
					$("#cashMoney").removeClass("invalid");
					$("#batchMaxMoney").removeClass("invalid");
					if( parseFloat(totalmoney)>0&&isNullOrZero(0,nowmoney)==false){
					    return;
					}
					if((parseFloat(nowmoney)>=parseFloat(totalmoney) && parseFloat(nowmoney)!=0 && parseFloat(totalmoney)!=0)||(parseFloat(nowmoney)!=0 && parseFloat(totalmoney)==0)){
						alert("现结金额必须小于合同金额");
						$("#cashMoney").addClass("invalid");
						return;
					}

                    if(batchType!=1){
						if(parseFloat(totalmoney)-parseFloat(nowmoney)>parseFloat(climitNomal)){
							alert("信用额度不足");
							$("#cashMoney").addClass("invalid");
							return;					
						}
					}
					strBatchTypeName = "不分批";
					dblNowMny = nowmoney;
					
                    if(batchType==1){//分批{
						if(parseFloat(totalmoney)>0&&isNullOrZero(1,batchMaxMoney)==false){
						    return;
						}
						if(parseFloat(batchMaxMoney)+parseFloat(nowmoney)>parseFloat(totalmoney)){
							alert("分批最大金额与现结金额之和不能大于合同金额");
							$("#batchMaxMoney").addClass("invalid");
							$("#cashMoney").addClass("invalid");
							return;					
						}
						if(parseFloat(batchMaxMoney)>parseFloat(climitNomal)){
							alert("分批最大金额不能大于信用额度");
							$("#cashMoney").removeClass("invalid");
							$("#batchMaxMoney").addClass("invalid");
							return;					
						}
						strBatchTypeName = "分批";
						intBatchType = "1";
						dblBatchMny = batchMaxMoney;
					}
					break;
			}
            initParentPayInfo();
			$("#salesPaymentWay",window.opener.document).html(strPayTypeName);
			$("#salesBatchWay",window.opener.document).html(strBatchTypeName);
			if(parseFloat(dblNowMny)>0){
		        $("#salesCashMoney",window.opener.document).html(formatMoney(dblNowMny,2));
			}else{
				$("#salesCashMoney",window.opener.document).html(dblNowMny);
			}
			$("#salesCon\\.cashMoney",window.opener.document).attr("value",dblNowMny);

			if(parseFloat(dblBatchMny)>0){
				$("#salesBatchMaxMoney",window.opener.document).html(formatMoney(dblBatchMny,2));
			}else{
				$("#salesBatchMaxMoney",window.opener.document).html(dblBatchMny);
			}
			$("#salesCon\\.batchMaxMoney",window.opener.document).attr("value",dblBatchMny);
			$("#salesCon\\.batchWay",window.opener.document).attr("value",intBatchType);
			$("#salesCon\\.paymentWay",window.opener.document).attr("value",intPayType);
			
            $("#salesCon\\.creditTypeId",window.opener.document).attr("value",arrCredit[0]); //信用类型编号
            $("#salesCon\\.cusCreditId",window.opener.document).attr("value",arrCredit[1]); //客户信用编号

			$("#salesCon\\.arterm",window.opener.document).attr("value",arrCredit[4]); //账期
            $("#salesCon\\.climit",window.opener.document).attr("value",arrCredit[5]); //信用额度

			$("#salesCon\\.projectName",window.opener.document).attr("value",arrCredit[3]); //项目名称
            $("#freeLimit",window.opener.document).attr("value",arrCredit[6]); //可用额度

			$("#salesCreTypeName",window.opener.document).html(arrCredit[2]);//项目类型
			if(arrCredit[3]==null || arrCredit[3]==""){
			    $("#salesProductName",window.opener.document).html("&nbsp;");//项目名称			
			}else{
			    $("#salesProductName",window.opener.document).html(arrCredit[3]);//项目名称
			}
			$("#salesArterm",window.opener.document).html(arrCredit[4]);//账期
			$("#salesClimit",window.opener.document).html(formatMoney(arrCredit[5], 2));//信用额度
			$("#salesFree",window.opener.document).html(formatMoney(arrCredit[6], 2));//可用额度
			
			//将type的值通过父页面传给后台action，进行判断
			$("#payType",window.opener.document).attr("value",intPayType);
			
            window.close();
		}

		//初始化父页面付款方式相关信息
		function initParentPayInfo(){
			$("#salesBatchMaxMoney",window.opener.document).html("");
			$("#salesCon.batchMaxMoney",window.opener.document).attr("value","");
			$("#salesBatchWay",window.opener.document).html("");
			$("#salesCon.batchWay",window.opener.document).attr("value","");
			$("#salesCon.paymentWay",window.opener.document).attr("value","");
			$("#salesPaymentWay",window.opener.document).html("");
			$("#salesCashMoney",window.opener.document).html("");
			$("#salesCon.cashMoney",window.opener.document).attr("value","");

			$("#salesCon.creditTypeId",window.opener.document).attr("value",""); //信用类型编号
			$("#salesCon.cusCreditId",window.opener.document).attr("value",""); //客户信用编号
			$("#salesCon.arterm",window.opener.document).attr("value",""); //账期
			$("#salesCon.climit",window.opener.document).attr("value",""); //信用额度
			$("#salesCon.projectName",window.opener.document).attr("value",""); //项目名称
			$("#freeLimit",window.opener.document).attr("value",""); //可用额度

			$("#salesCreTypeName",window.opener.document).html(arrCredit[2]);//项目类型
			$("#salesProductName",window.opener.document).html(arrCredit[3]);//项目名称
			$("#salesArterm",window.opener.document).html(arrCredit[4]);//账期
			$("#salesClimit",window.opener.document).html(arrCredit[5]);//信用额度
			$("#salesFree",window.opener.document).html(arrCredit[6]);//可用额度

			//将type的值通过父页面传给后台action，进行判断
			$("#payType",window.opener.document).attr("value","");
		}

		// 验证输入金额
		function checkCashMoneyFormat(obj){
			var money = obj.value;
			if($.trim(money)==""){
					alert("金额不能为空");
					obj.focus();
				return;
			}
			if(/^[1-9](\d{1,7})?(\.\d{1,2})?$/.test(money) || /^0{1}\.\d{1,2}$/.test(money) || money==0){
	
			}else{
				alert("输入的金额错误");
				obj.focus();
				return;
			}
		};

		//金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
		function formatMoney(s,n){
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

		//判断现结金额和分批金额是否为空和零 flg:0现结,1分批;obj金额
		function isNullOrZero(flg,obj){
			if(obj=="" || isNaN(obj)){
				if(flg==0){
					alert("现结金额不能为空");
					$("#cashMoney").addClass("invalid");
					return false;	
				}
				if(flg==1){
					alert("分批最大金额不能为空");
					$("#batchMaxMoney").addClass("invalid");
					return false;		
				}
			}

			if(obj==0){
				if(flg==0){
					alert("现结金额不能为零");
					$("#cashMoney").addClass("invalid");
					return false;	
				}
				if(flg==1){
					alert("分批最大金额不能为零");
					$("#batchMaxMoney").addClass("invalid");
					return false;			
				}
			}
		}		
        </script>
	</head>

	<body>
		<form	action=""	method="post">
			<br />
			<table width="96%" border="0" cellpadding="0" cellspacing="0"
				align="center" class="biao3">
				<input type="hidden" id="climitmoney">
				<input type="hidden" id="totalMoney" value="${totalMoney}">
				<tr>
					<td height="18" nowrap="nowrap" class="td_01">
						客户名称
					</td>
					<td colspan="4" class="td_02">
						${customerName}
					</td>
				</tr>
				<tr>
					<td class="td_01" height="18" nowrap="nowrap">
						产品分类名称
					</td>
					<td class="td_02">
						${proTypeName}
					</td>
					<td class="td_01">
						销售总额
					</td>
					<td class="td_02" id="totalmoneyObj">
						${totalMoney}&nbsp;元
					</td>
				</tr>
			</table>
			<br />
			<form action="sell.do?method=chosePayType" method="post">
				<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="table">
					<input type="hidden" value="${proTypeName}" name="proTypeName">
					
					<tr>
						<th width="30">
							选择
						</th>
						<th>
							信用类型
						</th>
						<th>
							项目名称
						</th>
						<th>
							账期(天)
						</th>
						<th>
							信用额度(元)
						</th>
						<th>
							可用额度(元)
						</th>
					</tr>
					<tr>
						<logic:present name="paymentWaylist">
							<logic:iterate id="paylist" name="paymentWaylist">
								<tr>
									<td width="30">
									&nbsp;<input type="radio" name="chooseradio" id="radio3"
											value="${paylist.creditTypeId}#${paylist.customerCrdId}#${paylist.creditTypeName}#${paylist.projectName}#${paylist.paymentTerm}#${paylist.assignedLimit}#${paylist.freeLimit}"
											onclick="returnObj(this)" />
									</td>
									<td width="96px">
										${paylist.creditTypeName}
									</td>
									<td width="96px">
										${paylist.projectName}&nbsp;
									</td>
									<td width="50px"
										style="text-align:right; padding-right:5px;">
										${paylist.paymentTerm}
									</td>
									<td width="84px"
										style="text-align:right; padding-right:5px;"
										id="climitObj">
										<fmt:formatNumber value="${paylist.assignedLimit}" type="number"
											pattern="#,###.00" />
									</td>
									<td width="84px"
										style="text-align:right; padding-right:5px;">
										<fmt:formatNumber value="${paylist.freeLimit}" type="number"
											pattern="#,###.00" />
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</tr>
				</table><br />
				<table cellpadding="0" cellspacing="0" width="96%" border="0"
					align="center">
					<tr>
						<td style="text-align:right" class="td_04">
							<%@ include file="/jsp/common/newPage.jsp"%>
						</td>
					</tr>
				</table>
			</form>
			<table cellpadding="0" cellspacing="0" width="96%" border="0"
				align="center" class="biao3">
				<tr>
					<td width="20%" class="td_03">
						付款方式
					</td>
					<td class="td_04"">
						<input type="radio" name="paymentTypeRadio" id="allCashPayMentWay" value="0"/>
						全部现结&nbsp;&nbsp;&nbsp;
						<input type="radio" name="paymentTypeRadio" id="allCreditPayMentWay" value="1"/>
						全部信用&nbsp;&nbsp;&nbsp;
						<input type="radio" name="paymentTypeRadio" id="partCashAndPartCreditPayMentWay" value="2"/>
						部分现结部分信用
					</td>
				</tr>
				<tr id="cashMoneyTR" style="display:none">
					<td class="td_03">
						现结金额
					</td>
					<td class="td_04">
						<input type="text" name="cashMoney" id="cashMoney" value="" style="width:120px;"
							onblur="checkCashMoneyFormat(this)" />
						元
					</td>
				</tr>
				<tr id="batchWayTR" style="display:none">
					<td class="td_03">
						分批方式选择
					</td>
					<td class="td_04">
						<input type="radio" name="batchWayRadio" id="notBatchWay" value="0" />
						不分批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="batchWayRadio" id="batchWay" value="1" />
						分批
					</td>
				</tr>
				<tr id="batchMaxMoneyDIV" style="display:none">
					<td class="td_03">
						分批最大金额
					</td>
					<td class="td_04">
						<input type="text" name="batchMaxMoney" id="batchMaxMoney" value="" style="width:120px;"
								onblur="checkCashMoneyFormat(this)" />
							元
					</td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td height="45px" colspan="2" align="center" valign="bottom">
						<a href="javascript:selectPaymentWay();"> <img
								src="${ctx}/images/btnChoice.gif" width="65" height="20" /> </a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
