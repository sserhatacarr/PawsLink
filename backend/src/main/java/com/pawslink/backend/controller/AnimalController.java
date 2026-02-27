package com.pawslink.backend.controller;

import com.pawslink.backend.model.Animal;
import com.pawslink.backend.repository.AnimalRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nearby")
    public List<Animal> getAnimalsNearby(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "5.0") double radiusKm) {
        return animalRepository.findWithinRadius(lat, lon, radiusKm);
    }

    @PostMapping
    public Animal reportAnimal(@Valid @RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal updated) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setName(updated.getName());
                    animal.setSpecies(updated.getSpecies());
                    animal.setBreed(updated.getBreed());
                    animal.setStatus(updated.getStatus());
                    animal.setLatitude(updated.getLatitude());
                    animal.setLongitude(updated.getLongitude());
                    animal.setLocationDescription(updated.getLocationDescription());
                    return ResponseEntity.ok(animalRepository.save(animal));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (!animalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
