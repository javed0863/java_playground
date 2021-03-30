package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        List<Integer> nums = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        System.out.println(LocalDateTime.now());
        int n = nums.stream()
                .filter(x->x<5)
                .map(integer -> integer+1)
                .reduce(Integer::sum)
                .get();
        System.out.println(n);
        System.out.println(LocalDateTime.now());
        int n1 = nums.parallelStream().filter(integer -> integer < 5).map(integer -> integer+1)
                .reduce(Integer::sum)
                .get();
        System.out.println(n1);
        System.out.println(LocalDateTime.now());
    }
}
