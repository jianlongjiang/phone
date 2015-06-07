(function($, undefined) {
	function Placeholder(ele) {
		this.ele = $(ele);

		this.init();
	}

	Placeholder.prototype = {
		init: function() {
			var ele = this.ele;
			var placehoder = "";
			if (ele && !("placeholder" in ele[0]) && (placehoder = ele.attr("placeholder"))) {
				ele.focus(function() {
					if (ele.val() === placehoder) {
						ele.val("");
					};
					ele.css("color", "");
				})
				ele.blur(function() {
					if (ele.val() === "") {
						ele.val(placehoder);
						ele.css("color", "gray");
					}
				})
				if (ele.val() === "") {
					ele.val(placehoder);
					ele.css("color", "gray");
				}
			}
		},
		destroy: function() {
			this.ele = null;
		}
	}
	$.fn.qfcplaceholder = function() {
		return new Placeholder(this.get(0));
	}
})(jQuery)
