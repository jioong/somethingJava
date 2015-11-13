2014/12/22 星期一 11:06:44 

# Python入门基础 #

## 1. Python面向对象 ##

Python从设计之初就是一门面向对象的语言，正因如此，在Python中创建一个类和对象是很容易的。

### 1.1 棉线对象技术简介 ###

1. **类(class):**用来描述具有**相同的属性和方法的对象的集合。**它定义了该集合中每个对象所**共有的**属性和方法。对象是类的实例。  
2. **继承：**即一个派生类(derived class)继承基类(base class)的字段和方法。继承也**允许把一个派生类的对象作为一个基类的对象对待**。  
3. **方法：**类中定义的函数。  
4. **对象：**通过类定义的数据结构实例。对象包括两个数据成员(类变量和实例变量)和方法。
5. **实例化：**创建一个类的实例，类的具体对象。  
6. **类变量：**类变量在整个实例化的对象中是共用的。类变量定义在类中且在函数体之外。类变量通常不作为实例变量使用。  
7. **数据成员：**类变量或者实例变量用于处理类及其实例对象的相关数据。  
8. **实例变量：**定义在方法中的变量，只作用于当前实例的类。  
9. **方法重载：**如果从父类继承的方法不能满足子类的需求，子类可以对其进行改写，这个过程叫做方法的覆盖(override)。  

### 1.2 创建类 ###

使用class语句创建一个新类，class 之后为类的名称并以冒号结尾。如下实例：

    class ClassName:
		'Optional class documention string'   #类文档字符串
		class_suite                           #类体

类的帮助信息可以通过 **ClassName._doc_**查看。  
class_suite 由类成员、方法、数据属性组成。

**实例：**

    class Employee:
   		'Common base class for all employees'
  		 empCount = 0

  		 def __init__(self, name, salary):
      		self.name = name
      		self.salary = salary
      		Employee.empCount += 1
   
   		def displayCount(self):
     		print "Total Employee %d" % Employee.empCount

   		def displayEmployee(self):
      		print "Name : ", self.name,  ", Salary: ", self.salary

其中：

	- empCount变量是一个类变量，它的值将在这个类的所有实例之间共享。你可以在内部类或外部类使用Employee.empCount访问。
	- 第一种方法__init__()方法是一种特殊的方法，被称为类的构造函数或初始化方法，当创建了这个类的实例时就会调用该方法。

### 1.3 创建实例对象 ###

要创建一个类的实例，可以使用类的名称，并通过 _init_ 方法接受参数。  

**实例：**

    "This would create first object of Employee class"
	emp1 = Employee("Zara", 2000)
	"This would create second object of Employee class"
	emp2 = Employee("Manni", 5000)

### 1.4 访问属性 ###

可以使用点号(.)来访问对象属性。

**实例：**

    emp1.displayEmployee()
	emp2.displayEmployee()
	print "Total Employee %d" % Employee.empCount

### 1.5 Python内置类属性 ###

	- __dict__: 类的属性(包含一个字典，由类的数据属性组成)。
	- __doc__ : 类的文档字符串。
	- __name__: 类名。
	- __module__: 类定义所在的模块(类的全名是‘_main_.className’,如果类位于一个导入模块mymod中，那么className._module_等于 mymod)。
	- __bases__: 类的所有父类构成元素(包含一个由所有父类组成的元祖)。

### 1.6 Python对象销毁(垃圾回收) ###

同java语言一样，Python只用了**引用计数**来追踪内存中的对象。  
在Python内部记录着所有使用中的对象各有多少引用。  
一个内部跟踪变量，称为一个引用计数器。  
当对象被创建时，就创建一个引用计数。当这个对象不再需要时，即这个对象的引用计数为 0 时，被回收。回收不是“立即”的，有解释器在适当的时机，将垃圾对象占用的内存空间回收。  

垃圾回收机制不仅针对引用计数为0的对象，同样也可以处理**循环引用**的情况。循环引用指的是，两个对象相互引用，但是没有其他变量引用他们。这种情况下，仅使用引用计数是不够的。Python 的垃圾收集器实际上是一个引用计数器和一个循环垃圾收集器。作为引用计数的补充， 垃圾收集器也会留心被分配的总量很大（及未通过引用计数销毁的那些）的对象。 在这种情况下， 解释器会暂停下来， 试图清理所有未引用的循环。

**析构函数 __del__ ，__del__在对象消逝的时候被调用，当对象不再被使用时，__del__方法运行：**

### 1.7 类的继承 ###

面向对象的编程带来的主要好处之一是代码的重用，实现这种重用的方法之一是通过继承机制。继承完全可以理解成类之间的类型和子类型关系。  

**继承语法：**

    class 派生类名（基类名）：  #... 基类名写在括号里，基本类是在类定义的时候，在元组之中指明的。

**在Python中继承的一些特点：**

	- 在继承中基类的构造（__init__()方法）不会被自动调用，它需要在其派生类的构造中亲自专门调用。
	- 在调用基类的方法时，需要加上基类的类名前缀，且需要带上self参数变量。区别于在类中调用普通函数时并不需要带上self参数。
	- Python总是首先查找对应类型的方法，如果它不能在派生类中找到对应的方法，它才开始到基类中逐个查找。（先在本类中查找调用的方法，找不到才去基类中找）。

**如果在继承元组中列了一个以上的类，那么它就被称作"多重继承" 。**  
**语法：**

    class SubClassName (ParentClass1[, ParentClass2, ...]):
   		'Optional class documentation string'
   		class_suite

### 1.8 方法重写 ###

如果父类方法的功能不能满足你的需求，可以在子类重写你父类的方法。

### 1.9 基础重载方法 ###

**通用的功能，可以在自己的类重写：**
<center>
![reload method]("./images/reload_method.png" "重载方法")
</center>

### 1.10 运算符重载 ###

Python支持运算符重载。实例如下：

    #!/usr/bin/python

	class Vector:
   		def __init__(self, a, b):
      		self.a = a
      		self.b = b

   		def __str__(self):
      		return 'Vector (%d, %d)' % (self.a, self.b)
   
   		def __add__(self,other):
      		return Vector(self.a + other.a, self.b + other.b)

	v1 = Vector(2,10)
	v2 = Vector(5,-2)
	print v1 + v2

输出为：

    Vector(7,8)

### 1.11 隐藏数据 ###

在Python中隐藏数据很简单，不需要再前面加什么关键字，**只要把类变量名或成员函数前面加两个下划线即可实现数据隐藏的功能。**这样，对于类的实例来说，其变量名和成员函数是**不能使用的，**对于其类的继承类来说，也是隐藏的。其继承类可以定义其一模一样的变量名或成员函数名，而不会引起命名冲突。

## 2. Python正则表达式 ##

正则表达式是一个特殊的字符序列，它能帮助你方便的检查一个字符串是否与某种模式匹配。Python 自1.5版本起增加了**re 模块**，它提供 **Perl 风格**的正则表达式模式。  
** re 模块使Python拥有全部的正则表达式功能。**  
** compile 函数**根据一个模式字符串和可选的标志参数生成一个**正则表达式对象。**该对象拥有一些列方法用于正则表达式的匹配和替换。  
** re 模块**也提供了与这些方法功能完全一致的函数，这些函数使用一个模式字符串做第一个参数。

### 2.1 re.match() 函数 ###

re.match 尝试从字符串的开始匹配一个模式。  
**函数语法：**

    re.match(pattern, string, flags = 0)

**函数参数说明：**
<center>
![re.match]("./images/re_match.png" "re match")
</center>

匹配成功re.match方法返回一个匹配的对象，否则返回None。  
可以使用group(num) 或 groups() 匹配对象函数来获取匹配表达式。  
<center>
![group]("./images/re_match_group.png" "group")
</center>

### 2.2 re.search() 函数 ###

re.search 尝试从字符串的开始匹配一个模式。  
**函数语法：**

    re.search(pattern, string, flags = 0)

参数意义与 re.match() 相同。

### 2.3 re.match 与 re.search 的区别 ###

re.match只匹配字符串的开始，如果字符串开始不符合正则表达式，则匹配失败，函数返回None；而re.search匹配整个字符串，直到找到一个匹配。

### 2.4 检索与替换 ###

Python 的re模块提供了**re.sub**用于替换字符串中的匹配项。  
**函数语法：**

    re.sub(pattern, repl, string, max = 0)

返回的字符串是在字符串中用 RE 最左边不重复的匹配来替换。如果模式没有发现，字符将被没有改变地返回。  
可选参数 count 是模式匹配后替换的最大次数；count 必须是非负整数。缺省值是 0 表示替换所有的匹配。

### 2.5 正则表达式修饰符 --- 可选标识 ###

正则表达式可以包含一些可选标志修饰符来控制匹配的模式。修饰符被指定为一个可选的标志。多个标志可以通过按位 OR(|) 它们来指定。
<center>
![flags]("./images/re_flags.png" "可选标识")
</center>

### 2.6 正则表达式模式 ###

模式字符串使用特殊的语法来表示一个正则表达式：  
1. 字母和数字表示他们自身。一个正则表达式模式中的字母和数字匹配同样的字符串。  
2. 多数字母和数字前加一个反斜杠时会拥有不同的含义。  
3. 标点符号只有被转义时才匹配自身，否则它们表示特殊的含义。  
4. 反斜杠本身需要使用反斜杠转义。  

**特殊元素：**
<center>
<table class="reference"> <tbody><tr><th style="width:25%">模式</th><th>描述</th></tr> <tr><td>^</td><td>匹配字符串的开头</td></tr> <tr><td>$</td><td>匹配字符串的末尾。</td></tr> <tr><td>.</td><td>匹配任意字符，除了换行符，当re.DOTALL标记被指定时，则可以匹配包括换行符的任意字符。</td></tr> <tr><td>[...]</td><td>用来表示一组字符,单独列出：[amk] 匹配 'a'，'m'或'k'</td></tr> <tr><td>[^...]</td><td>不在[]中的字符：[^abc] 匹配除了a,b,c之外的字符。</td></tr> <tr><td>re*</td><td>匹配0个或多个的表达式。</td></tr> <tr><td>re+</td><td>匹配1个或多个的表达式。</td></tr> <tr><td>re?</td><td> 匹配0个或1个由前面的正则表达式定义的片段，贪婪方式</td></tr> <tr><td>re{ n}</td><td></td></tr> <tr><td>re{ n,}</td><td>精确匹配n个前面表达式。</td></tr> <tr><td>re{ n, m}</td><td>匹配 n 到 m 次由前面的正则表达式定义的片段，贪婪方式</td></tr> <tr><td>a| b</td><td>匹配a或b</td></tr> <tr><td>(re)</td><td>G匹配括号内的表达式，也表示一个组</td></tr> <tr><td>(?imx)</td><td>正则表达式包含三种可选标志：i, m, 或 x 。只影响括号中的区域。</td></tr> <tr><td>(?-imx)</td><td>正则表达式关闭 i, m, 或 x 可选标志。只影响括号中的区域。</td></tr> <tr><td>(?: re)</td><td> 类似 (...), 但是不表示一个组</td></tr> <tr><td>(?imx: re)</td><td>在括号中使用i, m, 或 x 可选标志</td></tr> <tr><td>(?-imx: re)</td><td>在括号中不使用i, m, 或 x 可选标志</td></tr> <tr><td>(?#...)</td><td>注释.</td></tr> <tr><td>(?= re)</td><td>前向肯定界定符。如果所含正则表达式，以 ... 表示，在当前位置成功匹配时成功，否则失败。但一旦所含表达式已经尝试，匹配引擎根本没有提高；模式的剩余部分还要尝试界定符的右边。</td></tr> <tr><td>(?! re)</td><td>前向否定界定符。与肯定界定符相反；当所含表达式不能在字符串当前位置匹配时成功</td></tr> <tr><td>(?&gt; re)</td><td>匹配的独立模式，省去回溯。</td></tr> <tr><td>\w</td><td> 匹配字母数字</td></tr> <tr><td>\W</td><td>匹配非字母数字</td></tr> <tr><td>\s</td><td> 匹配任意空白字符，等价于 [\t\n\r\f].</td></tr> <tr><td>\S</td><td>匹配任意非空字符</td></tr> <tr><td>\d</td><td> 匹配任意数字，等价于 [0-9].</td></tr> <tr><td>\D</td><td>匹配任意非数字</td></tr> <tr><td>\A</td><td>匹配字符串开始</td></tr> <tr><td>\Z</td><td>匹配字符串结束，如果是存在换行，只匹配到换行前的结束字符串。c</td></tr> <tr><td>\z</td><td>匹配字符串结束</td></tr> <tr><td>\G</td><td>匹配最后匹配完成的位置。</td></tr> <tr><td>\b</td><td>匹配一个单词边界，也就是指单词和空格间的位置。例如， 'er\b' 可以匹配"never" 中的 'er'，但不能匹配 "verb" 中的 'er'。</td></tr> <tr><td>\B</td><td>匹配非单词边界。'er\B' 能匹配 "verb" 中的 'er'，但不能匹配 "never" 中的 'er'。</td></tr> <tr><td>\n, \t, 等.</td><td>匹配一个换行符。匹配一个制表符。等</td></tr> <tr><td>\1...\9</td><td>比赛第n个分组的子表达式。</td></tr> <tr><td>\10</td><td>匹配第n个分组的子表达式，如果它经匹配。否则指的是八进制字符码的表达式。</td></tr> </tbody></table>
</center>

## 3. Python CGI 编程 ##

CGI 目前由NCSA维护，NCSA定义CGI如下：  
CGI(Common Gateway Interface),通用网关接口,它是一段程序,运行在服务器上如：HTTP服务器，提供同客户端HTML页面的接口。

CGI程序可以是Python脚本，PERL脚本，SHELL脚本，C或者C++程序等。

### 3.1 CGI 框架图 ###

<center>
![CGI]("./images/cgi_framework.png" "CGI框架")
</center>

## 4. Python 操作 mysql 数据库 ##

Python 标准数据库接口为 **Python DB-API**，Python DB-API 为开发人员提供了数据库应用编程接口。  
Python 数据库接口支持非常多的数据库。包括如下：

	- GadFly
	- mSQL
	- MySQL
	- PostgreSQL
	- Microsoft SQL Server 2000
	- Informix
	- Interbase
	- Oracle
	- Sybase

不同的数据库需要下载不同的 DB API 模块。  
DB-API是一个规范，它定义了一些列必须的对象和数据库存取方式，以便为各种各样的底层数据库系统和多种多样的数据库接口程序提供一致的访问接口。  

Python DB-API使用流程：

		- 引入 API 模块。
		- 获取与数据库的连接。
		- 执行 SQL 语句和存储过程。
		- 关闭数据库连接。

### 4.1 什么是 MySQLdb? ###

MySQLdb 是用于Python链接Mysql数据库的接口，它实现了 Python 数据库 API 规范 V2.0，基于 MySQL C API 上建立的。

### 4.2 如何安装 MySQLdb? ###

为了用 DB-API编写 MySQL 脚本，必须确保安装了 MySQL。

**执行：**

    # encoding: utf-8
	#!/usr/bin/python

	import MySQLdb

如果执行后的输出结果如下所示，意味着你没有安装 MySQLdb 模块：  

    Traceback (most recent call last):
		File "test.py", line 3, in <module>
    		import MySQLdb
		ImportError: No module named MySQLdb

### 4.3 数据库连接 ###

**实例：**

    # encoding: utf-8
	#!/usr/bin/python

	import MySQLdb

	# 打开数据库连接
	db = MySQLdb.connect("localhost","testuser","test123","TESTDB" )   # 从左到右依次为：连接地址，数据库用户名，密码，连接的数据库名

	# 使用cursor()方法获取操作游标 
	cursor = db.cursor()

	# 使用execute方法执行SQL语句
	cursor.execute("SELECT VERSION()")

	# 使用 fetchone() 方法获取一条数据库。
	data = cursor.fetchone()

	print "Database version : %s " % data

	# 关闭数据库连接
	db.close()

### 4.4 创建数据库表 ###

**实例：**

    # encoding: utf-8
	#!/usr/bin/python

	import MySQLdb

	# 打开数据库连接
	db = MySQLdb.connect("localhost","testuser","test123","TESTDB" )

	# 使用cursor()方法获取操作游标 
	cursor = db.cursor()

	# 如果数据表已经存在使用 execute() 方法删除表。
	cursor.execute("DROP TABLE IF EXISTS EMPLOYEE")

	# 创建数据表SQL语句
	sql = """CREATE TABLE EMPLOYEE (
         FIRST_NAME  CHAR(20) NOT NULL,
         LAST_NAME  CHAR(20),
         AGE INT,  
         SEX CHAR(1),
         INCOME FLOAT )"""

	cursor.execute(sql)

### 4.5 数据库插入操作 ###

### 4.6 数据库查询操作 ###

Python查询Mysql使用 fetchone() 方法获取单条数据, 使用fetchall() 方法获取多条数据。  

	- fetchone(): 该方法获取下一个查询结果集。结果集是一个对象。
	- fetchall():接收全部的返回结果行。
	- rowcount: 这是一个只读属性，并返回执行execute()方法后影响的行数。

### 4.7 数据库更新操作 ###

### 4.8 执行事务 ###

事务机制可以确保数据的一致性。  
事务应该具有4个属性：原子性、一致性、隔离性、持久性。这四个属性通常称为ACID特性。  

	- 原子性（atomicity）。一个事务是一个不可分割的工作单位，事务中包括的诸操作要么都做，要么都不做。
	- 一致性（consistency）。事务必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。
	- 隔离性（isolation）。一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
	- 持久性（durability）。持续性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。

**Python DB API 2.0 的事务提供了两个方法 commit 或 rollback。**
commit()方法游标的所有更新操作，rollback（）方法回滚当前游标的所有操作。

### 4.9 错误处理 ###

**DB API中定义了一些数据库操作的错误及异常：**
<center>
![error]("./images/db_error.png" "错误及异常")
</center>

## 5. Python 使用 SMTP 发送邮件 ##

SMTP（Simple Mail Transfer Protocol）即简单邮件传输协议,它是一组用于由源地址到目的地址传送邮件的规则，由它来控制信件的中转方式。  
python的** smtplib **提供了一种很方便的途径发送电子邮件。它对smtp协议进行了简单的封装。  
**Python创建 SMTP 对象语法如下：**

    import smtplib

	smtpObj = smtplib.SMTP( [host [, port [, local_hostname]]] )

**参数说明：**

	- host: SMTP 服务器主机。 你可以指定主机的ip地址或者域名如:w3cschool.cc，这个是可选参数。
	- port: 如果你提供了 host 参数, 你需要指定 SMTP 服务使用的端口号，一般情况下SMTP端口号为25。
	- local_hostname: 如果SMTP在你的本机上，你只需要指定服务器地址为 localhost 即可。

Python SMTP对象使用sendmail方法发送邮件，语法如下：

    SMTP.sendmail(from_addr, to_addrs, msg[, mail_options, rcpt_options]

**参数说明：**

	- from_addr: 邮件发送者地址。
	- to_addrs: 字符串列表，邮件发送地址。
	- msg: 发送消息。

**实例：使用Python发送邮件**

    #!/usr/bin/python

	import smtplib

	sender = 'from@fromdomain.com'
	receivers = ['to@todomain.com']

	message = """From: From Person <from@fromdomain.com>
				 To: To Person <to@todomain.com>
				 Subject: SMTP e-mail test

				 This is a test e-mail message.
			  """

	try:
		smtpObj = smtplib.SMTP('localhost')
		smtpObj.sendmail(sender, receivers, message)         
		print "Successfully sent email"
	except SMTPException:
		print "Error: unable to send email"

### 5.1 使用 Python 发送 HTML 格式的邮件 ###

Python发送 HTML 格式的邮件与发送纯文本信息的邮件的不同之处就是**将MIMEText中 _subtype 设置为 html.**  
**实例如下：**

    import smtplib  
	from email.mime.text import MIMEText  
	mailto_list=["YYY@YYY.com"] 
	mail_host="smtp.XXX.com"  #设置服务器
	mail_user="XXX"    #用户名
	mail_pass="XXXX"   #口令 
	mail_postfix="XXX.com"  #发件箱的后缀
  
	def send_mail(to_list,sub,content):  #to_list：收件人；sub：主题；content：邮件内容
    	me="hello"+"<"+mail_user+"@"+mail_postfix+">"   #这里的hello可以任意设置，收到信后，将按照设置显示
    	msg = MIMEText(content,_subtype='html',_charset='gb2312')    #创建一个实例，这里设置为html格式邮件
    	msg['Subject'] = sub    #设置主题
    	msg['From'] = me  
    	msg['To'] = ";".join(to_list)  
		try:  
        	s = smtplib.SMTP()  
        	s.connect(mail_host)  #连接smtp服务器
        	s.login(mail_user,mail_pass)  #登陆服务器
        	s.sendmail(me, to_list, msg.as_string())  #发送邮件
        	s.close()  
        	return True  
		except Exception, e:  
        	print str(e)  
        	return False  
	if __name__ == '__main__':  
    	if send_mail(mailto_list,"hello","<a href='http://www.cnblogs.com/xiaowuyi'>小五义</a>"):  
       	 	print "发送成功"  
    	else:  
        	print "发送失败" 

也可以在消息体中指定Content-type为text/html。  
**实例如下：**

    #!/usr/bin/python

	import smtplib

	message = """From: From Person <from@fromdomain.com>
	To: To Person <to@todomain.com>
	MIME-Version: 1.0
	Content-type: text/html
	Subject: SMTP HTML e-mail test

	This is an e-mail message to be sent in HTML format

	<b>This is HTML message.</b>
	<h1>This is headline.</h1>
	"""

	try:
		smtpObj = smtplib.SMTP('localhost')
		smtpObj.sendmail(sender, receivers, message)         
		print "Successfully sent email"
	except SMTPException:
		print "Error: unable to send email"

### 5.2 Python 发送带附件的邮件 ###

发送带附件的邮件，首先要创建MIMEMultipart()实例，然后构造附件，如果有多个附件，可依次构造，最后利用smtplib.smtp发送。

    from email.mime.text import MIMEText
	from email.mime.multipart import MIMEMultipart
	import smtplib

	#创建一个带附件的实例
	msg = MIMEMultipart()

	#构造附件1
	att1 = MIMEText(open('d:\\123.rar', 'rb').read(), 'base64', 'gb2312')
	att1["Content-Type"] = 'application/octet-stream'
	att1["Content-Disposition"] = 'attachment; filename="123.doc"'#这里的filename可以任意写，写什么名字，邮件中显示什么名字
	msg.attach(att1)

	#构造附件2
	att2 = MIMEText(open('d:\\123.txt', 'rb').read(), 'base64', 'gb2312')
	att2["Content-Type"] = 'application/octet-stream'
	att2["Content-Disposition"] = 'attachment; filename="123.txt"'
	msg.attach(att2)

	#加邮件头
	msg['to'] = 'YYY@YYY.com'
	msg['from'] = 'XXX@XXX.com'
	msg['subject'] = 'hello world'
	#发送邮件
	try:
    	server = smtplib.SMTP()
    	server.connect('smtp.XXX.com')
    	server.login('XXX','XXXXX')#XXX为用户名，XXXXX为密码
    	server.sendmail(msg['from'], msg['to'],msg.as_string())
    	server.quit()
    	print '发送成功'
	except Exception, e:  
    	print str(e) 

## 6. Python 多线程 ##

多线程类似于同时执行多个不同程序，多线程运行有如下优点：

	- 使用线程可以把占据长时间的程序中的任务放到后台去处理。
	- 用户界面可以更加吸引人，这样比如用户点击了一个按钮去触发某些事件的处理，可以弹出一个进度条来显示处理的进度。
	- 程序的运行速度**可能**加快。
	- 在一些等待的任务实现上如用户输入、文件读写和网络收发数据等，线程就比较有用了。在这种情况下我们可以释放一些珍贵的资源如内存占用等等。

每个线程都有他自己的一组CPU寄存器，称为线程的上下文，该上下文反映了线程上次运行该线程的CPU寄存器的状态。  
指令指针和堆栈指针寄存器是线程上下文中两个最重要的寄存器，线程总是在进程得到上下文中运行的，这些地址都用于标志拥有线程的进程地址空间中的内存。

	- 线程可以被抢占（中断）。
	- 在其他线程正在运行时，线程可以暂时搁置（也称为睡眠） -- 这就是线程的退让。

### 6.1 开始学习 Python 线程 ###

Python中使用线程有两种方式：函数或者用类来包装线程对象。  

1. 函数式：调用thread模块中的start_new_thread()函数来产生新线程。  
**语法：**

    thread.start_new_thread ( function, args[, kwargs] )

**参数说明：**

	- function - 线程函数。
	- args - 传递给线程函数的参数,他必须是个tuple类型。
	- kwargs - 可选参数。

**实例：**

    #!/usr/bin/python

	import thread
	import time

	# 为线程定义一个函数
	def print_time( threadName, delay):
		count = 0
		while count < 5:
      		time.sleep(delay)
      		count += 1
      		print "%s: %s" % ( threadName, time.ctime(time.time()) )

	# 创建两个线程
	try:
		thread.start_new_thread( print_time, ("Thread-1", 2, ) )
		thread.start_new_thread( print_time, ("Thread-2", 4, ) )
	except:
		print "Error: unable to start thread"

	while 1:
		pass

**线程的结束一般依靠线程函数的自然结束；也可以在线程函数中调用thread.exit()，他抛出SystemExit exception，达到退出线程的目的。**

### 6.2 线程模块 ###

Python通过两个标准库thread和threading提供对线程的支持。thread提供了低级别的、原始的线程以及一个简单的锁。  
**thread 模块提供的其他方法：**

	- threading.currentThread(): 返回当前的线程变量。
	- threading.enumerate(): 返回一个包含正在运行的线程的list。正在运行指线程启动后、结束前，不包括启动前和终止后的线程。
	- threading.activeCount(): 返回正在运行的线程数量，与len(threading.enumerate())有相同的结果。

除了使用方法外，线程模块同样提供了 **Thread类** 来处理线程，Thread类提供了以下方法:

	- run(): 用以表示线程活动的方法。
	- start():启动线程活动。
	- join([time]): 等待至线程中止。这阻塞调用线程直至线程的join() 方法被调用中止-正常退出或者抛出未处理的异常-或者是可选的超时发生。
	- isAlive(): 返回线程是否活动的。
	- getName(): 返回线程名。
	- setName(): 设置线程名。

### 6.3 使用 Threading 模块创建线程 ###

使用Threading模块创建线程，直接从threading.Thread继承，然后重写__init__方法和run方法：  

**实例：**

    #!/usr/bin/python

	import threading
	import time

	exitFlag = 0

	class myThread (threading.Thread):   #继承父类threading.Thread
    	def __init__(self, threadID, name, counter):
        	threading.Thread.__init__(self)
        	self.threadID = threadID
        	self.name = name
        	self.counter = counter
    	def run(self):                   #把要执行的代码写到run函数里面 线程在创建后会直接运行run函数 
        	print "Starting " + self.name
        	print_time(self.name, self.counter, 5)
        	print "Exiting " + self.name

	def print_time(threadName, delay, counter):
    	while counter:
        	if exitFlag:
            	thread.exit()
        	time.sleep(delay)
        	print "%s: %s" % (threadName, time.ctime(time.time()))
        	counter -= 1

	# 创建新线程
	thread1 = myThread(1, "Thread-1", 1)
	thread2 = myThread(2, "Thread-2", 2)

	# 开启线程
	thread1.start()
	thread2.start()

	print "Exiting Main Thread"

### 6.4 线程同步 ###

如果多个线程共同对某个数据修改，则可能出现不可预料的结果，为了保证数据的正确性，需要对多个线程进行同步。  
使用Thread对象的Lock和Rlock可以实现简单的线程同步，这两个对象都有acquire方法和release方法，对于那些需要每次只允许一个线程操作的数据，可以将其操作放到acquire和release方法之间。  
**如下：**  
多线程的优势在于可以同时运行多个任务（至少感觉起来是这样）。但是当线程需要共享数据时，可能存在数据不同步的问题。  
**锁**有两种状态——锁定和未锁定。每当一个线程比如"set"要访问共享数据时，必须先获得锁定；如果已经有别的线程比如"print"获得锁定了，那么就让线程"set"暂停，也就是**同步阻塞**；等到线程"print"访问完毕，释放锁以后，再让线程"set"继续。

### 6.5 线程优先级队列(Queue) ###

Python的Queue模块中提供了同步的、线程安全的队列类，包括FIFO（先入先出)队列Queue，LIFO（后入先出）队列LifoQueue，和优先级队列PriorityQueue。这些队列都实现了锁原语，能够在多线程中直接使用。可以使用队列来实现线程间的同步。  

**Queue模块中的常用方法:**

	- Queue.qsize() 返回队列的大小。
	- Queue.empty() 如果队列为空，返回True,反之False。
	- Queue.full() 如果队列满了，返回True,反之False。
	- Queue.full 与 maxsize 大小对应。
	- Queue.get([block[, timeout]])获取队列，timeout等待时间。
	- Queue.get_nowait() 相当Queue.get(False)。
	- Queue.put(item) 写入队列，timeout等待时间。
	- Queue.put_nowait(item) 相当Queue.put(item, False)。
	- Queue.task_done() 在完成一项工作之后，Queue.task_done()函数向任务已经完成的队列发送一个信号。
	- Queue.join() 实际上意味着等到队列为空，再执行别的操作。

**实例：**

    #!/usr/bin/python

	import Queue
	import threading
	import time

	exitFlag = 0

	class myThread (threading.Thread):
    	def __init__(self, threadID, name, q):
        	threading.Thread.__init__(self)
        	self.threadID = threadID
        	self.name = name
        	self.q = q
    	def run(self):
        	print "Starting " + self.name
        	process_data(self.name, self.q)
        	print "Exiting " + self.name

	def process_data(threadName, q):
    	while not exitFlag:
        	queueLock.acquire()
        	if not workQueue.empty():
            	data = q.get()
            	queueLock.release()
            	print "%s processing %s" % (threadName, data)
        	else:
            	queueLock.release()
        	time.sleep(1)

	threadList = ["Thread-1", "Thread-2", "Thread-3"]
	nameList = ["One", "Two", "Three", "Four", "Five"]
	queueLock = threading.Lock()
	workQueue = Queue.Queue(10)
	threads = []
	threadID = 1

	# 创建新线程
	for tName in threadList:
    	thread = myThread(threadID, tName, workQueue)
    	thread.start()
    	threads.append(thread)
    	threadID += 1

	# 填充队列
	queueLock.acquire()
	for word in nameList:
    	workQueue.put(word)
	queueLock.release()

	# 等待队列清空
	while not workQueue.empty():
    	pass

	# 通知线程是时候退出
	exitFlag = 1

	# 等待所有线程完成
	for t in threads:
    	t.join()
	print "Exiting Main Thread"

输出为：

    	Starting Thread-1
	Starting Thread-2
	Starting Thread-3
	Thread-1 processing One
	Thread-2 processing Two
	Thread-3 processing Three
	Thread-1 processing Four
	Thread-2 processing Five
	Exiting Thread-3
	Exiting Thread-1
	Exiting Thread-2
	Exiting Main Thread

## 7. Python GUI编程(TKinter) ##

python提供了多个图形开发界面的库，几个常用Python GUI库如下：  

	- Tkinter： Tkinter模块("Tk 接口")是Python的标准Tk GUI工具包的接口.Tk和Tkinter可以在大多数的Unix平台下使用,同样可以应用在Windows和Macintosh系统里.,Tk8.0的后续版本可以实现本地窗口风格,并良好地运行在绝大多数平台中。
	- wxPython：wxPython 是一款开源软件，是 Python 语言的一套优秀的 GUI 图形库，允许 Python 程序员很方便的创建完整的、功能键全的 GUI 用户界面。
	- Jython：Jython程序可以和Java无缝集成。除了一些标准模块，Jython使用Java的模块。Jython几乎拥有标准的Python中不依赖于C语言的全部模块。比如，Jython的用户界面将使用Swing，AWT或者SWT。Jython可以被动态或静态地编译成Java字节码。

### 7.1 Tkinter 编程 ###

Tkinter 是Python的标准GUI库。Python使用Tkinter可以快速的创建GUI应用程序。  
由于Tkinter是内置到python的安装包中、只要安装好Python之后就能import Tkinter库、而且IDLE也是用Tkinter编写而成、对于简单的图形界面Tkinter还是能应付自如。  

**创建一个 CUI 程序：**

	- 1. 导入 Tkinter 模块。
	- 2. 创建控件。
	- 3. 指定该控件的 master，即**这个控件属于哪一个**。
	- 4. 告诉 GM(geometry manager)有一个空间产生了。

**实例：**

    #!/usr/bin/python

	import Tkinter
	top = Tkinter.Tk()
	# 进入消息循环
	top.mainloop()

### 7。2 Tkinter 组件 ###

Tkinter的提供各种控件，如按钮，标签和文本框，一个GUI应用程序中使用。这些控件通常被称为控件或者部件。  

**控件列表：**
<center>
![Tkinter 控件]("./images/tkinter.png" "控件列表")
</center>

### 7.3 标准属性 ###

标准属性也就是所有控件的共同属性，如大小，字体和颜色等等。
<center>
![标准属性]("./images/tkinter_property.png" "标准属性")
</center>

### 7.4 几何管理 ###

Tkinter控件有特定的几何状态管理方法，管理整个控件区域组织，一下是Tkinter公开的几何管理类：包、网格、位置。  

<center>
![几何管理]("./images/tkinter_manger.png" "几何管理")
</center>