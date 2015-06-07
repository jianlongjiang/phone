(function($) {
    var sel = {
        1: {
            10: "尾货",
            11: "现货囤积",
            12: "清仓",
            13: "抵债",
            14: "取消订单"
        },
        2: {
            20: "纬斜",
            21: "断经",
            22: "断纬",
            23: "破洞"
        },
        3: {
            30: "色差",
            31: "色档",
            32: "批差",
            33: "抵债"
        },
        4: {
            40: "露白",
            41: "印花色泽不匀（斑点）",
            42: "搭色污斑",
            43: "双版色差",
            44: "花版错位"
        },
        5: {
            50: "手感问题",
            51: "泛黄或色变问题"
        },
        6: {
            60: "成分",
            61: "尺寸稳定性",
            62: "色牢度",
            63: "撕裂强度",
            64: "甲醛",
            65: "PH",
            66: "起毛起球等检测不过关"
        },
        7: {
            70: "详细描述"
        }
    };
    var dataVal = 0;
    var _res = '为避免交易纠纷，请对库存原因做详细描述';
    var _key = ['company', 'name', 'mobile']
    var _company = '公司名';
    var _mobile = '手机号';
    var _name = '联系人';

    $(".J_Select_rea").length &&
        $(".J_Select_rea").on('change', function() {
            dataVal = $(this).find("option:selected").attr("data-value");
            var next = $(this).next().next('.J_Select_rea1')
            next.html("");
            for (var i in sel[dataVal]) {
                next.append("<option value=" + sel[dataVal][i] + ">" + sel[dataVal][i] + "</option>");
            }
        });

    $(".J_textarea").length &&
        $(".J_textarea").on('focus', function() {
            if (this.value == _res) {
                this.value = '';
            } else {

            }
        }).on('blur', function() {
            if (this.value == '' || this.value == undefined) {
                this.value = _res;
                optTip(this, 1);
            } else {
                optTip(this, 0);
            }
        }).on('keyup', function() {
            optTip(this, 0);
        });
    $(".J_Select_rea").trigger("change");

    $(".J_Contact_company").length &&
        $(".J_Contact_company")
        .on('focus', function() {
            if (this.value == _company) {
                optTip(this, 1);
                this.value = '';
            } else  {
                optTip(this, 0);
            }
        })
        .on('blur', function() {
            if (this.value == '' || this.value == undefined) {
                this.value = _company;
                optTip(this, 1);
            } else {
                optTip(this, 0);
            }
        })
        .on('keyup', function(){
            optTip(this, 0);
        });
    $(".J_Contact_mobile").length &&
        $(".J_Contact_mobile")
        .on('focus', function() {
            if (this.value == _mobile) {
                optTip(this, 1);
                this.value = '';
            } else {
                optTip(this, 0);
            }
        })
        .on('blur', function() {
            if (this.value == '' || this.value == undefined) {
                this.value = _mobile;
                optTip(this, 1);
            } else {
                optTip(this, 0);
            }
        })
        .on('keyup', function(){
            optTip(this, 0);
        });
    $(".J_Contact_name").length &&
        $(".J_Contact_name")
        .on('focus', function() {
            if (this.value == _name) {
                this.value = '';
                optTip(this, 1);
            } else {
                optTip(this, 0);
            }
        })
        .on('blur', function() {
            if (this.value == '' || this.value == undefined) {
                this.value = _name;
                optTip(this, 1);
            } else {
                optTip(this, 0);
            }
        })
        .on('keyup', function() {
            optTip(this, 0);
        });

    $(".J_Btn_save").click(function() {
        var tr = $(this).parent().parent().parent();
        var id = tr.attr('data-id');
        if (!isNaN(id)) {
            if (checkSave(tr) == false) {
                return false;
            }
            applyFormByID(id);
        }
        return false;
    });


    function optTip(t,isTip) {
        if (isTip) {
            $(t).addClass("input-tip");
        } else {
            $(t).removeClass("input-tip");
        }
    }
    function selectAll() {
        $("#applyForm").find("input[type='checkbox']").each(function() {
            $(this).prop("checked", true);
        })
    }

    function selectNull() {
        $("#applyForm").find("input[type='checkbox']").each(function() {
            $(this).prop("checked", false);
        })
    }

    function applyFormByID(id) {
        selectNull();
        $("input[name='productId'][value=" + id + "]").prop("checked", true);
        if ($("input[name='productId']:checked").length) {
            $("#applyForm").submit();
        } else {
            alert("请选择产品");
        }
    }

    $("#J_SelectAll").change(function() {
        if ($(this).prop('checked') == true) {
            selectAll();
        } else {
            selectNull();
        }
    })

    $("#btn-submit-apply").click(function() {
        var trs = $(this).parent().parent().find('.tr');
        var ckb = 0,
            tag = true;
        trs.each(function(i) {
            if ($(this).find(".td-chk").find("input[type='checkbox']").prop("checked")) {
                ckb++;
                if (checkSave($(this), i + 1) == false) {
                    tag = false;
                    return false;
                }
            }
        });

        if (tag == false) {
            return false;
        }
        if (ckb == 0) {
            alert('未选择申请的产品');
        }
    });

    function checkSave(tr, num) {
        var company = $.trim(tr.find(".J_Contact_company").val());
        var mobile = $.trim(tr.find(".J_Contact_mobile").val());
        var name = $.trim(tr.find(".J_Contact_name").val());
        var textarea = $.trim(tr.find(".J_textarea").val());
        var oPirce = $.trim(tr.find(".J_oPirce").val());
        var pirce = $.trim(tr.find(".J_Pirce").val());
        var unit = $.trim(tr.find(".J_Unit").val());

//        var status = tr.attr('data-status');
        if (oPirce == "" || !(/^[+-]?\d+(\.\d+)?$/.test(oPirce))) {
            showError("原价 不能为空或不正确", num);
            return false;
        }
        if (pirce == "" || !(/^[+-]?\d+(\.\d+)?$/.test(pirce))) {
            showError("库存价 不能为空或不正确", num);
            return false;
        }
        if (unit == "") {
            showError("库存量 不能为空", num);
            return false;
        }
        
        if (tr.find(".J_cp").prop("checked") == true) {
            if ( ! checkPcPrice(tr)) {
            	showError("请设置分批出售价格", num);
                return false;
            }	
        }
       

        if (tr.find(".J_Way2").prop("checked")) {
            if ($.trim($(".J_Way2").parent().next().find('input').val()) == '') {
                showError(" 分批出售内容不能为空", num);
                return false;
            }
        }
        if (company == _company || company == "") {
            showError(_company + " 不能为空", num);
            return false;
        }
        if (mobile == _mobile || mobile == "" || (!(/^1[3|4|5|7|8]{1}\d{9}$/.test(mobile)))) {
            showError(_mobile + " 不能为空或不正确", num);
            return false;
        }
        if (name == _name || name == "") {
            showError(_name + " 不能为空", num);
            return false;
        }
        if (textarea == _res) {
        	tr.find(".J_textarea").val("");
        }

    }

    function showError(msg, num) {
        if (num) {
            alert('第' + num + '条，' + msg);
        } else {
            alert('该条 ' + msg);
        }
    }
    
    function checkPcPrice(t) {
        var tag = 0;
        var empty = 0;

        t.find(".qq .J_cp_unit").each(function(i) {
        	if (i==0 && $(this).val() == '' && $(this).next('input').val() == '') {
                empty = 1;
        	}
            if ($(this).val() != '' && $(this).next('input').val() == '') {
                tag = 1;
                return false;
            }

            if ($(this).val() == '' && $(this).prev('input').val() == '') {
                tag = 1;
                return false;
            }
        });

        if (tag || empty) {
            return false;
        } else {
            return true;
        }
    }

})(jQuery);