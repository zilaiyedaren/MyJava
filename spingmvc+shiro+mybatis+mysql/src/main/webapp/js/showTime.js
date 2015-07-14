// JavaScript Document

/********************************************************************
* @summary Create the function for show the current time to the html 
* @author  Dang Xiao-Qiang
********************************************************************/
     
	 var enabled = 0;  //防止错误显示
	 var dateString = "";
	 var current = new Date();
	 var year = current.getFullYear();
	 var month = (parseInt(current.getMonth())+1);
	 var today = current.getDate();
	 var week = current.getDay();
	 switch (week)
	 {
	   case 0 : week = "星期天"; break;
	   case 1 : week = "星期一"; break;
	   case 2 : week = "星期二"; break;
	   case 3 : week = "星期三"; break;
	   case 4 : week = "星期四"; break;
	   case 5 : week = "星期五"; break;
	   case 6 : week = "星期六"; break;
	 }
	 dateString += year +"-";
	 dateString += ((month<10) ? "0" : "") + month +"-";
	 dateString += ((today<10) ? "0" : "") + today;
	 dateString += " "+week + " ";
	
	 //Define a function showTime() for showing time 
	 function showTime() 
	 {
		 var timeString = "";
		 var now = new Date();
		 var hour = now.getHours();
		 var minute = now.getMinutes();
		 var second = now.getSeconds();
		 try
		 {
			 timeString += ((hour<10) ? "0" : "") + hour;
			 timeString += ((minute<10) ? ":0" : ":") + minute;
			 timeString += ((second<10) ? ":0" : ":") + second;
			 //Insert current time into the place in the source code which its id=clock 
			 clock.innerHTML = dateString + timeString;
			 window.setTimeout("showTime()", 1000);
		 }
		 catch(e){ }
	 }
	 showTime();
