function onloadEvent(func){ 
	var one=window.onload 
	if(typeof window.onload!='function'){ 
		window.onload=func 
	}else{ 
		window.onload=function(){ 
			one(); 
			func(); 
  		} 
	} 
} 

function showtable(){ 
var tableid='table';  //表格的id 
var tableid1='table1';  //表格的id 
var overcolor='#f2f2f2'; //鼠标经过颜色 
var color1='#ecf6fc';  //第一种颜色 
var color2='#FFFFFF';  //第二种颜色 
var tablename=document.getElementById(tableid) 
var tablename1=document.getElementById(tableid1) 
var tr=tablename.getElementsByTagName("tr") 
var tr1=tablename1.getElementsByTagName("tr") 
for(var i=1 ;i<tr.length;i++){ 
  tr[i].onmouseover=function(){ 
   	this.style.backgroundColor=overcolor; 
  } 
  tr[i].onmouseout=function(){ 
   if(this.rowIndex%2==0){ 
    this.style.backgroundColor=color1; 
   }else{ 
    this.style.backgroundColor=color2; 
   } 
  } 
  if(i%2==0){ 
   tr[i].className="color1"; 
  }else{ 
   tr[i].className="color2"; 
  } 
} 
for(var i=1 ;i<tr1.length;i++){ 
  tr1[i].onmouseover=function(){ 
   	this.style.backgroundColor=overcolor; 
  } 
  tr1[i].onmouseout=function(){ 
   if(this.rowIndex%2==0){ 
    this.style.backgroundColor=color1; 
   }else{ 
    this.style.backgroundColor=color2; 
   } 
  } 
  if(i%2==0){ 
   tr1[i].className="color1"; 
  }else{ 
   tr1[i].className="color2"; 
  } 
} 
} 

onloadEvent(showtable); 


function getSubString(oldString,length){
 if(oldString.length>length){
  oldString=oldString.substr(0,length)+"...";
 }
 return oldString;
}


function fMainListToggle(obj){
	obj = obj.parentNode;
	var domselect = null;
	var domImg = null ;
	var tmp = obj.getElementsByTagName('div');
	for(var i=0;i<tmp.length;i++){
		if(tmp[i].className == 'mo_con'){
			domselect = tmp[i];
			domImg = tmp[i];
		}
	}
	if(domselect.style.display == 'none'){
		domselect.style.display = '';
		domImg.style.backgroundPosition = '0 0';
	}else{
		domselect.style.display = 'none';
		domImg.style.backgroundPosition = '-16px 0';
	}
	return ;
}