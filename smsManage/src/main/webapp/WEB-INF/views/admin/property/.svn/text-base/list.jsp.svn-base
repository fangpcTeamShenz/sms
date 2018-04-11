<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			数据字典列表
		</h5>
		<shiro:hasPermission name="property_insert">
			<div class="widget-toolbar no-border">
				<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/property/admin/add?parentPath=${criteria.parentPath}">
					<i class="ace-icon fa fa-plus"></i>
					新增数据字典
				</button>
			</div>
	
			<div class="widget-toolbar no-border">
				<button class="btn btn-xs btn-success bigger event-ajax" data-url="${ctx}/property/clearCache">
					<i class="ace-icon fa fa-barcode"></i>
					&nbsp;清除缓存
				</button>
			</div>
		</shiro:hasPermission>
	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<h3 class="header smaller lighter red">父路径：${criteria.parentPath}</h3>
			<form class="form-inline hide">
				<input type="text" class="form-control" name="parentPath" placeholder="key" value="${criteria.parentPath}">

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/property/admin/page/list" data-tpl="tpl-property">
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
						<th>字典名称</th>
						<th>值</th>
						<th>显示名称</th>
						<th>显示顺序</th>
						<th>状态</th>
						<th>是否显示</th>
						<th class="hidden-480">描述</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-property-list" name="menu"/>
</jsp:include>

<script id="tpl-property" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
	{{# d[i].path = d[i].parentPath == '' ? d[i].propertyName : d[i].parentPath + '.' + d[i].propertyName; }}
      <tr>
        <td><a href="${ctx}/property/admin/list?parentPath={{=d[i].path}}">{{=d[i].propertyName}}</a></td>
		<td>{{=d[i].propertyKey}}</td>
        <td>{{=d[i].propertyValue}}</td>
		<td>{{=d[i].propertyIndex}}</td>
		<td>
			{{# if (d[i].status == 1) { }}
				<span class="label label-success">有效</span>
			{{# } else { }}
				<span class="label">无效</span>
			{{# } }}
		</td>
		<td>{{=d[i].disable}}</td>
		<td class="hidden-480">{{=d[i].memo}}</td>
		<td>

            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/property/admin/edit/{{=d[i].id}}">
              <i class="ace-icon fa fa-pencil"></i>
            </button>

			{{# if (d[i].status == 1) { }}
			<button type="button" class="btn btn-xs btn-danger event-ajax" data-url="${ctx}/property/admin/disable?parentPath={{=d[i].parentPath}}&propertyName={{=d[i].propertyName}}&status=0"
             	 data-tips="您确认禁用字典 <span class='text-warning'>{{d[i].propertyName}}</span> 以及子路径下所有字典吗？"><i class="ace-icon fa fa-ban"></i> 禁用</button>
			{{# } else { }}
			<button type="button" class="btn btn-xs btn-success event-ajax" data-url="${ctx}/property/admin/disable?parentPath={{=d[i].parentPath}}&propertyName={{=d[i].propertyName}}&status=1"
             	 data-tips="您确认启用字典 <span class='text-warning'>{{d[i].propertyName}}</span> 以及子路径下所有字典吗？"><i class="ace-icon fa fa-check-circle-o"></i> 启用</button>
			{{# } }}
		</td>
      </tr>
    {{# } }}
</script>
