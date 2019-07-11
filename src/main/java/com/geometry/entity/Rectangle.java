package com.geometry.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rectangle")
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sideA")
    private double sideA;

    @Column(name = "sideB")
    private double sideB;

    @Column(name = "diagonal")
    private double diagonal;

    @Column(name = "perimeter")
    private double perimeter;

    @Column(name = "square")
    private double square;

    @Column(name = "angleA")
    private double angleA;

    @Column(name = "angleB")
    private double angleB;
}