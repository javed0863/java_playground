package org.example.collectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4)
                                                .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        System.out.println(collect.toString());
    }
}

