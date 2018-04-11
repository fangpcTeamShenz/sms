<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>登陆 - 沛璟短信管理平台</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.onpage-help.css" />

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/assets/js/html5shiv.js"></script>
		<script src="${ctx}/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">沛璟短信</span>
									<span class="gray">管理平台</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; 沛璟信息科技有限公司</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请输入用户名和密码
											</h4>

											<div class="space-6"></div>

											<form action="${ctx}/user/login" method="post">
												<div class="alert alert-danger hidden"></div>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="nickname" id="nickname" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" id="password" placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="clearfix">
														<button id="btn-login" type="button" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key animated-rotate"></i>
															<span class="bigger-110">登陆</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
											
										</div>
										
										<div class="toolbar clearfix">
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													忘记密码了？
												</a>
											</div>
											<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													我要去注册
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>

									</div>
								</div>
								
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												重置密码
											</h4>

											<div class="space-6"></div>
											<p>
												新密码会通过短信的形式发送给您
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="username1" type="text" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button id="btn-sendMsg" type="button" onclick="pwdReset();" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110">重置</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								
								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												新用户注册
											</h4>
											<div class="space-6"></div>
											<p> 请输入你的详细信息: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" id="nickname1"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
														<span style="color: red;">注：用户名可为英文或汉字</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" id="pwd"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="确定密码" id="pwd2"/>
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success" onclick="register();">
															<span class="bigger-110">注册</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回登录
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
		<script src="${ctx}/assets/js/jquery.form.min.js"></script>
		<script src="${ctx}/assets/js/sha256.js"></script>
		
		<script src='${ctx}/assets/js/jquery.min.js'></script>
		<script src="${ctx}/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/assets/js/bootbox.min.js"></script>
		<script src="${ctx}/assets/js/ace.min.js"></script>
		<script src="${ctx}/assets/js/custom/app.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				
				$(document).on('click', '.toolbar a[data-target]', function(e) {
					e.preventDefault();
					var target = $(this).data('target');
					$('.widget-box.visible').removeClass('visible');//hide others
					$(target).addClass('visible');//show target
				});
				
				$(document).on('click', '#btn-login', function() {
					var $btn = $(this);
					$btn.addClass('disabled').find('span').html('登陆中...');
					var $alert = $(this).closest('form').find('.alert-danger');
					var nickname = $('#nickname').val();
					var password = $('#password').val();
					$.ajax({
						type: 'post',
						url: '${ctx}/user/login',
						data: 'nickname='+nickname+'&password='+sha256_digest(password),
						dataType: 'json',
						success: function(data) {
							var status = data.status;
							if (status == 'SUCCESS') {
								window.location.href = '${ctx}'+data.data;
							} else if(status == 'EXCEPTION' || status == 'LOGIN_ERROR') {
								$alert.html(data.message);
								$alert.removeClass('hidden');
							} else {
								var text = '';
								for (var item in data.errors) {
									text += '<i class="ace-icon fa fa-times"></i> <span>' + data.errors[item] + '</span><br>';
								}
								$alert.html(text);
								$alert.removeClass('hidden');
							}
							$btn.removeClass('disabled').find('span').html('登陆');
						},
						error: function(msg) {
							$alert.html('系统异常，请稍后再试');
							$alert.removeClass('hidden');
						}
					});
				});
				
				$('#login-box input').keydown(function(e){
					if (e.which == 13){
						$("#btn-login").click();
						return false;
					}
				});
			});
		</script>
		
		<!-- 业务操作 -->
		<script type="text/javascript">
			//重置密码
			var pwdReset = function(){
				var nickname = $("#username1").val();
				if(nickname == ""){
					app.alert("用户名不能为空！");
					return;
				}
				$.ajax({
					type: 'post',
					url: '${ctx}/smsUser/pwdReset',
					data: 'nickname='+nickname,
					dataType: 'json',
					success: function(data) {
						var status = data.status;
						if (status == 'SUCCESS') {
							app.alert("重置成功");
						} else {
							app.alert(data.data);
						}
					},
					error: function(msg) {
						app.alert('网络异常，请稍后再试');
					}
				});
			};
			
			//新用户注册
			var register = function(){
				var nickname = $("#nickname1").val();
				var pwd = $("#pwd").val();
				var pwd2 = $("#pwd2").val();
				if(nickname == ""){
					app.alert("请输用户名！");
					return;
				}
				if(pwd == ""){
					app.alert("请输入密码！");
					return;
				}
				if(pwd2 == ""){
					app.alert("请输入确认密码！");
					return;
				}
				if(pwd != pwd2){
					app.alert("两次密码不一致！");
					return;
				}
				$.ajax({
					type: 'post',
					url: '${ctx}/smsUser/register',
					data: 'nickname='+nickname+'&password='+pwd,
					dataType: 'json',
					success: function(data) {
						var status = data.status;
						if (status == 'SUCCESS') {
							bootbox.confirm({ 
							    size: 'small',
							    title: '<i class="ace-icon fa fa-exclamation-triangle red"></i> 提示',
							    message: "注册成功，去登陆?", 
							    callback: function(result){
							    	if (result) {
							    		window.location.href = '${ctx}/user/logout';
							    	}
							    }
							});
						} else {
							app.alert(data.data);
						}
					},
					error: function(msg) {
						app.alert('网络异常，请稍后再试');
					}
				});
			};
		</script>
	</body>
</html>
