# 注解 #
  
*Java 1.5* 发行版本中增加注解，类库中也增加了几种注解类型。  
  
## 坚持使用 Override 注解 ##
  
*Override* 注解只能用在方法声明中，它表示被注解的方法声明覆盖了超类型中的一个声明。如果坚持使用这个注解，可以防止一大类的非法错误。  
  
```Java  
public class Bigram{
	private final char first;
	private final char second;
	public Bigram(char first, char second) {
		this.first = first;
		this.second = second;
	}

	public boolean equals (Bigram b) {
		return b.first == first && b.second == second;
	}  
	public static hashCode () {
		return 31 * first + second;
	}

	public static void main(String[] args) {
		Set<Bigram> s = new HashSet<Bigram>();
		for(int i = 0; i < 10; i ++)
			for(char ch = 'a'; ch <= 'z' ; ch ++)
				s.add(new Bigram(ch, ch));
		System.out.println(s.size());
	}
}
```  
  
原本期待是大打印值为 26， 因为集合不能包含重复值。事实上，确是输出值为 260。**原因是：本意是要去覆盖 Object 类型中的 equals() 和 hashCode() 方法，实际确是将它们重载了。**  
  
如果使用 *Override* 注解，对于上面代码的编译会产生错误。可以提供正确的覆盖方法：  
```Java
	@Override
	public boolean equals (Object o) {
		if(!(o instance Bigram))
			return false
		Bigram b = (Bigram) o;
		return b.first == first && b.second == second;
	}  
```  
  
应该在想要覆盖超类声明的每个方法声明中使用 *Override* 注解。有一个例外，如果编写一个没有标注为抽象的类，并且确信它覆盖了抽象的方法，在这种情况下，就不必将 *Override* 注解放在该方法上。  
  
**总结**：  
* 在想要的每个方法声明中使用 *Override* 注解来覆盖超类声明，编译器可以答题防止大量的错误。  
* 例外情况是，在具体的类中，不必标注确信覆盖了抽象方法声明的方法。