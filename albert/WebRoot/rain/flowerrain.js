
$(function(){
		 for(var i=1;i<9;i++)
		 { 
		   var j = Math.ceil(Math.random()*8);

		   var s = "<img src='rain/petal"+j+".png' class='pic'>";
		   //alert(s);
		   $(".content").append(s);
		 }
		 var len = $(".content .pic").length;
		 var bodywidth=document.body.clientWidth;
		 var bodyheight=document.body.clientHeight;
		 
         function flowerdrop(){
		
		  for(var i=0;i<len;i++)
		   {
		      $temp=$(".content .pic").eq(i);
			  var le1=parseInt($temp.css("left"));
			  var top1=parseInt($temp.css("top"));
			  var bodybuttom=620-parseInt($temp.css("height"))-5;
			  var bwidth=bodywidth-parseInt($temp.css("width"))-20;
	            
			  if(le1<0||top1>bodybuttom+Math.random()*25)
			  {
			    if(le1>0)
				{
				 var $clone1=$temp.clone();
				$(".div2").append($clone1);
				}
				
				var yoffset=Math.random()*10+0.6;
				var xoffset=Math.random()/3+0.05;
				
			 	$temp.attr("off_x",xoffset);  
				$temp.attr("off_y",yoffset);
				
				$temp.css("top",0);
				x=Math.random()*bwidth;
				$temp.css("left",x)
				
			   }
			  $temp.css("left","+="+$temp.attr("off_x")+"px");
			  $temp.css("top","+="+$temp.attr("off_y")+"px");
		   } 
		  }
 
         function delpeony()
		 {
		   
		    $(".div2 .pic:eq(0)").remove(); 
		 }
		 setid=setInterval(flowerdrop,100);
		
});
	   

