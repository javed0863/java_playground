package org.example.reactive;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

public class StockServer {
    public static Observable<StockInfo> getFeed(List<String> symbols) {
        return Observable.create(observableEmitter -> emit(observableEmitter,symbols));
    }

    private static void emit(ObservableEmitter<StockInfo> observableEmitter, List<String> symbols) {
        System.out.println("Start Emitting...");
        int count = 0;
        while (count <3){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            symbols.stream()
                    .map(StockInfo::fetch)
                    .forEach(observableEmitter::onNext);
            count++;
            //throw new RuntimeException("Error :(");
        }

        observableEmitter.onComplete();
        observableEmitter.onNext(new StockInfo("dummy"));
    }
}
