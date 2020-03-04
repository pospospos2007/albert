/*
By:zihan
URL:http://www.zi-han.net/
Version: 3.0
*/
;(function ($) {
    $.fn.extend({
        textSearch:function(str,options){
        var defaults = {
            divFlag: true,
            divStr: " ",
            markClass: "",
            markColor: "red",
            nullReport: true,
            callback: function(){
                return false;
            }
        };
        var sets = $.extend({}, defaults, options || {}), clStr;
        if(sets.markClass){
            clStr = "class='"+sets.markClass+"'";
        }else{
            clStr = "style='color:"+sets.markColor+";'";
        }
        $("span[rel='mark']").removeAttr("class").removeAttr("style").removeAttr("rel");
        $.regTrim = function(s){
            var imp = /[\^\.\\\|\(\)\*\+\-\$\[\]\?]/g;
            var imp_c = {};
            imp_c["^"] = "\\^";
            imp_c["."] = "\\.";
            imp_c["\\"] = "\\\\";
            imp_c["|"] = "\\|";
            imp_c["("] = "\\(";
            imp_c[")"] = "\\)";
            imp_c["*"] = "\\*";
            imp_c["+"] = "\\+";
            imp_c["-"] = "\\-";
            imp_c["$"] = "\$";
            imp_c["["] = "\\[";
            imp_c["]"] = "\\]";
            imp_c["?"] = "\\?";
            s = s.replace(imp,function(o){
                return imp_c[o];
            });
            return s;
        };
        $(this).each(function(){
            var t = $(this);
            str = $.trim(str);
            if(str === ""){
                return false;
            }else{
                var arr = [];
                if(sets.divFlag){
                    arr = str.split(sets.divStr);
                }else{
                    arr.push(str);
                }
            }
            var v_html = t.html();
            v_html = v_html.replace(/<!--(?:.*)\-->/g,"");
            var tags = /[^<>]+|<(\/?)([A-Za-z]+)([^<>]*)>/g;
            var a = v_html.match(tags), test = 0;
            $.each(a, function(i, c){
                if(!/<(?:.|\s)*?>/.test(c)){
                    $.each(arr,function(index, con){
                        if(con === ""){return;}
                        var reg = new RegExp($.regTrim(con), "g");
                        if(reg.test(c)){
                            c = c.replace(reg,"♂"+con+"♀");
                            test = 1;
                        }
                    });
                    c = c.replace(/♂/g,"<span rel='mark' "+clStr+">").replace(/♀/g,"</span>");
                    a[i] = c;
                }
            });
            var new_html = a.join("");
            $(this).html(new_html);
            if(test === 0 && sets.nullReport){
                return false;
            }
            sets.callback();
        });
    }
    });
})(jQuery);

