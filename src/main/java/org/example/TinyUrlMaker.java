package org.example;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;

public class TinyUrlMaker {

    private static final char[] corpus   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static void main(String[] args) {
        Long seed = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) +
                Long.valueOf(LocalDateTime.now().getNano());
        System.out.println(
                getBase62From10(seed)
        );

        System.out.println(
                getBase10From62(Long.parseLong(getBase62From10(seed)))
        );
    }

    /**
     * Note if seed is unique then generated base62 number will be unique as well under
     * load balance make sure this value is not same.
     */
    public static final String getBase62From10(final long seed)
    {
        String number = seed + "";
        char[] buf = new char[number.length()];
        int charPos = number.length() - 1;
        BigInteger bigIntegerNumber = new BigInteger(number);
        BigInteger radix = BigInteger.valueOf(62);

        while (bigIntegerNumber.compareTo(radix) >= 0)
        {
            buf[charPos--] = corpus[bigIntegerNumber.mod(radix).intValue()];
            bigIntegerNumber = bigIntegerNumber.divide(radix);
        }
        buf[charPos] = corpus[bigIntegerNumber.intValue()];
        return new String(buf, charPos, (number.length() - charPos));
    }

    /**
     * @param longNumber
     * a positive number in base 62
     * @return the same number, in base 10
     */
    public static final String getBase10From62(final long longNumber)
    {
        String number = longNumber + "";
        BigInteger value = BigInteger.ZERO;
        for (char c : number.toCharArray())
        {
            value = value.multiply(BigInteger.valueOf(62));
            if ('0' <= c && c <= '9')
            {
                value = value.add(BigInteger.valueOf(c - '0'));
            }
            if ('a' <= c && c <= 'z')
            {
                value = value.add(BigInteger.valueOf(c - 'a' + 10));
            }
            if ('A' <= c && c <= 'Z')
            {
                value = value.add(BigInteger.valueOf(c - 'A' + 36));
            }
        }
        return value.toString();
    }
}
