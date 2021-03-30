package org.example.codility;
import java.util.*;
public class Task1 {

	public static int solution(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);
        
        Set<Integer> set = new LinkedHashSet<Integer>();
        for(int i=0;i<A.length;i++){
        	if(A[i]>0){
        		set.add(A[i]);
        	}
        }
        
        if(set.size()<1){
        	return 1;
        }
        for(int i=1;i<=100000 && i<=A.length;i++){
        	if(!set.contains(i)){
        		return i;
        	}
        }
        return (A.length+1);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A=new int[]{1, 3, 6, 4, 1, 2};
		int[] B=new int[]{1, 2, 3};
		int[] C=new int[]{-1, -3};
		System.out.println(solution(A));
		System.out.println(solution(B));
		System.out.println(solution(C));
		
	}

}
