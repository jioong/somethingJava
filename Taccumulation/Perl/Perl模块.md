2014/12/12 星期五 21:16:04 

# Perl 模块 #

## 找到模块 ##

模块有两种发布方式：  
1. 模块随附在Perl包中。  
2. 从[CPAN](http://search.cpna.org "CPNA")获得来自行安装。

## 安装模块 ##

当想要安装系统上没有的模块时，可以直接下载包并解开它，然后在shell中运行一连串的命令。(逗？)  
查看 README 和 INSTALL 两个文件，可以看到一些有用信息。  

如果该模块使用了 MakeMaker，多半可以使用如下方式：  

	$perl Makefile.PL
	$ make install