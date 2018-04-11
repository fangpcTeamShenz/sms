(function($, app) {
	bootbox.setLocale('zh_CN');
	
	app.alert = function(message) {
		bootbox.alert({
		    size: 'small',
		    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
		    message: message,
		    callback: function(){}
		});
	};
	
	app.showOverlay = function($widget_body) {
		$widget_body.append('<div class="widget-box-overlay"><i class="ace-icon fa fa-spinner fa-spin orange fa-3x"></i></div>');
	};
	
	app.hideOverlay = function($widget_body) {
		$widget_body.find('.widget-box-overlay').remove();
	};
	
	app.showErrorMsg = function(name, msg) {
		var $element = $('input[name="'+name+'"]');
		//var $i = $element.siblings('i');
		//if ($i.length == 0) $element.after('<i class="ace-icon fa fa-times-circle"></i>');
		//else $i.removeClass('fa-check-circle').addClass('fa-times-circle');
		var $form_group = $element.closest('.form-group');
		$form_group.append('<div for="'+name+'" class="help-block col-xs-12 col-sm-4 inline">'+msg+'</div>');
		$form_group.removeClass('has-info has-success').addClass('has-error');
	};
	
	app.getJSON = function(url) {
		$.getJSON(url, function(res){
			if (res.status == 'SUCCESS') {
				bootbox.alert({
				    size: 'small',
				    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
				    message: res.message,
				    callback: function(){
				    	$('.event-search').trigger(ace.click_event);
				    }
				});
			} else if (res.status == 'ERROR_PARAS') {
				var msg = '';
				for (var item in res.errors) {
					msg += res.errors[item]+'<br>';
				}
				app.alert(msg);
			} else if (res.status == 'ERROR_NO_LOGIN') {
				bootbox.alert({
				    size: 'small',
				    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
				    message: res.message,
				    callback: function(){
				    	window.location.reload();
				    }
				});
			} else if (res.status == 'ERROR_NO_PERMISSION') {
				app.alert(res.message);
			} else if (res.status == 'CUSTOM_MESSAGE'){
				app.alert(res.data);
			} else {
				app.alert(res.message);
			}
		});
	};
	
	/**
	 * 设置jquery.validate默认参数
	 */
	$.validator.setDefaults({
		errorElement: 'div',
		errorClass: 'help-block col-xs-12 col-sm-4 inline',
		highlight: function(element){
			//var $i = $(element).siblings('i');
			//if ($i.length == 0) $(element).after('<i class="ace-icon fa fa-times-circle"></i>');
			//else $i.removeClass('fa-check-circle').addClass('fa-times-circle');
			$(element).closest('.form-group').removeClass('has-info has-success').addClass('has-error');
		},
		success: function(error, element){
			//var $i = $(element).siblings('i');
			//if ($i.length == 0) $(element).after('<i class="ace-icon fa fa-check-circle"></i>');
			//else $i.removeClass('fa-times-circle').addClass('fa-check-circle');
			$(element).closest('.form-group').removeClass('has-info has-error');//.addClass('has-success');
			error.remove();
		},
		errorPlacement: function(error, element){
			element.closest('.form-group').append(error);
		},
		submitHandler: function(form){
			var $widget_body = $(form).closest('.widget-body');
			app.showOverlay($widget_body);
			$(form).ajaxSubmit({
				success: function(res){
					if (res.status == 'SUCCESS') {
						var action = $(form).attr('action');
						if (action.indexOf('insert') > -1) {
							bootbox.dialog({
								size: 'small',
								title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
								message: res.message,
								buttons: {
									back: {
										label: "<i class='ace-icon fa fa-arrow-left'></i> 返回列表",
										className: "btn-sm",
										callback: function() {
											bootbox.hideAll();
											$('.event-search').trigger(ace.click_event);
										}
									},
									cont: {
										label: "继续添加 <i class='ace-icon fa fa-arrow-right'></i>",
										className: "btn-sm btn-success",
										callback: function() {
											$(form).clearForm();
										}
									}
								}
							});
						} else {
							bootbox.alert({
							    size: 'small',
							    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
							    message: res.message,
							    callback: function(){
							    	bootbox.hideAll();
							    	$('.event-search').trigger(ace.click_event);
							    }
							});
						}
					} else if (res.status == 'ERROR_PARAS') {
						for (var item in res.errors) {
							app.showErrorMsg(item, res.errors[item]);
						}
					} else if (res.status == 'ERROR_NO_LOGIN') {
						bootbox.alert({
						    size: 'small',
						    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
						    message: res.message,
						    callback: function(){
						    	window.location.reload();
						    }
						});
					} else if (res.status == 'ERROR_NO_PERMISSION') {
						bootbox.alert({
						    size: 'small',
						    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
						    message: res.message,
						    callback: function(){}
						});
					} else {
						if(res.message){
							bootbox.alert({
							    size: 'small',
							    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
							    message: res.message,
							    callback: function(){}
							});
						}
					}
					app.hideOverlay($widget_body);
				}
			});
		}
	});
	
	$.extend($.validator.messages, {
	    required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入长度最大为 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入长度最小为 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入长度介于 {0}~{1} 之间的字符串"),
		range: jQuery.validator.format("请输入介于 {0}~{1} 之间的值"),
		max: jQuery.validator.format("请输入最大为 {0} 的值"),
		min: jQuery.validator.format("请输入最小为 {0} 的值")
	});
	
	$.validator.addMethod("rangelen", function(value, element, param) {
		var length = value.length;
	    for(var i = 0; i < value.length; i++){
	        if(value.charCodeAt(i) > 127){
	            length++;
	        }
	    }
	  return this.optional(element) || ( length >= param[0] && length <= param[1] );
	}, $.validator.format("请输入长度介于 {0}~{1} 之间的字符串(一个中文字占2个字节)"));
	
	$.validator.addMethod("maxlen", function(value, element, param) {
		var length = value.length;
		for(var i = 0; i < value.length; i++){
			if(value.charCodeAt(i) > 127){
				length++;
			}
		}
		return this.optional(element) || length <= param;
	}, $.validator.format("请输入长度最大为 {0} 的字符串(一个中文字占2个字节)"));
	
	$.validator.addMethod("english", function(value, element, param) {
		return /^[A-Za-z]+$/.test(value.replace(/(^\s*)|(\s*$)/g, ""));
	}, "只能输入英文字符");
	
	$.validator.addMethod("chinese", function(value, element, param) {
		return /^[\u0391-\uFFE5]+$/.test(value.replace(/(^\s*)|(\s*$)/g, ""));
	}, "只能输入中文字符");
	
	/**
	 * 将表单序列化为JSON格式
	 */
	$.fn.serializeObject = function(){
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};
	
	$(function(){
		
		/**
		 * 打开对话框
		 * data-url: 必须，对话框内容加载地址
		 * data-title: 可选，对话框标题
		 */
		$(document).on(ace.click_event, '.event-dialog', function(){
			var $div = $('<div class="widget-box transparent position-relative"><div class="widget-body"><div class="widget-main no-padding"></div></div></div>');
			app.showOverlay($div.find('.widget-body'));
			bootbox.dialog({
				title: $(this).attr('data-title') || $(this).html(),
				message: $div,
				size: 'large',
				buttons: {
					close: {
						label: '<i class="ace-icon fa fa-times"></i> 关闭',
						className: "btn-sm btn-danger"
					}
				}
			});
			$div.find('.widget-main').load($(this).attr('data-url'), function(){
				// 为加载出来的form表单添加验证功能
				$div.find('form').validate();
				app.hideOverlay($div.find('.widget-body'));
			});
		});
		
		/**
		 * ajax请求
		 * data-url: 必须，请求链接
		 * data-tips: 可选，执行ajax请求之前弹出的确认提示
		 */
		$(document).on(ace.click_event, '.event-ajax', function(){
			var url=$(this).attr('data-url');
			if(url){
				var tips=$(this).attr('data-tips');
				if(tips){
					bootbox.confirm({ 
					    size: 'small',
					    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
					    message: tips, 
					    callback: function(result){
					    	if (result) {
					    		app.getJSON(url);
					    	}
					    }
					});
				}else{
					app.getJSON(url);
				}
			}
		});
		
		$('.form-inline').keypress(function(e){
            if(e.keyCode==13){
                e.preventDefault();
            }
		});
		
		/**
		 * 列表页查询按钮事件
		 * data-url: 必须，分页查询数据获取地址
		 * data-tpl: 必须，模板ID
		 */
		$(document).on(ace.click_event, '.event-search', function(){
			var $widget = $(this).closest('.widget-box');
			var $body = $widget.find('.widget-body');
        	//$body.append(app.overlay);
			app.showOverlay($body);
			// 创建分页容器
			var $container = $body.find('.widget-toolbox').eq(1);
			if ($container.length==0) {
				$container = $('<div class="widget-toolbox padding-8 clearfix"></div>');
				$container.append('<div class="pull-left"></div>');
				$container.append('<div class="pull-right"></div>');
				$body.append($container);
			}
			var tpl = $('#'+$(this).attr('data-tpl')).html();
			var cols = $body.find('table thead th').length;
			var noData = '<tr><td colspan="'+cols+'" class="center"><h5 class="text-warning orange">未查询到符合条件的数据</h5></td></tr>';
			
			var url = $(this).attr('data-url');
			var data = $(this).closest('form').serializeObject();
			var index = $('.laypage_curr').html();
			$.getJSON(url, $.extend(data, {pageNo:index}), function(res){
			    laypage({
			        cont: $container.find('.pull-right'),
			        pages: res.totalPages,
			        curr: res.pageNo,
			        first: 1,
			        last: res.totalPages,
			        jump: function(e, first){
				        if (!first){
				        	app.showOverlay($body);
					        $.getJSON(url, $.extend(data, {pageNo:e.curr}), function(res){
					        	e.pages = e.last = res.totalPages;
					        	$container.find('.pull-left').html('共有数据：<span class="text-success orange">'+res.totalCount+'</span> 条');
					        	if (res.totalCount == 0) {
					        		$body.find('table tbody').html(noData);
							    } else {
								    laytpl(tpl).render(res.result, function(html){
								    	$body.find('table tbody').html(html);
									});
							    }
					        	app.hideOverlay($body);
					        });
				        }
				    }
			    });
			    $container.find('.pull-left').html('共有数据：<span class="text-success orange">'+res.totalCount+'</span> 条');
			    if (res.totalCount == 0) {
			    	$body.find('table tbody').html(noData);
			    } else {
				    laytpl(tpl).render(res.result, function(html){
				    	$body.find('table tbody').html(html);
					});
			    }
			    app.hideOverlay($body);
			});
		});
		// 模拟搜索按钮点击事件
		$('.event-search').trigger(ace.click_event);
		
		// 为表单添加验证功能
		$('form').validate();
		
	});
}(jQuery, window.app = {}));