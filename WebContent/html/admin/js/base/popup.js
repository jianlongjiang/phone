(function($, undefined) {
	function Popup(opts) {
		this.box = null;
		this.background = null;
		this.width = 0;
		this.height = 0;
		this.top = 0;
		this.left = 0;
		this.title = null;
		this.content = null;

		//cls = $.extend(popup, cls);
		this.opts = $.extend({
			/*
			 *@Property:{String} id
			 */
			id: "",
			/*
			 *@Property:{String} class
			 */
			className: "",
			/*
			 *@Property:{Number} width
			 *default value is 300
			 */
			width: 300,
			/*
			 *@Property:{Number} height
			 */
			height: 0,
			/*
			 *@Property:{Number} left
			 */
			left: 0,
			/*
			 *@Property:{Number} top
			 */
			top: 0,
			/*
			 *@Property:{Object} css
			 *if css is defined, the box will render the style
			 */
			css: null,
			/*
			 *@Property:{Bool} center
			 *whether set the box center or not
			 */
			center: true,
			/*
			 *@Property:{Bool} fixed
			 *whether set the box fixed or not
			 */
			fixed: true,
			/*
			 *@Property:{String} background
			 *color,alpha
			 */
			background: "#000000,0.4",
			/*
			 *@Property:{String} border
			 *e.g. ".border" border class name
			 */
			border: "",
			/*
			 *@Property:{Bool} x
			 *whether show the "x" or not
			 */
			x: true,
			/*
			 *@Property:{String} title
			 */
			title: "对话框",
			/*
			 *@Property:{Function} title
			 */
			close: null
		}, opts);
	}

	Popup.prototype = {
		init: function() {
			var _this = this;
			var opts = _this.opts;

			if (opts.id == "") {
				opts.id = "QFCPopup";
			}
			if ($("#" + opts.id).length) {
				return;
			}

			_this.box = $('<div class="act-pop-box" style="display:none">').appendTo("body");
			_this.box.attr('id', opts.id);
			_this.box.addClass(opts.className);

			//加载DOM
			if (opts.title != "") {
				_this.title = $('<div class="act-pop-title"></div>');
				_this.box.prepend(_this.title);
				_this.title.text(opts.title);
			}

			_this.content = $('<div class="act-pop-contnet"></div>').appendTo(_this.box);
			_this.content.html(opts.content);

			if (opts.x) {
				var x = $('<a herf="javascript:void(0)" class="act-pop-x">x</a>').appendTo(this.box);

				x.on("click", function() {
					_this.hide(opts.close);
				});
			}

			
			_this.resize();
			_this.fixed(opts.fixed);

			_this.box.css("z-index","9999")
			if (opts.css) {
				_this.box.css(opts.css);
			}

			$(window).on("resize", function() {
				_this.resize();
			});

			if (opts.background !="") {
				_this.background = $('<div class="act-poup-bg"></div>');
				_this.background.css({
					width: $("body").width(),
					height: $("body").height(),
					position: "absolute",
					top:0,
					left:0,
					background:opts.background.split(",")[0],
					opacity:opts.background.split(",")[1],
					zIndex:990
				});
				_this.background.appendTo("body");
			};
		},
		show: function(callback) {
			if (!this.box) {
				this.init();
			}
			var callback = callback || $.noop;
			this.box.fadeIn(100, callback);
			if (this.background) {
				this.background.fadeIn();
			}
		},
		hide: function(callback) {
			var callback = callback || $.noop;
			this.box.fadeOut("slow", callback);
			if (this.background) {
				this.background.fadeOut();
			};
		},
		close: function(callback) {
			var callback = callback || $.noop;
			this.hide(callback);
			this.box.fadeOut("slow");
			this.box.remove();
		},
		resize: function() {
			var opts = this.opts;
			this.width = opts.width;
			this.height = opts.height;

			if (this.height == 0) {
				this.height = this.getHeightByWidth(this.width) + (this.title ? this.title.outerHeight() : 0);
			}
			this.box.css({
				width: this.width,
				height: this.height
			});
			this.repos();
		},
		repos: function() {
			var opts = this.opts;
			this.top = opts.top;
			this.left = opts.left;

			if (opts.center) {
				this.top = ($(window).height() - this.height) / 2;
				this.left = ($(window).width() - this.width) / 2;
			}

			this.box.css({
				top: this.top,
				left: this.left
			})
		},
		setTop: function(newTop){
			this.top = newTop;
			this.box.css("top", newTop);
		},
		fixed: function(isFixed) {
			var ie6 = !-[1,] && !window.XMLHttpRequest;
			var _this = this;
			_this.box.css("position", "absolute");

			if (isFixed) {
				if (ie6) {
					var _this = this;
					var html = $("html")[0];
					html.style.backgroundImage = 'url(about:blank)';
					html.style.backgroundAttachment = 'fixed';
					_this.box[0].style.setExpression("top", 'eval(document.compatMode && document.compatMode=="CSS1Compat" ? documentElement.scrollTop + ' + _this.top + ' : document.body.scrollTop + ' + _this.top + ')');
					_this.box[0].style.setExpression("left", 'eval(document.compatMode && document.compatMode=="CSS1Compat" ? documentElement.scrollLeft + ' + _this.left + ' : document.body.scrollLeft + ' + _this.left + ')');
					// $(window).bind('scroll.QFCPopupFixed', function(e) {
					// 	_this.box.css('margin-top', $(window).scrollTop());
					// 	//_this.box.stop().animate({"marginTop": ($(window).scrollTop()) + "px"},"slow")
					// });
					// 	//_this.box[0].style.setExpression("top",'eval((document.documentElement).scrollTop+ + '+ _this.top +') + "px"');
					// });
					/*
					 * CSS????鈧?-??????隆矛?隆矛?????隆搂o?隆搂隆搂????expression((document.compatMode && document.compatMode=='CSS1Compat' ? documentElement.scrollTop+(documentElement.clientHeight - {Number}):document.body.scrollTop+(document.body.clientHeight - {Number}))-4);
					 */
				} else {
					_this.box.css("position", "fixed");
				}
			} else {
				if (ie6) {
					$(window).unbind('scroll.QFCPopupFixed');
				}
			}
		},
		getHeightByWidth: function(width) {
			var clone = this.content.clone();
			var box = $('<div style="display:none;width:' + width + 'px"></div>').appendTo("body");
			box.append(clone);
			var height = box.outerHeight(true);
			box.remove();

			return height;
		},
		destroy: function() {

		}
	}

	//扩展
	var Message = function(opts) {
		Popup.call(this, $.extend({
			width: 300,
			id: "QFCPopupMessage" + new Date().getTime(),
			title: "消息框"
		}, opts));
	}

	Message.prototype = new Popup();
	Message.prototype.constructor = Message;
	$.extend(Message.prototype, {
		init: function() {
			var _this = this;
			
			_this.opts.content = '<div>' + _this.opts.content + '</div>';
			Popup.prototype.init.call(this);
			_this.box.addClass("act-message");

			var button = $('<a class="act-message-enter" href="javascript:void(0)">&nbsp;</a>');
			button.bind('click', function() {
				_this.close();
			});

			_this.content.append(button);
			_this.resize();
		}
	});

	var Former = function(opts) {
		Popup.call(this, $.extend({
			id: "QFCPopupFormer" + new Date().getTime(),
			reset: false,
			submit: null
		}, opts));
		this.former = null;
	}
	Former.prototype = new Popup();
	Former.prototype.constructor = Former;
	$.extend(Former.prototype, {
		init: function() {
			var _this = this;
			_this.opts.content = '<div>' + _this.opts.content + '</div>';
			Popup.prototype.init.call(this);

			this.former = this.content.find("form");
			if (_this.opts.submit) {
				_this.former.submit(function() {
					_this.submit;
				})
			}
		},
		hide: function(callback) {
			var _this = this;
			Popup.prototype.hide.call(_this, function() {
				if (_this.opts.reset) {
					_this.former[0].reset();
				}
				var callback = callback || $.noop;
				callback();
			});
		}
	});

	//exports
	window.QFCPopup = {}
	QFCPopup.base = Popup;
	QFCPopup.message = Message;
	QFCPopup.former = Former;
})(jQuery)