function che(a){
   		var all=document.getElementsByName("ss");
    		 if(a.checked==true){
       			 for(i=0;i<all.length;i++){
            		all[i].checked=true;
            		myArray.push(all[i].value);
       		 }
   			 }else if(a.checked==false){
        		for(i=0;i<all.length;i++){
            		all[i].checked=false;
        		}
        		myArray=new Array();
   	 		 }

		}
function che1(a){
   		var all=document.getElementsByName("ss1");
    		 if(a.checked==true){
       			 for(i=0;i<all.length;i++){
            		all[i].checked=true;
            		myArray.push(all[i].value);
       		 }
   			 }else if(a.checked==false){
        		for(i=0;i<all.length;i++){
            		all[i].checked=false;
        		}
        		myArray=new Array();
   	 		 }

		}
		
