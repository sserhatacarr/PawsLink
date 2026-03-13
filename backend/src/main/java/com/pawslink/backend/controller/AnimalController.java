package com.pawslink.backend.controller;

import com.pawslink.backend.model.Animal;
import com.pawslink.backend.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping("/nearby")
    public List<Animal> getAnimalsNearby(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "5.0") double radiusKm) {
        return animalService.getAnimalsNearby(lat, lon, radiusKm);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal reportAnimal(@Valid @RequestBody Animal animal) {
        return animalService.reportAnimal(animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal updated) {
        return animalService.updateAnimal(id, updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
