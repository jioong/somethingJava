# 类加载器 #
  
*Class Loader* 是负责加载 *Java* ；类的字节码到 *Java* 虚拟机中。一般来说， *Java* 虚拟机使用*Java* 类的方式如下： *Java* 源代码在经过 *Java* 编译器编译之后被转换为 *Java* 字节码 `.class` 文件。类加载器负责读取 *Java* 字节码，并转换为 `java.lang.Class` 类的一个实例。  
  
## java.lang.ClassLoader 类 ##
  
`java.lang.ClassLoader` 类的基本职责是根据一个指定的类名称，找到或生成其对应的字节码，然后从这些字节码中定义出一个 *Java* 类，集 `java.lang.Class` 的一个实例。除此之外，`ClassLoader` 还负责加载 *Java* 应用所需的资源，如图像文件和配置文件等。  
  
**ClassLoader 中与加载类相关的方法：**  
* `getParent()` --- 返回该类加载器的父类加载器。  
* `loadClass(String name)` --- 加载名为 `name` 的类，返回结果为 `java.lang.Class` 类的实例。  
* `findClass(String name)` --- 查找名为 `name` 的类，返回结果为 `java.lang.Class` 类的实例。  
* `findLoadedClass(String name)` --- 查找名为 `name` 的已经被加载过的类，返回结果为 `java.lang.Class` 类的实例。  
* `defineClass(String name, byte[] b, int off, int len)` --- 把字节数组 `b` 中的内容转换为 *Java* 类，返回结果为 `java.lang.Class` 类的实例，该方法声明为 `final` 。  
* `resolveClass(Class<?> c)` --- 链接指定的 *Java* 类。  

## 类加载器的组织结构 ##
  
加载器可以分为两类，一是系统提供的，一是由开发人员自己编写的。  
**系统类加载器：**  
* 引导类加载器 *bootstrap class loader* ： 用来加载 *Java* 的核心库，是用原生代码实现的，并不继承自 `java.lang.ClassLoader` 。  
* 扩展类加载器 *extensions class loader* : 用来加载 *Java* 的扩展库。*Java* 虚拟机的实现会提供一个扩展库目录，该类加载器在次目录中查找并加载类。  
* 系统类加载器 *system class loader* : 根据 `CLASSPATH` 类路径来加载 *Java* 类。一般来说， *Java* 应用的类都是由它来完成加载的，可以通过 `ClassLoader.getSystemClassLoader()` 来获取。  

除了系统提供的类加载器以外，还可以通过继承 `java.lang.ClassLoader` 类来实现自己的类加载器。  
  
除了引导类加载器之外，所有的类加载器都有一个父类加载器。系统类加载器的父类加载器是扩展类加载器，扩展类加载器的父类加载器是引导类加载器。  
  
**每个 Java 类都维护着一个指向定义它的类加载器的引用，通过 `getClassLoader()` 方法可以获取到此引用。**  
  
### 类加载其的代理模式 ###
  
类加载器在尝试自己查找某个类的字节码并定义它时，会先代理给其父类加载器，由父类加载器先去尝试加载该类，以此类推。  
**虚拟机如何判定两个类是相同的：**
1. 类的全名是否相同。  
2. 加载此类的类加载器是否一样。  

只有两者都相同的情况，才认为两个类是相同的。即使是同样的字节码，被不同的类加载器加载之后得到的类也是不同的。  
  
### 加载类的过程 ###
  
代理模式使得真正完成类加载工作的类加载器和启动这个类加载过程的类加载器可能不是同一个。真正晚成类的加载工作是通过调用 `defineClass` 来实现的，而启动类的加载过程是通过调用 `loadClass` 来实现的。前者称为一个类的 **定义加载器 define loader** ，后者称为 **初始加载器 initiating loader** 。在*Java* 虚拟机判断两个类是否相同的时候，使用的类的定义加载器。  
两种类加载器的关联之处在于：**一个类的定义加载器是它引用的其他类的初始加载器**。  
  
在类加载器成功加载某个类之后，会把得到的 `java.lang.Class` 类的实例缓存起来。下次再次请求加载该类的时候，类加载器会直接使用缓存的类的实例，而不会尝试再次加载。   
  
### Class.forName ###
  
`Class.forName` 是一个静态方法，同样可以用来加载类。该方法有两种形式：  
1. `Class.forName(String name, boolean initialize, ClassLoader loader)` 
2. `Class.forName(String name)`  

其中， `name` 表示的是类的全名， `initialize` 表示是否初始化类， 默认为 `true` ， `loader` 表示加载时使用的类加载器，默认为当前类的类加载器。