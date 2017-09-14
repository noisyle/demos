<!DOCTYPE html>
<html lang="zh_CN">
<head>
<style>
@page {
  size: 11.69in 8.27in;
  margin: 0.7in 0.4in 0.7in 0.4in;
  @top-center {
    content: element(header);
  }
  @bottom-right {
    content: element(print-time);
  }
  @bottom-center {
    content: element(page-info);
  }
}
* {
  font-family: 'Arial Unicode MS';
  font-size: 12px;
}
div.header {
  display: block;
  text-align: center; 
  position: running(header);
}
div.print-time {
  display: block;
  text-align: center;
  position: running(print-time);
}
div.page-info {
  display: block;
  text-align: center;
  position: running(page-info);
}
@media screen {
  div.print-time {
    display: none;
  }
  div.page-info {
    display: none;
  }
}
span.page_number:before {
  content: counter(page);
}
span.page_count:before {
  content: counter(pages);
}
html, body {
  padding: 0px;
  margin: 0px;
}
table {
  width: 100%;
  border: 0;
  border-collapse: collapse;
  page-break-inside: auto;
  -fs-table-paginate: paginate;
  border-spacing: 0;
}
thead {
  display: table-header-group;
}
tfoot {
  display: table-footer-group;
}
td, th {
  border: 1px solid #000;
  padding: 10px;
  word-wrap: break-word;
  overflow: hidden;
}
th {
  background-color: #F5F5F5;
  text-align: center;
  page-break-inside: avoid;
  page-break-after: auto;
}
.page_break_after {
  page-break-after: always;
} 
</style>
</head>
<body>
<div class='header'><h1>${table_name}</h1></div>
<div class='print-time'>${print_time?string("yyyy-MM-dd HH:mm:ss")}</div>
<div class='page-info'>第<span class="page_number"></span>页，共<span class="page_count"></span>页</div>
<#list table_datas as table_data>
<div class='page_break_after'>
  <table cellspacing="0" cellpadding="0">
    <thead>
      <#list title as row>
      <tr>
        <#list row as col>
        <th>${col.title}</th>
        </#list>
      </tr>
      </#list>
    </thead>
    <tbody>
    <#list table_data.rows as row>
      <tr class="<#if (row_index+1)%10==0>page_break_after</#if>">
        <#list flat_title as col>
        <td>${row[col.field]}</td>
        </#list>
      </tr>
    </#list>
    </tbody>
    <#if table_data.footer??>
    <tfoot>
    <#list table_data.footer as row>
      <tr>
        <#list flat_title as col>
        <td>${row[col.field]}</td>
        </#list>
      </tr>
    </#list>
    </tfoot>
    </#if>
  </table>
</div>
</#list>
</body>
</html>
