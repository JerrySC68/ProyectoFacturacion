package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.ActComercial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActComercialRepository extends JpaRepository<ActComercial, String> {
    Optional<ActComercial> findByName(String url); // ?
}