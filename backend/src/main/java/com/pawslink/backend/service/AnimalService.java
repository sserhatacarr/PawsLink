package com.pawslink.backend.service;

import com.pawslink.backend.exception.ResourceNotFoundException;
import com.pawslink.backend.model.Animal;
import com.pawslink.backend.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Animal> getAnimalsNearby(double latitude, double longitude, double radiusKm) {
        return animalRepository.findWithinRadius(latitude, longitude, radiusKm);
    }

    @Transactional
    public Animal reportAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal updateAnimal(Long id, Animal updated) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));

        animal.setName(updated.getName());
        animal.setSpecies(updated.getSpecies());
        animal.setBreed(updated.getBreed());
        animal.setStatus(updated.getStatus());
        animal.setLatitude(updated.getLatitude());
        animal.setLongitude(updated.getLongitude());
        animal.setLocationDescription(updated.getLocationDescription());

        return animalRepository.save(animal);
    }

    @Transactional
    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Animal not found with id: " + id);
        }
        animalRepository.deleteById(id);
    }
}
