package org.example.codility;

public class Test2 {

	public int solution(int A) {
        // write your code in Java SE 8
    	String num = String.valueOf(A);
    	int len = num.length();
    	
    	String temp="";
    	int count = 0;
    	for(int i=0;i<len;i++){
    		if(i%2 == 0){
    			temp=temp+num.charAt(count);
    			count++;
    		}else{
    			temp=temp+num.charAt(len-count);
    		}
    		
    		//System.out.println("Temp String: "+temp);
    	}
    	
    	return Integer.valueOf(temp);
    }    
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 t = new Test2();
		//System.out.println(t.solution(123456));
		System.out.println(t.solution(-123456789));
		
	}

}
