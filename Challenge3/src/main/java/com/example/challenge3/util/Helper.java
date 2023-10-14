package com.example.challenge3.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Helper {

    // Convert currency
    public static String convertCurrency(long price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat pattern = new DecimalFormat("#,###.000", symbols);
        return pattern.format(price/1000);
    }

    // Get user input
    public static int getUserChoice() {
        return new Scanner(System.in).nextInt();
    }

    public static String getUserInputInvalid() {
        return new Scanner(System.in).next();
    }

    // Current format date
    public static String getCurrentFormatDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return currentTime.format(dateFormat);
    }

    // Exit Application
    public static void exitApplication() {
        System.exit(0);
    }
}
