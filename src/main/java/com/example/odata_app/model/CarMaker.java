package com.example.odata_app.model;

import com.example.odata_app.model.CarModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CarMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "maker",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CarModel> models;

}
