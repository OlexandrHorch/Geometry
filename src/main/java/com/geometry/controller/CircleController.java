package com.geometry.controller;

import com.geometry.entity.Circle;
import com.geometry.service.ServiceForNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CircleController {
    private ServiceForNumber serviceForNumber = new ServiceForNumber();

    @GetMapping("/circle")
    public ModelAndView index(@RequestParam(required = false) Double radiusRequest,
                              @RequestParam(required = false) Double diameterRequest,
                              @RequestParam(required = false) Double perimeterRequest,
                              @RequestParam(required = false) Double squareRequest) {

        ModelAndView result = new ModelAndView("circle");
        Circle circle = new Circle();

        // filling the object with input values
        if (radiusRequest != null) {
            circle.setRadius(radiusRequest);
        }
        if (diameterRequest != null) {
            circle.setDiameter(diameterRequest);
        }
        if (perimeterRequest != null) {
            circle.setPerimeter(perimeterRequest);
        }
        if (squareRequest != null) {
            circle.setSquare(squareRequest);
        }

        // checking data for calculation
        checkingDataForCalculation(result, circle);

        return result;
    }


    // Method for checking data for calculation
    private void checkingDataForCalculation(ModelAndView result, Circle circle) {
        // checking data for calculation by radius
        if (circle.getRadius() > 0) {
            result.addObject("circle", calculationByRadius(circle));
        }

        // checking data for calculation by diameter
        else if (circle.getDiameter() > 0) {
            result.addObject("circle", calculationByDiameter(circle));
        }

        // checking data for calculation by perimeter
        else if (circle.getPerimeter() > 0) {
            result.addObject("circle", calculationByPerimeter(circle));
        }

        // checking data for calculation by square
        else if (circle.getSquare() > 0) {
            result.addObject("circle", calculationBySquare(circle));
        }

        // replacement negative data for zero
        else {
            result.addObject("circle", replacementNegativeDataForZero(circle));
        }
    }


    // Method for calculation by radius
    private Circle calculationByRadius(Circle circle) {
        double diameter = 2 * circle.getRadius();
        double perimeter = 2 * Math.PI * circle.getRadius();
        double square = Math.PI * Math.pow(circle.getRadius(), 2);

        circle.setDiameter(serviceForNumber.roundingNumber(diameter));
        circle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        circle.setSquare(serviceForNumber.roundingNumber(square));

        return circle;
    }


    // Method for calculation by diameter
    private Circle calculationByDiameter(Circle circle) {
        double radius = circle.getDiameter() / 2;
        double perimeter = Math.PI * circle.getDiameter();
        double square = ( Math.PI * Math.pow(circle.getDiameter(), 2) ) / 4;

        circle.setRadius(serviceForNumber.roundingNumber(radius));
        circle.setPerimeter(serviceForNumber.roundingNumber(perimeter));
        circle.setSquare(serviceForNumber.roundingNumber(square));

        return circle;
    }


    // Method for calculation by perimeter
    private Circle calculationByPerimeter(Circle circle) {
        double radius = circle.getPerimeter() / ( 2 * Math.PI );
        double diameter = 2 * radius;
        double square = Math.PI * Math.pow(radius, 2);

        circle.setRadius(serviceForNumber.roundingNumber(radius));
        circle.setDiameter(serviceForNumber.roundingNumber(diameter));
        circle.setSquare(serviceForNumber.roundingNumber(square));

        return circle;
    }


    // Method for calculation by square
    private Circle calculationBySquare(Circle circle) {
        double radius = Math.sqrt(circle.getSquare() / Math.PI);
        double diameter = 2 * radius;
        double perimeter = 2 * Math.PI * radius;

        circle.setRadius(serviceForNumber.roundingNumber(radius));
        circle.setDiameter(serviceForNumber.roundingNumber(diameter));
        circle.setPerimeter(serviceForNumber.roundingNumber(perimeter));

        return circle;
    }


    // Method for replacement negative data for zero
    private Circle replacementNegativeDataForZero(Circle circle) {
        if (circle.getRadius() < 0) {
            circle.setRadius(0);
        }
        if (circle.getDiameter() < 0) {
            circle.setDiameter(0);
        }
        if (circle.getPerimeter() < 0) {
            circle.setPerimeter(0);
        }
        if (circle.getSquare() < 0) {
            circle.setSquare(0);
        }
        return circle;
    }
}