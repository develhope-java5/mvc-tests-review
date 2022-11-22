package com.develhope.java5.mvctestsreview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develhope.java5.mvctestsreview.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

}
