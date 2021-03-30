package org.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sample {

    public static void main(String[] args) {
        Employee e1 = new Employee("John", "1");
        Employee e2 = new Employee("Mary", "2");

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);

        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        Function<Employee, String> getLastname = e -> e.getName();

        getName(getLastname);

        int n = employees.stream()
                .map(emp -> emp.getName().length())
                .reduce(0, (e11,e12)->e11+e12);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(() -> e1.getName());


    }

    public static String getName(Function<Employee, String> func){

        return null;
    }
}

class ClassLevel<T>{
    private T instance;
}
