package com.geometry.controller;

import com.geometry.entity.Rectangle;
import com.geometry.service.ServiceForNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RectangleController {
    private ServiceForNumber serviceForNumber = new ServiceForNumber();

    @GetMapping("/rectangle")
    public ModelAndView index(@RequestParam(required = false) Double sideARequest,
                              @RequestParam(required = false) Double sideBRequest,
                              @RequestParam(required = false) Double perimeterRequest,
                              @RequestParam(required = false) Double diagonalRequest,
                              @RequestParam(required = false) Double squareRequest,
                              @RequestParam(required = false) Double angleARequest,
                              @RequestParam(required = false) Double angleBRequest) {

        ModelAndView result = new ModelAndView("rectangle");
        Rectangle rectangle = new Rectangle();

        // filling the object with input values
        if (sideARequest != null) {
            rectangle.setSideA(sideARequest);
        }
        if (sideBRequest != null) {
            rectangle.setSideB(sideBRequest);
        }
        if (perimeterRequest != null) {
            rectangle.setPerimeter(perimeterRequest);
        }
        if (diagonalRequest != null) {
            rectangle.setDiagonal(diagonalRequest);
        }
        if (squareRequest != null) {
            rectangle.setSquare(squareRequest);
        }
        if (angleARequest != null) {
            rectangle.setAngleA(angleARequest);
        }
        if (angleBRequest != null) {
            rectangle.setAngleB(angleBRequest);
        }

        // checking data for calculation
        checkingDataForCalculation(result, rectangle);

        return result;
    }


    // Method for checking data for calculation
    private void checkingDataForCalculation(ModelAndView result, Rectangle rectangle) {
        // checking data for calculation by side A and side B
        if (rectangle.getSideA() > 0 && rectangle.getSideB() > 0) {
            result.addObject("rectangle", calculationBySideAAndSideB(rectangle));
        }

        // checking data for calculation by side A and diagonal
        else if (rectangle.getSideA() > 0 && rectangle.getDiagonal() > 0) {
            result.addObject("rectangle", calculationBySideAAndDiagonal(rectangle));
        }

        // checking data for calculation by perimeter and square
        else if (rectangle.getPerimeter() > 0 && rectangle.getSquare() > 0) {
            result.addObject("rectangle", calculationByPerimeterAndSquare(rectangle));
        }

        // checking data for calculation by diagonal and angle A
        else if (rectangle.getDiagonal() > 0 && rectangle.getAngleA() > 0) {
            result.addObject("rectangle", calculationByDiagonalAndAngleA(rectangle));
        }

        // checking data for calculation by side A and angle A
        else if (rectangle.getSideA() > 0 && rectangle.getAngleA() > 0) {
            result.addObject("rectangle", calculationBySideAAndAngleA(rectangle));
        }

        // checking data for calculation by side B and angle A
        else if (rectangle.getSideB() > 0 && rectangle.getAngleA() > 0) {
            result.addObject("rectangle", calculationBySideBAndAngleA(rectangle));
        }

        // replacement negative data for zero
        else {
            result.addObject("rectangle", replacementNegativeDataForZero(rectangle));
        }
    }


    // Method for calculation by side A and side B
    private Rectangle calculationBySideAAndSideB(Rectangle rectangle) {
        double diagonal = Math.sqrt(Math.pow(rectangle.getSideA(), 2) + Math.pow(rectangle.getSideB(), 2));
        double perimeter = 2 * ( rectangle.getSideA() + rectangle.getSideB() );
        double square = rectangle.getSideA() * rectangle.getSideB();
        double angleA = Math.toDegrees(( 2 * Math.acos(( rectangle.getSideB() / ( Math.sqrt(Math.pow(rectangle.getSideA(), 2) + Math.pow(rectangle.getSideB(), 2)) ) )) ));
        double angleB = 180 - angleA;

        rectangle.setDiagonal(serviceForNumber.roundingNumber(diagonal));
        rectangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rectangle.setSquare(serviceForNumber.roundingNumber(square));
        rectangle.setAngleA(serviceForNumber.roundingNumber(angleA));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for calculation by side A and diagonal
    private Rectangle calculationBySideAAndDiagonal(Rectangle rectangle) {
        double sideB = Math.sqrt(Math.pow(rectangle.getDiagonal(), 2) - Math.pow(rectangle.getSideA(), 2));
        double perimeter = 2 * ( rectangle.getSideA() + sideB );
        double square = rectangle.getSideA() * sideB;
        double angleA = Math.toDegrees(( 2 * Math.acos(( sideB / ( Math.sqrt(Math.pow(rectangle.getSideA(), 2) + Math.pow(sideB, 2)) ) )) ));
        double angleB = 180 - angleA;

        rectangle.setSideB(serviceForNumber.roundingNumber(sideB));
        rectangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rectangle.setSquare(serviceForNumber.roundingNumber(square));
        rectangle.setAngleA(serviceForNumber.roundingNumber(angleA));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for calculation by perimeter and square
    private Rectangle calculationByPerimeterAndSquare(Rectangle rectangle) {
        double sideA = ( ( rectangle.getPerimeter() / 2 ) - ( Math.sqrt(( Math.pow(rectangle.getPerimeter(), 2) / 4 ) - 4 * rectangle.getSquare()) ) ) / 2;
        double sideB = ( ( rectangle.getPerimeter() / 2 ) + ( Math.sqrt(( Math.pow(rectangle.getPerimeter(), 2) / 4 ) - 4 * rectangle.getSquare()) ) ) / 2;
        double diagonal = Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
        double angleA = Math.toDegrees(( 2 * Math.acos(sideB / ( Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)) )) ));
        double angleB = 180 - angleA;

        rectangle.setSideA(serviceForNumber.roundingNumber(sideA));
        rectangle.setSideB(serviceForNumber.roundingNumber(sideB));
        rectangle.setDiagonal(serviceForNumber.roundingNumber(diagonal));
        rectangle.setAngleA(serviceForNumber.roundingNumber(angleA));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for calculation by diagonal and angle A
    private Rectangle calculationByDiagonalAndAngleA(Rectangle rectangle) {
        double sideA = rectangle.getDiagonal() * Math.sin(Math.toRadians(rectangle.getAngleA()) / 2);
        double sideB = rectangle.getDiagonal() * Math.cos(Math.toRadians(rectangle.getAngleA()) / 2);
        double perimeter = 2 * ( sideA + sideB );
        double square = sideA * sideB;
        double angleB = 180 - rectangle.getAngleA();

        rectangle.setSideA(serviceForNumber.roundingNumber(sideA));
        rectangle.setSideB(serviceForNumber.roundingNumber(sideB));
        rectangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rectangle.setSquare(serviceForNumber.roundingNumber(square));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for calculation by side A and angle A
    private Rectangle calculationBySideAAndAngleA(Rectangle rectangle) {
        double sideB = rectangle.getSideA() / ( Math.tan(Math.toRadians(rectangle.getAngleA()) / 2) );
        double diagonal = rectangle.getSideA() / Math.sin(Math.toRadians(rectangle.getAngleA() / 2));
        double perimeter = 2 * ( rectangle.getSideA() + sideB );
        double square = rectangle.getSideA() * sideB;
        double angleB = 180 - rectangle.getAngleA();

        rectangle.setSideB(serviceForNumber.roundingNumber(sideB));
        rectangle.setDiagonal(serviceForNumber.roundingNumber(diagonal));
        rectangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rectangle.setSquare(serviceForNumber.roundingNumber(square));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for calculation by side B and angle A
    private Rectangle calculationBySideBAndAngleA(Rectangle rectangle) {
        double sideA = rectangle.getSideB() * ( Math.tan(Math.toRadians(rectangle.getAngleA()) / 2) );
        double diagonal = rectangle.getSideB() / Math.cos(Math.toRadians(rectangle.getAngleA() / 2));
        double perimeter = 2 * ( sideA + rectangle.getSideB() );
        double square = sideA * rectangle.getSideB();
        double angleB = 180 - rectangle.getAngleA();

        rectangle.setSideA(serviceForNumber.roundingNumber(sideA));
        rectangle.setDiagonal(serviceForNumber.roundingNumber(diagonal));
        rectangle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        rectangle.setSquare(serviceForNumber.roundingNumber(square));
        rectangle.setAngleB(serviceForNumber.roundingNumber(angleB));

        return rectangle;
    }


    // Method for replacement negative data for zero
    private Rectangle replacementNegativeDataForZero(Rectangle rectangle) {
        if (rectangle.getSideA() < 0) {
            rectangle.setSideA(0);
        }
        if (rectangle.getSideB() < 0) {
            rectangle.setSideB(0);
        }
        if (rectangle.getPerimeter() < 0) {
            rectangle.setPerimeter(0);
        }
        if (rectangle.getDiagonal() < 0) {
            rectangle.setDiagonal(0);
        }
        if (rectangle.getSquare() < 0) {
            rectangle.setSquare(0);
        }
        if (rectangle.getAngleA() < 0) {
            rectangle.setAngleA(0);
        }
        if (rectangle.getAngleB() < 0) {
            rectangle.setAngleB(0);
        }
        return rectangle;
    }
}