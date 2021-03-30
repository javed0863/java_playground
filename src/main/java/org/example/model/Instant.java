package org.example.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Instant {

    public Long epochSecond;
    public Integer nanoOfSecond;

}
