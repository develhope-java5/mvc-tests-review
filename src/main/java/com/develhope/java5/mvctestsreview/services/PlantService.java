package com.develhope.java5.mvctestsreview.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develhope.java5.mvctestsreview.entities.Plant;
import com.develhope.java5.mvctestsreview.repositories.PlantRepository;

@Service
public class PlantService {
    @Autowired
    private PlantRepository repository; 

    public Collection<Plant> getAll() {
        return repository.findAll();
    }

    public Optional<Plant> getOne(long id) {
        return repository.findById(id);
    }

    public Plant save(Plant plant) {
        return repository.save(plant);
    }

    // TODO: Implement
    public void delete(Long id) {

    }
}
