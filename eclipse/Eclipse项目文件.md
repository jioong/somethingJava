# Eclipse 新建项目之 .project 和 .classpath #
  
用 *eclipse* 新建一个 *Java Project*。在生成项目的问价夹中会有 *.classpath , .project* 两个文件。  
  
## .classpath ##
  
该文件用于定义项目结构。  
1. `kind="src"` 用于定义源文件的位置。  
2. `kind="con"` 用于指定运行的系统环境。  
3. `kind="lib"` 用于说明外部导入的 *JAR* 包。  
4. `kind="output"` 项目的输出目录。  

*Eclipse* 自动生成的文件：  
```XML  
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
	<classpathentry kind="lib" path="C:/Users/Administrator/Desktop/struts-2.3.24.1-all/struts-2.3.24.1/lib/commons-logging-1.1.3.jar"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
```  
  
## .project ##
  
1. `<name></name>` 项目名。  
2. `<comment></comment>` 项目注释描述。  
3. `<nature></nature>` 运行时需要的额外 *Eclipse* 插件。  
4. `<buildSpec></buildSpec>` 具体加载方式信息。  

```XML
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>HelloWorld</name>
	<comment></comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
	</natures>
</projectDescription>  
```
