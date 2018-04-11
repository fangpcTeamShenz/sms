<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>后台首页</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/assets/images/favicon.ico" /> --%>
		
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-editable.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${ctx}/assets/css/jquery.gritter.css" />
		<!--time datepicker -->
		<link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/chosen.min.css" />
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
		<![endif]-->
		<!-- sesrchableselect -->
		<!-- inline styles related to this page -->
		<style type="text/css">
			.widget-toolbox{background-color: #EFF3F8;}
		</style>

		<!-- ace settings handler -->
		<script src="${ctx}/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${ctx}/assets/js/html5shiv.min.js"></script>
		<script src="${ctx}/assets/js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript" language="javascript">
		var __ctx = '${ctx}';
		</script>
	</head>

	<body class="no-skin">
		
		<div id="navbar" class="navbar navbar-default navbar-fixed-top">

			<div class="navbar-container" id="navbar-container">
				
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="${ctx}/admin/index" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							沛璟SMS管理系统
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>您好，</small>
									${sessionScope.SESSION_USER.nickname}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
								<li>
									<a href="javascript:;" class="event-dialog" data-url="${ctx}/user/self/edit">
										<i class="ace-icon fa fa-cog"></i>
										个人设置
									</a>
								</li>
								
								<li>
									<a href="javascript:;" class="event-dialog" data-url="${ctx}/user/smsNotice" id="smsNotice">
										<i class="ace-icon fa fa-cog"></i>
										用户须知
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="${ctx}/user/logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出登陆
									</a>
								</li>
							</ul>
						</li>

					</ul>
				</div>

			</div>
		</div>

		<div class="main-container" id="main-container">
			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive sidebar-fixed sidebar-scroll">
				<ul class="nav nav-list">
					<shiro:hasPermission name="smsUser_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-users"></i>
							<span class="menu-text"> 客户管理</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="smsUser_view">
							<li id="menu-smsUser-list">
								<a href="${ctx}/smsUser/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									短信用户
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="companyInfo_view">
							<li id="menu-companyInfo-list">
								<a href="${ctx}/companyInfo/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									企业信息
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="smsSignature_view">
							<li id="menu-smsSignature-list">
								<a href="${ctx}/smsSignature/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									签名管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="smsTemplate_view">
							<li id="menu-smsTemplate-list">
								<a href="${ctx}/smsTemplate/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									模板管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="charge_view">
							<li id="menu-charge-list">
								<a href="${ctx}/charge/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									充值订单
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="userBill_view">
							<li id="menu-userBill-list">
								<a href="${ctx}/userBill/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									充值消费记录
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="base_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-cogs"></i>
							<span class="menu-text"> 基础管理</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="product_view">
							<li id="menu-product-list">
								<a href="${ctx}/product/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									产品管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="routeForce_view">
							<li id="menu-routeForce-list">
								<a href="${ctx}/routeForce/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									路由管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="channel_view">
							<li id="menu-channel-list">
								<a href="${ctx}/channel/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									通道管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="module_view">
							<li id="menu-module-list">
								<a href="${ctx}/module/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									模块管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="orderSms_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon glyphicon glyphicon-list"></i>
							<span class="menu-text"> 订单管理</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="orderSms_view">
							<li id="menu-orderSms-list">
								<a href="${ctx}/orderSms/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									发送记录
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="smsUpstream_view">
							<li id="menu-smsUpstream-list">
								<a href="${ctx}/smsUpstream/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									上行记录
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="user_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-fighter-jet"></i>
							<span class="menu-text"> 高级设置</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="user_view">
							<li id="menu-admin-user-list">
								<a href="${ctx}/user/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									管理员列表
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
					
							<shiro:hasPermission name="role_view">
							<li id="menu-role-list">
								<a href="${ctx}/role/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									角色管理
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="property_view">
							<li id="menu-property-list">
								<a href="${ctx}/property/admin/list">
									<i class="menu-icon fa fa-caret-right"></i>
									数据字典
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="clientUser_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-users"></i>
							<span class="menu-text"> 个人中心</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="clientUser_view">
							<li id="menu-clientUser-list">
								<a href="${ctx}/clientUser/client/userInfo">
									<i class="menu-icon fa fa-caret-right"></i>
									个人信息
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="clientSignature_view">
							<li id="menu-clientSignature-list">
								<a href="${ctx}/clientSignature/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									短信签名
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="clientTemplate_view">
							<li id="menu-clientTemplate-list">
								<a href="${ctx}/clientTemplate/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									短信模板
								</a>
								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="clientUser_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-mobile"></i>
							<span class="menu-text"> 短信管理</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="clientOrderSms_send">
							<li id="menu-clientSendSms-list">
								<a href="${ctx}/clientOrderSms/client/sendSms">
									<i class="menu-icon fa fa-caret-right"></i>
									发送短信
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="clientOrderSms_view">
							<li id="menu-clientOrderSms-list">
								<a href="${ctx}/clientOrderSms/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									短信记录
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="clientUpstream_view">
							<li id="menu-clientUpstream-list">
								<a href="${ctx}/clientUpstream/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									上行记录
								</a>

								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="clientCharge_view">
					<li>
						<a href="javascript:;" class="dropdown-toggle">
							<i class="menu-icon fa fa-credit-card"></i>
							<span class="menu-text"> 财务管理</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<shiro:hasPermission name="clientCharge_view">
							<li id="menu-clientCharge-list">
								<a href="${ctx}/clientCharge/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									充值记录
								</a>
								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
							<shiro:hasPermission name="clientUserBill_view">
							<li id="menu-clientUserBill-list">
								<a href="${ctx}/clientUserBill/client/list">
									<i class="menu-icon fa fa-caret-right"></i>
									消费记录
								</a>
								<b class="arrow"></b>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
				</ul>

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

			</div>

			<div class="main-content">
			
				<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${ctx}/admin/index">Home</a>
						</li>
					</ul>

				</div>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="space-6"></div>
