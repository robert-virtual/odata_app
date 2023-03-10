package com.example.odata_app.repository;

import com.example.odata_app.model.CarMaker;
import com.example.odata_app.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
}
