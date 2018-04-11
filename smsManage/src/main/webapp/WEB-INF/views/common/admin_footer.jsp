<%@page pageEncoding="utf-8" %>

						</div>
					</div>
				</div>
			</div>
		</div>
		
		<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

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
		<script src="${ctx}/assets/js/bootstrap.min.js"></script>
		<script src="${ctx}/assets/js/uncompressed/x-editable/bootstrap-editable.js"></script>
		
		<!-- page specific plugin scripts -->
		<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
		<script src="${ctx}/assets/js/additional-methods.min.js"></script>
		<script src="${ctx}/assets/js/jquery.form.min.js"></script>
		<script src="${ctx}/assets/js/bootbox.min.js"></script>
		<script src="${ctx}/assets/js/laypage/laypage.js"></script>
		<script src="${ctx}/assets/js/laytpl/laytpl.js"></script>
		<!--datepicker  -->
		<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="${ctx}/assets/js/date-time/moment.min.js"></script>
		<script src="${ctx}/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		<!-- spinner -->
		<script src="${ctx}/assets/js/fuelux/fuelux.spinner.min.js"></script>
		<!-- ace scripts -->
		<script src="${ctx}/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/assets/js/ace.min.js"></script>
		<script src="${ctx}/assets/js/jquery.dataTables.min.js"></script>
		<script src="${ctx}/assets/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${ctx}/assets/js/custom/app.js"></script>
		<script src="${ctx}/assets/js/chosen.jquery.min.js"></script>		
		<script type="text/javascript">
			jQuery(function($) {
				var menuid = '${param.menu}';
				if (menuid && menuid != '') {
					var $menu = $('#'+menuid);
					var subTitle = $menu.parent().closest('li').find('.menu-text').text();
					$menu.addClass('active').parent().closest('li').addClass('active open');
					$('.breadcrumb').append(subTitle==''?'':('<li class="active">'+subTitle+'</li>')).append('<li class="active">'+$menu.text()+'</li>');
					window.document.title = $menu.text() + ' - ' + (subTitle==''?'':(subTitle + ' - ')) + window.document.title;
				}
			});
		</script>

	</body>
</html>