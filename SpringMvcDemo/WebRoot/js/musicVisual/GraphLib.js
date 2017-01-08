function getGraphlib(lib){
var Glib={};
Glib.Graph=[];
Glib.SetCanvasLib=function(lib){
	if(lib)
	this.lib=lib;
};
Glib.SetCanvasLib(lib);
Glib.getGraphObj=function(type,optionjson){
	if(!this.lib){console.log("未设置绘图库");return false;}
	if(!Glib.Graph[type]){console.log("没有此图形:"+type);return false;}
	return Glib.Graph[type](optionjson);
}
Glib.Graph['star']=function(optionjson){
	var g=GraphPrototype.star.New(true,true);
	if(optionjson){
		for(op in optionjson){
			g[op]=optionjson[op];
		}
	}
	g.width=2*g.r;
	g.height=2*g.r;
	return g;
};

Glib.Graph['arc'] = function(optionjson) {
	var g = GraphPrototype.arc.New(true,true);
	if(optionjson){
		for(op in optionjson){
			g[op]=optionjson[op];
		}
	}
	g.width = 2 * g.r;
	g.height = 2 * g.r;
	return g;
};
Glib.Graph['circle'] = function(optionjson) {
	var g = GraphPrototype.circle.New(true,true);
	if(optionjson){
		for(op in optionjson){
			g[op]=optionjson[op];
		}
	}
	g.width = 2 * g.r;
	g.height = 2 * g.r;
	return g;
};
Glib.Graph['rect'] = function(optionjson) {
		var g = GraphPrototype.rect.New(true,true);
		if(optionjson){
			for(op in optionjson){
				g[op]=optionjson[op];
			}
		}
		return g;
	};
var GraphPrototype={
	circle:new function(){
		var g = Glib.lib.Graph.New();
		g.r = 10;
		g.graphFun = function(ct) {
			ct.beginPath();
			ct.moveTo(this.r+this.r,this.r);
			ct.arc(this.r, this.r, this.r, 0, 2*Math.PI, false);
			ct.closePath();
		}
		g.setR = function(r) {
			this.r = r;
			this.width = 2 * r;
			this.height = 2 * r;
		}
		g.drawfunction = function(ct,color) {
			ct.fillStyle = color||this.fillColor|| "#66CCFF";
			if(!color&&this.borderWidth){
				ct.strokeStyle = this.borderColor || "#000";
				ct.lineWidth =this.borderWidth|| 0;
			}
			this.graphFun(ct);
			ct.fill();
			if(!color&&this.borderWidth){
				ct.stroke();
			}
		}
		g.eventRange=g.drawfunction;
		return g;
	},
	arc:new function(){
		var g = Glib.lib.Graph.New();
		g.r = 10;
		g.startAngle=0;
		g.endAngle=2*Math.PI;
		g.anticlockwise=false;
		g.graphFun = function(ct) {
			ct.beginPath();
			ct.moveTo(this.r+this.r*Math.cos(this.startAngle),this.r+this.r*Math.sin(this.startAngle));
			ct.arc(this.r, this.r, this.r, this.startAngle, this.endAngle, this.anticlockwise);
			ct.closePath();
		}
		g.setR = function(r) {
			this.r = r;
			this.width = 2 * r;
			this.height = 2 * r;
		}
		g.drawfunction = function(ct,color) {
			ct.fillStyle = color||this.fillColor|| "#66CCFF";
			if(!color&&this.borderWidth){
				ct.strokeStyle = this.borderColor || "#000";
				ct.lineWidth =this.borderWidth|| 0;
			}
			this.graphFun(ct);
			ct.fill();
			if(!color&&this.borderWidth){
				ct.stroke();
			}
		}
		g.eventRange=g.drawfunction;
		return g;
	},
	rect:new function(){
		var g = Glib.lib.Graph.New();
		g.width =50;
		g.height =50;
		g.iffill = true;
		g.graphFun = function(ct) {
			ct.rect(0, 0, this.width, this.height);
		}
		g.drawfunction = function(ct,color) {
			this.graphFun(ct);
			if (this.iffill||color) {
				ct.fillStyle  = color||this.fillColor|| "#000";
				ct.fill();
			}
			if(!color&&this.borderWidth  > 0) {
				ct.strokeStyle = this.borderColor|| "#000";
				ct.lineWidth = this.borderWidth ;
				ct.stroke();
			}
		}
		g.eventRange=g.drawfunction;
		return g;
	},
	star:new function(){
		var g=Glib.lib.Graph.New();
		g.r=10;
		g.width=2*g.r;
		g.height=2*g.r;
		g.graphFun=function(ct,color){
			ct.rotate(Math.PI/2*3);
			ct.beginPath();
			ct.fillStyle =color||this.color||"#000";
			ct.moveTo(this.r, 0);
			for (var i = 0; i < 9; i++) {
				ct.rotate(Math.PI / 5);
				if (i % 2 == 0) {
					ct.lineTo(this.r*0.3819653, 0);
				} else {
					ct.lineTo(this.r, 0);
				}
			}
			ct.closePath();
		}
		g.strokeetR=function(r){
			this.return=r;
			this.width=2*r;
			this.height=2*r;
		}
		g.drawfunction=function(ct,color){
			this.graphFun(ct,color);
			ct.fill();
		}
		g.eventRange=g.drawfunction;
		return g;
	}
};

return Glib;
}


