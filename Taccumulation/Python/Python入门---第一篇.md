2014/12/21 星期日 12:43:52 
# Python 入门基础 #

## 0. 准备 ##

### 0.1 Python 标识符 ###

在Python里，标识符由字母、数字。下划线组成，不能以数字开头，区分大小写。  
**以下划线开头的标识符具有特殊意义：**  
1. 以单下划线开头(_foo)的代表不能直接访问的**类属性**，需要通过类提供的借口进行访问，不能用 “from XXX import *”而导入。  
2. 以双下划线开头(__foo)代表**类的私有成员**。  
3. 以双下划线**开头和结尾的**(__foo__)代表Python里特殊方法专用的标识。如__init__()代表类的构造函数。

### 0.2 Python 保留字符 ###

保留字不能用作常数或变数，或任何其他标识符名称。  
所有Python的关键字**只包含小写字母**。  
**关键字：**  
<center>![key_words]("./images/keywords.png")</center>

### 0.3 行和缩进 ###

Python的代码块不适用大括号({})来控制类，函数及其他逻辑判断。  
**Python最具特色的就是用缩进来写模块。**  
缩进的空白数量是可变的，但是所有代码块语句必须包含相同的缩进空白缩量，这个必须严格执行。  

### 0.4 多行语句 ###

Python一般以新行作为语句的结束符。但是，可以使用协杠(\)将一行的语句分为多行显示：  

    例子：
		total = item_one + \
				item_two + \
				item_three
	等价于
		total = item_one + item_two + item_three  

若，语句汇总包含 [], {}, () 括号就不需要使用多行连接符(\).  

    例子：
		days = ['Monday', 'Tuesday', 'Wednesday',
				'Thursday', 'Friday']

### 0.5 Python 引号 ###

Python接受单引号(')，双引号(")，三引号(''' """)来表示字符串。引号的开始与结束必须是相同类型的。  
其中，三引号可以由多行组成，是编写多行文本的快捷语法。常用于文档字符串，在文件的特定地点，被当做注释。  

    例子：
		word = 'word'
		sentence = "This is a sentence."
		paragraph = """This is a paragraph. It is
					made up of multiple lines and sentences."""  

### 0.6 Python 注释 ###

Python中单行注释用 # 开头， 没有块注释。

### 0.7 Python 空行 ###

函数之间或类的方法之间用空格分割，表示一段新的代码开始。类和函数入口之间也用一行空行分割，**以突出函数入口的位置。**  
空行与代码缩进不同，**空行不是Python语法的一部分。**书写时不插入空行，Python解释器运行也不会出错。  
**但是，空行的作用在于分割两端不同功能或含义的代码，便于日后代码的维护或重构。**  

### 0.8 同一行显示多条语句 ###

语句之间使用分号(;)隔开就可以了。  

    例子：
		import sys; x = 'foo'; sys.stdout.write(x + '\n')

### 0.9 多个语句构成代码组 ###

代码组：**缩进相同的一组语句构成一个代码块。**  


## 1. 变量 ##

### 1.1 Python 变量类型 ###

变量存储在内存中的值，这就意味着在创建变量时会在内存中开辟一个空间。基于变量的数据类型，解释器会**分配指定内存，并决定什么书籍可以被存储在内存中。**  
因此，变量可以存储不同的数据类型，这些变量可以存储整数、小数或字符。  

### 1.2 变量赋值 ###

1. **Python中的变量不需要声明，变量的赋值操作即是变量声明和定义的过程。**  
2. 每个变量在内存中创建，都包括变量的标识，名称和数据这些信息。  
3. **每个变量在使用前必须赋值，变量赋值以后该变量才会被创建。**
4. 变量赋值操作符 =

### 1.3 多个变量赋值 ###

Python允许同时为多个变量赋值。  

    例子：
		a = b = c = 1  #创建一个整型对象，值为1，三个变量被分配到相同的内存空间上。 

也可以为**多个对象**指定**多个变量**：

    例子：
		a, b, c = 1, 2, "tom"  #创建两个整型对象 1 和 2 分配给变量 a 和 b ,字符串对象分配给变量 c


### 1.4 标准数据类型 ###

Python有五个标准的数据类型：  
1. Numbers     (数字)  
2. String      (字符串)  
3. List        (列表)  
4. Tuple       (元祖)  
5. Dictionary  (字典)

#### 1.4.1 Python数字 ####

数字数据类型用于存储数值。它们是**不可变的**数据类型，这意味着改变数字数据类型会**分配一个新的对象。**  
当指定一个值时，Number对象就会被创建：  

    例子：  
		var1 = 1
		var2 = 10 

使用**del语句**可以删除一些对象引用。  

    del语句的语法：
		del var1[,var2[,var3[...,varN]]]]

Python支持四种不同的数值类型：  

    1. int      (有符号整型)
    2. long     (长整形(也可以代表八进制和十六进制))
    3. float    (浮点型)
    4. complex  (复数)

<center>![numbers]("./images/number_example.png" "数值类型")</center>

#### 1.4.2 Python 字符串 ####

字符串是由数字、字母、下划线组成的一串字符。

    一般记为：
		s = "a1a2a3...an" (n >= 0)

它是编程语言中表示文本的数据类型。  
Python的字串列表有两种取值顺序：
1. 从左到右索引默认 0 开始的，最大范围是字符串长度少 1 。
2. 从右到左索引默认 -1 开始，最大范围是字符串开头。

要取得一段子串时，可以用**变量[头下标:尾下标]**，借款已截取相应的字符串。其中，下标是从 0 开始算起，可以是正数或负数，下标可以为空表示取头或尾。

    例子：
		s = 'ilovepython'
		s[1:5] = 'love'  # 含头不含尾

当使用冒号分割字符串，Python返回一个新的对象。结果包含了以这对偏移标识的连续内容。  
**加号(+)是字符串的连接运算符，星号(*)是重复操作。**

#### 1.4.3 Python 元祖 ####

元祖是另一种数据类型，类似于 List。  
元祖用 “()” 标识。内部元素用逗号隔开。但是元素不能**二次赋值**，相当于只读列表。  

**元祖是不允许更新的。**

#### 1.4.4 Python字典 ####

字典(dictionary)是除列表以外Python之中**最灵活的内置数据类型。**  
列表是**有序**的对象集合，字典是**无序**的对象集合。  
区别在于：字典当中的元素是通过**键**来存取，而不是通过偏移存取。  

字典用 “{}” 标识。字典由索引(key)和它对应的值(value)组成。

    例子：
		tinydict = {'name': 'john','code':6734, 'dept': 'sales'}
		dict = {}
		dict['one'] = "This is one"
		dict[2] = "This is two"

### 1.5 Python 数据类型转换 ###

数据类型的转换，只需要将数据类型作为函数名即可。  

这些函数会返回一个新的对象，表示转换的值。

<center><table class="reference"> <tbody><tr><th>函数</th><th>描述</th></tr> <tr valign="top"> <td> <p>int(x [,base])</p> </td> <td> <p>将x转换为一个整数</p> </td> </tr> <tr valign="top"> <td> <p>long(x [,base] )</p> </td> <td> <p>将x转换为一个长整数</p> </td> </tr> <tr valign="top"> <td> <p>float(x)</p> </td> <td> <p>将x转换到一个浮点数</p> </td> </tr> <tr valign="top"> <td> <p>complex(real [,imag])</p> </td> <td> <p>创建一个复数</p> </td> </tr> <tr valign="top"> <td> <p>str(x)</p> </td> <td> <p>将对象 x 转换为字符串</p> </td> </tr> <tr valign="top"> <td> <p>repr(x)</p> </td> <td> <p>将对象 x 转换为表达式字符串</p> </td> </tr> <tr valign="top"> <td> <p>eval(str)</p> </td> <td> <p>用来计算在字符串中的有效Python表达式,并返回一个对象</p> </td> </tr> <tr valign="top"> <td> <p>tuple(s)</p> </td> <td> <p>将序列 s 转换为一个元组</p> </td> </tr> <tr valign="top"> <td> <p>list(s)</p> </td> <td> <p>将序列 s 转换为一个列表</p> </td> </tr> <tr valign="top"> <td> <p>set(s)</p> </td> <td> <p>转换为可变集合</p> </td> </tr> <tr valign="top"> <td> <p>dict(d)</p> </td> <td> <p>创建一个字典。d 必须是一个序列 (key,value)元组。</p> </td> </tr> <tr valign="top"> <td> <p>frozenset(s)</p> </td> <td> <p>转换为不可变集合</p> </td> </tr> <tr valign="top"> <td> <p>chr(x)</p> </td> <td> <p> 将一个整数转换为一个字符</p> </td> </tr> <tr valign="top"> <td> <p>unichr(x)</p> </td> <td> <p>将一个整数转换为Unicode字符</p> </td> </tr> <tr valign="top"> <td> <p>ord(x)</p> </td> <td> <p> 将一个字符转换为它的整数值</p> </td> </tr> <tr valign="top"> <td> <p>hex(x)</p> </td> <td> <p> 将一个整数转换为一个十六进制字符串</p> </td> </tr> <tr valign="top"> <td> <p>oct(x)</p> </td> <td> <p> 将一个整数转换为一个八进制字符串</p> </td> </tr> </tbody></table></center>

## 2. 运算符 ##

### 2.1 Python 算术运算符 ###

以下假设变量 a 为 10，变量 b 为 20
<center>
![arithmetic operator]("./images/arithmetic_operator.png" "算术运算符")
</center>

### 2.2 Python 比较运算符 ###

<center>
![comparison operator]("./images/comparison_operator.png"  "比较运算符")
</center>

### 2.3 Python 赋值运算符 ###

<center>
![assigning operator]("./images/assigning_operator.png"  "赋值运算符")
</center>

### 2.4 Python 位运算符 ###

按位运算符是把数字看作二进制来进行计算的。  

<center>
![bit operator]("./images/bit_operator.png" "位运算符")
</center>

### 2.5 Python 逻辑运算符 ###

<center>
![logical operator]("./images/logical_operator.png" "逻辑运算符")
</center>

### 2.6 Python 成员运算符 ###

<center>
![member operator]("./images/member_operator.png"  "成员运算符")
</center>

### 2.7 Python 身份运算符 ###

身份运算符**用于比较两个对象的存储单元。**
<center>
![identity operator]("./images/identity_operator.png"  "身份运算符")
</center>

### Python 运算符优先级 ###

表格列出从最高到最低优先级的所有运算符：
<center>
![priority level]("./images/priority_level.png"  "运算符优先级")
</center>

## 3. 控制结构 ##

### 3.1 Python 条件语句 ###

**Python并不支持 switch 语句。**
Python条件语句通过一条或多条语句的执行结果(true or false)来决定执行的代码块。
<center>
![if]("./images/if.png" "if 条件语句")
</center>

**Python程序语言制定任何非 0 和非空(null)值为 true, 0 或者 null 为 false。**  
Python编程中 if 语句用于控制程序的执行，基本形式为：

    if 判断条件 ：
		执行语句...      #执行内容可以多行，以缩进来区分表示同一范围。
	else:               # else 为可选语句
		执行语句...

当判断条件为多个值时，可以使用以下形式：

    if 判断条件1:
		执行语句1...
	elif 判断条件2:
		执行语句2...
	elif 判断条件3:
		执行语句3...
	...
	else:
		执行语句...

### 3.2 Python 循环语句 ###

循环语句允许执行一个语句或语句组多次。  
<center>
![circulate]("./images/circulate.png" "循环语句")
</center>

#### 3.2.1 Python while循环语句 ####

基本形式为：

    while 判断条件:     #判断条件可以是任何表达式。 当条件为 false 时，循环结束。
		执行语句...     # 执行语句可以是单个语句或语句块。

执行流程：
<center>
![while]("./images/while.png" "while循环语句")
</center>

while 语句有两个重要的命令 continue、break来跳过循环。  
1. continue   用于跳过该次循环。
2. break      用于退出循环。

**循环使用 else 语句**

else 语句会在循环**正常执行完(即while不是通过break跳出而中断)的情况下执行**

    例子：
	#!/usr/bin/python

	count = 0
	while count < 5:
   		print count, " is  less than 5"
   		count = count + 1
	else:
   		print count, " is not less than 5"

输出为：

    0 is less than 5
	1 is less than 5
	2 is less than 5
	3 is less than 5
	4 is less than 5
	5 is not less than 5

#### 3.2.2 Python for循环语句 ####

for循环可以遍历任何序列的项目，如一个列表或者一个字符串。  

for循环语法：

    for iterating_var in sequence
		statements(s)

执行流程：
<center>
![for]("./images/for.png" "for循环")
</center>

**通过序列索引迭代**  
通过索引遍历也可以执行循环。  

    fruits = ['banana', 'apple',  'mango']
	for index in range(len(fruits)):
	   print 'Current fruit :', fruits[index]     #内置函数 len()返回列表的长度，即元素个数。 range()返回一个序列的数

**循环使用 else语句**  

else 中的语句会在循环正常执行完（即 for 不是通过 break 跳出而中断的）的情况下执行

#### 3.2.3 Python 循环嵌套 ####

Python语言允许在一个循环体力嵌套另一个循环。任何循环都可以相互嵌套。  

    for循环嵌套：
		for iterating_var in sequence:
   			for iterating_var in sequence:
      			statements(s)
  			statements(s)

#### 3.2.4 Python break语句 ####

Python中的break语句，与C中类似，打破最小封闭for或while循环。  
break语句用来终止循环语句，**即循环条件没有false或序列还没有被完全递归完，**也会停止循环执行语句。  
如果使用嵌套循环，break语句将停止执行最深层的循环，并开始执行下一行代码。

**执行流程：**
<center>
![break]("./images/break.png" "break流程")
</center>

#### 3.2.5 Python continue语句 ####
Python continue 语句跳出本次循环，而break跳出整个循环。  
continue 语句用来告诉Python跳过当前循环的剩余语句，然后继续进行下一轮循环。

**执行流程**
<center>
![continue]("./images/continue.png" "continue循环")
</center>

#### 3.2.6 Python pass语句 ####

pass是空语句，是为了保持程序结构的完整性。

    例子：
		#!/usr/bin/python

		for letter in 'Python': 
   			if letter == 'h':
     			pass
      			print 'This is pass block'
   		print 'Current Letter :', letter

输出为：

    Current Letter : P
	Current Letter : y
	Current Letter : t
	This is pass block
	Current Letter : h
	Current Letter : o
	Current Letter : n

## 4. Python 日期和时间 ##

Python程序能以很多方式处理日期和时间。转换日期格式是一个常见的例行琐事。  
Python有一个 time and calendar 模组。

### 4.1 什么是 Tick? ###

时间间隔是以秒为单位的浮点小数。  
每个时间戳都以自从1970年1月1日午夜经过了多长时间来表示。

### 4.2 什么是时间元祖？ ###

很多Python函数用一个元祖装起来的 9 组数组处理时间：
<center>
![struct time]("./images/struct_time.png" "时间元祖")
</center>

结构具有如下属性：
<center>
![property]("./images/struct_time_property.png" "属性")
</center>

### 4.3 获取当前时间 ###

从返回浮点数的时间戳方式向时间元祖转换，只要将浮点数传递给**localtime()**之类的函数。  

    例子：
		#!/usr/bin/python
		import time;

		localtime = time.localtime(time.time())
		print "Local current time :", localtime
输出为：

    Local current time : time.struct_time(tm_year=2013, tm_mon=7, 
	tm_mday=17, tm_hour=21, tm_min=26, tm_sec=3, tm_wday=2, tm_yday=198, tm_isdst=0)

### 4.4 获取格式化时间 ###

可以根据需求选取各种格式，但是最简单的获取可读的时间模式的函数是**asctime()**。

    例子：
		#!/usr/bin/python
		import time;

		localtime = time.asctime( time.localtime(time.time()) )
		print "Local current time :", localtime
输出为：

    	Local current time : Tue Jan 13 10:17:09 2009

### 4.5 获取某月日历 ###

Calendar模块用来处理年历和月历。

    例子：
		#!/usr/bin/python
		import calendar

		cal = calendar.month(2008, 1)
		print "Here is the calendar:"
		print cal;

输出为：

    Here is the calendar:
    	January 2008
	Mo Tu We Th Fr Sa Su
    1  2  3  4  5  6
	7  8  9 10 11 12 13
	14 15 16 17 18 19 20
	21 22 23 24 25 26 27
	28 29 30 31

### 4.6 Time 模块 ###

Time模块包含以下内置函数，既有时间处理的，也有转换时间格式的：

<center>
![time]("./images/time.png" "Time模块")
</center>

Time模块包含两个非常重要的属性：
<center>
![time property]("./images/time_property.png" "Time模块属性")
</center>

### 4.7 Calendar 模块 ###

此模块的函数都是日历相关的。  
星期一是默认的每周第一天，星期天是默认的最后一天。更改设置需调用calendar.setfirstweekday()函数。

**模块内置函数**
<center>
![calendar]("./images/calendar.png" "日历模块")
</center>

### 4.8 其他相关模块和函数 ###

在Python中，其他处理日期和时间的模块还有：

	- datatime模块
	- pytz模块
	- ateutil模块


## 5. Python 函数 ##

函数是组织好的，可重复使用的，用来实现单一，或相关联功能的代码段。  
函数能提高应用的模块性和代码的重复利用率。

### 5.1 定义一个函数 ###

可以自己定义想要的功能函数，以下是简单规则：

	- 函数代码块以**def关键词开头，**后接函数标识符名称和圆括号()。
	- 任何传入参数和自变量必须放在圆括号中间。圆括号之间可以用于定义参数。
	- 函数的第一行语句可以选择性地使用文档字符串---用于存放函数说明。
	- 函数内容以冒号起始，并且缩进。
	- return [expression]结束函数，选择性地返回一个值给调用方。不带表达式的 return 相当于返回 None。

**语法：**

    def functionname( parameters ):
   		"函数_文档字符串"
   		function_suite
   		return [expression]

默认情况下，参数值和参数名称是按函数声明中定义的顺序匹配起来的。

**实例：**

    def printme( str ):
   		"打印传入的字符串到标准显示设备上"
   		print str
   		return

### 5.2 函数调用 ###

定义一个函数只给了函数一个名称，指定了函数里包含的参数和代码块结构。 

**调用实例：**

    #!/usr/bin/python
 
	# Function definition is here
	def printme( str ):
   		"打印任何传入的字符串"
  		 print str;
   		return;
 
	# Now you can call printme function
	printme("我要调用用户自定义函数!");
	printme("再次调用同一函数");

### 5.3 按值传递参数和按引用传递参数 ###

**所有参数(自变量)在Python里都是按引用传递的**。如果你在函数里修改了参数，那么在调用这个函数的函数里，原始的参数也被改变了。

### 5.4 参数 ###

以下是调用函数时可使用的正式参数类型：

	- 必备参数
	- 命名参数
	- 缺省参数
	- 不定长参数

#### 5.4.1 必备参数 ####

必备参数须以正确的顺序传入函数。**调用时的数量必须和声明时的一样。**

#### 5.4.2 命名参数 ####

命名参数和函数调用关系密切，调用方用参数的名字确定传入的参数值。可以跳过不传的参数或者乱序传参，因为Python解释器能够**用参数名匹配参数值。**

**实例：**

    #!/usr/bin/python
 
	#可写函数说明
	def printme( str ):
   		"打印任何传入的字符串"
   		print str;
   		return;
 
	#调用printme函数
	printme( str = "My string");

#### 5.4.3 缺省参数 ####

调用函数时，缺省参数的值如果没有传入，则被认为是默认值。

**实例**

    #!/usr/bin/python
 
	#可写函数说明
	def printinfo( name, age = 35 ):
   		"打印任何传入的字符串"
   		print "Name: ", name;
  		print "Age ", age;
  		return;
 
	#调用printinfo函数
	printinfo( age=50, name="miki" );
	printinfo( name="miki" );

#### 5.4.4 不定长参数 ####

**基本语法：**

    def functionname([formal_args,] *var_args_tuple ):
   		"函数_文档字符串"
   		function_suite
   		return [expression]

加了星号（*）的变量名会存放所有未命名的变量参数。

### 5.5 匿名函数 ###

用**lambda关键词能创建小型匿名函数。**该种函数得名于省略了用def声明函数的标准步骤。

	- lambda函数能接受任何数量的参数但只能返回一个表达式的值，同时（不能包含命令或多个表达式？）
	- 匿名函数不能直接调用 print，因为 lambda需要一个表达式。
	- lambda函数拥有自己的命名空间，且不能访问自有参数列表之外或全局名字空间里的参数。
	- 虽然 lambda函数看起来只能写一行，却不等同于C或C++的内联函数，后者的目的是调用小函数时不占用栈内存从而增加运行效率。

**语法：**

    lambda [arg1 [,arg2,.....argn]]:expression

**实例：**

    #!/usr/bin/python
 
	#可写函数说明
	sum = lambda arg1, arg2: arg1 + arg2;
 
	#调用sum函数
	print "Value of total : ", sum( 10, 20 )
	print "Value of total : ", sum( 20, 20 )

输出：

    Value of total :  30
	Value of total :  40

### 5.6 return 语句 ###

return语句[表达式]退出函数，选择性地向调用方返回一个表达式。不带参数值的return语句返回None。

### 5.7 变量作用域 ###

一个程序的所有的变量并不是在哪个位置都可以访问的。访问权限决定于这个变量是在哪里赋值的。  
变量的作用域决定了在哪一部分程序可以访问哪个特定的变量名称。两种最基本的变量作用域如下：

	- 全局变量
	- 局部变量

**定义在函数内部的变量拥有一个局部作用域，定义在函数外的拥有全局作用域。**  
局部变量只能在其被声明的函数内部访问，而全局变量可以在整个程序范围内访问。调用函数时，所有在函数内声明的变量名称都将被加入到作用域中。

## 6. Python 模块 ##

模块能够有逻辑的组织Python代码段。  
把相关的代码分配到一个模块里能让代码更好用，更易懂。  
模块也是Python对象，具有随机的名字属性用来绑定或引用。  
**简单地说，模块就是一个保存了Python代码的文件。**模块能定义函数、类和变量。模块里也能包含可执行的代码。

> 一个叫做aname的模块里的Python代码一般都能在一个叫aname.py的文件中找到

### 6.1 import语句 ###

想使用Python源文件，只需在另一个源文件里执行 import 语句。语法如下：

    import module1[, module2[,... moduleN]

当解释器遇到 import 语句时，如果模块在当前的搜索路径就会被导入。  
**搜索路径**是一个解释器会先进行搜索的搜有目录的列表。

**实例：**

    #!/usr/bin/python
 
	# 导入模块
	import support
 
	# 现在可以调用模块里包含的函数了
	support.print_func("Zara")

**一个模块只会被导入一次，不管你执行了多少次import。这样可以防止导入模块被一遍又一遍地执行。**

### 6.2 from ... import 语句 ###

Python的from语句让你从模块中导入一个**指定的部分**到当前命名空间中。语法如下：

    from modname import name1[, name2[, ... nameN]]

**实例：**

    from fib import fibonacci  # 导入模块 fib 中的 fibonacci函数

**这个声明不会把整个fib模块导入到当前的命名空间中，它只会将fib里的fibonacci单个引入到执行这个声明的模块的全局符号表。**

### 6.3 from ... import * 语句 ###

把一个模块的所有内容全都导入到当前的命名空间也是可行的，只需使用如下声明：

    from modname import *

**这提供了一个简单的方法来导入一个模块中的所有项目。然而这种声明不该被过多地使用。**

### 6.4 定位模块 ###

当导入一个模块时，Python解释器对模块位置的搜索顺序是：

	- 当前目录。
	- 如果不在当前目录，Python则搜索在shell变量PYTHONPATH下的每个目录。
	- 如果都找不到，Python会察看默认路径。Unix下，默认路径一般为 /usr/local/lib/python

**模块搜索路径存储在system模块的sys.path变量中。变量里包含当前目录，PYTHONPATH和由安装过程决定的默认目录。**

### 6.5 PYTHONPATH 变量 ###

作为环境变量，PYTHONPATH由装在一个列表里的许多目录组成。PYTHONPATH的语法和shell变量PATH的一样。

1. 在Windows系统，典型的PYTHONPATH如下：

    set PYTHONPATH=c:\python20\lib;

2. 在Unix系统，典型的PYTHONPATH如下：

    set PYTHONPATH=/usr/local/lib/python

### 6.6 命名空间和作用域 ###

1. 变量是拥有匹配对象的名字(标识符)。**命名空间**是一个包含了变量名称们(键)和它们各自相应的对象们(值)的**字典**。  
2. 一个Python表达式可以访问局部命名空间和全局命名空间了的变量。如果一个局部变量和一个全局变量重名，则局部变量会覆盖全局变量。  
3. 每个函数都有自己的命名空间。类的方法的作用域规则和通常函数一样。  
4. Python会智能地猜测一个变量是局部变量还是全局变量，它**假设任何在函数内赋值的变量都是局部的。**因此，如果要给全局变量在一个函数里赋值，必须使用**global语句。**global VarName的表达式会告诉Python，VarName是一个全局变量。这样Python就不会在局部命名空间里寻找这个变量了。

### 6.7 dir() 函数 ###

dir()函数返回一个排好序的字符串列表，内容是一个模块里定义过的名字。  
返回的列表容纳了在一个模块里定义的所有模版。变量和函数。

### 6.8 globals() 和 locals() 函数 ###

1. 根据调用地方的不同，globals()和locals()函数可被用来返回全局和局部命名空间里的名字。  
2. 如果在函数内部调用locals()，返回的是所有能在该函数里访问的**局部名字**（？）。  
3. 如果在函数内部调用globals()，返回的是所有在该函数里能访问的**全局名字**。  
4， 两个函数的**返回类型都是字典**。所以名字们能用keys()函数摘取。

### 6.9 reload() 函数 ###

当一个模块被导入到一个脚本，**模块顶层部分**的代码只会被执行一次。  
因此，如果你想重新执行模块里顶层部分的代码，可以用reload()函数。该函数会重新导入之前导入过的模块。语法如下：

    reload(module_name)

在这里，module_name要直接放模块的名字，而不是一个字符串形式。

**实例：**

    reload(hello)      #重载 hello 模块

### 6.10 Python中的包 ###

包是一个**分层次的文件目录结构**，它定义了一个由模块及子包，和子包下的子包等组成的Python的应用环境。

## 7. Python 文件 I/O ##

### 7.1 打印到屏幕 ###

最简单的输出方法是 print 语句，可以传递零个或多个用逗号隔开的表达式。此函数将传递的表达式转换成一个字符串表达式，并将结果写到标准输出。

**实例：**

    #!/usr/bin/python
 
	print "Python is really a great language,", "isn't it?";

### 7.2 读取键盘输入 ###

Python提供了两个内置函数从**标准输入**读入一行文本，默认的标准输入是键盘。如下：

1. raw_input  
2. input

#### 7.2.1 raw_input函数 ####

raw_input([prompt]) 函数从标准输入读取一个行，并返回一个字符串（**去掉结尾的换行符**）。

**实例：**

    #!/usr/bin/python
 
	str = raw_input("Enter your input: ");
	print "Received input is : ", str

#### 7.2.2 input函数 ####

input([prompt]) 函数和raw_input([prompt]) 函数基本可以互换，但是input会假设你的输入是一个**有效的Python表达式**，并返回运算结果。  

**实例：**

    #!/usr/bin/python
 
	str = input("Enter your input: ");
	print "Received input is : ", str

输出为：

    Enter your input: [x*5 for x in range(2,10,2)]
	Recieved input is :  [10, 20, 30, 40]

### 7.3 打开和关闭文件 ###

Python提供了必要的函数和方法进行默认情况下的文件基本操作。你可以用**file对象**做大部分的文件操作。

1. open()函数  
必须先用open()函数打开一个文件，**创建一个file对象，**相关的辅助方法才可以调用它进行读写。  

**语法：**

    file object = open(file_name [, access_mode][, buffering])

其中，
	- file_name      变量是一个包含了要访问的文件名称的字符串值。
	- access_mode    决定了打开文件的模式：**只读、写入、追加等。**这个参数是可选的，默认为只读(r)。
	- buffering      如果 buffering 值设置为 0 ，就不会有**寄存器**。如果 buffering 设置为 1 ， 访问文件时会**寄存行**。如果将 buffering 的值设为大于 1 的整数，表明这是寄存区的缓冲大小。如果取负值，寄存区的缓冲大小则为系统默认。

**打开文件模式列表：**
<center>
![open file]("./images/open_file_mode.png"  "文件打开模式")
</center>

#### 7.3.1 file对象的属性 ####

一个文件被打开后，会返回一个 file 对象，可以得到有关该文件的各种信息。

**file 对象属性**
<center>
![file object]("./images/file_object_properity.png"  "file对象属性")
</center>

2. close()函数

file对象的 close()方法刷新缓冲区里任何还没写入的信息，并关闭该文件。关闭之后就不能在进行写入。  
当一个文件对象的引用被重新指定给另一个文件时，Python会关闭之前的文件。用close() 方法关闭文件是一个很好的习惯。  

**语法**  

    fileObject.close();

### 7.4 读写文件 ###

1. write()方法

write()方法可以将任何字符串写入一个打开的文件。需要注意的重点是，Python字符串可以是二进制数据，而不是仅仅是文字。  
write()方法不在字符串的结尾添加换行符("\n").

**语法：**

    fileObject.write(string);

被传递的参数是要写入已打开文件的内容。	

2. read()方法

read（）方法从一个打开的文件中读取一个字符串。需要重点注意的是，Python字符串可以是二进制数据，而不是仅仅是文字。  

**语法：**

    fileObject.read([count]);

被传递的参数是要从已打开文件中读取的**字节计数。**该方法从文件的开头开始读入，如果没有传入 count，它会尝试尽可能多的读取更多的内容，很可能是知道文件的末尾。

### 7.5 文件位置 ###

1. tell()方法告诉你文件内的当前位置。换句话说，下一次的读写会发生在文件开头这么多字节之后。
2. seek(offset[,from])方法**改变当前文件的位置**。offset变量表示要移动的字节数。from变量指定开始移动字节的参考位置。

**如果from被设为0，这意味着将文件的开头作为移动字节的参考位置。如果设为1，则使用当前的位置作为参考位置。如果它被设为2，那么该文件的末尾将作为参考位置。**

### 7.6 重命名和删除文件 ###

Python的**os模块**提供了帮你执行文件处理操作的方法，比如重命名和删除文件。  
要使用这个模块，你必须先导入它，然后可以调用相关的各种功能。

1. rename()方法：

rename()方法需要两个参数，当前文件名和新文件名。  
**语法**

    os.rename(current_file_name, new_file_name)

2. remove()方法：

remove()方法删掉文件，需要提供要删除的文件名作为参数。  
**语法**

    os.remove(file_name)

### 7.7 Python里的目录 ###

所有文件都包含在各个不同的目录下，不过Python也能轻松处理。os模块有许多方法能帮你创建，删除和更改目录。

1. mkdir()方法：

可以使用os模块的mkdir()方法在**当前目录下**创建新的目录们。你需要提供一个包含了要创建的目录名称的参数。  
**语法：**

    os.mkdir("newdir")

2. chdir()方法：

可以用chdir()方法来**改变当前的目录**。chdir()方法需要的一个参数是你想设成当前目录的目录名称。  
**语法：**

    os.chdir("newdir")

3. getcwd()方法：

getcwd()方法显示当前的工作目录。  
**语法：**

    os.getcwd()

4. rmdir()方法：

rmdir()方法删除目录。传递的参数为要删除的目录。  
在删除该目录之前，它的所有内容应该先被清除。  
**语法：**

    os.rmdir('dirname')

### 7.8 文件、目录相关的方法 ###

三个重要的方法来源能对Windows和Unix操作系统上的文件及目录进行一个广泛且实用的处理及操控，如下：

	- file对象方法：提供了操作文件的一系列方法。
	-  os 对象方法：提供了处理文件及目录的一系列方法。  

## 8. Python 异常处理 ##

python提供了两个非常重要的功能来处理python程序在运行中出现的异常和错误。

	- 异常处理。
	- 断言(assertions)。

### 8.1 Python 标准异常 ###

**异常列表：**
<center>
<table class="reference"> <tbody><tr> <th> 异常名称</th><th> 描述</th> </tr><tr> </tr><tr><td> BaseException</td><td> 所有异常的基类</td></tr><tr><td> SystemExit</td><td>解释器请求退出</td></tr><tr><td> KeyboardInterrupt</td><td> 用户中断执行(通常是输入^C)</td></tr><tr><td> Exception</td><td>常规错误的基类</td></tr><tr><td> StopIteration </td><td>迭代器没有更多的值</td></tr><tr><td> GeneratorExit </td><td>生成器(generator)发生异常来通知退出</td></tr><tr><td> StandardError</td><td> 所有的内建标准异常的基类</td></tr><tr><td> ArithmeticError </td><td>所有数值计算错误的基类</td></tr><tr><td> FloatingPointError </td><td>浮点计算错误</td></tr><tr><td> OverflowError</td><td> 数值运算超出最大限制</td></tr><tr><td> ZeroDivisionError</td><td> 除(或取模)零 (所有数据类型)</td></tr><tr><td> AssertionError</td><td> 断言语句失败</td></tr><tr><td> AttributeError</td><td> 对象没有这个属性</td></tr><tr><td> EOFError </td><td>没有内建输入,到达EOF 标记</td></tr><tr><td> EnvironmentError </td><td>操作系统错误的基类</td></tr><tr><td> IOError </td><td>输入/输出操作失败</td></tr><tr><td> OSError </td><td>操作系统错误</td></tr><tr><td> WindowsError</td><td> 系统调用失败</td></tr><tr><td> ImportError </td><td>导入模块/对象失败</td></tr><tr><td> LookupError </td><td>无效数据查询的基类</td></tr><tr><td> IndexError</td><td> 序列中没有此索引(index)</td></tr><tr><td> KeyError</td><td> 映射中没有这个键</td></tr><tr><td> MemoryError</td><td> 内存溢出错误(对于Python 解释器不是致命的)</td></tr><tr><td> NameError</td><td> 未声明/初始化对象 (没有属性)</td></tr><tr><td> UnboundLocalError </td><td>访问未初始化的本地变量</td></tr><tr><td> ReferenceError</td><td> 弱引用(Weak reference)试图访问已经垃圾回收了的对象</td></tr><tr><td> RuntimeError </td><td>一般的运行时错误</td></tr><tr><td> NotImplementedError</td><td> 尚未实现的方法</td></tr><tr><td> SyntaxError</td><td>Python 语法错误</td></tr><tr><td> IndentationError</td><td> 缩进错误</td></tr><tr><td> TabError</td><td> Tab 和空格混用</td></tr><tr><td> SystemError </td><td>一般的解释器系统错误</td></tr><tr><td> TypeError</td><td> 对类型无效的操作</td></tr><tr><td> ValueError </td><td>传入无效的参数</td></tr><tr><td> UnicodeError </td><td>Unicode 相关的错误</td></tr><tr><td> UnicodeDecodeError </td><td>Unicode 解码时的错误</td></tr><tr><td> UnicodeEncodeError </td><td>Unicode 编码时错误</td></tr><tr><td> UnicodeTranslateError</td><td> Unicode 转换时错误</td></tr><tr><td> Warning </td><td>警告的基类</td></tr><tr><td> DeprecationWarning</td><td> 关于被弃用的特征的警告</td></tr><tr><td> FutureWarning </td><td>关于构造将来语义会有改变的警告</td></tr><tr><td> OverflowWarning</td><td> 旧的关于自动提升为长整型(long)的警告</td></tr><tr><td> PendingDeprecationWarning</td><td> 关于特性将会被废弃的警告</td></tr><tr><td> RuntimeWarning </td><td>可疑的运行时行为(runtime behavior)的警告</td></tr><tr><td> SyntaxWarning</td><td> 可疑的语法的警告</td></tr><tr><td> UserWarning</td><td> 用户代码生成的警告</td></tr></tbody></table>
</center>

### 8.2 什么是异常？ ###

异常算是一个事件，该事件会在程序执行过程中发生，影响程序的正常执行。一般情况下，在Python无法正常处理程序时就会发生一个异常。  
**异常是Python对象，表示一个错误。**  
当Python脚本发生异常时**需要捕获处理它**，否则程序会终止执行。

### 8.3 异常处理 ###

捕捉异常可以使用try/except语句。  
try/except语句用来检测try语句块中的错误，从而让except语句捕获异常信息并处理。  
如果你不想在异常发生时结束你的程序，只需在try里捕获它。
**语法：**

    try:
	<语句>        #运行别的代码
	except <名字>：
	<语句>        #如果在try部份引发了'name'异常
	except <名字>，<数据>:
	<语句>        #如果引发了'name'异常，获得附加的数据
	else:
	<语句>        #如果没有异常发生

try的工作原理是，当开始一个try语句后，python就在当前程序的上下文中作标记，这样当异常出现时就可以回到这里，try子句先执行，接下来会发生什么依赖于执行时是否出现异常。

	- 如果当try后的语句执行时发生异常，python就（跳出？）try并执行第一个匹配该异常的except子句，异常处理完毕，控制流就通过整个try语句（除非在处理异常时又引发新的异常）。
	- 如果在try后的语句里发生了异常，却没有匹配的except子句，异常将被递交到上层的try，或者到程序的最上层（这样将结束程序，并打印缺省的出错信息）。
	- 如果在try子句执行时没有发生异常，python将执行else语句后的语句（如果有else的话），然后控制流通过整个try语句。

### 8.4 使用 except 而不带任何异常类型 ###

可以不带任何异常类型使用 except, 如下实例：

    try:
   		You do your operations here;
   		......................
	except:
   		If there is any exception, then execute this block.
   		......................
	else:
   		If there is no exception then execute this block. 

以上方式try-except语句**捕获所有**发生的异常。不能通过该程序识别出具体的异常信息。因为它捕获所有的异常。

### 8.5 使用 except而带多种异常类型 ###

你也可以使用相同的except语句来处理多个异常信息，如下所示：

    try:
   		You do your operations here;
   		......................
	except(Exception1[, Exception2[,...ExceptionN]]]):
   		If there is any exception from the given exception list, 
   		then execute this block.
   		......................
	else:
   		If there is no exception then execute this block.  

### 8.6 try-finally语句 ###

try-finally 语句无论是否发生异常都将执行最后的代码。

    try:
	<语句>
	finally:
	<语句>    #退出try时总会执行
	raise

**你可以使用except语句或者finally语句，但是两者不能同时使用。else语句也不能与finally语句同时使用。**

### 8.7 异常的参数 ###

一个异常可以带上参数，可作为输出的异常信息参数。  
可以通过except语句来捕获异常的参数，如下所示：  

    try:
   		You do your operations here;
   		......................
	except ExceptionType, Argument:
   		You can print value of Argument here...

变量接收的异常值通常包含在异常的语句中。在元组的表单中变量可以接收一个或者多个值。
元组通常包含错误字符串，错误数字，错误位置。

### 8.8 触发异常 ###

可以使用 raise语句自己触发异常。raise语法格式如下：

    raise [Exception [, args [, traceback]]]

其中，语句中Exception是异常的类型（例如，NameError）。args参数是一个异常参数值。该参数是可选的，如果不提供，异常的参数是"None"。
最后一个参数是可选的（在实践中很少使用），如果存在，是跟踪异常对象。

### 8.9 用户自定义异常 ###

通过创建一个新的异常类，程序可以命名它们自己的异常。异常应该是典型的继承自Exception类，通过直接或间接的方式。