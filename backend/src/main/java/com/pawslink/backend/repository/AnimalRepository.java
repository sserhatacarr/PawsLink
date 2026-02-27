package com.pawslink.backend.repository;

import com.pawslink.backend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByStatus(Animal.AnimalStatus status);

    /**
     * Find animals within a given radius (in kilometers) using the Haversine formula.
     */
    @Query(value = """
            SELECT * FROM animals a
            WHERE (
                6371 * acos(
                    cos(radians(:lat)) * cos(radians(a.latitude)) *
                    cos(radians(a.longitude) - radians(:lon)) +
                    sin(radians(:lat)) * sin(radians(a.latitude))
                )
            ) <= :radiusKm
            """, nativeQuery = true)
    List<Animal> findWithinRadius(
            @Param("lat") double latitude,
            @Param("lon") double longitude,
            @Param("radiusKm") double radiusKm
    );
}
