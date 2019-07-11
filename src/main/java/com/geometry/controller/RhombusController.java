package com.geometry.controller;

import com.geometry.entity.Rhombus;
import com.geometry.service.ServiceForNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RhombusController {
    private ServiceForNumber serviceForNumber = new ServiceForNumber();

    @GetMapping("/rhombus")
    public ModelAndView index(@RequestParam(required = false) Double sideRequest,
                              @RequestParam(required = false) Double diagonal1Request,
                              @RequestParam(required = false) Double diagonal2Request,
                              @RequestParam(required = false) Double perimeterRequest,
                              @RequestParam(required = false) Double squareRequest,
                              @RequestParam(required = false) Double heightRequest,
                              @RequestParam(required = false) Double angleARequest,
                              @RequestParam(required = false) Double angleBRequest) {

        ModelAndView result = new ModelAndView("rhombus");
        Rhombus rhombus = new Rhombus();

        // filling the object with input values
        if (sideRequest != null) {
            rhombus.setSide(sideRequest);
        }
        if (diagonal1Request != null) {
            rhombus.setDiagonal1(diagonal1Request);
        }
        if (diagonal2Request != null) {
            rhombus.setDiagonal2(diagonal2Request);
        }
        if (perimeterRequest != null) {
            rhombus.setPerimeter(perimeterRequest);
        }
        if (squareRequest != null) {
            rhombus.setSquare(squareRequest);
        }
        if (heightRequest != null) {
            rhombus.setHeight(heightRequest);
        }
        if (angleARequest != null) {
            rhombus.setAngleA(angleARequest);
        }
        if (angleBRequest != null) {
            rhombus.setAngleB(angleBRequest);
        }

        // checking data for calculation
        checkingDataForCalculation(result, rhombus);

        return result;
    }


    // Method for checking data for calculation
    private void checkingDataForCalculation(ModelAndView result, Rhombus rhombus) {
        // checking data for calculation by side and height
        if (rhombus.getSide() > 0 && rhombus.getHeight() > 0) {
            result.addObject("rhombus", calculationBySideAndHeight(rhombus));
        }

        // checking data for calculation by diagonal 1 and diagonal 2
        else if (rhombus.getDiagonal1() > 0 && rhombus.getDiagonal2() > 0) {
            result.addObject("rhombus", calculationByDiagonal1AndDiagonal2(rhombus));
        }

        // replacement negative data for zero
        else {
            result.addObject("rhombus", replacementNegativeDataForZero(rhombus));
        }
    }


    // Method for calculation by side and height
    private Rhombus calculationBySideAndHeight(Rhombus rhombus) {
        double perimeter = 4 * rhombus.getSide();
        double square = rhombus.getSide() * rhombus.getHeight();
        double angleA = Math.toDegrees(Math.asin(rhombus.getHeight() / rhombus.getSide()));
        double angleB = 180 - angleA;
        double diagonal1 = rhombus.getSide() * Math.sqrt(2 + 2 * Math.cos(Math.toRadians(angleA)));
        double diagonal2 = 2 * rhombus.getSide() * Math.sin(Math.toRadians(angleA / 2));

        rhombus.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rhombus.setSquare(serviceForNumber.roundingNumber(square));
        rhombus.setAngleA(serviceForNumber.roundingNumber(angleA));
        rhombus.setAngleB(serviceForNumber.roundingNumber(angleB));
        rhombus.setDiagonal1(serviceForNumber.roundingNumber(diagonal1));
        rhombus.setDiagonal2(serviceForNumber.roundingNumber(diagonal2));

        return rhombus;
    }


    // Method for calculation by diagonal 1 and diagonal 2
    private Rhombus calculationByDiagonal1AndDiagonal2(Rhombus rhombus) {
        double side = Math.sqrt(Math.pow(rhombus.getDiagonal1(), 2) + Math.pow(rhombus.getDiagonal2(), 2)) / 2;
        double perimeter = 2 * Math.sqrt(Math.pow(rhombus.getDiagonal1(), 2) + Math.pow(rhombus.getDiagonal2(), 2));
        double square = rhombus.getDiagonal1() * rhombus.getDiagonal2() / 2;
        double height = square / side;
        double angleA = Math.toDegrees(Math.asin(height / side));
        double angleB = 180 - angleA;

        rhombus.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rhombus.setSquare(serviceForNumber.roundingNumber(square));
        rhombus.setSide(serviceForNumber.roundingNumber(side));
        rhombus.setHeight(serviceForNumber.roundingNumber(height));
        rhombus.setAngleA(serviceForNumber.roundingNumber(angleA));
        rhombus.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rhombus;
    }


    // Method for replacement negative data for zero
    private Rhombus replacementNegativeDataForZero(Rhombus rhombus) {
        if (rhombus.getSide() < 0) {
            rhombus.setSide(0);
        }
        if (rhombus.getDiagonal1() < 0) {
            rhombus.setDiagonal1(0);
        }
        if (rhombus.getDiagonal2() < 0) {
            rhombus.setDiagonal2(0);
        }
        if (rhombus.getPerimeter() < 0) {
            rhombus.setPerimeter(0);
        }

        if (rhombus.getSquare() < 0) {
            rhombus.setSquare(0);
        }
        if (rhombus.getHeight() < 0) {
            rhombus.setHeight(0);
        }
        if (rhombus.getAngleA() < 0) {
            rhombus.setAngleA(0);
        }
        if (rhombus.getAngleB() < 0) {
            rhombus.setAngleB(0);
        }
        return rhombus;
    }
}