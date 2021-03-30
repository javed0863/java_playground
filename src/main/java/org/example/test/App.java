package org.example.test;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

public class App {
    public static int solution(int[] A) {
        int result = Integer.MAX_VALUE;
        HashMap<Integer, Boolean> cities = new HashMap<>();

        for (int element : A) {
            cities.put(element, false);
        }

        for (int i = 0; i < A.length; i++) {
            cities.replaceAll((k, v) -> v = false);
            for (int j = i; j < A.length; j++) {
                cities.put(A[j], true);
                int size = 0;
                for (Integer key : cities.keySet()) {
                    if (cities.get(key)) {
                        size++;
                    }
                }
                if (size == cities.size()) {
                    if (j - i + 1 < result) {
                        result = j - i + 1;
                    }
                }
            }
        }

        return result;
    }

    static int evaluatePostfix(String s) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Stack<Integer> stack = new Stack<>();

        String[] operations = s.split(" ");
        for (int i = 0; i < operations.length; i++) {
            String c = operations[i];

            if(pattern.matcher(c).matches()){
                stack.push(Integer.parseInt(c));
            }else if(c.equalsIgnoreCase("POP")){
                stack.pop();
            }else if(c.equalsIgnoreCase("DUP")){
                int latest = stack.peek();
                stack.push(latest);
            }else {
                if(stack.size() == 1){
                    return -1;
                }
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case "+":
                        stack.push(val1 + val2);
                        break;
                    case "-":
                        stack.push(val1 - val2);
                        break;
                }
            }

        }
        return stack.pop();
    }

    public static void main(String[] args) {
        int[] intArray = new int[]{2,1,1,3,2,1,1,3};
        System.out.println(solution(intArray));

        int[] intArray2 = new int[]{7,5,2,7,2,7,4,7};
        System.out.println(solution(intArray2));

        System.out.println(evaluatePostfix("4 5 6 - 7 +"));
        System.out.println(evaluatePostfix("13 DUP 4 POP 5 DUP + DUP + -"));
        System.out.println(evaluatePostfix("5 6 - +"));
    }
}
