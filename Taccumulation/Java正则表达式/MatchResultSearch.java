package com.github.jioong.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchResultSearch {
	
	public static void main(String[] args) {
		String str = "What is Regular Expression? "
				+ "How to learn regular expression? Is Regular expression hard to learn";
		String regex = "(regular) (expression)";
		
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		
		while (m.find()){
			int groupCount = m.groupCount();
			System.out.println("总共的匹配结果数 = " + groupCount);
			for (int i = 0; i <= groupCount; i ++){
				System.out.println("第" + i + "个匹配结果，开始索引值 = " + m.start(i) + ",结束索引值 = " + m.end(i) + 
						":匹配结果为：" + m.group(i));
			}
			System.out.println("======================================");
		}
	}

}
