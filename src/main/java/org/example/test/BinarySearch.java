package org.example.test;

public class BinarySearch {

    public static int binarySearch(int[] numbers, int search){
        int arrayLength = numbers.length;
        int start = 0;
        int end = numbers.length-1;

        int result = 0;
        for(int i=start; i <= end;i++){
            int midElement = numbers[arrayLength/2];
            if(midElement == search){
                return midElement;
            }else if(search > midElement){
                start = midElement;
                end = numbers.length-1;
            }else {
                start = i;
            }
        }
        return result;
    }
}
