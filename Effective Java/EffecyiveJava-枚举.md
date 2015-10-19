# 枚举 #
  
*Java 1.5* 发行版本中增加了两个新的引用型家族：一种新的类型称为 **枚举类型 (enum type)** , 一种新的接口称为 **注解类型 (annotation type)** 。  
  
## 用 enum 代替 int 常量 ##、
  
枚举类型 (*enum type*) 是指由一组固定的常量组成合法值的类型。在编程语言中还没有引入枚举类型之前，表示枚举类型的常用模型是声明一组具名的 *int* 常量，每个类型成员是一个常量：  
  
```Java
// The int enum pattern severely deficient!
	public static final int APPLE_FUJI         = 0;
	public static final int APPLE_PIPPIN       = 1;
	public static final int APPLE_GRANNY_SMITH = 2;  
```  
  
这种方法称作 *int* 枚举模式，存在诸多不足。它在类型安全性和使用方便性方面没有任何帮助。  
Java 枚举：  
```Java  
	public enum Aplle {FUJI, PIPPIN, GRANNY_SMITH }  
	public enum Orange {NAVEL, TEMPLE, BLOOD }
```  
*Java* 的枚举类型是功能十分齐全的**类**，枚举在本质上是 *int* 值。  
*Java* 枚举类型背后的基本想法非常简单：它们就是通过共有静态 *final* 域为每个枚举常量导出实例的类。因为没有可以访问的构造器，枚举类型是真正的 *final* 。因为客户端既不能创建枚举类型的实例，也不能对它进行扩展，因此很可能没有实例，而只有声明过的枚举常量。换句话说， **枚举类型是实例受控的。** 它们是单例的泛型化，本质上是单元素的枚举。  
  
**枚举提供了编译时的类型安全。** 如果声明一个参数的类型为 *Apple*，就可以保证被传到该参数上的任何非 *null* 的对象引用一定属于三个有效的 *Apple* 值之一。试图传递类型错误的值时，会导致编译时错误，就像试图将某种枚举类型的表达式赋给另一种枚举类型的常量，或者试图利用 *==* 操作符比较不同枚举类型的值一样。  
**每个枚举类型都有自己的命名空间。** 这样包含同名常量的多个枚举类型可以在一个系统中和平的共处。可以增加或重新排列枚举类型中的常量，而无需重新编译它的客户端代码，因为导出常量的域在枚举类型和它的客户端之间提供了一个隔离层：常量值并没有被编译到客户端代码中，而是在 *int* 枚举模式中。最终，可以通过 *tostreing()* 方法，将枚举转换成可打印的字符串。  
**枚举类型允许添加任意的方法和域，并实现任意的接口。** 为什么要将方法或者域添加到枚举类型中呢？ 1）可能是想将数据与它的常量关联起来。枚举类型可以先作为一个枚举常量的简单集合，随着时间的推移再演变称为全功能的抽象。  
**枚举是不可变的，所有的域都应该为 final 的。**它们可以是公有的，但是最好私有，并提供公有的访问方法。  
  
如果一个枚举具有普遍适用性，就应该成为一个顶层类(*top-level class*); 如果它只是被用在一个特定的顶层类中，它就应该称为该顶层类的一个成员类。  
将不同的行为与每个枚举常量关联起来的方法：在枚举类型中声明一个抽象的 *apply* 方法，并在特定于常量的类主体 (*constant-specific class body*) 中，用具体的方法覆盖每个常量的抽象 *apply* 方法。这种方法被称作**特定于常量的方法实现 (constant-specific method implementation)**:  
```Java  
// Enum type with constant-specific method implementation  
public enum Operation {
	PLUS   {double apply(double x, double y) {return x + y;}}, 
	MINUS  {double apply(double x, double y) {return x - y;}},
	TIMES  {double apply(double x, double y) {return x * y;}},
	DIVIDE {double apply(double x, double y) {return x / y;}},

	abstract double apply(doublex, double y);
}
```  
  
枚举类型中的抽象方法必须被它所有常量中的具体方法所覆盖。特定于常量的方法实现可以与特定于常量的数据结合起来。



**来源于：[Joshua Bloch 《Effective Java 中文版》](http://www.amazon.cn/Sun-%25E5%2585%25AC%25E5%258F%25B8%25E6%25A0%25B8%25E5%25BF%2583%25E6%258A%2580%25E6%259C%25AF%25E4%25B8%259B%25E4%25B9%25A6-Effective-Java%25E4%25B8%25AD%25E6%2596%2587%25E7%2589%2588-Joshua-Bloch/dp/B001PTGR52?SubscriptionId=AKIAJMGEVRIO53UGJCYQ&tag=16-28-282__-23&linkCode=sp1&camp=2025&creative=165953&creativeASIN=B001PTGR52 "Effective Java 中文版")**    