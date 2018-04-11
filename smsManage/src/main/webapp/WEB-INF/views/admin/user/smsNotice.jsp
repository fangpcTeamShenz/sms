<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<style>
	span {
		line-height::5px;
	}
</style>
	<p>
		<span>尊敬的客户：</span><br>
  		<span>为了便利您的发送和保证通道质量，以下几点请您注意：</span><br>
  		<span>1.本平台不发任何违法及违规信息，若有发现，暂停账户，余额不退</span><br>
  		<span>2.短信发送格式：签名+内容，签名+内容，签名+内容，重要的事情说三遍，签名格式：【XX】，XX尽量为2--8个字符中英文字母及数字，黑色中括号为中文输入法状态下的中括号，例如:【顺丰】您的快递已寄送，请及时签收！</span><br>
  		<span>3.系统自动识别签名，没有带签名短信将无法发送，请知晓！</span><br>
  		<span>4.点击发送短信之前，请检查下短信内容，如遇特殊符号，系统和通道无法识别，内容将以?呈现特殊字符，请及时与工作人员联系修改！</span><br>
  		<span>5.短信发送后请耐心等待，我们会及时处理，保证您的短信以最快速度送到到客户手机。</span><br>
  		<span>6.如遇到手机正常状态下收不到短信情况，极有可能是号码进入黑名单，请及时联系工作人员进行去除黑名单。</span><br>
  		<span>以上，祝您天天开心，谢谢！</span>
	</p>
