package org.example.eithersample;

import java.util.stream.Stream;

import static org.example.eithersample.Either.liftWithValue;

public class App {

    public static void main(String[] args) {
        System.out.println(
                Stream.of(0,1,2,3,4)
                        .map(liftWithValue(n->10/n))
                        .filter(either -> either.isLeft())
                        .findFirst().get()
        );

    }
}
