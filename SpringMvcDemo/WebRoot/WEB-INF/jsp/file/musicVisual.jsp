<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <%@ include file="/include/head.jsp"%> --%>
    <title>音乐可视化</title>
  
  
  <canvas id="canvas"></canvas>
<div id="sourceinput" style="opacity:1;"><div id="msg" style="font-size: 29px;">点我或拖进音乐喵！</div><input type="file" multiple /></div>

<!-- 播放列表 -->
<div id="playlist"  style="opacity:1;">
	<div id="controls">
		<div id="pre" style="left: 0px;"><svg width="40.0px" height="40.0px"><polyline fill="#fff" points="30.11111,5.2222223 11.732024,20.99673 30.555555,34.96732 30.408497,4.9673195" stroke-linejoin="round" stroke-linecap="round" stroke="#fff"/><line stroke-linecap="round" fill="none" x1="10.555555" x2="10.555555" y1="5.261437" y2="35.555557" stroke="#fff"stroke-width="3"/></svg></div>
		<div id="play" style="left: 40px;"><svg width="40.0px" height="40.0px"><polyline fill="#fff" transform="matrix(-1.0 0.0 -0.0 -1.0 42.28758 39.93464)" points="30.444445,5.111111 12.065357,20.88562 30.88889,34.85621 30.74183,4.8562083" stroke-linejoin="round" stroke-linecap="round" stroke="#fff"/></svg></div>
		<div id="pause" style="left: 80px;"><svg width="40.0px" height="40.0px"><line stroke-linecap="round" transform="matrix(-1.0 0.0 -0.0 -1.0 41.11111 40.522877)" fill="none" x1="14.111108"
        x2="14.111115" y1="7.8169928" y2="32.300655" stroke="#fff" stroke-width="8"/><line stroke-linecap="round" transform="matrix(-1.0 0.0 0.0 -1.0 41.11111 40.522877)" fill="none" x1="27.999985" x2="27.999992" y1="7.8169928" y2="32.300655" stroke="#fff" stroke-width="8"/></svg></div>
		<div id="next" style="left: 120px;"><svg width="40.0px" height="40.0px"><polyline fill="#fff" transform="matrix(-1.0 0.0 -0.0 -1.0 41.11111 40.522877)" points="30.11111,5.2222223 11.732024,20.99673 30.555555,34.96732 30.408497,4.9673195" stroke-linejoin="round" stroke-linecap="round" stroke="#fff"/><line stroke-linecap="round" transform="matrix(-1.0 0.0 -0.0 -1.0 41.11111 40.522877)" fill="none" x1="10.555555"x2="10.555555" y1="5.261437" y2="35.555557" stroke="#fff" stroke-width="3"/></svg></div>
		<div id="playmode" style="left: 160px;"><svg width="40.0px" height="40.0px"></svg></div>
		<div id="songlist" style="left: 200px;"><svg width="40.0px" height="40.0px"><line stroke-linecap="round" fill="none" x1="7.4444447" x2="32.333332" y1="11.111111" y2="11.111111" stroke="#fff" stroke-width="6"/><line stroke-linecap="round" fill="none" x1="7.3333335" x2="32.22222" y1="20.222225" y2="20.222225" stroke="#fff" stroke-width="6"/><line stroke-linecap="round" fill="none" x1="7.4444447" x2="32.333332" y1="29.555557" y2="29.555557" stroke="#fff" stroke-width="6"/></svg></div>
		<div id="online"><span>Online</span><br><span id="onlinenumber">0</span></div>
		<div id="coding" style="left: 290px; width: 53px;"><a target="_blank" href="https://coding.net/u/luojia/p/Audio-Visualizations/git"><svg width="50px" height="40px" viewBox="0 0 407.5 306.577" enable-background="new 0 0 407.5 306.577" ><g><ellipse fill="#fff" cx="159.785" cy="167.517" rx="3.656" ry="4.39"/><ellipse fill="#fff" cx="246.618" cy="167.517" rx="3.656" ry="4.39"/><path fill="#fff" d="M299.252,139.319c-5.997,0-11.253,3.803-14.191,9.509c-9.997-21.679-30.795-41.764-56.498-50.035 c-13.869-5.534-23.608-16.155-24.812-28.568c-1.204,12.413-10.943,23.034-24.812,28.568c-25.703,8.271-46.501,28.355-56.498,50.035 c-2.938-5.706-8.194-9.509-14.191-9.509c-9.21,0-16.676,8.965-16.676,20.025c0,11.059,7.466,20.025,16.676,20.025 c2.801,0,5.439-0.833,7.757-2.299c0.793,35.91,39.753,59.219,87.745,59.282c47.991-0.062,86.952-23.372,87.745-59.282 c2.318,1.466,4.956,2.299,7.757,2.299c9.21,0,16.676-8.965,16.676-20.025C315.928,148.285,308.461,139.319,299.252,139.319z  M115.83,165.602c-0.78,2.883-4.253,4.111-7.757,2.744c-3.504-1.367-5.712-4.812-4.932-7.695c0.78-2.883,4.253-4.111,7.757-2.744 S116.61,162.72,115.83,165.602z M203.75,226.356c-38.492-0.06-69.664-18.201-69.664-44.17c0-0.544,0.02-1.083,0.046-1.621 c0.009-0.177,0.018-0.354,0.03-0.531c0.024-0.367,0.057-0.732,0.093-1.097c0.049-0.489,0.107-0.976,0.178-1.46 c0.007-0.048,0.013-0.096,0.02-0.144c0.775-5.104,2.759-12.893,5.755-17.382c7.198-10.412,20.037-17.348,34.684-17.348 c11.276,0,21.473,4.118,28.858,10.759c7.385-6.641,17.582-10.759,28.858-10.759c14.647,0,27.486,6.937,34.684,17.348 c2.996,4.489,4.98,12.278,5.755,17.382c0.007,0.048,0.013,0.096,0.02,0.144c0.072,0.484,0.129,0.972,0.178,1.46 c0.036,0.365,0.069,0.729,0.093,1.097c0.012,0.177,0.021,0.354,0.03,0.531c0.026,0.538,0.046,1.077,0.046,1.621 C273.414,208.155,242.242,226.295,203.75,226.356z M299.427,168.346c-3.504,1.367-6.977,0.139-7.757-2.744s1.428-6.328,4.932-7.695 c3.504-1.367,6.977-0.139,7.757,2.744C305.139,163.534,302.931,166.979,299.427,168.346z"/></g></svg></a></div>
	</div>
	<div id="list">
		<div></div><br>
		<div></div><br>
		<div></div><br>
		<div></div><br>
		<div></div>
	</div>
</div>
<style>
html{
background-color: rgb(247, 246, 242);
moz-user-select: -moz-none;
-moz-user-select: none;
-o-user-select:none;
-khtml-user-select:none;
-webkit-user-select:none;
-ms-user-select:none;
user-select:none;
}
body{
	margin: 0px;
	padding: 0px;
}
canvas{
	position:fixed;
	width:100%;
	height:100%;
}
#online{
left: 240px;
font-size: 14px;
text-align: center;
  line-height: 17px;
width: 50px !important;
}
#online span:last-child{
	font-size: 19px;
}
#sourceinput{
  position: fixed;
  font-family: "黑体","微软雅黑";
  color: #D2DBCF;;
  border-radius: 0 0 11px 11px;
  top: 0px;
  width: 450px;
  height: 40px;
  background-color: rgba(12, 34, 81, 0.29);
  left: calc(50% - 225px);
  overflow: hidden;
  transition:opacity 0.5s;
}
input,#msg{
    position: absolute;
  left: 0px;
  min-width: 100%;
  height: 100%;
  white-space: nowrap;
  border: none;
  line-height: 40px;
  text-align: center;
  cursor: pointer;
}
input{
	width: 100% !important;
	opacity: 0;
}
#msg{
	transition:opacity 0.2s;
}
#ctrl div{
  background-color: rgba(232, 188, 188, 0.43);
  font-size: 28px;
  padding: 0 5px;
  cursor: pointer;
  transition: background-color 0.2s;
}
#ctrl div:hover{
	 background-color: rgba(121, 134, 38, 0.43);;
}
#playlist{
	transition: bottom 0.2s,opacity 0.5s;
  height: 240px;
  background-color: rgba(92, 90, 90, 0.49);
  width: 100%;
  position: fixed;
  color: #F7F1F1;
  font-size: 30px;
  left: 0px;
  bottom: -240px;
}
#controls{
  position: absolute;
  background-color: inherit;
  width: 343px;
  left: calc(50% - 171.5px);
  height: 40px;
  border-radius: 8px 8px 0 0;
  bottom: 100%;
  overflow: hidden;
}
#list div{
	height: 40px;
  position: relative;
  display: inline-block;
  width: auto;
  white-space: nowrap;
}
#controls div{
	position: absolute;
	display: inline-block;
	height: 40px;
	width: 40px;
	transition:background-color 0.3s;
	cursor: pointer;
}
#controls div:hover{
	background-color:#5C5C5C;
}
#list{
	  overflow-x: auto;
  height: 100%;

}
.listitem{
	  transition: opacity 0.1s,background-color 0.3s,color 0.3s;
  display: inline-block;
  padding: 0 3px 0 6px;
  cursor: pointer;
  border: 1px solid #ccc;
  opacity: 1;
    line-height: 40px;
  background-color: rgba(255,255,255,0);
}
.listitem:hover{
	opacity: 0.5;
	color:#000;
	background-color: rgba(255,255,255,0.6);
}
</style>
<script src="<%=path%>/js/musicVisual/CanvasObjLibrary.js"></script>
<script src="<%=path%>/js/musicVisual/GraphLib.js"></script>
<script>

var playmodesvgs={
	"顺序":'<line stroke-linecap="round" fill="none" x1="7.7777777" x2="31.777779" y1="19.777779" y2="19.777779" stroke="#fff" stroke-width="5"></line><line stroke-linecap="round" fill="none" x1="20.88889" x2="31.88889" y1="8.555555" y2="19.555555" stroke="#fff" stroke-width="5"></line><line stroke-linecap="round" fill="none" x1="21.666664" x2="31.777779" y1="31.14384" y2="19.999998" stroke="#fff" stroke-width="5"></line>',
	"单曲循环":'<rect x="7.111111" y="7.2222223" fill="none" width="25.777779" stroke-linejoin="round" height="25.777779" stroke="#fff" stroke-width="3"></rect><polyline fill="none" stroke-width="3" points="20.222221,27.333334 20.222221,13.111111 15.666667,17.666666" stroke-linejoin="round" stroke-linecap="round" stroke="#fff"></polyline>',
	"随机":'<polyline fill="none" stroke-width="3" points="4.6666656,28.333334 21.56118,11.438817 36.04219,11.438817" stroke-linecap="round" stroke="#fff"></polyline><polyline fill="none" stroke-width="3" transform="matrix(1.0 0.0 0.0 -0.9047619 0.0 56.296295)" points="5.0689154,48.98653 21.963428,32.09201 36.444443,32.09201" stroke-linecap="round" stroke="#fff"></polyline>',
	"列表循环":'<rect x="7.111111" y="7.2222223" fill="none" width="25.777779" stroke-linejoin="round" height="25.777779" stroke="#fff" stroke-width="3"></rect><line stroke-linecap="round" fill="none" x1="13.111112" x2="26.88889" y1="20.222223" y2="20.222223" stroke="#fff" stroke-width="3"></line><line stroke-linecap="round" fill="none" x1="13.111112" x2="26.88889" y1="13.888889" y2="13.888889" stroke="#fff" stroke-width="3"></line><line stroke-linecap="round" fill="none" x1="13.111112" x2="26.88889" y1="26.222221" y2="26.222221" stroke="#fff" stroke-width="3"></line>',
}

if(!window.AudioContext){
	window.AudioContext = window.webkitAudioContext || window.mozAudioContext || window.msAudioContext;
}

if(!(Audio&&XMLHttpRequest&&AudioContext&&FileReader&&Uint8Array)){
	document.write("您的浏览器无法正常查看此作品");
}else{
function clone(obj){
	if(typeof(obj) != 'object') return obj;
	if(obj == null) return obj;
	var myNewObj ={};
	for(var i in obj)
		myNewObj[i] = clone(obj[i]);
	return myNewObj;
}
function minus(origin,to,pre,speed){
	var speed=speed||1;
	return origin+(to-origin)*pre*speed;
}
function c_ele(name){
	return document.createElement(name);
}
function rand(min, max) {
	return (min + Math.random() * (max - min)+0.5)|0;
}

var playmode=["顺序","单曲循环","随机","列表循环"];
var playoption={
	mode:0
}
var playmodesvg=document.querySelector("#playmode svg");
var input=document.querySelector("input"),
msg=document.querySelector("#msg");

//击退率
var backrate=0;


var Debug={
	rawFrequency:false
}

var playingid=0,playlist=[];
input.onchange=function(e){
	var p=false;
 	if(playlist.length===0)p=true;
 	loadfilelist(input.files);
 	if(p){playmusic(0);}
}

function loadfilelist(fl){
	for(var i=0;i<fl.length;i++){
 		if(fl[i].type.match(/^audio/)){
 			playlist.push({name:fl[i].name.replace(/\.(?:mp3|ogg|mp4|wav)$/i,""),src:URL.createObjectURL(fl[i])});
 			setTimeout(addtoplaylist,0,playlist.length-1);
 		}
 	}
}

function playmusic(ind){
	if(playlist[ind]){
		audio.pause();
   	 	audio.src=playlist[ind].src;
   	 	playingid=ind;
   	 	audio.play();
   	 	msg.style.opacity=0;
   	 	msg.innerHTML="正在播放:"+playlist[ind].name;
   	 	setTimeout(adjustfontsize,50,true);
	}else{
		console.log(playlist[ind]);
	}
}

function setmode(m){
	if(playmode[m]){
		playmodesvg.innerHTML=playmodesvgs[playmode[m]];
		playoption.mode=m;
	}
}

var listtracks=document.querySelectorAll("#list div"),
playlistdom=document.querySelector("#playlist"),
sourceinputdom=document.querySelector("#sourceinput"),
online=document.querySelector("#onlinenumber");
function addtoplaylist(id){
	var item=c_ele("span"),track=0,trackwidth=[];
	item.className="listitem";
	item.innerHTML=playlist[id].name;
	item.songid=id;
	for(var qwe=listtracks.length;qwe--;){
		trackwidth[qwe]=listtracks[qwe].offsetWidth;
	}
	for(var qwe=0;qwe<trackwidth.length;qwe++){
		if(trackwidth[qwe]<trackwidth[track])track=qwe;
	}
	listtracks[track].appendChild(item);
}

function getfontsize(ele){
	return ele.style.fontSize=Number(msg.style.fontSize.match(/\d+/));
}
function adjustfontsize(first){
	var size=getfontsize(msg);
	if(first){
		msg.style.fontSize="29px";
		setTimeout(adjustfontsize,50);
		return;
	}
	if(size<12){msg.style.opacity=1;return;}
	if(msg.offsetWidth>450||size>29){
		msg.style.fontSize=getfontsize(msg)-0.2+"px";
		setTimeout(adjustfontsize,50);
		return;
	}else if(msg.offsetWidth<430){
		msg.style.fontSize=getfontsize(msg)+0.2+"px";
		setTimeout(adjustfontsize,50);
		return;
	}
	msg.style.opacity=1;
}

var speed=1;
var canvas=document.querySelector("canvas"); 
//初始化图形库
var COL=newCOL(canvas),Glib=getGraphlib(COL);;
COL.setCanvas(canvas);
COL.autoClear=true;
COL.document.eventable=false;
COL.document.drawtype="function";
//COL.Debug.on();
//COL.Debug.eleinfo=true;

//初始宽高数据
var w=window.innerWidth,h=window.innerHeight;
var cr=(w>h?h:w)/2;
//禁用右键菜单
canvas.addEventListener("contextmenu",function(e){
	e.preventDefault();
});




//波形层
var wavelayer=COL.Graph.New();
wavelayer.relativesize.width=1;
wavelayer.relativesize.height=1;
wavelayer.wavearray=new Uint8Array(512);
wavelayer.drawfunction=function(ct){
	if(wavelayer.wavearray){
		var sum=0;
		ct.beginPath();
		ct.strokeStyle="rgba(52, 52, 52, 0.42)";
		ct.lineWidth=2;
		//ct.moveTo(1,COL.height/2);
		for (var i = 0; i < wavelayer.wavearray.length; i++) {
			if(wavelayer.wavearray[i]>128)sum+=wavelayer.wavearray[i];
			ct.lineTo(COL.width/wavelayer.wavearray.length*(i+1),(wavelayer.wavearray[i]-128)/2+COL.height/2);
		}
		ct.stroke();
		//Debugwave.height=sum/512;
	}
	
}
COL.document.addChild(wavelayer);


//两边的小圆
var sidecircletemple=Glib.getGraphObj("arc",{r:cr*0.39,fillColor:"rgba(102, 102, 102, 0.4)"});
sidecircletemple.relativerotatecenter.x=0.5;
sidecircletemple.relativerotatecenter.y=0.5;
sidecircletemple.relativepositionpoint.x=0.5;
sidecircletemple.relativepositionpoint.y=0.5;
//sidecircletemple.Composite="source-out";

var sidecirclearr=[];
(function(){
	//var 
	for(var i=0;i<4;i++){
		sidecirclearr[i]=sidecircletemple.New(true,false,true);
		sidecirclearr[i].offsetzoom=0;
		COL.Graph.Eventable(sidecirclearr[i]);
		sidecirclearr[i].addEvent("mouseover",function(e){
			if(e.target)e.target.offsetzoom=0.6;
		});
		sidecirclearr[i].addEvent("mouseout",function(e){
			if(e.target)e.target.offsetzoom=0;
		});
		COL.document.addChild(sidecirclearr[i]);
	}
})();
sidecirclearr[1].relativeposition={x:1,y:0};
sidecirclearr[2].relativeposition={x:0,y:1};
sidecirclearr[3].relativeposition={x:1,y:1};

//星星层
var starlayer=COL.Graph.New();
starlayer.relativesize.width=1;
starlayer.relativesize.height=1;
starlayer.needsort=false;
COL.document.addChild(starlayer);

//圆圈框架
var circleframe=COL.Graph.New();
circleframe.zoom.x=0.86;
circleframe.zoom.y=0.86;
circleframe.relativesize.width=1;
circleframe.relativesize.height=1;
circleframe.relativerotatecenter.x=0.5;
circleframe.relativerotatecenter.y=0.5;
circleframe.relativeposition.x=0.5;
circleframe.relativeposition.y=0.5;
circleframe.relativepositionpoint.x=0.5;
circleframe.relativepositionpoint.y=0.5;
COL.document.addChild(circleframe);

//频率条框架
var pinlvbarframe=COL.Graph.New({width:0,height:0});
pinlvbarframe.relativeposition.x=0.5;
pinlvbarframe.relativeposition.y=0.5;
pinlvbarframe.rotate=45;
circleframe.addChild(pinlvbarframe);

//后方黑圆
var backcircle=Glib.getGraphObj("arc",{r:cr*0.86,fillColor:"#000",anticlockwise:false,opacity:0.5});
backcircle.relativeposition.x=0.5;
backcircle.relativeposition.y=0.5;
backcircle.relativepositionpoint.x=0.5;
backcircle.relativepositionpoint.y=0.5;
backcircle.relativerotatecenter.x=0.5;
backcircle.relativerotatecenter.y=0.5;
circleframe.addChild(backcircle);

var framezoominterval;//整体框架缩放定时器

//coding圆圈
var codingcircle=Glib.getGraphObj("arc",{r:cr*0.75,fillColor:"#fafafa",anticlockwise:false,borderWidth:cr*0.08,borderColor:"#353535"});
circleframe.addChild(codingcircle);
COL.Graph.Eventable(codingcircle);
codingcircle.relativeposition.x=0.5;
codingcircle.relativeposition.y=0.5;
codingcircle.relativepositionpoint.x=0.5;
codingcircle.relativepositionpoint.y=0.5;
codingcircle.relativerotatecenter.x=0.5;
codingcircle.relativerotatecenter.y=0.5;
codingcircle.Composite="source-atop";
codingcircle.addEvent("mouseover",function(){
	if(framezoominterval)clearInterval(framezoominterval);
	framezoominterval=setInterval(function(){
		circleframe.zoom.x=circleframe.zoom.y=minus(circleframe.zoom.x,0.86+0.08,0.5,speed);
		if(circleframe.zoom.x>=0.86+0.07){
			clearInterval(framezoominterval);
			framezoominterval=0;
		}
	},1000/30);
});
codingcircle.addEvent("mouseout",function(){
	if(framezoominterval)clearInterval(framezoominterval);
	framezoominterval=setInterval(function(){
		circleframe.zoom.x=circleframe.zoom.y=circleframe.zoom.x+(0.86-circleframe.zoom.x)*0.5*speed;
		if(circleframe.zoom.x<=0.86+0.01){
			circleframe.setZoom(0.86,0.86);
			clearInterval(framezoominterval);
			framezoominterval=0;
		}
	},1000/30);
});

//Coding
var text=COL.Graph.NewTextObj("Coding",cr*0.36,{color:"#353535",fontWeight:600});
text.relativeposition.x=0.5;
text.relativeposition.y=0.5;
text.relativepositionpoint.x=0.5;
text.relativepositionpoint.y=0.5;
text.relativerotatecenter.x=0.5;
text.relativerotatecenter.y=0.5;
codingcircle.addChild(text);
//text.realtimeVary=true;//实时渲染缩放文字会锯齿，已禁用


//星星模板
var startemplate=Glib.getGraphObj("star",{r:cr*0.026,color:"#3e3e3e"});
startemplate.opacity=0;
function a_star(){
	this.g=startemplate.New(true,true);
	this.g.x=rand(0,COL.width);
	this.g.y=rand(0,COL.height);
	this.dera=[1,-1][rand(0,1)];
	this.full=false;
	starlayer.addChild(this.g);
	this.time=setInterval(a_star_fun,1000/30,this);
}
var a_star_fun=function(t){
	t.g.rotate=minus(t.g.rotate,t.g.rotate-8*t.dera,0.5);
	if(t.full){
		t.g.opacity=minus(t.g.opacity,0,0.04);
		if(t.g.opacity<=0.01){
			clearInterval(t.time);
			COL.Graph.Delete(t.g);
			delete t;
			return;
		}
	}else{
		t.g.opacity=minus(t.g.opacity,1,0.1);
		if(t.g.opacity>=0.99)t.full=true;
	}
}

var array= new Uint8Array(640),averagepinlv=0;
var valley=0,laststat=false,heavyrecord=0;
var cturn=[0.6,0.4,0.2,0.45,0.5,0.4,0.6,0.3,0.3,0.4,0.56,0.61,0.3,0.6,0.4,0.3,0.6,0.4];
function changecolor(){
	cturn.push(cturn.shift());
}
function beforedraw(){//绘制前处理函数
	if(!launched){
		codingcircle.setZoom(minus(codingcircle.zoom.x,1,0.01,speed));
		if(codingcircle.zoom.x<1.02){launched=true;}
	}
	averagepinlv=0;
	if(!redzoominterval){
		codingcircle.zoom.x=codingcircle.zoom.y=minus(codingcircle.zoom.x,1,0.08,speed);
	}
	backcircle.zoom.x=backcircle.zoom.y=minus(backcircle.zoom.x,codingcircle.zoom.x,0.05,speed);
	if(analyser){
		analyser.getByteFrequencyData(array);
		var tmpavgpinlv=0;
		heavyrecord=(array[2]*1.2+array[0]*1.1)/2;
		var pre,co;
		for(var iplc=pinlvbar.length;iplc--;){
			var a=pinlvbar[iplc];
			if(iplc<4&&sidecirclearr){
				pre=array[iplc*5]/255;
				co=pre*255/0.564;
				sidecirclearr[iplc].zoom.x=sidecirclearr[iplc].zoom.y=1+pre*0.2+sidecirclearr[iplc].offsetzoom;
			}
			if(iplc>=59)tmpavgpinlv+=array[iplc*5];
			a.height=minus(a.height,0,0.06*speed);
			if(Debug.rawFrequency){
				a.height=array[iplc*5]*cr/255;
			}else if(a.height<array[iplc*5]-100){a.height=array[iplc*5]*cr/255*1.7;}
		}
		sidecircletemple.fillColor="rgba("+((co*cturn[0]+0.5)|0)+","+((co*cturn[1]+0.5)|0)+","+((co*cturn[2]+0.5)|0)+","+pre*0.6+")";
		pinlvbartemple.backgroundColor="rgba("+(255-(co*cturn[0]+0.5)|0)+","+(255-(co*cturn[1]+0.5)|0)+","+(255-(co*cturn[2]+0.5)|0)+",0.6)";

		averagepinlv=tmpavgpinlv/69*0.8+heavyrecord*1.5;
		if(averagepinlv>valley+17){
			valley=averagepinlv;
			suo();changecolor();
		}
		if(averagepinlv<valley){valley=averagepinlv;}

		//调整星星位置
		backrate=4.4*pre;
		var snode=starlayer.childNode;
		var cx=COL.width/2,
		cy=COL.height/2,
		sn;
		var cline=Math.sqrt(cx*cx,cy*cy);
		for(var i in snode){
			sn=snode[i];
			var line=Math.sqrt(Math.pow(cx-sn.x,2)+Math.pow(cy-sn.y,2));
			sn.setZoom(line/cline+0.2);
			sn.x-=backrate*(cx-sn.x)/line;
			sn.y-=backrate*(cy-sn.y)/line;
		}
	}


}


var redzoominterval;
//中央圆圈缩进
function suo(){
	if(codingcircle.zoom.x<=0.938){
		return;
	}
	if(redzoominterval)clearInterval(redzoominterval);
	redzoominterval=setInterval(function(){
		codingcircle.zoom.x=codingcircle.zoom.y=minus(codingcircle.zoom.x,0.93,0.87*speed);
		if(codingcircle.zoom.x<=0.935){
			clearInterval(redzoominterval);
			redzoominterval=0;
		}
	},1000/30);
	new a_star();
	new a_star();
}


//频率条模板
var pinlvbartemple=COL.Graph.New({width:codingcircle.r*2*3.141592653/138,height:500,y:codingcircle.r,backgroundColor:"rgba(78, 145, 245, 0.29)"});
pinlvbartemple.relativerotatecenter.x=0.5;
pinlvbartemple.relativepositionpoint.x=0.5;
pinlvbartemple.rotatecenter.y=-codingcircle.r;
//生成128个频率条
var pinlvbar=[];
for(var i=0;i<128;i++){
	pinlvbar[i]=pinlvbartemple.New(true);
	pinlvbar[i].rotate=360/128*i;
	pinlvbarframe.addChild(pinlvbar[i]);
}



(function(){
	var dir=0.09;
	codingcircle.rotate=0;
	window.cctm=setInterval(function(){
		if(!audio.paused){
			codingcircle.rotate+=dir;
			if(codingcircle.rotate>10)dir=-0.09;
			if(codingcircle.rotate<-10)dir=0.09;
		}
	},70);
})();


//音频上下文
var audioCtx = new window.AudioContext();
var audio=new Audio();
var meAudioSourceNode=audioCtx.createMediaElementSource(audio);
var analyser = audioCtx.createAnalyser();
var gainNode = audioCtx.createGain();
meAudioSourceNode.connect(gainNode);
meAudioSourceNode.connect(analyser);
gainNode.connect(audioCtx.destination);


//音乐控制
audio.onended=function(){
	next();
};
var songlist=document.querySelector("#list");
songlist.addEventListener("click",function(e){
	var o=e.target;
	if(o.songid>=0){
		playmusic(o.songid);
	}
});
var control=document.querySelector("#controls");
control.addEventListener("click",function(e){
	if(e.target.parentNode.localName!="div"){
		if(e.target.parentNode.parentNode.localName!="div"){
			console.log("?")
		}else{
			var o=e.target.parentNode.parentNode;
		}
	}else{
		var o=e.target.parentNode;
	}
	switch(o.id){
		case "pre":{
			pre();
			break;
		}
		case "play":{
			if(audio.paused)audio.play();
			break;
		}
		case "pause":{
			if(!audio.paused)audio.pause();
			break;
		}
		case "next":{
			next();
			break;
		}
		case "songlist":{
			if(playlistdom.style.bottom=="0px"){
				playlistdom.style.bottom="-240px";
			}else{
				playlistdom.style.bottom="-0px";
			}
			break;
		}
		case "playmode":{
			playoption.mode++;
			if(playoption.mode==4)playoption.mode=0;
			setmode(playoption.mode);
			break;
		}
	}
});

function next(){
	switch(playoption.mode){
		case 0:{
			if(playlist[playingid+1]){
				playmusic(playingid+1);
			}
			break;
		}
		case 1:{
			playmusic(playingid);
			break;
		}
		case 2:{
			playmusic(rand(0,playlist.length-1));
			break;
		}
		case 3:{
			if(playlist[playingid+1]){
				playmusic(playingid+1);
			}else{
				playmusic(0);
			}
			break;
		}
	}
}
function pre(){
	switch(playoption.mode){
		case 0:{
			if(playlist[playingid-1]){
				playmusic(playingid-1);
			}
			break;
		}
		case 1:{
			audio.seek(0);
			break;
		}
		case 2:{
			playmusic(rand(0,playlist.length-1));
			break;
		}
		case 3:{
			if(playlist[playingid-1]){
				playmusic(playingid-1);
			}else{
				playmusic(playlist.length-1);
			}
			break;
		}
	}
}




//以较低频率定时获取波形
setInterval(function(){
	analyser.getByteTimeDomainData(wavelayer.wavearray);
},1000/50);

codingcircle.setZoom(5);

(function draw(){
	beforedraw();//执行绘制前函数
	COL.draw();//绘制
	requestAnimationFrame(draw);//预订动画帧
})();


var launched=false;
//若歌曲不在播放且在前台，就定时打节奏
setInterval(function(){
	if(launched&&!document.hidden&&audio.paused)suo();
},1100);

}


//无操作隐藏界面
var oprcd=6;
setInterval(function(){
	if(oprcd==-1){
		ctrls.hide();
	}
	oprcd--;
},1000);

var ctrls={
	hide:function(){
		playlistdom.style.opacity=0;
		sourceinputdom.style.opacity=0;
	},
	show:function(){
		playlistdom.style.opacity=1;
		sourceinputdom.style.opacity=1;
	}
}
window.addEventListener("mousemove",function(){
	oprcd=6;
	ctrls.show();
});
window.addEventListener("mousedown",function(){
	oprcd=6;
	ctrls.show();
});

//设置
window.addEventListener("load",function(){
	if(window.setmode)setmode(0);
});

window.addEventListener("dragover",function(e){
	e.dataTransfer.dropEffect="copy";
	e.stopPropagation();
	e.preventDefault();
	//oprcd=6;
	//ctrls.show();
});
window.addEventListener("drop",function(e){
	e.dataTransfer.dropEffect="copy";
	e.stopPropagation();
	e.preventDefault();
	var p=false;
 	if(playlist.length===0)p=true;
 	loadfilelist(e.dataTransfer.files);
 	if(p){playmusic(0);}
},false);

//处理窗口变化时部件的动作
window.addEventListener("resize",function(){
	w=window.innerWidth;
	h=window.innerHeight;
	COL.adjustcanvas(w,h);
	cr=(w>h?h:w)/2;
	codingcircle.setR(cr*0.75);
	codingcircle.borderWidth=cr*0.08;
	backcircle.setR(cr*0.86);
	startemplate.r=cr*0.026;
	pinlvbartemple.y=codingcircle.r;
	pinlvbartemple.rotatecenter.y=-codingcircle.r;
	pinlvbartemple.width=codingcircle.r*2*3.141592653/138;
	text.lineHeight = text.fontSize=cr*0.36;
	text.prepareText();
	sidecircletemple.r=cr*0.39;
	sidecircletemple.width=sidecircletemple.height=cr*0.39*2;
});


//在线人数
function _(type, data) {
	return JSON.stringify({
		type: type,
		data: data
	});
}
var socket,pinginterval;
(function newconnection(){
	if(socket&&socket.readyState!==3){return;}else{delete socket;}
	socket = new WebSocket("wss://luojia-online.daoapp.io");
	//socket = new WebSocket("ws://127.0.0.1:9888");
	socket.ping=function(){
		socket.send("1");
	}
	socket.onopen = function(data) {
		socket.send(_("url","project://audio-visualization"));
		if(pinginterval)clearInterval(pinginterval);
		pinginterval=setInterval(socket.ping,30000);
		
	};
	socket.onmessage = function(data) {
		if(typeof data=="string"&&data==="1")return;
		try{
			var msg = JSON.parse(data.data);
		}catch(e){
			return;
		}
		switch (msg.type) {
		case "ol":
			{
				online.innerHTML=msg.data;
				break;
			}
		}
		
	};
	socket.onerror = function(data) {
		delete socket;
		setTimeout(newconnection,3000);
		online.innerHTML=0;
	};
	socket.onclose = function(data) {
		delete socket;
		setTimeout(newconnection,3000);
		online.innerHTML=0;
	};
})();

</script>
  
  
  
<%-- <%@ include file="/include/footer.jsp"%> --%>