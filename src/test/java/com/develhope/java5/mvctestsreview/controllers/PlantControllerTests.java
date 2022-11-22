package com.develhope.java5.mvctestsreview.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.develhope.java5.mvctestsreview.entities.Plant;
import com.develhope.java5.mvctestsreview.services.PlantService;

@SpringBootTest
@AutoConfigureMockMvc
public class PlantControllerTests {
    @Autowired
    private PlantService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllWhenEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/plants"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @DirtiesContext
    void testInsert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/plants/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Ficus\", \"quantity\": 100 }")
        )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Ficus\",\"quantity\":100}"));
    }

    @Test
    @DirtiesContext
    void testGetAllWhenOnePlantIsPresent() throws Exception {
        // NOTA: L'id con cui creiamo l'oggetto non viene considerato, viene rigenerato dal database
        service.save(new Plant(0L, "Ficus", 10));

        mockMvc.perform(MockMvcRequestBuilders.get("/plants"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"Ficus\",\"quantity\":10}]"));
    }

    @Test
    @DirtiesContext
    void testGetOneWhenPlantIsPresent() throws Exception {
        service.save(new Plant(0L, "Ficus", 10));

        mockMvc.perform(MockMvcRequestBuilders.get("/plants/1"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Ficus\",\"quantity\":10}"));
    }

    @Test
    void testGetOneWhenPlantIsNotPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/plants/2"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void testGetOneWhenIdIsNotNumeric() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/plants/ficus"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testDeleteWhenPlantIsNotPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/plants/2"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DirtiesContext
    void testDeleteWhenPlantIsPresent() throws Exception {
        service.save(new Plant(0L, "Ficus", 10));

        mockMvc.perform(MockMvcRequestBuilders.delete("/plants/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteWhenIdIsNotNumeric() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/plants/ficus"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
