package com.develhope.java5.mvctestsreview.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.develhope.java5.mvctestsreview.entities.Plant;
import com.develhope.java5.mvctestsreview.services.PlantService;

@Controller
@RequestMapping("/plants")
public class PlantController {
    @Autowired
    private PlantService service; 

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<Plant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Plant> getOne(@PathVariable Long id) {
        Optional<Plant> result = service.getOne(id);

        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(result.get());
        }
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Plant insert(@RequestBody Plant plant) {
        return service.save(plant);
    }
}
