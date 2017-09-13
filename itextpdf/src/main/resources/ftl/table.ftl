<!DOCTYPE html>
<html>
<head>
<style>
@page {
	size: 11.69in 8.27in;
	margin: 0.7in 0.4in 0.7in 0.4in;
}
* {
	font-family: 'Arial Unicode MS';
	font-size: 12px;
}
html, body {
	padding: 0px;
	margin: 0px;
}
table {
	width: 100%;
	border: 0;
	border-collapse: collapse;
	table-layout: fixed;
	word-break: break-strict;
	page-break-after: always;
}
td, th {
	border: 1px solid #000;
	padding: 10px;
	word-wrap: break-word;
	overflow: hidden;
}
th {
	background-color: #F5F5F5;
	text-align:center;
}
</style>
</head>
<body>
	<table cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th colspan="${flat_title?size}">${table_name}</th>
			</tr>
			<#list title as row>
			<tr>
				<#list row as col>
				<th>${col.title}</th>
				</#list>
			</tr>
			</#list>
		</thead>
		<tbody>
		<#list rows as row>
			<tr>
				<#list flat_title as col>
				<td>${row[col.field]}</td>
				</#list>
			</tr>
		</#list>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="${flat_title?size}">打印时间: ${print_time?string("yyyy-MM-dd HH:mm:ss")}，共${total}条记录。</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
