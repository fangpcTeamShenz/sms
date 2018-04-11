<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			管理员列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/user/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增管理员
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="nickname" placeholder="用户名">
				<input type="text" class="form-control" name="email" placeholder="邮箱">
				<input type="hidden" name="userType" value="1"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/user/admin/page/list" data-tpl="tpl-user">
					<i class="ace-icon fa fa-search bigger-110"></i> 搜索
				</button>
				<button type="reset" class="btn btn-sm">
					<i class="ace-icon fa fa-undo bigger-110"></i> 清空
				</button>
			</form>
		</div>
		<div class="widget-main no-padding">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>用户名</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-admin-user-list" name="menu"/>
</jsp:include>

<script id="tpl-user" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].nickname}}</td>
        <td>{{=d[i].email}}</td>
        <td>

            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/user/admin/edit/{{=d[i].id}}">
              <i class="ace-icon fa fa-pencil"></i>
            </button>

			 <button type="button" class="btn btn-xs btn-inverse event-dialog" data-url="${ctx}/user/admin/roleedit/{{=d[i].id}}">
              <i class="ace-icon fa fa-key"></i>
            </button>

        </td>
      </tr>
    {{# } }}
</script>
