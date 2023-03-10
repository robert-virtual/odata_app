package com.example.odata_app.model;


import jakarta.persistence.*;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String year;
    private String sku;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    private CarMaker maker;


}
