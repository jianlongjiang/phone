(function($) {
    var a, b;
    $.uaMatch = function(a) {
        a = a.toLowerCase();
        var b = /(chrome)[ \/]([\w.]+)/.exec(a) || /(webkit)[ \/]([\w.]+)/.exec(a) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(a) || /(msie) ([\w.]+)/.exec(a) || a.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(a) || [];
        return {
            browser: b[1] || "",
            version: b[2] || "0"
        }
    },
    a = $.uaMatch(navigator.userAgent),
    b = {},
    a.browser && (b[a.browser] = !0, b.version = a.version),
    b.chrome ? b.webkit = !0 : b.webkit && (b.safari = !0),
    $.browser = b,
    $.sub = function() {
        function a(b, c) {
            return new a.fn.init(b, c)
        }
        $.extend(!0, a, this),
        a.superclass = this,
        a.fn = a.prototype = this(),
        a.fn.constructor = a,
        a.sub = this.sub,
        a.fn.init = function c(c, d) {
            return d && d instanceof p && !(d instanceof a) && (d = a(d)),
            $.fn.init.call(this, c, d, b)
        },
        a.fn.init.prototype = a.fn;
        var b = a(e);
        return a
   };
})(jQuery);