package com.geometry.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "circle")
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "radius")
    private double radius;

    @Column(name = "diameter")
    private double diameter;

    @Column(name = "perimeter")
    private double perimeter;

    @Column(name = "square")
    private double square;
}