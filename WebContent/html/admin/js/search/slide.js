(function($) {
	function Slide(opts) {
		this.list = null;
		this.prevBtn = null;
		this.nextBtn = null;
		this.current = 0;
		this.view = 0;
		this.piece = 0;
		this.animate = false;
		this.opts = $.extend({}, opts);
		this.init();
	}
	Slide.prototype = {
		init: function() {
			this.ele = this.opts.ele;
			this.view = Number(this.ele.attr("data-view"));
			this.wrap = this.ele.find(".act-slide-list").length ?this.ele.find(".act-slide-list"):this.ele.find("ul");
			this.list = this.ele.find(".act-slide-piece").length ?this.ele.find(".act-slide-piece"):this.ele.find("li");
			this.piece = this.list.outerWidth(true);
			this.prevBtn = this.ele.find(".prev");
			this.nextBtn = this.ele.find(".next");
			var _this =this;
			this.prevBtn.bind("click", function(){
				_this.prev();
			});
			this.nextBtn.bind("click", function(){
				_this.next();
			});
			this.toggleBtn();
		},
		next: function() {
			if ((this.current + this.view >= this.list.length) || this.animate) {
				return
			}
			if($("#rementuijian").length > 0){
				this.current = this.current + 4;
				$("#rementuijian").html((this.current/4+1)+"/4");
			}else{
				this.current++;
			}
			this.animate = true;
			var _this = this;
			this.wrap.animate({
				marginLeft: -this.piece * this.current
			}, 500, function() {
				_this.animate = false;
			});
			this.toggleBtn();
		},
		prev: function() {
			if (this.current <= 0 || this.animate) {
				return
			}
			if($("#rementuijian").length > 0){
				this.current = this.current - 4;
				$("#rementuijian").html((this.current/4+1)+"/4");
			}else{
				this.current--;
			}
			this.animate = true;
			var _this = this;
			this.wrap.animate({
				marginLeft: -this.piece * this.current
			}, 500, function() {
				_this.animate = false;
			});
			this.toggleBtn();
		},
		toggleBtn: function(){
			if (this.current + this.view >= this.list.length) {
				this.nextBtn.hide();
			}else{
				this.nextBtn.show();
			}

			if (this.current <= 0) {
				this.prevBtn.hide();
			}else{
				this.prevBtn.show();
			}
		}
	}
	$.fn.slide = function(opts) {
		this.each(function() {
			var _this = $(this);
			return new Slide($.extend({
				ele: _this
			}, opts));
		});

	}
})(jQuery)