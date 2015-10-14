# Scala 基础 #
  
## Hello Scala ##
  
以 *Hello, World* 作为开始！  
[在线编译 Scala](http://www.scalakata.com/ "在线编译 Scala")
  
```Scala
object HelloWorld{
	def main(args: Array[String]){
		println("Hello world!")
	}
}
```  

1. 将其复制到 *Scala* 命令行环境下，回车执行。返回  
```Scala  
	defined object HelloWorld  
  
	-------调用方式---------  
	HelloWorld.main(null) 
```  
  
2. 将代码保存为 *HelloWorld.scala* 文件，通过 *scalac HelloWorld.scala* 命令编译，再通过 *scala HelloWorld* 命令来执行。与 *Java* 类似。  
  
## Scala 变量 ##
  
 *Scala* 定义了两种类型的变量 *var* 和 *val* , 其中 *val* 类似于 *Java* 中的 *final* 变量，一旦初始化之后，不可以重新赋值。而 *var* 类似于一般的非 *final* 变量，可以任意重新赋值。  
  
## Scala 函数 ##
  
*Scala* 函数以 *def* 定义，然后是函数的名称，再然后是以逗号分割的参数。 *Scala* 中变量类型是放在参数和变量的后面，以 “：“ 隔开。同样的，如果函数需要返回值，它的类型也是定义在参数的后面。实际上，每个 *Scala* 函数都有返回值，只是有些函数的返回值为 *Unit*， 类似于 *void* 类型。  
每个 *Scala* 函数都有返回结果，且函数返回结果无需使用 *return* 语句。在 *Scala* 中应该尽量避免使用 *return* 语句。 **函数的最后一个表达式的值就是函数的返回值。**