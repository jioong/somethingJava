# Scala 基础 #
  
以 *Hello, World* 作为开始！  
  
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
  
## Scala 命令行 ##
  
