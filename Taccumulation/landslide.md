2015/1/26 星期一 17:27:53 

# Landslide 笔记 #

*[Landslide]("https://github.com/adamzap/landslide")* 是基于谷歌 *html5slides* 模版，从 markdown文件、ReST文件或是textfile生成幻灯片的工具。

## 1. 基础要求 ##

需要安装 *python*, 以及下列模块：
	- jinja2
	- *pygments*    (用于代码块语法着色)

### 1.1 标记转换 ###

1. *markdown*，当使用*markdown*语法写幻灯片内容。
2. *docutils*,当使用*ReStructuredText*语法写幻灯片内容。
3. *textfile*，用于支持 textfile 文件。

### 1.2 可选 ###

	- *watchdog*,通过 *-w* 标识开启**watching/auto-regeneration**
	- *PrinceXML*,用于导出 PDF 文件。

********

## 2. 安装 ##

1. 通过 python 包管理工具 *pip* 安装：

		$pip install landslide

2. 通过源码安装：

		$git clone https://github.com/adamzap/landslide.git
		$cd landslide
		$python setup.py build
		$sudo pyhon setup.py install

## 3. 格式 ##

### 3.1 Markdown ###

	- Markdown 文件的后缀名必须是*.md,.markdn,.mdwn,.mdown,.markdown*。
	- 通过一级标题(h1)生成幻灯片标题。
	- 在 markdown 中通过水平线( --- )将内容分割成一页一页的幻灯片,最后一页幻灯片可以不加。
	- Your other slides should have a heading that renders to an h1 element。
	- 高亮代码块 (To highlight blocks of code, put !lang where lang is the pygment supported language identifier as the first indented line)

### 3.2 ReStructuredText ###

	- ReST 文件的后缀名必须是*.ret,.rest*,不支持 .txt。
	- Use headings for slide titles
	- 同上，用水平线 ( --- )分割每一张幻灯片。

### 3.3 Textfile ###

	- Textile cannot generate <hr />, so you must insert those manually to separate slides

## 4. 渲染翻译 ##

1. 运行 *landslide slides.md* 或者 *landslide slides.rst*
2. 生成 *presentation.html* 文件即可。

当系统有安装 *PrinceXML* 时，可以导出 PDF 文件：

3. $landslide README.md -d readme.pdf
4. $open readme.pdf

## 5. 视图 ##

- 按 *h* 显示帮助信息。
- 左右方向键用于翻页。
- 按 *t* 表格显示展示内容。
- 按 *esc* 显示全视图。

## 6. 命令行选项 ##

- -h,--help   显示帮助信息并退出。
- -c,--copy-theme   将主题问佳佳复制到当前的源文件夹。
- -b,--debug   将任何异常追踪显示到标准输入。
- -d FILE,--destination=FILE   指定目标文件的路径，允许 .html 和 .pdf 后缀。默认输出为 *presentation.html*。
- -e ENCODING,--encoding=ENCODING   指定文件编码方式 (默认为 *utf8*)。
- -i,--embed   嵌入样式表和JS内容，Base64编码的图像会表示为一份单独的文档。
- l LINENOS,--linenos=LINENOS   指定处理源代码行号的方式。有三个可选值：1. no (不输出行号)。 2. inline  3. table
- -o,--direct-output   打印生成的 HTML 代码到标准输入。不支持 PDF 导出。
- -q,--quite   不打印任何信息到标准输出。
- -r,--relative   
- -t THEME,--theme=THEME   指定主题名称，或 landslide 主题文件夹的路径。
- -v,--verbose   打印输出相信信息到标准输入(默认方式)。
- -w,--watch     监视源文件夹的改变，并自动生成对应展示文件。
- -x EXTENSIONS,--extensions=EXTENSIONS  用逗号分隔 Markdown 扩展。
- -m,--math-output  启用使用 *mathjax* 进行书序输出。

## 7. 演示配置 ##

*Landslide* 允许使用一个 *cfg* 配置文件来配置演示。配置文件使用 *cfg 语法*。  

例子：
		[landslide]
		theme  = /path/to/my/beautiful/theme
		source = 0_my_first_slides.md
				a_directory
				another_directory
				now_a_slide.markdown
				another_one.rst
		destination = myWonderfulPresentation.html
		css =    my_first_stylesheet.css
				my_other_stylesheet.css
		js =     jquery.js
				my_fancy_javascript.js
		relative = True
		linenos = inline

切记不要忘了声明 **[landslide]**。所有的配置文件都必须以 *.cfg* 结尾。使用配置文件生成演示文件，只需运行：

		$cd /path/to/your/presentation/sources
		$landslide config.cfg

Landslide 支持*宏*来提高演示效果。

**笔记：**
可以使用关键字 *.notes* 在slides 中增加笔记，例子

		# My Slide Title

		.notes:这里是笔记，默认被隐藏。

		显示的内容...
可以使用 数字键 *2* 来显示被隐藏的笔记。再次按下 *2*，则笔记又被隐藏。

## 8. 高级应用 ##

1. 设置目标文件

		$landslide slides.md -d ~/MyPresentations/presentation.html

不会自动生成文件夹，所以目标文件一定要是已经存在的。

2. 工作目录

		$landslide slides/
即，用 slides 文件夹里的所有文件生成一个输出演示文件。

3. 设置使用的 Landslide 主题

		$landslide slides.md -t mytheme
		$landslide slides.md -t /path/to/theme/dir

4. 嵌入 Base64 编码的图像

		$landslide slides.md -i

5. 导出 PDF 

		$landslide slides.md -d presentation.pdf

6. 启用数学符号
	
	**需要使用 Docutils 0.8 或更新版本写 Rest 风格的slides 文件。**
	
		$landslide slides.rst -m
		

## 9. 主题 ##

Landslide 主题的目录结构：

	mytheme/
	|-- base.html
	|-- css
	|	|-- print.css
	|	`-- screen.css
	`-- js
		`-- slides.js

如果没有指定主题，则使用默认主题。Css 是不可选的。
