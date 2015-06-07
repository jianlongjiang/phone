(function($, undefined) {

	function ImageSlider(ele, opts) {
		this.imgSn = null;
		this.ele = ele;
		this.eleli = null;
		this.nextele = ele.next();
		this.nexteleli = null;
		this.nextIndex = -1;
		this.currentIndex = null;
		this.opts = $.extend({
			time:4000,
			speed:500
			
		},opts);
        this.init();

	}

	ImageSlider.prototype = {
		init : function() {
			this.nextIndex = 0;
			this.eleli = this.ele.find("li");
			this.nexteleli = this.nextele.find("li");
			if (this.nexteleli.length == 1) {
				this.nexteleli.hide();
			}
	      
			var _this = this;
			//注册事件
			this.ele.bind("mouseover", function() {
				_this.stop();
			})
			this.ele.bind("mouseout", function() {
				_this.go();
			})
			
		    this.nextele.bind("mouseover", function() {
		    	_this.stop();
			})
			this.nextele.bind("mouseout", function() {
				_this.go();
			})
			
			
			this.nexteleli.each(function(index){
				 $(this).mouseover(function(){_this.scroll(index)});
			});
			
			this.go();
			
			
		},
		go : function() {
			this.stop();
			var _this = this;
			this.imgSn = setInterval(function(){_this.scroll(_this.nextIndex)}, this.opts.time);
			
		},

		stop : function() {
			if (this.imgSn) {
				clearInterval(this.imgSn);
			}
		},
		scroll : function(index) {
//			console.log(this.nexteleli);
			 this.nexteleli.removeClass("ahover");
			 this.nexteleli.eq(index).addClass("ahover");
			 if (this.currentIndex) {
				 this.eleli.eq(this.currentIndex).fadeOut(this.opts.speed);
			 } else {
				 this.eleli.hide();
			 }
			 //this.eleli.eq(index).show();
			 this.eleli.eq(index).fadeIn(this.opts.speed);
			 //this.eleli.eq(index).slideUp();
			 //this.eleli.eq(index).animate({opacity:'0.5'});
			 this.currentIndex = index;
			 this.nextIndex = index + 1 >= this.eleli.length ? 0 : index + 1;
		}
	}

	$.fn.qfcImageSlider = function(opts) {
		return new ImageSlider(this, opts);
	}

})(jQuery)
