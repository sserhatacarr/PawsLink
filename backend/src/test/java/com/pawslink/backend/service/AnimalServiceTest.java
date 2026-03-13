package com.pawslink.backend.service;

import com.pawslink.backend.exception.ResourceNotFoundException;
import com.pawslink.backend.model.Animal;
import com.pawslink.backend.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

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
    void getAllAnimals_shouldReturnAllAnimals() {
        when(animalRepository.findAll()).thenReturn(List.of(sampleAnimal));

        List<Animal> result = animalService.getAllAnimals();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Buddy");
        verify(animalRepository).findAll();
    }

    @Test
    void getAnimalById_whenExists_shouldReturnAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(sampleAnimal));

        Animal result = animalService.getAnimalById(1L);

        assertThat(result.getName()).isEqualTo("Buddy");
        verify(animalRepository).findById(1L);
    }

    @Test
    void getAnimalById_whenNotExists_shouldThrowException() {
        when(animalRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> animalService.getAnimalById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Animal not found with id: 99");
    }

    @Test
    void reportAnimal_shouldSaveAndReturnAnimal() {
        when(animalRepository.save(any(Animal.class))).thenReturn(sampleAnimal);

        Animal result = animalService.reportAnimal(sampleAnimal);

        assertThat(result.getName()).isEqualTo("Buddy");
        verify(animalRepository).save(sampleAnimal);
    }

    @Test
    void updateAnimal_whenExists_shouldUpdateAndReturnAnimal() {
        Animal updated = new Animal();
        updated.setName("Buddy Updated");
        updated.setSpecies("Dog");
        updated.setStatus(Animal.AnimalStatus.RESCUE_IN_PROGRESS);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(sampleAnimal));
        when(animalRepository.save(any(Animal.class))).thenReturn(sampleAnimal);

        Animal result = animalService.updateAnimal(1L, updated);

        assertThat(result).isNotNull();
        verify(animalRepository).findById(1L);
        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    void updateAnimal_whenNotExists_shouldThrowException() {
        when(animalRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> animalService.updateAnimal(99L, sampleAnimal))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Animal not found with id: 99");
    }

    @Test
    void deleteAnimal_whenExists_shouldDelete() {
        when(animalRepository.existsById(1L)).thenReturn(true);

        animalService.deleteAnimal(1L);

        verify(animalRepository).deleteById(1L);
    }

    @Test
    void deleteAnimal_whenNotExists_shouldThrowException() {
        when(animalRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> animalService.deleteAnimal(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Animal not found with id: 99");
    }
}
