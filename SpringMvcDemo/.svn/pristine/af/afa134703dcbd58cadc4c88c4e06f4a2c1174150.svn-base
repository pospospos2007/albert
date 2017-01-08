(function($){
	var bigColorPicker = new function(){
		var currentPicker = null; //当前绑定拾取器的对象
		var allColorArray = [];//整个颜色区域分为前两列，后面所有列分为6个块，计算出各个区域的数组后放入一个总的二维数组里
		var sideLength = 6;//六个区域的每个边长
		this.init = function(){
			$("body").append('<div id="bigpicker" class="bigpicker"><ul class="bigpicker-bgview-text" ><li><div id="bigBgshowDiv"></div></li><li><input id="bigHexColorText" size="7" maxlength="7" value="#000000" /></li></ul><div id="bigSections" class="bigpicker-sections-color"></div><div id="bigLayout" class="biglayout" ></div></div>');
			
			$("#bigLayout").hover(function(){
				$(this).show();
			},function(){
				$(this).hide();
			}).click(function(){
				$("#bigpicker").hide();
			});
			
			//颜色拾取器text输入框注册事件
			$("#bigHexColorText").keypress(function(){
				var text = $.trim($(this).val());
				$(this).val(text.replace(/[^A-Fa-f0-9#]/g,''));
				if(text.length <= 0)return;
				text = text.charAt(0)=='#'?text:"#" + text;
				var countChar = 7 - text.length;
				if(countChar < 0){
					text = text.substring(0,7);
				}else if(countChar > 0){
					for(var i=0;i<countChar;i++){
						text += "0";
					};
				}
				$("#bigBgshowDiv").css("backgroundColor",text);
			}).keyup(function(){
				var text = $.trim($(this).val());
				$(this).val(text.replace(/[^A-Fa-f0-9#]/g,''));	
			}).focus(function(){
				this.select();
			});	
			
			//单击页面选择器以外的地方时选择器隐藏
			$(document).bind('mousedown',function(event){
				if(!($(event.target).parents().andSelf().is('#bigpicker'))){
					$("#bigpicker").hide();
				}
			});			
		};
		
		/*
		 * callback:可以是undefined（不传参数时）、字符串、函数
		 * 		undefined：把选择的颜色设置到绑定bigColorpicker的元素的value上。
		 * 		字符串：把选择的颜色设置到id为"字符串"的元素的value上。
		 * 		函数：执行传入的函数以实现自定义的获取颜色后的动作。
		 * engine:可以是undefined、p(或P)、l(或L)，
		 * 		在bigColorpicker展现颜色选择区域小格时有两种实现方式：
		 *     一、是一张背景图片，采用光标定位的方式获取颜色。p(或P)
		 *     二、每个小格就是一个li，设置li的背景颜色。l(或L)
		 *     实现方式的不同，效率有所差异，可以自己在使用时选择，默认是p(或P)。
		 *     undefined（不传参数时）：使用默认p(或P)
		 * sideLength:设置颜色区域的大小取值范围为2～10，默认为6,只有engine为L时才生效
		 */
		this.showPicker = function(callback,engine,sideLength_){
			
			if($("body").length > 0 && $("#bigpicker").length <= 0){
				bigColorPicker.init();	
			}
			
			var sl_ = parseInt(sideLength_,10);
			if(engine == "L" && !isNaN(sl_) && sl_ >=2 && sl_ <= 10){
				sideLength = sl_;
			}else{
				sideLength = 6;
			}
			
			this.getAllColorArray = function(){
				allColorArray = new Array(sideLength*3 + 2);
				//计算第一列的数组
				var mixColorArray = [];
				var blackWhiteGradientArray = gradientColor(new RGB(0,0,0),new RGB(255,255,255));
				for(var i=0;i<blackWhiteGradientArray.length;i++){
					mixColorArray[i] = blackWhiteGradientArray[i];
				}
				var baseArray = [new RGB(255,0,0),new RGB(0,255,0),new RGB(0,0,255),new RGB(255,255,0),new RGB(0,255,255),new RGB(255,0,255),new RGB(204,255,0),new RGB(153,0,255),new RGB(102,255,255),new RGB(51,0,0)];
				mixColorArray = mixColorArray.concat(baseArray.slice(0, sideLength));
				allColorArray[0] = mixColorArray;
				
				//计算第二列的数组
				var blackArray = new Array(sideLength*2);
				for(var i=0;i<blackArray.length;i++){
					blackArray[i] = new RGB(0,0,0);
				}
				allColorArray[1] = blackArray;
				
				//计算六个区域的数组
				var cornerColorArray = new Array();//六个元素，每个元素放6个区域的四角颜色二维数组
				cornerColorArray.push(generateBlockcornerColor(0),generateBlockcornerColor(51),generateBlockcornerColor(102),generateBlockcornerColor(153),generateBlockcornerColor(204),generateBlockcornerColor(255));
				var count = 0;
				var halfOfAllArray1 = [];
				var halfOfAllArray2 = [];
				for(var i=0;i<cornerColorArray.length;i++){
					var startArray = gradientColor(cornerColorArray[i][0][0],cornerColorArray[i][0][1]);
					var endArray = gradientColor(cornerColorArray[i][1][0],cornerColorArray[i][1][1]);
					for(var j=0;j<sideLength;j++){
						if(i < 3){
							halfOfAllArray1[count] = gradientColor(startArray[j],endArray[j]);
						}else{
							halfOfAllArray2[count - sideLength*3] = gradientColor(startArray[j],endArray[j]);
							
						}
						count++;
					}
				}
				for(var i=0;i<halfOfAllArray1.length;i++){
					allColorArray[i + 2] = halfOfAllArray1[i].concat(halfOfAllArray2[i]);
				}
				
				//将数组里所有的RGB颜色转换成Hex形式
				for(var i=0;i<allColorArray.length;i++){
					for(var j=0;j<allColorArray[i].length;j++){
						allColorArray[i][j] = RGBToHex(allColorArray[i][j]);
					}
				}
			};
			
			this.getAllColorArray();
			
			$(this).data("bigpickerCallback",callback);
			
			if(engine){
				try{
					engine = engine.toUpperCase();
				}catch(e){
					engine = "P";
				}
			}
			
			if(engine == "L" ){
				$("#bigSections").unbind("mousemove").unbind("mouseout").removeClass("bigpicker-bgimage");
				var ulArray = new Array();
				for(var i=0;i<sideLength*3 + 2;i++){
					ulArray.push("<ul>");
					for(var j=0;j<sideLength*2;j++){
						ulArray.push("<li data-color='" + allColorArray[i][j] + "' style='background-color: " + allColorArray[i][j] + ";' ></li>");
					}
					ulArray.push("</ul>");
				}
				$("#bigSections").html(ulArray.join(""));
				var minBigpickerHeight = 90;
				var minBigpickerWidth = 129;
				var minSectionsHeight = minBigpickerHeight - 29;
				var minSectionsWidth = minBigpickerWidth - 1;
				
				var defaultSectionsWidth = (sideLength*3 + 2)*11 + 1;
				if(defaultSectionsWidth < minSectionsWidth){
					$("#bigSections li,#bigLayout").width(minSectionsWidth/(sideLength*3 + 2) - 2)
						    			.height(minSectionsHeight/(sideLength*2) - 1);
					$("#bigpicker").height(minBigpickerHeight).width(minBigpickerWidth);
					$("#bigSections").height(minSectionsHeight).width(minSectionsWidth);
				}else{
					$("#bigSections").width(defaultSectionsWidth)
					                 .height(sideLength*2*11);
					$("#bigpicker").width(defaultSectionsWidth + 5)
					               .height(sideLength*2*11 + 29);
				}
				
				$("#bigSections ul").find("li:last").css("border-bottom","1px solid #000000");
				$("#bigSections ul:last li").css("border-right","1px solid #000000");
				
				$("#bigSections li").hover(function(){
					var $this = $(this);
					$("#bigLayout").css({"left":$this.position().left,"top":$this.position().top}).show();
					var cor = $this.attr("data-color");
					$("#bigBgshowDiv").css("backgroundColor",cor);
					$("#bigHexColorText").val(cor);
					invokeCallBack(cor);				
				},function(){
					$("#bigLayout").hide();
				});
			}else{
				//预加载图片
			     var bgmage = new Image();
			     bgmage.src = "";
			     //初始化为默认样式
				$("#bigSections").height(134).width(222).addClass("bigpicker-bgimage").empty();
				$("#bigpicker").width(227).height(163);
				//P模式时鼠标在颜色小格上移动时获取颜色
				$("#bigSections").unbind("mousemove").unbind("mouseout").mousemove(function(event){
					var xSections = getSections(sideLength*3 + 2);
					var ySections = getSections(sideLength*3);
					var $this = $(this);
					var cursorXPos = event.pageX - $this.offset().left;
					var cursorYPos = event.pageY - $this.offset().top;
					var xi = 0;
					var yi = 0;
					for(var i=0;i<(sideLength*3+2);i++){
						if(cursorXPos >= xSections[i][0] && cursorXPos <= xSections[i][1]){
							xi = i;
							break;
						}
					}
					for(var i=0;i<(sideLength*3);i++){
						if(cursorYPos >= ySections[i][0] && cursorYPos <= ySections[i][1]){
							yi = i;
							break;
						}
					}
					$("#bigLayout").css({"left":$this.position().left + xi*11,"top":$this.position().top + yi*11}).show();
					var hex = allColorArray[xi][yi];
					$("#bigBgshowDiv").css("backgroundColor",hex);
					$("#bigHexColorText").val(hex);
					
					invokeCallBack(hex);
					
				}).mouseout(function(){
					$("#bigLayout").hide();
				});			
			}
			
			//给传入的拾取颜色的元素绑定click事件，显示颜色拾取器
			$(this).bind("click",function(event){
				currentPicker = event.currentTarget;
				$("#bigBgshowDiv").css("backgroundColor","#000000");
				$("#bigHexColorText").val("#000000");
				
				var pos = calculatePosition ($(currentPicker));
				$("#bigpicker").css({"left":pos.left + "px","top":pos.top + "px"}).fadeIn(300);
				
				var bigSectionsP = $("#bigSections").position();
				$("#bigLayout").css({"left":bigSectionsP.left,"top":bigSectionsP.top}).show();
				
			});			
		};
		
		this.hidePicker = function(){
			$("#bigpicker").hide();
		};
		
		this.movePicker = function(){
			var pos = calculatePosition ($(currentPicker));
			$("#bigpicker").css({"left":pos.left + "px","top":pos.top + "px"});
			$("#bigLayout").hide();
		};
		
		
		//计算出拾取器层的left,top坐标
		function calculatePosition ($el) {
			var offset = $el.offset();
			var compatMode = document.compatMode == 'CSS1Compat';
			var w = compatMode ? document.documentElement.clientWidth : document.body.clientWidth;
			var h = compatMode ? document.documentElement.clientHeight : document.body.clientHeight;
			var pos = {left:offset.left,top:offset.top + $el.height() + 7};
			var $bigpicker = $("#bigpicker");
			if(offset.left + $bigpicker.width() > w){
				pos.left = offset.left - $bigpicker.width() - 7;
				if(pos.left < 0){
					pos.left = 0;
				}						
			}
			if(offset.top + $el.height() + 7 + $bigpicker.height() > h){
				pos.top = offset.top -  $bigpicker.height() - 7;
				if(pos.top < 0){
					pos.top = 0;
				}
			}
			return pos;
		}		
		
		//创建小区域的四个角的颜色二维数组
		function generateBlockcornerColor(r){
			var a = new Array(2);
			a[0] = [new RGB(r,0,0),new RGB(r,255,0)];
			a[1] = [new RGB(r,0,255),new RGB(r,255,255)];			
			return a;
		}

		//两个颜色的渐变颜色数组
		function gradientColor(startColor,endColor){
			var gradientArray = [];
			gradientArray[0] = startColor;
			gradientArray[sideLength-1] = endColor;
			var averageR = Math.round((endColor.r - startColor.r)/sideLength);
			var averageG = Math.round((endColor.g - startColor.g)/sideLength);
			var averageB = Math.round((endColor.b - startColor.b)/sideLength);
			for(var i=1;i<sideLength-1;i++){
				gradientArray[i] =  new RGB(startColor.r + i*averageR,startColor.g + i*averageG,startColor.b + i*averageB);
			}			
			return gradientArray;
		}
		/*获取一个二维区间数组比如[0,11],[12,23],[24,37]..
		 * sl:区间的长度
		*/
		function getSections(sl){
			var sections = new Array(sl);
			var initData = 0; 
			for(var i=0;i<sl;i++){
				var temp = initData + 1;
				initData += 11;
				sections[i] = [temp,initData];
			}			
			return sections;
		}
		
		function RGBToHex(rgb){
			var hex = "#";
			for(c in rgb){
				var h = rgb[c].toString(16).toUpperCase();
				if(h.length == 1){
					hex += "0" + h;
				}else{
					hex += h;
				}
			}
			return hex;
		}
		
		//RGB对象函数，用于创建一个RGB颜色对象
		function RGB(r,g,b){
			this.r = Math.max(Math.min(r,255),0);
			this.g = Math.max(Math.min(g,255),0);
			this.b = Math.max(Math.min(b,255),0);
		}
		
		function invokeCallBack(hex){
			var callback_ = $(currentPicker).data("bigpickerCallback");
			if($.isFunction(callback_)){
				callback_(currentPicker,hex);
			}else if(callback_ == undefined || callback_ == ""){
				$(currentPicker).val(hex);
			}else{
				if(callback_.charAt(0) != "#"){
					callback_ = "#" + callback_;
				}
				$(callback_).val(hex);
			}			
		}
				
	};
	$.fn.bigColorpicker = bigColorPicker.showPicker;
	$.fn.bigColorpickerMove = bigColorPicker.movePicker; //使用在拖拽的浮层上，在拖拽时拾取器随浮层移动位置
	$.fn.bigColorpickerHide = bigColorPicker.hidePicker; //对应一些特定的应用需要手动隐藏拾取器时使用。比如拖拽浮层时隐藏拾取器
})(jQuery);