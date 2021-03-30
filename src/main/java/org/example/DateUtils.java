package org.example;

import org.example.model.Instant;
import org.example.model.JsonLog;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

public class DateUtils {
    public static void main(String[] args) {

        JsonLog jsonLog = new JsonLog();
        jsonLog.setInstant(new Instant(1603812519L, 839000000));
        LocalDateTime timestamp = LocalDateTime.ofEpochSecond(
                Optional.of(jsonLog)
                        .map(o->o.getInstant())
                        .map(o->o.getEpochSecond())
                        .orElse(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8))),
                Optional.of(jsonLog)
                        .map(o->o.getInstant())
                        .map(o->o.getNanoOfSecond())
                        .orElse(LocalDateTime.now().getNano()),
                ZoneOffset.ofHours(8));
        System.out.println(timestamp);
    }
}
