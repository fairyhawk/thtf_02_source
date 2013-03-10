 
 //销售合同预览
 function previewContract(ctx,contractId) {
 	
 	window.open(ctx+'/salesContractPreview.do?salesContractId=' + contractId + '&command=preview','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=100,left=400, width=750,height=680");
 }
 
 //设置弹出窗口,只需传窗口的高度
function popWindows(height){
	var win = "toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height="+height
	return win;
}