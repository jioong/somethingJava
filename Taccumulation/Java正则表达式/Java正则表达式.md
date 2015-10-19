# Java 正则表达式源码 #
  
## MatchResult.java ##
  
*MatchResult.java* 是一个接口，总共声明了 7 个方法。    
 
1. *start()* 方法返回匹配的开始索引值。即匹配结果的第一个字符在目标字符串中的索引值。
```Java
	public int start();
```  
如果没有尝试匹配或是前一个匹配操作失败，则会抛出*IllegalStateException* 。    
2. *start(int group)* 返回匹配中给定捕获组的开始索引值。  
```Java
	public int start(int group);
```  
捕获组的索引从左到右，从 1 开始。 0 表示整个匹配结果。  
```Java
	m.start(0) == m.start();
```  
同上，可以抛出 *IllegalStateException* 异常。当没有指定的捕获组数时，抛出 *IndexOutOfBoundsException* 异常。  
3. *end()* 与 *start()* 相对应，返回上次字符匹配的偏移量。  
```Java  
	public int end();
```  
同上，可能抛出 *IllegalStateException* 异常。  
4. *end(int group)* 指定捕获组匹配的结果的偏移量。  
```Java
	public int end(int group);		
			
	m.end(0) == m.end();
```
可能抛出的异常与 *start(group)* 相同。  
5. *group()* 返回上一次匹配成功的字符串。  
```Java  
	public String group();  
  
	m.group() == s.substring(m.start(), m.end());
```  
可能返回结果为空。可能抛出的异常为 *IllegalStateException* 。  
6. *groun(int group)* 返回指定捕获组匹配的字符串。   
```Java
	public String group(int group);
  
	m.group(g) == s.substring(m.start(g), m.end(g));
```  
可能抛出的异常与 *start(group)* 相同。  
7. *groupCount()* 返回捕获组的数目。  】
```Java 
	public int groupCount();
```
捕获组 0 表示整个匹配，不包括在该计数中。任何小于或等于该方法返回值的非负整数都被保证为该 *matcher* 的有效索引值。