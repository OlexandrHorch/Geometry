package com.geometry.controller;

import com.geometry.entity.Triangle;
import com.geometry.service.ServiceForNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TriangleController {
    private ServiceForNumber serviceForNumber = new ServiceForNumber();

    @GetMapping("/triangle")
    public ModelAndView index(@RequestParam(required = false) Double sideARequest,
                              @RequestParam(required = false) Double sideBRequest,
                              @RequestParam(required = false) Double sideCRequest,
                              @RequestParam(required = false) Double perimeterRequest,
                              @RequestParam(required = false) Double squareRequest,
                              @RequestParam(required = false) Double heightRequest,
                              @RequestParam(required = false) Double bisectorRequest,
                              @RequestParam(required = false) Double medianRequest,
                              @RequestParam(required = false) Double angleARequest,
                              @RequestParam(required = false) Double angleBRequest,
                              @RequestParam(required = false) Double angleCRequest) {

        ModelAndView result = new ModelAndView("triangle");
        Triangle triangle = new Triangle();

        // filling the object with input values
        if (sideARequest != null) {
            triangle.setSideA(sideARequest);
        }
        if (sideBRequest != null) {
            triangle.setSideB(sideBRequest);
        }
        if (sideCRequest != null) {
            triangle.setSideC(sideCRequest);
        }
        if (angleARequest != null) {
            triangle.setAngleA(angleARequest);
        }
        if (angleBRequest != null) {
            triangle.setAngleB(angleBRequest);
        }
        if (angleCRequest != null) {
            triangle.setAngleC(angleCRequest);
        }

        // checking data for calculation
        checkingDataForCalculation(result, triangle);

        return result;
    }


    // Method for checking data for calculation
    private void checkingDataForCalculation(ModelAndView result, Triangle triangle) {
        // checking data for calculation by side A, side B, side C
        if (triangle.getSideA() > 0 && triangle.getSideB() > 0 && triangle.getSideC() > 0) {
            result.addObject("triangle", calculationBySideASideBSideC(triangle));
        }

        // checking data for calculation by side A, angle B, angle C
        else if (triangle.getSideA() > 0 && triangle.getAngleB() > 0 && triangle.getAngleC() > 0) {
            result.addObject("triangle", calculationBySideAAngleBAngleC(triangle));
        }

        // checking data for calculation by side A, side B, angle A
        else if (triangle.getSideA() > 0 && triangle.getSideB() > 0 && triangle.getAngleA() > 0) {
            result.addObject("triangle", calculationBySideASideBAngleA(triangle));
        }

        // replacement negative data for zero
        else {
            result.addObject("triangle", replacementNegativeDataForZero(triangle));
        }
    }


    // Method for calculation by side A, side B, side C
    private Triangle calculationBySideASideBSideC(Triangle triangle) {
        double perimeter = triangle.getSideA() + triangle.getSideB() + triangle.getSideC();
        double halfPerimeter = perimeter / 2;
        double square = Math.sqrt(halfPerimeter * ( halfPerimeter - triangle.getSideA() ) * ( halfPerimeter - triangle.getSideB() ) * ( halfPerimeter - triangle.getSideC() ));
        double height = 2 * ( Math.sqrt(halfPerimeter * ( halfPerimeter - triangle.getSideA() ) * ( halfPerimeter - triangle.getSideB() ) * ( halfPerimeter - triangle.getSideC() )) / triangle.getSideA() );
        double bisector = 2 * ( Math.sqrt(triangle.getSideA() * triangle.getSideB() * halfPerimeter * ( halfPerimeter - triangle.getSideC() )) / ( triangle.getSideA() + triangle.getSideB() ) );
        double median = Math.sqrt(2 * Math.pow(triangle.getSideA(), 2) + 2 * Math.pow(triangle.getSideC(), 2) - Math.pow(triangle.getSideB(), 2)) / 2;
        double angleA = Math.toDegrees(Math.acos(( Math.pow(triangle.getSideA(), 2) + Math.pow(triangle.getSideB(), 2) - Math.pow(triangle.getSideC(), 2) ) / ( 2 * triangle.getSideA() * triangle.getSideB() )));
        double angleB = Math.toDegrees(Math.acos(( Math.pow(triangle.getSideB(), 2) + Math.pow(triangle.getSideC(), 2) - Math.pow(triangle.getSideA(), 2) ) / ( 2 * triangle.getSideB() * triangle.getSideC() )));
        double angleC = Math.toDegrees(Math.acos(( Math.pow(triangle.getSideA(), 2) + Math.pow(triangle.getSideC(), 2) - Math.pow(triangle.getSideB(), 2) ) / ( 2 * triangle.getSideA() * triangle.getSideC() )));

        triangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        triangle.setSquare(serviceForNumber.roundingNumber(square));
        triangle.setHeight(serviceForNumber.roundingNumber(height));
        triangle.setBisector(serviceForNumber.roundingNumber(bisector));
        triangle.setMedian(serviceForNumber.roundingNumber(median));
        triangle.setAngleA(serviceForNumber.roundingNumber(angleA));
        triangle.setAngleB(serviceForNumber.roundingNumber(angleB));
        triangle.setAngleC(serviceForNumber.roundingNumber(angleC));

        return triangle;
    }


    // Method for calculation by side A, angle β, angle γ
    private Triangle calculationBySideAAngleBAngleC(Triangle triangle) {
        double angleA = 180 - triangle.getAngleB() - triangle.getAngleC();
        double sideB = triangle.getSideA() * Math.sin(Math.toRadians(triangle.getAngleC())) / Math.sin(Math.toRadians(triangle.getAngleB()));
        double sideC = triangle.getSideA() * Math.sin(Math.toRadians(angleA)) / Math.sin(Math.toRadians(triangle.getAngleB()));
        double perimeter = triangle.getSideA() + sideB + sideC;
        double square = triangle.getSideA() * sideB * Math.sin(Math.toRadians(angleA)) / 2;
        double height = sideC * Math.sin(Math.toRadians(triangle.getAngleC()));
        double bisector = 2 * triangle.getSideA() * sideB * Math.cos(Math.toRadians(angleA / 2)) / ( triangle.getSideA() + sideB );
        double median = Math.sqrt(2 * Math.pow(triangle.getSideA(), 2) + 2 * Math.pow(sideC, 2) + 2 * triangle.getSideA() * sideC * Math.cos(Math.toRadians(triangle.getAngleC()))) / 2;

        triangle.setSideB(serviceForNumber.roundingNumber(sideB));
        triangle.setSideC(serviceForNumber.roundingNumber(sideC));
        triangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        triangle.setSquare(serviceForNumber.roundingNumber(square));
        triangle.setHeight(serviceForNumber.roundingNumber(height));
        triangle.setBisector(serviceForNumber.roundingNumber(bisector));
        triangle.setMedian(serviceForNumber.roundingNumber(median));
        triangle.setAngleA(serviceForNumber.roundingNumber(angleA));

        return triangle;
    }


    // Method for calculation by side A, side B, angle A
    private Triangle calculationBySideASideBAngleA(Triangle triangle) {
        double sideC = Math.sqrt(Math.pow(triangle.getSideA(), 2) + Math.pow(triangle.getSideB(), 2) - 2 * triangle.getSideA() * triangle.getSideB() * Math.cos(Math.toRadians(triangle.getAngleA())));
        double perimeter = triangle.getSideA() + triangle.getSideB() + sideC;
        double square = triangle.getSideA() * triangle.getSideB() * Math.sin(Math.toRadians(triangle.getAngleA())) / 2;
        double height = triangle.getSideB() * Math.sin(Math.toRadians(triangle.getAngleA()));
        double bisector = 2 * triangle.getSideA() * triangle.getSideB() * Math.cos(Math.toRadians(triangle.getAngleA() / 2)) / ( triangle.getSideA() + triangle.getSideB() );
        double angleB = Math.toDegrees(Math.acos(( Math.pow(triangle.getSideB(), 2) + Math.pow(sideC, 2) - Math.pow(triangle.getSideA(), 2) ) / ( 2 * triangle.getSideB() * sideC )));
        double angleC = Math.toDegrees(Math.acos(( Math.pow(triangle.getSideA(), 2) + Math.pow(sideC, 2) - Math.pow(triangle.getSideB(), 2) ) / ( 2 * triangle.getSideA() * sideC )));
        double median = Math.sqrt(Math.pow(triangle.getSideA(), 2) + Math.pow(sideC, 2) + 2 * triangle.getSideA() * sideC * Math.cos(Math.toRadians(angleC))) / 2;

        triangle.setSideC(serviceForNumber.roundingNumber(sideC));
        triangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        triangle.setSquare(serviceForNumber.roundingNumber(square));
        triangle.setHeight(serviceForNumber.roundingNumber(height));
        triangle.setBisector(serviceForNumber.roundingNumber(bisector));
        triangle.setMedian(serviceForNumber.roundingNumber(median));
        triangle.setAngleB(serviceForNumber.roundingNumber(angleB));
        triangle.setAngleC(serviceForNumber.roundingNumber(angleC));

        return triangle;
    }


    // Method for replacement negative data for zero
    private Triangle replacementNegativeDataForZero(Triangle triangle) {
        if (triangle.getSideA() < 0) {
            triangle.setSideA(0);
        }

        if (triangle.getSideB() < 0) {
            triangle.setSideB(0);
        }

        if (triangle.getSideC() < 0) {
            triangle.setSideC(0);
        }

        if (triangle.getPerimeter() < 0) {
            triangle.setPerimeter(0);
        }

        if (triangle.getSquare() < 0) {
            triangle.setSquare(0);
        }

        if (triangle.getHeight() < 0) {
            triangle.setHeight(0);
        }

        if (triangle.getBisector() < 0) {
            triangle.setBisector(0);
        }

        if (triangle.getMedian() < 0) {
            triangle.setMedian(0);
        }

        if (triangle.getAngleA() < 0) {
            triangle.setAngleA(0);
        }

        if (triangle.getAngleB() < 0) {
            triangle.setAngleB(0);
        }

        if (triangle.getAngleC() < 0) {
            triangle.setAngleC(0);
        }

        return triangle;
    }
}