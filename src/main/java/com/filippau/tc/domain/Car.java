package com.filippau.tc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "car_catalog")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @NotNull
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @NotNull
    private String volume;

    @NotNull
    private String year;

    @NotNull
    private Integer price;

    @NotNull
    private String description;

    @ManyToOne
    private User carOwner;

    private String filename;

    private String filename2;

    private String filename3;

    private String filename4;

    private String filename5;

}
