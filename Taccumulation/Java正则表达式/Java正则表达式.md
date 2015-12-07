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
7. *groupCount()* 返回捕获组的数目。 
```Java 
	public int groupCount();
```
捕获组 0 表示整个匹配，不包括在该计数中。任何小于或等于该方法返回值的非负整数都被保证为该 *matcher* 的有效索引值。 
   
## Matcher.java ##

该类不是多线程安全的。  
  
一个 `Pattern` 通过调用 `matcher()` 方法，得到一个 `Matcher`。一旦得到一个 `Matcher`,可以对其执行三种不同的 *match* 操作：  
1. 调用 `matches()` 方法，尝试用匹配模式*pattern* 去匹配整个输入字符序列。如果该模式能够匹配整个字符序列，则返回 `true`, 否则，返回 `false` 。  
2. 调用 `lookingAt()` 方法，与 `matches()`方法的不同在于，该方法不需要完整的匹配整个输入字符序列。只需要匹配模式 *pattern* 的整个表达式都能够匹配到字符序列中的内容就返回 `true`。如果`pattern` 有表达式没有匹配到内容则返回 `false`。   
3. 调用 `find()` 方法，浏览整个输入字符序列，找出下一个匹配模式的子字符序列。  

上面三个方法，都会返回一个 `boolean` 值表示匹配成功或失败。成功匹配的更多信息能通过查询 `matcher` 的状态获得。  
  
### 实例域 ###
  
1. `Pattern parentPattern` 创造该匹配器*matcher*的模式*Pattern*。  
2. `int[] groups` 用于存储捕获组信息。如果一个组在匹配时被跳过，将会包含无效值。  
3. `int from, to` 用于被匹配的字符序列的范围。改变 *region* 会改变这两个值。  
4. `lookbehindTo`   
5. `CharSequence text` 用于匹配的原始字符串。  
6. `boolean hitEnd` `boolean`值暗示更多的输入是否能够改变上一个匹配。  
	* 如果 `hitEnd` 为 `true`,且一个匹配被找到，更多的输入 **可能造成一个不同的匹配结果被找到。**
	* 如果 `hitEnd` 为 `true`,且没有匹配被找到，更多的输入 **能够造成一个匹配被找到。**
	* 如果 `hitEnd` 为 `false`,且一个匹配被找到，更多的输入 **不会改变匹配。**
	* 如果 `hitEnd` 为 `false`,且没有匹配被找到，跟多的输入 **不会找到一个匹配。**

  
### 构造函数 ###
  
1. `Matcher(Pattern parent, CharSequence text)`
	* *groups* 的最小 *size* 为 20 `2 * Math.max(parent.capturingGroupCount, 10)`。
	* *locals* 的 *size*为 `parent.localCount`。  
	* 设置匹配器 `matcher`的初始状态。  
	*  
## Pattern.java ##
  
该类的实例表示一个编译过的正则表达式。一个正则表达式，也就是一个指定的字符串，必须首先编译成该类的一个实例。然后这个 *pattern* 就可以创造一个对象`Matcher`匹配器去匹配任意的字符序列。  
所有的在涉及执行匹配的的状态信息都保存在匹配器中，因此**多个匹配器可以共享一个相同的pattern模式。**  
  
`Pattern` 是实例是不可变的，并且是多线程安全的。而 `Matcher`则不是多线程安全的。  
`Pattern` 仅仅有两个序列化的组件，也就是**模式字符串** 和 **修饰值**，也就是反序列化时所需要用到的所有的用于重新编译该*pattern*的信息。
  
```Java
public final class Pattern implements java.io.Serializable
```  
### 正则表达式修饰值 ###
  
1. `public static final int UNIX_LINES = 0x01;`  
开启 *Unix* 行模式。在该模式只有 '\n' 终结符是被 ' . ^ $ ' 公认的行为。(?d)  
```Java
// 两个等效
RegExp r1 = RegExp.compile("abd"), Pattern.I | Pattern.M);
RegExp r2 = RegExp.compile("(?im)abc", 0);
```   
2. `public static final int CASE_INSENSITIVE = 0x02;`  
启用大小写不敏感模式。(?!)   
3. `public static final int COMMENTS = 0x04;`  
在匹配模式中允许出现空格和注释。在该模式中，空格被忽略掉，内嵌的以 '#' 开头的注释会被忽略直到行尾。(?x)  
4. `public static final int MULTILINE = 0x08;`  
开启多行模式。在多行模式中， ** ^ $** 分别值匹配每行的开头和结尾。在默认情况下，则是会匹配整个输入字符串的开头和结尾。(?m)  
5. `public static final int LITERAL = 0x10;`    
开启文字模式解析。在该模式下，所有的元字符和转义序列会被认为是没有特殊意义。与其他修饰一起使用时，只有 *CASE_INSENSITIVE, UNICODE_CASE* 会影响匹配结果，其他的修饰都是多余的。  
6. `public static final int DOTALL = 0x20;`  
该模式下， '.' 可以匹配任意的字符，包括行结束符。(?s)  
7. `public static final int UNICODE_CASE = 0x40;`  
该模式下，不区分大小写匹配。(?u)  
8. `public static final int CANON_EQ = 0x80;`  
9. `public static final int UNICODE_CHARACTER_CLASS = 0x100;`  

### 实例域 ###
  
1. `private String pattern` 原始的正则表达式模式字符串。  
2. `orivate int flags` 原始的模式标记。  
当序列化时，只有上述两个域会被保存。  
3. `private transient volatile boolean compiled = false` 指示该 *Pattern* 是否被编译。  
4. `private transient String normalizedPattern` 表示标准化的模式字符串。  
5. `transient Node root` 是状态机开始查找操作的起点，可以允许起点在输入字符序列的任何地方。  
6. `transient Node matchRoot` 查找操作对象树的根节点。匹配模式从其起点开始匹配。  
7. `transient int[] buffer` 解析模式片的暂存。  
8. `transient volatile Map<String, Integer> namedGroups` 将命名捕获组的名字映射到对应的捕获组 *id* 。  
9. `transient GroupHead[] groupNodes` 解析组引用 (*group reference*) 时的暂存。  
10. `private transient int[] temp` 模式编译用到的暂时空终结代码点数组。  
11. `transient int capturingGroupCount` 表示该 *Pattern* 捕获组的数量。用于 *matchers* 去分配用于执行匹配的存储空间。  
12. `transient int localCount` 用于解析树。  
13. `private transient int cursor` *pattern* 字符串的索引，用于追踪多少字符已经被解析。  
14. `private transient int patternLength` *pattern* 字符串的长度。  
15. `private transient boolean hasSupplementary` 用于指示开始节点是否可能匹配到补充的字符序列。编译时设置为 *true* 的情况包括：如果 1）在 *pattern* 中有补充字符。 2）有完整的类或块的节点。  
  
### 构造函数 ###
  
1. `compile(String regex)` 实际调用 `Pattern(regex, 0)`。  


### 重点方法 ###
  
1. `compile()`
  
### 静态方法 ###
  
1. `public static Pattern compile(String regex)` 将给定的正则表达式编译成一个 *pattern* 。  、
如果表达式有语法错误，则会抛出 *PatternSyntaxException* 异常。  
2. `public static Pattern compile(String regex, int flags)` 以指定的标志编译正则表达式。 *flags* 就是正则表达式修饰值。可能抛出的异常有： *PatternSyntaxException ， IllegalArgumentException* 。  

### 一般方法 ###
  
1. `public String pattern()` 返回该编译成该 *pattern* 的正则表达式。  
2. `public String toString()` 同上。  
3. `public int flags()` 返回 *pattern* 匹配的标志。  

