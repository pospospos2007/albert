function beDragable ($dom){

	var _zindex = 10000;

	var window_width = document.width;

	$dom.mousedown(function(e){

		var $that = $(this).parent();

		if($that.css('position')==='static'){

			$that.css({

			'position':'relative',
			'top':'0',
			'left':'0',
			'z-index':_zindex

			})
		}

		else{

			if($that.css('left') === 'auto'){

				$that.css('left', '0');
			}

			if($that.css('top') === 'auto'){

				$that.css('top', '0');
			}
		}
			$that[0].style.zIndex =  ++_zindex;

			e.stopPropagation();

			//console.log($that)

			var ori_left = e.pageX - parseInt($that.css('left'));
			var ori_top = e.pageY - parseInt($that.css('top'));

			$(window).bind('mousemove',function(e){

				$('body').css({'-webkit-user-select': 'none',
					'-moz-user-select': 'none',
					'-ms-user-select': 'none',
					'-o-user-select': 'none'})

				if(e.pageY <= 0){
				   e.pageY = 1;
				}

				if(e.pageX >= window_width){
					e.pageX = window_width;
				}
				//console.log(e.pageX)

				var reset_left = e.pageX - ori_left;
				var reset_top = e.pageY - ori_top;

				//console.log(e.pageY);

				$that.css({'left':reset_left+'px','top':reset_top+'px'});

			}).mouseup(function(){

				$('body').css({'-webkit-user-select': 'text',
					'-moz-user-select': 'text',
					'-ms-user-select': 'text',
					'-o-user-select': 'text'})

				$(window).unbind('mousemove');

			})

	})}