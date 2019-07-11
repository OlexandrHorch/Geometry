package com.geometry.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rhombus")
public class Rhombus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "side")
    private double side;

    @Column(name = "diagonal1")
    private double diagonal1;

    @Column(name = "diagonal2")
    private double diagonal2;

    @Column(name = "perimeter")
    private double perimeter;

    @Column(name = "square")
    private double square;

    @Column(name = "height")
    private double height;

    @Column(name = "angleA")
    private double angleA;

    @Column(name = "angleB")
    private double angleB;
}