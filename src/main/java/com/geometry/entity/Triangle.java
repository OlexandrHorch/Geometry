package com.geometry.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "triangle")
public class Triangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sideA")
    private double sideA;

    @Column(name = "sideB")
    private double sideB;

    @Column(name = "sideC")
    private double sideC;

    @Column(name = "perimeter")
    private double perimeter;

    @Column(name = "square")
    private double square;

    @Column(name = "height")
    private double height;

    @Column(name = "bisector")
    private double bisector;

    @Column(name = "median")
    private double median;

    @Column(name = "angleA")
    private double angleA;

    @Column(name = "angleB")
    private double angleB;

    @Column(name = "angleC")
    private double angleC;
}