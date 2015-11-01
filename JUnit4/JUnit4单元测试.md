# JUnit 单元测试 #
  
**定义测试类：**  
* 该类必须是公共的，并且包含一个无参数的构造函数。  

**定义测试方法：**  
* 方法必须使用 `@Test` 注解。  
* 必须是共有的 `public`。  
* 不带有任何参数。  
* 返回类型为 `void`。  

*JUnit* 在调用执行每个 `@Test` 方法之前，为测试类创建一个新的实例。这有助于提供测试方法之间的独立性，并且避免在测试代码中产生以外的副作用。因为每个测试方法都运行在一个新的测试类实例上，所以就不能在测试方法之间重用各个实例变量值。  
  
当需要一次运行多个测试类时，就要创建一个叫做 **测试集 test suite** 的对象。测试集也是一个特定的测试运行器 `Runner` ，因此可以像运行测试类那样运行。  
  
> 测试类(Test Case) ： 一个包含一个或多个测试的类，而这些测试就是指那些用 `@Test` 注解的方法。使用一个测试类，可以把具有公共行为的测试归为一组。通常，生产类和测试类之间有着一对一的对应关系。

> 测试集(Suite) : 一组测试。测试集是一种把多个相关测试归入一组的便捷方式。如果没有为测试类定义一个测试集，那么 *Junit* 会自动提供一个测试集，包含测试类中的所有测试。一个测试集通常会将同一个包中的测试类归入一个组。  

> 测试运行器(Runner) : 执行测试集的程序。 *JUnit* 提供了多种运行器来执行测试。  

## JUnit 测试运行器 ##
  
**运行器：**  
1. `org.junit.internal.runners.JUnit38ClassRunner` ：兼容 *JUnit3.8* 的测试用例。  
2. `org.junit.runners.JUnit4` :作为 *JUnit4* 的测试用例来启动。  
3. `org.junit.runners.Parameterized` :该测试运行器可以使用不同参数来运行相同的测试集。  
4. `org.junit.runners.Suite` : *Suite* 是一个包含不同测试的容器，同时也是一个运行器，可以运行一个测试类中的所有以 `@Test` 注解的方法。  

如果测试类中没有提供任何运行器，那么 *JUnit* 将会使用默认的运行器。如果希望使用特定的运行器，可以使用 `@RunWith` 注解来指定测试运行器类。如：  
```Java
	@RunWith(value=org.junit.internal.runners.JUnit38ClassRunner.class)
	public class TestWithJUnit38 extends junit.framework.TestCase {
		...
	}
```  
  
## 用 Suite 来组合测试 ##
  
*JUnit* 设计 `Suite` 的目的是为了运行一个或多个测试用例。测试运行器会2启动 `Suite` ，然后运行哪个测试用例是由 `Suite` 来决定的。  
默认的 `Suite` 会扫描测试类，找出所有 `@Test` 注解的方法。默认的 `Suite` 会在内部为每个 `@Test` 方法创建一个测试类实例，然后 *JUnit* 就会独立的执行每个 `@Test` 方法，以避免潜在的负面影响。 