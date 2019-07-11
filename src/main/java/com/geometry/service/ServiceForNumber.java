package com.geometry.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ServiceForNumber {

    // Method for rounding number
    public Double roundingNumber(Double number) {
        int numberDecimalPlaces = 2;
        return new BigDecimal(number).setScale(numberDecimalPlaces, RoundingMode.HALF_UP).doubleValue();
    }
}