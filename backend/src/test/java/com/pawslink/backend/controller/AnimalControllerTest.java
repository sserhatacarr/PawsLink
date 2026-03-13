package com.pawslink.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawslink.backend.exception.ResourceNotFoundException;
import com.pawslink.backend.model.Animal;
import com.pawslink.backend.security.SecurityConfig;
import com.pawslink.backend.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimalController.class)
@Import(SecurityConfig.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Animal sampleAnimal;

    @BeforeEach
    void setUp() {
        sampleAnimal = new Animal();
        sampleAnimal.setId(1L);
        sampleAnimal.setName("Buddy");
        sampleAnimal.setSpecies("Dog");
        sampleAnimal.setBreed("Golden Retriever");
        sampleAnimal.setStatus(Animal.AnimalStatus.REPORTED);
        sampleAnimal.setLatitude(41.0082);
        sampleAnimal.setLongitude(28.9784);
        sampleAnimal.setLocationDescription("Istanbul, Kadikoy");
    }

    @Test
    @WithMockUser
    void getAllAnimals_shouldReturnAnimalsList() throws Exception {
        when(animalService.getAllAnimals()).thenReturn(List.of(sampleAnimal));

        mockMvc.perform(get("/api/v1/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"))
                .andExpect(jsonPath("$[0].species").value("Dog"));
    }

    @Test
    @WithMockUser
    void getAnimalById_whenExists_shouldReturnAnimal() throws Exception {
        when(animalService.getAnimalById(1L)).thenReturn(sampleAnimal);

        mockMvc.perform(get("/api/v1/animals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"))
                .andExpect(jsonPath("$.species").value("Dog"));
    }

    @Test
    @WithMockUser
    void getAnimalById_whenNotExists_shouldReturn404() throws Exception {
        when(animalService.getAnimalById(99L))
                .thenThrow(new ResourceNotFoundException("Animal not found with id: 99"));

        mockMvc.perform(get("/api/v1/animals/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getNearbyAnimals_shouldBeAccessibleWithoutAuth() throws Exception {
        when(animalService.getAnimalsNearby(41.0, 29.0, 5.0)).thenReturn(List.of(sampleAnimal));

        mockMvc.perform(get("/api/v1/animals/nearby")
                        .param("lat", "41.0")
                        .param("lon", "29.0")
                        .param("radiusKm", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"));
    }

    @Test
    @WithMockUser
    void reportAnimal_shouldReturnCreatedAnimal() throws Exception {
        when(animalService.reportAnimal(any(Animal.class))).thenReturn(sampleAnimal);

        mockMvc.perform(post("/api/v1/animals")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAnimal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    @WithMockUser
    void updateAnimal_whenExists_shouldReturnUpdatedAnimal() throws Exception {
        when(animalService.updateAnimal(eq(1L), any(Animal.class))).thenReturn(sampleAnimal);

        mockMvc.perform(put("/api/v1/animals/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAnimal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    @WithMockUser
    void deleteAnimal_whenExists_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/v1/animals/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void deleteAnimal_whenNotExists_shouldReturn404() throws Exception {
        doThrow(new ResourceNotFoundException("Animal not found with id: 99"))
                .when(animalService).deleteAnimal(99L);

        mockMvc.perform(delete("/api/v1/animals/99")
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }
}
