# Java continue循环 #
  
在看 *Java* 源代码的时候，发现 *Java* 中的`continue` 可以使用 **标签** 来实现不同层级的循环之间的跳转。示例如下：  
```Java
	loop:	for(int i = 0; i < 10; i ++){
				for(int j = 0; j < 2; j ++){
					if(j == 1)
						continue loop;   //跳转到带 1oop 标签层级的 for 循环
					System.out.println(i + " " + j);
				}
			}
```
  
输出：  
```Java
	0 0
	1 0
	2 0
	3 0
	4 0
	5 0
	6 0
	7 0
	8 0
	9 0
```

此外， ** `while` 和 `for` 的循环可以嵌套使用该特性。**
