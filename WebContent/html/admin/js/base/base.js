(function(a) {
    a.fn.hoverClass = function(b) {
        var a = this;
        b = b ? b : 'hover';
        a.each(function(c) {
            a.eq(c).hover(function() {
                $(this).addClass(b)
            }, function() {
                $(this).removeClass(b)
            })
        });
        return a
    };
})(jQuery);

(function($, undefined) {
    var QFC = window.QFC || {};
    QFC.util = QFC.util || {};

    function isType(type) {
        return function(obj) {
            return Object.prototype.toString.call(obj) === "[object " + type + "]"
        }
    }
    $.extend(QFC.util, {
        isArray: Array.isArray || isType("Array"),
        isString: isType("String"),
        sObject: isType("Object"),
        isFunction: isType("Function")
    });

    QFC.util.require = QFC.util.require || function(module, url, callback) {
        if ($.fn['qfc' + module]) {
            return
        }
        if (QFC.util.isFunction(url)) {
            var callback = url;
            var path = QFC.config ? QFC.config.path.js : '../js';
            url = path + '/' + module + '.js';
        }

        var script = document.createElement('script');
        var head = document.getElementsByTagName("head")[0];
        script.type = 'text/javascript';
        script.charset = 'gb2312';
        script.async = true;
        if (script.readyState) { //IE
            script.onreadystatechange = function() {
                if (script.readyState == "loaded" || script.readyState == "complete") {
                    script.onreadystatechange = null;
                    callback();
                    head.removeChild(script);
                }
            };
        } else { //Others
            script.onload = function() {
                callback();
                head.removeChild(script);
            }
        }

        script.src = url;
        head.appendChild(script);
    };

    $(document).ready(function() {
        $('#QFCSlider').length && sliderHandle();
        $('.act-tab').length && tabSwitch();
        $('.act-slide').length && slide();
        $('input[name="keyword"]').length && placeholder($('input[name="keyword"]'));
        $("div[name='zx_fcous_dimg']").length && imageSliderHandle($("div[name='zx_fcous_dimg']"));
        // ��˾��Ʒ��
        $('.pro-photo').length && QFC.util.require('search/slide', function() {
            $('.pro-photo').slide();
        });
        $('.hot-comm').length && QFC.util.require('search/slide', function() {
            $('.hot-comm').slide();
        });

        $('.J_xslider').length && xsliderHandle($('.J_xslider'));
        $('.J_Search_param').length && searchParamHandle($('.J_Search_param'));
        $('.date-limit').length && dateLimitHandle($('.date-limit'));

        $('img.lazyload').length && lazyloadHandle();
        
        //����t�ӹ�Ч��
        if ($("#demo").width() < $("#demo1").width()) {
            var speed = 60; //����Խ���ٶ�Խ��
            var tab = document.getElementById("demo");
            var tab1 = document.getElementById("demo1");
            var tab2 = document.getElementById("demo2");
            tab2.innerHTML = tab1.innerHTML;

            function Marquee() {
                if (tab2.offsetWidth - tab.scrollLeft <= 0)
                    tab.scrollLeft -= tab1.offsetWidth;
                else {
                    tab.scrollLeft++;
                }
            }
            var MyMar = setInterval(Marquee, speed);
            tab.onmouseover = function() {
                clearInterval(MyMar);
            };
            tab.onmouseout = function() {
                MyMar = setInterval(Marquee, speed);
            };
        }
        $("#J_CateList dl").hoverClass();

        $(".J_search_tab").hover(function() {
            $(this).addClass("PAY_search_nav_hover");
            $('li', this).click(function() {
                $(this).addClass("selected").siblings().removeClass("selected").appendTo($(this).parent());
                $(".J_search_tab").removeClass("PAY_search_nav_hover");
                $(".J_PAY_search_input").attr("placeholder", $(this).attr("data-placeholder"));
                $(".J_search_form").attr("action", $(this).attr("data-action"));
            });
        }, function() {
            $(this).removeClass("PAY_search_nav_hover");
        });


        $('#J_SelectAll').length && applyHander();

        $('#J_FPrice').length && (function() {
            var t;
            $('#J_FPrice input')
                .focus(function() {
                    $('#J_FPrice').addClass("fPrice-hover");
                    clearInterval(t);
                }).blur(function() {
                    hide();
                }).keyup(function() {
                    if (!(/^([1-9][0-9]*|0)(\.[0-9]*)?$/.test(this.value))) {
                        this.value = this.value ? this.value.substr(0,(this.value.length-1)) : "";
                    } 
                });
            $("#J_FPCancel").click(function() {
                $(".fP-box input").val('');
            });

            function hide() {
                t = setTimeout(function() {
                    $('#J_FPrice').removeClass("fPrice-hover");
                }, 100)
            }
        })();

        $(".J_Search_wholesale").hoverClass("search-wholesale-hover");

        $(".ol-kf .close").length &&
        $(".ol-kf .close").on('click', function() {
            $(".ol-kf").remove();
        });

    });

    function sliderHandle() {
        QFC.util.require('search/slider', function() {
            $("#QFCSlider").qfcslider();
        });
    }

    function tabSwitch() {
        QFC.util.require('tab', function() {
            $('.act-tab').qfctab();
            $('.act-tab-hover').qfctab("mouseover")
        });
    }

    function placeholder(obj) {
        QFC.util.require('base/placeholder', function() {
            obj.each(function() {
                $(this).qfcplaceholder();
            });
        });
    }

    function imageSliderHandle(obj) {
        QFC.util.require('base/imageSlider', function() {
            obj.each(function(index) {
                $(this).qfcImageSlider()
            });
        });
    }

    function xsliderHandle(obj) {
        if (obj.find("li").length <= 4) return;
        QFC.util.require('base/Xslider', function() {
            obj.each(function() {
                $(this).Xslider({
                    unitdisplayed: 4,
                    numtoMove: 1,
                    unitlen: 223
                });
            });
        });
    }

    function searchParamHandle(obj) {
        $(obj).find(".opt").click(function() {
            var dd = $(this).parent("dd");
            if (dd.hasClass("all")) {
                dd.removeClass("all");
                $(this).html("\u66f4\u591a"); //���
            } else {
                dd.addClass("all");
                $(this).html("\u6536\u8d77"); //����
            }
        });
    }

    function applyHander() {
        QFC.util.require('base/checkApply', function() {});
    }

    function dateLimitHandle(obj) {
        QFC.util.require('datelimit', function() {
            obj.each(function() {
                $(this).qfcdatelimit();
            });
        });
    }
    
	function lazyloadHandle() {
		QFC.util.require('base/jquery.lazyload.min', function(){
			 $("img.lazyload").lazyload({
			 	threshold : 200,
			 	effect : "fadeIn",
			 	skip_invisible : false
			 	// placeholder: "../images/transparent.png"
			 });
		});
	}
})(jQuery)