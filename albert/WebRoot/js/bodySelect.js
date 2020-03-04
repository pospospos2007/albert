		function bodySelect (t_or_f){

			if(t_or_f === true){

				$('body').css({

					'-moz-user-select': 'text',
					'-webkit-user-select': 'text',
					'-ms-user-select': 'text'	//IE 10

				});
					//IE
				document.body.onselectstart = document.body.ondrag = function(){
	
					    return true;
					}

			}
			if(t_or_f === false){
					
				$('body').css({

					'-moz-user-select': 'none',
					'-webkit-user-select': 'none',
					'-ms-user-selecct': 'none'	//IE 10

				});

				document.body.onselectstart = document.body.ondrag = function(){
	
					    return false;
					}
			}
		}