<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			角色列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/role/admin/toroleadd">
				<i class="ace-icon fa fa-plus"></i>
				新增角色
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
	<button type="button" class="btn btn-info btn-sm event-search" style="display: none;"
					data-url="${ctx}/role/admin/page/list" data-tpl="tpl-role">
					<i class="ace-icon fa fa-search bigger-110"></i> 搜索
				</button>
		</div>
		<div class="widget-main no-padding">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>角色名</th>
						<th>角色说明</th>
						
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-role-list" name="menu"/>
</jsp:include>

<script id="tpl-role" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].role_name}}</td>
        <td>{{=d[i].description}}</td>
       
        <td>

            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/role/admin/editinfo/{{=d[i].id}}">
              <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button type="button" class="btn btn-xs btn-danger event-ajax" data-url="${ctx}/role/admin/roledelete/{{=d[i].id}}"
             	 data-tips="您确认删除角色 <span class='text-warning'>{{d[i].role_name}}</span> 吗？"><i class="ace-icon fa fa-trash-o"></i></button>
    
		</td>
      </tr>
    {{# } }}
</script>
