<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
  <script src="js/jquery.js"></script>
	<script src="js/bodySelect.js"></script>
	<script src="js/createDragbar.js"></script>
	<script src="js/beDragable.js"></script>

	<!-- <link rel="stylesheet" href="css/drag-button.css"> -->
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/style2.css">
 	 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    
    <title>MusicBox</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/css/style.css" rel="stylesheet" />
  </head>
  
  	<body>
	<div class="music-player-wrap" style="width:282px">
		<audio id="music-player" src="sound/1.mp3" ></audio>
		<div class="music-volume clearfix">
			<div class="volume-0 left icon"></div>
			<div class="volume-drag left"></div>
			<div class="volume-1 left icon"></div>
		</div>
		<div class="song-logo-wrapper">
				<div class="song-logo icon"></div>
		</div>
		<div class="music-name">1</div>
		<div class="drag-bar-wrapper">
		<div class="drag-bar"></div>
		<div class="time-wrap clearfix">
			<div class="time-played left">00:00</div>
			<div class="time-info right">03:22</div>
		</div>
		</div>
		<div class="music-buttons">
			<div class="play-buttons clearfix">
				<a class="pre-song left icon" title='上一首'>上一首</a>
				<div id="play-button" class="icon left" title="">
					<a class="icon">play</a>
				</div>
				<a class="next-song left icon" title='下一首'>下一首</a>
			</div>
		</div>
		<div class="music-buttons-footer clearfix">
			<a class="icon" id="song-list" title="歌曲列表"></a>
			<!-- <a href="" class="icon" id="play-mode" titie="播放模式"></a> -->
				<div class="icon current-mode" title="顺序播放">
					<ul class="play-mode-ul">
						<li class="play-mode-item icon mode_random" title="随机播放"></li>
						<li class="play-mode-item icon mode_singleloop" title="单曲循环"></li>
						<li class="play-mode-item icon mode_listloop" title="列表循环"></li>
						<li class="play-mode-item icon mode_list" title="顺序播放"></li>
					</ul>
				</div>
			<a class="change-theme icon" title="皮肤"></a>
				<div class="theme-box">
			<h1 class="clearfix">
				<p>更换皮肤</p>
				<a href="javascript:;">x</a>
			</h1>
			<div class="theme-content">
				<ul class="clearfix">
					<li class="theme-item"></li>
					<li class="theme-item"></li>
					<li class="theme-item"></li>
					<li class="theme-item"></li>
				</ul>
			</div>
		</div>
		</div>
	</div>	
	<div id="modal-box"></div>

	<script>


	$(function(){			// for change theme box

		var $theme_head = $('.theme-box').click(function(event) {
			
			 event.stopPropagation();
			 
		}).find('h1')

			$theme_head.on('mouseenter', function () {

				$(this).css('cursor', 'move');

		});

		beDragable($theme_head);

		var player_theme = ['#30556b','#C55F4C','#83BD38','#369EAC'];

		var $theme_item = $('.theme-box .theme-content').find('li');

		$theme_item.each(function(index, el) {

			$(this).css('background-color', player_theme[index])

					.click(function(event) {
						
						$('.music-player-wrap').css('background-color', player_theme[index]);
						
						$('.music-player-wrap .play-mode-ul').css('background-color', player_theme[index]);

					});
		});

		$theme_head.find('a').click(function() {
				
				$theme_head.parent().fadeOut('600');

				$('#modal-box').fadeOut('300');

		});
	})


	$(function  () {
		
		var url_ = 'sound/';

		var music_list = [url_+'1.mp3',url_+'2.mp3',url_+'3.mp3'];

		var music_index = 0;

		var __music_unit;	//__music_unit : every __music_unit seconds,drag button moving right 1px

		var music_len ;

		var time_, time_uadate_auto, music_wait;		//use for settimeinterval

		var $music_name = $('.music-name');

		var $time_info = $('.time-info'); 

		var $time_played = $('.time-played');

		var isplaying = false;

		var $drag_bar = $('.drag-bar');

		var player = $('#music-player')[0];				//audio tag


		var is_music_ok = function(){

			if(player.readyState === 4){

				return true;
			}

			return false;

		}


		var moveVolumebutton = function (){

			var $volume_drag = $('.volume-drag').children('div').children('a');
					
			player.volume = parseInt($volume_drag.css('left'))/
							($volume_drag.next().width()+$volume_drag.prev().width()-$volume_drag.width())
				}

			createDragbar($('.volume-drag'),
						moveVolumebutton,
						function(){},
						{width:'221px',
						left_bg: '#4FC797',
						button_bg: '#ffffff',
						right_bg: '#495C5C'}
						);

		var upDragbutton = function (){

			//console.log(player.currentTime)


			var curtime =  parseInt($drag_button.css('left')) * 
										__music_unit/1000;

			if(player.buffered.end(0) < curtime ){

				var left_pos = player.currentTime / (__music_unit/1000);
				var right_pos = player.duration / ( __music_unit/1000) + $drag_button.outerWidth();

					$drag_button.css('left',left_pos+'px').prev('p').outerWidth(left_pos).siblings('p').outerWidth(right_pos-left_pos)
					playInit();

					_time_update(player.currentTime);

				return ;
			}
			player.currentTime = curtime;

			playInit();

			_time_update(player.currentTime);
		}
		var moveDragbutton = function  () {
					
					if(time_){

						clearTimeout(time_);
					}
		}

		createDragbar($drag_bar,moveDragbutton,upDragbutton,{
			width:'221px',
			left_bg: '#62c6c7',
			button_bg: '#ffffff',
			right_bg: '#172525'});

		var $drag_button = $drag_bar.children().children('a');

		var $drag_button_left = $drag_button.prev();

		var $drag_button_right = $drag_button.next();

		var drag_len = $drag_button_right.outerWidth()+
			$drag_button_left.outerWidth() - $drag_button.outerWidth();

		function getModetype() {

		var modetype = 4  // play mode default: order play

			// To add mode button select process.
		if($('.current-mode').attr('title') === '列表循环'){

				modetype = 1 ;

				return modetype;
			}
		else if($('.current-mode').attr('title') === '单曲循环'){

				modetype = 2 ;

				return modetype;

			}
		else if ($('.current-mode').attr('title') === '随机播放') {

				modetype = 3 ;

				return modetype;
				
			}
		else if($('.current-mode').attr('title') === '顺序播放'){

				modetype = 4 ;

				return modetype;
			}

		return modetype;

		}

		var playMode = function(){

			var modetype = getModetype();

			var $next_song = $('#play-button').next()

			var random_num = Math.random() * music_list.length

			random_num = Math.ceil( random_num )

			if(modetype === 1){

				$next_song.trigger('click') //list loop
			}
				
			else if (modetype === 2) {
				
			$('#play-button').trigger('click');   // single loop
			
			}

			else if (modetype === 3){

				for(var i = 0; i < random_num; i++){

					$next_song.trigger('click')				
				}				
			}

			else if(modetype === 4){

				if( $('.playing_song').index() !== music_list.length ){

					$next_song.trigger('click')				
				}

			}

			// console.log('the random number: ' + random_num)

			// $('#play-button').next().trigger('click').trigger('click').trigger('click');


		}

		var switchMusic = function  (music_index,music_name) {

				$music_name.text(music_name);

				player.src=music_list[music_index];
			
				_time_update(0);

				dragInit($drag_button);	
				
				isplaying = true;		

				// $('#play-button').text('pause');

				if(time_){

					clearTimeout(time_);
				}

				if(music_wait){

					clearInterval(music_wait);
				}
			 	
			 	music_wait = setInterval(function  () {

			 		if(!is_music_ok()){
			 			return ;
			 		}
			 		clearInterval(music_wait)
				
				playInit();

				player.play();

			},500)

		}

		var _time_update = function  (time) {
			
			var mins = Math.floor(time / 60); 

			var secs = Math.floor(time - mins * 60) ; 

			var time_played = '0'+ mins +':' + (secs >= 10 ? secs :( '0'+ secs));

			$time_played.text(time_played);
		}

		var dragInit = function  ($dom) {
			
			$dom.css('left', '0');

			var width = $dom.next().outerWidth()+
			$dom.prev().outerWidth();

			$dom.prev().outerWidth('0');

			$dom.next().outerWidth(width);
		}

		var dragMoving = function  (__music_unit) {

			if( parseFloat($drag_button.css('left')) >= drag_len){	//music is over?

				isplaying = false;

				// $('#play-button').text('play');
				$('#play-button').children().css({
				
					'backgroundPositionX':'-131px',
					'left':'45%'

				});

				clearTimeout(time_);

				clearTimeout(time_uadate_auto);

				dragInit($drag_button);

				player.currentTime = 0;

				player.pause();

				_time_update(0);

				playMode();

				return; 				
			} 

			$drag_button_left.outerWidth('+=1');
			
			$drag_button_right.outerWidth('-=1');
			
			$drag_button.css('left',  '+=1px' );
			
		}

		function playInit () {

			if(time_uadate_auto){

				clearTimeout(time_uadate_auto);
			}

			time_uadate_auto = setInterval(function  () {

			_time_update(player.currentTime);

			},1000)

			music_len = player.duration;			//music time length

			var mins = Math.floor(music_len / 60); 

			var secs = Math.floor(music_len - mins * 60) ; 

			var time_data = '0'+ mins +':' + (secs >= 10 ? secs :( '0'+ secs));

			$time_info.text(time_data);

			 __music_unit = music_len / drag_len * 1000 ;

			 if(time_){					//time_ : the timer for every __music_unit seconds update once

			 	clearTimeout(time_);
			 }

			time_ = setInterval(function(){

				dragMoving(__music_unit);

			},__music_unit)

		}

		$('.music-player-wrap .change-theme').click(function() {
					
				$('.theme-box').css({
					'top': '-125px',
					'left': '248px'
				}).fadeIn('600');

				$('#modal-box').fadeIn('300');
		});

		$('#play-button').mousedown(function(event) {

			// if(!is_music_ok()){
				
			// 	return ;
			// }
			
			$(this).css('box-shadow','0 0 5px pink')

		}).mouseup(function(event) {

			$(this).css('box-shadow', 'none');

		}).click(function() {

			if(!is_music_ok()){	//readystate
				
				return ;
			}
			// setInterval(function(){

			// 	console.log(player.buffered.end(0))

			// },1000)

			if(!isplaying){

				player.play();
				
				$(this).children().css({
				
					'background-position':'-170px -89px',
					'left':'40%'

				});
				
				playInit();

				isplaying = !isplaying;
			}

			else{

				player.pause();

				$(this).children().css({
				
					'background-position':'-131px -89px',
					'left':'45%'

				});

				isplaying = !isplaying;				
				
				clearTimeout(time_);
			}
		});

		var reg_music = /[^a-zA-Z0-9\/\:\.]+/;

		var music_name ='';

		var $song_list_ul = $('<ul></ul>').addClass('song-list-ul');

		var $song_list_li = $('<li></li>')

		$song_list_li.clone().text('所有歌曲').addClass('song-list-head').appendTo($song_list_ul);

		$.each(music_list, function(i, val) {

			$song_list_li.clone().text(music_list[i].

				match(reg_music)).on('dblclick', function () {

			$(this).addClass('playing_song').siblings().removeClass('playing_song');

			switchMusic( i , $(this).text() );

			$('#play-button').children().css({
				
				'background-position':'-170px -89px',
				'left':'40%'

			});
			
		}).hover(function() {
					
					$(this).addClass('song-list-items');

				}, function() {
					
					$(this).removeClass('song-list-items');

				}).appendTo($song_list_ul);
		});

		$song_list_ul.find('li:eq(1)').addClass('playing_song');

		$song_list_ul.appendTo('.music-buttons-footer');

		beDragable($('.song-list-head'));

		$('#song-list').click(function(event) {

			$song_list_ul.css({
				'top': '-198px',
				'left': '-213px'
			});
			
			$song_list_ul.show('500');

			$('#modal-box').fadeIn('300');
		});

		$('.play-mode-item').hover(function(e) {
			
			var bg_pos = $(this).css('background-position').split(' ');
			
			var bg_pos_x = parseInt(bg_pos[0]) - 38 + 'px ';

			$(this).css('background-position', bg_pos_x + bg_pos[1]);

			e.stopPropagation();

		}, function(e) {
						
			var bg_pos = $(this).css('background-position').split(' ');
			
			var bg_pos_x = parseInt(bg_pos[0]) + 38 + 'px ';

			$(this).css('background-position', bg_pos_x + bg_pos[1]);

			e.stopPropagation();

		}).click(function(event) {

			event.stopPropagation();

			var bg_pos = $(this).css('background-position').split(' ');
			
			var bg_pos_x = parseInt(bg_pos[0]) + 38 + 'px ';

			$(this).parent().fadeOut(300)

			$('.current-mode').css('background-position', bg_pos_x + bg_pos[1]).attr('title', $(this).attr('title'));

		});
		$('.current-mode').mouseover(function(e) {
			
			var bg_pos = $(this).css('background-position').split(' ');
			
			var bg_pos_x = parseInt(bg_pos[0]) - 38 + 'px ';

			$(this).css('background-position', bg_pos_x + bg_pos[1]);

			e.stopPropagation();
			

		}).mouseout(function(e) {
						
			var bg_pos = $(this).css('background-position').split(' ');
			
			var bg_pos_x = parseInt(bg_pos[0]) + 38 + 'px ';

			$(this).css('background-position', bg_pos_x + bg_pos[1]);

			e.stopPropagation();

		}).click(function(event) {
			
			$(this).children().stop().slideToggle(500)
		});


		// $('.current-mode').click(function(){

		// 	$(this).children().show();
		// })
		$('#modal-box').click(function(event) {

			$('.song-list-ul').fadeOut('500');

			$('.theme-box').fadeOut('500');

			$(this).fadeOut('300');
		});

		$('.pre-song').mousedown(function(event) {
			
			$(this).css('box-shadow','0 0 5px pink')

		}).mouseup(function(event) {

			$(this).css('box-shadow', 'none');
			
			setTimeout(function  () {
				
			bodySelect(true)
				
			},3000)

		}).click(function() {

			bodySelect(false);
			
			music_index = (music_index === 0) ? music_list.length-1 : music_index-1;  

			$('.song-list-ul').find('li:eq('+(music_index+1)+')').trigger('dblclick');
				
		});

		$('.next-song').mousedown(function(event) {
			
			$(this).css('box-shadow','0 0 5px pink')

		}).mouseup(function(event) {

			$(this).css('box-shadow', 'none');
			
			setTimeout(function  () {
				
			bodySelect(true)
				
			},3000)

		}).click(function() {

			bodySelect(false)

			music_index = (music_index === music_list.length-1) ? 0 : music_index+1;  
			
			$('.song-list-ul').find('li:eq('+(music_index+1)+')').trigger('dblclick');

		});

		$('.volume-0').click(function(event) {
			
			dragInit($('.volume-drag').children('div').children('a'))
			 player.volume = 0 ;

		});

		$('.volume-1').click(function(event) {

			var drag_to_full = (function  ($dom) {
			
			var width = $dom.next().outerWidth()+
			$dom.prev().outerWidth() -$dom.outerWidth();

			$dom.css('left', width + 'px');

			$dom.prev().outerWidth(width);

			$dom.next().outerWidth($dom.outerWidth());

		})($('.volume-drag').children('div').children('a'))

			 player.volume = 1 ;			

		});

		var time_loading = setInterval( function() {

			//set autoplay when open,if you want to autoplay,just delete one .trigger
			if(is_music_ok()){

				clearInterval(time_loading);

			$('#play-button').trigger('click').trigger('click')

			}

			console.log(player.readyState)


		},500)

		$('.volume-1').trigger('click');

		// for(var i = 0;i<music_list.length;i++)
		// console.log(music_list[i].match(reg_music));

		})

// console.dir(window)
	</script>
	    
    </body>
    
    
</html>
