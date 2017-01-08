+function(Handlebars, app) { "use strict";
  if ( !Handlebars ) {
    return;
  }

  Handlebars.registerHelper( 'rownum', function( index, page ) {
    return page.number * page.size + index + 1;
  });
  Handlebars.registerHelper( 'eq', function( value1, value2 ) {
    return value1 == value2;
  });
  Handlebars.registerHelper( 'number', function( value ) {
    return value === undefined ? 0: value;
  });
  Handlebars.registerHelper( 'number_add', function( number1, number2 ) {
    return (number1 + number2);
  });
  Handlebars.registerHelper( 'number_multiply', function( number1, number2 ) {
    return (number1 * number2);
  });
  Handlebars.registerHelper( 'number_divide', function( number1, number2 ) {
	  return (number1 / number2);
  });
  Handlebars.registerHelper( 'format_currency', function( value ) {
    return app.currencySymbol + value.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
  });
  Handlebars.registerHelper( 'format_date', function( value ) {
    return value ===  undefined ? '': value.substr(0, value.indexOf('T'));
  });
  Handlebars.registerHelper( 'to_fixed', function( value ) {
	  return value ===  undefined ? 0: value.toFixed(0);
  });
  Handlebars.registerHelper( 'to_fixed2', function( value ) {
	  return value ===  undefined ? 0: value.toFixed(2);
  });
  Handlebars.registerHelper( 'unit_option', function( value ) {
	  var res = '月';
	  switch(value)
	  {
	  case 1:
		res = '天'
	    break;
	  case 2:
		  res = '周'
			  break;
	  case 3:
		  res = '月'
			  break;
	  case 4:
		res = '年'
	    break;
	  }
	  return res;
  });
  
  Handlebars.registerHelper ("text_helper", function (title ,size) {
	 if(title.length > size){
		 title = title.substr(0,size) +"..."
	 }
	return title;
  });
  Handlebars.registerHelper( 'isTwo', function( value ) {
	   return value == 2 ? true : false;
	  });
  Handlebars.registerHelper( 'isNotTwo', function( value ) {
	   return value != 2 ? true : false;
	  });
  
  Handlebars.registerHelper( 'isEqual', function( value1,value2 ) {
	   return value1 == value2 ? true : false;
	  });
  
  Handlebars.registerHelper( 'isNotEqual', function( value1,value2 ) {
	   return value1 != value2 ? true : false;
	  });
  
}(window.Handlebars, window.app);

+function($, app) { "use strict";
//翻页
	$.fn.extend({
	　　appPagination:function(){
		var elem = this;
		elem.find('.pagination [data-page-number]').each(function() {
			$(this).click(function(){
				 var pageNumber = $(this).data('pageNumber')
	      		 elem.find('[name="currentPage"]').val(pageNumber);
				 elem.submit();
				 return false;
			});
		 });
	   }
	}); 	

}(window.jQuery, window.app);



+function($, app) { "use strict";
  app.buildPage = function( data ) {
    var page = {}, start, end;
    page.size = data.pageSize;
    page.content = data.voList;
    page.pages = [];
    if ( data.currentPage ) {
      page.number = data.currentPage - 1;
      page.totalPages = data.pageCount;
      page.firstPage = (data.currentPage == 1);
      page.lastPage = (data.currentPage == data.pageCount);
      
      start = Math.max(0, page.number - app.displayPages / 2);
      end = Math.min(Math.max(page.number + app.displayPages / 2 - 1, app.displayPages - 1), page.totalPages - 1);
      page.visible = (end-start > 0);
      for( var i = start; i <= end; i++ ) {
        page.pages.push(i);
      }
    } else {
      page.number = 0;
      page.totalPages = 0;
      page.firstPage = true;
      page.lastPage = true;
    }

    return page;
  };

}(window.jQuery, window.app);



+function($, app) { "use strict";

  $.fn.appGrid = function(options) {

    options = $.extend({}, $.fn.appGrid.defaults, options);

    return this.each(function() {
      render($(this), options);
    });
  };

  $.fn.appGrid.defaults = {
  };

  function render(elem, options) {
    //
    // 选中
    //
    elem.find('input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
    });
    elem.find('input[data-action="chk-all"]').on('ifUnchecked', function(event) {
        elem.find('input[type="checkbox"]').iCheck('uncheck');
    });
    elem.find('input[data-action="chk-all"]').on('ifChecked', function(event) {
        elem.find('input[type="checkbox"]').iCheck('check');
    });

    //
    // 翻页
    //
    var listform = elem.find('form[name="listform"]');
    elem.find('.pagination [data-page-number]').click(function() {
      listform.find('[name="currentPage"]').val($(this).data('currentPage'));
      listform.submit();
      return false;
    });
  }

}(window.jQuery, window.app);



