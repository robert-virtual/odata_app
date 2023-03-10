package com.example.odata_app.repository;

import com.example.odata_app.model.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMakerRepository extends JpaRepository<CarMaker,Long> {
}
