package org.example.codility;

public class Test1 {
	public String solution(String s) {
        char c = s.charAt(0);
        if (Character.isUpperCase(c)) {  // please fix condition
            return "upper";
        } else if (Character.isLowerCase(c)) {  // please fix condition
            return "lower";
        } else if (Character.isDigit(c)) {  // please fix condition
            return "digit";
        } else {
            return "other";
        }
    }
	
	public static void main(String[] args) {
		Test1 t = new Test1();
		System.out.println(t.solution("Javed"));
		System.out.println(t.solution("avJd"));
		System.out.println(t.solution("1Javed"));
		System.out.println(t.solution("@Javed"));
	}
}
