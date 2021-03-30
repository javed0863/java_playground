package org.example.reactive;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class StockInfo {
    private String stockInfo;

    public static StockInfo fetch(String s) {
        return new StockInfo(s+" : " +LocalDateTime.now().getNano());
    }
}
