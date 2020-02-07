## 动态多数据源demo
![结构图](image.jpg "结构图") 
### 优点
运行时动态切换数据源

	MultipleDataSource.setDataSourceType(Target.DB2);
### 缺点
事务内无法切换数据源