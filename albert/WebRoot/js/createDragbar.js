var  createDragbar = function  ($dom , fun_for_mousemove, fun_for_mouseup , opts) {

			//fun_for_mouseup : function: while mouseup after dragging the drag-button
			
			opts = $.extend(
				{
					width: '285px',
					bar_height: '6px',
					margin: '6px 0',
					left_bg: '#74818b',
					button_bg: '#5a656e',
					right_bg: '#c7c7c7',
					dir: 'x'
				}
				, opts);

			opts.buttoon_size = parseInt(opts.bar_height) + 2 * parseInt(opts.margin) +'px';

			var $html = $('<div><p></p><a></a><p></p></div>');

			var e_mousemove_active = false;		//judge for mouseup

			if(opts.dir !== 'x' && opts.dir !== 'X'){

				 	$html.first().css({

						'-webkit-transform': 'rotate(90deg)',
						'-moz-transform': 'rotate(90deg)',
						'-ms-transform': 'rotate(90deg)'
				 	});
			}

			$html.addClass('clearfix').css({

					'position': 'relative',
					'display': 'inline-block'
				})

			.children('a').css({

				width: opts.buttoon_size,
				height: opts.buttoon_size,
				display: 'inline-block',
				position: 'absolute',
				top:0,
				left:0,
				'border-radius': '100%',
				'background-color': opts.button_bg

			}).on('mousedown', function (e) {

				var left_offset;

				var $that = $(this);

				e_mousemove_active = true;

				if(opts.dir != 'x' && (opts.dir != 'X')){

				 	left_offset =  -e.pageY + parseInt($(this).css('left'));
				 }

				else{

					left_offset = e.pageX - parseInt($(this).css('left'));
				}

				$(window).on('mousemove.drag_moving', function (e) {
					
					var left_reset;

					if(opts.dir != 'x' && (opts.dir != 'X')){

					 	left_reset = e.pageY + left_offset;
					 }

					else{

						left_reset = e.pageX - left_offset;
					}

						if(left_reset < 0){

							left_reset = 0;
						}

						var max_left = parseInt(opts.width)-parseInt(opts.buttoon_size);

						if(left_reset > max_left ){

							left_reset  = max_left;
						}
				
							bodySelect(false);

						$that.css({

							left: left_reset+'px',
						});

						$that.prev().width(left_reset);

						$that.next().width(parseInt(opts.width)-left_reset);

						fun_for_mousemove();

				}).on('mouseup', function () {

						if(!e_mousemove_active){

							return ;
						}

						$(this).off('.drag_moving');

						fun_for_mouseup();

						bodySelect(true);

						e_mousemove_active = false;

				});
				
			}).siblings('p').css({

				float: 'left',
				height: opts.bar_height

			});

			$html.children('p').css('margin', opts.margin)

			//		can add other ClassName using for more stylesheet

				.first().css({

						'background-color': opts.left_bg,
						'border-radius': '55px 0 0 55px'
					})
				.mousedown(function(e) {

					var $dragbtn = $(this).siblings('a')

					var half_dragbtn = $dragbtn.outerWidth() / 2 ; 

					if (e.offsetX < half_dragbtn) {	//not over the outer_width_left

						e.offsetX = half_dragbtn;
					}

					$dragbtn.css('left', parseInt($dragbtn[0].style.left) - ( $(this).outerWidth() - e.offsetX ) - half_dragbtn +'px')
					.next('p').css('width',$dragbtn.next('p').outerWidth() + ( $(this).outerWidth() - e.offsetX ) + half_dragbtn);
					
					$(this).css('width',e.offsetX - half_dragbtn);

					fun_for_mousemove();
					fun_for_mouseup();

				})

				.siblings('p').css({

					'background-color': opts.right_bg,
					'width': opts.width,
					'border-radius': '0 55px 55px 0'

				}).mousedown(function(e) {

					var $dragbtn = $(this).siblings('a')

					var half_dragbtn = $dragbtn.outerWidth() / 2 ; 

					//not over the outer_width_right
					if (e.offsetX > $dragbtn.next().outerWidth() - half_dragbtn) {

						e.offsetX = $dragbtn.next().outerWidth() - half_dragbtn;
					}

					$(this).css('width',$(this).outerWidth()-e.offsetX + half_dragbtn);

					$dragbtn.css('left', parseInt($dragbtn[0].style.left) + e.offsetX - half_dragbtn +'px')
					.prev('p').css('width', $dragbtn.prev('p').outerWidth() + e.offsetX - half_dragbtn)

					fun_for_mousemove();
					fun_for_mouseup();

				});

			$dom.append($html);

		}