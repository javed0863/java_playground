package org.example.reactive;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;


public class App {

    public static void main(String[] args) {
        final List<String> symbols = Arrays.asList("GOOG", "AMZN", "MSFT");
        final Observable<StockInfo> feed = StockServer.getFeed(symbols)
                .filter(s -> s.getStockInfo().contains("AMZN"))
                .map(stockInfo -> new StockInfo("GOOG-"+stockInfo.getStockInfo()))
                .take(2);
        System.out.println("Got Observable");

        feed.subscribe(new Observer<StockInfo>() {

            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(@NonNull StockInfo stockInfo) {
                /*if(stockInfo.getStockInfo().contains("AMZN")){
                    System.out.println("Unsubscribing...");
                    disposable.dispose();
                }*/
                System.out.println(stockInfo.getStockInfo());
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("DONE");
            }
        });


        /*feed.subscribe(
                o -> System.out.println(o.getStockInfo()),
                e -> System.out.println(e.getMessage() + " !!!"),
                () -> System.out.println("DONE :D")
        );*/
    }
}
