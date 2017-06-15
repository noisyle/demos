<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- charset -->
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<!-- xUaCompatible -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- 使用浏览器的最新内核渲染，借助RESET CSS解决浏览器兼容性问题，无极特殊情况不要修改或删除此值 -->
<meta name="renderer" content="webkit"><!-- 开启国产浏览器的webkit内核渲染模式 -->
<meta name="viewport" content="width=device-width, initial-scale=1"><!--移动端优先-->
<!-- jquery -->
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/jquery/jquery-1.8.3.min.js"></script>
<!-- bootstrap IAF定制版 -->
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/bootstrap/css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/bootstrap/css/bootstrap-theme.min.css" type="text/css"></link>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/bootstrap/js/bootstrap.min.js"></script>
<!-- html5shiv 将不支持html5的浏览器兼容HTML5标签 -->
<!--[if lt IE 9]>
	<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/html5shiv/html5shim.js"></script>
	<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/html5shiv/respond.min.js"></script>
<![endif]-->
<!-- easyui -->
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/easyui/themes/default/easyui.css" type="text/css"></link>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/easyui/jquery.easyui.min.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-datagrid-extend.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-datagrid-detailview.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/easyui/themes/iaf-easyui.css" type="text/css"></link>
<!-- iafbase IAF基础库 -->
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag.css" type="text/css"></link>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/json.min.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/Math.uuid.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-selector.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-amount.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-validatebox.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-utils.js"></script>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-base/iaf-tag-export.js"></script>
<!-- constant js常量 -->
<script type="text/javascript">
	window.contextPath="/iaf-tag";
	window.taglibPath="/iaf-tag/IAF_TAGLIB_TEMP";
</script>
<!--[if IE 6]>
	<script charset="UTF-8" type="text/javascript">window.iaf_ie6="true";</script>
<![endif]-->
<!--[if IE 7]>
	<script charset="UTF-8" type="text/javascript">window.iaf_ie7="true";</script>
<![endif]-->
<!-- nocache -->
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<!-- checkRadio -->
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/iaf-checkRadio/css/jquery.iafCheckRadio.css" type="text/css"></link>
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/iaf-checkRadio/css/jquery.iafCheckRadio.cloudy.css" type="text/css"></link>
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/iaf-checkRadio/js/jquery.iafCheckRadio.js"></script>
<!-- fontAwesome 字体图标库 -->
<link rel="stylesheet" href="/iaf-tag/IAF_TAGLIB_TEMP/font-awesome/css/font-awesome.min.css" type="text/css"></link>
<!-- layer -->
<script charset="UTF-8" type="text/javascript" src="/iaf-tag/IAF_TAGLIB_TEMP/layer/layer.js"></script>
</head>
<body style="" class="iaf-body overflow-hidden" >
<div class="container-fluid height-100">
<div class="row height-100 overflow-auto"><!-- Bootstrap栅格系统：流式布局容器 -->
<style>
	.box-style{margin-top: 100px;}
</style>

<div class=" col-xs-6 col-xs-offset-3 box-style" >
<div id="iafAuto2" data-height="160" class="iaf-panel iaf-panel-default iaf-panel-fix" style="height:160px; " >
<div class="iaf-panel-body" ><div class="container-fluid"><div class="row">
<div class="alert alert-info" style="text-align:center; padding-top: 30px; padding-bottom: 35px;">
	<h1><i class="icon-laptop"></i><span id="systemInfo"></span></h1>
</div>
</div></div></div></div></div>
<script>
$(function(){
	if('${status}'==='1'){
		$("#systemInfo").text(" 系统已关机，不能继续处理业务");
	}else if('${status}'==='2'){
		$("#systemInfo").text(" 系统正在关机，不能继续处理业务");
	}else if('${status}'==='3'){
		$("#systemInfo").text(" 系统正在开机，不能继续处理业务");
	};
});
</script>

</div></div>
</body>
</html>
