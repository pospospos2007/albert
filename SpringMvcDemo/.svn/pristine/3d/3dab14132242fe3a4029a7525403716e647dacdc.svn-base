window.onload=function(){
	waterfall("waterfall","updateItem");
	window.onresize=function(){
		waterfall("waterfall","updateItem");
	}
}
function waterfall(oParentId,clsName){
	var main=document.getElementById(oParentId);
	var imgBox=getByClass(oParentId,clsName);
	var wBox=parseFloat(getStyle(imgBox[0],'width'))
	var wClient=main.offsetWidth;
	var colNum=Math.floor(wClient/wBox);
	var offHeight=new Array();
	for(var j=0;j<imgBox.length;j++){
		imgBox[j].style.position="static";
		imgBox[j].style.top="none";
		imgBox[j].style.left="none";
	}
	for(var i=0;i<imgBox.length;i++){
		if(i<colNum){
			imgBox[i].style.position="absolute";
			imgBox[i].style.top=0+"px";
			imgBox[i].style.left=wBox*i+"px";
			offHeight.push(imgBox[i].offsetHeight);
		}
		else{
			var minH=Math.min.apply(null,offHeight);
			minIndex=getIndex(offHeight,minH);
			imgBox[i].style.position="absolute";
			imgBox[i].style.top=minH+"px";
			imgBox[i].style.left=wBox*minIndex+"px";
			offHeight[minIndex]+=imgBox[i].offsetHeight;
		}
	}
	main.style.height=Math.max.apply(null,offHeight)+'px';
}
function getByClass(oParentId,clsName){
	var oParent=document.getElementById(oParentId);
	var allElement=oParent.getElementsByTagName("*");
	var oElement=[];
	for(var i=0;i<allElement.length;i++){
		if(allElement[i].className.indexOf(clsName+' ')>=0||allElement[i].className.indexOf(' '+clsName)>=0||allElement[i].className.indexOf(' '+clsName+' ')>=0){
			oElement.push(allElement[i]);
		}
	}
	return oElement;
}
function getIndex(array,num){
	var index;
	for(var i=0;i<array.length;i++){
		if(array[i]==num){
			index=i;
			break;
		}
	}
	return index;
}
//取样式
function getStyle(obj,attr){
	if(obj.currentStyle){//IE取样式
		return obj.currentStyle[attr];
	}else{
		return getComputedStyle(obj,false)[attr];
	}

}