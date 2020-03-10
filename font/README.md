## 使用系统字体

### 默认字体
默认情况下，会使用系统默认的衬线字体 (`Font.SERIF`)。

## 使用外部字体
支持 ttf 字体，以转换为 ttf 的思源宋体为例。[下载地址](https://github.com/Pal3love/Source-Han-TrueType)。

### 从classpath加载字体文件
1. 将 SourceHanSerifCN-Bold.ttf 和 SourceHanSerifCN-Regular.ttf 解压到 src/main/resources/font 目录下。
2. 修改 application.properties，设置 poster.regular-font 和 poster.bold-font。

### 从文件系统加载字体文件
1. 将 SourceHanSerifCN-Bold.ttf 和 SourceHanSerifCN-Regular.ttf 解压到磁盘上，目录不要包含中文。
2. 修改 application.properties，设置 poster.regular-font 和 poster.bold-font。
