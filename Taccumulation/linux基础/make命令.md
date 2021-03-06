2015/3/5 星期四 15:30:49 

# Make #

## Makefile 基本结构 ##

    TARGET: Dependency files
		COMMAND
	TARGET: Dependency files
		COMMAND
	...
	TARGET: Dependenvy files
		COMMAND

> TARGET: 表示*make*工具创建的目标体，通常是最后需要生成的文件名或者为了实现这个目的而必须的中间过程的文件名。
> Dependency files: 表示创建目标体所需要**依赖**的文件，通常一个目标依赖一个或者多个文件。如果其中的一个文件比目标文件的“时间戳”**新**，这个目标就被认为是“过时的”，需要重新编译生成。
> COMMAND: 表示创建目标体时需要运行的命令，它限定了*make*执行规则时需要的动作，通常**可由一行或者多行命令组成**。

如果*COMMAND*命令不与"TARGET:Dependency files" 在一行，那么需要以**[TAB]**字符作为本行的开头，[TAB]字符告诉*make*此行是一个命令行。

# Makefile #

*Makefile* 一般工作过程：

1. 读取 *Makefile*。根据 *make* 的执行选项，查找**当前目录**或者**其他目录**要执行的 *Makefile*。
2. 初始化*Makefile*。将制定的*Makefile* 中的**变量**进行替换，如果该*Makefile*中包含其他文件，则将其加载。
3. 解释规则。将*Makefile*中的执行规则进行解释，同时**推导文件中的隐藏规则**。其次，查找文件中的目标、依赖、命令之间的关系，为创建目标建立**关系链**。
4. 分析变更。根据依赖关系和“时间戳”，判断是否有依赖文件发生变化。如果有变化，则重新编译；如果没有变化，当前的目标不需要重新编译。
5. 执行。执行*Makefile*中的命令。

**make 命令的选项**

1. -f file   将指定当前目录下的 file 作为 Makefile。
2. -I dir    将 dir 作为被包含的 Makefile 所在的目录。
3. -C Dir    将指定目录下的 file 作为 Makefile。
4. -i        忽略所有命令执行的错误。
5. -j        输出执行规则中命令的详细信息。
6. -n        只打印**要执行**的命令，但并**不执行**这些命令。
7. -s        在执行命令时不显示命令。
8. -d        除打印正常的操作信息外，还打印调试信息。

**一个目标可以没有依赖文件，只有命令。**

## Makefile 变量 ##

在*Makefile* 中，变量是一个名字，不仅可以代表一个字符串，还可以用来代表文件名、编译选项、程序运行的选项参数、搜索源文件的目录，以及编译输出的目录。在*Makefile* 的目标、依赖、命令中任意引用变量的地方，在执行*make*命令后，都会被变量定义的值所取代。  
*Makefile* 中变量的特征：  
1. *Makefile* 中变量和函数的展开，是在 *make* 读取*Makefile* 文件时进行的，这里的变量不仅包括使用“=”定义变量，而且包含使用指示符*define*定义的。
2. 变量的命名可以包含字符、数字、下划线，但绝对不可以使用含有 **“：”，“#”，“=”或是空字符**的字符，同时变量中字母、数字、下划线以外的字符，应该尽量避免使用。
3. 变量对大小写敏感。
4. 自动化变量。 

### 变量的引用 ###

当定义了一个变量后，就可以在*Makefile* 的很多地方使用这个变量。变量的引用方式是：

    $(VARIABLE_NAME) 或 ${VARIABLE_NAME}

对一个变量的引用可以在*Makefile* 的任何上下文中，目标、依赖、命令、绝大多数指示符和新变量的赋值中。

### 变量的定义与赋值 ###

*Makefile* 文件在进行变量定义时通常可以采用两种方式，第一种是**递归展开定义法**，第二种是**直接展开定义法**。两者的差别在于：定义的方式和展开的时机不同。  
递归展开定义法可以使用之前没有定义过的变量，而直接展开定义法不允许引用变量之后定义过的变量。  

1. 递归展开定义法  

可以通过“=” 或*define*来定义变量。  
对使用递归展开定义的变量，其引用的地方是**严格的文本替换过程**。变量将会原样的被字符串替代。

2. 直接展开定义法  

为避免“递归展开”中可能出现的死循环和低效率问题，*Makefile* 中可使用**“：=”**定义变量。  

    Var := variable