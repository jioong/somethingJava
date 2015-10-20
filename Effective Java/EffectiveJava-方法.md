# 方法 #
  
## 检查参数的有效性 ##
  
绝大多数方法和构造器对于传递给它们的参数值都会有某些限制。如果传递无效的参数值给方法，这个方法在执行之前先对参数进行了检查，那么它很快就会失败，并且清楚地出现适当的异常。  
对于共有方法，要用 *javadoc* 的 *@throw* 标签在文档中说明违反参数值限制时会抛出的异常。  
对于非公有的方法，通常使用断言 (*assertion*) 来检查它们的参数，如：  
```Java
private static void sort(long a[], int offest, int length) {
	assert a != null;
	assert offest >= 0 && offest <= a.length;
	assert length >= 0 && length <= a.length - offest;
	...
}
```  
从本质上讲，断言是在声称被断言的条件将会为真，无论外围包的客户端如何使用它。不同于一般的检查，断言如果失败，则会抛出 *AssertionError* 。也不同于一般的检查，如果它们没有起到作用，本质上也不会有成本开销，除非通过将 *-ea (-enableassertions)* 标记传递给 *Java* 解释器，来启用它们。  
  


**来源于：[Joshua Bloch 《Effective Java 中文版》](http://www.amazon.cn/Sun-%25E5%2585%25AC%25E5%258F%25B8%25E6%25A0%25B8%25E5%25BF%2583%25E6%258A%2580%25E6%259C%25AF%25E4%25B8%259B%25E4%25B9%25A6-Effective-Java%25E4%25B8%25AD%25E6%2596%2587%25E7%2589%2588-Joshua-Bloch/dp/B001PTGR52?SubscriptionId=AKIAJMGEVRIO53UGJCYQ&tag=16-28-282__-23&linkCode=sp1&camp=2025&creative=165953&creativeASIN=B001PTGR52 "Effective Java 中文版")**